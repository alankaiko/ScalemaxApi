package br.com.scalemax.model;

import br.com.scalemax.acore.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table
public class AccountBank extends AbstractEntity {
    private String email;

    private String name;

    private String code;

    private String agency;

    private String passwordCard;

    private String passwordEmail;

    private String passwordApp;

    @OneToOne
    @JoinColumn(name = "tbl_bank_id", referencedColumnName = "codigo")
    private Bank bank;
}
