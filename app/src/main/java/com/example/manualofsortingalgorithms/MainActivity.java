package com.example.manualofsortingalgorithms;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    LinearLayout llListSorting;
    Configuration config;
    MainActivity mainActivity;

    private void changeToTutorial(int value) {
        Intent myIntent = new Intent(this, Lesson.class);
        myIntent.putExtra("key", value); //Optional parameters
        startActivity(myIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        mainActivity = this;

        llListSorting = new LinearLayout(this);
        llListSorting.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        llListSorting.setOrientation(LinearLayout.VERTICAL);
        llListSorting.setGravity(Gravity.CENTER_HORIZONTAL);

        config = this.getResources().getConfiguration();
        if(!this.getSupportActionBar().isShowing()) this.getSupportActionBar().show();

        TextView txtTitle = new TextView(this);
        txtTitle.setTextSize(24f);
        txtTitle.setText("Choose a sorting algorithm you want to learn");
        llListSorting.addView(txtTitle);

        Button btnBubbleSort = new Button(this);
        btnBubbleSort.setText("Bubble Sort");
        btnBubbleSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToTutorial(0);
            }
        });
        llListSorting.addView(btnBubbleSort);

        Button btnInsertionSort = new Button(this);
        btnInsertionSort.setText("Insertion Sort");
        btnInsertionSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "In development", Toast.LENGTH_SHORT).show();
            }
        });
        llListSorting.addView(btnInsertionSort);

        Button btnSelectionSort = new Button(this);
        btnSelectionSort.setText("Selection Sort");
        btnSelectionSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "In development", Toast.LENGTH_SHORT).show();
            }
        });
        llListSorting.addView(btnSelectionSort);

        Button btnMergeSort = new Button(this);
        btnMergeSort.setText("Merge Sort");
        btnMergeSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToTutorial(3);
            }
        });
        llListSorting.addView(btnMergeSort);

        setContentView(llListSorting);
    }
}