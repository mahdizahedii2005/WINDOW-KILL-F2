package Game.game.Contoroler;

import Game.game.gameApplication;
import Game.game.model.Move.Direction;
import Game.game.model.Move.Moveable;
import Game.game.model.Move.follower;
import Game.game.model.characterModel.*;
import Game.game.model.collision.Collidable;
import Game.game.model.shooting.shootGiver;
import Game.game.view.DrawAble;
import Game.game.view.characterView.PrizeView;
import Game.game.view.characterView.boltView;
import Game.game.view.characterView.enemyView;
import Game.game.view.characterView.epsilonView;
import Game.game.view.frameInGame;
import Game.game.view.panelInGame;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static Game.Data.constants.*;
import static Game.game.Contoroler.Controller.*;
import static Game.game.model.characterModel.ObjectInGame.objectInGames;
import static Game.game.model.characterModel.bolt.boltList;
import static Game.game.view.DrawAble.DRAW_ABLES;
import static Game.helper.addVectors;
import static Game.helper.relativeLocation;

public class Update {
    public static Timer t;

    public Update () {
        t = new Timer ((int) DELAY_OF_CLOSE_FRAME, e -> closeThePanel ()) {{
            setCoalesce (true);
        }};
        t.start ();
        new Timer ((int) FRAME_UPDATE_TIME, e -> updateView ()) {{
            setCoalesce (true);
        }}.start ();
        new Timer ((int) MODEL_UPDATE_TIME, e -> {
            updateModel ();
        }) {{
            setCoalesce (true);
        }}.start ();
    }

    private void updateModel () {
        // closeThePanel ();
        for (Moveable a : Moveable.moveAble) {
            if (a instanceof follower) {
                ((follower) a).follow ();
            }
            a.move ();
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
        ArrayList<Collidable> collidables = Collidable.collidables;
        for (int i = 0 ; i < collidables.size () ; i++) {
            // TODO: ۲۳/۰۴/۲۰۲۴ prize part 
            for (int j = i + 1 ; j < collidables.size () ; j++) {
                if (collidables.get (i).collision (collidables.get (j))) {
//                    Point2D.Double colPoint = addVectors (collidables.get(i).getCenter (),collidables.get(j).getCenter ());
//                    if (collidables.get(i) instanceof Moveable){
//                        ((Moveable) collidables.get(i)).setDirection(new Direction (relativeLocation(collidables.get(i).getCenter (),colPoint)));
//                    }
//                    if (collidables.get(j) instanceof Moveable){
//                        ((Moveable) collidables.get(j)).setDirection(new Direction(relativeLocation(collidables.get(j).getCenter (),colPoint)));
//                    }
                    if (collidables.get (i) instanceof Epsilon || collidables.get (j) instanceof Epsilon) {
                        Epsilon.getEpsilon ().setHP (Math.max (0, Epsilon.getEpsilon ().getHP () - 5));
                    }
                    new impact (collidables.get (i).findCollisionPoint (collidables.get (j)));
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
            }
        }
        frameInGame.getFrame ().repaint ();
    }

}
