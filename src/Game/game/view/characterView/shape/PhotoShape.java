package Game.game.view.characterView.shape;

import Game.game.view.characterView.DrawAbleObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PhotoShape extends DrawAbleObject {
    private BufferedImage image;
    private int x, y;

    public PhotoShape(BufferedImage image, int x, int y,Color color) {
        super(color);
        this.image = image;
        this.x = x;
        this.y = y;
    }

    @Override
    public void Draw(Graphics g, JPanel panel) {
        g.drawImage(image, x, y, panel);
    }
}
