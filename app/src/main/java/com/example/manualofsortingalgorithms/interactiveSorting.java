package com.example.manualofsortingalgorithms;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class interactiveSorting extends AppCompatActivity {

    LinearLayout linlay;
    int[] array = new int[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    String bufArray = "";
    TextView txtArraySorted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        linlay = new LinearLayout(this);
        linlay.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        linlay.setOrientation(LinearLayout.VERTICAL);
        linlay.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView txtAnuncio = new TextView(this);
        txtAnuncio.setText("Choose a sort's algorithm");
        linlay.addView(txtAnuncio);

        bufArray = "<[";
        Log.e("ERROR ", String.valueOf(array.length));
        for (int i = 0; i < array.length; i++) {
            bufArray = bufArray + Integer.toString(array[i]);
            if (i < array.length - 1) bufArray += ", ";
            else bufArray += "]>";
        }
        Log.e("ERROR ", bufArray);
        TextView txtArrayUnsorted = new TextView(this);
        txtArrayUnsorted.setText(bufArray);
        linlay.addView(txtArrayUnsorted);

        Button btnSort = new Button(this);
        btnSort.setText("Sort array!");
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuickSort qSort = new QuickSort(array);
                qSort.sort();
                array = qSort.getArray();
                bufArray = "";
                bufArray = "<[";
                Log.e("ERROR ", String.valueOf(array.length));
                for (int i = 0; i < array.length; i++) {
                    bufArray = bufArray + Integer.toString(array[i]);
                    if (i < array.length - 1) bufArray += ", ";
                    else bufArray += "]>";
                }
                Log.e("ERROR ", bufArray);
                txtArraySorted.setText(bufArray);
            }
        });
        linlay.addView(btnSort);

        bufArray = "";
        bufArray = "<[";
        Log.e("ERROR ", String.valueOf(array.length));
        for (int i = 0; i < array.length; i++) {
            bufArray = bufArray + Integer.toString(array[i]);
            if (i < array.length - 1) bufArray += ", ";
            else bufArray += "]>";
        }
        Log.e("ERROR ", bufArray);
        txtArraySorted = new TextView(this);
        txtArraySorted.setText(bufArray);
        linlay.addView(txtArraySorted);

        setContentView(linlay);
    }
}
