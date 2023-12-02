package tnt.viewmodel;

/**
 * @author Hendrik Gonschor
 */

 /**
  * ScreenSize Class which controlls the Size of the Screen
  */
public class ScreenSize {
    private static double screenWidth;
    private static double screenHeight;

    public static double getScreenWidth() {
        return screenWidth;
    }

    public static void setScreenWidth(double width) {
        screenWidth = width;
    }

    public static double getScreenHeight() {
        return screenHeight;
    }

    public static void setScreenHeight(double height) {
        screenHeight = height;
    }
}