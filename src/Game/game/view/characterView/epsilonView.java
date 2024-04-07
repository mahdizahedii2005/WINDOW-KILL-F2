package Game.game.view.characterView;

import Game.game.view.DrawAble;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class epsilonView implements DrawAble {
    double radius;
    Point2D center;
    String id;
    JPanel drawPanel;

    public epsilonView(Point2D.Double center, String id, double radius, JPanel drawPanel) {
        this.radius = radius;
        this.drawPanel = drawPanel;
        this.center = center;
        this.id = id;
        DRAW_ABLES.add(this);
    }

    @Override
    public void Draw(Graphics g, JPanel jPanel) {
        if (jPanel == drawPanel) {
            g.setColor(Color.WHITE);
            g.fillOval((int) (center.getY() - radius), (int) (center.getY() - radius), (int) (2 * radius), (int) (2 * radius));
            g.setColor(Color.black);
            int lowerRadius =(int) (radius*0.85);
            g.fillOval((int) (center.getY() - lowerRadius), (int) (center.getY() - lowerRadius), (int) (2 * lowerRadius), (int) (2 * lowerRadius));

        }
    }
}
