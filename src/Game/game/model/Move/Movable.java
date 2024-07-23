package Game.game.model.Move;

import java.awt.geom.Point2D;
import java.util.ArrayList;
public interface Movable  {
    class fillses {
        public static boolean dontComeNewrMe = false;
        public static boolean stopEveryThing = false;
    }
    ArrayList<Movable> MOVE_ABLE = new ArrayList<>();
    void move();

    Point2D getAnchor();
}
