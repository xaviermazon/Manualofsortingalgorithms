package com.example.manualofsortingalgorithms;

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
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InteractiveSorting extends AppCompatActivity {

    LinearLayout linlay;
    int[] array = new int[] {11, 12, 13, 14, 15, 16, 17, 18, 19, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int sort;
    long curmilliseconds;
    String bufArray = "";
    TextView txtArraySorted;
    Configuration config;
    ImageView imageView;
    Bitmap bitmap;
    Canvas canvas;
    CoordinatesManager cm;
    PolygonManager pm;
    BubbleSort bs;
    MergeSort ms;
    point pCamera = new point(24, 0);
    Handler handler;
    Runnable runnable;

    void draw() {
        canvas.drawColor(Color.YELLOW);
        Matrix matrix = new Matrix();
        point o = cm.world2screen(new point(0, 0));
        point ox = cm.vecworld2vecscreen(new point(1, 0));
        point oy = cm.vecworld2vecscreen(new point(0, 1));
        matrix.setValues(new float[]{
                (float) ox.x, (float) oy.x, (float) o.x,
                (float) ox.y, (float) oy.y, (float) o.y,
                0, 0, 1});
        canvas.save();
        canvas.setMatrix(matrix);
        switch(sort) {
            case 1: bs.draw(canvas);
                break;
            case 3: ms.draw(canvas);
                break;
            default:
                Toast.makeText(this, "the lesson you have chosen is inactive", Toast.LENGTH_LONG);
        }
        canvas.restore();
        imageView.invalidate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        sort = intent.getIntExtra("sort", 0);
        int level = intent.getIntExtra("level", 0);
        boolean improved = intent.getBooleanExtra("improved",false);

        switch(sort) {
            case 1: bs = new BubbleSort(level);
                break;
            case 3: ms = new MergeSort(level);
                break;
            default:
                Toast.makeText(this, "the lesson you have chosen is inactive", Toast.LENGTH_LONG);
        }
        config = this.getResources().getConfiguration();
        linlay = new LinearLayout(this);
        linlay.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        this.getSupportActionBar().hide();
        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            config.orientation = Configuration.ORIENTATION_LANDSCAPE;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        linlay.setOrientation(LinearLayout.VERTICAL);
        linlay.setGravity(Gravity.CENTER_HORIZONTAL);

        LinearLayout llPanelCam = new LinearLayout(this);
        llPanelCam.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        llPanelCam.setOrientation(LinearLayout.HORIZONTAL);
        llPanelCam.setGravity(Gravity.CENTER_HORIZONTAL);

        Button btnPlaceholder = new Button(this);
        btnPlaceholder.setText("Reset Camera");
        btnPlaceholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cm.setCamera(new point(0, 0), pCamera);
                draw();
            }
        });
        llPanelCam.addView(btnPlaceholder);

        linlay.addView(llPanelCam);

        imageView = new ImageView(this);
        linlay.addView(imageView);
        Point p = new Point();
        getWindowManager().getDefaultDisplay().getSize(p);
        int size = (int) (p.y * 0.6);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(p.x, size));
        bitmap = Bitmap.createBitmap(p.x, size, Bitmap.Config.ARGB_8888);
        imageView.setImageBitmap(bitmap);
        canvas = new Canvas();
        canvas.setBitmap(bitmap);
        canvas.drawColor(Color.LTGRAY);

        cm = new CoordinatesManager();
        cm.size = size;
        cm.setCamera(new point(0, 0), pCamera);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_UP) ||
                        (event.getPointerCount() != 1 &&
                                event.getPointerCount() != 2)) {
                    cm.touch();
                } else if (event.getPointerCount() == 1) {
                    cm.touch(new point(event.getX(), event.getY()));
                } else if (event.getPointerCount() == 2) {
                    cm.touch(new point(event.getX(0), event.getY(0)),
                            new point(event.getX(1), event.getY(1)));
                }
                draw();
                return true;
            }
        });

        draw();
        curmilliseconds = System.currentTimeMillis();
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                long nextCurMilliSeconds = System.currentTimeMillis();
                double delta = (nextCurMilliSeconds-curmilliseconds)/1000.0;
                curmilliseconds = nextCurMilliSeconds;
                draw();
                //si la bola te sigue el dedo
                handler.postDelayed(runnable,10);
            }
        };

        LinearLayout llPanelInteractive = null;

        switch(sort) {
            case 1: llPanelInteractive = bs.PutPanel(this, improved);
                    break;
            case 3: llPanelInteractive = ms.PutPanel(this);
                    break;
            default:
                    Toast.makeText(this, "the lesson you have chosen is inactive", Toast.LENGTH_LONG);
        }

        linlay.addView(llPanelInteractive);

        setContentView(linlay);
    }
}
