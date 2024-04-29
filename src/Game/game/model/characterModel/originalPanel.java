package Game.game.model.characterModel;

import Game.game.Contoroler.Update;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import static Game.Data.constants.*;

public class originalPanel extends ObjectInGame {

    private static originalPanel Panel;
    private double x, y,
            width, height;
    private Dimension size;

    public originalPanel () {
        super (new Point2D.Double (0, 0), PANEL_BACK_GRAND, "panel", 1,0);
        width = FIRST_PANEL_DIMENSION.width;
        height = FIRST_PANEL_DIMENSION.height;
        x = FIRST_PANEL_RECTANGLE.x;
        y = FIRST_PANEL_RECTANGLE.y;
        size = new Dimension ((int) width, (int) height);
        Panel = this;
    }

    public double getX () {
        return x;
    }

    public void setX (double x) {
        this.x = x;
    }

    public double getY () {
        return y;
    }

    public void setY (double y) {
        this.y = y;
    }

    public double getWidth () {
        size.width = (int) width;
        return width;
    }

    public void setWidth (double width) {
        this.width = width;
    }

    public double getHeight () {
        return height;
    }

    public void setHeight (double height) {
        size.height = (int) height;
        this.height = height;
    }

    public Dimension getSize () {
        return size;
    }

    public void setSize (Dimension size) {
        width = size.width;
        height = size.height;
        this.size = size;
    }

    public Line2D.Double getLeftPanel () {
        return new Line2D.Double (x, y, x, y + height);
    }

    public Line2D.Double getUpPanel () {
        return new Line2D.Double (x, y, x + width, y);
    }

    public Line2D.Double getRightPanel () {
        return new Line2D.Double (x + width, y, x + width, y + height);
    }

    public Line2D.Double getDownPanel () {
        return new Line2D.Double (x, y + height, x + width, y + height);
    }

    public enum panelWall {
        up {
            public Line2D.Double getLine () {
                up.line = originalPanel.getPanel ().getUpPanel ();
                return line;
            }
        },

        right {
            public Line2D.Double getLine () {
                right.line = originalPanel.getPanel ().getRightPanel ();
                return line;
            }
        },

        left {
            public Line2D.Double getLine () {
                left.line = originalPanel.getPanel ().getLeftPanel ();
                return line;
            }
        },

        down {
            public Line2D.Double getLine () {
                down.line = originalPanel.getPanel ().getDownPanel ();
                return line;
            }
        };
        Line2D.Double line;

        public Line2D.Double getLine () {
            return line;
        }
    }

    int upI = 0, downI = 0, rightI = 0, leftI = 0;

    public boolean ReduceSizeOfFrame () {
        if (upI > 0 || downI > 0 || leftI > 0 || rightI > 0) {
            if (upI > 0) {
                setSize (new Dimension ((int) (getWidth ()), (int) (getHeight () + RANGE_OF_INCREASE_PLACE)));
                setX (getX ());
                setY (getY () - RANGE_OF_INCREASE_PLACE - 4);
                upI--;
            }
            if (downI > 0) {
                setSize (new Dimension ((int) (getWidth ()), (int) (getHeight () + RANGE_OF_INCREASE_SIZE)));
                setX (getX ());
                setY (getY () + 4);
                downI--;
            }
            if (rightI > 0) {
                setSize (new Dimension ((int) (getWidth () + RANGE_OF_INCREASE_SIZE), (int) (getHeight ())));
                setX (getX () + 4);
                setY (getY ());
                rightI--;
            }
            if (leftI > 0) {
                setSize (new Dimension ((int) (getWidth () + RANGE_OF_INCREASE_PLACE), (int) (getHeight ())));
                setX (getX () - RANGE_OF_INCREASE_PLACE - 4);
                setY (getY ());
                leftI--;
            }
            return true;
        }
        if (validSizeHeght () || validSizeWidth ()) {
            if (validSizeWidth ()) {
                if (!Epsilon.getEpsilon ().dontGoLeft (new Point2D.Double (0, 0))) {
                    setSize (new Dimension ((int) (getWidth () - DECREASE_OF_PANEL), (int) (getHeight ())));
                    setX (getX () + DECREASE_OF_PANEL);
                }
                if (!Epsilon.getEpsilon ().dontGoRight (new Point2D.Double (0, 0))) {
                    setSize (new Dimension ((int) (getWidth () - (DECREASE_OF_PANEL)), (int) (getHeight ())));
                    setX (getX ());
                }
            }
            if (validSizeHeght ()) {
                if (!Epsilon.getEpsilon ().dontGoUp (new Point2D.Double (0, 0))) {
                    setSize (new Dimension ((int) (getWidth ()), (int) (getHeight () - DECREASE_OF_PANEL)));
                    setY (getY () + DECREASE_OF_PANEL);
                }
                if (!Epsilon.getEpsilon ().dontGoDown (new Point2D.Double (0, 0))) {
                    setSize (new Dimension ((int) (getWidth ()), (int) (getHeight () - (DECREASE_OF_PANEL))));
                    setY (getY ());
                }
            }
            return true;
        } else {
            if (dothat) {
                DECREASE_OF_PANEL = 0.8;
                DELAY_OF_CLOSE_FRAME = 20;
                Update.CLOSE_FRAME.stop ();
                Update.CLOSE_FRAME.start ();
                dothat = false;
            }
            setSize (SMALLEST_SIZE_OF_ORIGINAL_PANEL);
            return false;
        }
    }

    private static boolean dothat = true;

    private static int RANGE_OF_INCREASE_CALL = 6;

    public void increase (panelWall panelWall) {
        switch (panelWall) {
            case left -> {
                leftI += RANGE_OF_INCREASE_CALL;
            }
            case right -> {
                rightI += RANGE_OF_INCREASE_CALL;
            }
            case up -> {
                upI += RANGE_OF_INCREASE_CALL;
            }
            case down -> {
                downI += RANGE_OF_INCREASE_CALL;
            }
        }
    }

    public static originalPanel getPanel () {
        if (Panel == null) {
            Panel = new originalPanel ();
        }
        return Panel;
    }

    public boolean validSizeWidth () {
        return getSize ().width > SMALLEST_SIZE_OF_ORIGINAL_PANEL.width;
    }

    public boolean validSizeHeght () {
        return getSize ().height > SMALLEST_SIZE_OF_ORIGINAL_PANEL.height;
    }
}
