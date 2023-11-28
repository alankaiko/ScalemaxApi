package br.com.scalemax.repository;

import br.com.scalemax.acore.repository.AbstractRepository;
import br.com.scalemax.model.Permissao;
import br.com.scalemax.model.dto.PermissaoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PermissaoRepository extends AbstractRepository<Permissao, PermissaoDTO, Long> {
    Page<Permissao> filtrando(PermissaoDTO permissaoDTO, Pageable pageable);
}
