package tnt.viewmodel;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Data;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * viewmodel for Setting
 *
 * @author Soohyeon Kim, Hendrik Gonschor
 */
@Data
public class SettingController implements Initializable {

    @FXML
    Button backBtn;

    @FXML
    StackPane settingScreen;

    @FXML
    private ImageView themeImageSettings;

    @FXML
    private VBox settingsBox;

    @FXML
    private ChoiceBox<String> themeChoiceBox;

    @FXML
    private ChoiceBox languageChoiceBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        settingScreen.setPrefWidth(ScreenSize.getScreenWidth());
        settingScreen.setPrefHeight(ScreenSize.getScreenHeight());
        translateManager();

        StackPane.setAlignment(settingsBox, javafx.geometry.Pos.CENTER);

        //background
        themeImageSettings.fitWidthProperty().bind(settingScreen.widthProperty());
        themeImageSettings.fitHeightProperty().bind(settingScreen.heightProperty());

        backBtn.setOnAction(actionEvent -> {
            loadmainmenu();
        });

        //Listeners
        settingScreen.widthProperty().addListener((obs, oldWidth, newWidth) -> {
        adjustSizes(settingScreen.getHeight(), newWidth.doubleValue());
        ScreenSize.setScreenWidth(newWidth.doubleValue());
        });
    
        settingScreen.heightProperty().addListener((obs, oldHeight, newHeight) -> {
        adjustSizes(newHeight.doubleValue(), settingScreen.getWidth());
        ScreenSize.setScreenHeight(newHeight.doubleValue());
        }); 

        //Set the selected Language
        String selectedLanguage = LanguageManager.getCurrentLanguage();
        if (selectedLanguage=="en"){
            languageChoiceBox.setValue("English");
        } else if (selectedLanguage=="de") {
            languageChoiceBox.setValue("Deutsch");
        } else if (selectedLanguage=="kr") {
            languageChoiceBox.setValue("\uD55C\uAD6D\uC5B4");
        }

        //Sets the Theme for the Scene
        themeChoiceBox.setItems(FXCollections.observableArrayList("Horror", "Blood Moon"));
        if (ChangeTheme.getTheme()==1) {
            themeChoiceBox.setValue("Horror");
        } else if (ChangeTheme.getTheme()==2) {
            themeChoiceBox.setValue("Blood Moon");
        }

        themeChoiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue.equals("Horror")) {
                ChangeTheme.setTheme(1);
            } else if (newValue.equals("Blood Moon")) {
                ChangeTheme.setTheme(2);
            }
            ChangeTheme.loadTheme(themeImageSettings, settingScreen);
        });

        //Changes the Theme
        ChangeTheme.loadTheme(themeImageSettings, settingScreen);
    }

    /**
     * Translate according to the language settings
     */
    public void translateManager(){
        languageChoiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->{
            if(newValue.equals("Deutsch")){
                LanguageManager.setLanguage("de");
                backBtn.setText( LanguageManager.getText("close"));

            }else if(newValue.equals("English")){
                LanguageManager.setCurrentLanguage("en");
                backBtn.setText("Back");

            }else if(newValue.equals("한국어")){
                LanguageManager.setLanguage("kr");
                backBtn.setText( LanguageManager.getText("close"));
            }

        });
    }

    private void adjustSizes(double containerSize, double containerSize2) {
        double scaleFactorHeight = containerSize / 500.0;
        double scaleFactorWidth = containerSize2 / 750.0; 
        double buttonWidth = Math.max(Math.min(180.0 * scaleFactorWidth, 360), 100.0);
        double buttonHeight = Math.max(Math.min(50.0 * scaleFactorHeight, 100), 30.0);
        double boxWidth = Math.max(Math.min(150.0 * scaleFactorWidth, 300), 75.0);
        double boxHeight = Math.max(Math.min(30.0 * scaleFactorHeight, 60), 20.0);

        backBtn.setPrefWidth(buttonWidth);
        backBtn.setPrefHeight(buttonHeight);

        languageChoiceBox.setPrefHeight(boxHeight);
        languageChoiceBox.setPrefWidth(boxWidth);

        themeChoiceBox.setPrefHeight(boxHeight);
        themeChoiceBox.setPrefWidth(boxWidth);

        VBox.setMargin(backBtn, new Insets(80.0, 0.0, 0.0, 0.0));
    }


    /**
     * show mainmenu
     */
    public void loadmainmenu(){
        try {
            URL url = getClass().getResource("/fxml/Startmenu.fxml");
            Parent root = FXMLLoader.load(url);

            Scene startScene = new Scene(root);

            Stage currentStage = (Stage) backBtn.getScene().getWindow();

            currentStage.setScene(startScene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

