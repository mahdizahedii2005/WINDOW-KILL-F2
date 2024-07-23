package Game.game.model.characterModel.Enemy.boss;

import Game.game.Contoroler.building.BuilderHelper;
import Game.game.Contoroler.building.MokhtasatPoint;
import Game.game.Contoroler.control.DefaultMethods;
import Game.game.model.Move.Direction;
import Game.game.model.Move.follower;
import Game.game.model.Move.linearMotion;
import Game.game.model.characterModel.Enemy.enemy;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.characterModel.Panels.isometricPanel;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.model.characterModel.epsilonFriend.bolt;
import Game.game.model.collision.Collidable;
import Game.game.model.shooting.shootGiver;
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
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import static Game.game.model.characterModel.Enemy.blackOrbs.makeLaser;
import static Game.helper.addVectors;

public class boss {
    static BufferedImage upPounch;
    static BufferedImage smily;
    static BufferedImage angry;
    static BufferedImage shy;
    static BufferedImage dead;
    static BufferedImage dikeL;
    static BufferedImage dikeR;
    static BufferedImage punchR;
    static BufferedImage punchL;
    static BufferedImage gunL;
    static BufferedImage gunR;
    static BufferedImage normalR;
    static BufferedImage normalL;

    private head head;
    private hand handR;
    private hand handL;

