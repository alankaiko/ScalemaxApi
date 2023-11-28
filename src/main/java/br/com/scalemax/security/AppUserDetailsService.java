package br.com.scalemax.security;

import br.com.scalemax.model.Usuario;
import br.com.scalemax.model.enuns.EnumPermissao;
import br.com.scalemax.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(login);

        if (usuario == null)
            throw new UsernameNotFoundException("Usuário e/ou senha incorretos");

        return new UsuarioSistema(usuario, getPermissoes(usuario));
    }

    private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        usuario.getPermissoes().forEach(permissao -> authorities.add(new SimpleGrantedAuthority(permissao.getDescricao().toString())));
        authorities.add(new SimpleGrantedAuthority(EnumPermissao.ROLE_ZE.toString()));
        return authorities;
    }

}
