package Game.game.model.characterModel.Enemy;

import Game.game.Contoroler.building.BuilderHelper;
import Game.game.Contoroler.building.Spawn;
import Game.game.model.Move.Direction;
import Game.game.model.characterModel.ObjectInGame;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.model.characterModel.epsilonFriend.bolt;
import Game.game.model.collision.Collidable;
import Game.game.model.shooting.Aoe;
import Game.game.view.characterView.DrawAbleObject;
import Game.game.view.characterView.shape.ovalShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;


public class Archmire extends moveAbleEnemy implements Aoe {
    ArrayList<String> coolDown = new ArrayList<>();

    public class archmireModel extends ObjectInGame implements Aoe {
        double rX, rY;
        Timer reduceColor = null;

        public archmireModel(Point2D.Double center, double radius1, double radius2) {
            super(center, new Color(150, 50, 50), "", -1, -1, false, 2);
            rX = radius1;
            rY = radius2;
            reduceColor = new Timer(1000, new AbstractAction() {
                int i = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    color = new Color(150 - (30 * i), 50 - (10 * i), 50 - (10 * i));
                    i++;
                    if (color.equals(Color.BLACK)) {
                        Die();
                    }
                }

            });
            reduceColor.start();
        }

        @Override
        public void Die() {
            super.Die();
            reduceColor.stop();
        }
        @Override
        public boolean checkIsOnCoolDown(String id) {
            for (int j = 0; j < coolDown.size(); j++) {
                if (coolDown.get(j).equals(id)) return true;
            }
            return false;
        }

        @Override
        public void addToCoolDownList(String id) {
            coolDown.add(id);
        }

        @Override
        public void removeFromCoolDownList(String id) {
            coolDown.remove(id);
        }
        @Override
        public DrawAbleObject getDrawAbleObject(PanelInGame panel) {
            return new ovalShape(new Point2D.Double(center.getX() - panel.getX(), center.getY() - panel.getY()), color, rX, rY);
        }

        @Override
        public double getMAXRadius() {
            return rX;
        }

        @Override
        public int getDamage() {
            return getDamageDaler();
        }

        @Override
        public int getX() {
            return 0;
        }

        @Override
        public int getY() {
            return 0;
        }

        @Override
        public int getWidth() {
            return 0;
        }

        @Override
        public int getHeight() {
            return 0;
        }

        @Override
        public double getRadiusX() {
            return rX;
        }

        @Override
        public double getRadiusY() {
            return rY;
        }

        @Override
        public Point2D.Double getAnchor() {
            return center;
        }


    }

    private double radiusx;
    private double radiusy;
    private Timer doAction;
    boolean isBaby;

    public Archmire(Point2D.Double center, double[] x, double[] y, boolean isBaby) {
        super(center, new Color(150, 50, 50), 8, x, y, isBaby ? 15 : 30, isBaby ? 5 : 10, isBaby ? 25 : 51);
        radiusx = isBaby ? 25 : 51;
        radiusy = isBaby ? 22 : 44;
        this.isBaby = isBaby;
        doAction = new Timer(1000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new archmireModel(getCenter(), radiusx, radiusy);
            }
        });
        doAction.start();
        Spawn.NUMBER_OF_ARCHMIRE_ENEMY++;
    }

    @Override
    public void Die() {
        super.Die();
        doAction.stop();
        if (!isBaby) {
            BuilderHelper.ArchmireBuilder(new Point2D.Double(center.getX() + 80, center.getY() + 80), true);
            BuilderHelper.ArchmireBuilder(new Point2D.Double(center.getX() - 80, center.getY() - 80), true);
        }
        Spawn.NUMBER_OF_ARCHMIRE_ENEMY--;
    }

    @Override
    public boolean collide(Collidable collidable) {
        if (collidable instanceof bolt && ((bolt) collidable).isGood()) return true;
        return false;
    }

    @Override
    public side findCollision(Point2D anchor) {
        return super.findCollision(anchor);
    }

    @Override
    public void setImpactNum(int impactNum) {
        super.setImpactNum(0);
    }

    @Override
    public void follow() {
        speed = Epsilon.getEpsilon().getSpeed() / 2.5;
        setDirection(new Direction(new Point2D.Double((Epsilon.getEpsilon().getCenter().getX() - getCenter().getX()), (Epsilon.getEpsilon().getCenter().getY() - getCenter().getY()))));
    }

    @Override
    public boolean checkIsOnCoolDown(String id) {
        for (int j = 0; j < coolDown.size(); j++) {
            if (coolDown.get(j).equals(id)) return true;
        }
        return false;
    }

    @Override
    public void addToCoolDownList(String id) {
        coolDown.add(id);
    }

    @Override
    public void removeFromCoolDownList(String id) {
        coolDown.remove(id);
    }

    @Override
    public double getMAXRadius() {
        return getRadiusX();
    }

    @Override
    public int getDamage() {
        return getDamageDaler();
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public double getRadiusX() {
        return 0;
    }

    @Override
    public double getRadiusY() {
        return 0;
    }


    @Override
    public Point2D.Double getAnchor() {
        return center;
    }

    @Override
    public DrawAbleObject getDrawAbleObject(PanelInGame panel) {
        return new ovalShape(new Point2D.Double(center.getX() - panel.getX(), center.getY() - panel.getY()), color, radiusx, radiusy);
    }

    public ArrayList<String> getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(ArrayList<String> coolDown) {
        this.coolDown = coolDown;
    }

    public double getRadiusx() {
        return radiusx;
    }

    public void setRadiusx(double radiusx) {
        this.radiusx = radiusx;
    }

    public double getRadiusy() {
        return radiusy;
    }

    public void setRadiusy(double radiusy) {
        this.radiusy = radiusy;
    }

    public Timer getDoAction() {
        return doAction;
    }

    public void setDoAction(Timer doAction) {
        this.doAction = doAction;
    }

    public boolean isBaby() {
        return isBaby;
    }

    public void setBaby(boolean baby) {
        isBaby = baby;
    }
}

