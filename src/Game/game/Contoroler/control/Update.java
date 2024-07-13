package Game.game.Contoroler.control;

import Game.game.Contoroler.thingInGame.impact;
import Game.game.gameApplication;
import Game.game.model.Move.Moveable;
import Game.game.model.Move.follower;
import Game.game.model.characterModel.ObjectInGame;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.characterModel.ThingsInGame;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.model.closeFRame.CLOSEABLE;
import Game.game.model.collision.Collidable;
import Game.game.model.collision.CollisionState;
import Game.game.view.PanelInGame.frameInGame;
import Game.game.view.PanelInGame.panelInGameView;

import javax.swing.*;


import static Game.Data.constants.*;
import static Game.game.Contoroler.control.Controller.*;

import static Game.game.model.characterModel.ObjectInGame.objectInGames;
import static Game.game.model.characterModel.Panels.PanelInGame.PANELS;
import static Game.game.model.collision.Collidable.*;

public class Update {
    public static boolean wait = false;
    public static Thread CLOSE_FRAME;
    public static Timer FRAME_UPDATE;
    public static Timer MODEL_UPDATE;

    public Update() {
        CLOSE_FRAME = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (wait) {
                        synchronized (Update.CLOSE_FRAME) {
                            try {
                                CLOSE_FRAME.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    synchronized (Update.class) {
                        closeThePanel();
                        try {
                            Thread.sleep(DELAY_OF_CLOSE_FRAME);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
        CLOSE_FRAME.start();
        FRAME_UPDATE = new

                Timer((int) FRAME_UPDATE_TIME, e ->

                        updateView()) {
                    {
                        setCoalesce(true);
                    }
                }

        ;
        FRAME_UPDATE.start();
        MODEL_UPDATE = new

                Timer((int) MODEL_UPDATE_TIME, e ->

                        updateModel()) {
                    {
                        setCoalesce(true);
                    }
                }

        ;
        MODEL_UPDATE.start();
        finish = true;
    }

    public static boolean finish = true;

    private void updateModel() {
        for (Moveable a : Moveable.moveAble) {
            if (a instanceof follower) {
                ((follower) a).follow();
            }
            if (a instanceof Epsilon && finish) {
                a.move();
            } else if (!(a instanceof Epsilon)) {
                a.move();
            }
        }
        CreateAllGeometries();
        for (int i = 0; i < collidables.size() - 1; i++) {
            Collidable c1 = collidables.get(i);
            for (int j = i + 1; j < collidables.size(); j++) {
                Collidable c2 = collidables.get(j);
                if (c1.collide(c2) && c2.collide(c1)) {
                    if ((c1 instanceof PanelInGame || c2 instanceof PanelInGame) || (c1.getAnchor().distance(c2.getAnchor()) <= c1.getMaxR() + c2.getMaxR() + COLLISION_SENSITIVITY)) {
                        CollisionState collision = c1.checkCollision(c2);
                        if (collision.isCollision()) {
                            if (!(c1 instanceof PanelInGame && c2 instanceof Epsilon) && !(c2 instanceof PanelInGame && c1 instanceof Epsilon)) {
                                new impact(PointFloutToDouble(collision.getCollisionPoint()));
                            }
                            collision.PlayCollisionAction();
                        }
                    }
                }
            }
        }
//        for (int i = 0 ; i < boltList.size () ; i++) {
//            bolt b = boltList.get (i);
//            b.wallCollision (originalPanel.panelWall.up);
//            b.wallCollision (originalPanel.panelWall.down);
//            b.wallCollision (originalPanel.panelWall.right);
//            b.wallCollision (originalPanel.panelWall.left);
//            for (int j = 0 ; j < objectInGames.size () ; j++) {
//                ObjectInGame a = objectInGames.get (j);
//                if (a instanceof shootGiver) {
//                    b.boltCollide ((shootGiver) a);
//                }
//            }
//        }
//        ArrayList<Collidable> collidables = Collidable.collidables;
//        for (int i = 0 ; i < collidables.size () ; i++) {
//            for (int j = i + 1 ; j < collidables.size () ; j++) {
//                if (collidables.get (i).getCenter ().distance (collidables.get (j).getCenter ()) < 50) {// TODO: ۲۳/۰۴/۲۰۲۴ prize part <----
//                    if (collidables.get (i).collision (collidables.get (j))) {
//                        if (collidables.get (i) instanceof rasEpsilon.ras || collidables.get (j) instanceof rasEpsilon.ras) {
//                            if (collidables.get (i) instanceof Enemy) {
//                                Epsilon.getEpsilon ().setHP (Epsilon.getEpsilon ().getHP () + ((Enemy) collidables.get (i)).getDamageTaker ());
//                                ((Enemy) collidables.get (i)).setHP (((Enemy) collidables.get (i)).getHP () - 5);
//                            } else if (collidables.get (j) instanceof Enemy) {
//                                ((Enemy) collidables.get (j)).setHP (((Enemy) collidables.get (j)).getHP () - 5);
//                            }
//                            return;
//                        }
//                        if (collidables.get (i) instanceof Epsilon || collidables.get (j) instanceof Epsilon) {
//                            try {
//                                Epsilon.getEpsilon ().setHP (Math.max (0, Epsilon.getEpsilon ().getHP () - ((Enemy) collidables.get (i)).getDamageTaker ()));
//                            } catch (ClassCastException e) {
//                                try {
//                                    Epsilon.getEpsilon ().setHP (Math.max (0, Epsilon.getEpsilon ().getHP () - ((Enemy) collidables.get (j)).getDamageTaker ()));
//                                } catch (ClassCastException r) {
//                                }
//                            }
//                        }
//                        new impact(collidables.get (i).findCollisionPoint (collidables.get (j)));
//                    }
//                }
//            }
//        }
    }

    int i = 0;

    private void closeThePanel() {
//        System.out.println(i);
//        i++;
        for (CLOSEABLE closeable : CLOSEABLE.CLOSEABLES) {
            closeable.reduceFrame();
        }
    }

    private void updateView() {
        String time = convertToMinSec(System.nanoTime() - Game.game.gameApplication.getGameApplication().timeOfStart);
        String exp = ((Integer) ((int) Epsilon.getEpsilon().getExp())).toString();
        String Hp = ((Integer) ((int) Epsilon.getEpsilon().getHP())).toString()+"/"+((Integer) ((int) Epsilon.getEpsilon().getMaxHp())).toString();
        findNotifPanel("Hp").fixDetail(Hp);
        findNotifPanel("exp").fixDetail(exp);
        findNotifPanel("time").fixDetail(time);
//        System.out.println("hi");
        for (PanelInGame p : PANELS) {
            panelInGameView panel = findPanelView(p.getId());
            if (panel == null) throw new NullPointerException();
            panel.fixDetail((int) p.getX(), (int) p.getY(), p.getSize(), p.getBackGrandColor());
            panel.restartObjectInside();
            for (ObjectInGame object : objectInGames) {
                if (isItInside(object, p)) {
                    panel.addToObjectInside(object.getDrawAbleObject(p));
                }
            }
        }
//        for (DrawAble d : DRAW_ABLES) {
//            if (d instanceof enemyView) {
//                enemyView enemy = (enemyView) d;
//                Enemy e = (Enemy) findObjectModel(enemy.getId());
//                // TODO: ۰۸/۰۴/۲۰۲۴ only for this this faze you have to fix the draw Jpanel for the other faze in this class and fix the final class for other state
//                MokhtasatPoint m = fixThePoint(e.getVertices(), whatPanelForDraw(e.getVertices()));
//                d.fixDetail(whatPanelForDraw(e.getVertices(), true), m.getArrayList(), e.getColor());
//            } else if (d instanceof epsilonView) {
//                epsilonView e = (epsilonView) d;
//                Epsilon epsilon = (Epsilon) findObjectModel(e.getId());
//                fixThePoint(epsilon.getCenter(), whatPanelForDraw(epsilon.getCenter()));
//                e.fixDetail(fixThePoint(epsilon.getCenter(), whatPanelForDraw(epsilon.getCenter())), epsilon.getRadius(), whatPanelForDraw(epsilon.getCenter()));
////                System.out.println(FPS);
//            } else if (d instanceof boltView) {
//                boltView bv = (boltView) d;
//                bolt bolt = (bolt) (findObjectModel(bv.getId()));
//                d.fixDetail(whatPanelForDraw(bolt.getCenter()), Color.WHITE, fixThePoint(bolt.getCenter(), whatPanelForDraw(bolt.getCenter())));
//            } else if ((d instanceof PrizeView)) {
//                PrizeView p = (PrizeView) d;
//                prize prize = (prize) findObjectModel(p.getId());
//                p.fixDetail(whatPanelForDraw(prize.getCenter()), prize.getColor(), fixThePoint(prize.getCenter(), whatPanelForDraw(prize.getCenter())));
//            } else if (d instanceof JPanel) {
//                d.fixDetail((int) originalPanel.getPanel().getX(), (int) originalPanel.getPanel().getY(), originalPanel.getPanel().getSize(), PANEL_BACK_GRAND, PANEL_BAR_GRAND);
////            } else if (d instanceof epsilonRas) {
////                epsilonRas p = (epsilonRas) d;
////                rasEpsilon.ras prize = (rasEpsilon.ras) findObjectModel (p.getId ());
////                p.fixDetail (whatPanelForDraw (prize.getCenter ()), prize.getColor (), fixThePoint (prize.getCenter (), whatPanelForDraw (prize.getCenter ())));
////            }
//            }
//        }
        frameInGame.getFrame().repaint();
    }
}
