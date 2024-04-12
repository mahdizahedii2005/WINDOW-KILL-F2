package Game.game.model.characterModel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ObjectInGame {
    public static ArrayList<ObjectInGame> objectInGames = new ArrayList<>();
    Point2D.Double center;
    Color color;
    String id;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ObjectInGame(Point2D.Double center, Color color, String id) {
        this.id = id;
        this.color = color;
        this.center = center;
        objectInGames.add(this);
    }

    public Point2D.Double getCenter() {
        return center;
    }

    public void setCenter(Point2D.Double center) {
        this.center = center;
    }
}
