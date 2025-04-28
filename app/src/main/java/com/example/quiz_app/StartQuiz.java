package com.example.quiz_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StartQuiz extends AppCompatActivity {
    Button btnstart;
    TextView tvDesc , tvTit ;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startquiz);

        btnstart = findViewById(R.id.btnStartQuiz);
        tvDesc = findViewById(R.id.tvDescription);
        tvTit = findViewById(R.id.tv_title);

        btnstart.setOnClickListener(view -> {
            Intent intent = new Intent(StartQuiz.this, QuizQuestions.class);
            startActivity(intent);
        });
    }
}
