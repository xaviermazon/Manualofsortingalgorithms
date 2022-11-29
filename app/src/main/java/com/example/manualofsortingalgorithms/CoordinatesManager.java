package com.example.manualofsortingalgorithms;

public class CoordinatesManager {

    int size;
    // Camera location and orientation.
    point O, OX, OY;
    int numfingers = 0;
    PolygonManager pm;
    point worldfinger;
    point worldfinger1;
    point worldfinger2;

    void setCamera(point O, point OX) {
        this.O = O;
        this.OX = OX;
        OY = new point(-OX.y, OX.x);
    }

    point veccanonic2vecscreen(point v) {
        return new point(v.x * size / 2.0, -v.y * size / 2.0);
    }

    point vecscreen2veccanonic(point v) {
        return new point(v.x / (size / 2.0), -v.y / (size / 2.0));
    }

    point canonic2screen(point p) {
        return veccanonic2vecscreen(point.sum(p, new point(1, -1)));
    }

    point screen2canonic(point p) {
        return point.sub(vecscreen2veccanonic(p), new point(1, -1));
    }

    point vecworld2veccamera(point v) {
        return new point(
                point.dotProd(v, OX) / point.norm(OX),
                point.dotProd(v, OY) / point.norm(OY)
        );
    }

    point veccamera2vecworld(point v) {
        return point.sum(
                point.prod(OX, v.x),
                point.prod(OY, v.y)
        );
    }                                        //pointToReferences

    point world2camera(point p) {
        return vecworld2veccamera(point.sub(p, O));
    }

    point camera2world(point p) {
        return point.sum(veccamera2vecworld(p), O);
    }

    point vecworld2vecscreen(point p) {
        return veccanonic2vecscreen(vecworld2veccamera(p));
    }

    point vecscreen2vecworld(point p) {
        return veccamera2vecworld(vecscreen2veccanonic(p));
    }

    point world2screen(point p) {
        return canonic2screen(world2camera(p));
    }

    point screen2world(point p) {
        return camera2world(screen2canonic(p));
    }

    void touch() {
        numfingers = 0;
    }

    void touch(point screenfinger) {
        point newworldfinger = screen2world(screenfinger);
        if (numfingers != 1) {
            numfingers = 1;
            worldfinger = newworldfinger;
            pm.select(worldfinger);
        } else {
            if (pm.thereIsSelected()) {
                pm.getSelected().add(point.sub(newworldfinger, worldfinger));
                worldfinger = newworldfinger;
            } else {
                O = point.sum(O, point.sub(worldfinger, newworldfinger));
            }
        }
    }

    void touch(point screenfinger1, point screenfinger2) {
        point newworldfinger1 = screen2world(screenfinger1);
        point newworldfinger2 = screen2world(screenfinger2);
        if (numfingers != 2) {
            numfingers = 2;
            worldfinger1 = newworldfinger1;
            worldfinger2 = newworldfinger2;
            pm.select(worldfinger1);
            if (!pm.thereIsSelected())
                pm.select(worldfinger2);
        } else {

            point o1 = worldfinger1;
            point ox1 = point.sub(worldfinger2, worldfinger1);
            point oy1 = point.orthogonal(ox1);
            point o2 = newworldfinger1;
            point ox2 = point.sub(newworldfinger2, newworldfinger1);
            point oy2 = point.orthogonal(ox2);
            if (pm.thereIsSelected()) {
                pm.getSelected().moveBetweenRefernces(o1, ox1, oy1, o2, ox2, oy2);
                worldfinger1 = newworldfinger1;
                worldfinger2 = newworldfinger2;
            } else {
                point newO = point.pointFromReferences
                        (point.pointToReferences(O, o2, ox2, oy2), o1, ox1, oy1);
                point newOX = point.vecFromReference(point.vecToReference(OX, ox2, oy2), ox1, oy1);
                point newOY = point.orthogonal(newOX);
                O = newO;
                OX = newOX;
            }
        }
    }
}

