package Game.game.model.closeFRame;

import java.util.ArrayList;

import static Game.Data.constants.MOVE_RANGE;
import static Game.Data.constants.RANGE_OF_DECREASE_PLACE;

public interface CLOSEABLE {
    public static ArrayList<CLOSEABLE> CLOSEABLES = new ArrayList<>();

    void reduceFrame();

    default void reduceLeft(double RANGE_OF_INCREASE_PLACE) {
        setWidth(getWidth() - RANGE_OF_INCREASE_PLACE);
        setX(getX() + RANGE_OF_INCREASE_PLACE);
    }

    default void reduceRight(double RANGE_OF_INCREASE_PLACE) {
        setWidth(getWidth() - RANGE_OF_INCREASE_PLACE);
    }

    default void reduceUp(double RANGE_OF_INCREASE_PLACE) {
        setHeight(getHeight() - RANGE_OF_INCREASE_PLACE);
        setY(getY() + RANGE_OF_INCREASE_PLACE);
    }

    default void reduceDown(double RANGE_OF_INCREASE_PLACE) {
        setHeight(getHeight() - RANGE_OF_INCREASE_PLACE);
    }

    default void reduceDown() {
        reduceDown(RANGE_OF_DECREASE_PLACE);
    }

    default void reduceUp() {
        reduceUp(RANGE_OF_DECREASE_PLACE);
    }

    default void reduceRight() {
        reduceRight(RANGE_OF_DECREASE_PLACE);
    }

    default void reduceLeft() {
        reduceLeft(RANGE_OF_DECREASE_PLACE);
    }

    public double getWidth();

    public void setWidth(double width);

    public double getHeight();

    public void setHeight(double height);

    public double getX();

    public void setX(double x);

    public double getY();

    public void setY(double y);
}
