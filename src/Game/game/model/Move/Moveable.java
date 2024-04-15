package Game.game.model.Move;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public interface Moveable {
    ArrayList<Moveable> moveAble = new ArrayList<>();

    void move(Direction direction, double speed);

    void move();

    void setDirection(Direction direction);
    void setSpeed(double speed);
}
