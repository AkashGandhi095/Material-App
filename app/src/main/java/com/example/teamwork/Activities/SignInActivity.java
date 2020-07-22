package com.example.teamwork.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.teamwork.Modal.LoginData;
import com.example.teamwork.R;
import com.example.teamwork.Utils.AppDatabase;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private TextInputEditText emailEditText , passwordEditText;
    private MaterialButton signInButton;
    private MaterialTextView hintText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initView();

        signInButton.setOnClickListener(v -> SignIn());

        hintText.setOnClickListener(v -> {
            startActivity(new Intent(this , SignUpActivity.class));
            finish();
        });


    }

    private void initView() {
        emailEditText = findViewById(R.id.editEmailSignIn);
        passwordEditText = findViewById(R.id.editPasswordSignIn);
        signInButton = findViewById(R.id.editSignInButton);
        layout = findViewById(R.id.constraint);
        hintText = findViewById(R.id.hintText);

    }

    private void SignIn()
    {
        String email = Objects.requireNonNull(emailEditText.getText()).toString().trim();
        String password = Objects.requireNonNull(passwordEditText.getText()).toString().trim();

        Thread thread = new Thread(() -> {
            boolean isLoginData = AppDatabase.getAppDatabase(SignInActivity.this).
                    dao().
                    getLoginData(email , password);


            if(isLoginData)
            {
                Snackbar.make(layout , "Login Successfully" , Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(this , HomeActivity.class);
                intent.putExtra("mEmail" , email);
                startActivity(intent);
                finish();
            }
            else
            {
                Snackbar.make(layout , "Login failed , Please Check your credentials !!" , Snackbar.LENGTH_SHORT).show();
                // Toast.makeText(this, "Login failed ! Please check your Credentials", Toast.LENGTH_SHORT).show();
            }

        });
        thread.start();
    }



}