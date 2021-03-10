package com.example.countdowntimerpractice;

import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

  private static final long START_TIME_IN_MILLIS = 6000;

  private TextView countDownText;
  private Button startPauseBtn;
  private CountDownTimer countDownTimer;
  private long timerLeftTimeMs = START_TIME_IN_MILLIS; // Fourth add assignment

  // Third
  boolean timerRunning;

  // 5th
  private Button resetBtn;

  // 6th
  private EditText inputTimeSeconds;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    startPauseBtn = findViewById(R.id.start_btn);
    countDownText = findViewById(R.id.timer_text);
    updateCountDownText();

    // Fifth reset
    resetBtn = findViewById(R.id.reset_btn);

    //6th
    inputTimeSeconds = findViewById(R.id.inputTime);
    inputTimeSeconds.setText(String.valueOf(START_TIME_IN_MILLIS/1000));

    startPauseBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Third change Add pause logic.
        if (timerRunning) {
          pauseTimer();
        } else {
          startTimer();
        }
      }
    });

    // 5th
    resetBtn.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        resetTimer();
      }
    });
  }

  private void startTimer() {
    countDownTimer = new CountDownTimer(timerLeftTimeMs, 1000) {
      @Override
      public void onTick(long millisUntilFinished) {
        timerLeftTimeMs = millisUntilFinished;
        updateCountDownText();
      }
      @Override
      public void onFinish() {
        //Second
        startPauseBtn.setText("Start");
        //Third
        timerRunning = false;
      }
    }.start();

    // Second
    startPauseBtn.setText("pause");
    //Third
    timerRunning = true;
  }

  // Third
  private void pauseTimer() {
    if (countDownTimer != null) {
      countDownTimer.cancel();
    }
    timerRunning = false;
    startPauseBtn.setText("start");
  }

  // 5th
  private void resetTimer() {
    pauseTimer();
    // change assignment 6th
    timerLeftTimeMs = Long.parseLong(inputTimeSeconds.getText().toString()) * 1000;
    updateCountDownText();
  }

  private void updateCountDownText() {
    int minutes = (int) (timerLeftTimeMs / 1000) / 60;
    int seconds = (int) (timerLeftTimeMs / 1000) % 60;
    String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    countDownText.setText(timeLeftFormatted);
  }
}