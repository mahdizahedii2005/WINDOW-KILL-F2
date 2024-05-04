package Game.game.view.waveAnimation;

import Game.game.Contoroler.Spawn;
import Game.game.Contoroler.Update;
import Game.game.model.characterModel.Epsilon;
import Game.game.view.frameInGame;
import Game.game.view.inputListener;
import Game.login.loginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class lostPanel extends JPanel implements Runnable {
    private JButton EXIT;
    private JLabel totalExp;

    public lostPanel () {
        run ();
    }

    @Override
    public void run () {
        Update.FRAME_UPDATE.stop ();
        Update.MODEL_UPDATE.stop ();
        setSize (200, 200);
        setLocation ((frameInGame.getFrame ().getWidth () - 200) / 2, (frameInGame.getFrame ().getHeight () - 200) / 2);
        setVisible (true);
        setOpaque (true);
        setFocusable (true);
        requestFocus ();
        requestFocusInWindow ();
        setLayout (null);
        setBorder (BorderFactory.createLineBorder (Color.WHITE, 5));
        setBackground (Color.YELLOW);
        EXIT = new JButton ();
        EXIT.setVisible (true);
        EXIT.setBounds (6, 6, 70, 41);
        EXIT.setIcon (new ImageIcon ("src\\sources\\photo\\Exit.png"));
        JPanel me = this;
        EXIT.addActionListener (new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                Update.FRAME_UPDATE.stop ();
                Update.MODEL_UPDATE.stop ();
                Update.CLOSE_FRAME.stop ();
                new loginFrame ();
                Spawn.setSpawnNull ();
                frameInGame.getFrame ().dispose ();
            }
        });
        this.add (EXIT);
        totalExp = new JLabel ("your total Exp : " + Epsilon.getEpsilon ().getTotalExp ());
        totalExp.setBounds ((getWidth () - 200) / 2, (getHeight () - 30) / 2, 200, 30);
        totalExp.setVisible (true);
        totalExp.setFont (new Font ("Arial", Font.ITALIC, 16));
        totalExp.setOpaque (true);
        totalExp.setBackground (Color.YELLOW);
        add (totalExp);
        frameInGame.getFrame ().add (this);
    }
}
