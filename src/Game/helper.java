package Game;

import java.awt.geom.Point2D;

public abstract class helper {
    public static int[] changerD(double[] arr) {
        int[] result = new int[arr.length];
        int i = 0;
        for (double a : arr) {
            result[i] = (int) (a);
            i++;
        }
        return result;
    }
    public static Point2D.Double multiplyVector(Point2D point, double scalar){
        return new Point2D.Double(point.getX()*scalar,point.getY()*scalar);
    }
    public static Point2D.Double addVectors(Point2D point1,Point2D point2){
        return new Point2D.Double(point1.getX()+point2.getX(),point1.getY()+point2.getY());
    }
}
