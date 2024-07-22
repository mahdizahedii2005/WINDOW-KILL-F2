package Game.game.model.characterModel.epsilonFriend;

import Game.game.model.Move.Direction;
import Game.game.model.Move.impactAble;
import Game.game.model.Move.linearMotion;
import Game.game.model.characterModel.ObjectInGame;
import Game.game.model.characterModel.Panels.NonIsometricPanel;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.collision.Collidable;
import Game.game.model.collision.CollisionState;
import Game.game.model.shooting.shootGiver;
import Game.game.model.shooting.shooter;
import Game.game.view.characterView.DrawAbleObject;
import Game.game.view.characterView.shape.epsilonView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.UUID;

import static Game.Data.constants.SPEED;
import static Game.game.Contoroler.control.Controller.fixThePoint;
import static Game.game.Contoroler.control.Update.finish;
import static Game.game.model.characterModel.Panels.PanelInGame.PANELS;
import static Game.helper.addVectors;
import static Game.helper.multiplyVector;

public class Epsilon extends ObjectInGame implements Collidable, linearMotion, shooter, impactAble, shootGiver {
    private int impactNum = 0;
    private double exp = 0;
    private boolean sefrShode = false;
    private static Epsilon epsilon = null;
    private int MaxHp = 100;
    private double speed = SPEED / 1.14;
    private Direction MoveDirection = new Direction(new Point2D.Double(0, 0));
    private double totalExp = 0;
    public NonIsometricPanel mainPanel;

    public Epsilon(Point2D.Double center, double radius, NonIsometricPanel nonIsometricPanel) {
        super(center, Color.WHITE, UUID.randomUUID().toString(), 100, radius, true, 5);
        epsilon = this;
        this.mainPanel = nonIsometricPanel;
    }

    @Override
    public void CreateGeometry() {
        super.CreateGeometry();
    }

    public void changDirection(double x, double y) {
        if (finish) {
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
        if (finish) {
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
        for (int i = 0; i < PANELS.size(); i++) {
            PanelInGame ri = PANELS.get(i);
            if (ri instanceof NonIsometricPanel) {
                Collidable rigid = (NonIsometricPanel) ri;
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
    public void fire(Point2D.Double mabda, Point2D.Double target) {
        if (finish) {
            if (AthenaPower) {
                new bolt(mabda, target, true, 5);
                new bolt(mabda, target, SPEED * 7, true, 5);
                new bolt(mabda, target, SPEED * 6, true, 5);

            } else {
                new bolt(mabda, target, true, 5);
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
        if (finish) {
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
        if (finish) {
            this.exp += exp;
            this.totalExp += exp;
        }
    }

    public void increaseHp(int a) {
        if (finish) {
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
        if (HP == 0 && finish) {
//            new lost();
        }
    }

    public double getTotalExp() {
        return totalExp;
    }

    @Override
    public void takingShot(bolt bolt) {
        getHit(bolt.getDamageDaler());
    }

    @Override
    public void Die() {
        super.Die();
        finish = false;
    }
}