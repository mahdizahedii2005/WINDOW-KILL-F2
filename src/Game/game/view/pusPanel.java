package Game.game.view;

import Game.Data.constants;
import Game.game.Contoroler.Update;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class pusPanel extends JPanel implements Runnable {
    private JButton EXIT;
    private JLabel pus;

    public pusPanel () {
    }

    @Override
    public void run () {
        setSize (200, 200);
        setLocation ((frameInGame.getFrame ().getWidth () - 200) / 2, (frameInGame.getFrame ().getHeight () - 200) / 2);
        setVisible (true);
        setOpaque (true);
        setFocusable (true);
        requestFocus ();
        requestFocusInWindow ();
        setLayout (null);
        setBorder (BorderFactory.createLineBorder (Color.BLACK, 5));
        setBackground (Color.RED);
        frameInGame.getFrame ().add (this);
        EXIT = new JButton ();
        EXIT.setVisible (true);
        EXIT.setBounds ((getWidth () - 70) / 2, (getHeight () - 41) / 2, 70, 41);
        EXIT.setIcon (new ImageIcon ("src\\sources\\photo\\Exit.png"));
        JPanel me = this;
        EXIT.addActionListener (new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                frameInGame.getFrame ().remove (me);
                Update.FRAME_UPDATE.start ();
                Update.MODEL_UPDATE.start ();
                inputListener.aaa = true;
                frameInGame.getFrame ().repaint ();
            }
        });
        this.add (EXIT);
        pus = new JLabel ("   STOP");
        pus.setBounds (((getWidth () - 70) / 2) , ((getHeight () - 41) / 2) - 50, 70, 41);
        pus.setVisible (true);
        pus.setOpaque (true);
        pus.setForeground (Color.WHITE);
        pus.setBackground (Color.red);
        add (pus);
        repaint ();

    }
}
