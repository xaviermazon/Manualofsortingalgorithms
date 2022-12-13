package com.example.manualofsortingalgorithms;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    LinearLayout llListSorting;
    Configuration config;
    MainActivity mainActivity;

    private void changeToTutorial() {
        Intent myIntent = new Intent(this, interactiveSorting.class);
        //myIntent.putExtra("key", "bs"); //Optional parameters
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
        Log.e("StatusBar:","ActionBar: "+this.getSupportActionBar().isShowing());


        TextView txtTitle = new TextView(this);
        txtTitle.setTextSize(24f);
        txtTitle.setText("Choose a sorting algorithm you want to learn");
        llListSorting.addView(txtTitle);

        Button btnBubbleSort = new Button(this);
        btnBubbleSort.setText("Bubble Sort");
        btnBubbleSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToTutorial();
            }
        });
        llListSorting.addView(btnBubbleSort);

        Button btnInsertionSort = new Button(this);
        btnInsertionSort.setText("Bubble Sort");
        btnInsertionSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "In develpoment", Toast.LENGTH_SHORT).show();
            }
        });
        llListSorting.addView(btnInsertionSort);

        setContentView(llListSorting);
    }
}