package Game.game.model.characterModel.epsilonFriend;

import Game.game.Contoroler.control.Update;
import Game.game.model.Move.Direction;
import Game.game.model.Move.Moveable;
import Game.game.model.Move.impactAble;
import Game.game.model.characterModel.ObjectInGame;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.characterModel.Panels.rigidPanel;
import Game.game.model.collision.Collidable;
import Game.game.model.collision.CollisionState;
import Game.game.model.shooting.shooter;
import Game.game.view.characterView.DrawAbleObject;
import Game.game.view.characterView.shape.epsilonView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.UUID;

import static Game.Data.constants.SPEED;
import static Game.game.Contoroler.control.Controller.fixThePoint;
import static Game.game.model.characterModel.Panels.PanelInGame.PANELS;
import static Game.helper.addVectors;
import static Game.helper.multiplyVector;

public class Epsilon extends ObjectInGame implements Collidable, Moveable, shooter, impactAble {
    private int impactNum = 0;
    private double exp = 0;
    private boolean sefrShode = false;
    private static Epsilon epsilon = null;
    private int MaxHp = 100;
    private double speed = SPEED * 4;
    private Direction MoveDirection = new Direction(new Point2D.Double(0, 0));
    private double totalExp = 0;

    public Epsilon(Point2D.Double center, double radius) {
        super(center, Color.WHITE, UUID.randomUUID().toString(), 100, radius, true, 5);
        epsilon = this;
        Moveable.moveAble.add(this);
    }

    public void changDirection(double x, double y) {
        if (Update.finish) {
            if (impactNum > 0) {
                setDirection(new Direction(addVectors(new Point2D.Double(x, y), getMoveDirection().getPoint())));
            } else {
                speed = SPEED * 1.5;
                setDirection(new Direction(new Point2D.Double(x, y)));
            }
            move();
        }
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

    public int getMaxHp() {
        return MaxHp;
    }

    @Override
    public void move(Direction direction, double speed) {
        if (Update.finish) {
            Point2D.Double movement = multiplyVector(direction.getDirectionVector(), speed);
            boolean L = dontGoOutOfPanel(movement);
            if (L) {
                impactNum = Math.max(0, impactNum - 1);
                return;
            }
            this.center = addVectors(center, movement);
            if (impactNum == 0 && sefrShode) {
                MoveDirection = new Direction(new Point2D.Double(0, 0));
                sefrShode = false;
            }
        }
    }

    public boolean dontGoOutOfPanel(Point2D.Double movement) {
        Point2D.Double centerr = addVectors(center, movement);
        for (PanelInGame ri : PANELS) {
            if (ri instanceof rigidPanel) {
                Collidable rigid = (rigidPanel) ri;
                rigid.createGeometry();
                CollisionState collisionState = this.checkCollision(rigid);
                if (collisionState.isCollision()) {
                    side side = rigid.findCollision(collisionState.getCollisionPoint());
                    switch (side) {
                        case up -> {
                            dontGoUp(movement, ri);
                            return true;
                        }
                        case down -> {
                            dontGoDown(movement, ri);
                            return true;
                        }
                        case left -> {
                            dontGoLeft(movement, ri);
                            return true;
                        }
                        case right -> {
                            dontGoRight(movement, ri);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void dontGoRight(Point2D.Double movement, PanelInGame originalPanel) {
        Point2D.Double centerr = addVectors(center, movement);
        setCenter(new Point2D.Double(originalPanel.getX() + originalPanel.getWidth() - radius - 1, centerr.getY()));
    }

    public void dontGoLeft(Point2D.Double movement, PanelInGame originalPanel) {
        Point2D.Double centerr = addVectors(center, movement);
        setCenter(new Point2D.Double(originalPanel.getX() + radius + 1, centerr.getY()));

    }

    public void dontGoUp(Point2D.Double movement, PanelInGame originalPanel) {
        Point2D.Double centerr = addVectors(center, movement);
        setCenter(new Point2D.Double(centerr.getX(), originalPanel.getY() + radius + 1));
    }

    public void dontGoDown(Point2D.Double movement, PanelInGame originalPanel) {
        Point2D.Double centerr = addVectors(center, movement);
        setCenter(new Point2D.Double(centerr.getX(), originalPanel.getY() + originalPanel.getHeight() - radius - 1));
    }


    public void move() {
        impactNum = Math.max(impactNum - 1, 0);
        move(MoveDirection, speed);
    }

    @Override
    public void setDirection(Direction direction) {
        this.MoveDirection = direction;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public Direction getMoveDirection() {
        return MoveDirection;
    }

    @Override
    public void fire(Point2D.Double target, Point2D.Double realTarget) {
        if (Update.finish) {
            if (AthenaPower) {
                new bolt(target, realTarget);
                new bolt(target, realTarget, SPEED * 7);
                new bolt(target, realTarget, SPEED * 6);

            } else {
                new bolt(target, realTarget);
            }
        }
    }

    public static boolean AthenaPower = false;

    @Override
    public void setMoveDirection(Direction moveDirection) {
        MoveDirection = moveDirection;
    }

    @Override
    public int getImpactNum() {
        return impactNum;
    }

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
        return true;
    }

    @Override
    public CollisionState checkCollision(Collidable collidable) {
        return Collidable.super.checkCollision(collidable);
    }


    @Override
    public void play(Collidable collidable) {
//         if () on skill tree enemy get damage

    }

    public void setImpactNum(int impactNum) {
        if (Update.finish) {
            this.impactNum = impactNum;
            if (impactNum > 0) {
                sefrShode = true;
            }
        }
    }

    public double getExp() {
        return exp;
    }

    public void increaseExp(double exp) {
        if (Update.finish) {
            this.exp += exp;
            this.totalExp += exp;
        }
    }

    public void increaseHp(int a) {
        if (Update.finish) {
            setHP(Math.min(getMaxHp(), getHP() + a));
        }
    }

    public boolean decreaseEXP(int a) {
        if (exp - a >= 0) {
            exp = exp - a;
            return true;
        }
        return false;
    }

    @Override
    public DrawAbleObject getDrawAbleObject(PanelInGame panel) {
        Color colorr = color;

//        if (skillReady) {
//            switch (new Random().nextInt(3)) {
//                case 1 -> colorr = Color.RED;
//                case 2 -> colorr = Color.GREEN;
//            }
//        }
        return new epsilonView(fixThePoint(center, panel), colorr, radius);

    }

    @Override
    public void setHP(double HP) {
        super.setHP(HP);
        if (HP == 0 && Update.finish) {
//            new lost();
        }
    }

    public double getTotalExp() {
        return totalExp;
    }
}