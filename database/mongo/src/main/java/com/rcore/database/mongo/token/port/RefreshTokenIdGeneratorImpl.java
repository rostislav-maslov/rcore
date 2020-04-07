package com.rcore.database.mongo.token.port;

import com.rcore.domain.base.port.impl.StringIdGenerator;
import com.rcore.domain.token.port.RefreshTokenIdGenerator;

public class RefreshTokenIdGeneratorImpl extends StringIdGenerator implements RefreshTokenIdGenerator<String> {
}
