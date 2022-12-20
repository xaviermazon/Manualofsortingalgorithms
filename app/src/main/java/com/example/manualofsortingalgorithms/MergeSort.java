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

import java.util.ArrayDeque;
import java.util.Random;

public class MergeSort {

    int[] array, status, newStatus, initialStatus;
    int level, iPlayer, jPlayer, jIinitalPos, lengthPlay=2;
    PolygonManager pm;
    String[] numsDraw;
    boolean checked = false, mergeEasy = false, mergeNormal = false, mergeHard = false;
    Paint tmpCell;
    Button btnResetFase, btnCheckFase, btnNextFase, btnResetExercise, btnCheckArray;
    ArrayDeque<Integer> posChecks = new ArrayDeque<Integer>();

    MergeSort() {}

    MergeSort(int level) {
        setLevel(level);
        this.iPlayer = 0;
        this.jPlayer = iPlayer+1;
        this.jIinitalPos = jPlayer;
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
            default: length = 6;
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

    boolean mergeSortFase() {
        int i = jIinitalPos;
        if(lengthPlay < array.length) {
            for(; i < lengthPlay-1 && array[i] < array[i+1]; i+=2) {
                posChecks.add(jIinitalPos+i);
                posChecks.add(jIinitalPos+i+1);
            }
        }
        return i == lengthPlay || i == array.length;
    }

    boolean checkSorted() {
        boolean correct = true;
        for (int i = 0; i < array.length-1 && correct; i++)
            if(array[i] > array[i+1]) correct = false;

        return correct;
    }

    void merge(int min, int mid, int max) {
        int tmp[] = new int[max-min];
        int i = min, j = mid, k = 0;

        while (i < mid && j < max) {
            if (array[i] <= array[j]) {
                tmp[k] = array[i];
                i++;
            }
            else {
                tmp[k] = array[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < mid) {
            tmp[k] = array[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < max) {
            tmp[k] = array[j];
            j++;
            k++;
        }

        for (i = min; i < max; ++i)
            array[i] = tmp[i-min];
    }

    void mergeSort(int min, int max) {
        if((max - min) > 1) {
            int mid = (max + min) / 2;
            mergeSort(min, mid);
            mergeSort(mid, max);
            merge(min, mid, max);
        }
    }

    void sort() {
        mergeSort(0, array.length);
    }

    void switchToElements() {
        int tmp = array[jPlayer];
        array[jPlayer] = array[iPlayer];
        array[iPlayer] = tmp;
    }

    int[] getArray() {
        return array;
    }

    void draw(Canvas canvas) {
        //canvas.drawPaint(tmpCell);

        tmpCell.setStyle(Paint.Style.FILL);
        tmpCell.setTextSize(12f);
        for(int i = 0; i < numsDraw.length; i++) {
            if(checked && !posChecks.isEmpty()) {
                if(i == posChecks.peek()) tmpCell.setColor(Color.GREEN);
                else tmpCell.setColor(Color.RED);
                posChecks.poll();
            } else tmpCell.setColor(Color.BLACK);
            canvas.drawText(numsDraw[i],0.0f+(16*i),0.0f, tmpCell);
        }
        pm.draw(canvas);

        tmpCell.setColor(Color.BLACK);
        canvas.drawText("j", 6.0f + (16 * jPlayer), 18.0f, tmpCell);
        canvas.drawText("i", 6.0f + (16 * iPlayer), 18.0f, tmpCell);

        tmpCell.setStyle(Paint.Style.STROKE);
        tmpCell.setStrokeWidth(1f);

        canvas.drawArc(new RectF(-2.0f+(16*iPlayer), 6.0f,0.0f+(16*(iPlayer+1))
                ,25.0f),225f,90f,false,tmpCell);
        canvas.drawArc(new RectF(-2.0f+(16*jPlayer), 6.0f,0.0f+(16*(jPlayer+1))
                ,25.0f),225f,90f,false,tmpCell);
        pm.draw(canvas);
    }

    LinearLayout PutPanel(InteractiveSorting iSActivity) {

        LinearLayout llPanelInteractive = new LinearLayout(iSActivity);
        llPanelInteractive.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        llPanelInteractive.setOrientation(LinearLayout.HORIZONTAL);
        llPanelInteractive.setGravity(Gravity.LEFT);

        btnResetFase = new Button(iSActivity);
        btnResetFase.setText("Reset fase");
        btnResetFase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPlayer = 0;
                jPlayer = 1;
                checked = false;
                for(int i = 0; i < array.length; i++) {
                    array[i] = status[i];
                    pm.add(new Polygon(new point(7f+(16*i), -4f), 8f, 4));
                    if(array[i] <= 9) numsDraw[i] = (" "+String.valueOf(array[i]));
                    else numsDraw[i] = String.valueOf(array[i]);
                }
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
                jPlayer = 1;
                iPlayer = 0;
                checked = false;
                for(int i = 0; i < array.length; i++) {
                    array[i] = initialStatus[i];
                    status[i] = initialStatus[i];
                    newStatus[i] = initialStatus[i];
                    pm.add(new Polygon(new point(7f+(16*i), -4f), 8f, 4));
                    if(array[i] <= 9) numsDraw[i] = (" "+String.valueOf(array[i]));
                    else numsDraw[i] = String.valueOf(array[i]);
                }
                btnCheckArray.setVisibility(View.INVISIBLE);
                iSActivity.draw();
            }
        });
        llPanelInteractive.addView(btnResetExercise);

        Button btnSwitchElement = new Button(iSActivity);
        btnSwitchElement.setText("i <> j");
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

        Button btnMoveCursorI = new Button(iSActivity);
        btnMoveCursorI.setText("I >>");
        btnMoveCursorI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iPlayer < array.length-jIinitalPos) {
                    iPlayer++;
                }
                if(iPlayer == array.length-jIinitalPos) {
                    btnCheckFase.setVisibility(View.VISIBLE);
                }
                iSActivity.draw();
            }
        });
        llPanelInteractive.addView(btnMoveCursorI);

        Button btnMoveCursorJ = new Button(iSActivity);
        btnMoveCursorJ.setText("J >>");
        btnMoveCursorJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jPlayer < array.length) {
                    jPlayer++;
                }
                if(jPlayer == array.length) {
                    btnCheckFase.setVisibility(View.VISIBLE);
                }
                iSActivity.draw();
            }
        });
        llPanelInteractive.addView(btnMoveCursorJ);

        btnCheckFase = new Button(iSActivity);
        btnCheckFase.setText("Merge");
        btnCheckFase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posChecks.size() < array.length || mergeSortFase()) {
                    if(checkSorted()) {
                        Toast toast = Toast.makeText(iSActivity, "Congratulations!", Toast.LENGTH_SHORT);
                        toast.show();
                        btnResetFase.setVisibility(View.INVISIBLE);
                    } else {
                        Toast toast = Toast.makeText(iSActivity, "Well done!", Toast.LENGTH_SHORT);
                        toast.show();
                        if(jPlayer < array.length) {
                            int diff = jPlayer-iPlayer;
                            iPlayer+=lengthPlay;
                            jPlayer+=lengthPlay;
                        } else {
                            iPlayer = 0;
                            jPlayer = 0 + jIinitalPos + lengthPlay/2;
                            lengthPlay *= 2;
                            if(array.length < lengthPlay) lengthPlay = array.length;
                        }
                        if(iPlayer >= array.length) {
                            iPlayer = 0;
                            jPlayer = 0 + jIinitalPos + lengthPlay/2;
                            lengthPlay *= 2;
                            if(array.length < lengthPlay) lengthPlay = array.length;
                        }
                        btnResetFase.setVisibility(View.INVISIBLE);
                    }
                }  else {
                    Toast toast = Toast.makeText(iSActivity, "A few errors have been found, please try again!", Toast.LENGTH_LONG);
                    toast.show();
                    btnResetFase.setVisibility(View.VISIBLE);
                }
                iSActivity.draw();
            }
        });
        llPanelInteractive.addView(btnCheckFase);

        btnCheckArray = new Button(iSActivity);
        btnCheckArray.setText("Check status");
        btnCheckArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean correct = true;
                for(int i = 0; i < array.length-1 && correct; i++) {
                    correct = (array[i] <= array[i+1]);
                }
                if(correct) {
                    Toast toast = Toast.makeText(iSActivity, "Congratulations!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                iSActivity.draw();
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
        txtTitle.setText("Merge Sort");
        txtTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        llPanelLesson.addView(txtTitle);

        TextView txtPropierties = new TextView(lessonTutorial);
        txtPropierties.setTextSize(16f);
        txtPropierties.setText("\nPropierties:\n - Space cost: Θ(n)\n - Temporary cost: Θ(n·log(n))\n - Method: Merging\n - Stable: Yes");
        txtPropierties.setPadding(20,0,0,20);
        llPanelLesson.addView(txtPropierties);

        TextView txtIntroduccion = new TextView(lessonTutorial);
        txtIntroduccion.setText("This algorithm is one of the most efficient, stable and easy to implement. It consists of 2 phases: split and merge\n");
        txtIntroduccion.setPadding(20,0,0,20);
        llPanelLesson.addView(txtIntroduccion);

        TextView txtParrafo1 = new TextView(lessonTutorial);
        txtParrafo1.setText("First phase (Division): We will start by marking the size of the entire vector and divide it by halves of that size until it is reduced to 2 elements. Thus creating 2 subsets of 1 elements\n");
        txtParrafo1.setPadding(20,0,0,20);
        llPanelLesson.addView(txtParrafo1);

        TextView txtParrafo2 = new TextView(lessonTutorial);
        txtParrafo2.setText("Second phase (Fusion): Once divided, we will verify that each element of the 2 subsets is greater than the other. Once the comparison is completed and if the exchange of elements is required, the size of the subsets will be doubled. This process will be repeated until the subset has the same size as the original set.\n");
        txtParrafo2.setPadding(20,0,0,20);
        llPanelLesson.addView(txtParrafo2);

        TextView txtParrafo3 = new TextView(lessonTutorial);
        txtParrafo3.setText("Note: Merge sort and Shell sort are almost the same, what differs is that the shell does not make divisions on the size of the array, but on the difference in indexes.\n");
        txtParrafo3.setPadding(20,0,0,20);
        llPanelLesson.addView(txtParrafo3);

        return llPanelLesson;
    }

}
