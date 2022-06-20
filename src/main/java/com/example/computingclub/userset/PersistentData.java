package com.example.computingclub.userset;

import java.io.Serializable;

public class PersistentData implements Serializable {
    private static int userCount;

    public static int getUserCount() {
        userCount++;
        return userCount;
    }
}
