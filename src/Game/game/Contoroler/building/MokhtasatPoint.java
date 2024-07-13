package Game.game.Contoroler.building;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class
MokhtasatPoint {

    Point2D.Double center;
    ArrayList<Point2D> arrayList;

    public MokhtasatPoint(ArrayList<Point2D> arrayList) {
        this.arrayList = arrayList;
    }

    public MokhtasatPoint(ArrayList<Point2D> arrayList, Point2D.Double center) {
        this.arrayList = arrayList;
        this.center = center;
    }

    public Point2D.Double getCenter() {
        return center;
    }

    public ArrayList<Point2D> getArrayList() {
        return arrayList;
    }
}
