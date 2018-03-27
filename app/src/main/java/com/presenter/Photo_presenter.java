package com.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.interfaces.Iview;
import com.interfaces.back_info;
import com.network.download_photo;

/**
 * Created by 佘松 on 2017/8/16.
 */

public class Photo_presenter implements back_info{
    private Iview iview;
    private back_info Iback;
    private String uid;
    private Context context;
    public Photo_presenter(Context context,String uid){
    this.context=context;
        iview=(Iview)context;
        this.uid=uid;
        this.Iback=(back_info)this;
    }
    public void start_photo(){
        download_photo download_photo=new download_photo(context,uid,Iback);
        download_photo.start_downloading();
    }
    @Override
    public void send_back(String s) {

    }

    @Override
    public void error_back(String e) {

    }

    @Override
    public void send_back(Bitmap bitmap) {
        iview.start_working(bitmap);
    }
}
