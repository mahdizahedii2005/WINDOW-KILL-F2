package Game.game.view.characterView;

import Game.game.view.DrawAble;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class PrizeView extends DrawAbleObject {
   public static final int radius = 3;

    public PrizeView (String id) {
        super (id);
    }

    @Override
    public void fixDetail (JPanel drawPanel, Color color,Point2D.Double center) {
        super.fixDetail (drawPanel, color,center);
    }

    @Override
    public void Draw (Graphics g, JPanel jPanel) {
        g.setColor (color);
        g.fillOval ((int) (center.getX () - radius), (int) (center.getY () - radius), (int) (2 * radius), (int) (2 * radius));
    }
}
