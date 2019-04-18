package com.example.pc.myapplication;


import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;




import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class Post_Activity extends AppCompatActivity {
    private EditText tv_title, tv_content;
    private Button write_btn;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        tv_title = findViewById(R.id.tv_title);
        tv_content = findViewById(R.id.tv_content);
        write_btn = findViewById(R.id.write_btn);

        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Long date= (-1*(new Date().getTime()))/1000;
                //String date2= String.valueOf(date);


                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("posts").push();


                Map<String, Object> map = new HashMap<>();
                map.put("id", databaseReference.getKey());
                map.put("title", tv_title.getText().toString());
                map.put("content", tv_content.getText().toString());
               // map.put("date2", date);

                databaseReference.setValue(map);
                databaseReference.child("date2").setValue(date);

                finish();


            }



            });



    }
}



