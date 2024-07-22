package Game.game.view.characterView;

import Game.game.Contoroler.control.whatPanelAndWhere;

import java.awt.*;
import java.util.ArrayList;

public abstract class DrawAbleObject implements DrawAble {
    protected Color color = Color.BLACK;
    protected ArrayList<whatPanelAndWhere> drawsPanel;

    public Color getColor() {
        return color;
    }

    //    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public DrawAbleObject(String id) {
//        this.id = id;
//        DrawAble.DRAW_ABLES.add(this);
//    }
    public DrawAbleObject(Color color) {
        this.color = color;
    }
}
