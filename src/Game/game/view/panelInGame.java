package Game.game.view;

import Game.game.model.characterModel.Epsilon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;

import static Game.Data.constants.*;
import static Game.game.view.DrawAble.DRAW_ABLES;

public class panelInGame extends JPanel implements DrawAble {
    // TODO: ۰۸/۰۴/۲۰۲۴ fix the final class
    private static panelInGame panel;
    private String id;
    private JLabel EpsilonHp;

    public static panelInGame getPanel () {
        if (panel == null) {
            panel = new panelInGame ("panel");
        }
        return panel;
    }

    public panelInGame (String id) {
        this.id = id;
        setFocusable (true);
        requestFocus ();
        requestFocusInWindow ();
        setLayout (null);
        setBorder (BorderFactory.createLineBorder (PANEL_BAR_GRAND, 5));
        setBackground (PANEL_BACK_GRAND);
        setVisible (true);
        setOpaque (true);
        frameInGame.getFrame ().add (this);
        repaint ();
        panel = this;
        DRAW_ABLES.add (this);
        EpsilonHp = new JLabel ();
        EpsilonHp.setSize (new Dimension (40, 30));
        EpsilonHp.setVisible (true);
        EpsilonHp.setLocation (new Point (this.getWidth () - 2 - EpsilonHp.getWidth (), 2));
        EpsilonHp.setText ("HP: " + 100);
        EpsilonHp.setFont (new Font ("Arial", Font.ITALIC, 10));
        EpsilonHp.setOpaque (true);
        EpsilonHp.show ();
        EpsilonHp.setBackground (Color.black);
        panel.add (EpsilonHp);
    }

    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent (g);
//        g.setColor (Color.WHITE);
//        g.drawString ("HP: " + Epsilon.getEpsilon ().getHP (), this.getWidth () - 2 - EpsilonHp.getWidth (), 2);
        for (int i = 0 ; i < DRAW_ABLES.size () ; i++) {
            DRAW_ABLES.get (i).Draw (g, this);
        }
    }

    @Override
    public void Draw (Graphics g, JPanel jPanel) {

    }

    @Override
    public void fixDetail (Point2D.Double center, double radius, JPanel drawPanel) {

    }

    @Override
    public void fixDetail (JPanel drawPanel, double[] xPoint, double[] yPoint, int nPoint, Color color) {

    }

    @Override
    public void fixDetail (JPanel drawPanel, Color color, Point2D.Double center) {

    }

    @Override
    public void fixDetail (int x, int y, Dimension size, Color backGrandColor, Color BarColor) {
        setLocation (x, y);
        setSize (size);
        setBackground (backGrandColor);
        setBorder (BorderFactory.createLineBorder (BarColor, 3));
        EpsilonHp.setLocation (new Point (this.getWidth () - 2 - EpsilonHp.getWidth (),  2));
        EpsilonHp.setText ("HP: " + Epsilon.getEpsilon ().getHP ());
        revalidate ();
        frameInGame.getFrame ().revalidate ();
    }

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }
}
