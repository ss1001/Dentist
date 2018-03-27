package com.entity;

/**
 * Created by 佘松 on 2017/10/18.
 */

public class menu_item_info {
    private int resourseId;
    private String text;

    public menu_item_info(int resourseId,String text){
        this.resourseId=resourseId;
        this.text=text;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getResourseId() {
        return resourseId;
    }

    public void setResourseId(int resourseId) {
        this.resourseId = resourseId;
    }
}
