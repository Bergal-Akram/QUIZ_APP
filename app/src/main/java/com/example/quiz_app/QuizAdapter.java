package com.example.quiz_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {



        private String[] questions;
        private String[][] choices;
        private String[] selectedAnswers;
        private OnAnswerSelectedListener listener;


    public interface OnAnswerSelectedListener {
            void onAnswerSelected(int position, String selectedAnswer);
        }

        public QuizAdapter(String[] questions, String[][] choices, OnAnswerSelectedListener listener) {
            this.questions = questions;
            this.choices = choices;
            this.listener = listener;
            selectedAnswers = new String[questions.length];
        }

        @NonNull
        @Override
        public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quest, parent, false);
            return new QuizViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
            return questions.length;
        }

        public void setSelectedAnswer(int position, String selectedAnswer) {
            selectedAnswers[position] = selectedAnswer;
        }

        public int getCorrectAnswersCount() {
            int count = 0;
            for (int i = 0; i < questions.length; i++) {
                if (QuestionAnswer.correctAnswers[i].equals(selectedAnswers[i])) {
                    count++;
                }
            }
            return count;
        }

        class QuizViewHolder extends RecyclerView.ViewHolder {

            TextView questionText;
            Button choiceA, choiceB, choiceC, choiceD;

            public QuizViewHolder(@NonNull View itemView) {
                super(itemView);
                questionText = itemView.findViewById(R.id.question_text);
                choiceA = itemView.findViewById(R.id.choiceA);
                choiceB = itemView.findViewById(R.id.choiceB);
                choiceC = itemView.findViewById(R.id.choiceC);
                choiceD = itemView.findViewById(R.id.choiceD);
            }

            void bind(int position) {
                questionText.setText(questions[position]);
                choiceA.setText(choices[position][0]);
                choiceB.setText(choices[position][1]);
                choiceC.setText(choices[position][2]);
                choiceD.setText(choices[position][3]);

                View.OnClickListener choiceClickListener = v -> {
                    Button selected = (Button) v;
                    listener.onAnswerSelected(position, selected.getText().toString());
                };

                choiceA.setOnClickListener(choiceClickListener);
                choiceB.setOnClickListener(choiceClickListener);
                choiceC.setOnClickListener(choiceClickListener);
                choiceD.setOnClickListener(choiceClickListener);
            }
        }
    }

