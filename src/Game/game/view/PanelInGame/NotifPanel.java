package Game.game.view.PanelInGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NotifPanel extends JPanel implements fixAble {
    private JLabel label;
    private String name;
    private String deffultText = "";

    NotifPanel(String name, int x, int y, Dimension size) {
        this.name = name;
        setBounds(x, y, size.width, size.height);
        setVisible(true);
        setOpaque(true);
        setLayout(null);
        setBackground(Color.BLACK);
        label = new JLabel();
        label.setBounds(0, 0, getWidth(), getHeight());
        label.setOpaque(false);
        label.setVisible(true);
        label.setFont(new Font("Arial", Font.ITALIC,  (int) (getHeight() / 3)));
        label.setForeground(Color.WHITE);
        label.setBackground(Color.BLACK);
        add(label);
        frameInGame.getFrame().add(this);
        frameInGame.getFrame().notifPanels.add(this);
    }

    NotifPanel(String name, int x, int y, Dimension size, String deffultText) {
        this(name, x, y, size);
        this.deffultText = deffultText;
    }

    @Override
    public void fixDetail(String text) {
        text = deffultText + text;
        label.setText(text);
    }

    @Override
    public String getName() {
        return name;
    }
}
