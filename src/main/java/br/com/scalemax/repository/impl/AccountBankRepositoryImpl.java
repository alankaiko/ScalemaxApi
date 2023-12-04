package br.com.scalemax.repository.impl;


import br.com.scalemax.acore.repository.impl.AbstractRepositoryImpl;
import br.com.scalemax.model.AccountBank;
import br.com.scalemax.model.dto.AccountBankDTO;
import br.com.scalemax.repository.AccountBankRepository;
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
public class AccountBankRepositoryImpl extends AbstractRepositoryImpl<AccountBank, AccountBankDTO, Long> implements AccountBankRepository {
    private final EntityManager entityManager;

    public AccountBankRepositoryImpl(EntityManager entityManager) {
        super(AccountBank.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<AccountBank> filtrando(AccountBankDTO accountBankDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<AccountBank> query = builder.createQuery(AccountBank.class);
        Root<AccountBank> root = query.from(AccountBank.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.adicionarRestricoes(builder, accountBankDTO, root);
        query.where(predicato);

        TypedQuery<AccountBank> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(accountBankDTO));
    }

    private Predicate[] adicionarRestricoes(CriteriaBuilder builder, AccountBankDTO accountBankDTO, Root<AccountBank> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(accountBankDTO.getName()))
            lista.add(builder.like(builder.lower(root.get("name")), "%" + accountBankDTO.getName().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private void adicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
        int paginaatual = page.getPageNumber();
        int totalporpagina = page.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalporpagina;

        tiped.setFirstResult(primeiroRegistroDaPagina);
        tiped.setMaxResults(totalporpagina);
    }

    private Long total(AccountBankDTO accountBankDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<AccountBank> root = query.from(AccountBank.class);

        Predicate[] predicato = this.adicionarRestricoes(builder, accountBankDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
