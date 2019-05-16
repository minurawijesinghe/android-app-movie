package com.example.slider;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Activity4 extends AppCompatActivity  {


    DatabaseReference reference1;
    FirebaseAuth activity4Auth;

    


    private Switch a1;
    private Switch a2;
    private Switch a3;
    private Switch a4;
    private Switch b1;
    private Switch b2;
    private Switch b3;
    private Switch b4;
    private Switch c1;
    private Switch c2;
    private Switch c3;
    private Switch c4;
    private Switch d1;
    private Switch d2;
    private Switch d3;
    private Switch d4;
    private Button buttonsee;

    int colorRed = Color.argb(200,233,30,99);
    int colorGreen = Color.argb(200,139,250,10);
    int colorGray = Color.argb(200,243,241,241);



    int count=0;
    String occupiedList  ;
    String seatsList;
    String remove = "null";
    String finalList;
    String userSelected;


    private Button activity4Continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        reference1 = FirebaseDatabase.getInstance().getReference();


        Intent intent = getIntent();
        final String dateAndMovie = intent.getExtras().getString("dateAndMovie");
        final boolean check = intent.getExtras().getBoolean("check");
        final String movieSelected = intent.getExtras().getString("movie");


        if(check==true){
           occupiedList= anything(dateAndMovie);
        } else {
            occupiedList = "null";
            DatabaseReference DateAndMoive = FirebaseDatabase.getInstance().getReference().child(dateAndMovie);
            Map reSet3 = new HashMap();
            reSet3.put("seats ids",remove);
            DateAndMoive.setValue(reSet3);
        }


        final List<String>Booked = new ArrayList<>();


        activity4Auth = FirebaseAuth.getInstance();


        activity4Continue = (Button)findViewById(R.id.activity4Continue);
        activity4Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i =0; i<Booked.size();i++) {
                    seatsList += ("," + Booked.get(i));
                }

                finalList = seatsList.replace(remove,"");


                    userSelected = finalList.replace(occupiedList, "");


                    String user_id = activity4Auth.getCurrentUser().getUid();


                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("users").child(user_id).child("booked seats");
                    Map newpost = new HashMap();
                    newpost.put("seats ids", userSelected);
                    current_user_db.setValue(newpost);


                    DatabaseReference occupiedSeats = FirebaseDatabase.getInstance().getReference().child(dateAndMovie);
                    Map seats = new HashMap();
                    seats.put("seats ids", finalList);
                    occupiedSeats.setValue(seats);




                                 OpenActivity5(movieSelected,count);


            }
        });
















        a1 = (Switch)findViewById(R.id.a1);
        a2 = (Switch)findViewById(R.id.a2);
        a3 = (Switch)findViewById(R.id.a3);
        a4 = (Switch)findViewById(R.id.a4);
        b1 = (Switch)findViewById(R.id.b1);
        b2 = (Switch)findViewById(R.id.b2);
        b3 = (Switch)findViewById(R.id.b3);
        b4 = (Switch)findViewById(R.id.b4);
        c1 = (Switch)findViewById(R.id.c1);
        c2 = (Switch)findViewById(R.id.c2);
        c3 = (Switch)findViewById(R.id.c3);
        c4 = (Switch)findViewById(R.id.c4);
        d1 = (Switch)findViewById(R.id.d1);
        d2 = (Switch)findViewById(R.id.d2);
        d3 = (Switch)findViewById(R.id.d3);
        d4 = (Switch)findViewById(R.id.d4);

        buttonsee  = findViewById(R.id.buttonsee);
        buttonsee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String status1  =IsOccupied("a1",occupiedList);
                final String status2  =IsOccupied("a2",occupiedList);
                final String status3  =IsOccupied("a3",occupiedList);
                final String status4  =IsOccupied("a4",occupiedList);
                final String status5  =IsOccupied("b1",occupiedList);
                final String status6  =IsOccupied("b2",occupiedList);
                final String status7  =IsOccupied("b3",occupiedList);
                final String status8  =IsOccupied("b4",occupiedList);
                final String status9  =IsOccupied("c1",occupiedList);
                final String status10 =IsOccupied("c2",occupiedList);
                final String status11 =IsOccupied("c3",occupiedList);
                final String status12 =IsOccupied("c4",occupiedList);
                final String status13 =IsOccupied("d1",occupiedList);
                final String status14 =IsOccupied("d2",occupiedList);
                final String status15 =IsOccupied("d3",occupiedList);
                final String status16 =IsOccupied("d4",occupiedList);

                if (occupiedList == "null"){
                    Toast.makeText(Activity4.this,"There are no any seats booked for the given show choose your seats",Toast.LENGTH_SHORT).show();

                }

                if (status1 =="0"){
                    a1.setChecked(true);
                    a1.setClickable(false);
                    a1.setBackgroundColor(colorGray);
                    count--;

                }
                if (status2 =="0"){
                    a2.setChecked(true);
                    a2.setClickable(false);
                    a2.setBackgroundColor(colorGray);
                    count--;

                }
                if (status3 =="0"){
                    a3.setChecked(true);
                    a3.setClickable(false);
                    a3.setBackgroundColor(colorGray);
                    count--;


                }
                if (status4 =="0"){
                    a4.setChecked(true);
                    a4.setClickable(false);
                    a4.setBackgroundColor(colorGray);
                    count--;


                }
                if (status5 =="0"){
                    b1.setChecked(true);
                    b1.setClickable(false);
                    b1.setBackgroundColor(colorGray);
                    count--;


                }
                if (status6 =="0"){
                    b2.setChecked(true);
                    b2.setClickable(false);
                    b2.setBackgroundColor(colorGray);

                    count--;

                }
                if (status7 =="0"){
                    b3.setChecked(true);
                    b3.setClickable(false);
                    b3.setBackgroundColor(colorGray);

                    count--;

                }
                if (status8 =="0"){
                    b4.setChecked(true);
                    b4.setClickable(false);
                    b4.setBackgroundColor(colorGray);

                    count--;

                }
                if (status9 =="0"){
                    c1.setChecked(true);
                    c1.setClickable(false);
                    c1.setBackgroundColor(colorGray);
                    count--;


                }
                if (status10 =="0"){
                    c2.setChecked(true);
                    c2.setClickable(false);
                    c2.setBackgroundColor(colorGray);
                    count--;


                }
                if (status11 =="0"){
                    c3.setChecked(true);
                    c3.setClickable(false);
                    c3.setBackgroundColor(colorGray);
                    count--;


                }
                if (status12 =="0"){
                    c4.setChecked(true);
                    c4.setClickable(false);
                    c4.setBackgroundColor(colorGray);
                    count--;


                }
                if (status13 =="0"){
                    d1.setChecked(true);
                    d1.setClickable(false);
                    d1.setBackgroundColor(colorGray);
                    count--;


                }
                if (status14 =="0"){
                    d2.setChecked(true);
                    d2.setClickable(false);
                    d2.setBackgroundColor(colorGray);

                    count--;

                }
                if (status15 =="0"){
                    d3.setChecked(true);
                    d3.setClickable(false);
                    d3.setBackgroundColor(colorGray);

                    count--;

                }
                if (status16 =="0"){
                    d4.setChecked(true);
                    d4.setClickable(false);
                    d4.setBackgroundColor(colorGray);

                    count--;

                }

            }
        });
        

        a1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (a1.isChecked() == true){
                    Toast.makeText(getBaseContext(),"A1 is selected", Toast.LENGTH_SHORT).show();
                    a1.setBackgroundColor(colorRed);
                    Booked.add("a1");
                    count++;

                }else{
                    Toast.makeText(getBaseContext(), "A1 is deselected", Toast.LENGTH_SHORT).show();
                    a1.setBackgroundColor(colorGreen);
                    for (int i =0; i<Booked.size();i++){
                        if (Booked.get(i)=="a1"){
                            Booked.remove(i);

                        }
                    }
                    count--;

                }

            }
        });

        a2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



                if (a2.isChecked() == true){
                    Toast.makeText(getBaseContext(),"A2 is selected", Toast.LENGTH_SHORT).show();
                    a2.setBackgroundColor(colorRed);
                    Booked.add("a2");
                    count++;

                }else{
                    Toast.makeText(getBaseContext(), "A2 is deselected", Toast.LENGTH_SHORT).show();
                    a2.setBackgroundColor(colorGreen);
                    for (int i =0; i<Booked.size();i++){
                        if (Booked.get(i)=="a2"){
                            Booked.remove(i);
                        }
                    }
                    count--;

                }

            }
        });
        a3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (a3.isChecked() == true){
                    Toast.makeText(getBaseContext(),"A3 is selected", Toast.LENGTH_SHORT).show();
                    a3.setBackgroundColor(colorRed);
                    Booked.add("a3");
                    count++;

                }else{
                    Toast.makeText(getBaseContext(), "A3 is deselected", Toast.LENGTH_SHORT).show();
                    a3.setBackgroundColor(colorGreen);
                    for (int i =0; i<Booked.size();i++){
                        if (Booked.get(i)=="a3"){
                            Booked.remove(i);
                        }
                    }
                    count--;

                }

            }
        });
        a4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (a4.isChecked() == true){
                    Toast.makeText(getBaseContext(),"A4 is selected", Toast.LENGTH_SHORT).show();
                    a4.setBackgroundColor(colorRed);
                    Booked.add("a4");
                    count++;

                }else{
                    Toast.makeText(getBaseContext(), "A4 is deselected", Toast.LENGTH_SHORT).show();
                    a4.setBackgroundColor(colorGreen);
                    for (int i =0; i<Booked.size();i++){
                        if (Booked.get(i)=="a4"){
                            Booked.remove(i);
                        }
                    }
                    count--;

                }

            }
        });
        b1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (b1.isChecked() == true){
                    Toast.makeText(getBaseContext(),"B1 is selected", Toast.LENGTH_SHORT).show();
                    b1.setBackgroundColor(colorRed);
                    Booked.add("b1");
                    count++;

                }else{
                    Toast.makeText(getBaseContext(), "B1 is deselected", Toast.LENGTH_SHORT).show();
                    b1.setBackgroundColor(colorGreen);
                    for (int i =0; i<Booked.size();i++){
                        if (Booked.get(i)=="b1"){
                            Booked.remove(i);
                        }
                    }
                    count--;

                }

            }
        });
        b2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (b2.isChecked() == true){
                    Toast.makeText(getBaseContext(),"B2 is selected", Toast.LENGTH_SHORT).show();
                    b2.setBackgroundColor(colorRed);
                    Booked.add("b2");
                    count++;

                }else{
                    Toast.makeText(getBaseContext(), "B2 is deselected", Toast.LENGTH_SHORT).show();
                    b2.setBackgroundColor(colorGreen);
                    for (int i =0; i<Booked.size();i++){
                        if (Booked.get(i)=="b2"){
                            Booked.remove(i);
                        }
                    }
                    count--;

                }

            }
        });
        b3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (b3.isChecked() == true){
                    Toast.makeText(getBaseContext(),"B3 is selected", Toast.LENGTH_SHORT).show();
                    b3.setBackgroundColor(colorRed);
                    Booked.add("b3");
                    count++;

                }else{
                    Toast.makeText(getBaseContext(), "B3 is deselected", Toast.LENGTH_SHORT).show();
                    b3.setBackgroundColor(colorGreen);
                    for (int i =0; i<Booked.size();i++){
                        if (Booked.get(i)=="b3"){
                            Booked.remove(i);
                        }
                    }
                    count--;

                }

            }
        });
        b4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (b4.isChecked() == true){
                    Toast.makeText(getBaseContext(),"B4 is selected", Toast.LENGTH_SHORT).show();
                    b4.setBackgroundColor(colorRed);
                    Booked.add("b4");
                    count++;

                }else{
                    Toast.makeText(getBaseContext(), "B4 is deselected", Toast.LENGTH_SHORT).show();
                    b4.setBackgroundColor(colorGreen);
                    for (int i =0; i<Booked.size();i++){
                        if (Booked.get(i)=="b4"){
                            Booked.remove(i);
                        }
                    }
                    count--;

                }

            }
        });
        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (c1.isChecked() == true){
                    Toast.makeText(getBaseContext(),"C1 is selected", Toast.LENGTH_SHORT).show();
                    c1.setBackgroundColor(colorRed);
                    Booked.add("c1");
                    count++;

                }else{
                    Toast.makeText(getBaseContext(), "C1 is deselected", Toast.LENGTH_SHORT).show();
                    c1.setBackgroundColor(colorGreen);
                    for (int i =0; i<Booked.size();i++){
                        if (Booked.get(i)=="c1"){
                            Booked.remove(i);
                        }
                    }
                    count--;

                }

            }
        });

        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (c2.isChecked() == true){
                    Toast.makeText(getBaseContext(),"C2 is selected", Toast.LENGTH_SHORT).show();
                    c2.setBackgroundColor(colorRed);
                    Booked.add("c2");
                    count++;

                }else{
                    Toast.makeText(getBaseContext(), "C2 is deselected", Toast.LENGTH_SHORT).show();
                    c2.setBackgroundColor(colorGreen);
                    for (int i =0; i<Booked.size();i++){
                        if (Booked.get(i)=="c2"){
                            Booked.remove(i);
                        }
                    }
                    count--;

                }

            }
        });
        c3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (c3.isChecked() == true){
                    Toast.makeText(getBaseContext(),"C3 is selected", Toast.LENGTH_SHORT).show();
                    c3.setBackgroundColor(colorRed);
                    Booked.add("c3");
                    count++;

                }else{
                    Toast.makeText(getBaseContext(), "C3 is deselected", Toast.LENGTH_SHORT).show();
                    c3.setBackgroundColor(colorGreen);
                    for (int i =0; i<Booked.size();i++){
                        if (Booked.get(i)=="c3"){
                            Booked.remove(i);
                        }
                    }
                    count--;

                }

            }
        });
        c4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (c4.isChecked() == true){
                    Toast.makeText(getBaseContext(),"C4 is selected", Toast.LENGTH_SHORT).show();
                    c4.setBackgroundColor(colorRed);
                    Booked.add("c4");
                    count++;

                }else{
                    Toast.makeText(getBaseContext(), "C4 is deselected", Toast.LENGTH_SHORT).show();
                    c4.setBackgroundColor(colorGreen);
                    for (int i =0; i<Booked.size();i++){
                        if (Booked.get(i)=="c4"){
                            Booked.remove(i);
                        }
                    }
                    count--;

                }

            }
        });
        d1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (d1.isChecked() == true){
                    Toast.makeText(getBaseContext(),"D1 is selected", Toast.LENGTH_SHORT).show();
                    d1.setBackgroundColor(colorRed);
                    Booked.add("d1");
                    count++;

                }else{
                    Toast.makeText(getBaseContext(), "D1 is deselected", Toast.LENGTH_SHORT).show();
                    d1.setBackgroundColor(colorGreen);
                    for (int i =0; i<Booked.size();i++){
                        if (Booked.get(i)=="d1"){
                            Booked.remove(i);
                        }
                    }
                    count--;

                }

            }
        });
        d2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (d2.isChecked() == true){
                    Toast.makeText(getBaseContext(),"D2 is selected", Toast.LENGTH_SHORT).show();
                    d2.setBackgroundColor(colorRed);
                    Booked.add("d2");
                    count++;

                }else{
                    Toast.makeText(getBaseContext(), "D2 is deselected", Toast.LENGTH_SHORT).show();
                    d2.setBackgroundColor(colorGreen);
                    for (int i =0; i<Booked.size();i++){
                        if (Booked.get(i)=="d2"){
                            Booked.remove(i);
                        }
                    }
                    count--;

                }

            }
        });
        d3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (d3.isChecked() == true){
                    Toast.makeText(getBaseContext(),"D3 is selected", Toast.LENGTH_SHORT).show();
                    d3.setBackgroundColor(colorRed);
                    Booked.add("d3");
                    count++;

                }else{
                    Toast.makeText(getBaseContext(), "D3 is deselected", Toast.LENGTH_SHORT).show();
                    d3.setBackgroundColor(colorGreen);
                    for (int i =0; i<Booked.size();i++){
                        if (Booked.get(i)=="d3"){
                            Booked.remove(i);
                        }
                    }
                    count--;

                }

            }
        });
        d4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (d4.isChecked() == true){
                    Toast.makeText(getBaseContext(),"D4 is selected", Toast.LENGTH_SHORT).show();
                    d4.setBackgroundColor(colorRed);
                    Booked.add("d4");
                    count++;

                }else{
                    Toast.makeText(getBaseContext(), "D4 is deselected", Toast.LENGTH_SHORT).show();
                    d4.setBackgroundColor(colorGreen);
                    for (int i =0; i<Booked.size();i++){
                        if (Booked.get(i)=="d4"){
                            Booked.remove(i);
                        }
                    }
                    count--;

                }

            }
        });

    }
    static String IsOccupied(String seatId, String occupidelis)
    {
        int M = seatId.length();
        int N = occupidelis.length();
        for (int i = 0; i <= N - M; i++) {
            int j;

            for (j = 0; j < M; j++)
                if (occupidelis.charAt(i + j) != seatId.charAt(j))
                    break;
            if (j == M)
                return "0";
        }
        return "1";
    }
    public void OpenActivity5(String movieSeleted,int count)
    {
        Intent intent = new Intent(this,Activity5.class);
        intent.putExtra("movie",movieSeleted);
        intent.putExtra("count",count);
        startActivity(intent);
    }

    public String anything(final String dateAndMovie){

        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    occupiedList = dataSnapshot.child(dateAndMovie).child("seats ids").getValue().toString();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



              return occupiedList;


    }


}

