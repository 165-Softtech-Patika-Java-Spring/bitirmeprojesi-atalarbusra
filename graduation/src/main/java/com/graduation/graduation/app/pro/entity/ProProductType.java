package com.graduation.graduation.app.pro.entity;

import com.graduation.graduation.app.gen.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRO_PRODUCT_TYPE")
@Getter
@Setter
public class ProProductType extends BaseEntity {

    public ProProductType() {

    }

    public ProProductType(Long id, String name, Long kdvRate) {
        this.id = id;
        this.name = name;
        this.kdvRate = kdvRate;
    }

    @Id
    @SequenceGenerator(name = "ProProductType" , sequenceName = "PRO_PRODUCT_TYPE_ID_SEQ")
    @GeneratedValue(generator = "ProProductType")
    private Long id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "RATE", nullable = false)
    private Long kdvRate;

}
