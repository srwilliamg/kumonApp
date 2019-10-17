package com.example.srwil.kumonapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class listActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ListView childList = findViewById(R.id.listSchedule);
        ArrayList children = new ArrayList<>(asList("Pedro", "Juan", "Leonardo", "Sebastian"));
        ArrayAdapter<String> childAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , children);

        childList.setAdapter(childAdapter);
        childList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
