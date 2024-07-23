package Game.game.model.characterModel.Enemy;

import Game.Data.constants;
import Game.game.Contoroler.building.Spawn;
import Game.game.model.Move.Direction;
import Game.game.model.Move.NormalFollower;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.view.characterView.DrawAbleObject;

import java.awt.*;
import java.awt.geom.Point2D;

import static Game.Data.constants.TRIANGLE_SAFE_DISTANCE;
import static Game.game.Contoroler.control.Controller.fixThePoint;
import static Game.game.Contoroler.control.Controller.makeNonCircularView;

public class TrigorathModel extends moveAbleEnemy implements NormalFollower {
    public TrigorathModel(Point2D.Double center, Color color, double[] xPoint, double[] yPoint) {
        super(center, color, 3, xPoint, yPoint, 15, 6, ((float) constants.HEIGHT_OF_TRIANGLE * 2) / 3);
        Spawn.NUMBER_OF_TRIG_ENEMY++;
    }

    @Override
    public void Die() {
        super.Die();
        Spawn.NUMBER_OF_TRIG_ENEMY--;
    }

    @Override
    public void follow() {
        if (impactNum > 0) {
            impactNum = Math.max(0, impactNum - 1);
            speed = Epsilon.getEpsilon().getSpeed();
            if (center.distance(Epsilon.getEpsilon().getCenter()) > TRIANGLE_SAFE_DISTANCE) {
                setSpeed(speed * 4d / 4d);
            } else {
                setSpeed(speed * 1d / 4d);
            }
            return;
        }
        setDirection(new Direction(new Point2D.Double((Epsilon.getEpsilon().getCenter().getX() - getCenter().getX()), (Epsilon.getEpsilon().getCenter().getY() - getCenter().getY()))));
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public DrawAbleObject getDrawAbleObject(PanelInGame panel) {
        return makeNonCircularView(fixThePoint(vertices, panel), color);
    }

}
