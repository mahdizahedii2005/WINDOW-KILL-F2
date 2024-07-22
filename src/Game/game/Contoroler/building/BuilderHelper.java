package Game.game.Contoroler.building;

import Game.game.Contoroler.control.Controller;
import Game.game.Contoroler.control.DefaultMethods;
import Game.game.model.characterModel.Enemy.*;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.characterModel.Panels.isometricPanel;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.model.collision.Collidable;
import Game.helper;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import static Game.Data.constants.*;
import static Game.game.model.characterModel.Panels.PanelInGame.PANELS;

public class BuilderHelper {
    public static void trigorathBuilder() {
        PanelInGame panelInGameView = PANELS.getFirst();
        trigorathBuilder(new Point2D.Double(panelInGameView.getX(), panelInGameView.getY()), new Point2D.Double(panelInGameView.getX() + SIDE_OF_TRIANGLE, panelInGameView.getY()));
    }

    public static void squarantineBuilder() {
        PanelInGame panelInGameView = PANELS.getFirst();
        squarantineBuilder(new Point2D.Double(panelInGameView.getX(), panelInGameView.getY()), new Point2D.Double(panelInGameView.getX() + SIDE_OF_TRIANGLE, panelInGameView.getY()));

    }

    public static void ArchmireBuilder(Point2D.Double point, boolean isBaby) {
        isBaby = !isBaby;
        double[] x = new double[]{isBaby ? -51 : -51 / 2d, isBaby ? -39 : -39 / 2d, 0, isBaby ? 35 : 35 / 2d, isBaby ? 51 : 51 / 2d, isBaby ? 35 : 35 / 2d,
                0, isBaby ? -41 : -41 / 2d};
        double[] y = new double[]{0, isBaby ? -29 : -29 / 2d, isBaby ? -44 : -22, isBaby ? -33 : -33 / 2d, 0, isBaby ? 34 : 34 / 2d, isBaby ? 44 : 22,
                isBaby ? 26 : 13};
        new Archmire(point, helper.addVectors(point.x, x), helper.addVectors(point.y, y), !isBaby);

    }

