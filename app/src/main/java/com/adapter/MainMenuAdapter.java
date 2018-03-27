package com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dentist.R;
import com.entity.menu_item_info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 佘松 on 2017/10/17.
 */

public class MainMenuAdapter extends ArrayAdapter<menu_item_info> {
    private List<menu_item_info>lists;

    public MainMenuAdapter(Context context, int resource) {
        super(context, resource);
    }

    public MainMenuAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }


    public MainMenuAdapter(Context context, int resource, int textViewResourceId, List<menu_item_info>list) {
        super(context, resource, textViewResourceId, list);
    }

    public MainMenuAdapter(Context context, int resource, List<menu_item_info> objects) {

        super(context, resource, objects);
        lists=objects;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public menu_item_info getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        menu_item_info item=getItem(position);
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.main_menu_item,null);
            holder=new ViewHolder();
            holder.imageView=(ImageView) convertView.findViewById(R.id.main_menu_icon);
            holder.textView=(TextView) convertView.findViewById(R.id.main_menu_text);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource(item.getResourseId());
        holder.textView.setText(item.getText());
        return convertView;
    }
    private class ViewHolder{
        public TextView textView;
        public ImageView imageView;
    }
}
