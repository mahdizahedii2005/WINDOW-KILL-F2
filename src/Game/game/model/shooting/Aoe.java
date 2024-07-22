package Game.game.model.shooting;

import Game.game.model.characterModel.ObjectInGame;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static Game.game.model.characterModel.ObjectInGame.objectInGames;

public interface Aoe {
    ArrayList<Aoe> AOES = new ArrayList<>();

    boolean checkIsOnCoolDown(String id);

    void addToCoolDownList(String id);

    void removeFromCoolDownList(String id);

    default void delADamage(ObjectInGame object) {
        if (!checkIsOnCoolDown(object.getId())) {
            object.getHit(getDamage());
            addToCoolDownList(object.getId());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    removeFromCoolDownList(object.getId());
                }
            }).start();
        }
    }
    default boolean isGeometryContained(Geometry geom1, Geometry geom2) {
        // بررسی اینکه آیا تمام نقاط geom1 در داخل geom2 قرار دارند
        for (Coordinate coord : geom1.getCoordinates()) {
            if (!isPointInPolygon(coord, geom2)) {
                return false;
            }
        }
        return true;
    }

    default boolean isPointInPolygon(Coordinate point, Geometry polygon) {
        int n = polygon.getCoordinates().length;
        boolean result = false;
        for (int i = 0, j = n - 1; i < n; j = i++) {
            if ((polygon.getCoordinates()[i].y > point.y) != (polygon.getCoordinates()[j].y > point.y) &&
                    (point.x < (polygon.getCoordinates()[j].x - polygon.getCoordinates()[i].x) * (point.y - polygon.getCoordinates()[i].y) / (polygon.getCoordinates()[j].y - polygon.getCoordinates()[i].y) + polygon.getCoordinates()[i].x)) {
                result = !result;
            }
        }
        return result;
    }

    default void TotalDelDamage() {
        for (int i = 0; i < objectInGames.size(); i++) {
            ObjectInGame object = objectInGames.get(i);
            for (int j = 0; j < AOES.size(); j++) {
                Aoe aoe = AOES.get(j);
                    try {
                        if (isGeometryContained(object.getGeometry(),((ObjectInGame) aoe).getGeometry())&&!(object instanceof Aoe)) {
                            delADamage(object);
                            break;
                        }
                    } catch (NullPointerException e) {
                        continue;
//                    if (object.isCirClr()) {
//                        if (aoe.isInsideAoe(object.getCenter())) {
//                            delADamage(object);
//                            break;
//                        }
//                    } else {
//                        boolean isInside = true;
//                        for (Point2D point2D : object.getVertices()) {
//                            if (!aoe.isInsideAoe(point2D)) isInside = false;
//                        }
//                        if (isInside) {
//                            delADamage(object);
//                            break;
//                        }
//                    }
                }
            }
        }
    }

    double getMAXRadius();

    int getDamage();

    int getX();

    int getY();

    int getWidth();

    int getHeight();

    double getRadiusX();

    double getRadiusY();

    Point2D.Double getAnchor();

//    boolean isSqe();
//
//    default boolean isInsideAoe(Point2D point) {
//        if (isSqe()) {
//            return point.getX() < getX() + getWidth() &&
//                    point.getX() > getX() &&
//                    point.getY() > getY() &&
//                    point.getY() < getY() + getHeight();
//        } else {
//            Point2D.Double newPoint = new Point2D.Double(point.getX() - getAnchor().getX(), point.getY() - getAnchor().getY());
//            return ((newPoint.getX() * newPoint.getX()) / (getRadiusX() * getRadiusX()) + (newPoint.getY() * newPoint.getY()) / (getRadiusY() * getRadiusY())) <= 1;
//        }
//    }
}
