package com.example.slider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Activity3 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    boolean chek;
    String date;

    private Button buttonContinue;
    TextView textView9 ;
    Button changeDate;
    Button confirm;
    Date today;
    Date slected;
    TextView textView10;
    long differenceDays = 0;
    FirebaseAuth activity3Auth;
    DatabaseReference refActivity3;
    String selectedDate;
    String dateAndMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        //getting movie number from activity 2
        Intent intent = getIntent();
        final String movieVerification = intent.getExtras().getString("whichMovie");


        activity3Auth = FirebaseAuth.getInstance();
        refActivity3 = FirebaseDatabase.getInstance().getReference();



        textView10 = (TextView)findViewById(R.id.textView10);
        textView9 = (TextView)findViewById(R.id.textView9);
        confirm = (Button)findViewById(R.id.confirm);


        Calendar b = Calendar.getInstance();

        int year = b.get(Calendar.YEAR);
        int month = b.get(Calendar.MONTH);
        int day = b.get(Calendar.DAY_OF_MONTH);
       final String currentDateString = DateFormat.getDateInstance().format(b.getTime());
       today = b.getTime();

       textView9.setText(currentDateString);






        changeDate = (Button)findViewById(R.id.buttonChangeDate);

        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datepicker = new DatePickerFragment();
                datepicker.show(getSupportFragmentManager(),"date picker");




            }
        });




        buttonContinue = (Button)findViewById(R.id.buttonContinue);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String user_id = activity3Auth.getCurrentUser().getUid();
                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("users").child(user_id).child("Date");
                Map newpost = new HashMap();
                    newpost.put("Date",date);
                    current_user_db.setValue(newpost);




             /*      */








                openActivity4(date,movieVerification);



                




            }
        });




        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (differenceDays<0){
                    Toast.makeText(Activity3.this,"selected day is not valid",Toast.LENGTH_SHORT).show();
                    return;
                } else

                      if (selectedDate==null){
                          date = currentDateString;
                      } else {
                          dateAndMovie = selectedDate + " " + movieVerification;
                          date = selectedDate;

                      }



                refActivity3.child(dateAndMovie).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.getValue() != null) {
                            //user exists, do something

                            chek = true;


                        } else {

                            chek = false;


                        }
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });


                       buttonContinue.setVisibility(View.VISIBLE);
            }



        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        selectedDate = DateFormat.getDateInstance().format(c.getTime());
        textView9.setText(selectedDate);
        slected = c.getTime();
        differenceDays = daysBetween(today,slected);

        String asString = Long.toString(differenceDays);
        

        textView10.setText(asString+" days from today");
    }

    public void openActivity4(String date,String movieVeri)
    {
       String datAndMovie = date+" "+movieVeri;

        Intent intent = new Intent(this,Activity4.class);
        intent.putExtra("dateAndMovie",datAndMovie) ;
        intent.putExtra("check",chek) ;
        intent.putExtra("movie",movieVeri);
        startActivity(intent);


    }

    public long daysBetween(Date date1, Date date2){


        long difference = (date1.getTime()-date2.getTime())/86400000;
              difference= difference*(-1);
        return  difference;
    }



}
