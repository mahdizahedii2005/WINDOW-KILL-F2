package Game.game.view.characterView.shape;

import Game.game.view.characterView.DrawAbleObject;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class nonCircular extends DrawAbleObject {
    protected int nPoint;
    protected int[] xPoint;
    protected int[] yPoint;

    public nonCircular(ArrayList<Point2D> geometry, Color color) {
        super(color);
        nPoint = geometry.size();
        xPoint = new int[nPoint];
        yPoint = new int[nPoint];
        for (int i = 0; i < nPoint; i++) {
            xPoint[i] = (int) geometry.get(i).getX();
            yPoint[i] = (int) geometry.get(i).getY();
        }
    }

    @Override
    public void Draw(Graphics g, JPanel jPanel) {
        g.setColor(color);
        g.fillPolygon(xPoint, yPoint, nPoint);
    }
}
