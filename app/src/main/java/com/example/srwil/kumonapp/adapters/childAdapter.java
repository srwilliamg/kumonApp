package com.example.srwil.kumonapp.adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.srwil.kumonapp.R;
import com.example.srwil.kumonapp.constants.GlobalVars;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class childAdapter extends BaseAdapter {
    String TAG = "childAdapter";
    Context context;
    JSONArray children;
    JSONObject currentElement;
    LayoutInflater inflter;

    public childAdapter(Context applicationContext, JSONArray children) {
        this.context = applicationContext;
        this.children = children;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return children.length();
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
        try {
            currentElement = children.getJSONObject(i);

            view.setTag(currentElement.getJSONObject("son").toString());

            ImageView ProfileImage = view.findViewById(R.id.child_image);
            Picasso.with(context).load(GlobalVars.getUrlAssets()+currentElement.getJSONObject("son").getString("image_url"))
                    .placeholder(R.drawable.ic_default_avatar)
                    .error(R.drawable.ic_default_avatar).into(ProfileImage);


            TextView text2 = view.findViewById(R.id.text2);
            TextView text3 = view.findViewById(R.id.text3);
            text3.setText(currentElement.getJSONObject("son").getString("name"));
            text2.setText(currentElement.getJSONObject("son").getString("lastname"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
}