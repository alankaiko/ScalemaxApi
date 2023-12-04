package br.com.scalemax.repository.impl;


import br.com.scalemax.acore.repository.impl.AbstractRepositoryImpl;
import br.com.scalemax.model.Bank;
import br.com.scalemax.model.dto.BankDTO;
import br.com.scalemax.repository.BankRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class BankRepositoryImpl extends AbstractRepositoryImpl<Bank, BankDTO, Long> implements BankRepository {
    private final EntityManager entityManager;

    public BankRepositoryImpl(EntityManager entityManager) {
        super(Bank.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Bank> filtrando(BankDTO bankDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Bank> query = builder.createQuery(Bank.class);
        Root<Bank> root = query.from(Bank.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.adicionarRestricoes(builder, bankDTO, root);
        query.where(predicato);

        TypedQuery<Bank> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(bankDTO));
    }

    private Predicate[] adicionarRestricoes(CriteriaBuilder builder, BankDTO bankDTO, Root<Bank> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(bankDTO.getName()))
            lista.add(builder.like(builder.lower(root.get("name")), "%" + bankDTO.getName().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private void adicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
        int paginaatual = page.getPageNumber();
        int totalporpagina = page.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalporpagina;

        tiped.setFirstResult(primeiroRegistroDaPagina);
        tiped.setMaxResults(totalporpagina);
    }

    private Long total(BankDTO bankDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Bank> root = query.from(Bank.class);

        Predicate[] predicato = this.adicionarRestricoes(builder, bankDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
