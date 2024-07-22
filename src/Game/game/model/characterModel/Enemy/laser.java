package Game.game.model.characterModel.Enemy;

import Game.game.model.characterModel.ObjectInGame;
import Game.game.model.characterModel.Panels.PanelInGame;
import Game.game.model.shooting.Aoe;
import Game.game.view.characterView.DrawAbleObject;
import Game.game.view.characterView.shape.nonCircular;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.UUID;

import static Game.game.Contoroler.control.Controller.fixThePoint;

public class laser extends ObjectInGame implements Aoe {
    ArrayList<String> coolDown =new ArrayList<>();
    public laser(ArrayList<Point2D> ver, Point2D.Double center,int damage) {
        super(center,Color.CYAN,UUID.randomUUID().toString(),1,0,damage);

        setVertices(ver);
    }

    @Override
    public DrawAbleObject getDrawAbleObject(PanelInGame panel) {
        return new nonCircular(fixThePoint( vertices, panel), color);
    }

    @Override
    public boolean checkIsOnCoolDown(String id) {
        for (String idCheck : coolDown){
            if (idCheck==null)continue;
            if (idCheck.equals(id))return true;
        }
        return false;
    }

    @Override
    public void CreateGeometry() {
        super.CreateGeometry();
    }

    @Override
    public void addToCoolDownList(String id) {
        coolDown.add(id);
    }

    @Override
    public void removeFromCoolDownList(String id) {
        coolDown.remove(id);
    }

    @Override
    public double getMAXRadius() {
        return getWidth() / 2d;
    }
    @Override
    public int getDamage() {
        return getDamageDaler();
    }

    @Override
    public int getX() {
        return (int) vertices.getFirst().getX();
    }


    @Override
    public int getY() {
        return (int) vertices.getFirst().getY();
    }

    @Override
    public int getWidth() {
        return (int) vertices.getFirst().distance(vertices.get(1));
    }

    @Override
    public int getHeight() {
        return (int) vertices.getFirst().distance(vertices.get(3));
    }

    @Override
    public double getRadiusX() {
        return 0;
    }

    @Override
    public double getRadiusY() {
        return 0;
    }

    @Override
    public Point2D.Double getAnchor() {
        return center;
    }

    @Override
    public void getHit(int damage) {
        return;
    }
}
