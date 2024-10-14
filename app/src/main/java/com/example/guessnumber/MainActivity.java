package com.example.guessnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int attemptCount = 3;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText =findViewById(R.id.eText);
        button=findViewById(R.id.btn);

        Random random = new Random();
        int secretKey = random.nextInt(20) + 1;
        Log.i("Result", secretKey + "");

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String value=editText.getText().toString();
                int intValue;
                try {
                     intValue = Integer.parseInt(value);

                    if (intValue < 1 || intValue > 20) {
                        Toast.makeText(getApplicationContext(), "Пожалуйста, введите число от 1 до 20", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(intValue==secretKey){
                        Toast.makeText(getApplicationContext(), "Победа!", Toast.LENGTH_LONG).show();
                    }else{
                        attemptCount--;
                        if (attemptCount == 0) {
                            Toast.makeText(getApplicationContext(), "Вы исчерпали все попытки! Загаданное число: " + secretKey, Toast.LENGTH_LONG).show();
                            editText.setEnabled(false);
                        } else {
                            String hint = (intValue < secretKey) ? "Ваше число меньше загаданного числа!" : "Ваше число больше загаданного числа!";
                            Toast.makeText(getApplicationContext(), hint + " Осталось попыток: " + attemptCount, Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Пожалуйста, введите целое число", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
}
