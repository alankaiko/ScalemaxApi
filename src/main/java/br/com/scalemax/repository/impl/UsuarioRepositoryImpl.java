package br.com.scalemax.repository.impl;

import br.com.scalemax.acore.repository.impl.AbstractRepositoryImpl;
import br.com.scalemax.model.Usuario;
import br.com.scalemax.model.dto.UsuarioDTO;
import br.com.scalemax.repository.UsuarioRepository;
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
public class UsuarioRepositoryImpl extends AbstractRepositoryImpl<Usuario, UsuarioDTO, Long> implements UsuarioRepository {
    private final EntityManager entityManager;

    public UsuarioRepositoryImpl(EntityManager entityManager) {
        super(Usuario.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Page<Usuario> filtrando(UsuarioDTO usuarioDTO, Pageable pageable) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
        Root<Usuario> root = query.from(Usuario.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = AdicionarRestricoes(builder, usuarioDTO, root);
        query.where(predicato);

        TypedQuery<Usuario> tiped = this.entityManager.createQuery(query);
        AdicionarPaginacao(tiped, pageable);

        return new PageImpl<>(tiped.getResultList(), pageable, Total(usuarioDTO));
    }

    @Override
    public Usuario findByLogin(String login) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> query = builder.createQuery(Usuario.class);
        Root<Usuario> root = query.from(Usuario.class);

        query.orderBy(builder.asc(root.get("codigo")));
        Predicate[] predicato = AdicionarRestricoesLogin(builder, login, root);
        query.where(predicato);

        TypedQuery<Usuario> tiped = this.entityManager.createQuery(query);

        return tiped.getSingleResult();
    }

    private Predicate[] AdicionarRestricoes(CriteriaBuilder builder, UsuarioDTO usuarioDTO, Root<Usuario> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(usuarioDTO.getNome()))
            lista.add(builder.like(builder.lower(root.get("nome")), "%" + usuarioDTO.getNome().toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private Predicate[] AdicionarRestricoesLogin(CriteriaBuilder builder, String login, Root<Usuario> root) {
        List<Predicate> lista = new ArrayList<>();

        if (!StringUtils.isEmpty(login))
            lista.add(builder.like(builder.lower(root.get("nome")), "%" + login.toLowerCase() + "%"));

        return lista.toArray(new Predicate[lista.size()]);
    }

    private void AdicionarPaginacao(TypedQuery<?> tiped, Pageable page) {
        int paginaatual = page.getPageNumber();
        int totalporpagina = page.getPageSize();
        int primeiroRegistroDaPagina = paginaatual * totalporpagina;

        tiped.setFirstResult(primeiroRegistroDaPagina);
        tiped.setMaxResults(totalporpagina);
    }

    private Long Total(UsuarioDTO usuarioDTO) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Usuario> root = query.from(Usuario.class);

        Predicate[] predicato = AdicionarRestricoes(builder, usuarioDTO, root);
        query.where(predicato);
        query.select(builder.count(root));
        return this.entityManager.createQuery(query).getSingleResult();
    }
}
