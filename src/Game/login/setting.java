package Game.login;

import Game.Data.constants;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;

import static Game.Data.constants.*;

public class setting extends JFrame {
    public static final int X_PLACE = LOGIN_WIDTH / 3;
    public static final int FIRST_Y_PLACE = LOGIN_HEIGHT / 4;
    private static float volumeSound = -30;
    private static float volumeDifficulty = 1;
    private static float volumeSpeed = 2;

    private static gameDifficulty gameDifficulty = setting.gameDifficulty.normal;

    public enum gameDifficulty {
        easy, normal, hard
    }

    public enum gameSpeed {
        verySlow, Slow, normal, fast, veryFast
    }

    private static gameSpeed gameSpeed = setting.gameSpeed.normal;

    private JSlider soundController;
    private JSlider speed;
    private JSlider difficulty;
    private JPanel mainPanel;
    private static final int width = 450;
    private static final int potoSize = 45;
    private JButton EXIT;
    private JLabel soundPhoto;
    private JLabel difficultyPhoto;
    private JLabel SpeedPhoto;

    public setting () {
        setIconImage (new ImageIcon (constants.PROJECT_ICON_PATH).getImage ());
        setUndecorated (true);
        setFocusable (true);
        requestFocus ();
        requestFocusInWindow ();
        setBackground (new Color (0, 0, 0, 0));
        setSize (new Dimension (LOGIN_WIDTH, LOGIN_HEIGHT));
        setLocationRelativeTo (null);
        setVisible (true);
        setLayout (null);
        mainPanel = new JPanel ();
        mainPanel.setFocusable (true);
        mainPanel.requestFocus ();
        mainPanel.requestFocusInWindow ();
        mainPanel.setLayout (null);
        mainPanel.setBorder (BorderFactory.createLineBorder (PANEL_BAR_GRAND, 6));
        mainPanel.setBackground (PANEL_BACK_GRAND);
        mainPanel.setVisible (true);
        mainPanel.setOpaque (true);
        this.add (mainPanel);
        mainPanel.setBackground (Color.BLACK);
        setContentPane (mainPanel);
        soundController = new JSlider (JSlider.HORIZONTAL, -80, 6, (int) volumeSound);
        soundController.setBounds (X_PLACE, FIRST_Y_PLACE, width, potoSize);
        soundController.setVisible (true);
        soundController.setOpaque (true);
        soundController.setFocusable (true);
        soundController.requestFocus ();
        soundController.requestFocusInWindow ();
        soundController.addChangeListener (new ChangeListener () {
            @Override
            public void stateChanged (ChangeEvent e) {
                System.out.println (soundController.getValue ());
                volumeSound = (float) soundController.getValue ();
            }
        });
        mainPanel.add (soundController);
        soundPhoto = new JLabel (new ImageIcon ("src\\sources\\photo\\images.png"));
        soundPhoto.setBounds (X_PLACE / 2, FIRST_Y_PLACE, potoSize, potoSize);
        soundPhoto.setVisible (true);
        soundPhoto.setOpaque (true);
        mainPanel.add (soundPhoto);
        EXIT = new JButton ();
        EXIT.setVisible (true);
        EXIT.setBounds (6, 6, 70, 41);
        EXIT.setIcon (new ImageIcon ("src\\sources\\photo\\Exit.png"));
        EXIT.addActionListener (new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                dispose ();
                new loginFrame ();
            }
        });
        mainPanel.add (EXIT);
        speed = new JSlider ();
        speed = new JSlider (JSlider.HORIZONTAL, 0, 4, (int) volumeSpeed);
        speed.setBounds (X_PLACE, FIRST_Y_PLACE * 2, width, potoSize);
        speed.setVisible (true);
        speed.setOpaque (true);
        speed.setFocusable (true);
        speed.requestFocus ();
        speed.requestFocusInWindow ();
        speed.addChangeListener (new ChangeListener () {
            @Override
            public void stateChanged (ChangeEvent e) {
                volumeSpeed = speed.getValue ();
                if (volumeSpeed ==0) {
                    gameSpeed = setting.gameSpeed.verySlow;
                } else if (volumeSpeed ==1) {
                    gameSpeed = setting.gameSpeed.Slow;
                } else if (volumeSpeed ==2) {
                    gameSpeed = setting.gameSpeed.normal;
                } else if (volumeSpeed  ==3) {
                    gameSpeed = setting.gameSpeed.fast;
                } else {
                    gameSpeed = setting.gameSpeed.veryFast;
                }
            }
        });
        SpeedPhoto = new JLabel (new ImageIcon ("src\\sources\\photo\\speed.png"));
        SpeedPhoto.setVisible (true);
        SpeedPhoto.setOpaque (true);
        SpeedPhoto.setBounds (X_PLACE - 170, FIRST_Y_PLACE * 2, 105, 38);
        mainPanel.add (SpeedPhoto);
        mainPanel.add (speed);
        difficulty = new JSlider ();
        difficulty = new JSlider (JSlider.HORIZONTAL, 0, 2, (int) volumeDifficulty);
        difficulty.setBounds (X_PLACE, FIRST_Y_PLACE * 3, width, potoSize);
        difficulty.setVisible (true);
        difficulty.setOpaque (true);
        difficulty.setFocusable (true);
        difficulty.requestFocus ();
        difficulty.requestFocusInWindow ();
        difficulty.addChangeListener (new ChangeListener () {
            @Override
            public void stateChanged (ChangeEvent e) {
                volumeDifficulty = difficulty.getValue ();
                System.out.println (volumeDifficulty);
                if (difficulty.getValue () == 0) {
                    gameDifficulty = setting.gameDifficulty.easy;
                    System.out.println ("easy");
                } else if (difficulty.getValue () == 1) {
                    gameDifficulty = setting.gameDifficulty.normal;
                    System.out.println ("normal");
                } else {
                    gameDifficulty = setting.gameDifficulty.hard;
                    System.out.println ("hard");
                }

            }
        });
        difficultyPhoto = new JLabel ();
        difficultyPhoto.setVisible (true);
        difficultyPhoto.setIcon (new ImageIcon ("src\\sources\\photo\\difficulty.png"));
        difficultyPhoto.setBounds (X_PLACE - 150, FIRST_Y_PLACE * 3, 70, 41);
        difficultyPhoto.setOpaque (true);
        mainPanel.add (difficultyPhoto);
        mainPanel.add (difficulty);
    }

    public static float getVolumeSound () {
        return volumeSound;
    }

    public static setting.gameDifficulty getGameDifficulty () {
        return gameDifficulty;
    }

    public static setting.gameSpeed getGameSpeed () {
        return gameSpeed;
    }
}
