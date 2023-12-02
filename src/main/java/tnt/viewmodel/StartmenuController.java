package tnt.viewmodel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import lombok.Data;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Soohyeon Kim, Hendrik Gonschor
 * @Version
 */
@Data
public class StartmenuController implements Initializable {
    @FXML
    private Button settingBtn;
    @FXML
    private Button scoreBtn;
    @FXML
    private Button endBtn;
    @FXML
    private Button startBtn;
    @FXML
    private Button loadBtn;

    @FXML
    private VBox buttonBox;

    @FXML
    private StackPane mainPane;

    @FXML
    private ImageView themeImage;

    /**
     * The function for the mainmenu's Actions of Buttons
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainPane.setPrefWidth(ScreenSize.getScreenWidth());
        mainPane.setPrefHeight(ScreenSize.getScreenHeight());

        StackPane.setMargin(themeImage, new Insets(0));
        translateManager();

        settingBtn.setOnAction(actionEvent -> {
            this.loadSettingScreen();
        });
        startBtn.setOnAction(actionEvent -> {
            loadGameSettingScreen();
        });
        this.scoreBtn.setOnAction((actionEvent) -> {
            this.loadScoreScreen();
        });
        this.endBtn.setOnAction((actionEvent) -> {
            Stage primaryStage = (Stage) endBtn.getScene().getWindow();
            primaryStage.close();
        });

        // Rufe die loadTheme-Methode auf
        ChangeTheme.loadTheme(themeImage, mainPane);
        StackPane.setAlignment(buttonBox, javafx.geometry.Pos.CENTER);

        themeImage.fitWidthProperty().bind(mainPane.widthProperty());
        themeImage.fitHeightProperty().bind(mainPane.heightProperty());


        //Listeners
        mainPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            adjustButtonSizes(mainPane.getHeight(), newWidth.doubleValue());
            ScreenSize.setScreenWidth(newWidth.doubleValue());
            //System.out.println(newWidth);
        });

        mainPane.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            adjustButtonSizes(newHeight.doubleValue(), mainPane.getWidth());
            ScreenSize.setScreenHeight(newHeight.doubleValue());
            //System.out.println(newHeight);
        });
    }


    /**
     * Translates the mainmenu
     */
    private void translateManager(){

        if(LanguageManager.getCurrentLanguage()== "en"){
            startBtn.setText("Play");
            settingBtn.setText("Setting");
            scoreBtn.setText("Score");
            loadBtn.setText("Load");
            endBtn.setText("Exit");

        }else if(LanguageManager.getCurrentLanguage()== "de"){
            startBtn.setText(LanguageManager.getText("play"));
            settingBtn.setText(LanguageManager.getText("setting"));
            scoreBtn.setText(LanguageManager.getText("score"));
            loadBtn.setText(LanguageManager.getText("load"));
            endBtn.setText(LanguageManager.getText("exit"));

        }else if(LanguageManager.getCurrentLanguage()== "kr"){
            startBtn.setText(LanguageManager.getText("play"));
            settingBtn.setText(LanguageManager.getText("setting"));
            scoreBtn.setText(LanguageManager.getText("score"));
            loadBtn.setText(LanguageManager.getText("load"));
            endBtn.setText(LanguageManager.getText("exit"));
        }
    }


    /**
     * load the SettingsScene
     */
    private void loadSettingScreen() {
        try {
            URL url = getClass().getResource("/fxml/Setting.fxml");
            Parent root = FXMLLoader.load(url);

            Scene settingScene = new Scene(root);

            Stage currentStage = (Stage) settingBtn.getScene().getWindow();

            currentStage.setScene(settingScene);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the Highscore Scene
     */
    private void loadScoreScreen() {
        try {
            URL url = getClass().getResource("/fxml/Score.fxml");
            Parent root = FXMLLoader.load(url);

            Scene scoreScene = new Scene(root);

            Stage currentStage = (Stage) scoreBtn.getScene().getWindow();

            currentStage.setScene(scoreScene);
        }catch (IOException var3) {
            var3.printStackTrace();
        }
    }

    /**
     * show mainmenulayout
     */
    private void loadGameSettingScreen() {
        try {
            URL url = getClass().getResource("/fxml/Gamesettings.fxml");
            Parent root = FXMLLoader.load(url);

            Scene gameSettingsScene = new Scene(root);

            Stage currentStage = (Stage) startBtn.getScene().getWindow();

            currentStage.setScene(gameSettingsScene);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * adjust the button size
     * @param containerSize for the height and width
     */
    private void adjustButtonSizes(double containerSize, double containerSize2) {
        double scaleFactorHeight = containerSize / 500.0; // 500.0 ist die ursprüngliche Höhe des Containers
        double scaleFactorWidth = containerSize2 / 750.0; // 750.0 ist die ursprüngliche Höhe des Containers
        double buttonWidth = Math.max(Math.min(180.0 * scaleFactorWidth, 360), 100.0);
        double buttonHeight = Math.max(Math.min(50.0 * scaleFactorHeight, 100), 30.0);

        startBtn.setPrefWidth(buttonWidth);
        startBtn.setPrefHeight(buttonHeight);

        loadBtn.setPrefWidth(buttonWidth);
        loadBtn.setPrefHeight(buttonHeight);
    
        settingBtn.setPrefWidth(buttonWidth);
        settingBtn.setPrefHeight(buttonHeight);
    
        scoreBtn.setPrefWidth(buttonWidth);
        scoreBtn.setPrefHeight(buttonHeight);
    
        endBtn.setPrefWidth(buttonWidth);
        endBtn.setPrefHeight(buttonHeight);

    }
}