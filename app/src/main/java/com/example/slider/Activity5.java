package com.example.slider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Activity5 extends AppCompatActivity {


    float prize;
    float total;
    TextView textView12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        textView12 = (TextView)findViewById(R.id.textView12) ;


        Intent intent = getIntent();
        final String movie = intent.getExtras().getString("movie");
        final int count = intent.getExtras().getInt("count");
        switch (movie){
            case "MOVIE1":
                prize = 650;
                break;
            case "MOVIE2":
                prize = 600;
                break;
            case "MOVIE3":
                prize = 650;



        }

             total = prize*count;

        String display = movie+" Tickets"+" * "+ String.valueOf(count)+"    = Rs."+String.valueOf(total);


                    textView12.setText(display);


      


    }
}
