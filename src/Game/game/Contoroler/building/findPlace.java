package Game.game.Contoroler.building;

import Game.Data.constants;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import Game.game.view.PanelInGame.panelInGameView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class findPlace {

    public Point2D.Double getP1() {
        return p1;
    }

    private void setP1(Point2D.Double p1) {
        this.p1 = p1;
    }

    public Point2D.Double getP2() {
        return p2;
    }

    private void setP2(Point2D.Double p2) {
        this.p2 = p2;
    }

    private java.awt.geom.Point2D.Double p1, p2;

    public findPlace(Point2D.Double p1, Point2D.Double p2) {
        this.p2 = p2;
        this.p1 = p1;
    }

    public findPlace() {
        this(constants.SMALLEST_SIZE_OF_ORIGINAL_PANEL);
    }

    public findPlace(Dimension SMALLEST_SIZE_OF_ORIGINAL_PANEL) {
        gen(SMALLEST_SIZE_OF_ORIGINAL_PANEL);
        wallNum++;
    }

    private static int wallNum = 0;

    private void gen(Dimension SMALLEST_SIZE_OF_ORIGINAL_PANEL) {
        int x = (int) (Epsilon.getEpsilon().getAnchor().getX());
        int y = (int) (Epsilon.getEpsilon().getAnchor().getX());
        int width = (int) ((2.5 * SMALLEST_SIZE_OF_ORIGINAL_PANEL.getWidth()));
        int height = (int) ((2.5 * SMALLEST_SIZE_OF_ORIGINAL_PANEL.getHeight()));
        Random random = new Random();
        switch (wallNum % 4) {
            case 0:
                setP1(new Point2D.Double(random.nextInt((int) (x + SMALLEST_SIZE_OF_ORIGINAL_PANEL.width), x + width),
                        random.nextInt(y - height, y + height)));
                setP2(new Point2D.Double(p1.x + 5, p1.y + 15));
                break;
            case 1:
                setP1(new Point2D.Double(random.nextInt(x - width, (int) (x - SMALLEST_SIZE_OF_ORIGINAL_PANEL.width)),
                        random.nextInt(y - height, y + height)));
                setP2(new Point2D.Double(p1.x + 15, p1.y - 5));
                break;
            case 2:
                setP1(new Point2D.Double(random.nextInt(x - width, x + width),
                        random.nextInt((int) (y + SMALLEST_SIZE_OF_ORIGINAL_PANEL.height), y + height)));
                setP2(new Point2D.Double(p1.x + 5, p1.y - 15));
                break;
            case 3:
                setP1(new Point2D.Double(random.nextInt(x - width, x + width),
                        random.nextInt( y - height,(int) (y - SMALLEST_SIZE_OF_ORIGINAL_PANEL.height))));
                setP2(new Point2D.Double(p1.x - 5, p1.y));
                break;
        }
    }
}
