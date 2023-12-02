package tnt.gui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.net.URL;

/**
 * Starting point of the JavaFX GUI
 */
public class Startmenu extends Application {

    /**
     * The entry point of the GUI application.
     *
     * @param args The command line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * This method is called by the Application to start the GUI.
     *
     * @param primaryStage The initial root stage of the application.
     */
    @Override
    public void start(Stage primaryStage) {
        try{
            URL url = getClass().getResource("/fxml/Startmenu.fxml");
            Parent root = FXMLLoader.load(url);

            Scene mainMenu = new Scene(root,750,500);
            primaryStage.setTitle("TNT");
            primaryStage.setScene(mainMenu);
            primaryStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
