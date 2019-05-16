package com.example.slider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Activity2 extends AppCompatActivity {

    private Button buttonMovie1;
    private Button buttonMovie2;
    private Button buttonMovie3;
    FirebaseAuth mAuth;
    final  String movie1 = "MOVIE1";
    final  String movie2 = "MOVIE2";
    final  String movie3 = "MOVIE3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        mAuth = FirebaseAuth.getInstance();

        buttonMovie1 = (Button)findViewById(R.id.buttonMovie1);
        buttonMovie2 = (Button)findViewById(R.id.buttonMovie2);
        buttonMovie3 = (Button)findViewById(R.id.buttonMovie3);

        buttonMovie1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String user_id = mAuth.getCurrentUser().getUid();
                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("users").child(user_id).child("Movie time");
                Map newpost = new HashMap();
                newpost.put("Movie",movie1);
                current_user_db.setValue(newpost);



                openActivity3(movie1);
            }
        });

        buttonMovie2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_id = mAuth.getCurrentUser().getUid();
                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("users").child(user_id).child("Movie time");
                Map newpost = new HashMap();
                newpost.put("Movie",movie2);
                current_user_db.setValue(newpost);

               openActivity3(movie2);
            }
        });
        buttonMovie3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_id = mAuth.getCurrentUser().getUid();
                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("users").child(user_id).child("Movie time");
                Map newpost = new HashMap();
                newpost.put("Movie",movie3);
                current_user_db.setValue(newpost);

                openActivity3(movie3);
            }
        });




    }

    public void openActivity3(String movie)
    {
        Intent intent = new Intent(this, Activity3.class);
        intent.putExtra("whichMovie",movie);
        startActivity(intent);

    }
}
