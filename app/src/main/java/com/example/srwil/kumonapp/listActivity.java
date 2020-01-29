package com.example.srwil.kumonapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.example.srwil.kumonapp.classes.CustomJsonArrayRequest;
import com.example.srwil.kumonapp.classes.RequestQueueInstance;
import com.example.srwil.kumonapp.constants.GlobalVars;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class listActivity extends AppCompatActivity {

    private static String TAG = "childList";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String url = GlobalVars.getUrlTest()+"message/consultSons";

        JSONObject jsonobject = new JSONObject();

        try {
            jsonobject.put("idparent", GlobalVars.getIdParent());


            Log.i(TAG, jsonobject.toString()+"; send to: "+url);

                CustomJsonArrayRequest jsonObjectRequest = new CustomJsonArrayRequest(Request.Method.POST, url, jsonobject, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        TextView listTitle = findViewById(R.id.title_list_child);
                        listTitle.setText(listTitle.getText()+" "+GlobalVars.getName());
                        ListView childList = findViewById(R.id.listSchedule);
                        ArrayList names = new ArrayList<>();
                        ArrayList lastnames = new ArrayList<>();

                        Log.i(TAG, response.toString());
                        JSONObject currentElement = null;

                        for (int i = 0; i < response.length() ; i++) {
                            currentElement = response.getJSONObject(i);
                            names.add(currentElement.getJSONObject("son").getString("name"));
                            lastnames.add(currentElement.getJSONObject("son").getString("lastname"));
                        }
                        //ArrayAdapter<String> childAdapter = new ArrayAdapter<String>(this, R.layout.component , children);
                        childAdapter childAdapter = new childAdapter(getApplicationContext(), names, lastnames);

                        childList.setAdapter(childAdapter);
                        childList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.e(TAG, error.toString());
                    VolleyLog.d(TAG, error.getMessage());

                }
            }){

                /**
                 * Passing some request headers
                 */
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    //headers.put("Content-Type", "application/json");
                    headers.put("Authorization", GlobalVars.getJwtToken());
                    return headers;
                }
            };

            RequestQueueInstance.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
        }
        catch (Exception error){
            Log.e(TAG, error.toString());
            VolleyLog.d(TAG, error.getMessage());
        }


    }
}
