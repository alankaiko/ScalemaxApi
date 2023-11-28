package br.com.scalemax.model;

import br.com.scalemax.acore.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table
public class Client extends AbstractEntity {
    private String name;
    private String rg;
    private String cpf;
    private String login;
    private String password;

    @Embedded
    private Contact contact;

    @Embedded
    private Address address;
}
