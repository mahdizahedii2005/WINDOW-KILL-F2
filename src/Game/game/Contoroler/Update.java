package Game.game.Contoroler;

import Game.game.model.Move.Moveable;
import Game.game.model.Move.follower;
import Game.game.model.characterModel.*;
import Game.game.model.collision.Collidable;
import Game.game.model.shooting.shootGiver;
import Game.game.view.DrawAble;
import Game.game.view.characterView.*;
import Game.game.view.frameInGame;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

import static Game.Data.constants.*;
import static Game.game.Contoroler.Controller.*;
import static Game.game.model.characterModel.ObjectInGame.objectInGames;
import static Game.game.model.characterModel.bolt.boltList;
import static Game.game.view.DrawAble.DRAW_ABLES;
public class Update {
    public static Timer CLOSE_FRAME;
    public static Timer FRAME_UPDATE;
    public static Timer MODEL_UPDATE;

    public Update () {
        CLOSE_FRAME = new Timer ((int) DELAY_OF_CLOSE_FRAME, e -> closeThePanel ()) {{
            setCoalesce (true);
        }};
        CLOSE_FRAME.start ();
        FRAME_UPDATE = new Timer ((int) FRAME_UPDATE_TIME, e -> updateView ()) {{
            setCoalesce (true);
        }};
        FRAME_UPDATE.start ();
        MODEL_UPDATE = new Timer ((int) MODEL_UPDATE_TIME, e -> updateModel ()) {{
            setCoalesce (true);
        }};
        MODEL_UPDATE.start ();
        finish = true;
    }

    public static boolean finish = true;

    private void updateModel () {
        for (Moveable a : Moveable.moveAble) {
            if (a instanceof follower) {
                ((follower) a).follow ();
            }
            if (a instanceof Epsilon && finish) {
                a.move ();
            } else if (!(a instanceof Epsilon)) {
                a.move ();
            }
        }
        for (int i = 0 ; i < boltList.size () ; i++) {
            bolt b = boltList.get (i);
            b.wallCollision (originalPanel.panelWall.up);
            b.wallCollision (originalPanel.panelWall.down);
            b.wallCollision (originalPanel.panelWall.right);
            b.wallCollision (originalPanel.panelWall.left);
            for (int j = 0 ; j < objectInGames.size () ; j++) {
                ObjectInGame a = objectInGames.get (j);
                if (a instanceof shootGiver) {
                    b.boltCollide ((shootGiver) a);
                }
            }
        }
        for (int i = 0 ; i < prize.prizeArrayList.size () ; i++) {
            if (Epsilon.getEpsilon ().prizeCollide (prize.prizeArrayList.get (i))) {
                prize.prizeArrayList.get (i).Action ();
            }
        }
        rasEpsilon.UpdateRAS ();
        ArrayList<Collidable> collidables = Collidable.collidables;
        for (int i = 0 ; i < collidables.size () ; i++) {
            for (int j = i + 1 ; j < collidables.size () ; j++) {
                if (collidables.get (i).getCenter ().distance (collidables.get (j).getCenter ()) < 50) {// TODO: ۲۳/۰۴/۲۰۲۴ prize part <----
                    if (collidables.get (i).collision (collidables.get (j))) {
                        if (collidables.get (i) instanceof rasEpsilon.ras || collidables.get (j) instanceof rasEpsilon.ras) {
                            if (collidables.get (i) instanceof Enemy) {
                                Epsilon.getEpsilon ().setHP (Epsilon.getEpsilon ().getHP () + ((Enemy) collidables.get (i)).getDamageTaker ());
                                ((Enemy) collidables.get (i)).setHP (((Enemy) collidables.get (i)).getHP () - 5);
                            } else if (collidables.get (j) instanceof Enemy) {
                                ((Enemy) collidables.get (j)).setHP (((Enemy) collidables.get (j)).getHP () - 5);
                            }
                            return;
                        }
                        if (collidables.get (i) instanceof Epsilon || collidables.get (j) instanceof Epsilon) {
                            try {
                                Epsilon.getEpsilon ().setHP (Math.max (0, Epsilon.getEpsilon ().getHP () - ((Enemy) collidables.get (i)).getDamageTaker ()));
                            } catch (ClassCastException e) {
                                try {
                                    Epsilon.getEpsilon ().setHP (Math.max (0, Epsilon.getEpsilon ().getHP () - ((Enemy) collidables.get (j)).getDamageTaker ()));
                                } catch (ClassCastException r) {
                                }
                            }
                        }
                        new impact (collidables.get (i).findCollisionPoint (collidables.get (j)));
                    }
                }
            }
        }
    }

    private void closeThePanel () {
        originalPanel.getPanel ().ReduceSizeOfFrame ();
    }

    private void updateView () {

        for (DrawAble d : DRAW_ABLES) {
            if (d instanceof enemyView) {
                enemyView enemy = (enemyView) d;
                Enemy e = (Enemy) findObjectModel (enemy.getId ());
                // TODO: ۰۸/۰۴/۲۰۲۴ only for this this faze you have to fix the draw Jpanel for the other faze in this class and fix the final class for other state
                MokhtasatPoint m = fixThePoint (e.getxPoint (), e.getyPoint (), whatPanelForDraw (e.getxPoint (), e.getyPoint ()));
                d.fixDetail (whatPanelForDraw (e.getxPoint (), e.getyPoint (), true), m.getxPoint (), m.getyPoint (), e.getnPoint (), e.getColor ());
            } else if (d instanceof epsilonView) {
                epsilonView e = (epsilonView) d;
                Epsilon epsilon = (Epsilon) findObjectModel (e.getId ());
                fixThePoint (epsilon.getCenter (), whatPanelForDraw (epsilon.getCenter ()));
                e.fixDetail (fixThePoint (epsilon.getCenter (), whatPanelForDraw (epsilon.getCenter ())), epsilon.getRadius (), whatPanelForDraw (epsilon.getCenter ()));
//                System.out.println(FPS);
            } else if (d instanceof boltView) {
                boltView bv = (boltView) d;
                bolt bolt = (bolt) (findObjectModel (bv.getId ()));
                d.fixDetail (whatPanelForDraw (bolt.getCenter ()), Color.WHITE, fixThePoint (bolt.getCenter (), whatPanelForDraw (bolt.getCenter ())));
            } else if ((d instanceof PrizeView)) {
                PrizeView p = (PrizeView) d;
                prize prize = (prize) findObjectModel (p.getId ());
                p.fixDetail (whatPanelForDraw (prize.getCenter ()), prize.getColor (), fixThePoint (prize.getCenter (), whatPanelForDraw (prize.getCenter ())));
            } else if (d instanceof JPanel) {
                d.fixDetail ((int) originalPanel.getPanel ().getX (), (int) originalPanel.getPanel ().getY (), originalPanel.getPanel ().getSize (), PANEL_BACK_GRAND, PANEL_BAR_GRAND);
            } else if (d instanceof epsilonRas) {
                epsilonRas p = (epsilonRas) d;
                rasEpsilon.ras prize = (rasEpsilon.ras) findObjectModel (p.getId ());
                p.fixDetail (whatPanelForDraw (prize.getCenter ()), prize.getColor (), fixThePoint (prize.getCenter (), whatPanelForDraw (prize.getCenter ())));
            }
        }
        frameInGame.getFrame ().repaint ();
    }
}