    static {

        try {
            upPounch = ImageIO.read(new File("D:\\Desktop\\programing\\java\\Ap\\project\\F2\\WINDOW KILL F2\\src\\sources\\photo\\bossEmoji\\handEmoji\\pounchUp.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            smily = ImageIO.read(new File("D:\\Desktop\\programing\\java\\Ap\\project\\F2\\WINDOW KILL F2\\src\\sources\\photo\\bossEmoji\\headEmoji\\smily.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            angry = ImageIO.read(new File("D:\\Desktop\\programing\\java\\Ap\\project\\F2\\WINDOW KILL F2\\src\\sources\\photo\\bossEmoji\\headEmoji\\angry.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dead = ImageIO.read(new File("D:\\Desktop\\programing\\java\\Ap\\project\\F2\\WINDOW KILL F2\\src\\sources\\photo\\bossEmoji\\headEmoji\\dead.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            shy = ImageIO.read(new File("D:\\Desktop\\programing\\java\\Ap\\project\\F2\\WINDOW KILL F2\\src\\sources\\photo\\bossEmoji\\headEmoji\\shy.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dikeL = ImageIO.read(new File("D:\\Desktop\\programing\\java\\Ap\\project\\F2\\WINDOW KILL F2\\src\\sources\\photo\\bossEmoji\\handEmoji\\dikeL.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            dikeR = ImageIO.read(new File("D:\\Desktop\\programing\\java\\Ap\\project\\F2\\WINDOW KILL F2\\src\\sources\\photo\\bossEmoji\\handEmoji\\dikeR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            gunL = ImageIO.read(new File("D:\\Desktop\\programing\\java\\Ap\\project\\F2\\WINDOW KILL F2\\src\\sources\\photo\\bossEmoji\\handEmoji\\gunL.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            gunR = ImageIO.read(new File("D:\\Desktop\\programing\\java\\Ap\\project\\F2\\WINDOW KILL F2\\src\\sources\\photo\\bossEmoji\\handEmoji\\gunR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            normalR = ImageIO.read(new File("D:\\Desktop\\programing\\java\\Ap\\project\\F2\\WINDOW KILL F2\\src\\sources\\photo\\bossEmoji\\handEmoji\\normalR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            normalL = ImageIO.read(new File("D:\\Desktop\\programing\\java\\Ap\\project\\F2\\WINDOW KILL F2\\src\\sources\\photo\\bossEmoji\\handEmoji\\normalL.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            punchL = ImageIO.read(new File("D:\\Desktop\\programing\\java\\Ap\\project\\F2\\WINDOW KILL F2\\src\\sources\\photo\\bossEmoji\\handEmoji\\punchL.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            punchR = ImageIO.read(new File("D:\\Desktop\\programing\\java\\Ap\\project\\F2\\WINDOW KILL F2\\src\\sources\\photo\\bossEmoji\\handEmoji\\punchR.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public boss() {
        head = new head(new Point2D.Double(Epsilon.getEpsilon().getCenter().getX(), -100));
        handR = new hand(new Point2D.Double(Toolkit.getDefaultToolkit().getScreenSize().getWidth() + 100, Epsilon.getEpsilon().getCenter().getY()), direction.right);
        handL = new hand(new Point2D.Double(-100, Epsilon.getEpsilon().getCenter().getY()), direction.left);
        startWar();
    }

    public void sadOfOpeningPanel() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (!handR.isAlive() || !handL.isAlive()) return;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handL.setHandForm(handForm.dike);
                        handR.setHandForm(handForm.dike);
                        handL.getIsometricPanel().setSolb(true);
                        handR.getIsometricPanel().setSolb(true);
                        handL.setHitAble(true);
                        handR.setHitAble(true);
                        handR.justFollow = true;
                        handL.justFollow = true;
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        synchronized (handL) {
                            handL.notifyAll();
                        }
                    }
                }).start();
                synchronized (handL) {
                    try {
                        handL.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                handL.setHandForm(handForm.normal);
                if (head.getHP() < 195) handR.setHandForm(handForm.punch);
                handL.getIsometricPanel().setSolb(false);
                handR.getIsometricPanel().setSolb(false);
                handL.changeHitAble();
                handR.changeHitAble();
                handR.justFollow = false;
                handL.justFollow = false;
                synchronized (handR) {
                    handR.notify();
                }
            }
        });
        thread.start();
        synchronized (handR) {
            handR.wait();
        }
    }

    public void shooting() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handR.setHandForm(handForm.gun);
                handL.setHandForm(handForm.gun);
                head.setHeadForm(headForm.smile);
                Timer shooting = new Timer(500, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (handR.isAlive())
                            new bolt(new Point2D.Double((double) (handR.isometricPanel.getAnchor().getX() + (handR.isometricPanel.getWidth() / 2)), (double) (handR.isometricPanel.getAnchor().getY() + (handR.isometricPanel.getHeight() / 2))), Epsilon.getEpsilon().getCenter(), Epsilon.getEpsilon().getSpeed() * 2, false, handR.boltDamage, Color.RED);
                        if (handL.isAlive())
                            new bolt(new Point2D.Double((double) (handL.isometricPanel.getAnchor().getX() + (handR.isometricPanel.getWidth() / 2)), (double) (handL.isometricPanel.getAnchor().getY() + (handR.isometricPanel.getHeight() / 2))), Epsilon.getEpsilon().getCenter(), Epsilon.getEpsilon().getSpeed() * 2, false, handL.boltDamage, Color.RED);
                    }
                });
                shooting.start();
                handR.setHitAble(true);
                handL.setHitAble(true);
                try {
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                shooting.stop();
                handR.changeHitAble();
                handL.changeHitAble();
                head.setHeadForm(headForm.shy);
                if (head.getHP() < 200) handR.setHandForm(handForm.punch);
                else handR.setHandForm(handForm.normal);
                handL.setHandForm(handForm.normal);
                synchronized (handL) {
                    handL.notify();
                }
            }
        }).start();
        synchronized (handL) {
            handL.wait();
        }
    }

    public void aoeAtack() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                head.headForm = headForm.shy;
                handR.setHandForm(handForm.gun);
                handL.setHandForm(handForm.gun);
                head.setHitAble(true);
                var headSpeed = head.speed;
                head.setSpeed(0);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                head.setSpeed(headSpeed);
                PanelInGame p = Epsilon.getEpsilon().mainPanel;
                for (int i = 0; i < 1; i++) {
                    BuilderHelper.ArchmireBuilder(new Point2D.Double(new Random().nextInt((int) p.getX(), (int) (p.getX() + p.getWidth())), new Random().nextInt((int) p.getY(), (int) (p.getY() + p.getHeight()))), false);
                }
                head.setHeadForm(headForm.smile);
                handR.setHandForm(handForm.normal);
                handL.setHandForm(handForm.normal);
                synchronized (handL) {
                    handL.notify();
                }
            }
        }).start();
        synchronized (handL) {
            handL.wait();
        }
    }

//    public void pokondanClid() {
//        head.setHeadForm(headForm.shy);
//        System.out.println("pokondanStart");
//        hand up = new hand(new Point2D.Double(Epsilon.getEpsilon().getCenter().getX(), Toolkit.getDefaultToolkit().getScreenSize().getHeight() + 400), null);
//        up.isometricPanel.setSolb(true);
//        up.setSpeed(handR.getSpeed() * 4);
//        try {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    while (!up.hit) {
//                        try {
//                            Thread.sleep(50);
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                    System.out.println("im out");
//                    synchronized (handR) {
//                        handR.notify();
//                    }
//                }
//            }).start();
//            synchronized (handR) {
//                handR.wait();
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        up.Die();
//        inputListener.crazy = true;
//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        head.setHeadForm(headForm.smile);
//        inputListener.crazy = false;
//    }

    public void rapidShooting() {
        head.setHeadForm(headForm.angry);
        head.setHitAble(true);
        Timer shoot = new Timer(1000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 16; i++) {
                    new bolt(head.getCenter(),
                            new Point2D.Double(head.getCenter().getX() + DefaultMethods.cosTable[new Random().nextBoolean() ? i * (360 / 16) : (i * (360 / 16) + 20)],
                                    head.getCenter().getY() + DefaultMethods.sinTable[new Random().nextBoolean() ? i * (360 / 16) : (i * (360 / 16) + 20)]), false, head.boltDamage, Color.RED);
                }
            }
        });
        shoot.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        shoot.stop();
        head.setHeadForm(headForm.smile);
        head.setHitAble(false);
    }

    public void compilerRapidAndsad() {
        if (handR.isAlive() || handL.isAlive()) return;
        head.setHeadForm(headForm.angry);
        head.setHitAble(true);
        Timer shoot = new Timer(500, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 16; i++) {
                    new bolt(head.getCenter(),
                            new Point2D.Double(head.getCenter().getX() + DefaultMethods.cosTable[(360 / 16) * i],
                                    head.getCenter().getY() + DefaultMethods.sinTable[(360 / 16) * i]), false, head.boltDamage, Color.RED);
                }
            }
        });
        shoot.start();
        handL.setHandForm(handForm.dike);
        handR.setHandForm(handForm.dike);
        handL.getIsometricPanel().setSolb(true);
        handR.getIsometricPanel().setSolb(true);
        handL.setHitAble(true);
        handR.setHitAble(true);
        handR.justFollow = true;
        handL.justFollow = true;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        handL.setHandForm(handForm.normal);
        if (head.getHP() < 195) handR.setHandForm(handForm.punch);
        else handR.setHandForm(handForm.normal);
        handL.getIsometricPanel().setSolb(false);
        handR.getIsometricPanel().setSolb(false);
        handL.changeHitAble();
        handR.changeHitAble();
        head.setHeadForm(headForm.smile);
        head.setHitAble(false);
        handR.justFollow = false;
        handL.justFollow = false;
        shoot.stop();
    }

    public void compileaoeAndshoot() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                head.headForm = headForm.shy;
                handR.setHandForm(handForm.gun);
                handL.setHandForm(handForm.gun);
                head.setHitAble(true);
                var headSpeed = head.speed;
                Timer shooting = new Timer(500, new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (handR.isAlive())
                            new bolt(new Point2D.Double((double) (handR.isometricPanel.getAnchor().getX() + (handR.isometricPanel.getWidth() / 2)), (double) (handR.isometricPanel.getAnchor().getY() + (handR.isometricPanel.getHeight() / 2))), Epsilon.getEpsilon().getCenter(), Epsilon.getEpsilon().getSpeed() * 2, false, handR.boltDamage, Color.RED);
                        if (handL.isAlive())
                            new bolt(new Point2D.Double((double) (handL.isometricPanel.getAnchor().getX() + (handR.isometricPanel.getWidth() / 2)), (double) (handL.isometricPanel.getAnchor().getY() + (handR.isometricPanel.getHeight() / 2))), Epsilon.getEpsilon().getCenter(), Epsilon.getEpsilon().getSpeed() * 2, false, handL.boltDamage, Color.RED);
                    }
                });
                shooting.start();
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                shooting.stop();
                head.setSpeed(headSpeed);
                PanelInGame p = Epsilon.getEpsilon().mainPanel;
                for (int i = 0; i < 1; i++) {
                    BuilderHelper.ArchmireBuilder(new Point2D.Double(new Random().nextInt((int) p.getX(), (int) (p.getX() + p.getWidth())), new Random().nextInt((int) p.getY(), (int) (p.getY() + p.getHeight()))), false);
                }
                head.headForm = headForm.smile;
                handR.setHandForm(handForm.normal);
                handL.setHandForm(handForm.normal);
                synchronized (handR) {
                    handR.notify();
                }
            }
        }).start();
        synchronized (handR) {
            handR.wait();
        }
    }

    public void startWar() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                boolean doit = false;
                while (head.isAlive()) {
                    if (head.getHP() < 205 && head.getHP() > 195) {
                        if (!doit) {
                            head.headForm = headForm.angry;
                            handR.setHandForm(handForm.punch);
                            doit = true;
                        }
                    }
                    if (head.getHP() < 200) handR.setHandForm(handForm.punch);
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        switch (new Random().nextInt(handR.getHandForm() == handForm.punch ? 7 : 2)) {
                            case 0 -> sadOfOpeningPanel();
                            case 1 -> shooting();
                            case 2 -> aoeAtack();
                            case 4 -> rapidShooting();
                            case 5 -> compilerRapidAndsad();
                            case 6 -> compileaoeAndshoot();
                        }
                    } catch (InterruptedException e) {
                        System.out.println("lol");
                    }
                }
                head.setSpeed(0);
                handR.setSpeed(0);
                handL.setSpeed(0);
                head.setHeadForm(headForm.dead);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);

                }
                //todo win
            }
        }).start();
    }

