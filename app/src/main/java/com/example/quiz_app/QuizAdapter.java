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

    private String question;
    private String[] choices;
    private OnAnswerSelectedListener listener;

    public interface OnAnswerSelectedListener {
        void onAnswerSelected(String selectedAnswer);
    }

    public QuizAdapter(String question, String[] choices, OnAnswerSelectedListener listener) {
        this.question = question;
        this.choices = choices;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quest, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        if (position == 0) {
            holder.questionText.setText(question);
        } else {
            holder.questionText.setText("");
        }
        holder.choiceButton.setText(choices[position]);
        holder.choiceButton.setOnClickListener(v -> listener.onAnswerSelected(choices[position]));
    }

    @Override
    public int getItemCount() {
        return choices.length;
    }

    static class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView questionText;
        Button choiceButton;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.question_text);
            choiceButton = itemView.findViewById(R.id.choice_button);
        }
    }
}
