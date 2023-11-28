package br.com.scalemax.repository.impl;


import br.com.scalemax.acore.repository.impl.AbstractRepositoryImpl;
import br.com.scalemax.model.Client;
import br.com.scalemax.model.dto.ClientDTO;
import br.com.scalemax.repository.ClientRepository;
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
public class ClientRepositoryImpl extends AbstractRepositoryImpl<Client, ClientDTO, Long> implements ClientRepository {
    private final EntityManager entityManager;

    public ClientRepositoryImpl(EntityManager entityManager) {
        super(Client.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Client> filtrando(ClientDTO clientDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = builder.createQuery(Client.class);
        Root<Client> root = query.from(Client.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = this.adicionarRestricoes(builder, clientDTO, root);
        query.where(predicato);

        TypedQuery<Client> tiped = this.entityManager.createQuery(query);
        this.adicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, this.total(clientDTO));
    }

    private Predicate[] adicionarRestricoes(CriteriaBuilder builder, ClientDTO clientDTO, Root<Client> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(clientDTO.getNome()))
            lista.add(builder.like(builder.lower(root.get("nome")), "%" + clientDTO.getNome().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private void adicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
        int paginaatual = page.getPageNumber();
        int totalporpagina = page.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalporpagina;

        tiped.setFirstResult(primeiroRegistroDaPagina);
        tiped.setMaxResults(totalporpagina);
    }

    private Long total(ClientDTO clientDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Client> root = query.from(Client.class);

        Predicate[] predicato = this.adicionarRestricoes(builder, clientDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
