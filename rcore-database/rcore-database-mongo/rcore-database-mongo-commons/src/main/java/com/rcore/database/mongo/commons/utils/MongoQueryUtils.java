package com.rcore.database.mongo.commons.utils;

import lombok.experimental.UtilityClass;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Arrays;

@UtilityClass
public class MongoQueryUtils {
    private final char[] notSupportedSymbols = "<([{\\^-=$!|]})?*+.>".toCharArray();

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
                        .map(f -> Criteria.where(f).regex(shieldNotSupportedSymbols(query)))
                        .toArray(Criteria[]::new)
        );
    }
}
