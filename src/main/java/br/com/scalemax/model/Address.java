package br.com.scalemax.model;

import br.com.scalemax.model.enuns.EnumEstado;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Embeddable
public class Address {
    private String street;
    private String complement;
    private String neighborhood;
    private String city;

    @Enumerated(EnumType.STRING)
    private EnumEstado uf;

    private String cep;
    private String number;
}
