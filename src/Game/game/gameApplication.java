package Game.game;

import Game.game.Contoroler.BuilderHelper;
import Game.game.Contoroler.Spawn;
import Game.game.Contoroler.Update;
import Game.game.Contoroler.soundPlayer;
import Game.game.model.characterModel.Epsilon;
import Game.game.model.characterModel.SquarantineModel;
import Game.game.model.characterModel.TrigorathModel;
import Game.game.model.characterModel.originalPanel;
import Game.game.view.frameInGame;
import Game.game.view.inputListener;
import Game.game.view.panelInGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import static Game.Data.constants.*;

public class gameApplication implements Runnable {
    @Override
    public void run () {
        {
            Robot robot = null;
            try {
                robot = new Robot ();
            } catch (AWTException e) {
                throw new RuntimeException (e);
            }
            robot.keyPress (KeyEvent.VK_WINDOWS);
            robot.keyPress (KeyEvent.VK_D);
            robot.keyRelease (KeyEvent.VK_D);
            robot.keyRelease (KeyEvent.VK_WINDOWS);
        }
        Frame f = frameInGame.getFrame ();
        new originalPanel ();
        soundPlayer.play ("src\\sources\\song\\download-white-voice_1_.wav",-62);
        new Epsilon (new Point2D.Double (f.getWidth () / 2d, f.getHeight () / 2d), (int) RADIUS_OF_EPSILON);
        new inputListener ();
        Spawn.getSpawn ();
        new Update ();

    }
}
