package com.example.computingclub.userset;

public final class VisitHolder {
    private User user;
    private final static VisitHolder INSTANCE = new VisitHolder();

    private VisitHolder() {}

    public static VisitHolder getInstance() {
        return INSTANCE;
    }

    public void setUser(User u) {
        this.user = u;
    }

    public User getUser() {
        return this.user;
    }
}