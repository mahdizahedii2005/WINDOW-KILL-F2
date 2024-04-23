package Game.game.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public interface DrawAble {
    ArrayList<DrawAble> DRAW_ABLES = new ArrayList<>();

    void Draw(Graphics g, JPanel jPanel);

    void fixDetail(Point2D.Double center, double radius, JPanel drawPanel);

    void fixDetail(JPanel drawPanel, double[] xPoint, double[] yPoint, int nPoint, Color color);

    void fixDetail(JPanel drawPanel,Color color, Point2D.Double center);
    void fixDetail(int x, int y , Dimension size , Color backGrandColor, Color BarColor);
}
