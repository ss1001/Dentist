package com.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.interfaces.back_info;

import java.util.Map;

/**
 * Created by 佘松 on 2017/8/16.
 */

public class volley_photo {
    private String uid;
    String method;
    Map<String,String> param;
    Context context;
    back_info Iback_info;
    String url;
    public volley_photo(Context context, String method, Map<String,String> param,back_info Iback_info){
        this.Iback_info=Iback_info;
        this.method=method;
        this.param=param;
        this.context=context;
        //
        //this.url="http://10.0.2.2:8013/flower.jpg";
        this.url="http://10.0.2.2:8013/photo.php";
    }
//    public void getPhoto() {
//        RequestQueue queue = Volley.newRequestQueue(context);
//        ImageRequest imageRequest =new ImageRequest(url,listener,0,0, Bitmap.Config.RGB_565,errorlistener){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return param;
//            }
//        };
//        queue.add(imageRequest);
//    }
//    private Response.Listener<Bitmap> listener=new Response.Listener<Bitmap>() {
//
//        @Override
//        public void onResponse(Bitmap bitmap) {
//            Iback_info.send_back(bitmap);
//        }
//    };
//    private Response.ErrorListener errorlistener=new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError volleyError) {
//            Log.d("photo",volleyError.getMessage(),volleyError);
//        }
//    };
    public void getPhoto(){

    }
}
