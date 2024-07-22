package Game.game.model.characterModel.Enemy;

import Game.game.Contoroler.building.BuilderHelper;
import Game.game.Contoroler.building.MokhtasatPoint;
import Game.game.Contoroler.building.Spawn;
import Game.game.Contoroler.player.soundPlayer;
import Game.game.model.Move.Direction;
import Game.game.model.Move.follower;
import Game.game.model.Move.impactAble;
import Game.game.model.characterModel.*;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.model.characterModel.epsilonFriend.bolt;
import Game.game.model.characterModel.epsilonFriend.prize;
import Game.game.model.collision.Collidable;
import Game.game.model.collision.CollisionState;
import Game.game.model.shooting.shootGiver;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.UUID;

import static Game.Data.constants.*;
import static Game.helper.addVectors;
import static Game.helper.checkNan;

public abstract class moveAbleEnemy extends enemy implements Collidable,follower, shootGiver, impactAble {

    int impactNum = 0;
    double speed = Epsilon.getEpsilon().getSpeed()/2;

    protected Direction MoveDirection = new Direction(new Point2D.Double(0, 0));

    private static int numberofenemy = 0;

    public moveAbleEnemy(Point2D.Double center, Color color, int nPoint, double[] xPoint, double[] yPoint, int hp, int damageDaler, float maxR ) {
        super(center, color, UUID.randomUUID().toString(), hp,maxR,damageDaler);
        if (checkNan(xPoint, yPoint)) {
            numberofenemy++;
            if (numberofenemy % 4 == 0) {
                soundPlayer.play("src\\sources\\song\\boom-1 (1).wav");
            }
            ArrayList<Point2D> point2DS = new ArrayList<>();
            for (int i = 0; i < nPoint; i++) {
                point2DS.add(new Point2D.Double(xPoint[i], yPoint[i]));
            }
            setVertices(point2DS);
        } else {
            ObjectInGame.objectInGames.remove(this);
            if (this instanceof TrigorathModel) {
                BuilderHelper.trigorathBuilder();
            } else if (this instanceof SquarantineModel) {
                BuilderHelper.squarantineBuilder();
            }
        }
    }

    @Override
    public void move(Direction direction, double speed) {
        MokhtasatPoint movement = addVectors(vertices, center, MoveDirection.getDirectionVector(), speed);
        this.vertices = movement.getArrayList();
        this.center = movement.getCenter();
    }

    @Override
    public void move() {
        move(MoveDirection, speed);
    }

    @Override
    public void setDirection(Direction direction) {
        this.MoveDirection = direction;
    }

    @Override
    public int getImpactNum() {
        return impactNum;
    }

    public void setImpactNum(int impactNum) {
        this.impactNum = impactNum;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
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
        return new Point2D.Float((float) center.x,(float) center.y);
    }
    @Override
    public float getSpeed() {
        return (float) speed;
    }

    @Override
    public boolean collide(Collidable collidable) {
        if (collidable instanceof PanelInGame)return false;
        return true;
    }

    @Override
    public void play(Collidable collidable) {
        if (collidable instanceof Epsilon){
            Epsilon.getEpsilon().getHit(getDamageDaler());
        }
    }

    @Override
    public float getRadius() {
        return super.getRadius();
    }

    public static int i = 0;

    @Override
    public void Die() {
        new prize(new Point2D.Double(center.getX(), center.getY()), color, this instanceof TrigorathModel ? 5 : 10);
        super.Die();
        if (this instanceof SquarantineModel) {
            Spawn.getSpawn().DecreaseSQNumEnemy();
        } else {
            Spawn.getSpawn().DecreaseTRNumEnemy();
        }
        soundPlayer.play(i % 2 == 0 ? ENEMY_DESTROID_SOUND_PATH1 : ENEMY_DESTROID_SOUND_PATH2);
        i++;
        // TODO: ۱۴/۰۴/۲۰۲۴ play music
    }

    @Override
    public void takingShot(bolt bolt) {
        HP = Math.max(0, HP - (ZaribOfEpsilonDamage * 5));
        if (HP == 0) {
            Die();
        }
    }

    @Override
    public Direction getMoveDirection() {
        return MoveDirection;
    }

    @Override
    public void setMoveDirection(Direction moveDirection) {
        MoveDirection = moveDirection;
    }
}
