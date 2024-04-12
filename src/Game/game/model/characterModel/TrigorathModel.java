package Game.game.model.characterModel;

import java.awt.*;
import java.awt.geom.Point2D;

public class TrigorathModel extends Enemy {
    public TrigorathModel(Point2D.Double center, Color color, double[] xPoint, double[] yPoint) {
        super(center, color, 3, xPoint, yPoint);
    }
}
