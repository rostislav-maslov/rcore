package com.ub.core.json.mapper;

import org.bson.types.ObjectId;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

public class UbCoreObjectMapper extends ObjectMapper {

    public UbCoreObjectMapper() {
        SimpleModule module = new SimpleModule("ObjectIdmodule", Version.unknownVersion());
        module.addSerializer(ObjectId.class, new ObjectIdSerializer());
        this.registerModule(module);
    }

}
