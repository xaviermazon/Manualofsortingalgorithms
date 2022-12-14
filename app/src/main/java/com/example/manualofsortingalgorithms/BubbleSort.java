package com.example.manualofsortingalgorithms;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class BubbleSort {

    int[] array, status, newStatus, initialStatus;
    int level, iPlayer, jPlayer, jPlayerPlus;
    PolygonManager pm;
    String[] numsDraw;
    boolean checked = false, variant = true, swap = false;
    boolean bubbleEasy = false, bubbleEasyVariant = false,
            bubbleNormal = false, bubbleNormalVariant = false,
            bubbleHard = false, bubbleHardVariant = false;
    Paint tmpCell;
    Button btnResetFase, btnCheckFase, btnNextFase, btnResetExercise, btnCheckArray;

    BubbleSort() {}

    BubbleSort(int level) {
        this.iPlayer = 0;
        this.jPlayer = 0;
        this.jPlayerPlus = 1;
        setLevel(level);
    }

    void setLevel(int level) {
        this.level = level;
        int length = 0, range = 0;
        pm = new PolygonManager();
        switch(level) {
            case 1:  length = 15;
                     range = 20;
                     break;
            case 2:  length = 30;
                     range = 40;
                     break;
            default: length = 5;
                     range = 20;
        }
        array = new int[length];
        status = new int[length];
        newStatus = new int[length];
        numsDraw = new String[length];
        initialStatus = new int[length];
        for(int i = 0; i < length; i++) {
            array[i] = new Random().nextInt(range);
            status[i] = array[i];
            newStatus[i] = array[i];
            initialStatus[i] = array[i];
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
        swap = true;
    }

    void draw(Canvas canvas) {
        //canvas.drawPaint(tmpCell);

        tmpCell.setStyle(Paint.Style.FILL);
        tmpCell.setTextSize(12f);
        for(int i = 0; i < numsDraw.length; i++) {
            if(checked) {
                if(array[i] == newStatus[i]) tmpCell.setColor(Color.GREEN);
                else tmpCell.setColor(Color.RED);
            } else tmpCell.setColor(Color.BLACK);
            canvas.drawText(numsDraw[i],0.0f+(16*i),0.0f, tmpCell);
        }
        pm.draw(canvas);

        tmpCell.setColor(Color.BLACK);
        if(iPlayer == jPlayer) {
            canvas.drawText("i j",3.0f+(16*iPlayer),18.0f, tmpCell);
            canvas.drawText("j'", 6.0f + (16 * jPlayerPlus), 18.0f, tmpCell);
        } else if(iPlayer == jPlayerPlus) {
            canvas.drawText("j", 6.0f + (16 * jPlayer), 18.0f, tmpCell);
            canvas.drawText("i j'",3.0f+(16*iPlayer),18.0f, tmpCell);
        } else {
            canvas.drawText("j", 6.0f + (16 * jPlayer), 18.0f, tmpCell);
            canvas.drawText("i", 6.0f + (16 * iPlayer), 18.0f, tmpCell);
            canvas.drawText("j'", 6.0f + (16 * jPlayerPlus), 18.0f, tmpCell);
        }

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

    LinearLayout PutPanel(InteractiveSorting iSActivity, boolean improved) {

        LinearLayout llPanelInteractive = new LinearLayout(iSActivity);
        llPanelInteractive.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        llPanelInteractive.setOrientation(LinearLayout.HORIZONTAL);
        llPanelInteractive.setGravity(Gravity.LEFT);

        btnResetFase = new Button(iSActivity);
        btnResetFase.setText("Reset fase");
        btnResetFase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jPlayerPlus = 1;
                jPlayer = 0;
                checked = false;
                swap = false;
                for(int i = 0; i < array.length; i++) {
                    array[i] = status[i];
                    pm.add(new Polygon(new point(7f+(16*i), -4f), 8f, 4));
                    if(array[i] <= 9) numsDraw[i] = (" "+String.valueOf(array[i]));
                    else numsDraw[i] = String.valueOf(array[i]);
                }
                btnCheckFase.setVisibility(View.INVISIBLE);
                btnNextFase.setVisibility(View.INVISIBLE);
                btnCheckArray.setVisibility(View.INVISIBLE);
                iSActivity.draw();
            }
        });
        llPanelInteractive.addView(btnResetFase);

        btnResetExercise = new Button(iSActivity);
        btnResetExercise.setText("Reset exercise");
        btnResetExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jPlayerPlus = 1;
                jPlayer = 0;
                iPlayer = 0;
                checked = false;
                swap = false;
                for(int i = 0; i < array.length; i++) {
                    array[i] = initialStatus[i];
                    status[i] = initialStatus[i];
                    newStatus[i] = initialStatus[i];
                    pm.add(new Polygon(new point(7f+(16*i), -4f), 8f, 4));
                    if(array[i] <= 9) numsDraw[i] = (" "+String.valueOf(array[i]));
                    else numsDraw[i] = String.valueOf(array[i]);
                }
                btnCheckFase.setVisibility(View.INVISIBLE);
                btnNextFase.setVisibility(View.INVISIBLE);
                btnCheckArray.setVisibility(View.INVISIBLE);
                iSActivity.draw();
            }
        });
        llPanelInteractive.addView(btnResetExercise);

        Button btnSwitchElement = new Button(iSActivity);
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
                iSActivity.draw();
            }
        });
        llPanelInteractive.addView(btnSwitchElement);

        Button btnMoveCursorJ = new Button(iSActivity);
        btnMoveCursorJ.setText("J & J' >>");
        btnMoveCursorJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jPlayer < array.length-2) {
                    jPlayer++;
                    jPlayerPlus++;
                }
                if(jPlayer == array.length-2) {
                    btnCheckFase.setVisibility(View.VISIBLE);
                    if(!swap) btnCheckArray.setVisibility(View.VISIBLE);
                }
                iSActivity.draw();
            }
        });
        llPanelInteractive.addView(btnMoveCursorJ);

        btnCheckFase = new Button(iSActivity);
        btnCheckFase.setText("Check status");
        btnCheckFase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bubbleSortFase();
                if(bubbleSortCheckerFase() && iPlayer < array.length-1) {
                    Toast toast = Toast.makeText(iSActivity, "Well done!", Toast.LENGTH_SHORT);
                    toast.show();
                    btnNextFase.setVisibility(View.VISIBLE);
                    btnResetFase.setVisibility(View.INVISIBLE);
                } else if(bubbleSortCheckerFase() && iPlayer == array.length-1) {
                    Toast toast = Toast.makeText(iSActivity, "Congratulations!", Toast.LENGTH_SHORT);
                    toast.show();
                    btnCheckFase.setVisibility(View.VISIBLE);
                    btnResetFase.setVisibility(View.INVISIBLE);
                } else {
                    Toast toast = Toast.makeText(iSActivity, "A few errors have been found, please try again!", Toast.LENGTH_LONG);
                    toast.show();
                    btnResetFase.setVisibility(View.VISIBLE);
                }
                checked = true;
                btnCheckFase.setVisibility(View.INVISIBLE);
                iSActivity.draw();
            }
        });
        btnCheckFase.setVisibility(View.INVISIBLE);
        llPanelInteractive.addView(btnCheckFase);

        btnNextFase = new Button(iSActivity);
        btnNextFase.setText("Next fase");
        btnNextFase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPlayer++;
                jPlayerPlus = 1;
                jPlayer = 0;
                checked = false;
                for(int i = 0; i < array.length; i++)
                    status[i] = array[i];
                btnCheckFase.setVisibility(View.INVISIBLE);
                btnNextFase.setVisibility(View.INVISIBLE);
                btnCheckArray.setVisibility(View.INVISIBLE);
                swap = false;
                iSActivity.draw();
            }
        });
        btnNextFase.setVisibility(View.INVISIBLE);
        llPanelInteractive.addView(btnNextFase);

        btnCheckArray = new Button(iSActivity);
        btnCheckArray.setText("Check status");
        btnCheckArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!swap && improved) {
                    boolean correct = true;
                    for(int i = 0; i < array.length-1 && correct;i++) {
                        correct = (array[i] <= array[i+1]);
                    }
                    if(correct) {
                        Toast toast = Toast.makeText(iSActivity, "Congratulations!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    iSActivity.draw();
                }
            }
        });
        btnCheckArray.setVisibility(View.INVISIBLE);
        llPanelInteractive.addView(btnCheckArray);

        return llPanelInteractive;
    }

    LinearLayout explainAlgorithm(Lesson lessonTutorial) {

        LinearLayout llPanelLesson = new LinearLayout(lessonTutorial);
        llPanelLesson.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        llPanelLesson.setOrientation(LinearLayout.VERTICAL);
        llPanelLesson.setGravity(Gravity.FILL_VERTICAL);

        TextView txtTitle = new TextView(lessonTutorial);
        txtTitle.setTextSize(36f);
        txtTitle.setText("Bubble Sort");
        txtTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        llPanelLesson.addView(txtTitle);

        TextView txtPropierties = new TextView(lessonTutorial);
        txtPropierties.setTextSize(16f);
        txtPropierties.setText("\nPropierties:\n - Space cost: ??(1)\n - Temporary cost: ??(n??)\n - Method: swap\n - Stable: Yes");
        txtPropierties.setPadding(20,0,0,20);
        llPanelLesson.addView(txtPropierties);

        TextView txtIntroduccion = new TextView(lessonTutorial);
        txtIntroduccion.setText("It is the first sorting algorithm that is taught to a student who aspires to be a developer, it is because of its simplicity to implement and not because of its performance.\n");
        txtIntroduccion.setPadding(20,0,0,20);
        llPanelLesson.addView(txtIntroduccion);

        TextView txtParrafo1 = new TextView(lessonTutorial);
        txtParrafo1.setText("We have a set of N numeric elements and 2 cursors are used: i and j. The cursor i will be the general cursor that will pass each of the elements of the set. Cursor j will check from its position and its next position will be from lowest to highest.\n");
        txtParrafo1.setPadding(20,0,0,20);
        llPanelLesson.addView(txtParrafo1);

        TextView txtParrafo2 = new TextView(lessonTutorial);
        txtParrafo2.setText("Once the cursor j is in the penultimate position it will return to its initial position and the cursor i will move to the next position. This process will be repeated until the i cursor is in the last position of the array.\n");
        txtParrafo2.setPadding(20,0,0,20);
        llPanelLesson.addView(txtParrafo2);

        return llPanelLesson;
    }

    LinearLayout explainAlgorithmVariant(Lesson lessonTutorial) {

        LinearLayout llPanelLesson = new LinearLayout(lessonTutorial);
        llPanelLesson.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        llPanelLesson.setOrientation(LinearLayout.VERTICAL);
        llPanelLesson.setGravity(Gravity.FILL_VERTICAL);

        TextView txtTitle = new TextView(lessonTutorial);
        txtTitle.setTextSize(36f);
        txtTitle.setText("Bubble Sort Improved");
        txtTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        txtTitle.setPadding(20,20,20,20);
        llPanelLesson.addView(txtTitle);

        TextView txtIntroduccion = new TextView(lessonTutorial);
        txtIntroduccion.setText("Unlike the base algorithm, there is an improved version, you simply have to notice if there has been a change of elements between the position of the cursor j and the next position.\n");
        txtIntroduccion.setPadding(20,0,0,20);
        llPanelLesson.addView(txtIntroduccion);

        TextView txtParrafo = new TextView(lessonTutorial);
        txtParrafo.setText("If there has been a change, the process will be repeated with the next position of the cursor i, otherwise we can order the set.\n");
        txtParrafo.setPadding(20,0,0,20);
        llPanelLesson.addView(txtParrafo);

        return llPanelLesson;
    }
}
