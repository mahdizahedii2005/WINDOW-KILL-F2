package Game.login;

import Game.Data.constants;
import Game.game.gameApplication;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class loginFrame extends JFrame {
    private JLabel photo;
    private JPanel mainPanel;
    private buttonLogin exit;
    private buttonLogin tutorial;
    private buttonLogin start;
    private buttonLogin setting;
    private buttonLogin skillTree;

    public loginFrame () {
        creatMainPanel ();
        creatFrame (mainPanel);
        creatPhoto ();

        setting = new buttonLogin (constants.LOGIN_SETTING_X, constants.LOGIN_SETTING_Y, new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                dispose ();
                new setting ();
            }
        });
        exit = new buttonLogin (constants.LOGIN_EXIT_X, constants.LOGIN_EXIT_Y, constants.LOGIN_EXIT_WIDTH, constants.LOGIN_EXIT_HEIGHT, new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                dispose ();
            }
        });
        tutorial = new buttonLogin (constants.LOGIN_TUTORIAL_X, constants.LOGIN_TUTORIAL_Y, new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {

            }
        });
        start = new buttonLogin (constants.LOGIN_START_X, constants.LOGIN_START_Y, new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                dispose ();
                new gameApplication ().run ();
            }
        });
        skillTree = new buttonLogin (constants.LOGIN_SKILL_TREE_X, constants.LOGIN_SKILL_TREE_Y, new AbstractAction () {
            @Override
            public void actionPerformed (ActionEvent e) {
                dispose ();
                new SkillTree ();
            }
        });
        mainPanel.add (photo);
        mainPanel.add (skillTree);
        mainPanel.add (tutorial);
        mainPanel.add (setting);
        mainPanel.add (exit);
        mainPanel.add (start);
        repaint ();
    }

    private void creatFrame (JPanel mainPanel) {
        setTitle (constants.PROJECT_NAME);
        setIconImage (new ImageIcon (constants.PROJECT_ICON_PATH).getImage ());
        setBounds (constants.LOGIN_X, constants.LOGIN_Y, constants.LOGIN_WIDTH, constants.LOGIN_HEIGHT);
        setVisible (true);
        setResizable (false);
        setDefaultCloseOperation (EXIT_ON_CLOSE);
        setLocationRelativeTo (null);
        setContentPane (mainPanel);
        setLayout (null);
    }

    private void creatMainPanel () {
        mainPanel = new JPanel ();
        mainPanel.setVisible (true);
        mainPanel.setOpaque (false);
        mainPanel.setLayout (null);
    }

    private void creatPhoto () {
        photo = new JLabel ();
        photo.setIcon (new ImageIcon (constants.LOGIN_PHOTO_PATH));
        photo.setVisible (true);
        photo.setLayout (null);
        photo.setOpaque (true);
        photo.setBounds (0, 0, constants.LOGIN_WIDTH, constants.LOGIN_HEIGHT);
    }
}
