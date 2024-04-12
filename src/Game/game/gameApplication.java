package Game.game;

import Game.game.Contoroler.BuilderHelper;
import Game.game.Contoroler.Update;
import Game.game.model.characterModel.Epsilon;
import Game.game.model.characterModel.SquarantineModel;
import Game.game.model.characterModel.TrigorathModel;
import Game.game.view.frameInGame;
import Game.game.view.inputListener;
import Game.game.view.panelInGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;

import static Game.Data.constants.*;

public class gameApplication implements Runnable {
    @Override
    public void run() {
        Frame f = frameInGame.getFrame();
        panelInGame p = panelInGame.getPanel();
        new Epsilon(new Point2D.Double(f.getWidth() / 2d, f.getHeight() / 2d), (int) RADIUS_OF_EPSILON);
//         new Timer(PANEL_CLOSE_DELAY, new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (p.validSize()){
//                p.setSize(new Dimension(p.getWidth() - 2, p.getHeight() - 2));
//                p.setLocation(p.getX() + 1, p.getY() + 1);
//                p.repaint();
//            }else {
//                    Timer t = (Timer) (e.getSource());
//                    t.stop();
//                }
//            }
//        });
//        try {
//            wait(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        BuilderHelper.trigorathBuilder(new Point2D.Double((f.getWidth() / 2d) - 80, (f.getHeight() / 2d) - 75), new Point2D.Double(((f.getWidth() / 2d) - 100), (f.getHeight() / 2d) - 150));
//        BuilderHelper.squarantineBuilder(new Point2D.Double((f.getWidth() / 2d) + 80, (f.getHeight() / 2d) + 75), new Point2D.Double(((f.getWidth() / 2d) + 100), (f.getHeight() / 2d) + 150));
        new inputListener();
        new Update();
    }
}
