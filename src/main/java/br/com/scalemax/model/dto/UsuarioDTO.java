package br.com.scalemax.model.dto;

import br.com.scalemax.acore.model.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO extends AbstractDTO {
    private String nome;
}
