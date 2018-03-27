package com.Application;

import android.app.Application;
import android.content.Context;

import com.entity.User_info;

/**
 * Created by 佘松 on 2017/10/11.
 */

public class MyApp extends Application {
    private static User_info user;
    private static Context context;
    public static User_info getUser() {
        return user;
    }

    public static void setUser(User_info userInfo) {
        user = userInfo;
    }
    public void onCreate(){
        user=new User_info();
        context=getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
