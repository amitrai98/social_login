package com.example.amitrai.sociallogin.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cynogen on 1/11/15.
 */
public class NotificationAdapter extends BaseAdapter{


    private LayoutInflater inflater = null;
    private Activity activity = null;
    private List<String> list_notification = null;


    public NotificationAdapter(Activity activity, List<String> list_notification){
        this.activity = activity;
        this.list_notification = list_notification;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list_notification.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){

        }else {

        }
        return view;
    }

    class Holder{
        TextView textView;
    }
}
