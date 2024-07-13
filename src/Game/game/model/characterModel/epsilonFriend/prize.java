package Game.game.model.characterModel.epsilonFriend;

import Game.game.Contoroler.building.Spawn;
import Game.game.model.characterModel.ObjectInGame;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.collision.Collidable;
import Game.game.model.collision.CollisionState;
import Game.game.view.characterView.DrawAbleObject;
import Game.game.view.characterView.shape.circleShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.UUID;

import static Game.Data.constants.PRIZE_RADIUS;
import static Game.Data.constants.PRIZE_TIME;
import static Game.game.Contoroler.control.Controller.fixThePoint;

public class prize extends ObjectInGame implements Collidable {
    int expValue;

    public prize(Point2D.Double center, Color color, int IncreaseHp) {
        super(center, color, UUID.randomUUID().toString(), 1, PRIZE_RADIUS, true, 5);
        this.expValue = IncreaseHp;
        switch (Spawn.spawnstate) {
            case first -> Epsilon.getEpsilon().increaseExp(expValue * 1.2);
            case second -> Epsilon.getEpsilon().increaseExp(expValue * 1.5);
            case third -> Epsilon.getEpsilon().increaseExp(expValue * 1.8);
        }
        new Timer(PRIZE_TIME, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!die) {
                    die = true;
                } else {
                    Die();
                    ((Timer) e.getSource()).stop();
                }
            }
        }).start();
    }

    boolean die = false;

    @Override
    public DrawAbleObject getDrawAbleObject(PanelInGame panel) {
        return new circleShape(fixThePoint(center, panel), color, radius);

    }

    @Override
    public void Die() {
        super.Die();
    }

    public void Action() {
        Epsilon.getEpsilon().increaseHp(expValue);
        Die();
    }

    @Override
    public void createGeometry() {
        super.CreateGeometry();
    }

    @Override
    public boolean isCircular() {
        return true;
    }

    @Override
    public Point2D getAnchor() {
        return new Point2D.Float((float) center.x,(float) center.y);
    }

    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public boolean collide(Collidable collidable) {
        if (collidable instanceof Epsilon) return true;
        return false;
    }

    @Override
    public CollisionState checkCollision(Collidable collidable) {
        return Collidable.super.checkCollision(collidable);
    }


    @Override
    public void play(Collidable collidable) {
        Epsilon.getEpsilon().increaseExp(expValue);
        Epsilon.getEpsilon().increaseHp(getDamageDaler());
        Die();
    }

    @Override
    public float getRadius() {
        return super.getRadius();
    }
}
