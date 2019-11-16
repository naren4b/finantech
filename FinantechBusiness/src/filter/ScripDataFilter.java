package filter;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.nor;
import static com.mongodb.client.model.Filters.or;

import java.util.List;

import org.bson.conversions.Bson;

import com.mongodb.client.model.Filters;

public class ScripDataFilter {

    public static Bson getFilter(List<ConditionalValue> values,
            String condtionOperator) {
        Bson filter = null;
        Bson[] conditions = new Bson[values.size()];
        int index = 0;
        for (ConditionalValue value : values) {
            conditions[index++] = value.getCondtion();

        }
        if (condtionOperator.equals("or")) {
            filter = or(conditions);
        } else if (condtionOperator.equals("not")) {
            filter = Filters.not(conditions[0]);
        } else if (condtionOperator.equals("nor")) {
            filter = nor(conditions);
        } else {
            filter = and(conditions);
        }

        return filter;
    }

}
