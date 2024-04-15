package Game.game.Contoroler;

import java.awt.*;
import java.awt.geom.Point2D;

public class MokhtasatPoint {
    double[] xPoint;
    double[] yPoint;
    Point2D.Double center;

    public MokhtasatPoint(double[] xPoint, double[] yPoint) {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
    }

    public MokhtasatPoint(double[] xPoint, double[] yPoint, Point2D.Double center) {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
        this.center = center;
    }

    public Point2D.Double getCenter() {
        return center;
    }

    public double[] getxPoint() {
        return xPoint;
    }

    public double[] getyPoint() {
        return yPoint;
    }
}
