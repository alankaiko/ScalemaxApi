package br.com.scalemax.repository.impl;


import br.com.scalemax.acore.repository.impl.AbstractRepositoryImpl;
import br.com.scalemax.model.Campaign;
import br.com.scalemax.model.dto.CampaignDTO;
import br.com.scalemax.repository.CampaignRepository;
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
public class CampaignRepositoryImpl extends AbstractRepositoryImpl<Campaign, CampaignDTO, Long> implements CampaignRepository {
    private final EntityManager entityManager;

    public CampaignRepositoryImpl(EntityManager entityManager) {
        super(Campaign.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Campaign> filtrando(CampaignDTO campaignDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Campaign> query = builder.createQuery(Campaign.class);
        Root<Campaign> root = query.from(Campaign.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.adicionarRestricoes(builder, campaignDTO, root);
        query.where(predicato);

        TypedQuery<Campaign> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(campaignDTO));
    }

    private Predicate[] adicionarRestricoes(CriteriaBuilder builder, CampaignDTO campaignDTO, Root<Campaign> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(campaignDTO.getNome()))
            lista.add(builder.like(builder.lower(root.get("nome")), "%" + campaignDTO.getNome().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private void adicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
        int paginaatual = page.getPageNumber();
        int totalporpagina = page.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalporpagina;

        tiped.setFirstResult(primeiroRegistroDaPagina);
        tiped.setMaxResults(totalporpagina);
    }

    private Long total(CampaignDTO campaignDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Campaign> root = query.from(Campaign.class);

        Predicate[] predicato = this.adicionarRestricoes(builder, campaignDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
