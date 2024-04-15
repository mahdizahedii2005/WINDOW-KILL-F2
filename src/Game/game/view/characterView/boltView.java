package Game.game.view.characterView;

import Game.game.view.DrawAble;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class boltView extends DrawAbleObject {
    public boltView (String id) {
        super (id);
    }

    public String getId () {
        return id;
    }

    JPanel jPanel;
    public static final double R1 = 4;

    @Override
    public void Draw (Graphics g, JPanel jPanel) {
        g.setColor (Color.WHITE);
        g.fillOval ((int) (center.getX () - R1), (int) (center.getY () - R1), (int) (2 * R1), (int) (2 * R1));
    }

    @Override
    public void fixDetail (Point2D.Double point2D, double radius, JPanel drawPanel) {

    }

    @Override
    public void fixDetail (JPanel drawPanel, double[] xPoint, double[] yPoint, int nPoint, Color color) {

    }

    @Override
    public void fixDetail (JPanel drawPanel, Color color, Point2D.Double center) {
        this.center = center;
        this.jPanel = drawPanel;
        this.color = color;
    }
}
