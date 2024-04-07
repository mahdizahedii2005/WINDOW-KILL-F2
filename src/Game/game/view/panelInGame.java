package Game.game.view;

import javax.swing.*;
import java.awt.*;

import static Game.game.view.DrawAble.DRAW_ABLES;
import static Game.helper.changerD;

public class panelInGame extends JPanel {
    JFrame fatherFrame;

    public panelInGame(JFrame jFrame) {
        fatherFrame = jFrame;
        setLayout(null);
        setSize(fatherFrame.getSize());
        setBorder(BorderFactory.createLineBorder(Color.RED,5));
        setBackground(Color.BLACK);
        setVisible(true);
        fatherFrame.setContentPane(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < DRAW_ABLES.size(); i++) {
            DRAW_ABLES.get(i).Draw(g, this);
        }
    }
}
