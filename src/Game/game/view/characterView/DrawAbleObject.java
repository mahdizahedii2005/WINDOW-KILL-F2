package Game.game.view.characterView;

import Game.game.view.DrawAble;
import Game.game.view.panelInGame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class DrawAbleObject implements DrawAble {
    String id;
    Point2D.Double center = new Point2D.Double (-1, -1);
    Color color = Color.BLACK;
    JPanel drawPanel = panelInGame.getPanel ();


    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public Point2D.Double getCenter () {
        return center;
    }

    public void setCenter (Point2D.Double center) {
        this.center = center;
    }

    public Color getColor () {
        return color;
    }

    public void setColor (Color color) {
        this.color = color;
    }

    public JPanel getDrawPanel () {
        return drawPanel;
    }

    public void setDrawPanel (JPanel drawPanel) {
        this.drawPanel = drawPanel;
    }

    public DrawAbleObject (String id) {
        this.id = id;
        DrawAble.DRAW_ABLES.add (this);
    }

    @Override
    public void Draw (Graphics g, JPanel jPanel) {

    }

    @Override
    public void fixDetail (Point2D.Double center, double radius, JPanel drawPanel) {
        this.center = center;
        this.drawPanel = drawPanel;
    }

    @Override
    public void fixDetail (JPanel drawPanel, double[] xPoint, double[] yPoint, int nPoint, Color color) {

    }

    @Override
    public void fixDetail (JPanel drawPanel, Color color, Point2D.Double center) {
        this.center = center;
        this.drawPanel = drawPanel;
        this.color = color;

    }

    public void clean () {
        DRAW_ABLES.remove (this);
    }
}
