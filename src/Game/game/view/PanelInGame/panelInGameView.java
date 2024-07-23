package Game.game.view.PanelInGame;

import Game.game.view.characterView.DrawAble;
import Game.game.view.characterView.DrawAbleObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Game.Data.constants.PANEL_BACK_GRAND;

public class panelInGameView extends JPanel implements fixAble {
    private ArrayList<DrawAbleObject> objectForDraw;
    //    public Animation animation;
    private String id;

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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//TODO
        try {
        if (objectForDraw != null) {
            for (int i = 0; i < objectForDraw.size(); i++) {
                DrawAble drawAbleObject = objectForDraw.get(i);
            if (drawAbleObject==null)continue;
            drawAbleObject.Draw(g, this);
            }
        }}catch (Exception e){
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

    public ArrayList<DrawAbleObject> getObjectForDraw() {
        return objectForDraw;
    }

    public void setObjectForDraw(ArrayList<DrawAbleObject> objectForDraw) {
        this.objectForDraw = objectForDraw;
    }

    public void restartObjectInside() {
        objectForDraw = new ArrayList<>();
    }
}
