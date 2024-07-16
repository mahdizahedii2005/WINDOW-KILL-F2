package Game.game.Contoroler.control;

import Game.game.Contoroler.player.soundPlayer;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.model.characterModel.ObjectInGame;
import Game.game.view.PanelInGame.NotifPanel;
import Game.game.view.PanelInGame.frameInGame;
import Game.game.view.PanelInGame.panelInGameView;
import Game.game.view.characterView.DrawAbleObject;
import Game.game.view.characterView.shape.circleShape;
import Game.game.view.characterView.shape.epsilonView;
import Game.game.view.characterView.shape.nonCircular;
import Game.helper;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static Game.Data.constants.FIRE_BOLT_PATH;


public abstract class Controller {
    public static DrawAbleObject makeEpsilonView(Point2D anchor, Color color, double Radius) {
        return new epsilonView(anchor, color, Radius);
    }

    public static DrawAbleObject makeNonCircularView(ArrayList<Point2D> geometry, Color color) {
        return new nonCircular(geometry, color);
    }

    public static DrawAbleObject makeCircularView(Point2D anchor, Color color, double Radius) {
        return new circleShape(anchor, color, Radius);
    }

    public static ObjectInGame findObjectModel(String id) {
        for (ObjectInGame o : ObjectInGame.objectInGames) {
            if (o.getId().equals(id)) {
                return o;
            }
        }
        return null;
    }

    public static NotifPanel findNotifPanel(String name) {
        for (NotifPanel notifPanel : frameInGame.getFrame().notifPanels) {
            if (name.equals(notifPanel.getName())) return notifPanel;
        }
        return null;
    }

    public static String convertToMinSec(long nanoSeconds) {
        // Convert nanoseconds to seconds
        long seconds = nanoSeconds / 1_000_000_000;
        // Calculate minutes
        long minutes = seconds / 60;
        // Calculate remaining seconds
        long remainingSeconds = seconds % 60;

        // Format the result as "min:sec"
        return minutes + ":" + remainingSeconds;
    }

    public static Point2D.Double PointFloutToDouble(Point2D.Float po) {
        return new Point2D.Double((double) po.getX(), (double) po.getY());
    }

    public static Point2D.Float Point2DToFlout(Point2D po) {
        return new Point2D.Float((float) po.getX(), (float) po.getY());
    }

    public static panelInGameView findPanelView(String id) {
        for (panelInGameView o : frameInGame.getFrame().PANELS) {
            try {
                if (o.getId().equals(id)) return o;
            } catch (Exception r) {
            }
        }
        return null;
    }

    public static boolean isItInside(ObjectInGame object, PanelInGame panel) {
        if (object.isCirClr()) {
            return object.getCenter().getY() + object.getRadius() > panel.getY() &&
                    object.getCenter().getY() - object.getRadius() < panel.getY() + panel.getHeight() &&
                    object.getCenter().getX() + object.getRadius() > panel.getX() &&
                    object.getCenter().getX() - object.getRadius() < panel.getX() + panel.getWidth();
        } else {
            for (Point2D point2D : object.getVertices()) {
                if (point2D.getX() >= panel.getX() &&
                        point2D.getX() <= panel.getX() + panel.getWidth() &&
                        point2D.getY() >= panel.getY() &&
                        point2D.getY() + panel.getHeight() >= point2D.getY()) return true;
            }
            return false;
        }
    }

//    public static ArrayList<PanelInGame> whatPanelForDraw(ArrayList<Point2D> ver) {
//        ArrayList<PanelInGame> result = new ArrayList<>();
//        for (PanelInGame panel : PANELS) {
//            for (Point2D point2D : ver) {
//                if (isItInside(point2D, panel)) {
//                    result.add(panel);
//                    break;
//                }
//            }
//        }
//        return result;
//    }

//    public static ArrayList<PanelInGame> whatPanelForDraw(ArrayList<Point2D> ver, boolean tru) {
//        ArrayList<PanelInGame> result = new ArrayList<>();
//        for (PanelInGame panel : PANELS) {
//
//        }
//        return panelInGameView.getPanel();
//    }

//    public static ArrayList<PanelInGame> whatPanelForDraw(Point2D.Double Point) {
//        ArrayList<PanelInGame> result = new ArrayList<>();
//        for (PanelInGame panel : PANELS) {
//            if (isItInside(Point, panel)) result.add(panel);
//        }
//        return result;
//    }

    public static ArrayList<Point2D> fixThePoint(ArrayList<Point2D> arrayList, PanelInGame panel) {
        ArrayList<Point2D> result = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            result.add(i, new Point2D.Double(arrayList.get(i).getX() - panel.getX(), arrayList.get(i).getY() - panel.getY()));
        }
        return result;
    }

    public static Point2D.Double fixThePoint(Point2D.Double anchor, PanelInGame panel) {
        return new Point2D.Double(anchor.getX() - panel.getX(), anchor.getY() - panel.getY());
    }

    public static void moveEpsilon(double x, double y) {
        if (x == 0 && y == 0) {
            Epsilon.getEpsilon().changDirection(x, y);
        } else if (x == 0 && Epsilon.getEpsilon() != null) {
            Epsilon.getEpsilon().changDirection(y, false);
        } else if (y == 0) {
            Epsilon.getEpsilon().changDirection(x, true);
        } else {
            Epsilon.getEpsilon().changDirection(x, y);
        }
    }

    public static Point2D.Double azHamKamKardanDoBordar(Point2D p1, Point2D p2) {
        return new Point2D.Double(p2.getX() - p1.getX(), p2.getY() - p1.getY());
    }

    public static void moveEpsilon(boolean b) {
        if (b) {
            Epsilon.getEpsilon().changDirection(0, b);
        } else {
            Epsilon.getEpsilon().changDirection(0, b);
        }
    }

    public static void fire(Point2D.Double target) {
        Epsilon.getEpsilon().fire(Epsilon.getEpsilon().getCenter(), target);
        soundPlayer.play(FIRE_BOLT_PATH);
    }

    public static void CreatNewPanelView(String id) {
        new panelInGameView(id);
    }

    public static <T> ArrayList<T> swich(ArrayList<T> arrayList, int d1, int d2) {
        T a1 = arrayList.get(d1);
        T a2 = arrayList.get(d2);
        arrayList.set(d1, a2);
        arrayList.set(d2, a1);
        return arrayList;
    }
}
