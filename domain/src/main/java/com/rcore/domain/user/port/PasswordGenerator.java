package com.rcore.domain.user.port;

public interface PasswordGenerator {
    public abstract String generate(String userId, String cleanPassword);

    public abstract Boolean check(String userId, String cleanPassword, String protectedPassword);
}
