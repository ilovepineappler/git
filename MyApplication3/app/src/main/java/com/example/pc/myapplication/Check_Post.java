package com.example.pc.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Check_Post  extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_checkpost);

        Button write_fab = (Button) findViewById(R.id.fab_check1);

        write_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        Intent intent = getIntent();
        String title  = intent.getStringExtra("title");
        Log.d("111",""+title);
        String content  = intent.getStringExtra("content");
        TextView title_T = (TextView)findViewById(R.id.check_title);
        title_T.setText(getIntent().getStringExtra("title"));
        TextView content_T = (TextView)findViewById(R.id.check_content);
        content_T.setText(getIntent().getStringExtra("content"));
    }




}