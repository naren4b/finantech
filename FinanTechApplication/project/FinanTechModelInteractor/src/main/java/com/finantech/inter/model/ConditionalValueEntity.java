package com.finantech.inter.model;

public class ConditionalValueEntity {

    public static final String[] OPS = { "eq", "gt", "gte", "lt", "lte",
            "ne" };

    String operator;

    Object value;

    String key;

    public String getOperator() {
        return operator;
    }

    public Object getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    public ConditionalValueEntity(String key, String operator,
            Object value) {
        super();
        this.operator = operator;
        this.value = value;
        this.key = key;
    }

    public String getCondtionValue() {
        return key + "#" + operator + "#" + value;
    }

}