//    public void pounch() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                handL.setHandForm(handForm.punch);
//                head.setHeadForm(headForm.angry);
//                try {
//                    Thread.sleep(4000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                var saveHeadSpeed = head.getSpeed();
//                var saveHandSpees = handL.getSpeed();
//                handL.speed
//            }
//        }).start();
//
//    }

    public enum direction {
        right, left
    }

    public class hand extends enemy implements follower, shootGiver, shooter, linearMotion {
        boolean hit = false;
        boolean justFollow = false;
        isometricPanel isometricPanel;
        float speed = (float) (Epsilon.getEpsilon().getSpeed() / 0.8);
        direction direction;
        int boltDamage = 10;
        int laserDamage = 15;
        handForm handForm = Game.game.model.characterModel.Enemy.boss.handForm.normal;
        boolean hitAble = false;
        Direction moveDirection = new Direction(new Point2D.Double(0, 0));

        public hand(Point2D.Double center, direction direction) {
            super(center, Color.RED, UUID.randomUUID().toString(), 100, 100F, 0);
            this.direction = direction;
            isometricPanel = new isometricPanel(center.getX() - normalR.getWidth() - 50, center.getY() - normalL.getHeight() - 50, 250, 250, false, 0);
            if (direction == null) {
                isometricPanel.setSolb(true);
                justFollow = true;
            }
        }


        @Override
        public void move(Direction direction, double speed) {
            MokhtasatPoint movement = addVectors(vertices, center, direction.getDirectionVector(), speed);
            this.center = movement.getCenter();

        }

        @Override
        public void move() {
            try {
//                if (this.direction == null) {
//                    System.out.print("");
//                }
                if (isometricPanel.movee(moveDirection, speed)) move(moveDirection, speed);
                else if (this.direction == null) {
                    hit = true;
                    System.out.println("hit detective");
                }
            } catch (NullPointerException d) {
            }
        }

        @Override
        public void setDirection(Direction direction) {
            moveDirection = new Direction(direction);
        }

        @Override
        public void setSpeed(double speed) {
            this.speed = (float) speed;
        }

        @Override
        public void takingShot(bolt bolt) {
            if (hitAble) getHit(bolt.getDamageDaler());
        }

        @Override
        public void fire(Point2D.Double mabda, Point2D.Double target) {
            new bolt(mabda, target, Epsilon.getEpsilon().getSpeed() / 1.3, false, boltDamage, Color.RED);
        }

        public void fireLaser(Point2D.Double mabda, Point2D.Double target) {
            makeLaser(mabda, target, laserDamage);
        }

        private Point2D.Double getCenterPhoto() {
            try {
                return new Point2D.Double(isometricPanel.getX() + (isometricPanel.getWidth() - handForm.getEmoji(direction).getWidth()) / 2, isometricPanel.getY() + (isometricPanel.getHeight() - handForm.getEmoji(direction).getHeight()) / 2);
            } catch (NullPointerException r) {
                System.out.println("heh");
                return new Point2D.Double(isometricPanel.getX() + (isometricPanel.getWidth() - 180) / 2, isometricPanel.getY() + (isometricPanel.getHeight() - 180) / 2);

            }
        }

        @Override
        public void Die() {
            super.Die();
            isometricPanel.clear();
        }

        @Override
        public DrawAbleObject getDrawAbleObject(PanelInGame panel) {
            if (isometricPanel == null) return null;
            Point2D.Double getlo = getCenterPhoto();
            return new PhotoShape(handForm.getEmoji(direction), (int) (getlo.getX() - panel.getX()), (int) (getlo.getY() - panel.getY()), color);
        }

        @Override
        public void createGeometry() {
            for (var a : handForm.getVert(new Point2D.Double(center.getX() - (handForm.getEmoji(direction).getWidth() / 2d), center.getY() - (handForm.getEmoji(direction).getHeight() / 2d)), direction)) {
                vertices.add(new Point((int) a.getX(), (int) a.getY()));
            }
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

        }

        @Override
        public void follow() {
            if (justFollow) {
                moveDirection = new Direction(center, Epsilon.getEpsilon().getCenter());
                return;
            }
            double dis = center.distance(Epsilon.getEpsilon().getCenter());
            if (dis > 500) moveDirection = new Direction(center, Epsilon.getEpsilon().getCenter());
            else if (dis < 400) moveDirection = new Direction(Epsilon.getEpsilon().getCenter(), center);
            else {
                moveDirection = new Direction(new Point2D.Double(0, 0));
            }
        }

        public Game.game.model.characterModel.Panels.isometricPanel getIsometricPanel() {
            return isometricPanel;
        }

        public void setIsometricPanel(Game.game.model.characterModel.Panels.isometricPanel isometricPanel) {
            this.isometricPanel = isometricPanel;
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }

        public boss.direction getDirection() {
            return direction;
        }

        public void setDirection(boss.direction direction) {
            this.direction = direction;
        }

        public int getBoltDamage() {
            return boltDamage;
        }

        public void setBoltDamage(int boltDamage) {
            this.boltDamage = boltDamage;
        }

        public int getLaserDamage() {
            return laserDamage;
        }

        public void setLaserDamage(int laserDamage) {
            this.laserDamage = laserDamage;
        }

        public Game.game.model.characterModel.Enemy.boss.handForm getHandForm() {
            return handForm;
        }

        public void setHandForm(Game.game.model.characterModel.Enemy.boss.handForm handForm) {
            this.handForm = handForm;
        }

        public boolean isHitAble() {
            return hitAble;
        }

        public void setHitAble(boolean hitAble) {
            this.hitAble = hitAble;
        }

        public void changeHitAble() {
            this.hitAble = !hitAble;
        }

        public Direction getMoveDirection() {
            return moveDirection;
        }

        public void setMoveDirection(Direction moveDirection) {
            this.moveDirection = moveDirection;
        }
    }

    class head extends enemy implements follower, shootGiver, shooter, linearMotion {
        float speed = (float) (Epsilon.getEpsilon().getSpeed() / 1.5);
        isometricPanel isometricPanel;
        int boltDamage = 10;
        headForm headForm = Game.game.model.characterModel.Enemy.boss.headForm.shy;
        boolean hitAble = true;
        Direction moveDirection = new Direction(new Point2D.Double(0, 0));

        public head(Point2D.Double center) {
            super(center, Color.CYAN, UUID.randomUUID().toString(), 300, 0, 125f);
            isometricPanel = new isometricPanel(center.getX() - 125, center.getY() - 125, 250, 250, false, 0);
        }

        @Override
        public void getHit(int damage) {
            setHP(Math.max(0, getHP() - damage));
        }

        @Override
        public void move(Direction direction, double speed) {
            try {
            MokhtasatPoint movement = addVectors(vertices, center, direction.getDirectionVector(), speed);
            this.vertices = movement.getArrayList();
            this.center = movement.getCenter();
            } catch (Exception e) {
            }
        }

        @Override
        public void move() {
            if (isometricPanel == null) return;
            isometricPanel.move(moveDirection, speed);
            move(moveDirection, speed);
        }

        @Override
        public void setDirection(Direction direction) {
            moveDirection = new Direction(direction);
        }

        @Override
        public void setSpeed(double speed) {
            this.speed = (float) speed;

        }

        @Override
        public void takingShot(bolt bolt) {
            if (hitAble) getHit(bolt.getDamageDaler());
        }

        @Override
        public void fire(Point2D.Double mabda, Point2D.Double target) {
            new bolt(mabda, target, Epsilon.getEpsilon().getSpeed() / 1.3, false, boltDamage, Color.RED);
        }

        private Point2D.Double getLocation() {
            return new Point2D.Double(isometricPanel.getX() + (isometricPanel.getWidth() - headForm.getEmoji().getWidth()) / 2, isometricPanel.getY() + (isometricPanel.getHeight() - headForm.getEmoji().getHeight()) / 2);
        }

        @Override
        public DrawAbleObject getDrawAbleObject(PanelInGame panel) {
            if (isometricPanel == null) return null;
            Point2D.Double getlo = getLocation();
            return new PhotoShape(headForm.getEmoji(), (int) (getlo.getX() - panel.getX()), (int) (getlo.getY() - panel.getY()), color);
        }

        @Override
        public void createGeometry() {
            CreateGeometry();
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
            return speed;
        }

        @Override
        public boolean collide(Collidable collidable) {
            if (collidable instanceof PanelInGame) return false;
            return true;
        }

        @Override
        public void play(Collidable collidable) {

        }


        @Override
        public void follow() {
            double dis = center.distance(Epsilon.getEpsilon().getCenter());
            if (dis > 400) moveDirection = new Direction(center, Epsilon.getEpsilon().getCenter());
            else if (dis < 300) moveDirection = new Direction(Epsilon.getEpsilon().getCenter(), center);
            else moveDirection = new Direction(new Point2D.Double(0, 0));
        }

        public void setSpeed(float speed) {
            this.speed = speed;
        }

        public Game.game.model.characterModel.Panels.isometricPanel getIsometricPanel() {
            return isometricPanel;
        }

        public void setIsometricPanel(Game.game.model.characterModel.Panels.isometricPanel isometricPanel) {
            this.isometricPanel = isometricPanel;
        }

        public int getBoltDamage() {
            return boltDamage;
        }

        public void setBoltDamage(int boltDamage) {
            this.boltDamage = boltDamage;
        }

        public Game.game.model.characterModel.Enemy.boss.headForm getHeadForm() {
            return headForm;
        }

        public void setHeadForm(Game.game.model.characterModel.Enemy.boss.headForm headForm) {
            this.headForm = headForm;
        }

        public boolean isHitAble() {
            return hitAble;
        }

        public void setHitAble(boolean hitAble) {
            this.hitAble = hitAble;
        }

        public Direction getMoveDirection() {
            return moveDirection;
        }

        public void setMoveDirection(Direction moveDirection) {
            this.moveDirection = moveDirection;
        }
    }
}

