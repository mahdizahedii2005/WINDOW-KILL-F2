package Game.game.model.characterModel;

import Game.game.model.Move.Moveable;
import Game.game.model.collision.colliAble;

import java.awt.*;
import java.awt.geom.Point2D;

public class Enemy extends ObjectInGame implements colliAble, Moveable {
    double[] xPoint;
    double[] yPoint;
    int nPoint;

    public Enemy(Point2D.Double center, Color color,String id, int nPoint, double[] xPoint, double[] yPoint) {
        super(center,color,id);
        this.nPoint = nPoint;
        this.xPoint = xPoint;
        this.yPoint = yPoint;
    }
}
