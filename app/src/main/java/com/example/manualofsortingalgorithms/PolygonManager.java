package com.example.manualofsortingalgorithms;

import android.graphics.Canvas;

import com.example.manualofsortingalgorithms.point;

import java.util.ArrayList;

public class PolygonManager {

    ArrayList<Polygon> lPolygons;
    int indexSelected = -1;

    PolygonManager() {
        lPolygons = new ArrayList<Polygon>();
    }
    void add(Polygon p) {
        lPolygons.add(p);
    }
    void draw(Canvas canvas) {
        for(int i = 0; i < lPolygons.size(); i++)
            lPolygons.get(i).draw(canvas);
    }
    void select(point p) {
       indexSelected = -1;
       for (int i = lPolygons.size()-1;i>=0;i--) {
           if(lPolygons.get(i).inside(p)){
               indexSelected = i;
               return;
           }
       }
    }
    boolean thereIsSelected() {
        return indexSelected != -1;
    }
    Polygon getSelected() {
        return lPolygons.get(indexSelected);
    }

}
