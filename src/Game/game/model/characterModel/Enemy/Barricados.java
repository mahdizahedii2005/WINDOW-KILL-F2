package Game.game.model.characterModel.Enemy;

import Game.game.model.Move.Direction;
import Game.game.model.Move.linearMotion;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.characterModel.Panels.isometricPanel;
import Game.game.model.characterModel.epsilonFriend.bolt;
import Game.game.model.collision.Collidable;
import Game.game.model.shooting.shootGiver;
import Game.game.view.characterView.DrawAbleObject;
import Game.game.view.characterView.shape.PhotoShape;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Barricados extends enemy implements shootGiver {
    private BufferedImage image;
    private final String imagePath = "src\\sources\\photo\\Barricados.png";
    private PanelInGame panel;
    private final double sideSize = 180;

    public Barricados(Point2D.Double center, ArrayList<Point2D> vert) {
        super(center, Color.ORANGE, UUID.randomUUID().toString(), -1, 180f, 0);
        setVertices(vert);
        panel = new isometricPanel(center.getX() - (sideSize / 2 + 20), center.getY() - (sideSize / 2 + 20), sideSize + 40,
                sideSize + 40, new Random().nextBoolean(), 0);
        System.out.println("is solb : " + panel.getIsSolb());
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DrawAbleObject getDrawAbleObject(PanelInGame panel) {
        return new PhotoShape(image, (int) (center.getX() - (image.getWidth() / 2d) - panel.getX()), (int) (center.getY() - (image.getHeight() / 2d) - panel.getY()), Color.ORANGE);
    }

    @Override
    public void createGeometry() {
        super.CreateGeometry();
    }

    @Override
    public boolean isCircular() {
        return false;
    }

    @Override
    public Point2D getAnchor() {
        return center;
    }

    @Override
    public float getSpeed() {
        return 0;
    }

    @Override
    public boolean collide(Collidable collidable) {
        return true;
    }

    @Override
    public void play(Collidable collidable) {
        if (collidable instanceof linearMotion)((linearMotion)collidable).setDirection(new Direction(new Point2D.Double(0,0)));
    }

    @Override
    public void takingShot(bolt bolt) {

    }

    @Override
    public void getHit(int damage) {
        return;
    }
}
