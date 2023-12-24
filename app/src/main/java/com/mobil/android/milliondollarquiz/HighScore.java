package com.mobil.android.milliondollarquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HighScore extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{

    private static final String GAME_IS_OVER =
            "com.mobil.android.MillionDollarQuiz.game_is_over";
    private static final String KEY_NUMBER_QUESTION = "numberQuestion";
    private final String[] moneyLevels = {
            "$0","$50","$100","$200","$300","$500","$1000","$2000","$4000","$8000","$16000","$32000",
            "$64000","$125000","$500000","$1000000"};
    private int numberQuestion;
    private TextView mMoneyLevelTextView;
    private TextView mGameOverTextView;
    private Button mBackHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_score);

        if (savedInstanceState != null) {
            //numberQuestion = savedInstanceState.getInt(KEY_NUMBER_QUESTION, 1);
        }



        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("16.11.1999   " + moneyLevels[9]);
        animalNames.add("12.12.2012   " + moneyLevels[15]);
        animalNames.add("06.06.2022   " + moneyLevels[3]);
        animalNames.add("02.02.2023   " + moneyLevels[6]);
        animalNames.add("01.01.2000   " + moneyLevels[2]);

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.high_scores);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        mBackHome = (Button) findViewById(R.id.back_home);

        mBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HighScore.this, MainMenü.class);
                startActivity(intent);
            }
        });



    }
    MyRecyclerViewAdapter adapter;


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
