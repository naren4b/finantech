package com.finantech.inter.bean;

public class ConditionalValueBean {

    public static final String[] OPS = { "eq", "gt", "gte", "lt", "lte",
            "ne" };

    String operator;

    Object value;

    String key;

    public ConditionalValueBean(String key, String operator, Object value) {
        super();
        this.operator = operator;
        this.value = value;
        this.key = key;
    }

    public String getCondtionValue() {
        return key + "#" + operator + "#" + value;
    }

    public static String[] getOps() {
        return OPS;
    }

    public String getOperator() {
        return operator;
    }

    public Object getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

}
