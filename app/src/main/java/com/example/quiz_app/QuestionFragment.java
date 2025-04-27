package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class QuestionFragment extends Fragment implements QuizAdapter.OnAnswerSelectedListener {

        private RecyclerView recyclerView;
        private QuizAdapter adapter;
        private int currentQuestionIndex = 0;
        private int score = 0;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_question, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            recyclerView = view.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            loadQuestion();
        }

        private void loadQuestion() {
            if (currentQuestionIndex == QuestionAnswer.question.length) {
                finishQuiz();
                return;
            }

            String question = QuestionAnswer.question[currentQuestionIndex];
            String[] choices = QuestionAnswer.choices[currentQuestionIndex];
            adapter = new QuizAdapter(question, choices, this);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void onAnswerSelected(String selectedAnswer) {
            if (selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])) {
                score++;
            }
            currentQuestionIndex++;
            loadQuestion();
        }

        private void finishQuiz() {
            Intent intent = new Intent(getActivity(), ResultActivity.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("TOTAL", QuestionAnswer.question.length);
            startActivity(intent);
            getActivity().finish();
        }
    }

