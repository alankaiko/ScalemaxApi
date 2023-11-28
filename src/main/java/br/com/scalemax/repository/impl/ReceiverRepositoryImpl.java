package br.com.scalemax.repository.impl;


import br.com.scalemax.acore.repository.impl.AbstractRepositoryImpl;
import br.com.scalemax.model.Receiver;
import br.com.scalemax.model.dto.ReceiverDTO;
import br.com.scalemax.repository.ReceiverRepository;
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
public class ReceiverRepositoryImpl extends AbstractRepositoryImpl<Receiver, ReceiverDTO, Long> implements ReceiverRepository {
    private final EntityManager entityManager;

    public ReceiverRepositoryImpl(EntityManager entityManager) {
        super(Receiver.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Receiver> filtrando(ReceiverDTO receiverDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Receiver> query = builder.createQuery(Receiver.class);
        Root<Receiver> root = query.from(Receiver.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.adicionarRestricoes(builder, receiverDTO, root);
        query.where(predicato);

        TypedQuery<Receiver> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(receiverDTO));
    }

    private Predicate[] adicionarRestricoes(CriteriaBuilder builder, ReceiverDTO receiverDTO, Root<Receiver> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(receiverDTO.getNome()))
            lista.add(builder.like(builder.lower(root.get("nome")), "%" + receiverDTO.getNome().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private void adicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
        int paginaatual = page.getPageNumber();
        int totalporpagina = page.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalporpagina;

        tiped.setFirstResult(primeiroRegistroDaPagina);
        tiped.setMaxResults(totalporpagina);
    }

    private Long total(ReceiverDTO receiverDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Receiver> root = query.from(Receiver.class);

        Predicate[] predicato = this.adicionarRestricoes(builder, receiverDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
