package Game.game.view.waveAnimation;

import Game.game.Contoroler.control.Update;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.login.SkillTree;

import java.awt.*;

public class lost extends Animation {
    public lost() {
        super(new Dimension(350, 75), "src\\sources\\photo\\lost.png", "src\\sources\\song\\lost.wav", 800000);
        Update.stpoUpdateModel = false;
        Update.stopFRAMEUPDATE = false;
        SkillTree.increaseTotalExp((int) Epsilon.getEpsilon().getTotalExp());
        Update.finish = false;
        new lostPanel().run();
    }
}
