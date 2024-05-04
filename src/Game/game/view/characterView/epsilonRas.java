package Game.game.view.characterView;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class epsilonRas extends DrawAbleObject {
    private double radius = 2;

    public epsilonRas (String id) {
        super (id);
    }

    @Override
    public void Draw (Graphics g, JPanel jPanel) {
        g.setColor (Color.GREEN);
        g.fillOval ((int) (center.getX () - radius), (int) (center.getY () - radius), (int) (2 * radius), (int) (2 * radius));
    }

    @Override
    public void fixDetail (Point2D.Double center, double radius, JPanel drawPanel) {
        super.fixDetail (center, radius, drawPanel);
        this.radius = radius;
    }
}
