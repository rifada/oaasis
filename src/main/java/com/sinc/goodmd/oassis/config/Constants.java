package com.sinc.goodmd.oassis.config;

public interface Constants {
    String AUTHORIZATION = "Authorization";
    String AUTH_PREFIX = "Bearer ";
    String USERID = "USERID";
    String USERROLE = "USERROLE";
    Long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60L;
    Long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 1L;
}
