package com.mobil.android.milliondollarquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Game extends AppCompatActivity {
    private int numberQuestion =1;
    private static final String KEY_NUMBER_QUESTION = "numberQuestion";

    //first element of moneyLevels array is empty because question numbers start with 1
    private final String[] moneyLevels = {
            "","$50","$100","$200","$300","$500","$1000","$2000","$4000","$8000","$16000","$32000",
            "$64000","$125000","$500000","$1000000"};
    private String[] questions;
    private int randomNumber = new Random().nextInt(50) + 0;
    private static final String KEY_RANDOM_NUMBER = "randomNumber";
    private int REQUEST_CODE_GAME_OVER = 0;
    private TextView mMoneyLevelTextView;
    private TextView mQuestionTextView;
    private Button mAnswerAButton;
    private Button mAnswerBButton;
    private Button mAnswerCButton;
    private Button mAnswerDButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        if (savedInstanceState != null) {
            numberQuestion = savedInstanceState.getInt(KEY_NUMBER_QUESTION, 0);
            randomNumber = savedInstanceState.getInt(KEY_RANDOM_NUMBER, 0);
        }

        questions = getResources().getStringArray(R.array.question);

        mMoneyLevelTextView = (TextView) findViewById(R.id.money_level);
        mMoneyLevelTextView.setText(moneyLevels[numberQuestion]);
        mQuestionTextView = (TextView) findViewById(R.id.question);

        mAnswerAButton = (Button) findViewById(R.id.a);
        mAnswerBButton = (Button) findViewById(R.id.b);
        mAnswerCButton = (Button) findViewById(R.id.c);
        mAnswerDButton = (Button) findViewById(R.id.d);

        String nextQuestion = questions[randomNumber];
        mQuestionTextView.setText(nextQuestion);

        String[] correctAnswers = getResources().getStringArray(R.array.correct_answers);

        mAnswerAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClicked(correctAnswers, 'a');
            }
        });
        mAnswerBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClicked(correctAnswers, 'b');
            }
        });

        mAnswerCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClicked(correctAnswers, 'c');
            }
        });
        mAnswerDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClicked(correctAnswers, 'd');
            }
        });
    }

    private void checkClicked(String[] correctAnswers, char clickedAnswer) {
        if (correctAnswers[randomNumber].equals(clickedAnswer+"")) {
            //new question
            int messageResourceId = 0;
            numberQuestion++;
            mMoneyLevelTextView.setText(moneyLevels[numberQuestion]);

            randomNumber = new Random().nextInt(50) + 0;
            String nextQuestion = questions[randomNumber];
            mQuestionTextView.setText(nextQuestion);
            messageResourceId = R.string.correct_toast;
            Toast.makeText(getApplicationContext(), messageResourceId, Toast.LENGTH_SHORT)
                    .show();
        } else {
            //game over
            Intent intent = GameOver.gameOver(Game.this, numberQuestion);
            startActivityForResult(intent, REQUEST_CODE_GAME_OVER);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_NUMBER_QUESTION, numberQuestion);
        savedInstanceState.putInt(KEY_RANDOM_NUMBER, randomNumber);
    }
}
