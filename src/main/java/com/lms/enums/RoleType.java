package com.lms.enums;

public enum RoleType {
    ROLE_STAFF("Staff"),
    ROLE_MEMBER("Member"),
    ROLE_ADMIN("Admin");
    private String name;

    private RoleType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
