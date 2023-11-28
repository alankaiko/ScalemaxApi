package br.com.scalemax.model;

import br.com.scalemax.acore.model.AbstractEntity;
import br.com.scalemax.model.enuns.EnumStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Campaign extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    private EnumStatus status;

    private String code;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCampgain;

    @OneToOne
    @JoinColumn(name = "tbl_usuario_id", referencedColumnName = "codigo")
    private Usuario usuario;

    private boolean model;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderCampgain> orders;
}
