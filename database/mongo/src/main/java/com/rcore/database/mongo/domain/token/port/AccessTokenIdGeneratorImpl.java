package com.rcore.database.mongo.domain.token.port;

import com.rcore.domain.base.port.impl.StringIdGenerator;
import com.rcore.domain.token.port.AccessTokenIdGenerator;

public class AccessTokenIdGeneratorImpl extends StringIdGenerator implements AccessTokenIdGenerator<String> {
}
