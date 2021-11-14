package com.booknotes.base.model;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private final String jwt;
    private final long userId;

    public AuthenticationResponse(String jwt, long userId) {
        this.jwt = jwt;
        this.userId = userId;
    }

    public String getJwt() {
        return this.jwt;
    }
    
    public long getUserId() {
    	return this.userId;
    }
}