package Game.game.view;

import javax.swing.*;
import java.awt.*;

public class frameInGame extends JFrame {
    private frameInGame() {
        setUndecorated(true);
        setLayout(null);
        setVisible(true);
    }

    public frameInGame(int width, int height) {
        this();
        setSize(new Dimension(width, height));
        setLocationRelativeTo(null);
    }
    public frameInGame(Dimension dimension){
        this(dimension.width, dimension.height);
    }

    public frameInGame(int x, int y, int width, int height) {
        this();
        setBounds(x, y, width, height);
    }
}
