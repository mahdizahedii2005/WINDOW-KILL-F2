package Game.game.view.waveAnimation;

import Game.game.Contoroler.Update;
import Game.game.model.characterModel.Epsilon;
import Game.game.view.DrawAble;

import java.awt.*;

import static Game.game.Contoroler.Controller.findObjectView;

public class lost extends Animation {

    public lost () {
        super (new Dimension (350, 75), "src\\sources\\photo\\lost.png", "src\\sources\\song\\lost.wav",800000);
        Update.finish = false;
        DrawAble.DRAW_ABLES.remove (findObjectView (Epsilon.getEpsilon ().getId ()));
    }

}