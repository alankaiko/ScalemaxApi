package br.com.scalemax.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Contact {
    private String phone;
    private String phone2;
    private String cellPhone;
    private String email;
}
