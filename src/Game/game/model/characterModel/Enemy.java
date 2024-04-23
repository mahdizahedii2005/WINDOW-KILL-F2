package Game.game.model.characterModel;

import Game.game.Contoroler.BuilderHelper;
import Game.game.Contoroler.MokhtasatPoint;
import Game.game.Contoroler.Spawn;
import Game.game.model.Move.Direction;
import Game.game.model.Move.Moveable;
import Game.game.model.Move.follower;
import Game.game.model.Move.impactAble;
import Game.game.model.collision.Collidable;
import Game.game.model.shooting.shootGiver;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.BufferedOutputStream;
import java.util.UUID;

import static Game.Data.constants.SQUAER_COLOR;
import static Game.game.Contoroler.Controller.addEnemy;
import static Game.game.Contoroler.Controller.fire;
import static Game.helper.addVectors;
import static Game.helper.checkNan;

public class Enemy extends ObjectInGame implements Collidable, follower, shootGiver, impactAble {

    int impactNum = 0;
    double[] xPoint;
    double speed = 0;
    double[] yPoint;
    int nPoint;
    protected int damageTaker = 5;
    Direction MoveDirection = new Direction (new Point2D.Double (0, 0));

    public double[] getxPoint () {
        return xPoint;
    }

    public void setxPoint (double[] xPoint) {
        this.xPoint = xPoint;
    }

    public double[] getyPoint () {
        return yPoint;
    }

    public void setyPoint (double[] yPoint) {
        this.yPoint = yPoint;
    }

    public int getnPoint () {
        return nPoint;
    }

    public void setnPoint (int nPoint) {
        this.nPoint = nPoint;
    }

    public Enemy (Point2D.Double center, Color color, int nPoint, double[] xPoint, double[] yPoint, int hp ,double radius) {
        super (center, color, UUID.randomUUID ().toString (), hp,radius);
        if (checkNan (xPoint, yPoint)) {
            addEnemy (getId ());
            this.nPoint = nPoint;
            this.xPoint = xPoint;
            this.yPoint = yPoint;
            this.damageTaker = 5;
            Moveable.moveAble.add (this);
        } else {
            ObjectInGame.objectInGames.remove (this);
            if (this instanceof TrigorathModel) {
                BuilderHelper.trigorathBuilder ();
            } else if (this instanceof SquarantineModel) {
                BuilderHelper.squarantineBuilder ();
            }
        }
    }

    @Override
    public void move (Direction direction, double speed) {
        MokhtasatPoint movement = addVectors (xPoint, yPoint, center, MoveDirection.getDirectionVector (), speed);
        this.xPoint = movement.getxPoint ();
        this.yPoint = movement.getyPoint ();
        this.center = movement.getCenter ();
    }

    @Override
    public void move () {
        impactNum = Math.max (impactNum - 1, 0);
        move (MoveDirection, speed);
    }

    @Override
    public void setDirection (Direction direction) {
        this.MoveDirection = direction;
    }

    @Override
    public int getImpactNum () {
        return impactNum;
    }

    public void setImpactNum (int impactNum) {
        this.impactNum = impactNum;
    }


    @Override
    public void setSpeed (double speed) {
        this.speed = speed;
    }

    @Override
    public double getSpeed () {
        return speed;
    }

    @Override
    public void follow () {

    }

    @Override
    public void Die () {
        new prize (new Point2D.Double (center.getX (), center.getY ()), color, this instanceof TrigorathModel ? 5 : 10);
        super.Die ();
        if (this instanceof SquarantineModel) {
            Spawn.getSpawn ().DecreaseSQNumEnemy ();
        } else {
            Spawn.getSpawn ().DecreaseTRNumEnemy ();
        }

        // TODO: ۱۴/۰۴/۲۰۲۴ play music
    }

    @Override
    public void takingShot () {
        HP = Math.max (0, HP - damageTaker);
        if (HP == 0) {
            Die ();
        }
    }

    @Override
    public Direction getMoveDirection () {
        return MoveDirection;
    }

    @Override
    public void setMoveDirection (Direction moveDirection) {
        MoveDirection = moveDirection;
    }
}
