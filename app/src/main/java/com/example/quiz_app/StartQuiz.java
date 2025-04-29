package com.example.quiz_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class StartQuiz extends AppCompatActivity {
    Button btnstart , btnLogOut;
    TextView tvDesc ;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startquiz);

        btnstart = findViewById(R.id.btnStartQuiz);
        tvDesc = findViewById(R.id.tvDescription);
        btnLogOut = findViewById(R.id.logout_btn);

        btnstart.setOnClickListener(view -> {
            Intent intent = new Intent(StartQuiz.this, QuizQuestions.class);
            startActivity(intent);
        });

        btnLogOut.setOnClickListener(v -> {
            new AlertDialog.Builder(StartQuiz.this)
                    .setTitle("Log Out")
                    .setMessage("do you want to log out ?")
                    .setPositiveButton("yes", (dialog, which) -> {
                        // حذف بيانات SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.apply();

                        // الانتقال إلى صفحة تسجيل الدخول
                        Intent intent = new Intent(StartQuiz.this, LoginPage.class);
                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton("no", (dialog, which) -> {
                        // فقط يغلق الرسالة
                        dialog.dismiss();
                    })
                    .show();
        });

    }
}
