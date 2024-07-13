package Game.game.model.collision;

import Game.game.model.characterModel.epsilonFriend.bolt;

import java.awt.geom.Point2D;

public class CollisionState {
    private Point2D.Float collisionPoint;
    private Collidable collidable1;
    private Collidable collidable2;
    private boolean collision = true;

    public CollisionState() {
        collision = false;
    }

    public CollisionState(Collidable c1, Collidable c2, Point2D.Float collisionPoint) {
        this.collisionPoint = collisionPoint;
        collidable1 = c1;
        collidable2 = c2;
    }
    public void PlayCollisionAction(){
        if (collidable1 instanceof bolt){
            collidable2.play(collidable1);
            collidable1.play(collidable2);
        }else {
            collidable1.play(collidable2);
            collidable2.play(collidable1);
        }
    }

    public Point2D.Float getCollisionPoint() {
        return collisionPoint;
    }



    public Collidable getCollidable1() {
        return collidable1;
    }


    public Collidable getCollidable2() {
        return collidable2;
    }

    public boolean isCollision() {
        return collision;
    }
}
