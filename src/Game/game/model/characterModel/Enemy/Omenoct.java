package Game.game.model.characterModel.Enemy;

import Game.game.model.Move.Direction;
import Game.game.model.characterModel.Panels.NonIsometricPanel;
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
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


import static Game.Data.constants.OMENOCT_RADIUS;
import static Game.game.Contoroler.control.Controller.*;
import static Game.game.model.characterModel.Panels.PanelInGame.PANELS;

public class Omenoct extends moveAbleEnemy implements shooter {
    public boolean isItFix = false;
    private BufferedImage image;
    private String imagePath = "src\\sources\\photo\\Omenoct .png";
    private int rangedDamage = 4;
    public side side;
    private final int actionDelay = 1250;
    private Timer actionAble;

    public Omenoct(Point2D.Double center, int nPoint, double[] xPoint, double[] yPoint, side side) {
        super(center, Color.BLACK, nPoint, xPoint, yPoint, 20, 8, (float) OMENOCT_RADIUS);
        speed = Epsilon.getEpsilon().getSpeed() / 4;
        this.side = side;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        actionAble = new Timer(actionDelay, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point2D.Double target = Epsilon.getEpsilon().getCenter();
                fire(getCenter(), target);
            }
        });
        actionAble.start();
    }

    @Override
    public void setDirection(Direction direction) {
        if (!isItFix) super.setDirection(direction);

    }

    @Override
    public void setImpactNum(int impactNum) {
        if (!isItFix) super.setImpactNum(impactNum);
    }

    @Override
    public DrawAbleObject getDrawAbleObject(PanelInGame panel) {
        Point2D fix = fixThePoint(new Point2D.Double((double) center.getX() - (image.getWidth() / 2), (double) center.getY() - (image.getHeight() / 2)), panel);
        return new PhotoShape(image, (int) fix.getX(), (int) fix.getY(),color);
    }

    public int getRangedDamage() {
        return rangedDamage;
    }

    @Override
    public boolean collide(Collidable collidable) {
        if (collidable instanceof Epsilon || collidable instanceof bolt) return true;
        return false;
    }

    @Override
    public void follow() {
        if (impactNum > 0) {
            impactNum = Math.max(0, impactNum - 1);
            return;
        }
        Epsilon epsilon = Epsilon.getEpsilon();
        Point2D.Double target = null;
        for (PanelInGame panel : PANELS) {
            if (panel instanceof NonIsometricPanel) {
                if (isItInside(epsilon, panel)) {
                    Line2D line2D;
                    switch (side) {
                        case right -> line2D = panel.getRightPanel();
                        case left -> line2D = panel.getLeftPanel();
                        case up -> line2D = panel.getUpPanel();
                        case down -> line2D = panel.getDownPanel();
                        default -> throw new IllegalStateException("Unexpected value: " + side);
                    }
                    target = new Point2D.Double((line2D.getX1() + line2D.getX2()) / 2, (line2D.getY1() + line2D.getY2()) / 2);
                    break;
                }
            }
        }
        if (getCenter().distance(target) > 2) {
            MoveDirection = (new Direction(getCenter(), target));
            impactNum = 0;
        } else {
            switch (side) {
                case right, left -> MoveDirection = new Direction(new Point2D.Double(0, random.nextBoolean() ? -1 : 1));
                case up, down -> MoveDirection = new Direction(new Point2D.Double(random.nextBoolean() ? -1 : 1, 0));
            }
        }
        if (getAnchor().distance(target) > 50) isItFix = false;
        else isItFix = true;
    }

    Random random = new Random();

    @Override
    public void fire(Point2D.Double mabda, Point2D.Double target) {
        new bolt(mabda, target, Epsilon.getEpsilon().getSpeed() * 1.2, false, rangedDamage, Color.RED);
    }

    @Override
    public void Die() {
        actionAble.stop();
        super.Die();
    }
}
