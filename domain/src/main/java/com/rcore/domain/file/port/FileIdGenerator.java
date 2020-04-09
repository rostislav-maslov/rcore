package com.rcore.domain.file.port;

import com.rcore.domain.base.port.BaseIdGenerator;
import com.rcore.domain.user.port.IdGenerator;

public interface FileIdGenerator<T> extends BaseIdGenerator<T>, IdGenerator<T> {
}
