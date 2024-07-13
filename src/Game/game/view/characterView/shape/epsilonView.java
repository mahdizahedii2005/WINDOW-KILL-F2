package Game.game.view.characterView.shape;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class epsilonView extends circleShape {
    public epsilonView(Point2D anchor, Color color, double radius) {
        super(anchor, color, radius);
    }

    @Override
    public void Draw(Graphics g, JPanel jPanel) {
            g.setColor(color);
            g.fillOval((int) (anchor.getX() - Radius), (int) (anchor.getY() - Radius), (int) (2 * Radius), (int) (2 * Radius));
            g.setColor(Color.black);
            int lowerRadius = (int) (Radius * 0.8);
            g.fillOval((int) (anchor.getX() - lowerRadius), (int) (anchor.getY() - lowerRadius), (int) (2 * lowerRadius), (int) (2 * lowerRadius));
    }
}
