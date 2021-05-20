package wsp.utils;

public class Util {
    private Util() {}

    public static int parseChoice(String choice) {
        try {
            return (Integer.parseInt(choice) - 1);
        } catch (NumberFormatException exc) {
            return -1;
        }
    }

    public static boolean isInRange(int number, int left, int right) {
        return (number >= left && number <= right);
    }
}