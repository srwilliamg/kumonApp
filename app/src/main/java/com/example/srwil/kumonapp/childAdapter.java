package com.example.srwil.kumonapp;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.srwil.kumonapp.constants.GlobalVars;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class childAdapter extends BaseAdapter {
    String TAG = "childAdapter";
    Context context;
    ArrayList<String> names;
    ArrayList<String> lastnames;
    ArrayList<String> urlImages;
    LayoutInflater inflter;

    public childAdapter(Context applicationContext, ArrayList<String> names, ArrayList<String> lastnames, ArrayList<String> urlImages) {
        this.context = applicationContext;
        this.lastnames = lastnames;
        this.urlImages = urlImages;
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

        ImageView ProfileImage = view.findViewById(R.id.child_image);
        Picasso.with(context).load(GlobalVars.getUrlAssets()+urlImages.get(i)).into(ProfileImage);

        TextView text2 = view.findViewById(R.id.text2);
        TextView text3 = view.findViewById(R.id.text3);
        text3.setText(names.get(i));
        text2.setText(lastnames.get(i));
        return view;
    }
}