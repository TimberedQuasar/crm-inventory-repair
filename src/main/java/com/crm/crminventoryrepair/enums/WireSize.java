package com.crm.crminventoryrepair.enums;

public enum WireSize {
    THIN("0,8"),
    MEDIUM("1,0"),
    THICK("1,2");

    private final String value;

    WireSize(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
