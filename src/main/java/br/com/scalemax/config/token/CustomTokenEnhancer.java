package br.com.scalemax.config.token;

import br.com.scalemax.security.UsuarioSistema;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UsuarioSistema usuario = (UsuarioSistema) authentication.getPrincipal();

        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", usuario.getUsuario().getNome());
        dados.put("login", usuario.getUsuario().getLogin());
        dados.put("codigo", usuario.getUsuario().getCodigo() + "");

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(dados);
        return accessToken;
    }

}
