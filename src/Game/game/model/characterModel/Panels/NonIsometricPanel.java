package Game.game.model.characterModel.Panels;

import Game.game.Contoroler.control.Update;
import Game.game.model.characterModel.Enemy.Omenoct;
import Game.game.model.characterModel.ObjectInGame;
import Game.game.model.characterModel.epsilonFriend.bolt;
import Game.game.model.closeFRame.CLOSEABLE;
import Game.game.model.collision.Collidable;
import Game.game.model.shooting.shootGiver;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static Game.Data.constants.*;
import static Game.game.Contoroler.control.Controller.isItInside;
import static Game.game.model.Utils.toCoordinate;

public class NonIsometricPanel extends PanelInGame implements CLOSEABLE, shootGiver {
    public NonIsometricPanel(double x, double y, double height, double width, boolean isSolb, double speed) {
        super(x, y, height, width, isSolb, speed);
    }

    //    enum wallSide {
//        RIGHT {
//            @Override
//            void reduce(double RANGE_OF_INCREASE_PLACE, double moveRange) {
//                setWidth(getWidth() - RANGE_OF_INCREASE_PLACE);
//                setX(getX() - RANGE_OF_INCREASE_PLACE - moveRange);
//            }
//
//            @Override
//            void increase(double RANGE_OF_INCREASE_PLACE, double moveRange) {
//
//            }
//        }, LEFT, DOWN, UP
//
//        abstract void reduce(double RANGE_OF_INCREASE_PLACE, double moveRange);
//
//        abstract void increase(double RANGE_OF_INCREASE_PLACE, double moveRange);
////        }
//    public NonIsometricPanel(double x, double y, double height, double width) {
//        super(x, y, height, width);
//        collidables.add(this);
//    }
//
//    public void reduceLeft(double RANGE_OF_INCREASE_PLACE, double moveRange) {
//        setWidth(getWidth() - RANGE_OF_INCREASE_PLACE);
//        setX(getX() + RANGE_OF_INCREASE_PLACE + moveRange);
//    }
//
//    public void reduceRight(double RANGE_OF_INCREASE_PLACE, double moveRange) {
//        setWidth(getWidth() - RANGE_OF_INCREASE_PLACE);
//        setX(getX() - RANGE_OF_INCREASE_PLACE - moveRange);
//    }
//
//    public void reduceUp(double RANGE_OF_INCREASE_PLACE, double moveRange) {
//        setHeight(getHeight() - RANGE_OF_INCREASE_PLACE);
//        setY(getY() + RANGE_OF_INCREASE_PLACE + moveRange);
//    }
//
//    public void reduceDown(double RANGE_OF_INCREASE_PLACE, double moveRange) {
//        setHeight(getHeight() - RANGE_OF_INCREASE_PLACE);
//        setY(getY() - RANGE_OF_INCREASE_PLACE - moveRange);
//    }
//
//    public void reduceDown() {
//        reduceDown(RANGE_OF_DECREASE_PLACE, MOVE_RANGE);
//    }
//
//    public void reduceUp() {
//        reduceUp(RANGE_OF_DECREASE_PLACE, MOVE_RANGE);
//    }
//
//    public void reduceRight() {
//        reduceRight(RANGE_OF_DECREASE_PLACE, MOVE_RANGE);
//    }
//
//    public void reduceLeft() {
//        reduceLeft(RANGE_OF_DECREASE_PLACE, MOVE_RANGE);
//    }
    private Omenoct checkOmenoctDamage(side side) {
        for (ObjectInGame o : ObjectInGame.objectInGames) {
            if (o instanceof Omenoct) {
                Omenoct omenoct = (Omenoct) o;
                if (omenoct.isItFix && omenoct.side.equals(side) && isItInside(omenoct, this)) {
                    return omenoct;
                }
            }
        }
        return null;
    }

    public Line2D addLineX(Line2D line2D, double range) {
        return new Line2D.Double(line2D.getX1() + range, line2D.getY1(), line2D.getX2() + range, line2D.getY2());
    }

    public Line2D addLineY(Line2D line2D, double range) {
        return new Line2D.Double(line2D.getX1(), line2D.getY1() + range, line2D.getX2(), line2D.getY2() + range);
    }

    public void IncreaseLeft(double RANGE_OF_INCREASE_PLACE) {
        if (validLine(addLineX(getLeftPanel(), -1 * RANGE_OF_INCREASE_PLACE), this)) {
            setWidth(getWidth() + RANGE_OF_INCREASE_PLACE);
            setX(getX() - RANGE_OF_INCREASE_PLACE);
        }
    }


    public void IncreaseRight(double RANGE_OF_INCREASE_PLACE) {
        if (validLine(addLineX(getRightPanel(), RANGE_OF_INCREASE_PLACE), this))
            setWidth(getWidth() + RANGE_OF_INCREASE_PLACE);
    }

    public void IncreaseUp(double RANGE_OF_INCREASE_PLACE) {
        if (validLine(addLineY(getUpPanel(), -1 * RANGE_OF_INCREASE_PLACE), this)) {
            setHeight(getHeight() + RANGE_OF_INCREASE_PLACE);
            setY(getY() - RANGE_OF_INCREASE_PLACE);
        }
    }

    public void IncreaseDown(double RANGE_OF_INCREASE_PLACE) {
        if (validLine(addLineY(getDownPanel(), RANGE_OF_INCREASE_PLACE), this))
            setHeight(getHeight() + RANGE_OF_INCREASE_PLACE);
    }

    public void IncreaseLeft() {
        IncreaseLeft(RANGE_OF_INCREASE_PLACE);
    }

