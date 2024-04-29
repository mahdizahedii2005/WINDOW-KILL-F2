package Game.game.view;

import Game.game.model.characterModel.Epsilon;
import Game.game.view.waveAnimation.Animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;

import static Game.Data.constants.*;
import static Game.game.view.DrawAble.DRAW_ABLES;

public class panelInGame extends JPanel implements DrawAble {
    // TODO: ۰۸/۰۴/۲۰۲۴ fix the final class
    private static panelInGame panel;
    public Animation animation;
    private String id;
    private JLabel EpsilonHp;
    private JLabel Exp;

    public static panelInGame getPanel () {
        if (panel == null) {
            panel = new panelInGame ("panel");
        }
        return panel;
    }

    public static boolean DO_I_HAVE_ANIMATION = false;

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
        EpsilonHp.setSize (new Dimension (60, 35));
        EpsilonHp.setVisible (true);
        EpsilonHp.setLocation (new Point (this.getWidth () - 2 - EpsilonHp.getWidth (), 2));
        EpsilonHp.setText ("HP: " + 100);
        EpsilonHp.setFont (new Font ("Arial", Font.ITALIC, 13));
        EpsilonHp.setForeground (Color.WHITE);
        EpsilonHp.setOpaque (false);
        EpsilonHp.setBackground (Color.black);
        panel.add (EpsilonHp);
        Exp = new JLabel ();
        Exp.setSize (new Dimension (70, 35));
        Exp.setVisible (true);
        Exp.setLocation (new Point (3, 2));
        Exp.setText ("exp :" + 0);
        Exp.setFont (new Font ("Arial", Font.ITALIC, 13));
        Exp.getFont ().deriveFont (Font.BOLD);
        Exp.setForeground (new Color (100, 0, 250));
        Exp.setOpaque (false);
        Exp.setBackground (Color.black);
        panel.add (Exp);
    }

    @Override
    protected void paintComponent (Graphics g) {
        super.paintComponent (g);
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
        if (DO_I_HAVE_ANIMATION) {
            animation.update ();
        }
        setBackground (backGrandColor);
        setBorder (BorderFactory.createLineBorder (BarColor, 3));
        EpsilonHp.setLocation (new Point (this.getWidth () - 2 - EpsilonHp.getWidth (), 2));
        EpsilonHp.setText (Epsilon.getEpsilon ().getHP () + " / " + Epsilon.getEpsilon ().getMaxHp ());
        Exp.setText ("exp :" + (int) Epsilon.getEpsilon ().getExp ());
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
