package com.example.bustest.Manager;

import com.example.bustest.db.User;

/**
 * Created by lenovo on 2018/3/28.
 */

public class UserManager {
    private static User user=null;
    public void setUser(User user_t){
        user=user_t;
    }
}
