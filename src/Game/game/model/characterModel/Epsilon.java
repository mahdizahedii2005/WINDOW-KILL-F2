package Game.game.model.characterModel;

import Game.game.model.Move.Moveable;
import Game.game.model.collision.colliAble;

import java.awt.*;
import java.awt.geom.Point2D;

public class Epsilon extends ObjectInGame implements colliAble, Moveable {
    double radius;
    public Epsilon(Point2D.Double center,String id, int radius) {
        super(center,null,id);
        this.radius = radius;
    }
}
