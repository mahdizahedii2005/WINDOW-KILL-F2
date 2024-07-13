package Game.game.model.Move;

import Game.game.Contoroler.control.DefaultMethods;

import java.awt.geom.Point2D;

import static Game.game.model.Utils.deepCopy;
import static Game.game.model.Utils.multiplyPoint;


public class DirectionAryanEdition  {
    public float directionSlope;
    public DirectionOrientation direction;
    public boolean isUpside = false;
    public boolean isDownside = false;

    public DirectionAryanEdition(float directionSlope, DirectionOrientation direction) {
        this.directionSlope = directionSlope;
        this.direction = direction;
    }
    float DIRECTION_SENSITIVITY =0.00001f;

    public DirectionAryanEdition(Point2D point) {
        if ((point.getX() == 0 || Math.abs(point.getY() / point.getX()) > 1 / DIRECTION_SENSITIVITY) && point.getY() > 0) {
            this.directionSlope = 0;
            this.direction = DirectionOrientation.positive;
            isUpside = true;
        } else if ((point.getX() == 0 || Math.abs(point.getY() / point.getX()) > 1 / DIRECTION_SENSITIVITY) && point.getY() < 0) {
            this.directionSlope = 0;
            this.direction = DirectionOrientation.positive;
            isDownside = true;
        } else if (point.getX() == 0 || Math.abs(point.getY() / point.getX()) > 1 / DIRECTION_SENSITIVITY) {
            this.directionSlope = 0;
            this.direction = DirectionOrientation.stable;
        } else {
            this.directionSlope = (float) (point.getY() / point.getX());
            if (point.getX() > 0) this.direction = DirectionOrientation.positive;
            else this.direction = DirectionOrientation.negative;
        }
    }

    public DirectionAryanEdition(float angle) {
        float newAngle = (float) (angle - Math.floor(angle / 360) * 360);
        deepCopy(new DirectionAryanEdition(new Point2D.Float((float) DefaultMethods.cosTable[(int) newAngle], (float) DefaultMethods.sinTable[(int) newAngle])), this);
    }

    public Point2D getDirectionVector() {
        if (direction == DirectionOrientation.stable) return new Point2D.Float(0, 0);
        if (isDownside) return new Point2D.Float(0, -1);
        if (isUpside) return new Point2D.Float(0, 1);

        float normalScale = (float) Math.sqrt(1 / (1 + directionSlope * directionSlope));
        if (direction == DirectionOrientation.positive) return multiplyPoint(new Point2D.Float(1, directionSlope), normalScale);
        if (direction == DirectionOrientation.negative) return multiplyPoint(new Point2D.Float(-1, -directionSlope), normalScale);
        return null;
    }

    public enum DirectionOrientation {
        positive, negative, stable
    }
}
