package Game.game.model.characterModel.Panels;

import Game.game.model.collision.Collidable;
import org.locationtech.jts.geom.Geometry;

import java.awt.geom.Point2D;

public class isometricPanel extends PanelInGame {


    public isometricPanel(double x, double y, double height, double width, boolean isSolb, double speed) {
        super(x, y, height, width, isSolb, speed);
        this.isSolb = isSolb;
        this.speed = speed;
    }

    @Override
    public boolean getIsSolb() {
        return isSolb;
    }

    public void setSolb(boolean solb) {
        this.isSolb = solb;
    }

    @Override
    public float getMaxR() {
        return (float) Math.max(getWidth(), getHeight());
    }

    @Override
    public Geometry getGeometry() {
        return geometry;
    }

    @Override
    public boolean isCircular() {
        return false;
    }

    @Override
    public float getRadius() {
        return -1;
    }

    @Override
    public Point2D getAnchor() {
        return new Point2D.Double(getX(), getY());
    }

    @Override
    public float getSpeed() {
        return (float) speed;
    }

    @Override
    public boolean collide(Collidable collidable) {
        return true;
    }

    @Override
    public void play(Collidable collidable) {
        if (collidable instanceof PanelInGame && ((PanelInGame) collidable).getIsSolb() && isSolb) {
            setSpeed(0);
            ((PanelInGame) collidable).setSpeed(0);
        }
    }
}
