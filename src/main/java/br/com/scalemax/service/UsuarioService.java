package br.com.scalemax.service;

import br.com.scalemax.acore.service.AbstractService;
import br.com.scalemax.model.Usuario;
import br.com.scalemax.model.dto.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService extends AbstractService<Usuario, UsuarioDTO> {
    Page<Usuario> filtrando(UsuarioDTO usuarioDTO, Pageable pageable);
}
