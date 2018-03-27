package com.entity;

import java.io.Serializable;

/**
 * Created by 佘松 on 2017/10/13.
 */

public class User_info implements Serializable{
    private String name;
    private String uid;
    private boolean isLogin=false;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public String getUid() {
        return uid;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
