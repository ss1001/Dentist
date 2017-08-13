package com.network;

import android.content.Context;

import com.interfaces.back_info;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 佘松 on 2017/8/13.
 */

public class Regist {
    private Context context;
    private String name,psd;
    private back_info Iback_info;
    public Regist(Context context,String name,String psd,back_info Iback_info){
        this.context=context;
        this.name=name;
        this.psd=psd;
        this.Iback_info=Iback_info;
    }
    public void regist(){
        Map<String,String> map=new HashMap<>();
        map.put("isRegist","1");
        map.put("name",name);
        map.put("password",psd);
        volley_param volleyParam=new volley_param(context,"","server",map,Iback_info);
        volleyParam.dopost();
    }
}
