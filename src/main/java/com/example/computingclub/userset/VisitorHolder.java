package com.example.computingclub.userset;

public final class VisitorHolder {

    private User user;
    private final static VisitorHolder INSTANCE = new VisitorHolder();

    private VisitorHolder() {}

    public static VisitorHolder getInstance() {
        return INSTANCE;
    }

    public void setUser(User u) {
        this.user = u;
    }

    public User getUser() {
        return this.user;
    }
}