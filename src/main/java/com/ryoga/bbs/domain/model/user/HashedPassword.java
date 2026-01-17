package com.ryoga.bbs.domain.model.user;

public class HashedPassword {
    private String value;

    public HashedPassword(String value) {
        if(value == null || value.isEmpty()) {
            throw new IllegalArgumentException("パスワードは必須です");
        }
        this.value = value;
    }

    public String value() {
        return value;
    }
}
