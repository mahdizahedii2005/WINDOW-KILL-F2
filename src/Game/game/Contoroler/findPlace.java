package Game.game.Contoroler;

import Game.game.view.panelInGame;

import java.awt.geom.Point2D;
import java.util.Random;

public class findPlace {

    public Point2D.Double getP1 () {
        return p1;
    }

    private void setP1 (Point2D.Double p1) {
        this.p1 = p1;
    }

    public Point2D.Double getP2 () {
        return p2;
    }

    private void setP2 (Point2D.Double p2) {
        this.p2 = p2;
    }

    private java.awt.geom.Point2D.Double p1, p2;

    public findPlace (Point2D.Double p1, Point2D.Double p2) {
        this.p2 = p2;
        this.p1 = p1;
    }

    public findPlace () {
        gen ();
        wallNum++;
    }

    private static int wallNum = 0;

    private void gen () {
        int x = panelInGame.getPanel ().getX ();
        int y = panelInGame.getPanel ().getY ();
        int width = panelInGame.getPanel ().getWidth () + x;
        int height = panelInGame.getPanel ().getHeight () + y;
        Random random = new Random ();
        switch (wallNum % 4) {
            case 0:
                setP1 (new Point2D.Double (random.nextInt (x, width), 0));
                setP2 (new Point2D.Double (p1.x + 5, p1.y + 15));
                break;
            case 1:
                setP1 (new Point2D.Double (0, random.nextInt (y, height)));
                setP2 (new Point2D.Double (p1.x + 15, p1.y - 5));
                break;
            case 2:
                setP1 (new Point2D.Double (random.nextInt (x, width), height));
                setP2 (new Point2D.Double (p1.x + 5, p1.y - 15));
                break;
            case 3:
                setP1 (new Point2D.Double (width, random.nextInt (y, height)));
                setP2 (new Point2D.Double (p1.x - 5, p1.y));
                break;
        }
    }
}
