package Game.game.Contoroler.control;

import Game.game.Contoroler.thingInGame.impact;
import Game.game.model.Move.Movable;
import Game.game.model.Move.follower;
import Game.game.model.characterModel.ObjectInGame;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.model.characterModel.epsilonFriend.bolt;
import Game.game.model.closeFRame.CLOSEABLE;
import Game.game.model.collision.Collidable;
import Game.game.model.collision.CollisionState;
import Game.game.view.PanelInGame.frameInGame;
import Game.game.view.PanelInGame.panelInGameView;
import Game.game.view.characterView.DrawAbleObject;
import Game.game.view.characterView.shape.ovalShape;

import java.util.ArrayList;

import static Game.Data.constants.*;
import static Game.game.Contoroler.control.Controller.*;
import static Game.game.model.Move.Movable.MOVE_ABLE;
import static Game.game.model.characterModel.ObjectInGame.objectInGames;
import static Game.game.model.characterModel.Panels.PanelInGame.PANELS;
import static Game.game.model.collision.Collidable.*;
import static Game.game.model.shooting.Aoe.AOES;

public class Update {
    public static boolean wait = false;
    public static Thread CLOSE_FRAME;
    public static Thread FRAME_UPDATE;
    public static Thread MODEL_UPDATE;
    public static boolean stopCLOSFRAME = true;
    public static boolean stopFRAMEUPDATE = true;
    public static boolean stpoUpdateModel = true;
    public static boolean enemyTakeDamage = false;
    public static void makeModel() {
        stpoUpdateModel = true;
        MODEL_UPDATE = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        updateModel();
                    } catch (Exception e) {
                    }

                    try {
                        Thread.sleep((long) MODEL_UPDATE_TIME);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        MODEL_UPDATE.start();
    }

    public static void makeFRame() {
        stopFRAMEUPDATE = true;
        FRAME_UPDATE = new
                Thread(new Runnable() {
            @Override
            public void run() {
                while (stopFRAMEUPDATE) {
                    try {
                        updateView();
                    } catch (Exception r) {
                    }
                    try {
                        Thread.sleep((long) FRAME_UPDATE_TIME);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        FRAME_UPDATE.start();
    }

    public static void makeClose() {
        stopCLOSFRAME = true;
        CLOSE_FRAME = new

                Thread(new Runnable() {
            @Override
            public void run() {
                while (stopCLOSFRAME) {
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
    }
    public Update() {

//        FRAME_UPDATE = new
//
//                Timer((int) FRAME_UPDATE_TIME, e ->
//
//                        updateView()) {
//                    {
//                        setCoalesce(true);
//                    }
//                }
//
//        ;
        makeFRame();
        makeClose();
        makeModel();
        finish = true;
    }

    public static boolean finish = true;

    private static void updateModel() {
        for (int j = 0; j < MOVE_ABLE.size(); j++) {
            Movable a = MOVE_ABLE.get(j);

            if (a instanceof follower) {
                ((follower) a).follow();
            }
            if (a instanceof PanelInGame) continue;
            if (a instanceof Epsilon && finish) {
                a.move();
            } else if (!(a instanceof Epsilon)) {
                if (Movable.fillses.dontComeNewrMe && a.getAnchor().distance(Epsilon.getEpsilon().getCenter()) < 50)
                    continue;
                if (Movable.fillses.stopEveryThing && (!(a instanceof bolt && ((bolt) a).isGood()))) continue;
                a.move();
            }
        }
        CreateAllGeometries();
        for (int j = 0; j < AOES.size(); j++) {
            AOES.get(j).TotalDelDamage();
        }
        for (int i = 0; i < collidables.size() - 1; i++) {
            Collidable c1 = collidables.get(i);
            for (int j = i + 1; j < collidables.size(); j++) {
                Collidable c2 = collidables.get(j);
                if (c1.collide(c2) && c2.collide(c1)) {
                    try {
                    if ((c1 instanceof PanelInGame || c2 instanceof PanelInGame) || (c1.getAnchor().distance(c2.getAnchor()) <= c1.getMaxR() + c2.getMaxR() + COLLISION_SENSITIVITY)) {
                        CollisionState collision = c1.checkCollision(c2);
                        if (collision.isCollision()) {
                            if (!(c1 instanceof PanelInGame && c2 instanceof Epsilon) && !(c2 instanceof PanelInGame && c1 instanceof Epsilon)) {
                                if (!(c1 instanceof PanelInGame) || !(c2 instanceof PanelInGame))
                                    if (c1 instanceof Epsilon && enemyTakeDamage) ((ObjectInGame) c2).getHit(2);
                                    else if (c2 instanceof Epsilon && enemyTakeDamage) ((ObjectInGame) c1).getHit(2);
                                new impact(PointFloutToDouble(collision.getCollisionPoint()));
                            }
                            collision.PlayCollisionAction();
                        }
                    }
                    } catch (NullPointerException p) {
                    }
                }
            }
        }
    }

    int i = 0;

    private static void closeThePanel() {
//        System.out.println(i);
//        i++;
        for (CLOSEABLE closeable : CLOSEABLE.CLOSEABLES) {
            closeable.reduceFrame();
        }
    }

    private static void updateView() {
//        System.out.println("draw start");
        String time = convertToMinSec(System.nanoTime() - Game.game.gameApplication.getGameApplication().timeOfStart);
        String exp = ((Integer) ((int) Epsilon.getEpsilon().getExp())).toString();
        String Hp = ((Integer) ((int) Epsilon.getEpsilon().getHP())).toString() + "/" + ((Integer) ((int) Epsilon.getEpsilon().getMaxHp())).toString();
        findNotifPanel("Hp").fixDetail(Hp);
        findNotifPanel("exp").fixDetail(exp);
        findNotifPanel("time").fixDetail(time);
//        System.out.println("hi");
        for (int j = 0; j < PANELS.size(); j++) {
            PanelInGame p = PANELS.get(j);

            panelInGameView panel = findPanelView(p.getId());
            if (panel == null) throw new NullPointerException();
            panel.fixDetail((int) p.getX(), (int) p.getY(), p.getSize(), p.getBackGrandColor());
            panel.restartObjectInside();
            for (int k = 0; k < objectInGames.size(); k++) {
                ObjectInGame object = objectInGames.get(k);
                panel.addToObjectInside(object.getDrawAbleObject(p));
            }
//            for (int j = 0; j < panel.getObjectForDraw().size(); j++) {
//                int numNEc = findNecropick(panel.getObjectForDraw(), j);
//                if (numNEc != -1) panel.setObjectForDraw(swich(panel.getObjectForDraw(), j, numNEc));
//                else break;
//
//            }
            panel.setObjectForDraw(sendTheArchToFirst(panel.getObjectForDraw()));
        }
        frameInGame.getFrame().repaint();
    }

    private static ArrayList<DrawAbleObject> sendTheArchToFirst(ArrayList<DrawAbleObject> drawAbleObjects) {
        ArrayList<DrawAbleObject> res = new ArrayList<>(drawAbleObjects);
        if (res == null) return drawAbleObjects;
        boolean NoFinish = true;
        int first = 0;
        while (NoFinish) {
            for (int j = (first + 1); j < res.size(); j++) {
                if (res.get(j) == null) {
                    continue;
                }
                if (res.get(j) instanceof ovalShape) {
                    swich(res, first, j);
                    first++;
                    if (first == res.size() - 1) NoFinish = false;
                    break;
                } else if (j == res.size() - 1) {
                    NoFinish = false;
                }
            }
            break;
        }
        return res;
    }
}
