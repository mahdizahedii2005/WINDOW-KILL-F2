package Game.game.view.characterView;

import Game.game.view.DrawAble;
import Game.game.view.panelInGame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class epsilonView extends DrawAbleObject {
    double radius = 0;
    JPanel drawPanel = panelInGame.getPanel();

    public epsilonView(String id) {
        super(id);
    }

    @Override
    public void Draw(Graphics g, JPanel jPanel) {
        if (jPanel == drawPanel) {
            g.setColor(Color.WHITE);
            g.fillOval((int) (center.getX() - radius), (int) (center.getY() - radius), (int) (2 * radius), (int) (2 * radius));
            g.setColor(Color.black);
            int lowerRadius = (int) (radius * 0.8);
            g.fillOval((int) (center.getX() - lowerRadius), (int) (center.getY() - lowerRadius), (int) (2 * lowerRadius), (int) (2 * lowerRadius));

        }
    }

    @Override
    public void fixDetail(Point2D.Double center, double radius, JPanel drawPanel) {
        this.radius = radius;
        this.drawPanel = drawPanel;
        this.center = center;
        panelInGame.getPanel().repaint();
    }

    @Override
    public void fixDetail(JPanel drawPanel, double[] xPoint, double[] yPoint, int nPoint, Color color) {
        return;
    }



    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public Point2D.Double getCenter() {
        return center;
    }

    public void setCenter(Point2D.Double center) {
        this.center = center;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JPanel getDrawPanel() {
        return drawPanel;
    }

    public void setDrawPanel(JPanel drawPanel) {
        this.drawPanel = drawPanel;
    }
}
