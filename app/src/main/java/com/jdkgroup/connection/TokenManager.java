package com.jdkgroup.connection;

public interface TokenManager {
    String getToken();
    boolean hasToken();
    void clearToken();
    void refreshToken();
}
