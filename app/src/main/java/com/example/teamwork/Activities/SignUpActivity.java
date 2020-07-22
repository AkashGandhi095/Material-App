package com.example.teamwork.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ScrollView;

import com.example.teamwork.Activities.SignInActivity;
import com.example.teamwork.Modal.LoginData;
import com.example.teamwork.R;
import com.example.teamwork.Utils.AppDatabase;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    private static final String STR_REGEX = "^[a-zA-Z ]*$";

    private TextInputEditText emailEditText , nameEditText , passwordEditText , reEnterPasswordEditText;
    private MaterialButton signUpButton;
    private ScrollView scrollView;
    private MaterialTextView hintText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();

        signUpButton.setOnClickListener(v -> validateInputFields());

        hintText.setOnClickListener(v ->{
            startActivity(new Intent(this , SignInActivity.class));
            finish();
        });



    }

    private void initViews() {

        emailEditText = findViewById(R.id.editEmailSignUp);
        nameEditText = findViewById(R.id.editNameSignUp);
        passwordEditText = findViewById(R.id.editPasswordSignUp);
        reEnterPasswordEditText = findViewById(R.id.editRePasswordSignUp);
        signUpButton = findViewById(R.id.editSignUpButton);
        scrollView = findViewById(R.id.scrollView);
        hintText = findViewById(R.id.signInHint);

    }

    private void validateInputFields() {

        if(!isEmailValid())
        {
            showValidationError("Please Enter a Valid Email !!");
        }
        else if (!isNameValid())
        {
            showValidationError("Name should be in Alphabets only !!");

        }
        else if(!isConfirmPasswordValid())
        {
            showValidationError("Password didn't match !!");

        }
        else
        {
            saveLoginData();
        }
    }

    private boolean isEmailValid()
    {
        String email = Objects.requireNonNull(emailEditText.getText()).toString().trim();
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isNameValid()
    {
        String name = Objects.requireNonNull(nameEditText.getText()).toString().trim();
        return name.matches(STR_REGEX);
    }

    private boolean isConfirmPasswordValid()
    {
        String password = Objects.requireNonNull(passwordEditText.getText()).toString().trim();
        String confirmPassword = Objects.requireNonNull(reEnterPasswordEditText.getText()).toString().trim();
        return password.equals(confirmPassword);
    }

    private void showValidationError(String error)
    {
        Snackbar.make(scrollView ,
                error ,
                Snackbar.LENGTH_SHORT).show();
    }

    private void saveLoginData()
    {
        String email = Objects.requireNonNull(emailEditText.getText()).toString().trim();
        String name = Objects.requireNonNull(nameEditText.getText()).toString().trim();
        String password = Objects.requireNonNull(passwordEditText.getText()).toString().trim();

        Thread thread = new Thread(() ->
        {
            AppDatabase.getAppDatabase(this).dao().SaveLoginData(new LoginData(
                    email, name, password ));

            startActivity(new Intent(this , SignInActivity.class));
            finish();

        });

        thread.start();
    }


}