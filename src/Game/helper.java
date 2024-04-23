package Game;

import Game.game.Contoroler.MokhtasatPoint;

import java.awt.geom.Point2D;


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

    public static Point2D.Double addVectors (Point2D.Double point1, Point2D.Double point2) {
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

    public static Point2D weightedAddVectors (Point2D point1, Point2D point2, double weight1, double weight2) {
        return multiplyVector (addVectors (multiplyVector (point1, weight1), multiplyVector (point2, weight2)), 1 / (weight1 + weight2));
    }

    public static Point2D.Double relativeLocation (Point2D.Double point, Point2D.Double anchor) {
        return new Point2D.Double (point.getX () - anchor.getX (), point.getY () - anchor.getY ());
    }

    public static Point2D.Double findCollisionPlace (Point2D centerO, Point2D mabda, Point2D maghsad, double R) {
        double a = ((double) mabda.getY () - maghsad.getY ()) / (mabda.getX () - maghsad.getX ());
        double h = mabda.getY () - a * mabda.getX ();
        double D = h - centerO.getY ();
        double A = (a * a) + 1;
        double B = (D * a) - centerO.getX ();
        double C = (centerO.getX () * centerO.getX ()) + (D * D) - (R * R);
        double X1 = (-B + Math.sqrt (B * B - A * C)) / A;
        double X2 = (-B - Math.sqrt (B * B - A * C)) / A;
        double Y1 = a * X1 + h;
        double Y2 = a * X2 + h;
        if (X1 != X1) {
            return null;
        }
        if ((mabda.getX () - X1) * (maghsad.getX () - X1) < 0) {
            return new Point2D.Double (X1, Y1);
        }
        return new Point2D.Double (X2, Y2);
    }
}
