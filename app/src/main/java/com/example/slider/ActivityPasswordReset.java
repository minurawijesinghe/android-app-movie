package com.example.slider;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityPasswordReset extends AppCompatActivity {

    EditText passReset;
    Button buttonPassReset;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        firebaseAuth = FirebaseAuth.getInstance();
        passReset = (EditText)findViewById(R.id.passReset);
        buttonPassReset = (Button)findViewById(R.id.buttonPassReset);

        buttonPassReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendResetEmail();

            }
        });
    }

    public void SendResetEmail(){
        String resetEmail;
        resetEmail = passReset.getText().toString();
        if (TextUtils.isEmpty(resetEmail)) {
            Toast.makeText(ActivityPasswordReset.this, "Empty fields are there", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.sendPasswordResetEmail(resetEmail).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(ActivityPasswordReset.this,"reset Email successfully sent to your email",Toast.LENGTH_SHORT).show();
                    LoginAgain();
                }
                else
                    {
                        Toast.makeText(ActivityPasswordReset.this,"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        return;

                    }

            }
        });

    }
    public void LoginAgain()
    {
        Intent intent = new Intent(this,ActivityLogin.class);
        startActivity(intent);
    }
}
