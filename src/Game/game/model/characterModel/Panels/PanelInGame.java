package Game.game.model.characterModel.Panels;

import Game.game.model.Move.Direction;
import Game.game.model.characterModel.ThingsInGame;
import Game.game.model.characterModel.epsilonFriend.Epsilon;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.UUID;

import static Game.Data.constants.PANEL_BACK_GRAND;
import static Game.Data.constants.PANEL_BAR_GRAND;
import static Game.game.Contoroler.control.Controller.CreatNewPanelView;
import static Game.game.Contoroler.control.Controller.findPanelView;
import static Game.game.model.Utils.toCoordinate;

public abstract class PanelInGame extends ThingsInGame implements BeingSolb {
    public static ArrayList<PanelInGame> PANELS = new ArrayList<>();
    private Dimension size;
    private double width;
    private double height;
    private double x;
    private double y;
    private Color backGrandColor = PANEL_BACK_GRAND;
    private Color SidebarColor = PANEL_BAR_GRAND;
    protected Geometry geometry;
    protected boolean isSolb;
    protected double speed;
    protected Direction moveDirection;
    private ArrayList<Point2D> vertices;

    private void fixVer() {
        try {
            vertices = new ArrayList<>();
            vertices.add(new Point2D.Double(x, y));
            vertices.add(new Point2D.Double(x + width, y));
            vertices.add(new Point2D.Double(x + width, y + height));
            vertices.add(new Point2D.Double(x, y + height));
        } catch (ArrayIndexOutOfBoundsException R) {
        }
    }

    public PanelInGame(double x, double y, double height, double width, boolean isSolb, double speed) {
        super(UUID.randomUUID().toString());
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        this.isSolb = isSolb;
        this.speed = speed;
        fixVer();
        size = new Dimension((int) width, (int) height);
        moveDirection = new Direction(new Point2D.Double(0, 0));
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
        return this instanceof NonIsometricPanel;
    }

    @Override
    public boolean getIsSolb() {
        return isSolb;
    }

    @Override
    public void createGeometry() {
        fixVer();
        var size = vertices.size();
        if (size != 0) {
            Coordinate[] coordinates = new Coordinate[size + 1];
            try {
                for (int i = 0; i < size; i++) coordinates[i] = toCoordinate(vertices.get(i));

            coordinates[size] = toCoordinate(vertices.get(0));
            geometry = new GeometryFactory().createLineString(coordinates);
            } catch (IndexOutOfBoundsException e) {
            }
        } else geometry = new GeometryFactory().createLineString(new Coordinate[0]);
    }

    @Override
    public void setDirection(Direction direction) {
        moveDirection = direction;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public void move(Direction direction, double speed) {
        setX(getX() + direction.getDirectionVector().getX() * speed);
        setY(getY() + direction.getDirectionVector().getY() * speed);
    }

    public boolean movee(Direction direction, double speed) {
        var saveX = getX();
        var saveY = getY();
        setX(getX() + direction.getDirectionVector().getX() * speed);
        setY(getY() + direction.getDirectionVector().getY() * speed);
        if (!validPanel(Epsilon.getEpsilon().mainPanel)) {
            setX(saveX);
            setY(saveY);
            setY(saveY);
            return false;
        }
        return true;
    }

    private boolean validPanel(NonIsometricPanel checkPanel) {
        return checkPanel.validLine(checkPanel.getDownPanel(), checkPanel) &&
                checkPanel.validLine(checkPanel.getUpPanel(), checkPanel) &&
                checkPanel.validLine(checkPanel.getRightPanel(), checkPanel) &&
                checkPanel.validLine(checkPanel.getLeftPanel(), checkPanel);

    }

    @Override
    public void move() {
        movee(moveDirection, getSpeed());
    }

    public ArrayList<Point2D> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Point2D> vertices) {
        this.vertices = vertices;
    }

    public void clear() {
        super.Die();
        if (findPanelView(id) != null) findPanelView(id).clear();
    }

    public static ArrayList<PanelInGame> getPANELS() {
        return PANELS;
    }

    public static void setPANELS(ArrayList<PanelInGame> PANELS) {
        PanelInGame.PANELS = PANELS;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    @Override
    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public boolean isSolb() {
        return isSolb;
    }

    public void setSolb(boolean solb) {
        isSolb = solb;
    }

    @Override
    public float getSpeed() {
        return (float) speed;
    }

    public Direction getMoveDirection() {
        return moveDirection;
    }

    public void setMoveDirection(Direction moveDirection) {
        this.moveDirection = moveDirection;
    }
}
