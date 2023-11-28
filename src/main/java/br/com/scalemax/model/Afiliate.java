package br.com.scalemax.model;

import br.com.scalemax.acore.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class Afiliate extends AbstractEntity {
    private String name;
    private String email;
    private String password;
    private double commission;
}
