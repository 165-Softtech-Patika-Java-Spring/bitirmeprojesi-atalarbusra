package com.graduation.graduation.app.pro.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class ProProductTypeDto {
    private Long id;

    private String name;

    private Long kdvRate;
}
