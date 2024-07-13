package Game.game.model.characterModel.epsilonFriend;

import Game.game.Contoroler.player.soundPlayer;
import Game.game.model.Move.Direction;
import Game.game.model.Move.Moveable;
import Game.game.model.characterModel.ObjectInGame;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.collision.Collidable;
import Game.game.model.shooting.shootGiver;
import Game.game.view.characterView.DrawAbleObject;
import Game.game.view.characterView.shape.circleShape;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.UUID;

import static Game.Data.constants.*;
import static Game.game.Contoroler.control.Controller.fixThePoint;
import static Game.helper.addVectors;
import static Game.helper.multiplyVector;

public class bolt extends ObjectInGame implements Moveable, Collidable {
    private Point2D.Double mabda;
    private double speed = SPEED * 4;
    private Direction moveDirection;

    public bolt(Point2D.Double target, Point2D.Double realTarget, double speed) {
        this(target, realTarget);
        this.speed = speed;
    }

    public bolt(Point2D.Double target, Point2D.Double realTarget) {
        super(new Point2D.Double(Epsilon.getEpsilon().getCenter().getX() + 1, Epsilon.getEpsilon().getCenter().getY() + 1), Color.white, UUID.randomUUID().toString(), 1, BOLT_RADIUS, true, 5);
        this.moveDirection = new Direction(target, realTarget);
        Moveable.moveAble.add(this);
        this.mabda = Epsilon.getEpsilon().getCenter();
        soundPlayer.play(FIRE_BOLT_PATH);
    }

    public void move(Direction direction, double speed) {
        Point2D.Double movement = multiplyVector(direction.getDirectionVector(), speed);
        this.center = addVectors(center, movement);
    }

    public void move() {
        move(moveDirection, speed);
    }

    @Override
    public void setDirection(Direction direction) {
        this.moveDirection = direction;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public DrawAbleObject getDrawAbleObject(PanelInGame panel) {
        return new circleShape(fixThePoint(center, panel), color, radius);
    }
//    @Override
//    public void wallCollision(originalPanel.panelWall a) {
//        if (wallHit(a)) {
//            originalPanel.getPanel().increase(a);
//            new impact(new Point2D.Double(center.getX(), center.getY()));
////            switch (a) {
////                case up: {
////                    new impact(new Point2D.Double(center.getX(), originalPanel.getPanel().getY()));
////                    break;
////                }
////                case down: {
////                    new impact(new Point2D.Double(center.getX(), originalPanel.getPanel().getY() + originalPanel.getPanel().getHeight()));
////                    break;
////                }
////                case left: {
////                    new impact(new Point2D.Double(originalPanel.getPanel().getX(), center.getY()));
////                    break;
////                }
////                case right: {
////                    new impact(new Point2D.Double(originalPanel.getPanel().getX() + originalPanel.getPanel().getWidth(), center.getY()));
////                    break;
////                }
////            }
//            Die();
//        }
//    }
//
//    private boolean wallHit(originalPanel.panelWall a) {
//        switch (a) {
//            case up -> {
//                return getCenter().getY() - R1 <= originalPanel.getPanel().getY();
//            }
//            case down -> {
//                return getCenter().getY() + R1 >= originalPanel.getPanel().getY() + originalPanel.getPanel().getHeight();
//            }
//            case left -> {
//                return getCenter().getX() - R1 <= originalPanel.getPanel().getX();
//            }
//            case right -> {
//                return getCenter().getX() + R1 >= originalPanel.getPanel().getX() + originalPanel.getPanel().getWidth();
//            }
//        }
//        return false;
//    }

    @Override
    public void createGeometry() {
        super.CreateGeometry();
    }

    @Override
    public boolean isCircular() {
        return super.isCirClr();
    }

    @Override
    public Point2D getAnchor() {
        return new Point2D.Float((float) center.x, (float) center.y);
    }

    @Override
    public float getSpeed() {
        return (float) speed;
    }

    @Override
    public boolean collide(Collidable collidable) {
        return collidable instanceof shootGiver;
    }

    @Override
    public void play(Collidable collidable) {
        if (collidable instanceof shootGiver) {
            shootGiver enemy = (shootGiver) collidable;
            enemy.takingShot(this);
            Die();
        }
    }
}
