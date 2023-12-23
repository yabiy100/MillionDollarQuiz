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

    //first element of moneyLevels array is empty because question numbers start with 1
    private final String[] moneyLevels = {
            "","$50","$100","$200","$300","$500","$1000","$2000","$4000","$8000","$16000","$32000",
            "$64000","$125000","$500000","$1000000"};

    private String[] questions;
    private int randomNumber;
    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.MillionDollarQuiz.answer_is_true";
    private static final String GAME_IS_OVER =
            "com.mobil.android.MillionDollarQuiz.game_is_over";
    private int REQUEST_CODE_GAME_OVER = 0;
    private static final String EXTRA_ANSWER_SHOWN =
            "com.bignerdranch.android.geoquiz.answer_shown";
    private static final String KEY_INDEX = "kopya";

    private boolean mAnswerIsTrue;
    private boolean isAnswerShown=false;

    private TextView mAnswerTextView;
    private TextView mMoneyLevelTextView;
    private TextView mQuestionTextView;
    private Button mShowAnswerButton;
    private Button mAnswerAButton;
    private Button mAnswerBButton;
    private Button mAnswerCButton;
    private Button mAnswerDButton;


    //old intent
    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent intent = new Intent(packageContext, Game.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        if (savedInstanceState != null) {
            isAnswerShown = savedInstanceState.getBoolean(KEY_INDEX, false);
            setAnswerShownResult(isAnswerShown);
        }

        /*
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);


        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });*/
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
                checkClicked(correctAnswers, 'a');
                //setAnswerShownResult(true);
            }
        });
        mAnswerBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClicked(correctAnswers, 'b');
                //setAnswerShownResult(true);
            }
        });

        mAnswerCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClicked(correctAnswers, 'c');
                //setAnswerShownResult(true);
            }
        });
        mAnswerDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkClicked(correctAnswers, 'd');
                //setAnswerShownResult(true);
            }
        });
    }

    private void checkClicked(String[] correctAnswers, char clickedAnswer) {
        if (correctAnswers[randomNumber].equals(clickedAnswer+"")) {
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
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean(KEY_INDEX, isAnswerShown);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        this.isAnswerShown=isAnswerShown;
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
}
