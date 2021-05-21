package wsp.utils;

public class Util {
    public static final String COLOR_RESET = "\033[0m";
    public static final String COLOR_BLACK = "\033[0;30m";
    public static final String COLOR_RED = "\033[0;31m";
    public static final String COLOR_GREEN = "\033[0;32m";
    public static final String COLOR_BLUE = "\033[0;34m";
    public static final String COLOR_WHITE = "\033[0;37m";

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