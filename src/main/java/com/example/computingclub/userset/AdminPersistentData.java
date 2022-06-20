package com.example.computingclub.userset;

import java.io.Serializable;

public class AdminPersistentData implements Serializable {
    private static int userCount;

    public static int getUserCount(boolean incrementOrNot) {
        if(incrementOrNot) {
            userCount++;
        }
        return userCount;
    }

    public static void setUserCount(int userCount) {
        AdminPersistentData.userCount = userCount;
    }
}
