package br.com.scalemax.acore.service.impl;

import br.com.scalemax.acore.model.AbstractDTO;
import br.com.scalemax.acore.model.AbstractEntity;
import br.com.scalemax.acore.repository.AbstractRepository;
import br.com.scalemax.acore.service.AbstractService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public abstract class AbstractServiceImpl<T extends AbstractEntity, D extends AbstractDTO> implements AbstractService<T, D> {
    protected AbstractRepository<T, D, Long> dao;

    public AbstractServiceImpl(AbstractRepository<T, D, Long> dao) {
        this.dao = dao;
    }

    @Override
    public T salvar(T entity) {
        return this.dao.save(entity);
    }

    @Override
    public void deletar(Long codigo) {
        this.dao.deleteById(codigo);
    }

    @Override
    public T buscarId(Long codigo) {
        return this.dao.findById(codigo).orElse((T) null);
    }

    @Override
    public List<T> listar() {
        return this.dao.findAll();
    }

    @Override
    public T atualizar(Long codigo, T entidade) {
        T entidadeSalva = this.buscarId(codigo);
        BeanUtils.copyProperties(entidade, entidadeSalva, "codigo");
        return this.salvar(entidadeSalva);
    }
}
