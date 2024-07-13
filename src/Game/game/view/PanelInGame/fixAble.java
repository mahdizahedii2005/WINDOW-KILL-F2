package Game.game.view.PanelInGame;

import java.awt.*;

public interface fixAble {

    default void fixDetail(int x, int y, Dimension size, Color backGrandColor) {
    }

    ;

    default void fixDetail(String text) {
    }
}
