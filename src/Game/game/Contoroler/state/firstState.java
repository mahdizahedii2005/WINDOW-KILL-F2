package Game.game.Contoroler.state;

import Game.game.model.characterModel.ObjectInGame;
import Game.game.model.characterModel.originalPanel;

public class firstState implements Runnable {
    Object s;

    public firstState (Object d) {
        s = d;
    }

    @Override
    public void run () {
        while (originalPanel.getPanel ().ReduceSizeOfFrame ()) {
            try {
                Thread.sleep (400);
            } catch (InterruptedException e) {
                throw new RuntimeException (e);
            }
            synchronized (this){
            this.notifyAll ();
        }}
    }
}
