package com.example.manualofsortingalgorithms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Lesson extends AppCompatActivity {
    BubbleSort bs;

    private void changeToExercise(int value, boolean improved) {
        Intent myIntent = new Intent(this, InteractiveSorting.class);
        myIntent.putExtra("level", value); //Optional parameters
        myIntent.putExtra("improved", improved);
        startActivity(myIntent);
    }

    void llPanelExercice(LinearLayout llExplain,boolean improved) {

        TextView txtAnnounceLevel = new TextView(this);
        txtAnnounceLevel.setText("Una vez leido la explicacion te propongo 3 ejercicios de diferentes dificultades. \nEscoje un nivell:\n");
        txtAnnounceLevel.setPadding(20,0,0,20);
        llExplain.addView(txtAnnounceLevel);

        Button btnEasyExercise = new Button(this);
        btnEasyExercise.setText("Easy (5 elements, between 0 & 10)");
        btnEasyExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToExercise(0,improved);
            }
        });
        llExplain.addView(btnEasyExercise);

        Button btnNormalExercise = new Button(this);
        btnNormalExercise.setText("Normal (15 elements, between 0 & 20)");
        btnNormalExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToExercise(1, improved);
            }
        });
        llExplain.addView(btnNormalExercise);

        Button btnHardExercise = new Button(this);
        btnHardExercise.setText("Hard (30 elements, between 0 & 40)");
        btnHardExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToExercise(2,improved);
            }
        });
        llExplain.addView(btnHardExercise);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int value = intent.getIntExtra("key", 0);
        if(value == 0) bs = new BubbleSort();

        LinearLayout llExplain = bs.explainAlgorithm(this);
        ScrollView sv = new ScrollView(this);
        sv.addView(llExplain);

        llPanelExercice(llExplain,false);

        if(bs != null && bs.variant) {
            llExplain.addView(bs.explainAlgorithmVariant(this));
            llPanelExercice(llExplain,true);
        }

        setContentView(sv);
    }
}
