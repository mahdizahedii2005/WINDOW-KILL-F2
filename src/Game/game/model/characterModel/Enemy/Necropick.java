package Game.game.model.characterModel.Enemy;

import Game.game.Contoroler.building.MokhtasatPoint;
import Game.game.Contoroler.control.DefaultMethods;
import Game.game.model.Move.Direction;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.model.characterModel.epsilonFriend.bolt;
import Game.game.model.collision.Collidable;
import Game.game.model.shooting.shooter;
import Game.game.view.characterView.DrawAbleObject;
import Game.game.view.characterView.shape.PhotoShape;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Game.helper.addVectors;

public class Necropick extends moveAbleEnemy implements shooter {
    private BufferedImage readyImage;
    private final String readyImagePath = "src\\sources\\photo\\faceSkel.png";
    private Point2D.Double PhotoCenter;
    private BufferedImage image;
    private final String imagePath = "src\\sources\\photo\\Necropick .png";
    private Thread action;
    private boolean isItHide = false;
    private Timer fire;
    private int fireDeley = 3000;
    private boolean readyToShow = false;

    public Necropick(Point2D.Double PhotoCenter, Point2D.Double center, int nPoint, double[] xPoint, double[] yPoint) {
        super(center, Color.YELLOW, nPoint, xPoint, yPoint, 20, 0, 100);
        try {
            readyImage = ImageIO.read(new File(readyImagePath));
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.PhotoCenter = PhotoCenter;
        fire = new Timer(fireDeley, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isItHide) {
                    for (int i = 0; i < 8; i++) {
                        fire(getCenter(), addVectors(getCenter(), new Point2D.Double(DefaultMethods.cosTable[i * 45], DefaultMethods.sinTable[45 * i])));
                    }
                }
            }
        });
        fire.start();
        action = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    isItHide = false;
                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        System.out.println("omg");
                    }
                    isItHide = true;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("omg");
                    }
                    readyToShow = true;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("omg");
                    }

                    readyToShow = false;
                }
            }
        });
        action.start();
    }

    @Override
    public void move(Direction direction, double speed) {
        super.move(direction, speed);
        MokhtasatPoint mokhtasatPoint = addVectors(vertices, PhotoCenter, MoveDirection.getDirectionVector(), speed);
        this.PhotoCenter = mokhtasatPoint.getCenter();
    }

    @Override
    public void follow() {
        if (impactNum > 0) {
            impactNum = Math.max(impactNum - 1, 0);
            return;
        }
        if (readyToShow) speed = 0;
        else if (isItHide) speed = Epsilon.getEpsilon().getSpeed();
        else speed = Epsilon.getEpsilon().getSpeed() / 10;
        if (getCenter().distance(Epsilon.getEpsilon().getCenter()) < 150) {
            setDirection(new Direction(new Point2D.Double(0, 0)));
        } else {
            setDirection(new Direction(new Point2D.Double(getCenter().getX(), getCenter().getY()), new Point2D.Double(Epsilon.getEpsilon().getCenter().getX(), Epsilon.getEpsilon().getCenter().getY())));
        }
    }

    @Override
    public boolean collide(Collidable collidable) {
        if (isItHide) return false;
        if (collidable instanceof Barricados)return true;
        if ((collidable instanceof bolt && ((bolt) collidable).isGood()) || collidable instanceof Epsilon) {
            return true;
        }
        return false;
    }

    @Override
    public DrawAbleObject getDrawAbleObject(PanelInGame panel) {
        if (readyToShow)
            return new PhotoShape(readyImage, (int) (PhotoCenter.getX() - panel.getX()), (int) (PhotoCenter.getY() - panel.getY()), color);
        if (!isItHide)
            return new PhotoShape(image, (int) (PhotoCenter.getX() - panel.getX()), (int) (PhotoCenter.getY() - panel.getY()), color);
        return null;
    }

    @Override
    public void Die() {
        super.Die();
        action.interrupt();
        fire.stop();
    }

    @Override
    public void fire(Point2D.Double mabda, Point2D.Double realTarget) {
        new bolt(mabda, realTarget, Epsilon.getEpsilon().getSpeed() / 2, false, 5, Color.PINK);
    }
}
