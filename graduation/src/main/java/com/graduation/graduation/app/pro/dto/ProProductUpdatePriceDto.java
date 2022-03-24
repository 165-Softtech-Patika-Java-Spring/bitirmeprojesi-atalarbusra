package com.graduation.graduation.app.pro.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProProductUpdatePriceDto {

    private Long productId;

    private double newPrice;
}
