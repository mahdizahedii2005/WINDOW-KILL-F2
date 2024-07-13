package Game.game.Contoroler.control;

import Game.game.view.PanelInGame.panelInGameView;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class whatPanelAndWhere {
    private panelInGameView jPanel;
    private ArrayList<Point2D> panelPoints;
    private Point2D anchor ;

    public whatPanelAndWhere(panelInGameView jPanel, ArrayList<Point2D> panelPoints) {
        this.jPanel = jPanel;
        this.panelPoints = panelPoints;
    }

    public whatPanelAndWhere(Point2D anchor, panelInGameView jPanel) {
        this.anchor = anchor;
        this.jPanel = jPanel;
    }

    public ArrayList<Point2D> getPanelPoints() {
        return panelPoints;
    }

    public panelInGameView getJPanel() {
        return jPanel;
    }

    public Point2D getAnchor() {
        return anchor;
    }
}
