package com.graduation.graduation.app.pro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class ProProductDetails {
    private final Long productTypeId;
    private final String productTypeName;
    private final Long rate;
    private final Double minPrice;
    private final Double maxPrice;
    private final Double avgPrice;
    private final Long countProduct;
}
