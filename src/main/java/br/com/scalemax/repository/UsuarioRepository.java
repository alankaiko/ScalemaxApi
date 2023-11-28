
package br.com.scalemax.repository;

import br.com.scalemax.acore.repository.AbstractRepository;
import br.com.scalemax.model.Usuario;
import br.com.scalemax.model.dto.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UsuarioRepository extends AbstractRepository<Usuario, UsuarioDTO, Long> {
    Page<Usuario> filtrando(UsuarioDTO usuarioDTO, Pageable pageable);

    Usuario findByLogin(String login);
}
