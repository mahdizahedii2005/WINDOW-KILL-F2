package Game.game.model.characterModel.Enemy;

import Game.game.Contoroler.building.MokhtasatPoint;
import Game.game.Contoroler.building.Spawn;
import Game.game.Contoroler.control.DefaultMethods;
import Game.game.model.Move.Direction;
import Game.game.model.Move.circluarMove;
import Game.game.model.Move.follower;
import Game.game.model.characterModel.ObjectInGame;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.characterModel.Panels.isometricPanel;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.model.characterModel.epsilonFriend.bolt;
import Game.game.model.collision.Collidable;
import Game.game.model.shooting.shootGiver;
import Game.game.view.characterView.DrawAbleObject;
import Game.game.view.characterView.shape.PhotoShape;
import Game.helper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Wyrm extends enemy implements circluarMove, follower, shootGiver {
    private int angle;
    private int ValidRaduice = 300;
    private ObjectInGame objectMabda;
    private boolean roundClock;
    private BufferedImage image;
    private final String imagPath = "src\\sources\\photo\\wyrm.png";
    private final isometricPanel panel;
    private Direction moveDirection;
    private float speed;
    private Timer fire;

    public Wyrm(Point2D.Double center, ArrayList<Point2D> vec, ObjectInGame objectMabda) {
        super(center, Color.YELLOW, UUID.randomUUID().toString(), 12, 40f, 8);
        Spawn.NUMBER_OF_WYRM_ENEMY++;
        setVertices(vec);
        this.objectMabda = objectMabda;
        try {
            image = ImageIO.read(new File(imagPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        panel = new isometricPanel(center.getX() - image.getWidth() / 2d - 10, center.getY() - image.getHeight() / 2d - 20, image.getWidth() + 30, image.getHeight() + 40, false, 0);
        fire = new Timer(2000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new bolt(getCenter(), objectMabda.getCenter(), Epsilon.getEpsilon().getSpeed() , false, getDamageDaler(),Color.YELLOW);
            }
        });
        fire.start();
    }

    @Override
    public void Die() {
        super.Die();
        Spawn.NUMBER_OF_WYRM_ENEMY--;
        panel.clear();
        fire.stop();
    }

    @Override
    public void getHit(int damage) {
        super.getHit(damage);
    }

    @Override
    public DrawAbleObject getDrawAbleObject(PanelInGame panel) {
        return new PhotoShape(image, (int) (center.getX() - (image.getWidth() / 2d) - panel.getX()), (int) (center.getY() - (image.getHeight() / 2d) - panel.getY()), Color.PINK);
    }

    @Override
    public void createGeometry() {
        super.CreateGeometry();
    }

    @Override
    public boolean isCircular() {
        return false;
    }

    @Override
    public Point2D getAnchor() {
        return center;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public boolean collide(Collidable collidable) {
        if (collidable instanceof PanelInGame) return false;
        return true;
    }

    @Override
    public void play(Collidable collidable) {
        roundClock = !roundClock;
    }

    @Override
    public void move() {
        MokhtasatPoint pointShape = helper.addVectors(vertices, center, moveDirection.getDirectionVector(), getSpeed());
        MokhtasatPoint pointPAnel = helper.addVectors(panel.getVertices(), new Point2D.Double(panel.getX(), panel.getY()), moveDirection.getDirectionVector(), getSpeed());
        panel.setX(pointPAnel.getCenter().getX());
        panel.setY(pointPAnel.getCenter().getY());
        panel.setVertices(pointPAnel.getArrayList());
        center = pointShape.getCenter();
        vertices = pointShape.getArrayList();
    }

    @Override
    public int getAngle() {
        double pi = Math.atan2(center.getY() - objectMabda.getCenter().getY(), (double) (center.getX() - objectMabda.getCenter().getX()));
        int angle = (int) ((pi * 180) / Math.PI);
        if (angle < 0) angle += 360;
        return angle;
    }

    @Override
    public void follow() {
        double distance = center.distance(objectMabda.getCenter());
        speed = 1.5f;
        if (distance > ValidRaduice) {
            moveDirection = new Direction(center, objectMabda.getCenter());
        } else {
            int angle = getAngle();
            angle = roundClock ? angle + 1 : angle - 1;
            if (angle < 0) {
                angle += 360;
            }
            moveDirection = new Direction(center, new Point2D.Double(objectMabda.getCenter().getX() + distance * DefaultMethods.cosTable[angle % 360], objectMabda.getCenter().getY() + (distance * DefaultMethods.sinTable[angle % 360])));
        }
    }

    @Override
    public void takingShot(bolt bolt) {
        getHit(bolt.getDamageDaler());
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getValidRaduice() {
        return ValidRaduice;
    }

    public void setValidRaduice(int validRaduice) {
        ValidRaduice = validRaduice;
    }

    public ObjectInGame getObjectMabda() {
        return objectMabda;
    }

    public void setObjectMabda(ObjectInGame objectMabda) {
        this.objectMabda = objectMabda;
    }

    public boolean isRoundClock() {
        return roundClock;
    }

    public void setRoundClock(boolean roundClock) {
        this.roundClock = roundClock;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getImagPath() {
        return imagPath;
    }

    public isometricPanel getPanel() {
        return panel;
    }

    public Direction getMoveDirection() {
        return moveDirection;
    }

    public void setMoveDirection(Direction moveDirection) {
        this.moveDirection = moveDirection;
    }

    public Timer getFire() {
        return fire;
    }

    public void setFire(Timer fire) {
        this.fire = fire;
    }
}
