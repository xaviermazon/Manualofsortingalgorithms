package com.example.manualofsortingalgorithms;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class BubbleSort {

    int[] array, status, newStatus;
    int level, iPlayer, jPlayer, jPlayerPlus;
    PolygonManager pm;
    String[] numsDraw;
    boolean switched = true;
    Paint tmpCell;
    Button btnCheckFase;

    BubbleSort(int level) {
        int length = 0, range = 0;
        this.level = level;
        this.iPlayer = 0;
        this.jPlayer = 0;
        this.jPlayerPlus = 1;
        pm = new PolygonManager();
        switch(level) {
            case 1:  length = 10;
                     range = 20;
                     break;
            case 2:  length = 30;
                     range = 40;
                     break;
            default: length = 10;
                     range = 20;
        }
        array = new int[length];
        status = new int[length];
        newStatus = new int[length];
        numsDraw = new String[length];
        for(int i = 0; i < length; i++) {
            array[i] = new Random().nextInt(range);
            status[i] = array[i];
            newStatus[i] = array[i];
            pm.add(new Polygon(new point(7f+(16*i), -4f), 8f, 4));
            if(array[i] <= 9) numsDraw[i] = (" "+String.valueOf(array[i]));
            else numsDraw[i] = String.valueOf(array[i]);
        }
        tmpCell = new Paint();
    }

    void bubbleSortFase() {
        if(iPlayer < array.length) {
            for(int j = 0; j < (newStatus.length-1); j++) {
                if(newStatus[j] > newStatus[j+1]) {
                    int tmp = newStatus[j];
                    newStatus[j] = newStatus[j+1];
                    newStatus[j+1] = tmp;
                }
            }
        }
    }

    boolean bubbleSortCheckerFase() {
        for(int i = 0; i < array.length; i++)
            if(array[i] != newStatus[i]) return false;
        return true;
    }

    void bubbleSort() {
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

    void bubbleSortImproved() {
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
        int tmp = array[jPlayer];
        array[jPlayer] = array[jPlayerPlus];
        array[jPlayerPlus] = tmp;
    }

    void draw(Canvas canvas) {
        //canvas.drawPaint(tmpCell);

        tmpCell.setColor(Color.BLACK);

        tmpCell.setStyle(Paint.Style.FILL);
        tmpCell.setTextSize(12f);
        for(int i = 0; i < numsDraw.length; i++)
            canvas.drawText(numsDraw[i],0.0f+(16*i),0.0f, tmpCell);
        pm.draw(canvas);

        if(iPlayer == jPlayer) canvas.drawText("i j",3.0f+(16*iPlayer),18.0f, tmpCell);
        else {
            canvas.drawText("j", 6.0f + (16 * jPlayer), 18.0f, tmpCell);
            canvas.drawText("i", 6.0f + (16 * iPlayer), 18.0f, tmpCell);
        }
        canvas.drawText("j'", 6.0f + (16 * jPlayerPlus), 18.0f, tmpCell);

        tmpCell.setStyle(Paint.Style.STROKE);
        tmpCell.setStrokeWidth(1f);

        canvas.drawArc(new RectF(-2.0f+(16*iPlayer), 6.0f,0.0f+(16*(iPlayer+1))
                ,25.0f),225f,90f,false,tmpCell);
        canvas.drawArc(new RectF(-2.0f+(16*jPlayer), 6.0f,0.0f+(16*(jPlayer+1))
                ,25.0f),225f,90f,false,tmpCell);
        canvas.drawArc(new RectF(-2.0f+(16*jPlayerPlus), 6.0f,0.0f+(16*(jPlayerPlus+1))
                ,25.0f),225f,90f,false,tmpCell);
        pm.draw(canvas);
    }

    LinearLayout PutPanel(MainActivity mainActivity) {
        LinearLayout llPanelInteractive = new LinearLayout(mainActivity);
        llPanelInteractive.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        llPanelInteractive.setOrientation(LinearLayout.HORIZONTAL);
        llPanelInteractive.setGravity(Gravity.CENTER_HORIZONTAL);

        Button btnSwitchElement = new Button(mainActivity);
        btnSwitchElement.setText("j <> j'");
        btnSwitchElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToElements();
                pm.clearPolygons();
                for(int i = 0; i < array.length; i++) {
                    pm.add(new Polygon(new point(7f+(16*i), -4f), 8f, 4));
                    if(array[i] <= 9) numsDraw[i] = (" "+String.valueOf(array[i]));
                    else numsDraw[i] = String.valueOf(array[i]);
                }
                mainActivity.draw();
            }
        });
        llPanelInteractive.addView(btnSwitchElement);

        Button btnMoveCursorJ = new Button(mainActivity);
        btnMoveCursorJ.setText("J & J' >>");
        btnMoveCursorJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jPlayer < array.length-2) {
                    jPlayer++;
                    jPlayerPlus++;
                }
                if(jPlayer == array.length-2) btnCheckFase.setVisibility(View.VISIBLE);
                mainActivity.draw();
            }
        });
        llPanelInteractive.addView(btnMoveCursorJ);

        btnCheckFase = new Button(mainActivity);
        btnCheckFase.setText("Check status");
        btnCheckFase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bubbleSortFase();
                if(bubbleSortCheckerFase()) {
                    Toast toast = Toast.makeText(mainActivity, "Well done!", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(mainActivity, "There are some bugs check them out!", Toast.LENGTH_LONG);
                    toast.show();
                }
                mainActivity.draw();
            }
        });
        btnCheckFase.setVisibility(View.INVISIBLE);
        llPanelInteractive.addView(btnCheckFase);
        return llPanelInteractive;
    }
}
