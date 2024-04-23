package Game.game.model.Move;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public interface impactAble extends Moveable {
    ArrayList<impactAble> impactAblesList = new ArrayList<> ();

    public int getImpactNum ();

    public void setImpactNum (int impactNum);


    Point2D.Double getCenter ();

    void setSpeed (double speed);

    double getSpeed ();

    void setMoveDirection (Direction direction);

    Direction getMoveDirection ();
}
