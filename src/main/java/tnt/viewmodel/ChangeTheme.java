package tnt.viewmodel;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;


/**
 * Change Theme Class which Controlls the Theme
 */
public class ChangeTheme {
    private static int theme = 1;
    Image image;

    public static void setTheme(int themeNumber) {
    theme = themeNumber;
    }

    public static int getTheme() {
    return theme;
    }

    public static void loadTheme(ImageView imageView, StackPane pane) {
        String imagePath = "";
        if (theme == 1) {
            // Pfad zum Bild für Theme 1
            imagePath = "/image/Theme1.png";
        } else if (theme == 2) {
            // Pfad zum Bild für Theme 2
            imagePath = "/image/Theme2.png";
        }
    
        // Lade das Bild in die ImageView
        Image image = new Image(ChangeTheme.class.getResourceAsStream(imagePath));

        imageView.setImage(image);

    }

    public static void loadTheme2(ImageView imageView, AnchorPane pane) {
        String imagePath = "";
        if (theme == 1) {
            // Pfad zum Bild für Theme 1
            imagePath = "/image/Theme1.png";
        } else if (theme == 2) {
            // Pfad zum Bild für Theme 2
            imagePath = "/image/Theme2.png";
        }
    
        // Lade das Bild in die ImageView
        Image image = new Image(ChangeTheme.class.getResourceAsStream(imagePath));

        imageView.setImage(image);

    }
}