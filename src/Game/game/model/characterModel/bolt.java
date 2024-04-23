package Game.game.model.characterModel;

import Game.game.Contoroler.Controller;
import Game.game.Contoroler.impact;
import Game.game.model.Move.Direction;
import Game.game.model.Move.Moveable;
import Game.game.model.collision.Collidable;
import Game.game.model.collision.boltCollide;
import Game.game.model.shooting.shootGiver;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static Game.Data.constants.*;
import static Game.game.view.characterView.boltView.R1;
import static Game.helper.*;

public class bolt extends ObjectInGame implements Moveable, Collidable, boltCollide {
    public static ArrayList<bolt> boltList = new ArrayList<> ();
    Point2D.Double mabda;
    double speed = SPEED * 8;
    Direction moveDirection;

    public bolt (Point2D.Double target, Point2D.Double realTarget) {
        super (new Point2D.Double (Epsilon.getEpsilon ().getCenter ().getX () + 1, Epsilon.getEpsilon ().getCenter ().getY () + 1), Color.white, UUID.randomUUID ().toString (), 1,R1);
        this.moveDirection = new Direction (target, realTarget);
        Controller.addBolt (getId ());
        Moveable.moveAble.add (this);
        this.mabda = Epsilon.getEpsilon ().getCenter ();
    }

    public void move (Direction direction, double speed) {
        Point2D.Double movement = multiplyVector (direction.getDirectionVector (), speed);
        this.center = addVectors (center, movement);
    }

    public void move () {
        move (moveDirection, speed);
    }

    @Override
    public void setDirection (Direction direction) {
        this.moveDirection = direction;
    }

    @Override
    public void setSpeed (double speed) {
        this.speed = speed;
    }

    @Override
    public void Die () {
//        new impact (getCenter ());
        super.Die ();
        boltList.remove (this);
        Moveable.moveAble.remove (this);
    }

    @Override
    public void boltCollide (shootGiver a) {
        if (a instanceof TrigorathModel) {
            if (HitTrigo ((TrigorathModel) a)) {
                new impact (findCollisionPlace (((Enemy) a).center, moveDirection.getRealPoint (), mabda, TRIANGLE_CHECK));
                HitTrigo ((TrigorathModel) a);
                a.takingShot ();
                Die ();
            }
        } else if (a instanceof SquarantineModel) {
            if (HitSqure ((SquarantineModel) a)) {
                new impact (findCollisionPlace (((Enemy) a).center, moveDirection.getRealPoint (), mabda, SQU_CHECK));
                a.takingShot ();
                Die ();
            }
        }
    }

    @Override
    public void wallCollision (originalPanel.panelWall a) {
        if (wallHit (a)) {
            originalPanel.getPanel ().increase (a);
            switch (a) {
                case up: {
                    new impact (new Point2D.Double (center.getX (), originalPanel.getPanel ().getY ()));
                    break;
                }
                case down: {
                    new impact (new Point2D.Double (center.getX (), originalPanel.getPanel ().getY () + originalPanel.getPanel ().getHeight ()));
                    break;
                }
                case left: {
                    new impact (new Point2D.Double (originalPanel.getPanel ().getX (), center.getY ()));
                    break;
                }
                case right: {
                    new impact (new Point2D.Double (originalPanel.getPanel ().getX () + originalPanel.getPanel ().getWidth (), center.getY ()));
                    break;
                }
            }
            Die ();
        }
    }

    private boolean wallHit (originalPanel.panelWall a) {
        switch (a) {
            case up -> {
                return getCenter ().getY () - R1 <= originalPanel.getPanel ().getY ();
            }
            case down -> {
                return getCenter ().getY () + R1 >= originalPanel.getPanel ().getY () + originalPanel.getPanel ().getHeight ();
            }
            case left -> {
                return getCenter ().getX () - R1 <= originalPanel.getPanel ().getX ();
            }
            case right -> {
                return getCenter ().getX () + R1 >= originalPanel.getPanel ().getX () + originalPanel.getPanel ().getWidth ();
            }
        }
        return false;
    }

    public static final double SQU_CHECK = R1 + ((SIDE_OF_SQUARE / 2d + (0.72 * SIDE_OF_SQUARE)) / 2d) + 7;


    private boolean HitSqure (SquarantineModel s) {
        if (s.getCenter ().distance (getCenter ()) < SQU_CHECK-2) {
            return true;
        }
        return false;
    }

    public static final double TRIANGLE_CHECK = R1 + (HEIGHT_OF_TRIANGLE / 2d) + 3;

    private boolean HitTrigo (TrigorathModel t) {
        if (t.getCenter ().distance (this.getCenter ()) < TRIANGLE_CHECK-2) {
            return true;
        }
        return false;
    }
}
