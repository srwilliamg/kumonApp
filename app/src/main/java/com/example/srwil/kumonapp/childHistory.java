package com.example.srwil.kumonapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.srwil.kumonapp.classes.CustomJsonArrayRequest;
import com.example.srwil.kumonapp.classes.RequestQueueInstance;
import com.example.srwil.kumonapp.constants.GlobalVars;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class childHistory extends AppCompatActivity {
    private static final String TAG = "childHistory";
    private ListView listSchedule;
    private View mProgressView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historychild);
        listSchedule = findViewById(R.id.listSchedule);
        mProgressView = findViewById(R.id.history_progress);


        String url = GlobalVars.getUrlTest()+"message/consultRecords";

        JSONObject son;
        try {
            // get data from intent and parsing to json
            son = new JSONObject(getIntent().getStringExtra("son"));

            Log.i(TAG, "ID_SON:"+son);

            TextView title = (findViewById(R.id.title_list_child));
            title.setText(title.getText()+ son.getString("name"));

            showProgress(true);

            JSONObject jsonobject = new JSONObject();
            try {
                jsonobject.put("idson", son.getString("id_son"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.i(TAG, jsonobject.toString()+"; send to: "+url);

            CustomJsonArrayRequest jsonObjectRequest = new CustomJsonArrayRequest(Request.Method.POST, url, jsonobject, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.i(TAG,"RESPOSE:"+response.toString());
                    ArrayList<String> temp = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++){
                        try {
                            temp.add(response.getJSONObject(i).getString("createdAt"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    ArrayAdapter<String> itemsAdapter =
                            new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, temp);

                    listSchedule.setAdapter(itemsAdapter);

                    showProgress(false);

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.e(TAG, error.toString());
                    VolleyLog.d(TAG, error.getMessage());
                    showProgress(false);

                }
            }){
                @Override
                public Map<String, String> getHeaders(){
                    HashMap<String, String> headers = new HashMap<String, String>();
                    //headers.put("Content-Type", "application/json");
                    headers.put("Authorization", GlobalVars.getJwtToken());
                    return headers;
                }
            };

            RequestQueueInstance.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            listSchedule.setVisibility(show ? View.GONE : View.VISIBLE);
            listSchedule.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    listSchedule.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            listSchedule.setVisibility(show ? View.VISIBLE : View.GONE);
            listSchedule.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
