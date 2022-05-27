package com.csStudy.CardGame.domain;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
