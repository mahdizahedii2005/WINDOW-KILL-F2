package Game.game.model.characterModel;

import Game.game.model.Move.Direction;
import Game.game.model.Move.Moveable;
import Game.game.model.collision.colliAble;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.UUID;

import static Game.Data.constants.SPEED;
import static Game.game.Contoroler.Controller.addEpsilon;
import static Game.helper.addVectors;
import static Game.helper.multiplyVector;

public class Epsilon extends ObjectInGame implements colliAble, Moveable {
    private static Epsilon epsilon = null;
    double radius;
    Direction MoveDirection = new Direction(new Point2D.Double(0, 0));

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Epsilon(Point2D.Double center, int radius) {
        super(center, null, UUID.randomUUID().toString());
        addEpsilon(getId());
        this.radius = radius;
        epsilon = this;
        Moveable.moveAble.add(this);
    }

    public void changDirection(double x, double y) {
        setDirection(new Direction(new Point2D.Double(x, y)));
        move();
    }

    public void changDirection(double x, boolean XorY) {
        if (XorY) {
            changDirection(x, MoveDirection.getPoint().getY());
        } else {
            changDirection(MoveDirection.getPoint().getX(), x);
        }
    }

    public static Epsilon getEpsilon() {
        return epsilon;
    }

    @Override
    public void move(Direction direction, double speed) {
        Point2D movement = multiplyVector(direction.getDirectionVector(), speed);
        this.center = addVectors(center, movement);
    }

    public void move() {
       move(MoveDirection, SPEED);
    }

    @Override
    public void setDirection(Direction direction) {
        this.MoveDirection = direction;
    }

    public Direction getMoveDirection() {
        return MoveDirection;
    }
}