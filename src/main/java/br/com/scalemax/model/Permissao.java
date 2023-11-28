package br.com.scalemax.model;

import br.com.scalemax.acore.model.AbstractEntity;
import br.com.scalemax.model.enuns.EnumPermissao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
public class Permissao extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    private EnumPermissao descricao;
}
