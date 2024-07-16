package Game.game.model.characterModel;

import Game.game.model.Move.Moveable;
import Game.game.model.Move.impactAble;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.closeFRame.CLOSEABLE;
import Game.game.model.collision.Collidable;

import static Game.game.model.characterModel.ObjectInGame.objectInGames;
import static Game.game.model.characterModel.Panels.PanelInGame.PANELS;

public class ThingsInGame {
    protected String id;

    public ThingsInGame(String id) {
        this.id = id;
        if (this instanceof impactAble) impactAble.impactAblesList.add((impactAble) this);
        if (this instanceof Collidable) Collidable.collidables.add((Collidable) this);
        if (this instanceof Moveable) Moveable.moveAble.add((Moveable) this);
        if (this instanceof CLOSEABLE) CLOSEABLE.CLOSEABLES.add((CLOSEABLE) this);
        if (this instanceof impactAble)impactAble.impactAblesList.add((impactAble) this);
    }


    public String getId() {
        return id;
    }

    public void Die() {
        if (this instanceof ObjectInGame) objectInGames.remove((ObjectInGame) this);
        if (this instanceof impactAble) impactAble.impactAblesList.remove((impactAble) this);
        if (this instanceof Moveable) Moveable.moveAble.remove((Moveable) this);
        if (this instanceof Collidable) Collidable.collidables.remove((Collidable) this);
        if (this instanceof PanelInGame) PANELS.remove((PanelInGame) this);
        if (this instanceof CLOSEABLE) CLOSEABLE.CLOSEABLES.remove((CLOSEABLE) this);
    }
}
