package com.graduation.graduation.app.pro.entity;

import com.graduation.graduation.app.gen.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRO_PRODUCT")
@Getter
@Setter
public class ProProduct extends BaseEntity {

    @Id
    @SequenceGenerator(name = "ProProduct" , sequenceName = "PRO_PRODUCT_ID_SEQ")
    @GeneratedValue(generator = "ProProduct")
    private Long id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "PRICE", length = 100, nullable = false)
    private Double price;

    @Column(name = "PRICE_WITH_KDV", length = 100, nullable = false)
    private Double priceWithKdv;

    @Column(name = "AMOUNT", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "ID_PRODUCT_TYPE")
    private Long productTypeId;
}
