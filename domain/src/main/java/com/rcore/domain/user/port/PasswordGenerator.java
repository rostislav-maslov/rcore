package com.rcore.domain.user.port;

public interface PasswordGenerator {
    String generate(String userId, String cleanPassword);

    Boolean check(String userId, String cleanPassword, String protectedPassword);
}
