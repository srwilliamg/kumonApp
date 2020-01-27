package com.example.srwil.kumonapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.srwil.kumonapp.constants.GlobalVars;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class listActivity extends AppCompatActivity {

    private static String TAG = "childList";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ListView childList = findViewById(R.id.listSchedule);
        ArrayList children = new ArrayList<>(asList("Pedro", "Juan", "Leonardo", "Sebastian"));
        //ArrayAdapter<String> childAdapter = new ArrayAdapter<String>(this, R.layout.component , children);
        childAdapter childAdapter = new childAdapter(this, children);

        childList.setAdapter(childAdapter);
        childList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
