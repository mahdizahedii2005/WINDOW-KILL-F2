package Game.game.model.collision;

import Game.game.model.characterModel.prize;

public interface PrizeCollide extends Collidable{
    boolean prizeCollide (prize prize);
}
