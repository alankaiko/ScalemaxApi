package br.com.scalemax.repository.impl;


import br.com.scalemax.acore.repository.impl.AbstractRepositoryImpl;
import br.com.scalemax.model.OrderCampgain;
import br.com.scalemax.model.dto.OrderCampgainDTO;
import br.com.scalemax.repository.OrderCampgainRepository;
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
public class OrderCampgainRepositoryImpl extends AbstractRepositoryImpl<OrderCampgain, OrderCampgainDTO, Long> implements OrderCampgainRepository {
    private final EntityManager entityManager;

    public OrderCampgainRepositoryImpl(EntityManager entityManager) {
        super(OrderCampgain.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<OrderCampgain> filtrando(OrderCampgainDTO orderCampgainDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderCampgain> query = builder.createQuery(OrderCampgain.class);
        Root<OrderCampgain> root = query.from(OrderCampgain.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.adicionarRestricoes(builder, orderCampgainDTO, root);
        query.where(predicato);

        TypedQuery<OrderCampgain> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(orderCampgainDTO));
    }

    private Predicate[] adicionarRestricoes(CriteriaBuilder builder, OrderCampgainDTO orderCampgainDTO, Root<OrderCampgain> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(orderCampgainDTO.getNome()))
            lista.add(builder.like(builder.lower(root.get("nome")), "%" + orderCampgainDTO.getNome().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private void adicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
        int paginaatual = page.getPageNumber();
        int totalporpagina = page.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalporpagina;

        tiped.setFirstResult(primeiroRegistroDaPagina);
        tiped.setMaxResults(totalporpagina);
    }

    private Long total(OrderCampgainDTO orderCampgainDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<OrderCampgain> root = query.from(OrderCampgain.class);

        Predicate[] predicato = this.adicionarRestricoes(builder, orderCampgainDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
