package Game.game.view.characterView;

import Game.game.view.DrawAble;
import javax.swing.*;
import java.awt.*;

import static Game.helper.changerD;

public class enemyView implements DrawAble {
    private JPanel drawPanel;
    private double[] xPoint;
    private double[] yPoint;
    private int nPoint;
    private Color color;
    private String id;

    public enemyView(JPanel drawPanel, int nPoint, double[] xPoint, double[] yPoint, Color color, String id) {
        this.drawPanel = drawPanel;
        this.nPoint = nPoint;
        this.xPoint = xPoint;
        this.yPoint = yPoint;
        this.color = color;
        this.id = id;
        DRAW_ABLES.add(this);
    }

    @Override
    public void Draw(Graphics g, JPanel jPanel) {
        if (jPanel == drawPanel) {
            g.setColor(color);
            g.fillPolygon(changerD(xPoint), changerD(yPoint), nPoint);
        }
    }
}
