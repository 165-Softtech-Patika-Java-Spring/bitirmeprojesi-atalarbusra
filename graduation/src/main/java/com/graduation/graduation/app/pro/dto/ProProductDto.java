package com.graduation.graduation.app.pro.dto;


import java.math.BigDecimal;

import com.graduation.graduation.app.pro.enums.ProProductTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProProductDto {

    private Long id;

    private String name;

    private Double price;

    private Double priceWithKdv;

    private BigDecimal amount;

    private Long productTypeId;

}
