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
    private final String[] moneyLevels = {
            "","$50","$100","$200","$300","$500","$1000","$2000","$4000","$8000","$16000","$32000",
            "$64000","$125000","$500000","$1000000"};
    private int numberQuestion;
    private TextView mMoneyLevelTextView;
    private TextView mGameOverTextView;

    public static Intent gameOver(Context packageContext, int numberQuestion) {
        Intent intent = new Intent(packageContext, Game.class);
        intent.putExtra(GAME_IS_OVER, numberQuestion);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);

        if (savedInstanceState != null) {
            //numberQuestion = savedInstanceState.getInt(GAME_IS_OVER, 1);
            Bundle extras = getIntent().getExtras();
            numberQuestion = extras.getInt("numberQuestion");

        }

        mMoneyLevelTextView = (TextView) findViewById(R.id.money_level);
        mGameOverTextView = (TextView) findViewById(R.id.game_over);

        System.out.print(numberQuestion);
        mMoneyLevelTextView.setText(numberQuestion+"");
        mGameOverTextView.setText(numberQuestion+"");

        //mMoneyLevelTextView.setText(moneyLevels[numberQuestion-1]);
        //mGameOverTextView.setText("Congratulations! You've answered "+ (numberQuestion-1) +
        //        " questions and won " + moneyLevels[numberQuestion-1] + " dollars!");


        /*
        questions = getResources().getStringArray(R.array.question);

        mMoneyLevelTextView = (TextView) findViewById(R.id.money_level);
        mMoneyLevelTextView.setText(moneyLevels[numberQuestion]);
        mQuestionTextView = (TextView) findViewById(R.id.question);

        mAnswerAButton = (Button) findViewById(R.id.a);
        mAnswerBButton = (Button) findViewById(R.id.b);
        mAnswerCButton = (Button) findViewById(R.id.c);
        mAnswerDButton = (Button) findViewById(R.id.d);

        randomNumber = new Random().nextInt(6) + 0;
        String nextQuestion = questions[randomNumber];
        mQuestionTextView.setText(nextQuestion);

        String[] correctAnswers = getResources().getStringArray(R.array.correct_answers);
        mAnswerAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (correctAnswers[randomNumber].equals("a")) {
                    //new question
                    int messageResourceId = 0;
                    numberQuestion++;
                    mMoneyLevelTextView.setText(moneyLevels[numberQuestion]);

                    randomNumber = new Random().nextInt(6) + 0;
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
                setAnswerShownResult(true);
            }
        });*/

    }


}
