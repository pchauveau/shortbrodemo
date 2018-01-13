package com.shortbrodemo.pch.shortbrodemo.event;

/**
 * Used with EventBus
 */
public class GetAllImageEvent {
    boolean isOk = false;

    public GetAllImageEvent(boolean isOk) {
        this.isOk = isOk;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }
}
