package Game.game.view.waveAnimation;

import Game.game.Contoroler.control.Update;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.view.PanelInGame.panelInGameView;
import Game.login.SkillTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class win extends Animation {
    public win() {
        super(new Dimension(350, 92), "src\\sources\\photo\\you win.png", "src\\sources\\song\\victory.wav");
        SkillTree.increaseTotalExp((int) Epsilon.getEpsilon().getTotalExp());
        new Timer(5, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Update.stopCLOSFRAME = false;
                Epsilon.getEpsilon().setRadius((double) Epsilon.getEpsilon().getRadius() + 1);
                if (Epsilon.getEpsilon().getRadius() >= Math.min(Epsilon.getEpsilon().mainPanel.getWidth(), Epsilon.getEpsilon().mainPanel.getHeight())) {
                    new lostPanel();
                    ((Timer) e.getSource()).stop();
                }
            }
        }).start();
    }
}
