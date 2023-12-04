package br.com.scalemax.repository.impl;


import br.com.scalemax.acore.repository.impl.AbstractRepositoryImpl;
import br.com.scalemax.model.Afiliate;
import br.com.scalemax.model.dto.AfiliateDTO;
import br.com.scalemax.repository.AfiliateRepository;
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
public class AfiliateRepositoryImpl extends AbstractRepositoryImpl<Afiliate, AfiliateDTO, Long> implements AfiliateRepository {
    private final EntityManager entityManager;

    public AfiliateRepositoryImpl(EntityManager entityManager) {
        super(Afiliate.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Afiliate> filtrando(AfiliateDTO afiliateDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Afiliate> query = builder.createQuery(Afiliate.class);
        Root<Afiliate> root = query.from(Afiliate.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.adicionarRestricoes(builder, afiliateDTO, root);
        query.where(predicato);

        TypedQuery<Afiliate> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(afiliateDTO));
    }

    private Predicate[] adicionarRestricoes(CriteriaBuilder builder, AfiliateDTO afiliateDTO, Root<Afiliate> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(afiliateDTO.getName()))
            lista.add(builder.like(builder.lower(root.get("name")), "%" + afiliateDTO.getName().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private void adicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
        int paginaatual = page.getPageNumber();
        int totalporpagina = page.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalporpagina;

        tiped.setFirstResult(primeiroRegistroDaPagina);
        tiped.setMaxResults(totalporpagina);
    }

    private Long total(AfiliateDTO afiliateDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Afiliate> root = query.from(Afiliate.class);

        Predicate[] predicato = this.adicionarRestricoes(builder, afiliateDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
