package Game.game.model.Move;

import Game.game.model.characterModel.epsilonFriend.Epsilon;

import java.awt.geom.Point2D;

public interface linearMotion extends Movable{

    void move(Direction direction, double speed);

    void move() ;

    void setDirection(Direction direction);

    void setSpeed(double speed);

    Point2D getAnchor();
}
