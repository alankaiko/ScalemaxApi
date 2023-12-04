package br.com.scalemax.repository.impl;


import br.com.scalemax.acore.repository.impl.AbstractRepositoryImpl;
import br.com.scalemax.model.Permissao;
import br.com.scalemax.model.dto.PermissaoDTO;
import br.com.scalemax.repository.PermissaoRepository;
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
public class PermissaoRepositoryImpl extends AbstractRepositoryImpl<Permissao, PermissaoDTO, Long> implements PermissaoRepository {
    private final EntityManager entityManager;

    public PermissaoRepositoryImpl(EntityManager entityManager) {
        super(Permissao.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Permissao> filtrando(PermissaoDTO permissaoDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Permissao> query = builder.createQuery(Permissao.class);
        Root<Permissao> root = query.from(Permissao.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.adicionarRestricoes(builder, permissaoDTO, root);
        query.where(predicato);

        TypedQuery<Permissao> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(permissaoDTO));
    }

    private Predicate[] adicionarRestricoes(CriteriaBuilder builder, PermissaoDTO permissaoDTO, Root<Permissao> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(permissaoDTO.getName()))
            lista.add(builder.like(builder.lower(root.get("name")), "%" + permissaoDTO.getName().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private void adicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
        int paginaatual = page.getPageNumber();
        int totalporpagina = page.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalporpagina;

        tiped.setFirstResult(primeiroRegistroDaPagina);
        tiped.setMaxResults(totalporpagina);
    }

    private Long total(PermissaoDTO permissaoDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Permissao> root = query.from(Permissao.class);

        Predicate[] predicato = this.adicionarRestricoes(builder, permissaoDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
