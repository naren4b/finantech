package filter;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Filters.ne;
import static com.mongodb.client.model.Filters.nin;

import org.bson.conversions.Bson;

public class ConditionalValue {

    String operator;

    Object value;

    String key;

    public ConditionalValue(String key, String operator, Object value) {
        super();
        this.operator = operator;
        this.value = value;
        this.key = key;
    }

    public Bson getCondtion() {
        if (operator.equals("gt")) {
            return gt(key, value);
        } else if (operator.equals("gte")) {
            return gte(key, value);
        } else if (operator.equals("lt")) {
            return lt(key, value);
        } else if (operator.equals("lte")) {
            return lte(key, value);
        } else if (operator.equals("ne")) {
            return ne(key, value);
        } else if (operator.equals("in")) {
            return in(key, value);
        } else if (operator.equals("nin")) {
            return nin(key, value);
        } else {
            return eq(key, value);
        }

    }

}
