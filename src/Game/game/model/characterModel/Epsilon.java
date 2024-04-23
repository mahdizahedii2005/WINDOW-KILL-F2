package Game.game.model.characterModel;

import Game.game.model.Move.Direction;
import Game.game.model.Move.Moveable;
import Game.game.model.Move.impactAble;
import Game.game.model.collision.Collidable;
import Game.game.model.collision.PrizeCollide;
import Game.game.model.shooting.shooter;
import Game.game.view.characterView.PrizeView;
import Game.game.view.panelInGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.UUID;

import static Game.Data.constants.SPEED;
import static Game.game.Contoroler.Controller.*;
import static Game.game.model.characterModel.bolt.boltList;
import static Game.game.model.characterModel.originalPanel.*;
import static Game.helper.addVectors;
import static Game.helper.multiplyVector;

public class Epsilon extends ObjectInGame implements Collidable, Moveable, shooter, PrizeCollide, impactAble {
    int impactNum = 0;
    boolean sefrShode = false;
    private static Epsilon epsilon = null;

    double speed = SPEED;
    Direction MoveDirection = new Direction (new Point2D.Double (0, 0));

    public Epsilon (Point2D.Double center, double radius) {
        super (center, Color.WHITE, UUID.randomUUID ().toString (), 100,radius);
        addEpsilon (getId ());
        epsilon = this;
        Moveable.moveAble.add (this);
    }

    public void changDirection (double x, double y) {
        if (impactNum > 0) {
            setDirection (new Direction (addVectors (new Point2D.Double (x, y), getMoveDirection ().getPoint ())));
        } else {
            speed = SPEED;
            setDirection (new Direction (new Point2D.Double (x, y)));
        }
        move ();
    }

    public void changDirection (double x, boolean XorY) {
        if (XorY) {
            changDirection (x, MoveDirection.getPoint ().getY ());
        } else {
            changDirection (MoveDirection.getPoint ().getX (), x);
        }
    }

    public static Epsilon getEpsilon () {
        return epsilon;
    }
    @Override
    public void move (Direction direction, double speed) {
        Point2D.Double movement = multiplyVector (direction.getDirectionVector (), speed);
        boolean L, R, U, D;
        L = dontGoLeft (movement);
        R = dontGoRight (movement);
        U = dontGoUp (movement);
        D = dontGoDown (movement);
        if (L || R || D | U) {
            impactNum = Math.max (0, impactNum - 1);
            return;
        }
        this.center = addVectors (center, movement);
        if (impactNum == 0 && sefrShode) {
            MoveDirection = new Direction (new Point2D.Double (0, 0));
            sefrShode = false;
        }
    }

    public boolean dontGoRight (Point2D.Double movement) {
        Point2D.Double centerr = addVectors (center, movement);
        if (centerr.getX () + radius > originalPanel.getPanel ().getX () + originalPanel.getPanel ().getWidth ()) {
            setCenter (new Point2D.Double (originalPanel.getPanel ().getX () + originalPanel.getPanel ().getWidth () - radius, getCenter ().getY ()));
            return true;
        }
        return false;
    }

    public boolean dontGoLeft (Point2D.Double movement) {
        Point2D.Double centerr = addVectors (center, movement);
        if (centerr.getX () - radius < originalPanel.getPanel ().getX ()) {
            setCenter (new Point2D.Double (originalPanel.getPanel ().getX () + radius, centerr.getY ()));
            return true;
        }
        return false;
    }

    public boolean dontGoUp (Point2D.Double movement) {
        Point2D.Double centerr = addVectors (center, movement);
        if (centerr.getY () - radius < originalPanel.getPanel ().getY ()) {
            setCenter (new Point2D.Double (centerr.getX (), originalPanel.getPanel ().getY () + radius));
            return true;
        }
        return false;
    }

    public boolean dontGoDown (Point2D.Double movement) {
        Point2D.Double centerr = addVectors (center, movement);
        if (centerr.getY () + radius > originalPanel.getPanel ().getY () + originalPanel.getPanel ().getHeight ()) {
            setCenter (new Point2D.Double (centerr.getX (), originalPanel.getPanel ().getY () + originalPanel.getPanel ().getHeight () - radius));
            return true;
        }
        return false;
    }


    public void move () {
        impactNum = Math.max (impactNum - 1, 0);
        move (MoveDirection, speed);
    }

    @Override
    public void setDirection (Direction direction) {
        this.MoveDirection = direction;
    }

    @Override
    public void setSpeed (double speed) {
        this.speed = speed;
    }

    @Override
    public Direction getMoveDirection () {
        return MoveDirection;
    }

    @Override
    public void fire (Point2D.Double target, Point2D.Double realTarget) {
        boltList.add (new bolt (target, realTarget));
    }

    @Override
    public boolean prizeCollide (prize prize) {
        if (prize.getCenter ().distance (getCenter ()) <= getRadius () + PrizeView.radius + 2) {
            return true;
        }
        return false;
    }

    @Override
    public void setMoveDirection (Direction moveDirection) {
        MoveDirection = moveDirection;
    }

    @Override
    public int getImpactNum () {
        return impactNum;
    }

    @Override
    public double getSpeed () {
        return speed;
    }

    public void setImpactNum (int impactNum) {
        this.impactNum = impactNum;
        if (impactNum > 0) {
            sefrShode = true;
        }
    }

}