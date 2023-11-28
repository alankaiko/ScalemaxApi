package br.com.scalemax.acore.service;


import br.com.scalemax.acore.model.AbstractDTO;
import br.com.scalemax.acore.model.AbstractEntity;

import java.util.List;

public interface AbstractService<T extends AbstractEntity, D extends AbstractDTO> {
    T salvar(T entidade);

    void deletar(Long codigo);

    T buscarId(Long codigo);

    List<T> listar();
}
