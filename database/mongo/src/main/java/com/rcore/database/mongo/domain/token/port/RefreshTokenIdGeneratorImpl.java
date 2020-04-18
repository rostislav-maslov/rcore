package com.rcore.database.mongo.domain.token.port;

import com.rcore.domain.base.port.impl.StringIdGenerator;
import com.rcore.domain.token.port.RefreshTokenIdGenerator;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenIdGeneratorImpl extends StringIdGenerator implements RefreshTokenIdGenerator<String> {
}
