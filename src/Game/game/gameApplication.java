package Game.game;

import Game.game.Contoroler.building.Spawn;
import Game.game.Contoroler.control.Update;
import Game.game.Contoroler.player.soundPlayer;
import Game.game.model.characterModel.*;
import Game.game.model.characterModel.Panels.rigidPanel;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.view.PanelInGame.frameInGame;
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
        new rigidPanel(FIRST_PANEL_RECTANGLE.x, FIRST_PANEL_RECTANGLE.y, FIRST_PANEL_DIMENSION.height, FIRST_PANEL_DIMENSION.width);
        new Epsilon(new Point2D.Double(frameOfGame.getWidth() / 2d, frameOfGame.getHeight() / 2d), (int) RADIUS_OF_EPSILON);
        new Update();
        new inputListener();
        new Spawn();
    }

    @Override
    public void run() {
        CLoseAllProg();
        timeOfStart = System.nanoTime();
        ObjectInGame.objectInGames = new ArrayList<>();
        collidables.clear();
        impactAblesList.clear();
        moveAble.clear();
        fixDifficallity();
        fixSpeed();
        initGeraghic();
        initGame();
        new Timer(50000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundPlayer.play("src\\sources\\song\\download-white-voice_1_.wav", -62);
            }
        });
    }
}
