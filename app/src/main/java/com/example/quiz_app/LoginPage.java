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
        setContentView(R.layout.activity_main);

        EditText username = findViewById(R.id.name_log);
        EditText password = findViewById(R.id.Pass_log);
        TextView Sign_page = findViewById(R.id.signup_now);
        TextView reset = findViewById(R.id.frg_log);
        Button login = findViewById(R.id.btn_log);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text_username = username.getText().toString();
                String text_password = password.getText().toString();

                if(text_username.equals("akram@gmail.com") && text_password.equals("password0123456789")){
                    Intent intent = new Intent(LoginPage.this , StartQuiz.class);
                    intent.putExtra("user_name",text_username);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginPage.this,"username or password are wrong",Toast.LENGTH_SHORT).show();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, ForgotPage.class);
                startActivity(intent);
            }
        });
    }
}