package Game.game.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public interface DrawAble {
    ArrayList<DrawAble> DRAW_ABLES = new ArrayList<>();
    void Draw(Graphics g,JPanel jPanel);
}
