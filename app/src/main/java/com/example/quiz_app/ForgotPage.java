package com.example.quiz_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ForgotPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ForgotPage);

        EditText EmailSend = findViewById(R.id.email_send);
        Button btnSendReset = findViewById(R.id.btnSendReset);

        btnSendReset.setOnClickListener(v -> {
            String input = EmailSend.getText().toString().trim(); // trim(): remove whitespace
            if (input.isEmpty()) {
                Toast.makeText(this, "Please enter your email or username", Toast.LENGTH_SHORT).show();
            } else {
                // how to send email
                Toast.makeText(this, "Check your email to reset password!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
