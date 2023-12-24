package com.mobil.android.milliondollarquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class GameOver extends AppCompatActivity {

    private static final String GAME_IS_OVER =
            "com.mobil.android.MillionDollarQuiz.game_is_over";
    private static final String KEY_NUMBER_QUESTION = "numberQuestion";
    private final String[] moneyLevels = {
            "$0","$50","$100","$200","$300","$500","$1000","$2000","$4000","$8000","$16000","$32000",
            "$64000","$125000","$500000","$1000000"};
    private int numberQuestion;
    private TextView mMoneyLevelTextView;
    private TextView mGameOverTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);

        if (savedInstanceState != null) {
            numberQuestion = savedInstanceState.getInt(KEY_NUMBER_QUESTION, 1);
        }

        Intent intent = getIntent();
        Integer numberQuestion = intent.getIntExtra("NUMBER_QUESTION",1);

        mMoneyLevelTextView = (TextView) findViewById(R.id.money_level);
        mGameOverTextView = (TextView) findViewById(R.id.game_over);

        mMoneyLevelTextView.setText(moneyLevels[numberQuestion-1]);
        if(numberQuestion>15){
            mGameOverTextView.setText("Congratulations! You won the 1 million dollar prize!");
        }else{
            mGameOverTextView.setText("Congratulations! You've answered "+ (numberQuestion-1) +
                    " questions and won " + moneyLevels[numberQuestion-1] + " dollars!");
        }


    }


}
