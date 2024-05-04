package Game.game;

import Game.game.Contoroler.Spawn;
import Game.game.Contoroler.Update;
import Game.game.Contoroler.soundPlayer;
import Game.game.model.characterModel.*;
import Game.game.model.collision.Collidable;
import Game.game.view.frameInGame;
import Game.game.view.inputListener;
import Game.login.setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import static Game.Data.constants.*;
import static Game.game.model.Move.Moveable.moveAble;
import static Game.game.model.Move.impactAble.impactAblesList;
import static Game.game.model.collision.Collidable.collidables;
import static Game.game.view.DrawAble.DRAW_ABLES;

public class gameApplication implements Runnable {
    public gameApplication () {
        gameApplication = this;
    }

    private static gameApplication gameApplication;

    public static Game.game.gameApplication getGameApplication () {
        return gameApplication;
    }

    @Override
    public void run () {
        DELAY_OF_CLOSE_FRAME = 1;
        ObjectInGame.objectInGames = new ArrayList<> ();
        prize.prizeArrayList = new ArrayList<> ();
        rasEpsilon.rasArrayList = new ArrayList<> ();
        bolt.boltList = new ArrayList<> ();
        for (int i = 0 ; i < collidables.size () ; ) {
            collidables.remove (collidables.get (i));
        }
        for (int i = 0 ; i < impactAblesList.size () ; ) {
            impactAblesList.remove (impactAblesList.get (i));
        }
        for (int i = 0 ; i < moveAble.size () ; ) {
            moveAble.remove (moveAble.get (i));
        }
        for (int i = 0 ; i < DRAW_ABLES.size () ; ) {
            DRAW_ABLES.remove (DRAW_ABLES.get (i));
        }
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
        switch (setting.getGameSpeed ()) {
            case verySlow -> SPEED = SPEED / 1.5;
            case Slow -> SPEED = SPEED / 1.25;
            case fast -> SPEED = SPEED * 1.25;
            case veryFast -> SPEED = SPEED * 1.5;
        }
        switch (setting.getGameDifficulty ()) {
            case easy -> {
                Spawn.zaribSpawn = 1;
                Spawn.zarib = 8;
                PRIZE_TIME = 10000;
            }
            case hard -> {
                Spawn.zaribSpawn = 3;
                Spawn.zarib = 12;
                PRIZE_TIME = 5000;
            }
        }
        Frame f = new frameInGame ();
        new originalPanel ();
        new Timer (50000, new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                soundPlayer.play ("src\\sources\\song\\download-white-voice_1_.wav", -62);
            }
        });
        new Epsilon (new Point2D.Double (f.getWidth () / 2d, f.getHeight () / 2d), (int) RADIUS_OF_EPSILON);
        new Update ();

//        Thread close = new Thread (new firstState (this));
//        close.run ();
//        try {
//            synchronized (this) {
//                close.wait ();
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException (e);
//        }
        new inputListener ();
        new Spawn ();
        //  new rasEpsilon (4);
    }
}
