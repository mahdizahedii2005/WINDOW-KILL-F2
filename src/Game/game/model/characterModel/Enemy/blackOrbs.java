package Game.game.model.characterModel.Enemy;

import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.characterModel.Panels.isometricPanel;
import Game.game.model.characterModel.epsilonFriend.bolt;
import Game.game.model.collision.Collidable;
import Game.game.model.shooting.shootGiver;
import Game.game.view.characterView.DrawAbleObject;
import Game.game.view.characterView.shape.PhotoShape;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class blackOrbs {
    private static boolean blackOrbActive = false;
    private static blackOrbs instans = null;
    ArrayList<blackOrb> blackOrbs = new ArrayList<>();
    private final ArrayList<laser> laserArrayList = new ArrayList<>();

    public blackOrbs() {
        instans = this;
        blackOrbActive = true;
    }

    public static Game.game.model.characterModel.Enemy.blackOrbs getInstans() {
        return instans;
    }

    public void checkFinish() {
        if (blackOrbs.isEmpty()) {
            instans = null;
            blackOrbActive = false;
        }
    }

    public void creatBlackOrb(Point2D.Double center, int number, isometricPanel panel) {
        new blackOrb(center, number, panel);
    }

    public void start() throws InterruptedException {
        while (!blackOrbs.isEmpty()) {
            blackOrbActive = true;
            for (int i = 0; i < blackOrbs.size(); i++) {
                for (int j = i + 1; j < blackOrbs.size(); j++) {
                    laserArrayList.add(makeLaser(blackOrbs.get(i).getCenter(), blackOrbs.get(j).getCenter(), blackOrbs.get(i).getDamageDaler()));
                }
            }
            Thread.sleep(5000);
            blackOrbActive = false;
            for (int i = 0; i < laserArrayList.size(); ) {
                laserArrayList.remove(i).Die();
            }
            Thread.sleep(5000);
        }
    }

    public static laser makeLaser(Point2D.Double b1, Point2D.Double b2,int damage) {
        ArrayList<Point2D> arrayList = new ArrayList<>();
        double deltaX = b1.getX() - b2.getX();
        double deltaY = b1.getY() - b2.getY();
//        if (deltaX == 0) {
//            arrayList.add(new Point2D.Double(b1.getCenter().getX() + radiusLaser, b1.getCenter().getY()));
//            arrayList.add(new Point2D.Double(b1.getCenter().getX() + radiusLaser, b1.getCenter().getY()));
//            arrayList.add(new Point2D.Double(b1.getCenter().getX() + radiusLaser, b1.getCenter().getY()));
//            arrayList.add(new Point2D.Double(b1.getCenter().getX() + radiusLaser, b1.getCenter().getY()));
//        } else {
//            double shib = deltaY / deltaX;
//            shib = -1 / shib;
//            double arzAzMabda = b1.getCenter().getY() - shib * b1.getCenter().getX();
//            var a = 1 - (2 * b1.getCenter().getX()) + (shib * shib);
//            var b = 2 * shib * arzAzMabda - 2 * shib * b1.getCenter().getY();
//            var c = (b1.getCenter().getX() * b1.getCenter().getX()) + (b1.getCenter().getX() * b1.getCenter().getX())
//                    - (2 * b1.getCenter().getY() * arzAzMabda) - (radiusLaser * radiusLaser);
//            var x1 = (b + Math.sqrt((b * b) - (4 * a * c))) / (2 * c);
//            var x2 = (b - Math.sqrt((b * b) - (4 * a * c))) / (2 * c);
//            Point2D.Double p1 = new Point2D.Double(x1, shib * x1 + arzAzMabda);
//            Point2D.Double p2 = new Point2D.Double(x2, shib * x2 + arzAzMabda);
//            arzAzMabda = b2.getCenter().getY() - shib * b2.getCenter().getX();
//            a = 1 - (2 * b2.getCenter().getX()) + (shib * shib);
//            b = 2 * shib * arzAzMabda - 2 * shib * b2.getCenter().getY();
//            c = (b2.getCenter().getX() * b2.getCenter().getX()) + (b2.getCenter().getX() * b2.getCenter().getX())
//                    - (2 * b2.getCenter().getY() * arzAzMabda) - (radiusLaser * radiusLaser);
//            x1 = (b - Math.sqrt((b * b) - (4 * a * c))) / (2 * c);
//            x2 = (b + Math.sqrt((b * b) - (4 * a * c))) / (2 * c);
//            Point2D.Double p3 = new Point2D.Double(x1, shib * x1 + arzAzMabda);
//            Point2D.Double p4 = new Point2D.Double(x2, shib * x2 + arzAzMabda);
//            arrayList.add(p1);
//            arrayList.add(p2);
//            arrayList.add(p3);
//            arrayList.add(p4);
//        }
        arrayList.add(new Point2D.Double(b1.getX() + 15, b1.getY() + 15));
        arrayList.add(new Point2D.Double(b1.getX() - 15, b1.getY() - 15));
        arrayList.add(new Point2D.Double(b2.getX() - 15, b2.getY() - 15));
        arrayList.add(new Point2D.Double(b2.getX() + 15, b2.getY() + 15));

        Point2D.Double center = new Point2D.Double((((arrayList.get(0).getX() + arrayList.get(1).getX()) / 2) + ((arrayList.get(2).getX() + arrayList.get(3).getX()) / 2)) / 2,
                (((arrayList.get(0).getY() + arrayList.get(1).getY()) / 2) + ((arrayList.get(2).getY() + arrayList.get(3).getY()) / 2)) / 2);
        return new laser(arrayList, center, damage);
    }

    public class blackOrb extends enemy implements shootGiver {
        private final String blackOrbPath = "src\\sources\\photo\\blackOrb";
        BufferedImage image;
        private final isometricPanel isometricPanel;

        public blackOrb(Point2D.Double center, int numberOfBlackOrb, isometricPanel panel) {
            super(center, Color.GRAY, UUID.randomUUID().toString(), 30, 12, 30d);
            blackOrbs.add(this);
            try {
                image = ImageIO.read(new File(blackOrbPath + numberOfBlackOrb + ".jpg"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            isometricPanel = panel;
        }

        @Override
        public void Die() {
            super.Die();
            isometricPanel.clear();
            blackOrbs.remove(this);
            checkFinish();
        }

        @Override
        public DrawAbleObject getDrawAbleObject(PanelInGame panel) {
            return new PhotoShape(image, (int) (center.getX() - 30 - panel.getX()), (int) (center.getY() - 30 - panel.getY()), color);
        }

        @Override
        public void createGeometry() {
            super.CreateGeometry();
        }

        @Override
        public boolean isCircular() {
            return true;
        }

        @Override
        public Point2D getAnchor() {
            return center;
        }

        @Override
        public float getSpeed() {
            return 0;
        }

        @Override
        public boolean collide(Collidable collidable) {
            if (collidable instanceof PanelInGame) return false;
            if (collidable instanceof bolt && !((bolt) collidable).isGood()) return false;
            return true;
        }

        @Override
        public void play(Collidable collidable) {

        }

        @Override
        public void takingShot(bolt bolt) {
            if (blackOrbActive) getHit(bolt.getDamageDaler());
        }
    }
}
