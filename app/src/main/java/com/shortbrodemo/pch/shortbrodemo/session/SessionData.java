package com.shortbrodemo.pch.shortbrodemo.session;

import com.shortbrodemo.pch.shortbrodemo.model.Base;

public class SessionData {
    private static final SessionData ourInstance = new SessionData();

    private Base base;


    public static SessionData getInstance() {
        return ourInstance;
    }

    private SessionData() {
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }
}
