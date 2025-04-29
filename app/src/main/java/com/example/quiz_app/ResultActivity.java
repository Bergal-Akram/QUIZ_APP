package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
        TextView resultText;
        Button restartBtn , BacktoHome;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.result_quiz);

            resultText = findViewById(R.id.result_quiz);
            restartBtn = findViewById(R.id.restart_btn);
            BacktoHome = findViewById(R.id.back_home);

            int score = getIntent().getIntExtra("score", 0);
            int total = getIntent().getIntExtra("total", 0);

            resultText.setText("Score: " + score + " out of " + total);

            restartBtn.setOnClickListener(v -> {
                Intent intent = new Intent(this, QuizQuestions.class);
                startActivity(intent);
            });

            BacktoHome.setOnClickListener(v -> {
                Intent intent = new Intent(this, StartQuiz.class);
                startActivity(intent);
            });
        }
    }
