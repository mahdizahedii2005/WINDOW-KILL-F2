package Game.game.model.characterModel;

import Game.game.model.Move.Direction;
import Game.game.model.Move.NormalFollower;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

import static Game.Data.constants.SPEED;
import static Game.Data.constants.SQUAER_COLOR;
import static Game.game.model.characterModel.bolt.SQU_CHECK;

public class SquarantineModel extends Enemy implements NormalFollower {
    private int cycle = 0;
    private boolean isPower = false;

    public SquarantineModel (Point2D.Double center, double[] xPoint, double[] yPoint) {
        super (center, SQUAER_COLOR, 4, xPoint, yPoint, 10, SQU_CHECK);
    }

    @Override
    public void follow () {
        if (impactNum > 0) {
            return;
        }
        speed = SPEED;
        if (new Random ().nextInt (18) == 1 && !isPower) {
            isPower = true;
            cycle = 15;
            setSpeed (SPEED * 2);
        }
        if (isPower) {
            cycle--;
            if (cycle == 0) {
                isPower = false;
                setSpeed (SPEED * 3d / 10d);
            }
        }
        setDirection (new Direction (new Point2D.Double ((Epsilon.getEpsilon ().getCenter ().getX () - getCenter ().getX ()), (Epsilon.getEpsilon ().getCenter ().getY () - getCenter ().getY ()))));
    }
}
