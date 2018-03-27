package com.util;

import com.entity.User_info;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Created by 佘松 on 2017/10/12.
 */

public class GlobalVariables implements Serializable,Cloneable {
     public static User_info user;
    private GlobalVariables instance;
    private static final String filePath="/data/data/com.dentist/files/userInfo.data";

    public  User_info getUser() {
        return user;
    }


    public void setUser(User_info user) {
        GlobalVariables.user = user;
    }

    public GlobalVariables getInstance() {
        if(instance==null){
            Object object=Utils.restoreObject(filePath);
            if(object==null){
                object =new GlobalVariables();
                Utils.saveObject(filePath,object);
            }
            instance=(GlobalVariables)object;
        }
        return instance;
    }
    public GlobalVariables readResolve()throws ObjectStreamException,CloneNotSupportedException{
        instance =(GlobalVariables)this.clone();
        return instance;
    }

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
    public void reset(){
        user=null;
        Utils.saveObject(filePath,this);
    }
}
