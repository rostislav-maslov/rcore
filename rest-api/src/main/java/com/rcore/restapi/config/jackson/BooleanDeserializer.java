package com.rcore.restapi.config.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.rcore.commons.utils.StringUtils;

import java.io.IOException;

public class BooleanDeserializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String str = p.getText().toLowerCase();
        if (!StringUtils.hasText(str))
            return null;

        if (str.equals("true"))
            return true;
        else if (str.equals("false"))
            return false;
        else
            return null;
    }
}
