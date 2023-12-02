package tnt.viewmodel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import lombok.Data;
import tnt.model.*;
import tnt.model.god.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Provides the user with options fo the game e.g. name and colour of the player, amount of building blocks and God cards.
 * Also, makes sure all interactive elements in the Gamesettings-Screen are functioning in the right way and the user is supported
 * with making valid and complete entries to continue to the actual game.
 * @author Veronika Wehr
 */

@Data
public class GamesettingsController implements Initializable {


    @FXML
    private AnchorPane settingPane;
    @FXML
    private Menu menumenu;
    @FXML
    private Text siegHoehe1;
    @FXML
    private Text seperateStonePoolText;
    @FXML
    private Text legalMovesLabel1;
    @FXML
    private Menu menusettings;
    @FXML
    private MenuItem menuload;
    @FXML
    private MenuItem menuToHome;
    @FXML
    private MenuItem germanItem2;
    @FXML
    private MenuItem englishItem2;
    @FXML
    private MenuItem koreanItem2;
    @FXML
    private MenuItem themeItem2;
    @FXML
    private Button startbutton;
    @FXML
    private Text playerLabel;

    @FXML
    private ImageView themeImageGamesettings;

    @FXML
    private HBox hboxPlayer3;

    @FXML
    private TextField namePlayer1;
    @FXML
    private TextField namePlayer2;
    @FXML
    private TextField namePlayer3;

    @FXML
    private ColorPicker colourPlayer1;
    @FXML
    private ColorPicker colourPlayer2;

    @FXML
    private ColorPicker colourPlayer3;


    @FXML
    private Text computerTextPlayer3;

    @FXML
    private Text selectGodTextPlayer3;


    @FXML
    public Button addPlayerButton;

    @FXML
    private Text godLabel;
    @FXML
    private Text godSubLabel1;
    @FXML
    private Text godSubLabel2;

    @FXML
    public CheckBox godsForceCheck;

    @FXML
    public ComboBox<String> godlist1;

    @FXML
    public ComboBox<String> godlist2;

    @FXML

    public ComboBox<String> godlist3;

    private List<ComboBox<String>> godlists = new ArrayList<>();

    @FXML
    public CheckBox isCompP1;

    @FXML
    public CheckBox isCompP2;

    @FXML
    public CheckBox isCompP3;

    @FXML
    public ToggleGroup workerToggle;

    @FXML
    public RadioButton oneWorker;

    @FXML
    public RadioButton twoWorkers;

    @FXML
    public RadioButton threeWorkers;

    @FXML
    private Text workerAmountLabel;

    @FXML
    private Text workerSubLabel;
    @FXML
    private Text steinAnzahlLabel;
    @FXML
    private Text stoneLabel1;
    @FXML
    private Text stoneLabel2;
    @FXML
    private Text stoneLabel3;
    @FXML
    private Text stoneLabel4;

    @FXML
    private Text stoneLabel5;
    @FXML
    private Text stoneLabel6;

    @FXML
    public TextField amountLevel1;

    @FXML
    public TextField amountLevel2;


    @FXML
    public TextField amountLevel3;

    @FXML
    public TextField amountDome;


    @FXML
    public TextField amountLevel5;

    @FXML
    public TextField amountLevel6;

    @FXML
    public Button deleteThirdPlayerX;


    @FXML
    private Text globeLabel;
    @FXML
    private CheckBox globe;

    @FXML
    private ChoiceBox winningHeightChoice;

    @FXML
    private VBox blockAmountLabels;

    @FXML
    private VBox blockAmountFields;

    @FXML
    private Text siegHoehe;

    @FXML
    private Text fieldsize;

    @FXML
    private Text legalMovesLabel;

    @FXML
    private Text moveUpLabel;

    @FXML
    private Text moveDownLabel;

    @FXML
    private String upAll;

    @FXML
    private String downAll;

    @FXML
    private ChoiceBox<String> upChoiceBox;

    @FXML
    private ChoiceBox<String> downChoiceBox;

    String all = "all";

    @FXML
    private CheckBox seperateStonePoolCheck;

    public CheckBox getSeperateStonePoolCheck(){
        return seperateStonePoolCheck;
    }

    private Player player1;

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer3() {
        return player3;
    }

    public void setPlayer3(Player player3) {
        this.player3 = player3;
    }

    private Player player2;
    private Player player3;

    String selectedGodP1;
    String selectedGodP2;
    String selectedGodP3;

    @FXML
    private ToggleGroup fieldToggle;
    @FXML
    private RadioButton fieldSize5;
    @FXML
    private RadioButton fieldSize8;
    private int fieldSize = 5;

    Random random = new Random();

    GameboardController tmp ;
    @FXML
    ChoiceBox aiLevel;

