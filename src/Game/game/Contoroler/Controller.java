package Game.game.Contoroler;

import Game.game.model.characterModel.Epsilon;
import Game.game.model.characterModel.ObjectInGame;
import Game.game.view.DrawAble;
import Game.game.view.characterView.*;
import Game.game.view.panelInGame;
import Game.helper;

import javax.swing.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import static Game.game.view.DrawAble.DRAW_ABLES;

public abstract class Controller {
    public static void addEpsilon (String id) {
        new epsilonView (id);
    }

    public static void addPrizeView (String id) {
        new PrizeView (id);
    }

    public static void addEnemy (String id) {
        new enemyView (id);
    }

    public static Line2D.Double getLeftPanel () {
        return new Line2D.Double (panelInGame.getPanel ().getX (), panelInGame.getPanel ().getY (), panelInGame.getPanel ().getX (), panelInGame.getPanel ().getY () + panelInGame.getPanel ().getHeight ());
    }

    public static Line2D.Double getUpPanel () {
        return new Line2D.Double (panelInGame.getPanel ().getX (), panelInGame.getPanel ().getY (), panelInGame.getPanel ().getX () + panelInGame.getPanel ().getWidth (), panelInGame.getPanel ().getY ());
    }

    public static Line2D.Double getRightPanel () {
        return new Line2D.Double (panelInGame.getPanel ().getX () + panelInGame.getPanel ().getWidth (), panelInGame.getPanel ().getY (), panelInGame.getPanel ().getX () + panelInGame.getPanel ().getWidth (), panelInGame.getPanel ().getY () + panelInGame.getPanel ().getHeight ());
    }

    public static Line2D.Double getDownPanel () {
        return new Line2D.Double (panelInGame.getPanel ().getX (), panelInGame.getPanel ().getY () + panelInGame.getPanel ().getHeight (), panelInGame.getPanel ().getX () + panelInGame.getPanel ().getWidth (), panelInGame.getPanel ().getY () + panelInGame.getPanel ().getHeight ());
    }

    public static ObjectInGame findObjectModel (String id) {
        for (ObjectInGame o : ObjectInGame.objectInGames) {
            if (o.getId ().equals (id)) {
                return o;
            }
        }
        return null;
    }

    public static DrawAbleObject findObjectView (String id) {
        for (DrawAble o : DRAW_ABLES) {
            DrawAbleObject l = (DrawAbleObject) o;
            if (l.getId ().equals (id)) {
                return l;
            }
        }
        return null;
    }

    public static JPanel whatPanelForDraw (double[] xPoint, double[] yPoint) {
        // TODO: ۱۰/۰۴/۲۰۲۴ for the next faze
        return panelInGame.getPanel ();
    }

    public static JPanel whatPanelForDraw (Point2D.Double Point) {
        // TODO: ۱۰/۰۴/۲۰۲۴ for the  next faze
        return panelInGame.getPanel ();
    }

    public static MokhtasatPoint fixThePoint (double[] xPoint, double[] yPoint, JPanel panel) {
        double[] resultX = new double[xPoint.length];
        double[] resultY = new double[xPoint.length];
        for (int i = 0 ; i < xPoint.length ; i++) {
            resultX[i] = xPoint[i] - panel.getX ();
            resultY[i] = yPoint[i] - panel.getY ();
        }
        return new MokhtasatPoint (resultX, resultY);
    }

    public static Point2D.Double fixThePoint (Point2D.Double point, JPanel panel) {
        return new Point2D.Double (point.getX () - panel.getX (), point.getY () - panel.getY ());
    }

    public static void moveEpsilon (double x, double y) {
        if (x == 0 && y == 0) {
            Epsilon.getEpsilon ().changDirection (x, y);
        } else if (x == 0 && Epsilon.getEpsilon () != null) {
            Epsilon.getEpsilon ().changDirection (y, false);
        } else if (y == 0) {
            Epsilon.getEpsilon ().changDirection (x, true);
        } else {
            Epsilon.getEpsilon ().changDirection (x, y);
        }
    }

    public static void moveEpsilon (boolean b) {
        if (b) {
            Epsilon.getEpsilon ().changDirection (0, b);
        } else {
            Epsilon.getEpsilon ().changDirection (0, b);
        }
    }

    public static void addBolt (String id) {
        new boltView (id);
    }

    public static void fire (Point2D.Double target) {
        Epsilon.getEpsilon ().fire (helper.addVectors (target, helper.multiplyVector (Epsilon.getEpsilon ().getCenter (), -1)));
    }
}
