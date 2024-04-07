package Game;
public abstract class helper {
    public static int[] changerD(double[] arr) {
        int[] result = new int[arr.length];
        int i = 0;
        for (double a : arr) {
            result[i] = (int) (a);
            i++;
        }
        return result;
    }
}
