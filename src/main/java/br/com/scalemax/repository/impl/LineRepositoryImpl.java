package br.com.scalemax.repository.impl;


import br.com.scalemax.acore.repository.impl.AbstractRepositoryImpl;
import br.com.scalemax.model.Line;
import br.com.scalemax.model.dto.LineDTO;
import br.com.scalemax.repository.LineRepository;
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
public class LineRepositoryImpl extends AbstractRepositoryImpl<Line, LineDTO, Long> implements LineRepository {
    private final EntityManager entityManager;

    public LineRepositoryImpl(EntityManager entityManager) {
        super(Line.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Line> filtrando(LineDTO lineDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Line> query = builder.createQuery(Line.class);
        Root<Line> root = query.from(Line.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.adicionarRestricoes(builder, lineDTO, root);
        query.where(predicato);

        TypedQuery<Line> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(lineDTO));
    }

    private Predicate[] adicionarRestricoes(CriteriaBuilder builder, LineDTO lineDTO, Root<Line> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(lineDTO.getName()))
            lista.add(builder.like(builder.lower(root.get("name")), "%" + lineDTO.getName().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private void adicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
        int paginaatual = page.getPageNumber();
        int totalporpagina = page.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalporpagina;

        tiped.setFirstResult(primeiroRegistroDaPagina);
        tiped.setMaxResults(totalporpagina);
    }

    private Long total(LineDTO lineDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Line> root = query.from(Line.class);

        Predicate[] predicato = this.adicionarRestricoes(builder, lineDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
