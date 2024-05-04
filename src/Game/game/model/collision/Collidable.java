package Game.game.model.collision;

import Game.game.model.characterModel.*;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import static Game.helper.addVectors;
import static Game.helper.multiplyVector;

public interface Collidable {
    ArrayList<Collidable> collidables = new ArrayList<> ();

    default boolean collision (Collidable col) {
        ObjectInGame s = (ObjectInGame) (col);
        if (this instanceof rasEpsilon.ras || col instanceof rasEpsilon.ras) {
            if (this instanceof Enemy || col instanceof Enemy) {
                return s.getCenter ().distance (getCenter ()) < s.getRadius () - 5;
            }
        }
        if (this instanceof Epsilon || col instanceof Epsilon) {
            if (this instanceof SquarantineModel || col instanceof SquarantineModel) {
                return s.getCenter ().distance (getCenter ()) < s.getRadius () - 5;
            }
            return s.getCenter ().distance (getCenter ()) < s.getRadius () - 2;

        }
        return s.getCenter ().distance (getCenter ()) < s.getRadius () + 2;
    }

    //        if (col instanceof TrigorathModel) {
//            return HitTrigo ((TrigorathModel) col);
//        } else if (col instanceof SquarantineModel) {
//            return HitSqure ((SquarantineModel) col);
//        }else if (col instanceof Epsilon){
//        }
//    }
    default Point2D.Double findCollisionPoint (Collidable collidable) {
        return addVectors (multiplyVector (collidable.getCenter (), 1 / 2d), multiplyVector (getCenter (), 1 / 2d));
    }

    Point2D.Double getCenter ();

    private boolean HitSqure (SquarantineModel s) {
        if (s.getCenter ().distance (getCenter ()) < s.getRadius () - 2) {
            return true;
        }
        return false;
    }


    private boolean HitTrigo (TrigorathModel t) {
        if (t.getCenter ().distance (this.getCenter ()) < t.getRadius () - 2) {
            return true;
        }
        return false;
    }
}
