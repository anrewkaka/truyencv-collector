package xyz.lannt.truyencvcollector.utilities;

public class NumberUtility {

    public static int toInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}
