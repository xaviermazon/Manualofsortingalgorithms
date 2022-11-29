package com.example.manualofsortingalgorithms;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    LinearLayout linlay;
    int[] array = new int[]{11, 12, 13, 14, 15, 16, 17, 18, 19, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    String bufArray = "";
    TextView txtArraySorted;
    Configuration config;
    ImageView imageView;
    Bitmap bitmap;
    Canvas canvas;
    CoordinatesManager cm;
    PolygonManager pm;

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
        pm.draw(canvas);
        canvas.restore();
        imageView.invalidate();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        config = this.getResources().getConfiguration();

        linlay = new LinearLayout(this);
        linlay.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            config.orientation = Configuration.ORIENTATION_LANDSCAPE;
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        linlay.setOrientation(LinearLayout.VERTICAL);
        linlay.setGravity(Gravity.CENTER_HORIZONTAL);
        imageView = new ImageView(this);
        linlay.addView(imageView);
        Point p = new Point();
        getWindowManager().getDefaultDisplay().getSize(p);
        int size = (int) (p.y * 0.6);
        Log.e("Dimensions", "onCreate: X:" + p.x + " p.y:" + p.y);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(p.x, size));
        bitmap = Bitmap.createBitmap(p.x, size, Bitmap.Config.ARGB_8888);
        imageView.setImageBitmap(bitmap);
        canvas = new Canvas();
        canvas.setBitmap(bitmap);
        canvas.drawColor(Color.LTGRAY);

        pm = new PolygonManager();
        pm.add(new Polygon(new point(0, 0), 0.3, 4));

        cm = new CoordinatesManager();
        cm.pm = pm;
        cm.size = size;
        cm.setCamera(new point(0, 0), new point(1, 0));
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

        LinearLayout llPanel = new LinearLayout(this);
        llPanel.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        llPanel.setOrientation(LinearLayout.HORIZONTAL);
        llPanel.setGravity(Gravity.CENTER_HORIZONTAL);

        Button btnPlaceholder = new Button(this);
        btnPlaceholder.setText("btnPlaceholder");
        llPanel.addView(btnPlaceholder);

        linlay.addView(llPanel);

        setContentView(linlay);
    }
}