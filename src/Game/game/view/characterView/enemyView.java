package Game.game.view.characterView;

import Game.game.view.DrawAble;
import Game.game.view.panelInGame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

import static Game.helper.changerD;

public class enemyView extends DrawAbleObject implements DrawAble {
    private JPanel drawPanel = panelInGame.getPanel ();
    private double[] xPoint = new double[0];
    private double[] yPoint = new double[0];
    private int nPoint = 0;
    private Color color = Color.BLACK;
    public enemyView (String id) {
        super (id);
    }

    @Override
    public void Draw (Graphics g, JPanel jPanel) {
        if (jPanel == drawPanel) {
            g.setColor (color);
            g.fillPolygon (changerD (xPoint), changerD (yPoint), nPoint);
        }
    }

    @Override
    public void fixDetail (Point2D.Double point2D, double radius, JPanel drawPanel) {
        return;
    }

    public JPanel getDrawPanel () {
        return drawPanel;
    }

    public void setDrawPanel (JPanel drawPanel) {
        this.drawPanel = drawPanel;
    }

    public double[] getxPoint () {
        return xPoint;
    }

    public void setxPoint (double[] xPoint) {
        this.xPoint = xPoint;
    }

    public double[] getyPoint () {
        return yPoint;
    }

    public void setyPoint (double[] yPoint) {
        this.yPoint = yPoint;
    }

    public int getnPoint () {
        return nPoint;
    }

    public void setnPoint (int nPoint) {
        this.nPoint = nPoint;
    }

    public Color getColor () {
        return color;
    }

    public void setColor (Color color) {
        this.color = color;
    }

    public void fixDetail (JPanel drawPanel, double[] xPoint, double[] yPoint, int nPoint, Color color) {
        this.drawPanel = drawPanel;
        this.xPoint = xPoint;
        this.yPoint = yPoint;
        this.nPoint = nPoint;
        this.color = color;
    }
}
