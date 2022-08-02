package com.rcore.database.mongo.commons.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Arrays;
import java.util.Collection;

@UtilityClass
public class MongoQueryUtils {
    private final char[] notSupportedSymbols = "\\<([{^-=$!|]})?*+.>".toCharArray();

    public String removeNotSupportedSymbols(String string) {
        String result = string;
        if (string != null)
            for (char notSupportedSymbol : notSupportedSymbols)
                result = result.replaceAll(String.format("\\%s", notSupportedSymbol), "");

        return result;
    }

    public String shieldNotSupportedSymbols(String string) {
        String result = string;

        if (string != null)
            for (char notSupportedSymbol : notSupportedSymbols)
                result = result.replaceAll(String.format("\\%s", notSupportedSymbol), String.format("\\\\%s", notSupportedSymbol));

        return result;
    }

    public Criteria generateQueryRegEXCriteria(String query, String... fields) {
        return new Criteria().orOperator(
                Arrays.stream(fields)
                        .map(f -> Criteria.where(f).regex(shieldNotSupportedSymbols(query), "i"))
                        .toArray(Criteria[]::new)
        );
    }

    public Criteria and(Collection<Criteria> collection) {
        if (collection.isEmpty())
            return new Criteria();
        else
            return new Criteria().andOperator(collection.toArray(Criteria[]::new));
    }
}
