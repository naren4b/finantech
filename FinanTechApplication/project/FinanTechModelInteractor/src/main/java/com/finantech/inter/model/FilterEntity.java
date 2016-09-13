package com.finantech.inter.model;

import java.util.ArrayList;
import java.util.List;
import com.finantech.inter.model.ConditionalValueEntity;

public class FilterEntity {

    List<ConditionalValueEntity> conditions = new ArrayList<ConditionalValueEntity>();

    public List<ConditionalValueEntity> getConditions() {
        return conditions;
    }

    public void setConditions(List<ConditionalValueEntity> conditions) {
        this.conditions = conditions;
    }

    public FilterEntity(ConditionalValueEntity conditionalValue) {
        super();
        conditions.add(conditionalValue);
    }

}
