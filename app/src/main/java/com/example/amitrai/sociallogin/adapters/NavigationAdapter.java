package com.example.amitrai.sociallogin.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.amitrai.sociallogin.R;

import java.util.List;

/**
 * Created by cynogen on 30/10/15.
 */
public class NavigationAdapter extends BaseAdapter {

    private Activity act = null;
    private List<String> menu_options = null;
    private LayoutInflater inflater = null;
    private Holder holder = null;

    public NavigationAdapter(Activity act, List<String> menu_options){

        this.act = act;
        this.menu_options = menu_options;
        inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return menu_options.size();
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
            view = inflater.inflate(R.layout.design_test,null);
            holder = new Holder();
            holder.textView = (TextView) view.findViewById(R.id.txt_option);
            view.setTag(holder);
        }else
            holder = (Holder) view.getTag();

        holder.textView.setText(menu_options.get(position));

        return view;
    }

    class Holder{
        TextView textView;
    }
}
