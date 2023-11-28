package br.com.scalemax.service.impl;

import br.com.scalemax.acore.service.impl.AbstractServiceImpl;
import br.com.scalemax.model.Permissao;
import br.com.scalemax.model.dto.PermissaoDTO;
import br.com.scalemax.repository.PermissaoRepository;
import br.com.scalemax.service.PermissaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissaoServiceImpl extends AbstractServiceImpl<Permissao, PermissaoDTO> implements PermissaoService {
    private final PermissaoRepository permissaoRepository;

    public PermissaoServiceImpl(PermissaoRepository permissaoRepository) {
        super(permissaoRepository);
        this.permissaoRepository = permissaoRepository;
    }

    @Override
    public Page<Permissao> filtrando(PermissaoDTO permissaoDTO, Pageable pageable) {
        return this.permissaoRepository.filtrando(permissaoDTO, pageable);
    }
}