    private void updateChoiceBoxStatus() {
        // CheckBox 중 하나라도 선택되어 있으면 ChoiceBox를 활성화, 그렇지 않으면 비활성화

        if (isCompP1.isSelected() || isCompP2.isSelected() || isCompP3.isSelected()) {
            aiLevel.setDisable(false);
        } else {
            aiLevel.setDisable(true);
        }

    }
    private void updateChoiceBoxStatus1() {
        // CheckBox 중 하나라도 선택되어 있으면 ChoiceBox를 활성화, 그렇지 않으면 비활성화

        if (isCompP1.isSelected() || isCompP2.isSelected()) {
            aiLevel.setDisable(false);
        } else {
            aiLevel.setDisable(true);
        }

    }
    public int getFieldSize(){

        if(fieldToggle.getSelectedToggle()==fieldSize8){
            fieldSize= 8;
        }else {
            fieldSize=5;
        }
        return fieldSize;
    }
    public void translateManager(){

        /**
         * translate according to the setting
         */
        if(LanguageManager.getCurrentLanguage()== "en"){
            playerLabel.setText("Player");
            namePlayer1.setText("Player 1");
            namePlayer2.setText("Player 2");
            godLabel.setText("God´s Force");
            godSubLabel1.setText("select a god");
            godSubLabel2.setText("select a god");
            addPlayerButton.setText("Add Player");
            workerAmountLabel.setText("Worker Amount");
            workerSubLabel.setText("per Player");
            steinAnzahlLabel.setText("Block amount");
            stoneLabel1.setText("Level 1");
            stoneLabel2.setText("Level 2");
            stoneLabel3.setText("Level 3");
            stoneLabel4.setText("Dome");
            globeLabel.setText("World is a globe");
            startbutton.setText("Start game!");
            menumenu.setText("Menu");
            menuload.setText("Load");
            menusettings.setText("Settings");
            menuToHome.setText("Home");
            germanItem2.setText("German");
            englishItem2.setText("English");
            koreanItem2.setText("Korean");
            themeItem2.setText("Change Theme");
            siegHoehe.setText("Winning height");
            legalMovesLabel.setText("Legal moves");
            moveUpLabel.setText("up");
            moveDownLabel.setText("down");
            downChoiceBox.setValue(all);
            fieldsize.setText("Field Size");



        }else if(LanguageManager.getCurrentLanguage()== "de"){
            playerLabel.setText(LanguageManager.getText("playerLabel"));
            namePlayer1.setText(LanguageManager.getText("player1"));
            namePlayer2.setText(LanguageManager.getText("player2"));
            godLabel.setText(LanguageManager.getText("godLabel"));
            godSubLabel1.setText(LanguageManager.getText("godSubLabel"));
            godSubLabel2.setText(LanguageManager.getText("godSubLabel"));
            addPlayerButton.setText(LanguageManager.getText("addPlayer"));
            workerAmountLabel.setText(LanguageManager.getText("workerAmount"));
            workerSubLabel.setText(LanguageManager.getText("workerSubLabel"));
            steinAnzahlLabel.setText(LanguageManager.getText("blockAmount"));
            stoneLabel1.setText(LanguageManager.getText("level1"));
            stoneLabel2.setText(LanguageManager.getText("level2"));
            stoneLabel3.setText(LanguageManager.getText("level3"));
            stoneLabel4.setText(LanguageManager.getText("dome"));
            globeLabel.setText(LanguageManager.getText("globeLabel"));
            startbutton.setText(LanguageManager.getText("startbutton"));
            menumenu.setText(LanguageManager.getText("menumenu"));
            menuload.setText(LanguageManager.getText("menuload"));
            menusettings.setText(LanguageManager.getText("menusettings"));
            menuToHome.setText(LanguageManager.getText("menuhome"));
            germanItem2.setText(LanguageManager.getText("menugerman"));
            englishItem2.setText(LanguageManager.getText("menuenglish"));
            koreanItem2.setText(LanguageManager.getText("menukorean"));
            themeItem2.setText(LanguageManager.getText("menutheme"));
            siegHoehe.setText(LanguageManager.getText("winheight"));
            legalMovesLabel.setText(LanguageManager.getText("legalmoves"));
            moveUpLabel.setText(LanguageManager.getText("up"));
            moveDownLabel.setText(LanguageManager.getText("down"));
            downChoiceBox.setValue(LanguageManager.getText("all"));
            fieldsize.setText(LanguageManager.getText("fieldsize"));


        }else if(LanguageManager.getCurrentLanguage()== "kr"){
            seperateStonePoolText.setText(LanguageManager.getText("seperateStonePoolText"));
            siegHoehe1.setText(LanguageManager.getText("siegHoehe1"));
            legalMovesLabel1.setText(LanguageManager.getText("legalMovesLabel1"));
            playerLabel.setText(LanguageManager.getText("playerLabel"));
            namePlayer1.setText(LanguageManager.getText("player1"));
            namePlayer2.setText(LanguageManager.getText("player2"));
            godLabel.setText(LanguageManager.getText("godLabel"));
            godSubLabel1.setText(LanguageManager.getText("godSubLabel"));
            godSubLabel2.setText(LanguageManager.getText("godSubLabel"));
            addPlayerButton.setText(LanguageManager.getText("addPlayer"));
            workerAmountLabel.setText(LanguageManager.getText("workerAmount"));
            workerSubLabel.setText(LanguageManager.getText("workerSubLabel"));
            steinAnzahlLabel.setText(LanguageManager.getText("blockAmount"));
            stoneLabel1.setText(LanguageManager.getText("level1"));
            stoneLabel2.setText(LanguageManager.getText("level2"));
            stoneLabel3.setText(LanguageManager.getText("level3"));
            stoneLabel4.setText(LanguageManager.getText("dome"));
            globeLabel.setText(LanguageManager.getText("globeLabel"));
            startbutton.setText(LanguageManager.getText("startbutton"));
            menumenu.setText(LanguageManager.getText("menumenu"));
            menuload.setText(LanguageManager.getText("menuload"));
            menusettings.setText(LanguageManager.getText("menusettings"));
            menuToHome.setText(LanguageManager.getText("menuhome"));
            germanItem2.setText(LanguageManager.getText("menugerman"));
            englishItem2.setText(LanguageManager.getText("menuenglish"));
            koreanItem2.setText(LanguageManager.getText("menukorean"));
            themeItem2.setText(LanguageManager.getText("menutheme"));
            siegHoehe.setText(LanguageManager.getText("winheight"));
            legalMovesLabel.setText(LanguageManager.getText("legalmoves"));
            moveUpLabel.setText(LanguageManager.getText("up"));
            moveDownLabel.setText(LanguageManager.getText("down"));
            downChoiceBox.setValue(LanguageManager.getText("all"));
            fieldsize.setText(LanguageManager.getText("fieldsize"));

        }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        translateManager();

        settingPane.setPrefWidth(ScreenSize.getScreenWidth());
        settingPane.setPrefHeight(ScreenSize.getScreenHeight());

        themeImageGamesettings.fitWidthProperty().bind(settingPane.widthProperty());
        themeImageGamesettings.fitHeightProperty().bind(settingPane.heightProperty());

        menuToHome.setOnAction(event -> loadMainmenuScreen());

        //Add godlists from Player 1 and Player 2 to a list
        godlists.add(godlist1);
        godlists.add(godlist2);

        //Add godlists from Player 1 and Player 2 to a list
        godlists.add(godlist1);
        godlists.add(godlist2);

        //If the Checkbox is not selected, disable the Combobox with the gods
        godlist1.setDisable(!godsForceCheck.isSelected());
        godlist2.setDisable(!godsForceCheck.isSelected());

        /*
        Add a listener to the Checkbox. Old Value is unselected, new value is selected.
        If the checkbox is not selected, disable all ComboBoxes.
         */
        godsForceCheck.selectedProperty().addListener((observable, oldValue, newValue) -> {
            godlist1.setDisable(!newValue);
            godlist1.setValue(null);

            godlist2.setDisable(!newValue);
            godlist2.setValue(null);

            if (godlist3 != null) {
                godlist3.setDisable(!newValue);
                godlist3.setValue(null);
            }

        });


        //when user clicks the Comboboxes and chooses an item, call handleGodDoubles

        godlist1.setOnAction(event-> handleGodDoubles());
        godlist2.setOnAction(event-> handleGodDoubles());
        godlist1.setOnAction(event-> handleGodDoubles());
        godlist2.setOnAction(event-> handleGodDoubles());


        //make sure the user's input for the block amount textfields is >=1
        amountLevel1.setOnAction(event -> handleBlockAmountInput());
        amountLevel2.setOnAction(event -> handleBlockAmountInput());
        amountLevel3.setOnAction(event -> handleBlockAmountInput());
        if(amountLevel5 != null) {
            amountLevel5.setOnAction(event -> handleBlockAmountInput());
        }
        if(amountLevel6 != null) {
            amountLevel6.setOnAction(event -> handleBlockAmountInput());
        }
        amountDome.setOnAction(event -> handleBlockAmountInput());

        //make sure the users input for the player names is valid
        namePlayer1.setOnAction(event -> handleInvalidNames());
        namePlayer2.setOnAction(event -> handleInvalidNames());

        //make sure the users input for the player color is valid
        colourPlayer1.setOnAction(event -> handleSameColors());
        colourPlayer2.setOnAction(event -> handleSameColors());

        //adds player options for a third player
        addPlayerButton.setOnAction(event -> handleAddPlayer());

        //adapts block amount options to the winning height choice

        winningHeightChoice.setOnAction(event->handleWinningHeightChoice());

        winningHeightChoice.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            handleWinningHeightChoice();
        });

        //when the start button is clicked, check if all required entries are made by the user
        startbutton.setOnAction(event -> checkStartRequirements());

        ChangeTheme.loadTheme2(themeImageGamesettings, settingPane);

        isCompP1.selectedProperty().addListener((observable, oldValue, newValue) ->
                updateChoiceBoxStatus1());
        isCompP2.selectedProperty().addListener((observable, oldValue, newValue) ->
                updateChoiceBoxStatus1());

        germanItem2.setOnAction(event -> {
        LanguageManager.setLanguage("de");
        playerLabel.setText( LanguageManager.getText("playerLabel"));
        namePlayer1.setText( LanguageManager.getText("player1"));
        namePlayer2.setText( LanguageManager.getText("player2"));
//        namePlayer3.setText( LanguageManager.getText("player3"));
        godLabel.setText( LanguageManager.getText("godLabel"));
        godSubLabel1.setText( LanguageManager.getText("godSubLabel"));
        godSubLabel2.setText( LanguageManager.getText("godSubLabel"));
//        selectGodTextP3.setText( LanguageManager.getText("godSubLabel"));
        workerAmountLabel.setText( LanguageManager.getText("workerAmount"));
        workerSubLabel.setText( LanguageManager.getText("workerSubLabel"));
        steinAnzahlLabel.setText( LanguageManager.getText("blockAmount"));
        addPlayerButton.setText(LanguageManager.getText("addPlayer"));
        stoneLabel1.setText( LanguageManager.getText("level1"));
        stoneLabel2.setText( LanguageManager.getText("level2"));
        stoneLabel3.setText( LanguageManager.getText("level3"));
        stoneLabel4.setText( LanguageManager.getText("dome"));
        globeLabel.setText( LanguageManager.getText("globeLabel"));
        startbutton.setText( LanguageManager.getText("startbutton"));
        menumenu.setText(LanguageManager.getText("menumenu"));
        menuload.setText(LanguageManager.getText("menuload"));
        menusettings.setText(LanguageManager.getText("menusettings"));
        menuToHome.setText(LanguageManager.getText("menuhome"));
        germanItem2.setText(LanguageManager.getText("menugerman"));
        englishItem2.setText(LanguageManager.getText("menuenglish"));
        koreanItem2.setText(LanguageManager.getText("menukorean"));
        themeItem2.setText(LanguageManager.getText("menutheme"));
        siegHoehe.setText(LanguageManager.getText("winheight"));
        legalMovesLabel.setText(LanguageManager.getText("legalmoves"));
        moveUpLabel.setText(LanguageManager.getText("up"));
        moveDownLabel.setText(LanguageManager.getText("down"));
        downChoiceBox.setValue(LanguageManager.getText("all"));
        if (namePlayer3 != null) {
            namePlayer3.setText( LanguageManager.getText("player3"));
            selectGodTextPlayer3.setText( LanguageManager.getText("godSubLabel"));
        }
        if(stoneLabel5 != null) {
            stoneLabel5.setText(LanguageManager.getText("level4"));
        }
        if(stoneLabel6 != null) {
            stoneLabel6.setText(LanguageManager.getText("level5"));
        }
        fieldsize.setText(LanguageManager.getText("fieldsize"));

    });
        englishItem2.setOnAction(event -> {
        LanguageManager.setLanguage("en");
        playerLabel.setText("Player");
        namePlayer1.setText("Player 1");
        namePlayer2.setText("Player 2");
        //namePlayer2.setText("Player 3");
        godLabel.setText("God's Force");
        godSubLabel1.setText("select a god");
        godSubLabel2.setText("select a god");
        //selectGodTextP3.setText("select a god");
        workerAmountLabel.setText("Worker Amount");
        workerSubLabel.setText("per Player");
        steinAnzahlLabel.setText("Block Amount");
        addPlayerButton.setText("Add Player");
        stoneLabel1.setText("Level 1");
        stoneLabel2.setText("Level 2");
        stoneLabel3.setText("Level 3");
        stoneLabel4.setText("Dome");
        globeLabel.setText("World is a globe");
        startbutton.setText("Start game!");
        menumenu.setText("Menu");
        menuload.setText("Load");
        menusettings.setText("Settings");
        menuToHome.setText("Home");
        germanItem2.setText("German");
        englishItem2.setText("English");
        koreanItem2.setText("Korean");
        themeItem2.setText("Change Theme");
        siegHoehe.setText("Winning Height");
        legalMovesLabel.setText("Legal Moves");
        moveUpLabel.setText("up");
        moveDownLabel.setText("down");
        downChoiceBox.setValue(all);
        if (namePlayer3 != null) {
            namePlayer3.setText("Player 3");
            selectGodTextPlayer3.setText("select a god");
        }
            if(stoneLabel5 != null) {
                stoneLabel5.setText("Level 4");
            }
            if(stoneLabel6 != null) {
                stoneLabel6.setText("Level 5");
            }
        fieldsize.setText("Field Size");

        });
        koreanItem2.setOnAction(event -> {
        LanguageManager.setLanguage("kr");
        legalMovesLabel1.setText(LanguageManager.getText("legalMovesLabel1"));
        playerLabel.setText( LanguageManager.getText("playerLabel"));
        namePlayer1.setText( LanguageManager.getText("player1"));
        namePlayer2.setText( LanguageManager.getText("player2"));
        godLabel.setText( LanguageManager.getText("godLabel"));
        godSubLabel1.setText( LanguageManager.getText("godSubLabel"));
        godSubLabel2.setText( LanguageManager.getText("godSubLabel"));
        workerAmountLabel.setText( LanguageManager.getText("workerAmount"));
        workerSubLabel.setText( LanguageManager.getText("workerSubLabel"));
        steinAnzahlLabel.setText( LanguageManager.getText("blockAmount"));
        addPlayerButton.setText(LanguageManager.getText("addPlayer"));
        stoneLabel1.setText( LanguageManager.getText("level1"));
        stoneLabel2.setText( LanguageManager.getText("level2"));
        stoneLabel3.setText( LanguageManager.getText("level3"));
        stoneLabel4.setText( LanguageManager.getText("dome"));
        globeLabel.setText( LanguageManager.getText("globeLabel"));
        startbutton.setText( LanguageManager.getText("startbutton"));
        menumenu.setText(LanguageManager.getText("menumenu"));
        menuload.setText(LanguageManager.getText("menuload"));
        menusettings.setText(LanguageManager.getText("menusettings"));
        menuToHome.setText(LanguageManager.getText("menuhome"));
        germanItem2.setText(LanguageManager.getText("menugerman"));
        englishItem2.setText(LanguageManager.getText("menuenglish"));
        koreanItem2.setText(LanguageManager.getText("menukorean"));
        themeItem2.setText(LanguageManager.getText("menutheme"));
        siegHoehe.setText(LanguageManager.getText("winheight"));
        legalMovesLabel.setText(LanguageManager.getText("legalmoves"));
        moveUpLabel.setText(LanguageManager.getText("up"));
        moveDownLabel.setText(LanguageManager.getText("down"));
        downChoiceBox.setValue(LanguageManager.getText("all"));
        if (namePlayer3 != null) {
            namePlayer3.setText( LanguageManager.getText("player3"));
            selectGodTextPlayer3.setText( LanguageManager.getText("godSubLabel"));
        }
            if(stoneLabel5 != null) {
                stoneLabel5.setText(LanguageManager.getText("level4"));
            }
            if(stoneLabel6 != null) {
                stoneLabel6.setText(LanguageManager.getText("level5"));
            }
        fieldsize.setText(LanguageManager.getText("siegHoehe1"));
    });
        themeItem2.setOnAction(event -> {
        if (ChangeTheme.getTheme()==1) {
            ChangeTheme.setTheme(2);
            ChangeTheme.loadTheme2(themeImageGamesettings, settingPane);
        } else {
            ChangeTheme.setTheme(1);
            ChangeTheme.loadTheme2(themeImageGamesettings, settingPane);
        }
    });
    }

    /**
     If the user chooses 1 God, the other player should not be able to choose the same god.
     Therefore, the respective god is then disabled for the other player and the opacity is set down.
     */

    public void handleGodDoubles() {
        for (ComboBox<String> godlist : godlists) {
            godlist.setCellFactory(param -> new ListCell<String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    boolean disabled = false;

                    for (ComboBox<String> otherGodlist : godlists) {
                        if (otherGodlist != godlist) {
                            String selectedGod = otherGodlist.getSelectionModel().getSelectedItem();
                            if (selectedGod != null && selectedGod.equals(item)) {
                                disabled = true;
                                break;
                            }
                        }
                    }

                    if (disabled) {
                        setDisable(true);
                        setStyle("-fx-opacity: 0.5;");
                    } else {
                        setDisable(false);
                        setStyle("");
                    }

                    setText(item);
                }
            });
        }
    }


    /**
     * In case that Player 1 and Plaer 2 first choose gods and later add a third player, the gods which are already chosen by player 1 and player 2,
     * are disabled in the list of available gods for player 3.
     */

    private void updateGodlist3() {
        String selectedGod1 = godlist1.getSelectionModel().getSelectedItem();
        String selectedGod2 = godlist2.getSelectionModel().getSelectedItem();

        if (godlist3 != null) {
            godlist3.setCellFactory(param -> new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(item);
                        boolean disableItem = selectedGod1 != null && selectedGod1.equals(item)
                                || selectedGod2 != null && selectedGod2.equals(item);
                        if (disableItem) {
                            setDisable(true);
                            setTextFill(Color.GRAY);
                        } else {
                            setDisable(false);
                            setTextFill(Color.BLACK);
                        }
                    }
                }
            });
        }
    }



    /** Shows an alert, if the entered building block amounts are not numeric or less than 1
     */

    public void handleBlockAmountInput() {

        if(!numericInputCheck() || !positiveNumbersCheck()) {
            if (!numericInputCheck()) {
                Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                if(LanguageManager.getCurrentLanguage()== "en") {
                    alert.setTitle("Amount Building Blocks");
                    alert.setContentText("The building block amount must be numeric!");
                } else if(LanguageManager.getCurrentLanguage()== "de") {
                    alert.setTitle("Anzahl Bausteine");
                    alert.setContentText("Die Anzahl der Bausteine muss numerisch sein!");
                } else if (LanguageManager.getCurrentLanguage()== "kr") {
                    alert.setTitle("");
                    alert.setContentText("");
                }
                alert.show();
            } else if(!positiveNumbersCheck()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                if(LanguageManager.getCurrentLanguage()== "en") {
                    alert.setTitle("Amount Building Blocks");
                    alert.setContentText("There must be at least one building block of each type!");
                } else if(LanguageManager.getCurrentLanguage()== "de") {
                    alert.setTitle("Anzahl Bausteine");
                    alert.setContentText("Es muss mindestens einen Baustein pro Level geben!");
                } else if (LanguageManager.getCurrentLanguage()== "kr") {
                    alert.setTitle(" ");
                    alert.setContentText(" ");
                }
                alert.show();
            }
        }
    }

    /**
     * Shows alert, if the user's entries for player names are invalid
     */

    public void handleInvalidNames() {
        if (!inputValidNamesCheck()) {
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            if(LanguageManager.getCurrentLanguage()== "en") {
                alert.setTitle("Player name");
                alert.setContentText("One or more player names are not valid. Please make sure the name field is not empty, the names differ & you're not using more than 16 characters!");
            } else if(LanguageManager.getCurrentLanguage()== "de") {
                alert.setTitle("Spielername");
                alert.setContentText("Ein oder mehrere Spielernamen sind nicht gültig. Bitte stelle sicher, dass das Namensfeld nicht leer ist, die Namen sich unterscheiden und die Namen nicht aus mehr als 16 Zeichen bestehen!");
            } else if(LanguageManager.getCurrentLanguage()== "kr") {
                alert.setTitle(" ");
                alert.setContentText(" ");
            }
            alert.show();
        }

    }

    /**
     * Shows alert, if the chosen colors are not valid (if a color is chosen twice or more)
     */

    public void handleSameColors() {
        if (!inputColorsCheck()) {
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            if(LanguageManager.getCurrentLanguage()== "en") {
                alert.setTitle("Colour");
                alert.setContentText("Please adjust the colours of the players - the colours have to differ!");
            } else if(LanguageManager.getCurrentLanguage()== "de") {
                alert.setTitle("Farbe");
                alert.setContentText("Die Farben der verschiedenen Spieler:innen müssen sich unterscheiden!");
            } else if(LanguageManager.getCurrentLanguage()== "kr") {
                alert.setTitle(" ");
                alert.setContentText(" ");
            }
            alert.show();
        }
    }


    /** Makes sure that all required entries are made for the game and if they are valid.
     * If yes, start the game. Else show alert with information that there are invalid entries.
     */
    public void checkStartRequirements() {

        if (inputValidBlockAmountCheck() && inputValidNamesCheck() && inputColorsCheck() && inputValidGodChoiceCheck()) {

            loadGameboardScreen();
            tmp.checkAi_FirstTurn(tmp.getPlayerInTurn(), Integer.parseInt((String) aiLevel.getValue()));

        } else {
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            if(LanguageManager.getCurrentLanguage()== "en") {
                alert.setTitle("Unvalid entries");
                alert.setContentText("Please check your entries! There must be at least 1 block for each level, differing player names with 16 or less characters and if God's Force is selected, every player has to choose a different god!");
            } else if (LanguageManager.getCurrentLanguage()== "de") {
                alert.setTitle("Ungültige Eingaben");
                alert.setContentText("Bitte überprüfe deine Eingaben! Für jedes Level muss es mind. einen Baustein geben, die Namen der Spieler:innen müssen sich unterscheiden und aus max. 16 Zeichen bestehen. Falls Gotteskraft gewählt ist, müssen die Spieler:innen verschiedene Götter wählen!");
            } else if (LanguageManager.getCurrentLanguage()== "de") {
                alert.setTitle(" ");
                alert.setContentText(" ");
            }
            alert.show();
        }
    }


    /**
     * Checks, if the entries for the block amounts are numeric.
     * @return true, if the input is numeric, false if not (letters or empty)
     */

    public boolean numericInputCheck() {

        try {
            String inputLevel1 = amountLevel1.getText();
            String inputLevel2 = amountLevel2.getText();
            String inputDome = amountDome.getText();

            Integer.parseInt(inputLevel1);
            Integer.parseInt(inputLevel2);
            Integer.parseInt(inputDome);

            if(amountLevel3 != null) {
                String inputLevel3 = amountLevel3.getText();
                Integer.parseInt(inputLevel3);
            }


            if(amountLevel5 != null) {
                String inputLevel4 = amountLevel5.getText();
                Integer.parseInt(inputLevel4);
            }

            if(amountLevel6 != null) {
                String inputLevel5 = amountLevel6.getText();
                Integer.parseInt(inputLevel5);
            }


            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if the entries for the block amounts are > 0
     * @return true, if each entry (per level) is positive, else false
     */

    public boolean positiveNumbersCheck() {

        String inputLevel1 = amountLevel1.getText();
        String inputLevel2 = amountLevel2.getText();
        String inputDome = amountDome.getText();

        int inputLevel1Numeric = Integer.parseInt(inputLevel1);
        int inputLevel2Numeric = Integer.parseInt(inputLevel2);
        int inputDomeNumeric = Integer.parseInt(inputDome);

        if (amountLevel5 == null && amountLevel6 == null) {
            if(amountLevel3 != null) {
                String inputLevel3 = amountLevel3.getText();
                int inputLevel3Numeric = Integer.parseInt(inputLevel3);
                if (inputLevel1Numeric > 0 && inputLevel2Numeric > 0 && inputLevel3Numeric > 0 && inputDomeNumeric > 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (amountLevel3 == null) {
                if (inputLevel1Numeric > 0 && inputLevel2Numeric > 0 && inputDomeNumeric > 0) {
                    return true;
                } else {
                    return false;
                }
            }

        } else if (amountLevel5 != null && amountLevel6 == null) {
            String inputLevel3 = amountLevel3.getText();
            String inputLevel4 = amountLevel5.getText();
            int inputLevel3Numeric = Integer.parseInt(inputLevel3);
            int inputLevel4Numeric = Integer.parseInt(inputLevel4);
            if (inputLevel1Numeric > 0 && inputLevel2Numeric > 0 && inputLevel3Numeric > 0 && inputDomeNumeric > 0 && inputLevel4Numeric > 0) {
                return true;
            } else {
                return false;
            }

        } else if (amountLevel5 != null && amountLevel6 != null) {
            String inputLevel4 = amountLevel5.getText();
            String inputLevel5 = amountLevel6.getText();
            String inputLevel3 = amountLevel3.getText();
            int inputLevel4Numeric = Integer.parseInt(inputLevel4);
            int inputLevel5Numeric = Integer.parseInt(inputLevel5);
            int inputLevel3Numeric = Integer.parseInt(inputLevel3);

            if (inputLevel1Numeric > 0 && inputLevel2Numeric > 0 && inputLevel3Numeric > 0 && inputDomeNumeric > 0 && inputLevel4Numeric > 0 && inputLevel5Numeric > 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }



    /**
     * Checks if zhe entries for the block amounts are valid.
     * @return true, if the input is numeric and > 0, else false
     */

    public boolean inputValidBlockAmountCheck() {
        if(numericInputCheck() && positiveNumbersCheck()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the entries for the player names are valid.
     * @return true, if the player name fields are not empty, the names contain of 16 or less characters and the names differ, else false
     */
    public boolean inputValidNamesCheck() {

        String nameP1 = namePlayer1.getText();
        String nameP2 = namePlayer2.getText();

        if (namePlayer3 != null) {
            if ((nameP1.length() > 0 && nameP1.length() <= 16) && (nameP2.length() > 0 && nameP2.length() <= 16) && (namePlayer3.getText().length() > 0 && namePlayer3.getText().length() <= 16) && !nameP2.equals(nameP1) && !nameP1.equals(namePlayer3.getText()) && !nameP2.equals(namePlayer3.getText())) {
                return true;
            } else {
                return false;
            }
        } else {
            if ((nameP1.length() > 0 && nameP1.length() <= 16) && (nameP2.length() > 0 && nameP2.length() <= 16) && !nameP2.equals(nameP1)) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Checks if the entries for Gods Force are valid.
     * @return true, if God's Force is selected and one God per Player is chosen OR if God's Force is not selected - else false.
     */

    public boolean inputValidGodChoiceCheck() {
        if(godlist3 != null) {
            if (((godsForceCheck.isSelected() && (!((godlist1.getValue() == null) || (godlist2.getValue() == null) || (godlist3.getValue() == null)))) &&
                    (!(godlist1.getValue().equals(godlist2.getValue()) || godlist1.getValue().equals(godlist3.getValue()) ||
                            godlist2.getValue().equals(godlist3.getValue())))) || (!godsForceCheck.isSelected())) {
                return true;
            } else {
                return false;
            }
        } else {
            if ((godsForceCheck.isSelected() && (!((godlist1.getValue() == null) || (godlist2.getValue() == null))) &&
                    (!(godlist1.getValue().equals(godlist2.getValue())))) || (!godsForceCheck.isSelected())) {
                return true;
            } else {
                return false;
            }
        }

    }

    /**
     * Checks, if the chosen colors of the players differ
     * @return true, if the colors differ; false, if any players have chosen the same colors
     */

    public boolean inputColorsCheck() {
        if (namePlayer3 != null) {
            if(!((colourPlayer3.getValue().equals(colourPlayer1.getValue())) || (colourPlayer2.getValue().equals(colourPlayer1.getValue())) || (colourPlayer2.getValue().equals(colourPlayer3.getValue())))) {
                return true;
            } else {
                return false;
            }
        } else {
            if (!((colourPlayer1.getValue().equals(colourPlayer2.getValue())))) {
                return true;
            } else {
                return false;
            }
        }

    }


    /** When a user clicks on "Add Player", a third section of player settings appears
     */
    public void handleAddPlayer() {

        LanguageManager.setThirdplayer(true);
        // deletes the "Add Player" Button
        settingPane.getChildren().remove(addPlayerButton);

        // creates new option elements for the third player

        HBox hboxP3 = new HBox();
        TextField nameP3 = new TextField();
        if(LanguageManager.getCurrentLanguage() == "en") {
            nameP3.setText("Player 3");
        } else if(LanguageManager.getCurrentLanguage() == "de") {
            nameP3.setText(LanguageManager.getText("player3"));
        } else if(LanguageManager.getCurrentLanguage() == "kr") {
            nameP3.setText(LanguageManager.getText("player3"));
        }

        ColorPicker colourP3 = new ColorPicker();
        hboxP3.getChildren().addAll(nameP3, colourP3);
        CheckBox computerCheckP3 = new CheckBox();
        Text computerTextP3 = new Text("Computer");
        ComboBox<String> comboBoxGodP3 = new ComboBox<>();
        comboBoxGodP3.getItems().addAll("Apollo", "Artemis", "Athena", "Atlas", "Demeter", "Hephaestus", "Hermes", "Minotaur", "Pan", "Prometheus");

        Text selectGodTextP3 = new Text();
        if(LanguageManager.getCurrentLanguage() == "en") {
            selectGodTextP3.setText("select a god");
        } else if(LanguageManager.getCurrentLanguage() == "de") {
            selectGodTextP3.setText("wähle einen Gott aus");
        } else if(LanguageManager.getCurrentLanguage() == "kr") {
            selectGodTextP3.setText("신을 고르세요");
        }

        Button deleteP3X = new Button("x");
        Font newFont = new Font("System", 13);
        settingPane.getChildren().addAll(hboxP3, computerCheckP3, computerTextP3, comboBoxGodP3, selectGodTextP3, deleteP3X);


        // sets the default layout attributes for the new Player 3 - elements

        // Player name and player color
        hboxP3.setLayoutX(66);
        hboxP3.setLayoutY(217);
        nameP3.setPrefWidth(74);
        colourP3.setPrefWidth(55);
        colourP3.setStyle("-fx-color-label-visible: false;");
        hboxP3.spacingProperty().setValue(9);
        colourP3.setValue(Color.GREEN);

        // computer option
        computerCheckP3.setLayoutX(66);
        computerCheckP3.setLayoutY(246);

        computerTextP3.setLayoutX(86);
        computerTextP3.setLayoutY(260);
        computerTextP3.setStrokeType(StrokeType.OUTSIDE);
        computerTextP3.setStroke(Color.WHITE);
        computerTextP3.setFont(newFont);

        // God's Force option
        comboBoxGodP3.setLayoutX(260);
        comboBoxGodP3.setLayoutY(217);
        comboBoxGodP3.setPrefWidth(116);
        comboBoxGodP3.setVisibleRowCount(3);

        selectGodTextP3.setLayoutX(261);
        selectGodTextP3.setLayoutY(256);
        selectGodTextP3.setStrokeType(StrokeType.OUTSIDE);
        selectGodTextP3.setStroke(Color.WHITE);
        selectGodTextP3.setFont(newFont);

        // Delete Player 3 option
        deleteP3X.setLayoutX(35);
        deleteP3X.setLayoutY(210);
        deleteP3X.setPrefHeight(25);
        deleteP3X.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: black; -fx-effect: dropshadow(gaussian, white, 1,1,0,0); -fx-font-size: 16px;");


        // set the Player 3 attributes to the given attributes
        hboxPlayer3 = hboxP3;
        namePlayer3 = nameP3;
        colourPlayer3 = colourP3;
        godlist3 = comboBoxGodP3;
        godlist3.setLayoutX(godlist2.getLayoutX());
        //godlist3 is added to the list of godlists to compare the chosen gods for equality
        godlists.add(godlist3);
        //when player1 and player2 have already chosen a god, these gods are initially disabled in the godlist for player 3
        updateGodlist3();
        deleteThirdPlayerX = deleteP3X;
        isCompP3 = computerCheckP3;
        isCompP3.selectedProperty().addListener((observable, oldValue, newValue) ->
                updateChoiceBoxStatus());
        computerTextPlayer3 = computerTextP3;
        selectGodTextPlayer3 = selectGodTextP3;
        selectGodTextPlayer3.setLayoutX(godlist3.getLayoutX());

        handleThirdPlayerOptions();

    }

    /**
     * Actions and validations that are performed, when interacting with the 3rd player options
     */

    public void handleThirdPlayerOptions() {

        namePlayer3.setOnAction(event -> handleInvalidNames());
        colourPlayer3.setOnAction(event -> handleSameColors());
        if (!godsForceCheck.isSelected()) {
            godlist3.setDisable(true);
        } else {
            godlist3.setDisable(false);
        }
        godlist1.setOnAction(event -> handleGodDoubles());
        godlist2.setOnAction(event -> handleGodDoubles());
        godlist3.setOnAction(event -> handleGodDoubles());

        deleteThirdPlayerX.setOnAction(event -> handleClickOnX());
    }



    /**
     * When the user clicks the deletion-x in the 3rd player part, all 3rd player options disappear and the "Add Player" Button is added again
     */
    public void handleClickOnX() {
        LanguageManager.setThirdplayer(false);
        settingPane.getChildren().remove(deleteThirdPlayerX);
        settingPane.getChildren().remove(hboxPlayer3);
        settingPane.getChildren().remove(godlist3);
        godlist3 = null;
        hboxPlayer3 = null;
        namePlayer3 = null;
        settingPane.getChildren().remove(isCompP3);
        settingPane.getChildren().remove(computerTextPlayer3);
        settingPane.getChildren().remove(selectGodTextPlayer3);
        settingPane.getChildren().add(addPlayerButton);

    }


    /**
     * Depending on the winning height choice, the block amount fields in the gamesettings are adapted to that choice
     */
    public void handleWinningHeightChoice() {
        String selectedHeight = (String) winningHeightChoice.getValue();
        Font newFont = new Font("System", 13);
        switch(selectedHeight) {
            case "2":
                blockAmountLabels.getChildren().remove(stoneLabel3);
                blockAmountFields.getChildren().remove(amountLevel3);
                amountLevel3 = null;

                blockAmountLabels.getChildren().remove(stoneLabel5);
                blockAmountFields.getChildren().remove(amountLevel5);
                amountLevel5 = null;

                blockAmountLabels.getChildren().remove(stoneLabel6);
                blockAmountFields.getChildren().remove(amountLevel6);
                amountLevel6 = null;

                amountDome.setPrefHeight(16);



                break;
            case "3":
                if(blockAmountLabels.getChildren().contains(stoneLabel5)) {
                    blockAmountLabels.getChildren().remove(stoneLabel5);
                    blockAmountFields.getChildren().remove(amountLevel5);
                    amountLevel5 = null;
                }

                if(blockAmountLabels.getChildren().contains(stoneLabel6)) {
                    blockAmountLabels.getChildren().remove(stoneLabel6);
                    blockAmountFields.getChildren().remove(amountLevel6);
                    amountLevel6 = null;
                }

                if (!blockAmountLabels.getChildren().contains(stoneLabel3)) {
                    Text stoneL3 = new Text();
                    if(LanguageManager.getCurrentLanguage() == "en") {
                        stoneL3.setText("Level 3");
                    } else if (LanguageManager.getCurrentLanguage() == "de") {
                        stoneL3.setText("Stufe 3");
                    } else if (LanguageManager.getCurrentLanguage() == "kr") {
                        stoneL3.setText("WIP Stufe 3");
                    }
                    stoneLabel3 = stoneL3;
                    stoneLabel3.setStrokeType(StrokeType.OUTSIDE);
                    stoneLabel3.setStroke(Color.WHITE);
                    stoneLabel3.setFont(newFont);
                    blockAmountLabels.getChildren().add(2,stoneLabel3);

                    TextField amountL3 = new TextField("14");
                    amountLevel3 = amountL3;
                    amountLevel3.setAlignment(Pos.CENTER);
                    blockAmountFields.getChildren().add(2, amountLevel3);


                }
                break;

            case "4":

                if (!blockAmountLabels.getChildren().contains(stoneLabel3)) {
                    Text stoneL3 = new Text();
                    if(LanguageManager.getCurrentLanguage() == "en") {
                        stoneL3.setText("Level 3");
                    } else if (LanguageManager.getCurrentLanguage() == "de") {
                        stoneL3.setText("Stufe 3");
                    } else if (LanguageManager.getCurrentLanguage() == "kr") {
                        stoneL3.setText("WIP Stufe 3");
                    }
                    stoneLabel3 = stoneL3;
                    stoneLabel3.setStrokeType(StrokeType.OUTSIDE);
                    stoneLabel3.setStroke(Color.WHITE);
                    stoneLabel3.setFont(newFont);
                    blockAmountLabels.getChildren().add(2,stoneLabel3);

                    TextField amountL3 = new TextField("14");
                    amountLevel3 = amountL3;
                    amountLevel3.setAlignment(Pos.CENTER);
                    blockAmountFields.getChildren().add(2, amountLevel3);

                }

                if (!blockAmountLabels.getChildren().contains(stoneLabel5)) {
                    Text stoneL5 = new Text();
                    if(LanguageManager.getCurrentLanguage() == "en") {
                        stoneL5.setText("Level 4");
                    } else if (LanguageManager.getCurrentLanguage() == "de") {
                        stoneL5.setText("Stufe 4");
                    } else if (LanguageManager.getCurrentLanguage() == "kr") {
                        stoneL5.setText("WIP Stufe 4");
                    }
                    stoneLabel5 = stoneL5;
                    stoneLabel5.setStrokeType(StrokeType.OUTSIDE);
                    stoneLabel5.setStroke(Color.WHITE);
                    stoneLabel5.setFont(newFont);
                    blockAmountLabels.getChildren().add(3, stoneLabel5);

                    TextField amountL5 = new TextField("14");
                    amountLevel5 = amountL5;
                    amountLevel5.setAlignment(Pos.CENTER);
                    blockAmountFields.getChildren().add(3, amountLevel5);
                }

                if(blockAmountLabels.getChildren().contains(stoneLabel6)) {
                    blockAmountLabels.getChildren().remove(stoneLabel6);
                    blockAmountFields.getChildren().remove(amountLevel6);
                    amountLevel6 = null;
                }


                break;

            case "5":

                if (!blockAmountLabels.getChildren().contains(stoneLabel3)) {
                    Text stoneL3 = new Text();
                    if(LanguageManager.getCurrentLanguage() == "en") {
                        stoneL3.setText("Level 3");
                    } else if (LanguageManager.getCurrentLanguage() == "de") {
                        stoneL3.setText("Stufe 3");
                    } else if (LanguageManager.getCurrentLanguage() == "kr") {
                        stoneL3.setText("WIP Stufe 3");
                    }
                    stoneLabel3 = stoneL3;
                    stoneLabel3.setStrokeType(StrokeType.OUTSIDE);
                    stoneLabel3.setStroke(Color.WHITE);
                    stoneLabel3.setFont(newFont);
                    blockAmountLabels.getChildren().add(2,stoneLabel3);

                    TextField amountL3 = new TextField("14");
                    amountLevel3 = amountL3;
                    amountLevel3.setAlignment(Pos.CENTER);
                    blockAmountFields.getChildren().add(2, amountLevel3);
                }

                if (!blockAmountLabels.getChildren().contains(stoneLabel5)) {
                    Text stoneL5 = new Text();
                    if(LanguageManager.getCurrentLanguage() == "en") {
                        stoneL5.setText("Level 4");
                    } else if (LanguageManager.getCurrentLanguage() == "de") {
                        stoneL5.setText("Stufe 4");
                    } else if (LanguageManager.getCurrentLanguage() == "kr") {
                        stoneL5.setText("WIP Stufe 4");
                    }
                    stoneLabel5 = stoneL5;
                    stoneLabel5.setStrokeType(StrokeType.OUTSIDE);
                    stoneLabel5.setStroke(Color.WHITE);
                    stoneLabel5.setFont(newFont);
                    blockAmountLabels.getChildren().add(3, stoneLabel5);

                    TextField amountL5 = new TextField("14");
                    amountLevel5 = amountL5;
                    amountLevel5.setAlignment(Pos.CENTER);
                    blockAmountFields.getChildren().add(3, amountLevel5);
                }

                if (!blockAmountLabels.getChildren().contains(stoneLabel6)) {
                    Text stoneL6 = new Text();
                    if(LanguageManager.getCurrentLanguage() == "en") {
                        stoneL6.setText("Level 5");
                    } else if (LanguageManager.getCurrentLanguage() == "de") {
                        stoneL6.setText("Stufe 5");
                    } else if (LanguageManager.getCurrentLanguage() == "kr") {
                        stoneL6.setText("WIP Stufe 5");
                    }
                    stoneLabel6 = stoneL6;
                    stoneLabel6.setStrokeType(StrokeType.OUTSIDE);
                    stoneLabel6.setStroke(Color.WHITE);
                    stoneLabel6.setFont(newFont);
                    blockAmountLabels.getChildren().add(4, stoneLabel6);

                    TextField amountL6 = new TextField("14");
                    amountLevel6 = amountL6;
                    amountLevel6.setAlignment(Pos.CENTER);
                    blockAmountFields.getChildren().add(4, amountLevel6);
                }

                break;

        }
    }



    /**
     * Gets the amount of workers the user selects in the gamesettings
     * @return the amount of workers per player on the gamefield
     */

    public int getWorkerAmount() {
        if (workerToggle.getSelectedToggle() == oneWorker) {
            player1.setInitialMove(1);
            player2.setInitialMove(1);
            if(namePlayer3 !=null){
                player3.setInitialMove(1);
            }
            return 1;
        } else if (workerToggle.getSelectedToggle() == threeWorkers) {
            player1.setInitialMove(3);
            player2.setInitialMove(3);
            if(namePlayer3 !=null){
                player3.setInitialMove(3);
            }
            return 3;
        } else {
            return 2;
        }
    }

    /**
     * gets th winning height, the user chooses in the gamesettings
     * @return selected height to win by the user
     */
    public String getWinningHeight() {
        String selectedHeight = (String) winningHeightChoice.getValue();
        System.out.println(selectedHeight);
        return selectedHeight;
    }


    /**
     * gets the chosen heights for legal moves up and legal moves down and saves the choice in an ID
     * @return legalMovesVersionId to determine the variant of legal moves chosen by the user
     */

    private int saveLegalMovesVersionId() {

        int legalMovesVersionId =0;
        String legalMovesUp  = (String) upChoiceBox.getValue();
        String legalMovesDown = (String) downChoiceBox.getValue();

        if (legalMovesUp.equals("1") && legalMovesDown.equals("all")) {
            legalMovesVersionId = 1;

        } else if(legalMovesUp.equals("all") && legalMovesDown.equals("1")) {
            legalMovesVersionId = 2;

        } else if(legalMovesUp.equals("all") && legalMovesDown.equals("all")) {
            legalMovesVersionId = 3;

        } else if(legalMovesUp.equals("1") && legalMovesDown.equals("1")) {
            legalMovesVersionId = 4;

        }
        return legalMovesVersionId;
    }



    /**
     * Gets the god which Player 1 chose and assigns it to the player 1 object
     * @param chosenGodP1 the god, that the player 1 chose in the gamesettings
     */

    public void setGodP1(String chosenGodP1) {
        God godP1 = null;

        switch(chosenGodP1) {
            case "Apollo":
                godP1 = new Apollo();
                player1.assignGod(godP1);
                player1.setAssignedGod(chosenGodP1);
                player1.setHasGod(true);
                break;
            case "Artemis":
                godP1 = new Artemis();
                player1.assignGod(godP1);
                player1.setAssignedGod(chosenGodP1);
                player1.setHasGod(true);
                break;
            case "Athena":
                godP1 = new Athena();
                player1.assignGod(godP1);
                player1.setAssignedGod(chosenGodP1);
                player1.setHasGod(true);
                break;
            case "Atlas":
                godP1 = new Atlas();
                player1.assignGod(godP1);
                player1.setAssignedGod(chosenGodP1);
                player1.setHasGod(true);
                break;
            case "Demeter":
                godP1 = new Demeter();
                player1.assignGod(godP1);
                player1.setAssignedGod(chosenGodP1);
                player1.setHasGod(true);
                break;
            case "Hephaestus":
                godP1 = new Hephaestus();
                player1.assignGod(godP1);
                player1.setAssignedGod(chosenGodP1);
                player1.setHasGod(true);
                break;
            case "Hermes":
                godP1 = new Hermes();
                player1.assignGod(godP1);
                player1.setAssignedGod(chosenGodP1);
                player1.setHasGod(true);
                break;
            case "Minotaur":
                godP1 = new Minotaur();
                player1.assignGod(godP1);
                player1.setAssignedGod(chosenGodP1);
                player1.setHasGod(true);
                break;
            case "Pan":
                godP1 = new Pan();
                player1.assignGod(godP1);
                player1.setAssignedGod(chosenGodP1);
                player1.setHasGod(true);
                break;
            case "Prometheus":
                godP1 = new Prometheus();
                player1.assignGod(godP1);
                player1.setAssignedGod(chosenGodP1);
                player1.setHasGod(true);
                break;
            default:
                System.out.println("Player 1 hat keinen Gott gewählt!");

        }

    }

    /**
     * Gets the god which Player 2 chose and assigns it to the player 2 object
     * @param chosenGodP2 the god, that the player 2 chose in the gamesettings
     */
    public void setGodP2(String chosenGodP2) {
        God godP2 = null;
        switch(chosenGodP2) {
            case "Apollo":
                godP2 = new Apollo();
                player2.assignGod(godP2);
                player2.setAssignedGod(chosenGodP2);
                player2.setHasGod(true);
                break;
            case "Artemis":
                godP2 = new Artemis();
                player2.assignGod(godP2);
                player2.setAssignedGod(chosenGodP2);
                player2.setHasGod(true);
                break;
            case "Athena":
                godP2 = new Athena();
                player2.assignGod(godP2);
                player2.setAssignedGod(chosenGodP2);
                player2.setHasGod(true);
                break;
            case "Atlas":
                godP2 = new Atlas();
                player2.assignGod(godP2);
                player2.setAssignedGod(chosenGodP2);
                player2.setHasGod(true);
                break;
            case "Demeter":
                godP2 = new Demeter();
                player2.assignGod(godP2);
                player2.setAssignedGod(chosenGodP2);
                player2.setHasGod(true);
                break;
            case "Hephaestus":
                godP2 = new Hephaestus();
                player2.assignGod(godP2);
                player2.setAssignedGod(chosenGodP2);
                player2.setHasGod(true);
                break;
            case "Hermes":
                godP2 = new Hermes();
                player2.assignGod(godP2);
                player2.setAssignedGod(chosenGodP2);
                player2.setHasGod(true);
                break;
            case "Minotaur":
                godP2 = new Minotaur();
                player2.assignGod(godP2);
                player2.setAssignedGod(chosenGodP2);
                player2.setHasGod(true);
                break;
            case "Pan":
                godP2 = new Pan();
                player2.assignGod(godP2);
                player2.setAssignedGod(chosenGodP2);
                player2.setHasGod(true);
                break;
            case "Prometheus":
                godP2 = new Prometheus();
                player2.assignGod(godP2);
                player2.setAssignedGod(chosenGodP2);
                player2.setHasGod(true);
                break;
            default:
                System.out.println("Player 2 hat keinen Gott gewählt!");
        }

    }

    /**
     * Gets the god which Player 3 chose and assigns it to the player 3 object
     * @param chosenGodP3 the god, that the player 3 chose in the gamesettings
     */
    public void setGodP3(String chosenGodP3) {
        God godP3 = null;
        switch(chosenGodP3) {
            case "Apollo":
                godP3 = new Apollo();
                player3.assignGod(godP3);
                player3.setAssignedGod(chosenGodP3);
                player3.setHasGod(true);
                break;
            case "Artemis":
                godP3 = new Artemis();
                player3.assignGod(godP3);
                player3.setAssignedGod(chosenGodP3);
                player3.setHasGod(true);
                break;
            case "Athena":
                godP3 = new Athena();
                player3.assignGod(godP3);
                player3.setAssignedGod(chosenGodP3);
                player3.setHasGod(true);
                break;
            case "Atlas":
                godP3 = new Atlas();
                player3.assignGod(godP3);
                player3.setAssignedGod(chosenGodP3);
                player3.setHasGod(true);
                break;
            case "Demeter":
                godP3 = new Demeter();
                player3.assignGod(godP3);
                player3.setAssignedGod(chosenGodP3);
                player3.setHasGod(true);
                break;
            case "Hephaestus":
                godP3 = new Hephaestus();
                player3.assignGod(godP3);
                player3.setAssignedGod(chosenGodP3);
                player3.setHasGod(true);
                break;
            case "Hermes":
                godP3 = new Hermes();
                player3.assignGod(godP3);
                player3.setAssignedGod(chosenGodP3);
                player3.setHasGod(true);
                break;
            case "Minotaur":
                godP3 = new Minotaur();
                player3.assignGod(godP3);
                player3.setAssignedGod(chosenGodP3);
                player3.setHasGod(true);
                break;
            case "Pan":
                godP3 = new Pan();
                player3.assignGod(godP3);
                player3.setAssignedGod(chosenGodP3);
                player3.setHasGod(true);
                break;
            case "Prometheus":
                godP3 = new Prometheus();
                player3.assignGod(godP3);
                player3.setAssignedGod(chosenGodP3);
                player3.setHasGod(true);
                break;
            default:
                System.out.println("Player 3 hat keinen Gott gewählt!");
        }
    }



    /**
     *
     * To transfer player data from the game board
     * @param player1
     * @param player2
     */
    public void setPlayers(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;

        namePlayer1.setText(player1.getName());
        namePlayer2.setText(player2.getName());
        colourPlayer1.setValue(player1.getColor());
        colourPlayer2.setValue(player2.getColor());
    }

    /**
     * show Mainmenu
     */
    public void loadMainmenuScreen(){
        try {
            URL url = getClass().getResource("/fxml/Startmenu.fxml");
            Parent root = FXMLLoader.load(url);

            Scene startScene = new Scene(root);

            Stage currentStage = (Stage) godLabel.getScene().getWindow();

            currentStage.setScene(startScene);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load the game board window with the set data
     *
     * @return
     */
    public void loadGameboardScreen(){

        //if(namePlayer3 != null) {
        if(godlist3 != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameBoard_P3.fxml"));
                Parent gameboardScreen = (Parent) loader.load();
                GameboardController gameboard = loader.getController();

                boolean b = random.nextBoolean();

                player1 = new Player(namePlayer1.getText(), colourPlayer1.getValue(), isCompP1.isSelected());
                player2 = new Player(namePlayer2.getText(), colourPlayer2.getValue(), isCompP2.isSelected());
                player3 = new Player(namePlayer3.getText(), colourPlayer3.getValue(), isCompP3.isSelected());

                player1.setId("player1");
                player2.setId("player2");
                player3.setId("player3");

                player1.setHasTurn(b);

                if(player1.isHasTurn()) {
                    player2.setHasTurn(!b);
                    player3.setHasTurn(!b);
                } else {
                    boolean b1 = random.nextBoolean();
                    player2.setHasTurn(b1);
                    player3.setHasTurn(!b1);
                }

                if(getWorkerAmount()==1){
                    gameboard.set1Worker(true);
                }else if(getWorkerAmount()==3){
                    gameboard.set3Worker(true);
                }

                Stage stage = (Stage) settingPane.getScene().getWindow();
                gameboard.buildField(getFieldSize());
                gameboard.setPlayersAndWorker(player1, player2, player3);
                gameboard.paintWorkerAndPlayer();

                if (godsForceCheck.isSelected()) {
                    setGodP1(godlist2.getSelectionModel().getSelectedItem());
                    player1.getGod().godSkill(gameboard);
                    gameboard.showGodP1(godlist2.getSelectionModel().getSelectedItem(), colourPlayer1.getValue());
                    setGodP2(godlist1.getSelectionModel().getSelectedItem());
                    player2.getGod().godSkill(gameboard);
                    gameboard.showGodP2(godlist1.getSelectionModel().getSelectedItem(), colourPlayer2.getValue(), getFieldSize());
                if (godlist3 != null) {
                    setGodP3(godlist3.getSelectionModel().getSelectedItem());
                    player3.getGod().godSkill(gameboard);
                    gameboard.showGodP3(godlist3.getSelectionModel().getSelectedItem(), colourPlayer3.getValue());
                }
            }


            gameboard.setStonesFromWinningHeight(getWinningHeight());

                if(globe.isSelected()){
                    gameboard.setGlobe(true);
                }else{
                    gameboard.setGlobe(false);
                }

            /**
             * stone settings will be applied in Gameboard
             */

            if(amountLevel3 != null && amountLevel5 == null && amountLevel6 == null) {
                gameboard.setStoneSetting(amountLevel1.getText(), amountLevel2.getText(),
                        amountLevel3.getText(), amountDome.getText()
                );
            } else if(amountLevel3 != null && amountLevel5 != null && amountLevel6 == null) {
                gameboard.setStoneSettingWinningHeight4(amountLevel1.getText(), amountLevel2.getText(),
                        amountLevel3.getText(), amountLevel5.getText(), amountDome.getText()
                );
            } else if(amountLevel3 != null && amountLevel5 != null && amountLevel6 != null) {
                gameboard.setStoneSettingWinningHeight5(amountLevel1.getText(), amountLevel2.getText(),
                        amountLevel3.getText(), amountLevel5.getText(), amountLevel6.getText(), amountDome.getText()
                );
            } else if(amountLevel3 == null) {
                gameboard.setStoneSettingWinningHeight2(amountLevel1.getText(), amountLevel2.getText(), amountDome.getText()
                );
            }

                gameboard.manageSeperateStonePools(seperateStonePoolCheck.isSelected());

                gameboard.setLegalMovesId(saveLegalMovesVersionId());

                if(getFieldSize() ==8) {
                    Scene gbScreen = new Scene(gameboardScreen, 1000, 700);
                    stage.setScene(gbScreen);
                } else if(getFieldSize() != 8 && getSeperateStonePoolCheck().isSelected()) {
                    Scene gbScreen = new Scene(gameboardScreen, 700, 600);
                    stage.setScene(gbScreen);
                }else{
                    settingPane.getChildren().setAll(gameboardScreen);
                }

                tmp = gameboard;
            } catch (IOException e) {
                System.out.println(e);
            }



        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameBoard.fxml"));
                Parent gameboardScreen = (Parent) loader.load();
                GameboardController gameboard = loader.getController();

                Stage stage = (Stage) settingPane.getScene().getWindow();

                gameboard.set3Player(false);

                boolean b = random.nextBoolean();

                player1 = new Player(namePlayer1.getText(), colourPlayer1.getValue(), isCompP1.isSelected());
                player2 = new Player(namePlayer2.getText(), colourPlayer2.getValue(), isCompP2.isSelected());
                player1.setId("player1");
                player2.setId("player2");

                player1.setHasTurn(b);
                player2.setHasTurn(!b);

                if(globe.isSelected()){
                    gameboard.setGlobe(true);
                }else{
                    gameboard.setGlobe(false);
                }

                System.out.println("globe : "  +gameboard.isGlobe());

                if(getWorkerAmount()==1){
                    gameboard.set1Worker(true);
                }else if(getWorkerAmount()==3){
                    gameboard.set3Worker(true);
                }

                gameboard.buildField(getFieldSize());
                gameboard.setPlayersAndWorker1(player1, player2);
                gameboard.paintWorkerAndPlayer1();

            if (godsForceCheck.isSelected()) {

                setGodP1(godlist2.getSelectionModel().getSelectedItem());
                player1.getGod().godSkill(gameboard);
                gameboard.showGodP1(godlist2.getSelectionModel().getSelectedItem(), colourPlayer1.getValue());
                setGodP2(godlist1.getSelectionModel().getSelectedItem());
                player2.getGod().godSkill(gameboard);
                gameboard.showGodP2(godlist1.getSelectionModel().getSelectedItem(), colourPlayer2.getValue(), getFieldSize());
            }


            gameboard.setStonesFromWinningHeight(getWinningHeight());



            /**
             * stone settings will be applied in Gameboard
             */
            if(amountLevel3 != null && amountLevel5 == null && amountLevel6 == null) {
                gameboard.setStoneSetting(amountLevel1.getText(), amountLevel2.getText(),
                        amountLevel3.getText(), amountDome.getText()
                );
            } else if(amountLevel3 != null && amountLevel5 != null && amountLevel6 == null) {
                gameboard.setStoneSettingWinningHeight4(amountLevel1.getText(), amountLevel2.getText(),
                        amountLevel3.getText(), amountLevel5.getText(), amountDome.getText()
                );
            } else if(amountLevel3 != null && amountLevel5 != null && amountLevel6 != null) {
                gameboard.setStoneSettingWinningHeight5(amountLevel1.getText(), amountLevel2.getText(),
                        amountLevel3.getText(), amountLevel5.getText(), amountLevel6.getText(), amountDome.getText()
                );
            } else if(amountLevel3 == null) {
                gameboard.setStoneSettingWinningHeight2(amountLevel1.getText(), amountLevel2.getText(), amountDome.getText()
                );
            }
                gameboard.manageSeperateStonePools(seperateStonePoolCheck.isSelected());

                gameboard.setLegalMovesId(saveLegalMovesVersionId());


                if(getFieldSize() ==8){
                    Scene gbScreen = new Scene(gameboardScreen,1000,700);
                    stage.setScene(gbScreen);
                } else if(getFieldSize() != 8 && getSeperateStonePoolCheck().isSelected()) {
                    Scene gbScreen = new Scene(gameboardScreen, 700, 600);
                    stage.setScene(gbScreen);
                }
                else{
                    settingPane.getChildren().setAll(gameboardScreen);
                }
                tmp = gameboard;


            } catch (IOException e) {
                System.out.println(e);
            }

        }
    }



}
