package Game.game.model.characterModel;

import Game.game.Contoroler.Controller;
import Game.game.model.Move.Direction;
import Game.game.model.Move.Moveable;
import Game.game.model.collision.Collidable;
import Game.game.model.collision.boltCollide;
import Game.game.model.shooting.shootGiver;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static Game.Data.constants.*;
import static Game.game.view.characterView.boltView.R1;
import static Game.helper.addVectors;
import static Game.helper.multiplyVector;

public class bolt extends ObjectInGame implements Moveable, Collidable, boltCollide {
    public static ArrayList<bolt> boltList = new ArrayList<> ();
    double speed = SPEED * 3;
    Direction moveDirection;

    public bolt (Point2D.Double target) {
        super (Epsilon.getEpsilon ().getCenter (), Color.white, UUID.randomUUID ().toString (), 1);
        this.moveDirection = new Direction (target);
        Controller.addBolt (getId ());
        Moveable.moveAble.add (this);
    }

    public void hit (shootGiver a) {
        HP = 0;
        a.takingShot ();
    }

    public void move (Direction direction, double speed) {
        Point2D movement = multiplyVector (direction.getDirectionVector (), speed);
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
        super.Die ();
        boltList.remove (this);
        Moveable.moveAble.remove (this);
    }

    @Override
    public void boltCollide (shootGiver a) {
        if (a instanceof TrigorathModel) {
            if (HitTrigo ((TrigorathModel) a)) {
                a.takingShot ();
                Die ();
            }
        } else if (a instanceof SquarantineModel) {
            if (HitSqure ((SquarantineModel) a)) {
                a.takingShot ();
                Die ();
            }
        }
    }

    private boolean HitSqure (SquarantineModel s) {
        if (s.getCenter ().distance (getCenter ()) <= R1 + ((SIDE_OF_SQUARE / 2d + (0.72 * SIDE_OF_SQUARE)) / 2d) + 4) {
            return true;
        }
        return false;
    }

    private boolean HitTrigo (TrigorathModel t) {
        if (t.getCenter ().distance (this.getCenter ()) <= R1 + (HEIGHT_OF_TRIANGLE / 2d) + 1) {
            return true;
        }
        return false;
    }
}
