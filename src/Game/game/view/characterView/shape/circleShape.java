package Game.game.view.characterView.shape;

import Game.game.view.characterView.DrawAbleObject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class circleShape extends DrawAbleObject {
    protected Point2D anchor;
    protected double Radius;

    @Override
    public void Draw(Graphics g, JPanel jPanel) {
        g.setColor(color);
        g.fillOval((int) (anchor.getX() - Radius), (int) (anchor.getY() - Radius), (int) (2 * Radius), (int) (2 * Radius));
    }

    public circleShape(Point2D anchor, Color color, double radius) {
        super(color);
        Radius = radius;
        this.anchor = anchor;
    }

}
