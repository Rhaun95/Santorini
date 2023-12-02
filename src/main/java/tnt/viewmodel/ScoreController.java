//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package tnt.viewmodel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * viewmodel for Score
 *
 * @author Hendrik Gonschor
 */

public class ScoreController implements Initializable {

    @FXML
    private Button backBtn;
    @FXML
    private ImageView themeImageScore;
    @FXML
    private StackPane scoreScreen;
    @FXML
    private VBox scoreBox;
    @FXML
    private Text score1;
    @FXML
    private Text score2;
    @FXML
    private Text score3;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        scoreScreen.setPrefWidth(ScreenSize.getScreenWidth());
        scoreScreen.setPrefHeight(ScreenSize.getScreenHeight());
        translateManager();

        StackPane.setAlignment(scoreBox, javafx.geometry.Pos.CENTER);

        this.backBtn.setOnAction((actionEvent) -> {
            this.loadmainmenu();
        });

        themeImageScore.fitWidthProperty().bind(scoreScreen.widthProperty());
        themeImageScore.fitHeightProperty().bind(scoreScreen.heightProperty());
        ChangeTheme.loadTheme(themeImageScore, scoreScreen);

        // Lade die Highscores in die Text-Labels
        loadHighscores();

        //listeners
        scoreScreen.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            adjustSizes(scoreScreen.getHeight(), newWidth.doubleValue());
            ScreenSize.setScreenWidth(newWidth.doubleValue());
        });
    
        scoreScreen.heightProperty().addListener((obs, oldHeight, newHeight) -> {
            adjustSizes(newHeight.doubleValue(), scoreScreen.getWidth());
            ScreenSize.setScreenHeight(newHeight.doubleValue());
        }); 

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

    private void adjustSizes(double containerSize, double containerSize2) {
        double scaleFactorHeight = containerSize / 500.0;
        double scaleFactorWidth = containerSize2 / 750.0; 
        double buttonWidth = Math.max(Math.min(180.0 * scaleFactorWidth, 360), 100.0);
        double buttonHeight = Math.max(Math.min(50.0 * scaleFactorHeight, 100), 30.0);
        double fontSize = containerSize / 20;

        backBtn.setPrefWidth(buttonWidth);
        backBtn.setPrefHeight(buttonHeight);

        Font font = Font.font("System Bold", fontSize);
        score1.setFont(font);
        score2.setFont(font);
        score3.setFont(font);
        score1.setWrappingWidth(containerSize2/3);
        score2.setWrappingWidth(containerSize2/3);
        score3.setWrappingWidth(containerSize2/3);

        VBox.setMargin(backBtn, new Insets(80.0, 0.0, 0.0, 0.0));
    }

    /**
     * translates
     */
    private void translateManager(){
        if(LanguageManager.getCurrentLanguage()== "en"){
            backBtn.setText("Back");
        } else if(LanguageManager.getCurrentLanguage()== "de"){
            backBtn.setText(LanguageManager.getText("close"));
        } else if(LanguageManager.getCurrentLanguage()== "kr") {
            backBtn.setText(LanguageManager.getText("close"));
        }
    }

    /**
     * loads the Highscores from the Highscore.txt and shows them on screen
     */
    private void loadHighscores() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/saves/highscores.txt"))) {
            String line;
            int index = 1;
            while ((line = reader.readLine()) != null && index <= 3) {
                String[] parts = line.split(" ",2);
                if (parts.length >= 2) {
                    String playerName = parts[1];
                    int turnAmount = Integer.parseInt(parts[0]);

                    // Setze den Highscore in das entsprechende Text-Label
                    if (index == 1) {
                        score1.setText("1. " + playerName + " - " + turnAmount + " turns");
                    } else if (index == 2) {
                        score2.setText("2. " + playerName + " - " + turnAmount + " turns");
                    } else if (index ==3) {
                        score3.setText("3. " + playerName + " - " + turnAmount + " turns");
                    }

                    index++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

