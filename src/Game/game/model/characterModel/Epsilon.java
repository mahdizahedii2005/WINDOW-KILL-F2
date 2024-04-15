package Game.game.model.characterModel;

import Game.game.model.Move.Direction;
import Game.game.model.Move.Moveable;
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
import static Game.helper.addVectors;
import static Game.helper.multiplyVector;

public class Epsilon extends ObjectInGame implements Collidable, Moveable, shooter, PrizeCollide {
    private static Epsilon epsilon = null;
    double radius;
    double speed = SPEED;
    Direction MoveDirection = new Direction (new Point2D.Double (0, 0));

    public double getRadius () {
        return radius;
    }

    public void setRadius (double radius) {
        this.radius = radius;
    }

    public Epsilon (Point2D.Double center, int radius) {
        super (center, Color.WHITE, UUID.randomUUID ().toString (), 100);
        addEpsilon (getId ());
        this.radius = radius;
        epsilon = this;
        Moveable.moveAble.add (this);
    }

    public void changDirection (double x, double y) {
        setDirection (new Direction (new Point2D.Double (x, y)));
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

    private javax.swing.Timer reduseHp = new Timer (1000, new AbstractAction () {
        @Override
        public void actionPerformed (ActionEvent e) {
            HP -= 5;
        }
    });
    private boolean isReduse = false;

    @Override
    public void move (Direction direction, double speed) {
        Point2D.Double movement = multiplyVector (direction.getDirectionVector (), speed);
        if (dontGoRight (movement) || dontGoDown (movement) || dontGoLeft (movement) || dontGoUp (movement)) {
            // TODO: ۱۵/۰۴/۲۰۲۴ immpact
            if (!isReduse) {
                HP -= 5;
                reduseHp.start ();
                isReduse = true;
            }
            return;
        }
        if (isReduse) {
            isReduse = false;
            reduseHp.stop ();
        }
        this.center = addVectors (center, movement);
    }

    private boolean dontGoRight (Point2D.Double movement) {
        return (getRightPanel ().ptSegDist (addVectors (center, movement)) < getRadius () + 1) ||
                (addVectors (center, movement).getX () + getRadius () >= panelInGame.getPanel ().getX () + panelInGame.getPanel ().getWidth ());
    }

    private boolean dontGoLeft (Point2D.Double movement) {
        return (getLeftPanel ().ptSegDist (addVectors (center, movement)) < getRadius () + 1) ||
                (addVectors (center, movement).getX () - getRadius () <= panelInGame.getPanel ().getX ());
    }

    private boolean dontGoUp (Point2D.Double movement) {
        return (getUpPanel ().ptSegDist (addVectors (center, movement)) < getRadius () + 1) ||
                (addVectors (center, movement).getY () - getRadius () <= panelInGame.getPanel ().getY ());
    }

    private boolean dontGoDown (Point2D.Double movement) {
        return (getDownPanel ().ptSegDist (addVectors (center, movement)) < getRadius () + 1) ||
                (addVectors (center, movement).getY () + getRadius () >= panelInGame.getPanel ().getY () + panelInGame.getPanel ().getHeight ());
    }


    public void move () {
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

    public Direction getMoveDirection () {
        return MoveDirection;
    }

    @Override
    public void fire (Point2D.Double target) {
        boltList.add (new bolt (target));
    }

    @Override
    public boolean prizeCollide (prize prize) {
        if (prize.getCenter ().distance (getCenter ()) <= getRadius () + PrizeView.radius + 2) {
            return true;
        }
        return false;
    }
}