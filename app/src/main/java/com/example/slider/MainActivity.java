package com.example.slider;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    ViewFlipper v_flipper;
   private Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenActivity2();
            }
        });

        int images[] = {R.drawable.race3,R.drawable.endgame,R.drawable.blackpanther};
        v_flipper = findViewById(R.id.v_flipper);

        for (int image: images){
            flipperImages(image);



        }


    }
    public void flipperImages(int image){

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(2500);
        v_flipper.setAutoStart(true);
        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);


    }

    public void OpenActivity2(){
        Intent intent = new Intent(this, ActivityLogin.class);
        startActivity(intent);

    }
}
