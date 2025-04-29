package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        EditText etUsername = findViewById(R.id.et_username);
        EditText etPassword = findViewById(R.id.et_password);
        Button btnRegister = findViewById(R.id.btn_register);
        TextView Have_acc = findViewById(R.id.login_now);

        Database_Quiz databaseHelper = new Database_Quiz(this);

        btnRegister.setOnClickListener(v -> {
            String username  = etUsername.getText().toString();
            String password   = etPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(SignUp.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 6) {
                Toast.makeText(SignUp.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            } else if (databaseHelper.checkUsername(username)) {
                Toast.makeText(SignUp.this, "Username already exists! Choose another one.", Toast.LENGTH_SHORT).show();
            } else {
                boolean success = databaseHelper.addUser(username, password);
                if (success) {
                    Toast.makeText(SignUp.this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                    finish(); // يرجع لصفحة تسجيل الدخول
                } else {
                    Toast.makeText(SignUp.this, "Error: Could not create account", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Have_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, LoginPage.class);
                startActivity(intent);
            }
        });
    }
}
