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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityLogin extends AppCompatActivity {

    Button buttonLogin,buttonLRegister;
    FirebaseAuth mAuth;
    EditText inLpass,inLemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inLemail = (EditText)findViewById(R.id.inLEmail);
        inLpass = (EditText)findViewById(R.id.inLpass);
        mAuth = FirebaseAuth.getInstance();
        buttonLRegister = (Button) findViewById(R.id.buttonLRegister);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);






        buttonLRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityLogin.this,ActivityRegister.class));
            }
        });




        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    public void userLogin()
    {

        String email,password;

        email = inLemail.getText().toString();
        password = inLpass.getText().toString();

        if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)) {
            Toast.makeText(ActivityLogin.this, "Empty fields are there", Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(ActivityLogin.this,"Login successful..",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Continue();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(ActivityLogin.this, "Authentication failed."+task.getException().getMessage()+"Try again",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    public void Continue(){
        Intent intent = new Intent(this,Activity2.class);
        startActivity(intent);
    }
    public void NewLogin(){
        Intent intent = new Intent(this,ActivityLogin.class);
        startActivity(intent);
    }


    public void ResetPassword(View view) {

        Intent intent = new Intent(this,ActivityPasswordReset.class);
        startActivity(intent);
    }
}