enum headForm {
    smile {
        @Override
        BufferedImage getEmoji() {
            return boss.smily;
        }
    }, angry {
        @Override
        BufferedImage getEmoji() {
            return boss.angry;
        }
    }, dead {
        @Override
        BufferedImage getEmoji() {
            return boss.dead;
        }
    }, shy {
        @Override
        BufferedImage getEmoji() {
            return boss.shy;
        }
    };

    abstract BufferedImage getEmoji();
}

enum handForm {
    normal {
        ArrayList<Point2D.Double> getVert(Point2D.Double center, boss.direction direction) {
            switch (direction) {
                case left -> {
                    ArrayList<Point2D.Double> result = new ArrayList<>();
                    result.add(new Point2D.Double(center.getX() + 80, center.getY() + 200));
                    result.add(new Point2D.Double(center.getX() + 40, center.getY() + 200));
                    result.add(new Point2D.Double(center.getX() + 10, center.getY() + 170));
                    result.add(new Point2D.Double(center.getX() + 0, center.getY() + 80));
                    result.add(new Point2D.Double(center.getX() + 80, center.getY() + 70));
                    result.add(new Point2D.Double(center.getX() + 80, center.getY() + 0));
                    result.add(new Point2D.Double(center.getX() + 100, center.getY() + 0));
                    result.add(new Point2D.Double(center.getX() + 110, center.getY() + 160));
                    return result;
                }
                case right -> {
                    ArrayList<Point2D.Double> result = new ArrayList<>();
                    result.add(new Point2D.Double(center.getX() + 80, center.getY() + 200));
                    result.add(new Point2D.Double(center.getX() + 40, center.getY() + 200));
                    result.add(new Point2D.Double(center.getX() + 10, center.getY() + 160));
                    result.add(new Point2D.Double(center.getX() + 0, center.getY() + 80));
                    result.add(new Point2D.Double(center.getX() + 80, center.getY() + 60));
                    result.add(new Point2D.Double(center.getX() + 80, center.getY() + 10));
                    result.add(new Point2D.Double(center.getX() + 100, center.getY() + 0));
                    result.add(new Point2D.Double(center.getX() + 110, center.getY() + 120));
                    result.add(new Point2D.Double(center.getX() + 110, center.getY() + 170));
                    return result;
                }
                case null, default -> {
                    return null;
                }
            }
        }

        BufferedImage getEmoji(boss.direction direction) {
            switch (direction) {
                case left -> {
                    return boss.normalL;
                }
                case right -> {
                    return boss.normalR;
                }
                case null -> {
                    return boss.upPounch;
                }
            }
        }
    },
    punch {
        ArrayList<Point2D.Double> getVert(Point2D.Double center, boss.direction direction) {
            switch (direction) {
                case left -> {
                    ArrayList<Point2D.Double> result = new ArrayList<>();
                    result.add(new Point2D.Double(center.getX() + 10, center.getY()));
                    result.add(new Point2D.Double(center.getX() + 181, center.getY() + 6));
                    result.add(new Point2D.Double(center.getX() + 192, center.getY() + 88));
                    result.add(new Point2D.Double(center.getX() + 172, center.getY() + 100));
                    result.add(new Point2D.Double(center.getX() + 74, center.getY() + 100));
                    result.add(new Point2D.Double(center.getX() + 50, center.getY() + 60));
                    result.add(new Point2D.Double(center.getX(), center.getY() + 50));
                    return result;
                }
                case right -> {
                    ArrayList<Point2D.Double> result = new ArrayList<>();
                    result.add(new Point2D.Double(center.getX() + 10, center.getY() + 10));
                    result.add(new Point2D.Double(center.getX() + 181, center.getY()));
                    result.add(new Point2D.Double(center.getX() + 200, center.getY() + 45));
                    result.add(new Point2D.Double(center.getX() + 150, center.getY() + 60));
                    result.add(new Point2D.Double(center.getX() + 120, center.getY() + 100));
                    result.add(new Point2D.Double(center.getX() + 40, center.getY() + 120));
                    result.add(new Point2D.Double(center.getX() + 20, center.getY() + 103));
                    result.add(new Point2D.Double(center.getX(), center.getY() + 90));
                    return result;
                }
                case null, default -> {
                    return null;
                }
            }
        }


        BufferedImage getEmoji(boss.direction direction) {
            switch (direction) {
                case left -> {
                    return boss.punchL;
                }
                case right -> {
                    return boss.punchR;
                }
                case null -> {
                    return boss.upPounch;
                }
            }
        }
    },
    gun {
        ArrayList<Point2D.Double> getVert(Point2D.Double center, boss.direction direction) {
            switch (direction) {
                case left -> {
                    ArrayList<Point2D.Double> result = new ArrayList<>();
                    result.add(new Point2D.Double(center.getX() + 0, center.getY() + 160));
                    result.add(new Point2D.Double(center.getX() + 70, center.getY() + 160));
                    result.add(new Point2D.Double(center.getX() + 80, center.getY() + 120));
                    result.add(new Point2D.Double(center.getX() + 110, center.getY() + 110));
                    result.add(new Point2D.Double(center.getX() + 130, center.getY() + 90));
                    result.add(new Point2D.Double(center.getX() + 140, center.getY() + 70));
                    result.add(new Point2D.Double(center.getX() + 190, center.getY() + 60));
                    result.add(new Point2D.Double(center.getX() + 200, center.getY() + 140));
                    result.add(new Point2D.Double(center.getX() + 190, center.getY() + 10));
                    result.add(new Point2D.Double(center.getX() + 10, center.getY() + 10));
                    result.add(new Point2D.Double(center.getX() + 0, center.getY() + 30));
                    result.add(new Point2D.Double(center.getX() + 0, center.getY() + 50));
                    result.add(new Point2D.Double(center.getX() + 10, center.getY() + 170));
                    return result;
                }
                case right -> {
                    ArrayList<Point2D.Double> result = new ArrayList<>();
                    result.add(new Point2D.Double(center.getX() + 140, center.getY() + 150));
                    result.add(new Point2D.Double(center.getX() + 120, center.getY() + 110));
                    result.add(new Point2D.Double(center.getX() + 70, center.getY() + 100));
                    result.add(new Point2D.Double(center.getX() + 60, center.getY() + 60));
                    result.add(new Point2D.Double(center.getX() + 10, center.getY() + 50));
                    result.add(new Point2D.Double(center.getX() + 0, center.getY() + 30));
                    result.add(new Point2D.Double(center.getX() + 10, center.getY() + 0));
                    result.add(new Point2D.Double(center.getX() + 180, center.getY() + 10));
                    result.add(new Point2D.Double(center.getX() + 200, center.getY() + 30));
                    result.add(new Point2D.Double(center.getX() + 180, center.getY() + 70));
                    result.add(new Point2D.Double(center.getX() + 190, center.getY() + 150));
                    return result;
                }
                case null, default -> {
                    return null;
                }
            }
        }


        BufferedImage getEmoji(boss.direction direction) {
            switch (direction) {
                case left -> {
                    return boss.gunL;
                }
                case right -> {
                    return boss.gunR;
                }
                case null -> {
                    return boss.upPounch;
                }
            }
        }
    },
    dike {
        ArrayList<Point2D.Double> getVert(Point2D.Double center, boss.direction direction) {
            switch (direction) {
                case left -> {
                    ArrayList<Point2D.Double> result = new ArrayList<>();
                    result.add(new Point2D.Double(center.getX() + 10, center.getY() + 130));
                    result.add(new Point2D.Double(center.getX() + 70, center.getY() + 140));
                    result.add(new Point2D.Double(center.getX() + 110, center.getY() + 0));
                    result.add(new Point2D.Double(center.getX() + 130, center.getY() + 0));
                    result.add(new Point2D.Double(center.getX() + 140, center.getY() + 20));
                    result.add(new Point2D.Double(center.getX() + 140, center.getY() + 80));
                    result.add(new Point2D.Double(center.getX() + 130, center.getY() + 190));
                    result.add(new Point2D.Double(center.getX() + 0, center.getY() + 200));
                    result.add(new Point2D.Double(center.getX() + 0, center.getY() + 130));
                    return result;
                }
                case right -> {
                    ArrayList<Point2D.Double> result = new ArrayList<>();
                    result.add(new Point2D.Double(center.getX() + 10, center.getY() + 90));
                    result.add(new Point2D.Double(center.getX() + 10, center.getY() + 20));
                    result.add(new Point2D.Double(center.getX() + 30, center.getY() + 0));
                    result.add(new Point2D.Double(center.getX() + 80, center.getY() + 140));
                    result.add(new Point2D.Double(center.getX() + 140, center.getY() + 130));
                    result.add(new Point2D.Double(center.getX() + 150, center.getY() + 170));
                    result.add(new Point2D.Double(center.getX() + 140, center.getY() + 190));
                    result.add(new Point2D.Double(center.getX() + 40, center.getY() + 190));
                    result.add(new Point2D.Double(center.getX() + 20, center.getY() + 180));
                    result.add(new Point2D.Double(center.getX() + 10, center.getY() + 160));
                    result.add(new Point2D.Double(center.getX() + 0, center.getY() + 100));
                    return result;
                }
                case null, default -> {
                    return null;
                }
            }
        }

        @Override
        BufferedImage getEmoji(boss.direction direction) {
            switch (direction) {
                case left -> {
                    return boss.dikeL;
                }
                case right -> {
                    return boss.dikeR;
                }
                case null -> {
                    return boss.upPounch;
                }
            }
        }
    };

    abstract ArrayList<Point2D.Double> getVert(Point2D.Double center, boss.direction direction);

    abstract BufferedImage getEmoji(boss.direction direction);
}
