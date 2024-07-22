package Game.game.view.characterView.shape;

import org.locationtech.jts.awt.PointShapeFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class ovalShape extends circleShape {
    private double radius2;

    public ovalShape(Point2D anchor, Color color, double radius, double radius2) {
        super(anchor, color, radius);
        this.radius2 = radius2;
    }

    @Override
    public void Draw(Graphics g, JPanel jPanel) {
        g.setColor(color);
        g.fillOval((int) (anchor.getX() - Radius), (int) (anchor.getY() - radius2), (int) (2 * Radius), (int) (2 * radius2));
    }
}
