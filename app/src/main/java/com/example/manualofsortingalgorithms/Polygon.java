package com.example.manualofsortingalgorithms;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.Random;

public class Polygon {
    point[] v;
    point c;
    double r;
    Paint boardColor;

    Polygon(point center, double radius, int numvertices) {
        c = center;
        r = radius;
        v = new point[numvertices];
        for (int i = 0; i < v.length; i++) {
            v[i] = point.sum(center, point.polar(radius, i * 2 * Math.PI / numvertices));
        }
        boardColor = new Paint();
        switch (new Random().nextInt() % 10 & 0x7fff) {
            case 1:
                boardColor.setColor(Color.GREEN);
                break;
            case 2:
                boardColor.setColor(Color.BLACK);
                break;
            case 3:
                boardColor.setColor(Color.WHITE);
                break;
            case 4:
                boardColor.setColor(Color.BLUE);
                break;
            case 5:
                boardColor.setColor(Color.MAGENTA);
                break;
            case 6:
                boardColor.setColor(Color.CYAN);
                break;
            case 7:
                boardColor.setColor(Color.GRAY);
                break;
            case 8:
                boardColor.setColor(Color.LTGRAY);
                break;
            case 9:
                boardColor.setColor(Color.DKGRAY);
                break;
            default:
                boardColor.setColor(Color.RED);
                break;
        }
        boardColor.setStyle(Paint.Style.FILL);
    }

    void draw(Canvas canvas, boolean isSelected) {
        Path path = new Path();
        //point p = cm.world2screen(v[0]);
        point p = v[0];
        path.moveTo((float) p.x, (float) p.y);
        for (int i = 1; i < v.length; i++) {
            //p = cm.world2screen(v[i]);
            p = v[i];
            path.lineTo((float) p.x, (float) p.y);
        }
        path.close();
        if (isSelected) {
            boardColor.setMaskFilter(new BlurMaskFilter(1f, BlurMaskFilter.Blur.SOLID));
        } else {
            boardColor.setMaskFilter(null);
        }
        canvas.drawPath(path, boardColor);
        Paint tmp = new Paint();
        tmp.setColor(Color.BLACK);
        tmp.setStrokeWidth(0.02f);
        canvas.drawLine(0, 1, 0, -1, tmp);
        canvas.drawLine(1, 0, -1, 0, tmp);
        tmp.setStyle(Paint.Style.STROKE);
        canvas.drawRect((float) (c.x-r), (float) (c.y+r), (float) (c.x+r), (float) (c.y-r),tmp);
    }

    boolean inside(point p) {
        for (int i = 0; i < v.length; i++)
            if (point.crossProd(
                    point.sub(v[(i + 1) % v.length], v[i]),
                    point.sub(p, v[i])) <= 0)
                return false;
        return true;
    }

    void add(point p) {
        for (int i = 0; i < v.length; i++) v[i] = point.sum(v[i], p);
    }

    void moveBetweenRefernces(point o1, point ox1, point oy1,
                              point o2, point ox2, point oy2) {
        for (int i = 0; i < v.length; i++)
            v[i] = point.moveBetweenRefernces(v[i], o1, ox1, oy1, o2, ox2, oy2);
    }

}
