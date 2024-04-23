package Game.game.model.characterModel;

import Game.game.view.characterView.PrizeView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.UUID;

import static Game.game.Contoroler.Controller.addPrizeView;

public class prize extends ObjectInGame {
    public static ArrayList<prize> prizeArrayList = new ArrayList<> ();
    int increaseHP;

    public prize (Point2D.Double center, Color color, int IncreaseHp) {
        super (center, color, UUID.randomUUID ().toString (), 1, PrizeView.radius);
        this.increaseHP = IncreaseHp;
        addPrizeView (id);
        prizeArrayList.add (this);
    }

    @Override
    public void Die () {
        prizeArrayList.remove (this);
        super.Die ();
    }

    public void Action () {
        Epsilon.getEpsilon ().HP += increaseHP;
        Die ();
    }
}
