package com.example.pettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pettracker.R;

public class LoginActivity extends AppCompatActivity {

    Button LoginBtn;
    Button signUpBtn;
    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginBtn = (Button) findViewById(R.id.btnLogIn);
        signUpBtn = (Button) findViewById(R.id.btnSignUp);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignup();
            }
        });
    }

    //goes to signup page
    private void onSignup() {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    //Checks to see if login credentials are correct
    private void onLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}