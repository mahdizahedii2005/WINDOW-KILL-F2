package Game.game;

import Game.game.Contoroler.building.Spawn;
import Game.game.Contoroler.control.Update;
import Game.game.Contoroler.player.soundPlayer;
import Game.game.model.characterModel.Panels.NonIsometricPanel;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.view.PanelInGame.frameInGame;
import Game.game.view.inputListener;
import Game.login.setting;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import static Game.Data.constants.*;
import static Game.game.model.Move.impactAble.impactAblesList;
import static Game.game.model.Move.linearMotion.MOVE_ABLE;
import static Game.game.model.characterModel.ObjectInGame.objectInGames;
import static Game.game.model.collision.Collidable.collidables;

public class gameApplication implements Runnable {
    public gameApplication() {
        gameApplication = this;
    }

    private static gameApplication gameApplication;

    public static Game.game.gameApplication getGameApplication() {
        return gameApplication;
    }

    public long timeOfStart;
    private frameInGame frameOfGame;

    private void CLoseAllProg() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        robot.keyPress(KeyEvent.VK_WINDOWS);
        robot.keyPress(KeyEvent.VK_D);
        robot.keyRelease(KeyEvent.VK_D);
        robot.keyRelease(KeyEvent.VK_WINDOWS);
    }

    private void fixDifficallity() {
        switch (setting.getGameDifficulty()) {
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
    }

    private void fixSpeed() {
        switch (setting.getGameSpeed()) {
            case verySlow -> SPEED = SPEED / 1.5;
            case Slow -> SPEED = SPEED / 1.25;
            case fast -> SPEED = SPEED * 1.25;
            case veryFast -> SPEED = SPEED * 1.5;
        }

    }

    private void initGeraghic() {
        frameOfGame = new frameInGame();
    }

    private void initGame() {
        NonIsometricPanel a = new NonIsometricPanel(FIRST_PANEL_RECTANGLE.x, FIRST_PANEL_RECTANGLE.y, FIRST_PANEL_DIMENSION.height, FIRST_PANEL_DIMENSION.width, false, 0);
        new Epsilon(new Point2D.Double(frameOfGame.getWidth() / 2d, frameOfGame.getHeight() / 2d), (int) RADIUS_OF_EPSILON, a);
        new Update();
        new inputListener();
        new Spawn();

    }

    public static soundPlayer first = new soundPlayer("src\\sources\\song\\start1.wav");
    public static soundPlayer second = new soundPlayer("src\\sources\\song\\start2.wav");

    @Override
    public void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    first.play();
                    try {
                        Thread.sleep(160000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    second.play();
                    try {
                        Thread.sleep(180000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
        CLoseAllProg();
        timeOfStart = System.nanoTime();
        objectInGames.clear();
        collidables.clear();
        impactAblesList.clear();
        MOVE_ABLE.clear();
        fixDifficallity();
        fixSpeed();
        initGeraghic();
        initGame();
    }
}