    public void IncreaseRight() {
        IncreaseRight(RANGE_OF_INCREASE_PLACE);
    }

    public void IncreaseUp() {
        IncreaseUp(RANGE_OF_INCREASE_PLACE);
    }

    public void IncreaseDown() {
        IncreaseDown(RANGE_OF_INCREASE_PLACE);
    }

    public boolean validWidth(double deC) {
        return getSize().width - deC > SMALLEST_SIZE_OF_ORIGINAL_PANEL.width;
    }

    public boolean validHeight(double deC) {
        return getSize().height - deC > SMALLEST_SIZE_OF_ORIGINAL_PANEL.height;
    }

    public boolean validHeight() {
        if (validHeight(RANGE_OF_DECREASE_PLACE * 2)) {
            return true;
        } else {
            if (first) {
                DELAY_OF_CLOSE_FRAME *= ZARIB_INCREASE_TIME_OF_CLOSE_PANEL;
                first = false;
            }
            return false;
        }
    }

    boolean first = true;

    public boolean validWidth() {
        return validWidth(RANGE_OF_DECREASE_PLACE * 2);
    }

    @Override
    public float getMaxR() {
        return 0;
    }

    @Override
    public void createGeometry() {
        geometry = new GeometryFactory().createLineString(new Coordinate[]{
                new Coordinate(getX(), getY()),
                new Coordinate(getX() + getWidth(), getY()),
                new Coordinate(getX() + getWidth(), getY() + getHeight()),
                new Coordinate(getX(), getY() + getHeight()),
                new Coordinate(getX(), getY())});
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
        return 0;
    }

    @Override
    public Point2D getAnchor() {
        return null;
    }

    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public boolean collide(Collidable collidable) {
        return true;
    }

    @Override
    public void play(Collidable collidable) {

    }

    @Override
    public side findCollision(Point2D anchor) {
        var right = getRightPanel().ptLineDist(anchor);
        var left = getLeftPanel().ptLineDist(anchor);
        var down = getDownPanel().ptLineDist(anchor);
        var up = getUpPanel().ptLineDist(anchor);
        var min = Math.min(Math.min(Math.min(right, left), down), up);
        if (min == right) return side.right;
        if (min == left) return side.left;
        if (min == down) return side.down;
        if (min == up) return side.up;
        return null;
    }

    public boolean validLine(Line2D line2D, PanelInGame notThisPanel) {
        for (int i = 0; i < PANELS.size(); i++) {
            PanelInGame panel = PANELS.get(i);

            if (panel != notThisPanel) {
                panel.createGeometry();
                if (panel.isSolb) {
                    if (panel.checkCollision(new Collidable() {
                        private Geometry geometry;

                        @Override
                        public float getMaxR() {
                            return 0;
                        }

                        @Override
                        public void createGeometry() {
                            ArrayList<Point2D.Double> vertices = new ArrayList<>();
                            vertices.add(new Point2D.Double(line2D.getX1(), line2D.getY1()));
                            vertices.add(new Point2D.Double(line2D.getX2(), line2D.getY2()));
                            if (vertices.size() != 0) {
                                Coordinate[] coordinates = new Coordinate[vertices.size() + 1];
                                for (int i = 0; i < vertices.size(); i++)
                                    coordinates[i] = toCoordinate(vertices.get(i));
                                coordinates[vertices.size()] = toCoordinate(vertices.get(0));
                                geometry = new GeometryFactory().createLineString(coordinates);
                            }
                        }

                        @Override
                        public Geometry getGeometry() {
                            createGeometry();
                            return geometry;
                        }

                        @Override
                        public boolean isCircular() {
                            return false;
                        }

                        @Override
                        public float getRadius() {
                            return 0;
                        }

                        @Override
                        public Point2D getAnchor() {
                            return null;
                        }

                        @Override
                        public float getSpeed() {
                            return 0;
                        }

                        @Override
                        public boolean collide(Collidable collidable) {
                            return true;
                        }

                        @Override
                        public void play(Collidable collidable) {

                        }
                    }).isCollision()) return false;
                }
            }
        }
        return true;
    }

    @Override
    public void reduceFrame() {
        if (validHeight()) {
            if (validLine(getDownPanel(), this)) reduceDown();
            if (validLine(getUpPanel(), this)) reduceUp();
        }
        if (validWidth()) {
            if (validLine(getLeftPanel(), this)) reduceLeft();
            if (validLine(getRightPanel(), this)) reduceRight();
        }
    }

    ArrayList<bolt> prosec = new ArrayList<>();

    @Override
    public void takingShot(bolt bolt) {
        Update.wait = true;
        prosec.add(bolt);
        switch (findCollision(bolt.getAnchor())) {
            case right -> {
                IncreaseRight();
                var o = checkOmenoctDamage(side.right);
                if (o != null) o.getHit(5);
            }
            case left -> {
                IncreaseLeft();
                var o = checkOmenoctDamage(side.left);
                if (o != null) o.getHit(5);
            }
            case up -> {
                IncreaseUp();
                var o = checkOmenoctDamage(side.up);
                if (o != null) o.getHit(5);
            }
            case down -> {
                IncreaseDown();
                var o = checkOmenoctDamage(side.down);
                if (o != null) o.getHit(5);
            }
            case null -> {
                return;
            }
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    prosec.remove(bolt);
                    imFinish();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void imFinish() {
        if (prosec.isEmpty()) {
            synchronized (Update.CLOSE_FRAME) {
                Update.wait = false;
                Update.CLOSE_FRAME.notifyAll();
            }
        }
    }
}
