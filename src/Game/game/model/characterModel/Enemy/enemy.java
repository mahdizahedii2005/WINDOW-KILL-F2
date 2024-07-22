package Game.game.model.characterModel.Enemy;

import Game.game.model.characterModel.ObjectInGame;
import Game.game.model.collision.Collidable;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class enemy extends ObjectInGame implements Collidable {

    public enemy(Point2D.Double center, Color color, String id, int hp, float maxRadius, int damageDaler) {
        super(center, color, id, hp, maxRadius, damageDaler);
    }
    public enemy(Point2D.Double center, Color color, String id, int hp, int damageDaler,double r) {
        super(center, color, id, hp,r, true, damageDaler);
    }

}
