package com.example.srwil.kumonapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class childAdapter extends BaseAdapter {
    String TAG = "childAdapter";
    Context context;
    ArrayList<String> names;
    ArrayList<String> lastnames;
    LayoutInflater inflter;

    public childAdapter(Context applicationContext, ArrayList<String> names, ArrayList<String> lastnames) {
        this.context = applicationContext;
        this.lastnames = lastnames;
        this.names = names;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return names.size();
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
        Log.i(TAG, "Niño "+i +": "+names.get(i) + lastnames.get(i));
        TextView text1 = (TextView) view.findViewById(R.id.text1);
        TextView text2 = (TextView) view.findViewById(R.id.text2);
        TextView text3 = (TextView) view.findViewById(R.id.text3);
        text1.setText("Niño "+i);
        text3.setText(names.get(i));
        text2.setText(lastnames.get(i));
        return view;
    }
}