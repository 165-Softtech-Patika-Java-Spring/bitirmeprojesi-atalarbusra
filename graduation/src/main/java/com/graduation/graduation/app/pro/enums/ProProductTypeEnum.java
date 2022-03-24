package com.graduation.graduation.app.pro.enums;

public enum ProProductTypeEnum {

    Gıda(1L),
    Kırtasiye(2L),
    Giyecek(3L),
    Teknoloji(4L),
    Temizlik(5L),
    Diğer(6L);

    private Long type;

    ProProductTypeEnum(Long type) {
        this.type = type;
    }
}
