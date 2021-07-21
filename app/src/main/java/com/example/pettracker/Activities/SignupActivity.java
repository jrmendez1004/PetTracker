package com.example.pettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pettracker.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    Button signUpBtn;
    EditText etUsername;
    EditText etPassword;
    EditText etConfirmPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUpBtn = (Button) findViewById(R.id.btnSignUp);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPw = (EditText) findViewById(R.id.etConfirmPw);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createHousehold();
            }
        });

    }

    private void createHousehold() {
        if(etPassword.getText().toString().compareTo(etConfirmPw.getText().toString()) == 0){
            ParseUser user = new ParseUser();
            user.setUsername(etUsername.getText().toString());
            user.setPassword(etPassword.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e == null) {
                        finish();
                        Toast.makeText(SignupActivity.this, "Account Created!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(SignupActivity.this, "Something went wrong :(", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        else {
            Toast.makeText(SignupActivity.this, "Passwords must match", Toast.LENGTH_SHORT).show();
        }
    }
}