package com.example.quiz_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class QuestionFragment extends Fragment {

    private TextView questionText, timerText;
    private Button choiceA, choiceB, choiceC, choiceD, nextButton;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String selectedAnswer = "";
    private CountDownTimer countDownTimer;
    private static final long QUESTION_TIME = 30000; // 30 seconds
    private long timeLeftInMillis;
    private Vibrator vibrator;
    private TextView questionNumberText;
    private ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        questionText = view.findViewById(R.id.question_text);
        timerText = view.findViewById(R.id.timer_text); // اضف TextView للوقت في XML
        choiceA = view.findViewById(R.id.choiceA);
        choiceB = view.findViewById(R.id.choiceB);
        choiceC = view.findViewById(R.id.choiceC);
        choiceD = view.findViewById(R.id.choiceD);
        nextButton = view.findViewById(R.id.nextButton);
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        questionNumberText = view.findViewById(R.id.question_number);
        progressBar = view.findViewById(R.id.progressBar);
        //AnimationDrawable animationDrawable = (AnimationDrawable) progressBar.getProgressDrawable();
        //animationDrawable.start();


        loadQuestion();

        View.OnClickListener choiceClickListener = v -> {
            Button selected = (Button) v;
            selectedAnswer = selected.getText().toString();
            resetButtonColors();
            selected.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        };

        choiceA.setOnClickListener(choiceClickListener);
        choiceB.setOnClickListener(choiceClickListener);
        choiceC.setOnClickListener(choiceClickListener);
        choiceD.setOnClickListener(choiceClickListener);

        nextButton.setOnClickListener(v -> {
            if (selectedAnswer.isEmpty()) {
                Toast.makeText(getContext(), "Please select an answer", Toast.LENGTH_SHORT).show();
            } else {
                moveToNextQuestion();
            }
        });

        return view;
    }

    private void loadQuestion() {
        if (currentQuestionIndex >= QuestionAnswer.question.length) {
            currentQuestionIndex = QuestionAnswer.question.length - 1;
        }

        selectedAnswer = "";
        questionText.setText(QuestionAnswer.question[currentQuestionIndex]);
        choiceA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        choiceB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        choiceC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        choiceD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
        questionNumberText.setText("Question " + (currentQuestionIndex + 1) + "/" + QuestionAnswer.question.length);

        // تحديث شريط التقدم
        int progress = (int) (((double)(currentQuestionIndex + 1) / QuestionAnswer.question.length) * 100);
        progressBar.setProgress(progress);
        resetButtonColors();
        startTimer();
    }

    private void checkAnswer() {
        if (selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])) {
            score++;
        }
    }

    private void moveToNextQuestion() {
        countDownTimer.cancel(); // أوقف المؤقت
        checkAnswer();
        highlightCorrectAnswer(); // تلوين الإجابة الصحيحة

        //  انتظر ثانية واحدة لعرض اللون، ثم انتقل للسؤال التالي
        new android.os.Handler().postDelayed(() -> {
            currentQuestionIndex++;
            if (currentQuestionIndex < QuestionAnswer.question.length) {
                loadQuestion();
            } else {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
                intent.putExtra("score", score);
                intent.putExtra("total", QuestionAnswer.question.length);
                startActivity(intent);
                getActivity().finish();
            }
        }, 500); // 1000 ملي ثانية = 1 ثانية
    }


    private void resetButtonColors() {
        int defaultColor = ContextCompat.getColor(getContext(), R.color.purple);
        choiceA.setBackgroundColor(defaultColor);
        choiceB.setBackgroundColor(defaultColor);
        choiceC.setBackgroundColor(defaultColor);
        choiceD.setBackgroundColor(defaultColor);
    }

    private void startTimer() {
        timeLeftInMillis = QUESTION_TIME;

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                Toast.makeText(getContext(), "Time's up!", Toast.LENGTH_SHORT).show();
                moveToNextQuestion();
            }
        }.start();
    }

    private void updateTimerText() {
        int secondsLeft = (int) (timeLeftInMillis / 1000);
        timerText.setText("Time left: " + secondsLeft + "s");

        if (secondsLeft <= 5) {
            timerText.setTextColor(ContextCompat.getColor(getContext(), android.R.color.holo_red_dark));

            // اهتزاز خفيف عندما يصل الوقت إلى أقل من أو يساوي 5 ثواني
            if (vibrator != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
            } else if (vibrator != null) {
                vibrator.vibrate(200);
            }
        } else {
            timerText.setTextColor(ContextCompat.getColor(getContext(), android.R.color.black));
        }
    }
    private void highlightCorrectAnswer() {
        String correct = QuestionAnswer.correctAnswers[currentQuestionIndex];
        if (choiceA.getText().toString().equals(correct)) {
            choiceA.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green));
        } else if (choiceB.getText().toString().equals(correct)) {
            choiceB.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green));
        } else if (choiceC.getText().toString().equals(correct)) {
            choiceC.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green));
        } else if (choiceD.getText().toString().equals(correct)) {
            choiceD.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green));
        }
    }


}


