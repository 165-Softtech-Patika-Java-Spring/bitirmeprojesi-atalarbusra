package com.graduation.graduation.app.acc.enums;

import com.graduation.graduation.app.gen.enums.BaseErrorMessage;

public enum AccErrorMessage implements BaseErrorMessage {
    ;


    private String message;
    AccErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
