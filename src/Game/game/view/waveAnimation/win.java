package Game.game.view.waveAnimation;

import Game.game.Contoroler.Update;
import Game.game.model.characterModel.Epsilon;
import Game.game.view.panelInGame;
import Game.login.SkillTree;

import javax.security.auth.login.AccountNotFoundException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class win extends Animation {
    public win () {
        super (new Dimension (350, 92), "src\\sources\\photo\\you win.png", "src\\sources\\song\\victory.wav");
        SkillTree.increaseTotalExp ((int) Epsilon.getEpsilon ().getTotalExp ());
        new Timer (5, new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                Update.CLOSE_FRAME.stop ();
                Epsilon.getEpsilon ().setRadius (Epsilon.getEpsilon ().getRadius () + 1);
                if (Epsilon.getEpsilon ().getRadius () >= Math.min (panelInGame.getPanel ().getWidth (), panelInGame.getPanel ().getHeight ())) {

                    new lostPanel ();
                    ((Timer) e.getSource ()).stop ();
                }
            }
        }).start ();
    }
}
