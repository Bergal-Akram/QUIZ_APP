package com.example.quiz_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class QuizQuestions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionquiz);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new QuestionFragment())
                .commit();
    }
}



