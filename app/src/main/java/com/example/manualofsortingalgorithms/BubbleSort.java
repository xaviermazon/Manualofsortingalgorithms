package com.example.manualofsortingalgorithms;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Random;

public class BubbleSort {

    int[] array;
    int level;

    BubbleSort(int level) {
        int length = 0, range = 0;
        this.level = level;
        switch(level) {
            case 1:  length = 10;
                     range = 20;
                     break;
            case 2:  length = 30;
                     range = 40;
                     break;
            default: length = 5;
                     range = 10;
        }
        array = new int[length];
        for(int i = 0; i < length; i++) array[i] = new Random().nextInt() % range;
    }

    void BubbleSort() {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array.length; j++) {
                if(array[j] > array[j+1]) {
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
    }

    void BubbleSortImproved() {
        for(int i = 0; i < array.length; i++) {
            boolean flag = false;
            for(int j = 0; j < array.length-i-1; j++) {
                if(array[j] > array[j+1]) {
                    flag = true;
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
            if(!flag) return;
        }
    }

    int[] getArray() {
        return array;
    }

    void switchToElements() {
        int tmp = array[0];
        array[0] = array[1];
        array[1] = tmp;
    }

    void draw(Canvas canvas) {
        Paint tmpCell = new Paint();
        tmpCell.setColor(Color.YELLOW);
        canvas.drawPaint(tmpCell);

        //canvas.scale(-1f,1f);
        //canvas.rotate(180f);
        tmpCell.setColor(Color.BLACK);
        tmpCell.setStyle(Paint.Style.FILL);
        float textsize=0.5f;
        tmpCell.setTextSize(12f);
        canvas.drawText(array[0]+" "+array[1],0.0f,0.0f, tmpCell);

        Paint paint=new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        for (int i=0;i<5;i++){
            for (int j=0;j<10;j++) {
                canvas.drawCircle(i,j,0.05f,paint);
            }
        }
    }

    LinearLayout PutPanel(MainActivity mainActivity) {
        LinearLayout llPanelInteractive = new LinearLayout(mainActivity);
        llPanelInteractive.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        llPanelInteractive.setOrientation(LinearLayout.HORIZONTAL);
        llPanelInteractive.setGravity(Gravity.CENTER_HORIZONTAL);

        Button btnSwitchElement = new Button(mainActivity);
        btnSwitchElement.setText("Switch element");
        btnSwitchElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToElements();
                mainActivity.draw();
            }
        });
        llPanelInteractive.addView(btnSwitchElement);
        return llPanelInteractive;
    }
}
