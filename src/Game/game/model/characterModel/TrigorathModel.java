package Game.game.model.characterModel;

import Game.game.model.Move.Direction;
import Game.game.model.Move.NormalFollower;

import java.awt.*;
import java.awt.geom.Point2D;

import static Game.Data.constants.SPEED;
import static Game.Data.constants.TRIANGLE_SAFE_DISTANCE;
import static java.lang.Double.NaN;

public class TrigorathModel extends Enemy implements NormalFollower {
    public TrigorathModel (Point2D.Double center, Color color, double[] xPoint, double[] yPoint) {
        super (center, color, 3, xPoint, yPoint, 100);
    }
    double a = NaN;

    @Override
    public void follow () {
        if (center.distance (Epsilon.getEpsilon ().getCenter ()) > TRIANGLE_SAFE_DISTANCE) {
            setSpeed (SPEED * 5d / 4d);
        } else {
            setSpeed (SPEED * 2d / 4d);
        }
        setDirection (new Direction (new Point2D.Double ((Epsilon.getEpsilon ().getCenter ().getX () - getCenter ().getX ()), (Epsilon.getEpsilon ().getCenter ().getY () - getCenter ().getY ()))));
    }

    @Override
    public void setSpeed (double speed) {
        this.speed = speed;
    }
}