    public static void trigorathBuilder(Point2D.Double p1, Point2D.Double p2) {
        double shib = (p2.y - p1.y) / (p2.x - p1.x);
        if (Math.sqrt(p1.distance(p2)) != SIDE_OF_TRIANGLE) {
            double arzAzMabda = p1.y - ((p1.x * shib));
            double D = arzAzMabda - p1.y;
            double a = (shib * shib) + 1;
            double b = (shib * D) - p1.x;
            double c = (p1.x * p1.x) + (D * D) - (SIDE_OF_TRIANGLE * SIDE_OF_TRIANGLE);
            double X1 = (-b + Math.sqrt((b * b) - a * c)) / a;
            double X2 = (-b - Math.sqrt((b * b) - a * c)) / a;
            if (X2 > X1) {
                X1 = X2;
            }
            double Y1 = (shib * X1) + arzAzMabda;
            p2 = new Point2D.Double(X1, Y1);
        }
        Point2D.Double vastKhat = new Point2D.Double((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
        if (shib == 0) {
            shib = 0.001;
        }
        double shibAmood = -1 / shib;
        double arzAzMabdaAmod = vastKhat.y - (shibAmood * vastKhat.x);
        double D = (arzAzMabdaAmod - vastKhat.y);
        double a = (shibAmood * shibAmood) + 1;
        double b = (shibAmood * D) - (vastKhat.x);
        double c = (D * D) + (vastKhat.x * vastKhat.x) - (HEIGHT_OF_TRIANGLE * HEIGHT_OF_TRIANGLE);
        double X1 = (-b + Math.sqrt((b * b) - a * c)) / a;
        double X2 = (-b - Math.sqrt((b * b) - a * c)) / a;
        if (X2 > X1) {
            X1 = X2;
        }
        double Y1 = (X1 * shibAmood) + arzAzMabdaAmod;
        Point2D.Double p3 = new Point2D.Double(X1, Y1);
        Point2D.Double center = new Point2D.Double((((2d / 3) * vastKhat.x) + ((1d / 3) * X1)), (((2d / 3) * vastKhat.y) + ((1d / 3) * Y1)));
        double[] XPoint = {p1.x, p2.x, p3.x};
        double[] YPoint = {p1.y, p2.y, p3.y};
        new TrigorathModel(center, TRIANGLE_COLOR, XPoint, YPoint);
    }

    public static void squarantineBuilder(Point2D.Double p1, Point2D.Double p2) {
        double shib = (p2.y - p1.y) / (p2.x - p1.x);
        if (Math.sqrt(p1.distance(p2)) != SIDE_OF_SQUARE) {
            double arzAzMabda = p1.y - ((p1.x * shib));
            double D = arzAzMabda - p1.y;
            double a = (shib * shib) + 1;
            double b = (shib * D) - p1.x;
            double c = (p1.x * p1.x) + (D * D) - (SIDE_OF_SQUARE * SIDE_OF_SQUARE);
            double X1 = (-b + Math.sqrt((b * b) - a * c)) / a;
            double X2 = (-b - Math.sqrt((b * b) - a * c)) / a;
            if (X2 > X1) {
                X1 = X2;
            }
            double Y1 = (shib * X1) + arzAzMabda;
            p2 = new Point2D.Double(X1, Y1);
        }
        if (shib == 0) {
            shib = 0.001;
        }
        double shibAmood = -1 / shib;
        double arzAzMabdaAmod = p1.y - (shibAmood * p1.x);
        double D = (arzAzMabdaAmod - p1.y);
        double a = (shibAmood * shibAmood) + 1;
        double b = (shibAmood * D) - (p1.x);
        double c = (D * D) + (p1.x * p1.x) - (SIDE_OF_SQUARE * SIDE_OF_SQUARE);
        double X1 = (-b + Math.sqrt((b * b) - a * c)) / a;
        double X2 = (-b - Math.sqrt((b * b) - a * c)) / a;
        if (X2 > X1) {
            X1 = X2;
        }
        double Y1 = (X1 * shibAmood) + arzAzMabdaAmod;
        Point2D.Double p3 = new Point2D.Double(X1, Y1);
        arzAzMabdaAmod = p2.y - (shibAmood * p2.x);
        D = (arzAzMabdaAmod - p2.y);
        a = (shibAmood * shibAmood) + 1;
        b = (shibAmood * D) - (p2.x);
        c = (D * D) + (p2.x * p2.x) - (SIDE_OF_SQUARE * SIDE_OF_SQUARE);
        X1 = (-b + Math.sqrt((b * b) - a * c)) / a;
        X2 = (-b - Math.sqrt((b * b) - a * c)) / a;
        if (X2 > X1) {
            X1 = X2;
        }
        Y1 = (X1 * shibAmood) + arzAzMabdaAmod;
        Point2D.Double p4 = new Point2D.Double(X1, Y1);
        double[] XPoint = {p1.x, p3.x, p4.x, p2.x};
        double[] YPoint = {p1.y, p3.y, p4.y, p2.y};
        new SquarantineModel(getCenter(XPoint, YPoint), XPoint, YPoint);
    }

    public static void OmenoctBuilder(Point2D.Double center, double radius, Collidable.side side) {
        double[] xPoint = new double[8];
        double[] yPoint = new double[8];
        for (int i = 0; i < 8; i++) {
            int dig = 23 + (45 * i);
            xPoint[i] = center.getX() + (radius * DefaultMethods.cosTable[dig]);
            yPoint[i] = center.getY() + (radius * DefaultMethods.sinTable[dig]);
        }
        new Omenoct(center, 8, xPoint, yPoint, side);
    }

    public static void NecropickBuilder(Point2D.Double ceneterOfPhoto) {
        Point2D.Double p1 = new Point2D.Double(ceneterOfPhoto.getX() + 25, ceneterOfPhoto.getY());
        Point2D.Double p2 = new Point2D.Double(ceneterOfPhoto.getX() + 50, ceneterOfPhoto.getY());
        Point2D.Double p3 = new Point2D.Double(ceneterOfPhoto.getX() + 83, ceneterOfPhoto.getY() + 35);
        Point2D.Double p4 = new Point2D.Double(ceneterOfPhoto.getX() + 70, ceneterOfPhoto.getY() + 45);
        Point2D.Double p5 = new Point2D.Double(ceneterOfPhoto.getX() + 50, ceneterOfPhoto.getY() + 35);
        Point2D.Double p6 = new Point2D.Double(ceneterOfPhoto.getX() + 47, ceneterOfPhoto.getY() + 100);
        Point2D.Double p7 = new Point2D.Double(ceneterOfPhoto.getX() + 31, ceneterOfPhoto.getY() + 100);
        Point2D.Double p8 = new Point2D.Double(ceneterOfPhoto.getX() + 30, ceneterOfPhoto.getY() + 35);
        Point2D.Double p9 = new Point2D.Double(ceneterOfPhoto.getX() + 9, ceneterOfPhoto.getY() + 45);
        Point2D.Double p10 = new Point2D.Double(ceneterOfPhoto.getX(), ceneterOfPhoto.getY() + 35);
        Point2D.Double realCenter = new Point2D.Double(ceneterOfPhoto.getX() + 40, ceneterOfPhoto.getY() + 30);
        new Necropick(ceneterOfPhoto, realCenter, 10, new double[]{p10.getX(), p9.getX(), p8.getX(), p7.getX(), p6.getX(), p5.getX(), p4.getX(), p3.getX(), p2.getX(), p1.getX()}, new double[]{p10.getY(), p9.getY(), p8.getY(), p7.getY(), p6.getY(), p5.getY(), p4.getY(), p3.getY(), p2.getY(), p1.getY()});
    }

    public static void BarricadosBuilder(Point2D.Double center) {
        ArrayList<Point2D> ver = new ArrayList<>();
        ver.add(new Point2D.Double(center.getX() - 90, center.getY() - 90));
        ver.add(new Point2D.Double(center.getX() + 90, center.getY() - 90));
        ver.add(new Point2D.Double(center.getX() + 90, center.getY() + 90));
        ver.add(new Point2D.Double(center.getX() - 90, center.getY() + 90));
        new Barricados(center, ver);
    }

    public static void wyrmBuilder(Point2D.Double center) {
        Point2D.Double photoCenter = new Point2D.Double(center.getX() - 40, center.getY() - 32);
        ArrayList<Point2D> arrayList = new ArrayList<>();
        arrayList.add(new Point2D.Double(photoCenter.getX(), photoCenter.getY() + 16));
        arrayList.add(new Point2D.Double(photoCenter.getX() + 24, photoCenter.getY()));
        arrayList.add(new Point2D.Double(photoCenter.getX() + 52, photoCenter.getY() + 1));
        arrayList.add(new Point2D.Double(photoCenter.getX() + 68, photoCenter.getY() + 20));
        arrayList.add(new Point2D.Double(photoCenter.getX() + 75, photoCenter.getY() + 44));
        arrayList.add(new Point2D.Double(photoCenter.getX() + 59, photoCenter.getY() + 55));
        arrayList.add(new Point2D.Double(photoCenter.getX() + 25, photoCenter.getY() + 61));
        arrayList.add(new Point2D.Double(photoCenter.getX() + 7, photoCenter.getY() + 41));
        new Wyrm(center, arrayList, Epsilon.getEpsilon());
    }

    private static Point2D.Double getCenter(double[] Xpoint, double[] Ypoint) {
//        ArrayList<Double> Y = new ArrayList<>();
//        ArrayList<Double> X = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            X.add(Xpoint[0] - Xpoint[i + 1]);
//            Y.add(Ypoint[0] - Ypoint[i + 1]);
//        }
//        if (X.get(0).equals(X.get(1))) {
//            if (Y.get(0).equals(Y.get(1))) {
//                return new Point2D.Double(X.get(0), Y.get(0));
//            }
//            return new Point2D.Double(X.get(0), Y.get(2));
//        } else {
//            if (Y.get(0).equals(Y.get(1))) {
//                return new Point2D.Double(X.get(2), Y.get(0));
//            }
//            return new Point2D.Double(X.get(2), Y.get(2));
//        }
        return new Point2D.Double((Xpoint[0] + Xpoint[3]) / 2d, (Ypoint[0] + Ypoint[3]) / 2d);
    }

    public static void startBlackOrb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Point2D.Double epsilonCenter = Epsilon.getEpsilon().getCenter();
                blackOrbs blackOrbs = new blackOrbs();
                ArrayList<isometricPanel> panels = new ArrayList<>();
                for (int i = 1; i < 6; i++) {
                    isometricPanel isometricPanel = null;
                    switch (i) {
                        case 1 ->
                                isometricPanel = new isometricPanel(epsilonCenter.getX() - 239 - 35, epsilonCenter.getY() - 111 - 35, 70, 70, false, 0);
                        case 2 ->
                                isometricPanel = new isometricPanel(epsilonCenter.getX() - 35, epsilonCenter.getY() - 251 - 35, 70, 70, false, 0);
                        case 3 ->
                                isometricPanel = new isometricPanel(epsilonCenter.getX() + 275 - 35, epsilonCenter.getY() - 126 - 35, 70, 70, false, 0);
                        case 4 ->
                                isometricPanel = new isometricPanel(epsilonCenter.getX() + 228 - 35, epsilonCenter.getY() + 208 - 35, 70, 70, false, 0);
                        case 5 ->
                                isometricPanel = new isometricPanel(epsilonCenter.getX() - 202 - 35, epsilonCenter.getY() + 226 - 35, 70, 70, false, 0);
                    }
                    panels.add(isometricPanel);
                    try {
                        Thread.sleep(1111);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                for (int i = 1; i < 6; i++) {
                    blackOrbs.creatBlackOrb(new Point2D.Double(panels.get(i-1).getX() + 35, panels.get(i-1).getY() + 35), i, panels.get(i-1));
                }
                try {
                    blackOrbs.start();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("finish");
            }
        }).start();
    }
}
