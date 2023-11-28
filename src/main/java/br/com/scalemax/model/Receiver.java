package br.com.scalemax.model;

import br.com.scalemax.acore.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table
public class Receiver extends AbstractEntity {
    private LocalDate birthDate;

    @Column(length = 11)
    private String cpf;

    private String nameMother;

    private String whastapp;

    @OneToOne
    @JoinColumn(name = "tbl_afiliate_id", referencedColumnName = "codigo")
    private Afiliate afiliate;

}
