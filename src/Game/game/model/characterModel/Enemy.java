package Game.game.model.characterModel;

import Game.game.model.Move.Direction;
import Game.game.model.Move.Moveable;
import Game.game.model.collision.colliAble;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.UUID;

import static Game.Data.constants.SPEED;
import static Game.game.Contoroler.Controller.addEnemy;

public class Enemy extends ObjectInGame implements colliAble, Moveable {
    double[] xPoint;
    double[] yPoint;
    int nPoint;
    Direction MoveDirection = new Direction(new Point2D.Double(0,0));

    public double[] getxPoint() {
        return xPoint;
    }

    public void setxPoint(double[] xPoint) {
        this.xPoint = xPoint;
    }

    public double[] getyPoint() {
        return yPoint;
    }

    public void setyPoint(double[] yPoint) {
        this.yPoint = yPoint;
    }

    public int getnPoint() {
        return nPoint;
    }

    public void setnPoint(int nPoint) {
        this.nPoint = nPoint;
    }

    public Enemy(Point2D.Double center, Color color, int nPoint, double[] xPoint, double[] yPoint) {
        super(center, color, UUID.randomUUID().toString());
        addEnemy(getId());
        this.nPoint = nPoint;
        this.xPoint = xPoint;
        this.yPoint = yPoint;
        Moveable.moveAble.add(this);
    }

    @Override
    public void move(Direction direction, double speed) {

    }

    @Override
    public void move() {
        move(MoveDirection,SPEED);
    }

    @Override
    public void setDirection(Direction direction) {
        this.MoveDirection = direction;
    }
}
