package com.mobil.android.milliondollarquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.TextView;

public class Game extends AppCompatActivity {
    private int numberQuestion =1;

    //first element of moneyLevels array is empty because question numbers start with 1
    private String[] moneyLevels = {
           "","$50","$100","$200","$300","$500","$1000","$2000","$4000","$8000","$16000","$32000","$64000","$125000","$500000","$1000000"
    };
    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN =
            "com.bignerdranch.android.geoquiz.answer_shown";
    private static final String KEY_INDEX = "kopya";

    private boolean mAnswerIsTrue;
    private boolean isAnswerShown=false;

    private TextView mAnswerTextView;
    private TextView mQuestionNumberTextView;
    private Button mShowAnswerButton;

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
        setContentView(R.layout.activity_cheat);

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

        mQuestionNumberTextView = (TextView) findViewById(R.id.money_level);
        mQuestionNumberTextView.setText(moneyLevels[numberQuestion]);
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
