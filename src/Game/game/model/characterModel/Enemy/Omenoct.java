package Game.game.model.characterModel.Enemy;

import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.shooting.shooter;
import Game.game.view.characterView.DrawAbleObject;
import Game.game.view.characterView.shape.PhotoShape;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Game.Data.constants.OMENOCT_RADIUS;

public class Omenoct extends Enemy implements shooter {
    private BufferedImage image;
    private String imagePath = "src\\sources\\photo\\Omenoct .png";
    private int rangedDamage = 4;

    public Omenoct(Point2D.Double center, int nPoint, double[] xPoint, double[] yPoint) {
        super(center, Color.BLACK, nPoint, xPoint, yPoint, 20, 8, (float) OMENOCT_RADIUS);
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DrawAbleObject getDrawAbleObject(PanelInGame panel) {
        return new PhotoShape(image, (int) center.getX() - (image.getWidth() / 2), (int) center.getY() - (image.getHeight() / 2));
    }

    public int getRangedDamage() {
        return rangedDamage;
    }

    @Override
    public void fire(Point2D.Double target, Point2D.Double realTarget) {
        
    }
}
