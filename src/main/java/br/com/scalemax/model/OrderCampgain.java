package br.com.scalemax.model;

import br.com.scalemax.acore.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Table
@Entity
public class OrderCampgain extends AbstractEntity {
    private String code;

    private String name;

    private Double price;

    @OneToOne
    @JoinColumn(name = "tbl_affiliate_id", referencedColumnName = "codigo")
    private Afiliate afiliate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Line> lines;
}
