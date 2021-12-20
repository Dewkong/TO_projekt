package com.objectzilla.model;

public enum TransactionCategory {
    RESTAURANTS("restauracje"),
    SHOPS("sklepy"),
    TIPS("napiwki"),
    TAXES("podatki"),
    NONE("");

    private final String friendlyName;



    TransactionCategory(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    @Override
    public String toString() {
        return friendlyName;
    }
}
