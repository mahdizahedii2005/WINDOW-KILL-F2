package Game.game.model.Move;

import java.util.ArrayList;

public interface Movable  {
    ArrayList<Movable> MOVE_ABLE = new ArrayList<>();
    void move();
}
