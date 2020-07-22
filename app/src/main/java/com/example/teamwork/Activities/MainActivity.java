package com.example.teamwork.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.teamwork.R;
import com.example.teamwork.Utils.AppDatabase;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.signInButton).
                setOnClickListener(v -> openSignInActivity());

        findViewById(R.id.signUpButton).
                setOnClickListener(v -> openSignUpActivity());



    }


    private void openSignInActivity() {
        startActivity(new Intent(this , SignInActivity.class));
        finish();
    }

    private void openSignUpActivity() {
        startActivity(new Intent(this , SignUpActivity.class));
        finish();
    }


}