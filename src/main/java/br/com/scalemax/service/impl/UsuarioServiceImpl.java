package br.com.scalemax.service.impl;


import br.com.scalemax.acore.service.impl.AbstractServiceImpl;
import br.com.scalemax.model.Usuario;
import br.com.scalemax.model.dto.UsuarioDTO;
import br.com.scalemax.repository.UsuarioRepository;
import br.com.scalemax.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioServiceImpl extends AbstractServiceImpl<Usuario, UsuarioDTO> implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Page<Usuario> filtrando(UsuarioDTO usuarioDTO, Pageable pageable) {
        return this.usuarioRepository.filtrando(usuarioDTO, pageable);
    }
}
