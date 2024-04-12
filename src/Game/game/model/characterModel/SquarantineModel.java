package Game.game.model.characterModel;

import java.awt.*;
import java.awt.geom.Point2D;

public class SquarantineModel extends Enemy {

    public SquarantineModel(Point2D.Double center, Color color, double[] xPoint, double[] yPoint) {
        super(center, color, 4, xPoint, yPoint);
    }
}
