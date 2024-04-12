package Game.game.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static Game.Data.constants.*;
import static Game.game.view.DrawAble.DRAW_ABLES;

public class panelInGame extends JPanel {
    // TODO: ۰۸/۰۴/۲۰۲۴ fix the final class
    private static panelInGame panel;

    public static panelInGame getPanel() {
        if (panel == null) {
            panel = new panelInGame(new Rectangle(FIRST_PANEL_RECTANGLE));
        }
        return panel;
    }

    public panelInGame(Rectangle rectangle) {
        setBounds(rectangle);
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        setBackground(Color.BLACK);
        setVisible(true);
        setOpaque(true);
        frameInGame.getFrame().add(this);
        repaint();
    }

    public void ReduceSizeOfFrame() {
        if (validSize()) {
            setSize(new Dimension(getWidth() - 2, getHeight() - 2));
            setLocation(getX() + 1, getY() + 1);
            repaint();
        } else {
            setSize(SMALLEST_SIZE_OF_ORIGINAL_PANEL);
            repaint();
        }
    }


    public boolean validSize() {
        return getSize().width > SMALLEST_SIZE_OF_ORIGINAL_PANEL.width && getSize().height > SMALLEST_SIZE_OF_ORIGINAL_PANEL.height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < DRAW_ABLES.size(); i++) {
            DRAW_ABLES.get(i).Draw(g, this);
        }
    }
}
