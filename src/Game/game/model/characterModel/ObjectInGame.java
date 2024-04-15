package Game.game.model.characterModel;

import Game.game.model.Move.Moveable;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static Game.game.Contoroler.Controller.findObjectView;

public class ObjectInGame {
    protected int HP;
    public static ArrayList<ObjectInGame> objectInGames = new ArrayList<> ();
    Point2D.Double center;
    Color color;
    String id;

    public Color getColor () {
        return color;
    }

    public void setColor (Color color) {
        this.color = color;
    }

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public ObjectInGame (Point2D.Double center, Color color, String id, int hp) {
        this.id = id;
        this.color = color;
        this.center = center;
        HP = hp;
        objectInGames.add (this);
    }

    public Point2D.Double getCenter () {
        return center;
    }

    public void setCenter (Point2D.Double center) {
        this.center = center;
    }

    public void Die () {
        if (findObjectView (id) != null) {
            findObjectView (id).clean ();
        }
        objectInGames.remove (this);
        if (this instanceof Moveable)
            Moveable.moveAble.remove (this);
    }

    public int getHP () {
        return HP;
    }

}
