package Game;

import Game.game.Contoroler.MokhtasatPoint;

import java.awt.geom.Point2D;

import static java.lang.Double.NaN;

public abstract class helper {
    public static int[] changerD (double[] arr) {
        int[] result = new int[arr.length];
        int i = 0;
        for (double a : arr) {
            result[i] = (int) (a);
            i++;
        }
        return result;
    }

    public static Point2D.Double multiplyVector (Point2D point, double scalar) {
        return new Point2D.Double (point.getX () * scalar, point.getY () * scalar);
    }

    public static Point2D.Double addVectors (Point2D point1, Point2D point2) {
        return new Point2D.Double (point1.getX () + point2.getX (), point1.getY () + point2.getY ());
    }

    public static MokhtasatPoint addVectors (double[] x, double[] y, Point2D.Double center, Point2D.Double vector, double speed) {
        double[] newX = new double[x.length];
        double[] newY = new double[x.length];
        for (int i = 0 ; i < x.length ; i++) {
            newX[i] = x[i] + (vector.x * speed);
            newY[i] = y[i] + (vector.y * speed);
        }
        return new MokhtasatPoint (newX, newY, addVectors (center, multiplyVector (vector, speed)));
    }

    public static boolean checkNan (double[] xPOint, double[] yPoint) {
        for (int i = 0 ; i < yPoint.length ; i++) {
            if (xPOint[i] != xPOint[i] || yPoint[i] != yPoint[i]) {
                return false;
            }
        }
        return true;
    }
}
