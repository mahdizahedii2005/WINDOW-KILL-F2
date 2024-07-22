package Game.game.model.characterModel.Enemy;

import Game.Data.constants;
import Game.game.model.Move.Direction;
import Game.game.model.Move.NormalFollower;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.view.characterView.DrawAbleObject;
import Game.game.view.characterView.shape.nonCircular;

import java.awt.geom.Point2D;
import java.util.Random;

import static Game.Data.constants.SPEED;
import static Game.Data.constants.SQUAER_COLOR;
import static Game.game.Contoroler.control.Controller.fixThePoint;

public class SquarantineModel extends moveAbleEnemy implements NormalFollower {
    private int cycle = 0;
    private boolean isPower = false;

    public SquarantineModel(Point2D.Double center, double[] xPoint, double[] yPoint) {
        super(center, SQUAER_COLOR, 4, xPoint, yPoint, 10, 10, (float) (constants.SIDE_OF_SQUARE) / (float) Math.sqrt(2));
    }

    @Override
    public void follow() {
        speed = SPEED;
        if (new Random().nextInt(20) == 1 && !isPower) {
            isPower = true;
            cycle = 25;
            setSpeed(SPEED * 2);
        }
        if (isPower) {
            cycle--;
            if (cycle == 0) {
                isPower = false;
                setSpeed(SPEED * 3d / 10d);
            }
        }
        if (impactNum > 0) {
            impactNum = Math.max(impactNum - 1, 0);
            return;
        }
        setDirection(new Direction(new Point2D.Double((Epsilon.getEpsilon().getCenter().getX() - getCenter().getX()), (Epsilon.getEpsilon().getCenter().getY() - getCenter().getY()))));
    }

    @Override
    public DrawAbleObject getDrawAbleObject(PanelInGame panel) {
        return new nonCircular(fixThePoint(vertices, panel), color);
    }
}
