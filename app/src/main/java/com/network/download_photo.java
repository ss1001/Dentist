package com.network;

import android.content.Context;

import com.interfaces.back_info;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 佘松 on 2017/8/17.
 */

public class download_photo {
    private back_info backInfo;
    private Context context;
    private String uid ;
   private back_info Iback_info;
    public download_photo(Context context,String uid,back_info backInfo){
        this.backInfo=backInfo;
        this.context=context;
        this.uid=uid;
    }
    public void start_downloading(){
        Map<String,String>map=new HashMap<>();
        map.put("uid",uid);
        map.put("getdata","1");
        volley_photo volleyPhoto=new volley_photo(context,"photo",map,backInfo);
        volleyPhoto.getPhoto();
    }

}
