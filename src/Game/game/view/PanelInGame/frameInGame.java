package Game.game.view.PanelInGame;

import Game.Data.constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Game.Data.constants.GLASS_FRAME_DIMENSION;

public final class frameInGame extends JFrame {
    private static frameInGame frame;
    public ArrayList<NotifPanel> notifPanels = new ArrayList<>();
    public ArrayList<panelInGameView> PANELS = new ArrayList<>();
    public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static frameInGame getFrame() {
        if (frame == null) {
            frame = new frameInGame();
        }
        return frame;
    }

    public frameInGame() {
        frame = this;

//        setSize(screenSize);
        setIconImage(new ImageIcon(constants.PROJECT_ICON_PATH).getImage());
        setUndecorated(true);
        setFocusable(true);
        requestFocus();
        requestFocusInWindow();
        setBackground(new Color(0, 0, 0, 0));
        setSize(GLASS_FRAME_DIMENSION);
        setLocationRelativeTo(null);
        setVisible(true);
        setLayout(null);
        new NotifPanel("exp", 0, 5, new Dimension(60, 40), "exp : ");
        new NotifPanel("Hp", (int) screenSize.getWidth() - 82, 5, new Dimension(80, 40), "Hp : ");
        new NotifPanel("time", (int) ((screenSize.getWidth() / 2) - 60), 5, new Dimension(120, 40), "time : ");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
