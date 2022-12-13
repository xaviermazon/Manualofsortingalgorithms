package com.example.manualofsortingalgorithms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Lesson extends AppCompatActivity {
    BubbleSort bs;

    private void changeToExercise(int value) {
        Intent myIntent = new Intent(this, InteractiveSorting.class);
        myIntent.putExtra("level", 0); //Optional parameters
        startActivity(myIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int value = intent.getIntExtra("key", 0);
        if(value == 0) bs = new BubbleSort(0);

        LinearLayout llExplain = bs.explainAlgorithm(this);

        TextView txtAnnounceLevel = new TextView(this);
        txtAnnounceLevel.setText("Escoje un nivell:");
        llExplain.addView(txtAnnounceLevel);

        Button btnEasyExercise = new Button(this);
        btnEasyExercise.setText("Level easy");
        btnEasyExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        llExplain.addView(btnEasyExercise);

        Button btnNormalExercise = new Button(this);
        btnNormalExercise.setText("Level normal");
        btnNormalExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        llExplain.addView(btnNormalExercise);

        Button btnHardExercise = new Button(this);
        btnHardExercise.setText("Level hard");
        btnHardExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        llExplain.addView(btnHardExercise);

        setContentView(llExplain);
    }
}
