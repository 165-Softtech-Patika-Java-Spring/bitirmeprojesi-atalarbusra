package com.graduation.graduation.app.acc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccAccountSaveRequestDto {

    private String name;
    private String surname;
    private String userName;
    private String password;
}
