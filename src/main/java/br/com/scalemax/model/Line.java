package br.com.scalemax.model;

import br.com.scalemax.acore.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Setter
@Getter
@Table
@Entity
public class Line extends AbstractEntity {
    private String code;

    private String name;

    private Double price;

    private String barcode;

    @OneToOne
    @JoinColumn(name = "tbl_affiliate_id", referencedColumnName = "codigo")
    private Afiliate afiliate;
}
