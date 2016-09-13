package com.finantech.inter.bean;

import java.util.ArrayList;
import java.util.List;

public class FilterBean{

    List<ConditionalValueBean> conditions = new ArrayList<ConditionalValueBean>();

    public List<ConditionalValueBean> getConditions() {
        return conditions;
    }

    public void setConditions(List<ConditionalValueBean> conditions) {
        this.conditions = conditions;
    }

    public FilterBean(ConditionalValueBean conditionalValue) {
        super();
        conditions.add(conditionalValue);
    }

}
