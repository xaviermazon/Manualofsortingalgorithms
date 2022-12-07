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
    }

    void draw(Canvas canvas) {
        Paint tmp = new Paint();
        tmp.setColor(Color.BLACK);
        tmp.setStrokeWidth(1f);
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

    //void moveBetweenRefernces(point o1, point ox1, point oy1,
    //                          point o2, point ox2, point oy2) {
    //    for (int i = 0; i < v.length; i++)
    //        v[i] = point.moveBetweenRefernces(v[i], o1, ox1, oy1, o2, ox2, oy2);
    //}

}
