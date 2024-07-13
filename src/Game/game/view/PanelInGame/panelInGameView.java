package Game.game.view.PanelInGame;

import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.view.characterView.DrawAble;
import Game.game.view.characterView.DrawAbleObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Game.Data.constants.*;

public class panelInGameView extends JPanel implements fixAble {
    private ArrayList<DrawAbleObject> objectForDraw;
    //    public Animation animation;
    private String id;
    private JLabel EpsilonHp;
    private JLabel Exp;
    public static boolean DO_I_HAVE_ANIMATION = false;

    public panelInGameView(String id) {
        DO_I_HAVE_ANIMATION = false;
        this.id = id;
        frameInGame.getFrame().PANELS.add(this);
        setFocusable(true);
        requestFocus();
        requestFocusInWindow();
        setLayout(null);
//        setBorder(BorderFactory.createLineBorder(PANEL_BAR_GRAND, 5));
        setBackground(PANEL_BACK_GRAND);
        setVisible(true);
        setOpaque(true);
        frameInGame.getFrame().add(this);
        repaint();
        EpsilonHp = new JLabel();
        EpsilonHp.setSize(new Dimension(60, 35));
        EpsilonHp.setVisible(true);
        EpsilonHp.setLocation(new Point(this.getWidth() - 2 - EpsilonHp.getWidth(), 2));
        EpsilonHp.setText("HP: " + 100);
        EpsilonHp.setFont(new Font("Arial", Font.ITALIC, 13));
        EpsilonHp.setForeground(Color.WHITE);
        EpsilonHp.setOpaque(false);
        EpsilonHp.setBackground(Color.black);
        add(EpsilonHp);
        Exp = new JLabel();
        Exp.setSize(new Dimension(70, 35));
        Exp.setVisible(true);
        Exp.setLocation(new Point(3, 2));
        Exp.setText("exp :" + 0);
        Exp.setFont(new Font("Arial", Font.ITALIC, 13));
        Exp.getFont().deriveFont(Font.BOLD);
        Exp.setForeground(new Color(100, 0, 250));
        Exp.setOpaque(false);
        Exp.setBackground(Color.black);
        add(Exp);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (DrawAble drawAbleObject : objectForDraw) {
            drawAbleObject.Draw(g, this);
        }
    }

    @Override
    public void fixDetail(int x, int y, Dimension size, Color backGrandColor) {
        setLocation(x, y);
        setSize(size);
//        if (DO_I_HAVE_ANIMATION) {
//            animation.update();
//        }
        setBackground(backGrandColor);
        EpsilonHp.setLocation(new Point(this.getWidth() - 2 - EpsilonHp.getWidth(), 2));
        EpsilonHp.setText((int) Epsilon.getEpsilon().getHP() + " / " + Epsilon.getEpsilon().getMaxHp());
        Exp.setText("exp :" + (int) Epsilon.getEpsilon().getExp());
        revalidate();
        repaint();
        frameInGame.getFrame().revalidate();
    }

    public String getId() {
        return id;
    }

    public void clear() {
        frameInGame.getFrame().PANELS.remove(this);
    }

    public void addToObjectInside(DrawAbleObject object) {
        objectForDraw.add(object);
    }

    public void restartObjectInside() {
        objectForDraw = new ArrayList<>();
    }
}
