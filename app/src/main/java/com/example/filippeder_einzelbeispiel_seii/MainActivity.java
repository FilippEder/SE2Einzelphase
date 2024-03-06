package com.example.filippeder_einzelbeispiel_seii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private MyThread thread;
    private TextView answer;
    private EditText numberInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answer = findViewById(R.id.ServerAnswer);
        numberInput = findViewById(R.id.NumberInput);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            if(!numberInput.getText().toString().equals("")) {
                String matrikelNumber = numberInput.getText().toString();
                handler = new Handler();
                thread = new MyThread(matrikelNumber,handler,answer);
                thread.start();
            } else{
                answer.setText("Can't send a request without Matrikelnummer.");
            }
        });

        Button calculateButton = findViewById(R.id.button2);
        calculateButton.setOnClickListener(v -> {
            String matrikelNumber = numberInput.getText().toString();

        });
    }
}