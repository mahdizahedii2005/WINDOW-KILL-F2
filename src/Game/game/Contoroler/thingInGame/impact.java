package Game.game.Contoroler.thingInGame;

import Game.game.model.Move.Direction;
import Game.game.model.Move.impactAble;
import Game.game.model.characterModel.Enemy.moveAbleEnemy;

import java.awt.geom.Point2D;

import static Game.Data.constants.*;
import static Game.helper.*;

public class impact {
    float distance;
    Point2D mabda;
    double impactPower;

    public impact (Point2D mabda, double impactPower, int distance) {
        if (mabda == null) {
            return;
        } else {
            this.distance = IMPACT_RADIUS;
            this.mabda = mabda;
            this.impactPower = impactPower;
            startImpact ();
        }
    }

    public impact (Point2D mabda) {
        this (mabda, IMPACT_POWER, 200);
    }

    public void startImpact () {
        for (int i = 0 ; i < impactAble.impactAblesList.size () ; i++) {
            impactAble imp = impactAble.impactAblesList.get (i);
            double distance = imp.getCenter ().distance (mabda);
            if (distance < this.distance) {
                makeAnImpact (imp, distance);
            }
        }
    }

    public void makeAnImpact (impactAble able, double distance) {
        double power = (impactPower / IMPACT_CYCLE);
        if (power > 0) {
//            if (impactPower == IMPACT_POWER) {
                able.setSpeed (Math.sqrt (power * power + able.getSpeed () * able.getSpeed () - (able.getSpeed () * power)));
//            }
            if (able instanceof moveAbleEnemy) {
                able.setMoveDirection (new Direction (new Direction (relativeLocation (able.getCenter (), new Point2D.Double((double) mabda.getX(),(double) mabda.getY())))));
            } else {
                able.setMoveDirection (new Direction (addVectors (new Direction (relativeLocation
                        (able.getCenter (), new Point2D.Double((double) mabda.getX(),(double) mabda.getY()))).getPoint (), able.getMoveDirection ().getPoint ())));
            }
            able.setImpactNum (Math.max (0, (int) ((IMPACT_CYCLE - (distance / (this.distance / IMPACT_CYCLE))))));
        }
    }
}
