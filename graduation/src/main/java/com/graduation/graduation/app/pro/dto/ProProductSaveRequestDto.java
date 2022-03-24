package com.graduation.graduation.app.pro.dto;

import com.graduation.graduation.app.pro.enums.ProProductTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProProductSaveRequestDto {

    private String name;

    private Double price;

    private BigDecimal amount;

    private Long productTypeId;

}
