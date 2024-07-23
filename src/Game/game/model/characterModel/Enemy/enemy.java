package Game.game.model.characterModel.Enemy;

import Game.game.model.characterModel.ObjectInGame;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.model.collision.Collidable;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class enemy extends ObjectInGame implements Collidable {
    public static boolean HEEL_THE_EPSILON = false;

    @Override
    public void getHit(int damage) {
        if (HEEL_THE_EPSILON) Epsilon.getEpsilon().setHP(Epsilon.getEpsilon().getHP() + 3);
        super.getHit(damage);
    }

    public enemy(Point2D.Double center, Color color, String id, int hp, float maxRadius, int damageDaler) {
        super(center, color, id, hp, maxRadius, damageDaler);
    }
    public enemy(Point2D.Double center, Color color, String id, int hp, int damageDaler,double r) {
        super(center, color, id, hp,r, true, damageDaler);
    }

}
