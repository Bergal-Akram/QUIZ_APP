package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        EditText username = findViewById(R.id.name_log);
        EditText password = findViewById(R.id.Pass_log);
        TextView Sign_page = findViewById(R.id.signup_now);
        TextView reset = findViewById(R.id.frg_log);
        Button lgn = findViewById(R.id.btn_log);

        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text_username = username.getText().toString();
                String text_password = password.getText().toString();

                    Intent intent = new Intent(LoginPage.this,StartQuiz.class);
                    //intent.putExtra("user_name",text_username);
                    startActivity(intent);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this,ForgotPage.class);
                startActivity(intent);
            }
        });

        Sign_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this,SignUp.class);
                startActivity(intent);
            }
        });
    }
}