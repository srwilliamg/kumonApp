package com.example.srwil.kumonapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class childAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> children;
    LayoutInflater inflter;

    public childAdapter(Context applicationContext, ArrayList<String> children) {
        this.context = applicationContext;
        this.children = children;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return children.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.component, null);
        TextView text1 = (TextView) view.findViewById(R.id.text1);
        TextView text2 = (TextView) view.findViewById(R.id.text2);
        text1.setText(children.get(i));
        text2.setText(children.get(i)+"2");
        return view;
    }
}