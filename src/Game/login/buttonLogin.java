package Game.login;

import Game.Data.constants;

import javax.swing.*;
import java.awt.event.ActionListener;

public class buttonLogin extends JButton {
    public buttonLogin(int x, int y, int width, int height, ActionListener I) {
        setBounds(x, y, width, height);
        setLayout(null);
        setVisible(true);
        setOpaque(false);
        addActionListener(I);
    }
    public buttonLogin(int x, int y, ActionListener I) {
        this(x, y, constants.LOGIN_BUTTON_WIDTH, constants.LOGIN_BUTTON_HEIGHT, I);
    }
}
