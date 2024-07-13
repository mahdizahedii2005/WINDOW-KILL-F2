package Game.game.model.characterModel;

import Game.game.model.Move.impactAble;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.collision.Collidable;
import Game.game.view.characterView.DrawAbleObject;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static Game.game.model.Utils.toCoordinate;

public abstract class ObjectInGame extends ThingsInGame {
    protected double HP;
    public static ArrayList<ObjectInGame> objectInGames = new ArrayList<>();
    protected Point2D.Double center;
    protected ArrayList<Point2D> vertices = new ArrayList<>();
    protected Color color;
    protected Double radius;
    private boolean isCirClr = false;
    private Geometry geometry;
    private float maxRadius;
    private int damageDaler;

    public ObjectInGame(Point2D.Double center, Color color, String id, int hp, double radius, boolean isCirClr, int damageDaler) {
        super(id);
        this.damageDaler = damageDaler;
        this.maxRadius = (float) radius;
        this.isCirClr = isCirClr;
        this.color = color;
        this.center = center;
        this.HP = hp;
        this.radius = radius;
        objectInGames.add(this);
        if (this instanceof impactAble) {
            impactAble.impactAblesList.add((impactAble) this);
        }
        if (this instanceof Collidable) {
            Collidable.collidables.add((Collidable) this);
        }
    }

    public ObjectInGame(Point2D.Double center, Color color, String id, int hp, float maxRadius, int damageDaler) {
        super(id);
        this.damageDaler = damageDaler;
        this.maxRadius = maxRadius;
        this.color = color;
        this.center = center;
        this.HP = hp;
        objectInGames.add(this);
        if (this instanceof impactAble) {
            impactAble.impactAblesList.add((impactAble) this);
        }
        if (this instanceof Collidable) {
            Collidable.collidables.add((Collidable) this);
        }
    }

    public abstract DrawAbleObject getDrawAbleObject(PanelInGame panel);

    public void CreateGeometry() {
        if (vertices.size() != 0) {
            Coordinate[] coordinates = new Coordinate[vertices.size() + 1];
            for (int i = 0; i < vertices.size(); i++) coordinates[i] = toCoordinate(vertices.get(i));
            coordinates[vertices.size()] = toCoordinate(vertices.get(0));
            geometry = new GeometryFactory().createLineString(coordinates);
        } else geometry = new GeometryFactory().createLineString(new Coordinate[0]);
    }

    public void getHit(int damage) {
        setHP(Math.max(0, getHP() - damage));
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point2D.Double getCenter() {
        return center;
    }

    public void setCenter(Point2D.Double center) {
        this.center = center;
    }

    public double getHP() {
        return HP;
    }

    public float getRadius() {
        return (float) ((double) radius);
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public void setHP(double HP) {
        this.HP = Math.max(HP, 0);
    }

    public ArrayList<Point2D> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Point2D> vertices) {
        this.vertices = vertices;
    }

    public boolean isCirClr() {
        return isCirClr;
    }

    public void setCirClr(boolean cirClr) {
        isCirClr = cirClr;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public float getMaxR() {
        return maxRadius;
    }

    public float getMaxRadius() {
        return maxRadius;
    }

    public int getDamageDaler() {
        return damageDaler;
    }

}
