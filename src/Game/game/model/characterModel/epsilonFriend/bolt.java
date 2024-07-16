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
import Game.helper;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.UUID;

import static Game.Data.constants.*;
import static Game.game.Contoroler.control.Controller.fixThePoint;
import static Game.helper.addVectors;
import static Game.helper.multiplyVector;

public class bolt extends ObjectInGame implements Moveable, Collidable {
    private double speed;
    private Direction moveDirection;
    private boolean good;

    private final static double zaribOfSpeed = 4d;

    public bolt(Point2D.Double mabda, Point2D.Double target, double speed, boolean good, int damage) {
        this(mabda, target, Color.WHITE, damage, good, speed, BOLT_RADIUS);
    }

    public bolt(Point2D.Double mabda, Point2D.Double target, double speed, boolean good, int damage, Color color) {
        this(mabda, target, color, damage, good, speed, BOLT_RADIUS);
    }

    public bolt(Point2D.Double mabda, Point2D.Double target, boolean good, int damage) {
        this(mabda, target, Color.WHITE, damage, good, Epsilon.getEpsilon().getSpeed() * zaribOfSpeed, BOLT_RADIUS);
    }

    public bolt(Point2D.Double mabda, Point2D.Double Target, boolean good, int damage, Color color) {
        this(mabda, Target, color, damage, good, Epsilon.getEpsilon().getSpeed() * zaribOfSpeed, BOLT_RADIUS);
    }

    public bolt(Point2D.Double mabda, Point2D.Double target, Color color, int damage, boolean isGood, double speed, double Radius) {
        super(mabda, color, UUID.randomUUID().toString(), -1, Radius, true, damage);
        this.speed = speed;
        good = isGood;
        moveDirection = new Direction(helper.relativeLocation(target, mabda));
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
        if (!good) return collidable instanceof Epsilon;
        return collidable instanceof shootGiver && !(collidable instanceof Epsilon);
    }

    @Override
    public void play(Collidable collidable) {
        if (collidable instanceof shootGiver) {
            shootGiver enemy = (shootGiver) collidable;
            enemy.takingShot(this);
            Die();
        }
    }

    public boolean isGood() {
        return good;
    }
}
