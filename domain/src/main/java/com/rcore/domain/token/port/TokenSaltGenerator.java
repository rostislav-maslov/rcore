package com.rcore.domain.token.port;

import com.rcore.domain.base.port.BaseIdGenerator;

public interface TokenSaltGenerator {
    String generate();
}
