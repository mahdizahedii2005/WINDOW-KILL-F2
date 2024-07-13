package Game.game.model.collision;

import Game.game.model.characterModel.Panels.rigidPanel;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineSegment;
import org.locationtech.jts.operation.distance.DistanceOp;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static Game.game.Contoroler.control.Controller.Point2DToFlout;
import static Game.game.model.Utils.*;

public interface Collidable {
    ArrayList<Collidable> collidables = new ArrayList<>();

    //    default boolean collision(Collidable col) {
//        ObjectInGame s = (ObjectInGame) (col);
//        if (this instanceof rasEpsilon.ras || col instanceof rasEpsilon.ras) {
//            if (this instanceof Enemy || col instanceof Enemy) {
//                return s.getCenter().distance(getCenter()) < s.getRadius() - 5;
//            }
//        }
//        if (this instanceof Epsilon || col instanceof Epsilon) {
//            if (this instanceof SquarantineModel || col instanceof SquarantineModel) {
//                return s.getCenter().distance(getCenter()) < s.getRadius() - 5;
//            }
//            return s.getCenter().distance(getCenter()) < s.getRadius() - 2;
//
//        }
//        return s.getCenter().distance(getCenter()) < s.getRadius() + 2;
//    }
//        if (col instanceof TrigorathModel) {
//            return HitTrigo ((TrigorathModel) col);
//        } else if (col instanceof SquarantineModel) {
//            return HitSqure ((SquarantineModel) col);
//        }else if (col instanceof Epsilon){
//        }
//    }
//    default Point2D.Double findCollisionPoint(Collidable collidable) {
//        return addVectors(multiplyVector(collidable.getCenter(), 1 / 2d), multiplyVector(getCenter(), 1 / 2d));
//    }
//
//    Point2D.Double getCenter();
//    private boolean HitSqure(SquarantineModel s) {
//        if (s.getCenter().distance(getCenter()) < s.getRadius() - 2) {
//            return true;
//        }
//        return false;
//    }
//    private boolean HitTrigo(TrigorathModel t) {
//        if (t.getCenter().distance(this.getCenter()) < t.getRadius() - 2) {
//            return true;
//        }
//        return false;
//    }
    static void CreateAllGeometries() {
        for (Collidable collidable : new ArrayList<>(Collidable.collidables)) collidable.createGeometry();
    }

    static Coordinate getClosestCoordinate(Coordinate anchor, Geometry geometry) {
        return DistanceOp.nearestPoints(geometry, new GeometryFactory().createLineString(new Coordinate[]{anchor, anchor}))[0];
    }

    float getMaxR();

    void createGeometry();

    Geometry getGeometry();

    boolean isCircular();

    float getRadius();

    Point2D getAnchor();

    float getSpeed();

    boolean collide(Collidable collidable);

    float COLLISION_SENSITIVITY = 0.8f;

    default CollisionState checkCollision(Collidable collidable) {
        if (!collide(collidable) || !collidable.collide(this)) return new CollisionState();
        Point2D collisionPoint = null;
        if (isCircular() && collidable.isCircular()) {
            if (getAnchor().distance(collidable.getAnchor()) <= (getRadius() + collidable.getRadius()) + COLLISION_SENSITIVITY) {
                collisionPoint = weightedAddPoints(getAnchor(), collidable.getAnchor(), collidable.getRadius(), getRadius());
            } else return new CollisionState();
        } else if (isCircular() && !collidable.isCircular()) {
            Coordinate closest = getClosestCoordinate(toCoordinate(getAnchor()), collidable.getGeometry());
            if (closest.distance(toCoordinate(getAnchor())) <= getRadius() + COLLISION_SENSITIVITY) {
                collisionPoint = toPoint(closest);
            } else return new CollisionState();
        } else if (!isCircular() && collidable.isCircular()) return collidable.checkCollision(this);
        else {
            Coordinate[] coordinates = DistanceOp.nearestPoints(getGeometry(), (collidable.getGeometry()));
            LineSegment segment = new LineSegment(coordinates[0], coordinates[1]);
            if (segment.getLength() <= COLLISION_SENSITIVITY) collisionPoint = toPoint(segment.midPoint());
            else return new CollisionState();
        }
        return new CollisionState(this, collidable, Point2DToFlout(collisionPoint));
    }

    void play(Collidable collidable);

    enum side {
        left, right, up, down
    }

    default side findCollision(Point2D anchor) {
        return null;
    }
}
