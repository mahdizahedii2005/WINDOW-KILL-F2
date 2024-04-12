package Game.game.Contoroler;

import Game.game.model.Move.Moveable;
import Game.game.model.characterModel.Enemy;
import Game.game.model.characterModel.Epsilon;
import Game.game.view.DrawAble;
import Game.game.view.characterView.enemyView;
import Game.game.view.characterView.epsilonView;
import Game.game.view.frameInGame;
import Game.game.view.panelInGame;

import javax.swing.*;

import static Game.Data.constants.*;
import static Game.game.Contoroler.Controller.*;
import static Game.game.view.DrawAble.DRAW_ABLES;

public class Update {
    public Update() {
        new Timer(PANEL_CLOSE_DELAY, e -> closeThePanel()) {{
            setCoalesce(true);
        }}.start();
        new Timer((int) FRAME_UPDATE_TIME, e -> updateView()) {{
            setCoalesce(true);
        }}.start();
        new Timer((int) MODEL_UPDATE_TIME, e -> updateModel()) {{
            setCoalesce(true);
        }}.start();
    }

    private void updateModel() {
        for (Moveable a : Moveable.moveAble) {
            a.move();
            try {
                Epsilon aa = (Epsilon) (a);
            } catch (Exception r) {
            }
        }
    }

    private void closeThePanel() {
        panelInGame.getPanel().ReduceSizeOfFrame();
    }

    private void updateView() {
//        if (panelInGame.getPanel().isReduce() && !panelInGame.getPanel().validSize()) {
//            panelInGame.getPanel().stopReduceSizeOfFrame();
//        } else if (!panelInGame.getPanel().isReduce() && panelInGame.getPanel().validSize()) {
//            panelInGame.getPanel().startReduceSizeOfFrame();
//        }
        for (DrawAble d : DRAW_ABLES) {
            if (d instanceof enemyView) {
                enemyView enemy = (enemyView) d;
                Enemy e = (Enemy) findObjectModel((enemy.getId()));
                // TODO: ۰۸/۰۴/۲۰۲۴ only for this this faze you have to fix the draw Jpanel for the other faze in this class and fix the final class for other state
                MokhtasatPoint m = fixThePoint(e.getxPoint(), e.getyPoint(), whatPanelForDraw(e.getxPoint(), e.getyPoint()));
                d.fixDetail(whatPanelForDraw(e.getxPoint(), e.getyPoint()), m.getxPoint(), m.getyPoint(), e.getnPoint(), e.getColor());
            } else if (d instanceof epsilonView) {
                epsilonView e = (epsilonView) d;
                Epsilon epsilon = (Epsilon) findObjectModel(e.getId());
                fixThePoint(epsilon.getCenter(), whatPanelForDraw(epsilon.getCenter()));
                e.fixDetail(fixThePoint(epsilon.getCenter(), whatPanelForDraw(epsilon.getCenter())), epsilon.getRadius(), whatPanelForDraw(epsilon.getCenter()));
                System.out.println(8);
            }
        }
        frameInGame.getFrame().repaint();
    }

}
