package Game.game.model.characterModel;

import Game.game.model.Move.Movable;
import Game.game.model.Move.impactAble;
import Game.game.model.Move.linearMotion;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.closeFRame.CLOSEABLE;
import Game.game.model.collision.Collidable;
import Game.game.model.shooting.Aoe;

import static Game.game.model.characterModel.ObjectInGame.objectInGames;
import static Game.game.model.characterModel.Panels.PanelInGame.PANELS;
import static Game.game.model.shooting.Aoe.AOES;

public class ThingsInGame {
    protected String id;

    public ThingsInGame(String id) {
        this.id = id;
        if (this instanceof impactAble) impactAble.impactAblesList.add((impactAble) this);
        if (this instanceof Collidable) Collidable.collidables.add((Collidable) this);
        if (this instanceof Movable) Movable.MOVE_ABLE.add((Movable) this);
        if (this instanceof CLOSEABLE) CLOSEABLE.CLOSEABLES.add((CLOSEABLE) this);
        if (this instanceof ObjectInGame) objectInGames.add((ObjectInGame) this);
        if (this instanceof Aoe) AOES.add((Aoe) this);
    }


    public String getId() {
        return id;
    }

    public void Die() {
        if (this instanceof ObjectInGame) objectInGames.remove((ObjectInGame) this);
        if (this instanceof impactAble) impactAble.impactAblesList.remove((impactAble) this);
        if (this instanceof linearMotion) linearMotion.MOVE_ABLE.remove((linearMotion) this);
        if (this instanceof Collidable) Collidable.collidables.remove((Collidable) this);
        if (this instanceof PanelInGame) PANELS.remove((PanelInGame) this);
        if (this instanceof CLOSEABLE) CLOSEABLE.CLOSEABLES.remove((CLOSEABLE) this);
        if (this instanceof Aoe) AOES.remove((Aoe) this);
    }
}
