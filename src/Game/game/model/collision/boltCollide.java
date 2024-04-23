package Game.game.model.collision;

import Game.game.model.characterModel.originalPanel;
import Game.game.model.shooting.shootGiver;

public interface boltCollide extends Collidable {
    void boltCollide (shootGiver a);

    void wallCollision (originalPanel.panelWall w);

}
