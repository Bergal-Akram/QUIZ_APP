package com.example.quiz_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    EditText username, password;
    Button lgn;
    TextView Sign_page, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        username = findViewById(R.id.name_log);
        password = findViewById(R.id.Pass_log);
        Sign_page = findViewById(R.id.signup_now);
        reset = findViewById(R.id.frg_log);
        lgn = findViewById(R.id.btn_log);

        SharedPreferences sharedPreferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("username", null);

        // ✅ تسجيل الدخول التلقائي إذا كان المستخدم مسجل مسبقًا
        if (savedUsername != null) {
            Intent intent = new Intent(LoginPage.this, StartQuiz.class);
            startActivity(intent);
            finish();
        }

        Database_Quiz dbHelper = new Database_Quiz(LoginPage.this);

        lgn.setOnClickListener(view -> {
            String text_username = username.getText().toString().trim();
            String text_password = password.getText().toString().trim();

            if (text_username.isEmpty() || text_password.isEmpty()) {
                Toast.makeText(LoginPage.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean isValid = dbHelper.checkUser(text_username, text_password);

                if (isValid) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", text_username);
                    editor.apply();

                    Intent intent = new Intent(LoginPage.this, StartQuiz.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginPage.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        reset.setOnClickListener(view -> {
            Intent intent = new Intent(LoginPage.this, ForgotPage.class);
            startActivity(intent);
        });

        Sign_page.setOnClickListener(view -> {
            Intent intent = new Intent(LoginPage.this, SignUp.class);
            startActivity(intent);
        });
    }
}
