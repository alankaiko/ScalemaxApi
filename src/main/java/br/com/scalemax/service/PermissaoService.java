package br.com.scalemax.service;

import br.com.scalemax.acore.service.AbstractService;
import br.com.scalemax.model.Permissao;
import br.com.scalemax.model.dto.PermissaoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissaoService extends AbstractService<Permissao, PermissaoDTO> {
    Page<Permissao> filtrando(PermissaoDTO permissaoDTO, Pageable pageable);
}
