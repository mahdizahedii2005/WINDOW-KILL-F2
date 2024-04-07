package Game.game.model.characterModel;

import java.awt.*;
import java.awt.geom.Point2D;

public class ObjectInGame {
    Point2D.Double center;
    Color color;
    String id;
    public ObjectInGame(Point2D.Double center, Color color, String id) {
        this.id = id;
        this.color = color;
        this.center = center;
    }

    public Point2D.Double getCenter() {
        return center;
    }

    public void setCenter(Point2D.Double center) {
        this.center = center;
    }
}
