package Game.game.model.characterModel;

import Game.game.model.Move.Moveable;
import Game.game.model.Move.impactAble;
import Game.game.model.collision.Collidable;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static Game.game.Contoroler.Controller.findObjectView;

public class ObjectInGame {
    protected double HP;
    public static ArrayList<ObjectInGame> objectInGames = new ArrayList<> ();
    Point2D.Double center;
    Color color;
    String id;
    protected Double radius;

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

    public ObjectInGame (Point2D.Double center, Color color, String id, int hp, double radius) {
        this.id = id;
        this.color = color;
        this.center = center;
        HP = hp;
        this.radius = radius;
        objectInGames.add (this);
        if (this instanceof impactAble) {
            impactAble.impactAblesList.add ((impactAble) this);
        }
        if (this instanceof Collidable) {
            Collidable.collidables.add ((Collidable) this);
        }
    }

    public Point2D.Double getCenter () {
        return center;
    }

    public void setCenter (Point2D.Double center) {
        this.center = center;
    }

    public void Die () {
        if (this instanceof impactAble) {
            impactAble.impactAblesList.remove (this);
        }
        if (findObjectView (id) != null) {
            findObjectView (id).clean ();
        }
        objectInGames.remove (this);
        if (this instanceof Moveable) {
            Moveable.moveAble.remove (this);
        }
        if (this instanceof Collidable) {
            Collidable.collidables.remove ((Collidable) this);
        }
    }

    public double getHP () {
        return HP;
    }

    public Double getRadius () {
        return radius;
    }

    public void setRadius (Double radius) {
        this.radius = radius;
    }

    public void setHP (double HP) {
        this.HP = Math.max (HP, 0);
    }
}
