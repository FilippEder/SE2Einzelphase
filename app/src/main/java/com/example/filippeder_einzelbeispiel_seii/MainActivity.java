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

        //Aufgabe 2.1
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

        //Aufgabe 2.2
        Button calculateButton = findViewById(R.id.button2);
        calculateButton.setOnClickListener(v -> {
            String matrikelNumber = numberInput.getText().toString();
            StringBuilder result = new StringBuilder();
            result.append("Prime numbers: ");
            for(int i = 1;i<=matrikelNumber.length();i++){
                String digit = matrikelNumber.substring(i-1,i);
                if(digit.matches("[12357]")) {
                    result.append(digit);
                    result.append(",");
                }
            }
            answer.setText(result.toString());
        });
    }
}