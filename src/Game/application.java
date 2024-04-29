package Game;

import Game.login.loginFrame;

public class application implements Runnable {
    @Override
    public void run () {
        new loginFrame ();
    }
}
