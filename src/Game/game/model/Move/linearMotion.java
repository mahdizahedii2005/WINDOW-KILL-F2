package Game.game.model.Move;

import java.util.ArrayList;

public interface linearMotion extends Movable{

    void move(Direction direction, double speed);

    void move();

    void setDirection(Direction direction);

    void setSpeed(double speed);
}
