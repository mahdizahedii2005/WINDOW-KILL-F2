package Game.game.Contoroler.control;

public class DefaultMethods {
    //SIN,COS PRE-PROCESSING//
    public static double[] sinTable = new double[360];
    public static double[] cosTable = new double[360];
    public static double[] radianTable = new double[360];

    static {
        for (int i = 0; i < 360; i++) {
            double radian = Math.toRadians(i);
            DefaultMethods.sinTable[i] = Math.sin(radian);
            DefaultMethods.cosTable[i] = Math.cos(radian);
            DefaultMethods.radianTable[i] = radian;
        }
    }

    public static String PURCHASE_MESSAGE(int cost) {
        return "Do you want to purchase this skill for " + cost + " XP?";
    }

    public static String SUCCESSFUL_PURCHASE_MESSAGE(String name) {
        return "You learned " + name + ".";
    }

    public static String ACTIVATE_MESSAGE(String name) {
        return "Do you want to choose \"" + name + "\" as your active skill?";
    }
}
