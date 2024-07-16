package Game.game.Contoroler.control;

import Game.game.Contoroler.thingInGame.impact;
import Game.game.gameApplication;
import Game.game.model.Move.Moveable;
import Game.game.model.Move.follower;
import Game.game.model.characterModel.Enemy.Necropick;
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


import java.util.ArrayList;

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
        String Hp = ((Integer) ((int) Epsilon.getEpsilon().getHP())).toString() + "/" + ((Integer) ((int) Epsilon.getEpsilon().getMaxHp())).toString();
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
                panel.addToObjectInside(object.getDrawAbleObject(p));
                if (object instanceof Necropick)
                    swich(panel.getObjectForDraw(), 0, panel.getObjectForDraw().size() - 1);
            }
//            for (int j = 0; j < panel.getObjectForDraw().size(); j++) {
//                int numNEc = findNecropick(panel.getObjectForDraw(), j);
//                if (numNEc != -1) panel.setObjectForDraw(swich(panel.getObjectForDraw(), j, numNEc));
//                else break;
//
//            }
        }
        frameInGame.getFrame().repaint();
    }

    private int findNecropick(ArrayList<?> arrayList, int start) {
        for (int j = start; j < arrayList.size(); j++) {
            if (arrayList.get(j) instanceof Necropick) return (j);
        }
        return -1;
    }
}
