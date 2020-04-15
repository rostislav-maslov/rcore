package com.rcore.database.mongo.domain.token.port;

import com.rcore.domain.base.port.impl.StringIdGenerator;
import com.rcore.domain.token.port.AccessTokenIdGenerator;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenIdGeneratorImpl extends StringIdGenerator implements AccessTokenIdGenerator<String> {
}
