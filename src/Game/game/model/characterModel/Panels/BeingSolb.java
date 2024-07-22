package Game.game.model.characterModel.Panels;

import Game.game.model.Move.linearMotion;
import Game.game.model.collision.Collidable;

public interface BeingSolb extends linearMotion, Collidable {
    boolean getIsSolb();
}
