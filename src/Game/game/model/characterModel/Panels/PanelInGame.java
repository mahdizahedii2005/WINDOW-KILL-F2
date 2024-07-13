package Game.game.model.characterModel.Panels;

import Game.game.model.characterModel.ThingsInGame;
import org.locationtech.jts.geom.Geometry;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.UUID;

import static Game.Data.constants.*;
import static Game.game.Contoroler.control.Controller.CreatNewPanelView;

public abstract class PanelInGame extends ThingsInGame  {
    public static ArrayList<PanelInGame> PANELS = new ArrayList<>();
    private Dimension size;
    private double width;
    private double height;
    private double x;
    private double y;
    private Color backGrandColor = PANEL_BACK_GRAND;
    private Color SidebarColor = PANEL_BAR_GRAND;
    protected Geometry geometry;

    public PanelInGame(double x, double y, double height, double width) {
        super(UUID.randomUUID().toString());
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        size = new Dimension((int) width, (int) height);
        CreatNewPanelView(getId());
        PANELS.add(this);
    }

    public Dimension getSize() {
        return size;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
        size = new Dimension((int) width, size.height);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        size = new Dimension(size.width, (int) height);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Color getBackGrandColor() {
        return backGrandColor;
    }

    public void setBackGrandColor(Color backGrandColor) {
        this.backGrandColor = backGrandColor;
    }

    public Color getSidebarColor() {
        return SidebarColor;
    }

    public void setSidebarColor(Color sidebarColor) {
        SidebarColor = sidebarColor;
    }

    public Line2D.Double getLeftPanel() {
        return new Line2D.Double(x, y, x, y + height);
    }

    public Line2D.Double getUpPanel() {
        return new Line2D.Double(x, y, x + width, y);
    }

    public Line2D.Double getRightPanel() {
        return new Line2D.Double(x + width, y, x + width, y + height);
    }

    public Line2D.Double getDownPanel() {
        return new Line2D.Double(x, y + height, x + width, y + height);
    }

    public boolean isRigid() {
        return this instanceof rigidPanel;
    }

}
