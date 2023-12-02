package tnt.viewmodel;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Data;
import tnt.model.*;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import tnt.model.god.*;


/**
 * viewmodel for the gameboard
 *
 * @author Soohyeon Kim, Veronika Wehr
 *
 */
@Data
public class GameboardController implements Initializable, Serializable {

    @FXML
    Label countlabelPlayer1;
    @FXML
    Label countlabelPlayer2;
    @FXML
    MenuItem saveItem;

    @FXML
    ImageView imagePlayer2;
    @FXML
    ImageView imagePlayer1;

    @FXML
    ImageView playerImage3;
    @FXML
    Label playerCountlabel3;
    @FXML
    AnchorPane gameboard;
    @FXML
    GridPane gamefield;

    @FXML
    Label playerName1;
    @FXML
    Label playerName2;

    @FXML
    Label playerName3;

    @FXML
    Label playerCount1;
    @FXML
    Label playerCount2;
    @FXML
    Label playerCount3;


    @FXML
    Text stone1;
    @FXML
    Text stone2;
    @FXML
    Text stone3;
    @FXML
    Text stone4;
    @FXML
    Text stoneLevel4;
    @FXML
    Text stoneLevel5;

    @FXML
    Text stone1Label;
    @FXML
    Text stone2Label;
    @FXML
    Text stone3Label;
    @FXML
    Text domeLabel;
    @FXML
    Text stone4Label;
    @FXML
    Text stone5Label;

    @FXML
    Text stone1X;
    @FXML
    Text stone2X;
    @FXML
    Text stone3X;
    @FXML
    Text domeX;
    @FXML
    Text stone4X;
    @FXML
    Text stone5X;

    @FXML
    Rectangle stone1Rect;
    @FXML
    Rectangle stone2Rect;
    @FXML
    Rectangle stone3Rect;
    @FXML
    Rectangle domeRect;
    @FXML
    Rectangle stone4Rect;
    @FXML
    Rectangle stone5Rect;

    @FXML
    Circle circle1;

    @FXML
    Circle circle2;

    @FXML
    Circle circle3;

    @FXML
    Circle circle4;

    @FXML
    Circle circle5;

    @FXML
    Circle circle6;

    @FXML
    Circle circle7;

    @FXML
    Circle circle8;

    @FXML
    Circle circle9;

    @FXML
    VBox workerContainerP1;

    @FXML
    VBox workerContainerP2;

    @FXML
    HBox workerContainerP3;

    @FXML
    Rectangle colorPlayer1;
    @FXML
    Rectangle colorPlayer2;
    @FXML
    Rectangle colorPlayer3;


    @FXML
    private MenuItem quitMenuItem;

    @FXML
    private MenuItem homeMenuItem;

    @FXML
    private MenuItem germanItem;

    @FXML
    private MenuItem englishItem;

    @FXML
    private MenuItem themeItem;

    @FXML
    private MenuItem koreanItem;

    @FXML
    VBox godPlayer1;

    @FXML
    VBox godPlayer2;

    @FXML
    VBox godPlayer3;

    @FXML
    HBox stonePoolP1;

    @FXML
    HBox stonePoolP2;

    @FXML
    HBox stonePoolP3;


    private List<Rectangle> validFields;

    /**
     * In this list, fields on the gamefield are saved, which are valid to move to by a specific worker
     * for manageHighlight()
     */
    private List<Rectangle> validFieldsToMoveTo;
    private List<Rectangle> validFieldsToBuild;

    public GameboardController(Label playerName1, Label playerName2, Rectangle colorPlayer1, Rectangle colorPlayer2) {
        playerName1 = playerName1;
        playerName2 = playerName2;
        colorPlayer1 = colorPlayer1;
        colorPlayer2 = colorPlayer2;
    }

    public int[][] getClickCount() {
        return clickCount;
    }

    public void setClickCount(int[][] clickCount) {
        this.clickCount = clickCount;
    }

    /**
     * counting the number of times each block has been clicked
     */
    public int[][] clickCount;

    /**
     * Player variable for the first/second/third player
     */
    private Player player1;
    private Player player2;
    private Player player3;

    /**
     * The circle, which has just been moved on the gameboard (drag and drop)
     */
    private Circle movingWorker;

    private Node clickedWorker;

    int colIndNotYetDragged;
    int rowIndNotYetDragged;

    int colIndNotYetDraggedAI;
    int rowIndNotYetDraggedAI;

    /**
     * Worker variable for the first worker
     */
    private Worker worker1;

    /**
     * Worker variable for the second worker
     */
    private Worker worker2;

    /**
     * Worker variable for the third worker
     */
    private Worker worker3;

    /**
     * Worker variable for the fourth worker
     */
    private Worker worker4;

    /**
     * Worker variable for the fifth worker
     */
    private Worker worker5;

    /**
     * Worker variable for the sixth worker
     */
    private Worker worker6;

    /**
     * Worker variable for the seventh worker
     */
    private Worker worker7;

    /**
     * Worker variable for the eights worker
     */
    private Worker worker8;

    /**
     * Worker variable for the ninth worker
     */
    private Worker worker9;

    /**
     * Worker variable for the worker, which has just been moved (drag and drop)
     */
    private Worker workerInTurn;

    /**
     * Player variable for the player, which has the turn
     */
    private Player playerInTurn;

    private Rectangle artemisRect;

    public int getWorkerHeight() {
        return workerHeight;
    }

    public void setWorkerHeight(int workerHeight) {
        this.workerHeight = workerHeight;
    }

    /**
     * Height of a field, where a specific worker is currently positoned
     */
    private int workerHeight;

    /**
     * Height of a field, where a specific worker was positioned before movement
     */
    public int heightBeforeMovement;

    public int heightBeforeMovementAI;


    public boolean isActive = false;

    public GameManager getGameManager() {
        return gameManager;
    }

    /**
     * Variable for the gamemanager
     */
    private GameManager gameManager;


    /**
     * variable for the option the world is a globe
     */
    private boolean isGlobe;
    public void setGlobe(boolean globe){
        isGlobe = globe;
    }

    public void setGamefield(GridPane f){
        gamefield =f;
    }

    public void setWorkerInTurn(Worker w){
        workerInTurn =w;
    }
    public void set1Worker(boolean is1Worker) {
        this.is1Worker = is1Worker;
    }
    public void set3Worker(boolean is3Worker) {
        this.is3Worker = is3Worker;
    }
    public void set3Player(boolean is3Player) {
        this.is3Player = is3Player;
    }
    public void setPlayer1(Player p1){this.player1 = p1;}
    public void setPlayer2(Player p2){this.player2 = p2;}
    public void setPlayer3(Player p3){this.player3 = p3;}
    public Player getPlayerInTurn(){
        return playerInTurn;
    }
    public void setPlayerInTurn(Player player){
        playerInTurn = player;
    }

    private boolean is1Worker = false;
    private boolean is2Worker = false;
    private boolean is3Worker = false;
    private boolean is3Player = false;


    Worker worker = new Worker();

    /**
     * Lists for valid fields per player (depending on worker amount)
     */

    List<Rectangle> allValidFieldsPlayer1 = new ArrayList<>();

    List<Rectangle> allValidFieldsPlayer2 = new ArrayList<>();

    List<Rectangle> allValidFieldsPlayer3 = new ArrayList<>();

    Random random = new Random();
    private int fieldsize;
    private boolean[][] occupiedPositions;

    public int getHeightToWin() {
        return heightToWin;
    }

    public void setHeightToWin(int heightToWin) {
        this.heightToWin = heightToWin;
    }

    /**
     * The height the user choses for the game to be won
     */

    private int heightToWin;

    @FXML
    private Menu menumenu;
    @FXML
    private Menu menusettings;

    public God godObjectP1;
    public God godObjectP2;
    public God godObjectP3;

    private int aiLevel;

    public boolean isStonePoolsSeparated() {
        return stonePoolsSeparated;
    }

    public void setStonePoolsSeparated(boolean stonePoolsSeparated) {
        this.stonePoolsSeparated = stonePoolsSeparated;
    }

    /**
     * Variable in which is saved if each player has a separate stone pool
     */

    public boolean stonePoolsSeparated;

    public Text stone1P1;
    public Text stone2P1;
    public Text stone3P1;
    public Text stone4P1;
    public Text stoneLevel4P1;
    public Text stoneLevel5P1;

    public Text stone1P2;
    public Text stone2P2;
    public Text stone3P2;
    public Text stone4P2;
    public Text stoneLevel4P2;
    public Text stoneLevel5P2;

    public Text stone1P3;
    public Text stone2P3;
    public Text stone3P3;
    public Text stone4P3;
    public Text stoneLevel4P3;
    public Text stoneLevel5P3;

    public boolean notValidAlertShown = false;

    /**
     * Variable in which is saved, which variant of movement (legal moves) the user chose in the gamesettings
     */

    private int legalMovesId;
    Text godTextP2;
    Text godTextP1;
    Text godTextP3;
    boolean isDom = false;

    boolean toMainForAI = false;
    boolean aiSelected = false;

    List<Rectangle> validFieldsToMoveToAI2 = new ArrayList<>();
    List<Rectangle> validFieldsToBuildAI2 = new ArrayList<>();
    List<Player> restPlayers = new ArrayList<>();


    private boolean playerAthenaMovedUp;

    private int diffInMovementAthena;

    private int workerHeightAthena;

    Artemis artemis = new Artemis();
    /**
     * Constructor for Gameboard, in which a Gamemanager object is instanciated
     */
    public GameboardController() {
        gameManager = new GameManager();
    }

    public void setAiLevel(int level){
        this.aiLevel = level;
    }
    /**
     * All actions performed, when the gameboard is loaded
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        translateManager();


        if (ChangeTheme.getTheme() == 2) {
            Image newImage = new Image(getClass().getResource("/image/vampire1.png").toExternalForm());
            imagePlayer1.setImage(newImage);
            Image newImage2 = new Image(getClass().getResource("/image/vampire2.png").toExternalForm());
            imagePlayer2.setImage(newImage2);
        }

        /**
         * The worker objects are created and as IDs the fxml-IDs of the circles on the gameboard (which represent the workers) are handed over
         */
        worker1 = new Worker(circle1.getId());
        worker2 = new Worker(circle2.getId());
        worker3 = new Worker(circle3.getId());
        worker4 = new Worker(circle4.getId());

        System.out.println("initialize");


        //quitgame Menu drop down menu
        quitMenuItem.setOnAction(event -> System.exit(0));

        //go Homescreen
        homeMenuItem.setOnAction(event -> loadmainmenu());

        germanItem.setOnAction(event -> {
            LanguageManager.setLanguage("de");
            translateManager();
        });
        englishItem.setOnAction(event -> {
            LanguageManager.setLanguage("en");
            translateManager();
        });
        koreanItem.setOnAction(event -> {
            LanguageManager.setLanguage("kr");
            translateManager();
        });

        themeItem.setOnAction(event -> {
            if (ChangeTheme.getTheme() == 1) {
                ChangeTheme.setTheme(2);
                Image newImage = new Image(getClass().getResource("/image/vampire1.png").toExternalForm());
                imagePlayer1.setImage(newImage);
                Image newImage2 = new Image(getClass().getResource("/image/vampire2.png").toExternalForm());
                imagePlayer2.setImage(newImage2);
                if (is3Player) {
                    Image newImage3 = new Image(getClass().getResource("/image/vampire3.png").toExternalForm());
                    playerImage3.setImage(newImage3);
                }
            } else {
                ChangeTheme.setTheme(1);
                Image newImage = new Image(getClass().getResource("/image/zombie1.png").toExternalForm());
                imagePlayer1.setImage(newImage);
                Image newImage2 = new Image(getClass().getResource("/image/zombie2.png").toExternalForm());
                imagePlayer2.setImage(newImage2);
                if (is3Player) {
                    Image newImage3 = new Image(getClass().getResource("/image/zombie3.png").toExternalForm());
                    playerImage3.setImage(newImage3);
                }
            }
        });

        gameboard.setOnMousePressed(actionEvent -> {
            Node clickedNode = (Node) actionEvent.getTarget();
            try {
                Circle clcikedCircle = (Circle) clickedNode;
                if (playerInTurn.getId().equals(worker.getPlayerFromCircle(clcikedCircle.getId())) && playerInTurn.isCom() == false ) {
                    makeWorkerDraggable(clcikedCircle);
                } else {
                    dontMakeDraggable(clcikedCircle);
                    System.out.println("not valid action");
                }
            } catch (NullPointerException | ClassCastException e) {

            }
        });

        gamefield.setOnMouseClicked(actionEvent -> {
            Node clickedNode = (Node) actionEvent.getTarget();
            try{
                if(clickedNode instanceof Circle  && playerInTurn.getId().equals(worker.getPlayerFromCircle(clickedNode.getId()))){
                    Circle clcikedCircle = (Circle) clickedNode;

                    //show the field, where the worker can build
                    if(playerInTurn.isMoved() && getWorkerOfCircle(clcikedCircle)== workerInTurn){
                            saveValidFields(clickedNode);
                            manageHighlighting();

                    }else if(playerInTurn.isMoved()==false) {

                        /*
                         * If the player which is in turn has Apollo chosen as a god, the additional valid fields are calculated and
                         * added to the regularValidFields and therefore saved in the validFieldsToMove list
                         */

                        System.out.println(anyPlayerHasAthena());

                        if ("Apollo".equals(playerInTurn.getAssignedGod())) {
                            Apollo apollo = new Apollo();
                            List<Rectangle> validFieldsApollo = apollo.adaptValidFields(gamefield, clickedNode);

                            System.out.println(playerInTurn.getAssignedGod());
                            System.out.println(anyPlayerHasAthena());

                            //if special legal moves have been chosen
                            if (legalMovesId == 2 || legalMovesId == 3 || legalMovesId == 4) {

                                //if any opponent has Athena as god
                                if (anyPlayerHasAthena()) {
                                    //if the player with Athena moved up in the last turn
                                    if (playerAthenaMovedUp) {
                                        List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                        fieldsLegalMoves.addAll(validFieldsApollo);
                                        saveWorkerHeight(clickedNode);
                                        fieldsLegalMoves.removeAll(rectsWithHigherWorkerHeights(gamefield));
                                        validFieldsToMoveTo = fieldsLegalMoves;
                                        manageHighlighting();

                                        //if the player with Athena did not move up in the last turn
                                    } else  {
                                        List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                        fieldsLegalMoves.addAll(validFieldsApollo);
                                        validFieldsToMoveTo = fieldsLegalMoves;
                                        manageHighlighting();
                                    }

                                    //if there is no opponent with Athena as God
                                } else if (!anyPlayerHasAthena()) {
                                    List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                    fieldsLegalMoves.addAll(validFieldsApollo);
                                    validFieldsToMoveTo = fieldsLegalMoves;
                                    manageHighlighting();
                                }

                                // same with default legal moves options
                            } else {
                                if (anyPlayerHasAthena()) {
                                    System.out.println("Ich habe Apollo, standard legal moves und jmd anderes hat Athena");
                                    if (playerAthenaMovedUp) {
                                        System.out.println("Ich hab Apollo & Athena hat sich hoch bewegt");
                                        List<Rectangle> regularValidFields = saveValidFields(clickedNode);
                                        regularValidFields.addAll(validFieldsApollo);
                                        saveWorkerHeight(clickedNode);
                                        regularValidFields.removeAll(rectsWithHigherWorkerHeights(gamefield));
                                        validFieldsToMoveTo = regularValidFields;
                                        manageHighlighting();
                                    } else {
                                        System.out.println("Ich hab Apollo & Athena hat sich nicht hoch bewegt");
                                        List<Rectangle> regularValidFields = saveValidFields(clickedNode);
                                        regularValidFields.addAll(validFieldsApollo);
                                        validFieldsToMoveTo = regularValidFields;
                                        manageHighlighting();
                                    }

                                } else if (!anyPlayerHasAthena()) {
                                    System.out.println("Ich hab Apollo & & keiner der anderen Spieler ist Athena");
                                    List<Rectangle> regularValidFields = saveValidFields(clickedNode);
                                    regularValidFields.addAll(validFieldsApollo);
                                    validFieldsToMoveTo = regularValidFields;
                                    manageHighlighting();
                                }

                            }

                        /*
                        If the player which is in turn chose Pan as a god, the validFieldsToMoveTo are adapted, also depending on the legal moves settings
                        */

                        } else if ("Pan".equals(playerInTurn.getAssignedGod())) {
                            if (legalMovesId == 2 || legalMovesId == 3 || legalMovesId == 4) {
                                if (anyPlayerHasAthena()) {
                                    if (playerAthenaMovedUp) {
                                        heightBeforeMovement = saveWorkerHeightBeforeMovement(clickedNode);
                                        List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                        saveWorkerHeight(clickedNode);
                                        fieldsLegalMoves.removeAll(rectsWithHigherWorkerHeights(gamefield));
                                        validFieldsToMoveTo = fieldsLegalMoves;
                                        manageHighlighting();
                                    } else {
                                        heightBeforeMovement = saveWorkerHeightBeforeMovement(clickedNode);
                                        List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                        validFieldsToMoveTo = fieldsLegalMoves;
                                        manageHighlighting();
                                    }
                                } else if (!anyPlayerHasAthena()) {
                                    heightBeforeMovement = saveWorkerHeightBeforeMovement(clickedNode);
                                    List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                    validFieldsToMoveTo = fieldsLegalMoves;
                                    manageHighlighting();
                                }

                            } else {
                                if (anyPlayerHasAthena()) {
                                    if (playerAthenaMovedUp) {
                                        heightBeforeMovement = saveWorkerHeightBeforeMovement(clickedNode);
                                        List<Rectangle> regularValidFields = saveValidFields(clickedNode);
                                        saveWorkerHeight(clickedNode);
                                        regularValidFields.removeAll(rectsWithHigherWorkerHeights(gamefield));
                                        validFieldsToMoveTo = regularValidFields;
                                        manageHighlighting();
                                    } else {
                                        heightBeforeMovement = saveWorkerHeightBeforeMovement(clickedNode);
                                        saveValidFields(clickedNode);
                                        manageHighlighting();
                                    }
                                } else if(!anyPlayerHasAthena()) {
                                    heightBeforeMovement = saveWorkerHeightBeforeMovement(clickedNode);
                                    saveValidFields(clickedNode);
                                    manageHighlighting();
                                }

                            }

                        /*
                        If Hermes has been chosen as god
                         */

                        } else if ("Hermes".equals(playerInTurn.getAssignedGod())) {

                            Hermes hermes = new Hermes();
                            saveWorkerHeight(clickedNode);
                            List<Rectangle> validFieldsHermes = hermes.adaptValidFields(gamefield, rectsWithWorkerHeights(gamefield), clickedNode);

                            if (legalMovesId == 2 || legalMovesId == 3 || legalMovesId == 4) {
                                if (anyPlayerHasAthena()) {
                                    if (playerAthenaMovedUp) {
                                        List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                        fieldsLegalMoves.addAll(validFieldsHermes);
                                        saveWorkerHeight(clickedNode);
                                        fieldsLegalMoves.removeAll(rectsWithHigherWorkerHeights(gamefield));
                                        validFieldsToMoveTo = fieldsLegalMoves;
                                        manageHighlighting();
                                    } else {
                                        List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                        fieldsLegalMoves.addAll(validFieldsHermes);
                                        validFieldsToMoveTo = fieldsLegalMoves;
                                        manageHighlighting();
                                    }
                                } else if(!anyPlayerHasAthena()) {
                                    List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                    fieldsLegalMoves.addAll(validFieldsHermes);
                                    validFieldsToMoveTo = fieldsLegalMoves;
                                    manageHighlighting();
                                }

                            } else {
                                if (anyPlayerHasAthena()) {
                                    if (playerAthenaMovedUp) {
                                        List<Rectangle> regularValidFields = saveValidFields(clickedNode);
                                        regularValidFields.addAll(validFieldsHermes);
                                        saveWorkerHeight(clickedNode);
                                        regularValidFields.removeAll(rectsWithHigherWorkerHeights(gamefield));
                                        validFieldsToMoveTo = regularValidFields;
                                        manageHighlighting();
                                    } else {
                                        List<Rectangle> regularValidFields = saveValidFields(clickedNode);
                                        regularValidFields.addAll(validFieldsHermes);
                                        validFieldsToMoveTo = regularValidFields;
                                        manageHighlighting();
                                    }
                                } else if(!anyPlayerHasAthena()) {
                                    List<Rectangle> regularValidFields = saveValidFields(clickedNode);
                                    regularValidFields.addAll(validFieldsHermes);
                                    validFieldsToMoveTo = regularValidFields;
                                    manageHighlighting();
                                }

                            }
                        }
                       /*
                            If neither Pan nor Apollo nor Hermes has been chosen as a god
                        */

                               /*
                        If Atlas has been chosen as god
                         */

                        else if ("Atlas".equals(playerInTurn.getAssignedGod())) {
                            if (legalMovesId == 2 || legalMovesId == 3 || legalMovesId == 4) {
                                if (anyPlayerHasAthena()) {
                                    if (playerAthenaMovedUp) {
                                        List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                        saveWorkerHeight(clickedNode);
                                        fieldsLegalMoves.removeAll(rectsWithHigherWorkerHeights(gamefield));
                                        validFieldsToMoveTo = fieldsLegalMoves;
                                        manageHighlighting();
                                    } else {
                                        List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                        validFieldsToMoveTo = fieldsLegalMoves;
                                        manageHighlighting();
                                    }
                                } else if(!anyPlayerHasAthena()) {
                                    List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                    validFieldsToMoveTo = fieldsLegalMoves;
                                    manageHighlighting();
                                }


                            } else {
                                if (anyPlayerHasAthena()) {
                                    if (playerAthenaMovedUp) {
                                        List<Rectangle> regularValidFields = saveValidFields(clickedNode);
                                        saveWorkerHeight(clickedNode);
                                        regularValidFields.removeAll(rectsWithHigherWorkerHeights(gamefield));
                                        validFieldsToMoveTo = regularValidFields;
                                        manageHighlighting();
                                    } else {
                                        saveValidFields(clickedNode);
                                        manageHighlighting();
                                    }
                                } else if (!anyPlayerHasAthena()) {
                                    saveValidFields(clickedNode);
                                    manageHighlighting();
                                }

                            }


                              /*
                        If Hephaestus has been chosen as god
                         */

                        } else if ("Hephaestus".equals(playerInTurn.getAssignedGod())) {
                            if (legalMovesId == 2 || legalMovesId == 3 || legalMovesId == 4) {
                                if (anyPlayerHasAthena()) {
                                    if (playerAthenaMovedUp) {
                                        List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                        saveWorkerHeight(clickedNode);
                                        fieldsLegalMoves.removeAll(rectsWithHigherWorkerHeights(gamefield));
                                        validFieldsToMoveTo = fieldsLegalMoves;
                                        manageHighlighting();
                                    } else {
                                        List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                        validFieldsToMoveTo = fieldsLegalMoves;
                                        manageHighlighting();
                                    }
                                } else if(!anyPlayerHasAthena()) {
                                    List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                    validFieldsToMoveTo = fieldsLegalMoves;
                                    manageHighlighting();
                                }
                            } else {
                                if (anyPlayerHasAthena()) {
                                    if (playerAthenaMovedUp) {
                                        List<Rectangle> regularValidFields = saveValidFields(clickedNode);
                                        saveWorkerHeight(clickedNode);
                                        regularValidFields.removeAll(rectsWithHigherWorkerHeights(gamefield));
                                        validFieldsToMoveTo = regularValidFields;
                                        manageHighlighting();
                                    } else {
                                        saveValidFields(clickedNode);
                                        manageHighlighting();
                                    }
                                } else if(!anyPlayerHasAthena()) {
                                    saveValidFields(clickedNode);
                                    manageHighlighting();
                                }
                            }



                           /*
                        if Athena has been chosen as god by the player in turn
                         */

                    } else if(("Athena".equals(playerInTurn.getAssignedGod()))) {
                            Athena athena = new Athena();
                            int heightBeforeMovementAthena = saveWorkerHeightBeforeMovement(clickedNode);
                            heightBeforeMovement = heightBeforeMovementAthena;

                            System.out.println("Höhe VOR der Bewegung: " + heightBeforeMovement);
                            System.out.println("Differenz zu davor: " + diffInMovementAthena);

                            if (diffInMovementAthena < 0) {
                                playerAthenaMovedUp = true;
                                if (legalMovesId == 2 || legalMovesId == 3 || legalMovesId == 4) {
                                    List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                    validFieldsToMoveTo = fieldsLegalMoves;
                                    manageHighlighting();

                                } else {
                                    saveValidFields(clickedNode);
                                    manageHighlighting();
                                }
                            } else {
                                playerAthenaMovedUp = false;
                                if (legalMovesId == 2 || legalMovesId == 3 || legalMovesId == 4) {
                                    List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(), clickedNode);
                                    validFieldsToMoveTo = fieldsLegalMoves;
                                    manageHighlighting();

                                } else {
                                    saveValidFields(clickedNode);
                                    manageHighlighting();
                                }
                            }
                        }
                           /*
                        If none of the above gods have has been chosen as a god
                         */
                        else {
                            if(legalMovesId == 2 || legalMovesId == 3 || legalMovesId == 4) {
                                List<Rectangle> fieldsLegalMoves = saveValidFieldsLegalMoves(getLegalMovesId(),clickedNode);
                                validFieldsToMoveTo = fieldsLegalMoves;
                                manageHighlighting();

                            } else {
                                saveValidFields(clickedNode);
                                manageHighlighting();
                            }
                        }
                    } else {
                        System.out.println("false worker");
                    }

                //see wheather the clicked field belongs to the valid field of worker
                }else if(clickedNode instanceof Rectangle && validFieldsToBuild.contains(clickedNode) && playerInTurn.isMoved()){

                    Rectangle clickedRectangle = (Rectangle) clickedNode;
                    CompletableFuture<Boolean> future = new CompletableFuture<>();
                    CompletableFuture<Boolean> future2 = new CompletableFuture<>();
                    CompletableFuture<Boolean> future3 = new CompletableFuture<>();
                    AtomicInteger godCount = new AtomicInteger();

                    if(playerInTurn.getGod()==null) {
                        clickStone(clickedNode);

                    }else if(playerInTurn.getAssignedGod().equals("Atlas")){
                        Atlas atlas = new Atlas(playerInTurn,
                                    gameboard, clickedRectangle, GridPane.getColumnIndex(clickedNode),
                                    GridPane.getRowIndex(clickedNode), heightToWin, clickCount, stone3, stone4,
                                    stoneLevel4, stoneLevel5 ,gameManager, future2);

                        atlas.build();

                        future2.thenAccept(tmp -> {
                          if(!tmp){
                              if(clickCount[GridPane.getRowIndex(clickedRectangle)][GridPane.getColumnIndex(clickedRectangle)]==heightToWin+1){
                                  isDom =true;
                                  clickStone(clickedNode);

                              }else{
                                  clickStone(clickedNode);
                                  future3.complete(true);

                              }
                            }else if(tmp){
                              godCount.set(2);
                              if(stonePoolsSeparated){
                                  if(playerInTurn==player1){
                                      String amount = stone4P1.getText();
                                      int newAmount = Integer.parseInt(amount);

                                      if (newAmount > 0) {
                                          newAmount -= 1;
                                          String res = String.valueOf(newAmount);
                                          stone4P1.setText(res);
                                     }
                                 }else if(playerInTurn==player2){
                                      String amount = stone4P2.getText();
                                      int newAmount = Integer.parseInt(amount);

                                      if (newAmount > 0) {
                                          newAmount -= 1;
                                          String res = String.valueOf(newAmount);
                                          stone4P2.setText(res);
                                      }
                                  }else if(playerInTurn==player3){
                                      String amount = stone4P3.getText();
                                      int newAmount = Integer.parseInt(amount);

                                      if (newAmount > 0) {
                                          newAmount -= 1;
                                          String res = String.valueOf(newAmount);
                                          stone4P3.setText(res);
                                      }
                                  }
                              }
                              future3.complete(true);

                          }
                        });

                    }else if(playerInTurn.getAssignedGod().equals("Hephaestus")){
                        Hephaestus hephaestus = new Hephaestus();
                        Button buildMore = new Button("+ 1");
                        Button cancle = new Button("x");
                        HBox hbox = new HBox();
                        hephaestus.buildMore(gameboard, buildMore, cancle, clickedRectangle, hbox, future2);

                        future2.thenAccept(tmp -> {
                            if (tmp && clickCount[GridPane.getRowIndex(clickedRectangle)][GridPane.getColumnIndex(clickedRectangle)]<=heightToWin-2) {
                                godCount.set(1);
                                clickStone(clickedNode);
                                clickStone(clickedNode);
                                gameboard.getChildren().remove(hbox);
                                future3.complete(true);

                            } else if(!tmp){
                                if(clickCount[GridPane.getRowIndex(clickedRectangle)][GridPane.getColumnIndex(clickedRectangle)]==heightToWin+1){
                                    isDom =true;
                                    clickStone(clickedNode);

                                }else{
                                    clickStone(clickedNode);
                                    future3.complete(true);

                                }
                                gameboard.getChildren().remove(hbox);

                            }else if(clickCount[GridPane.getRowIndex(clickedRectangle)][GridPane.getColumnIndex(clickedRectangle)]>=heightToWin-1){
                                if(LanguageManager.getCurrentLanguage() == "en") {
                                    showAlert("you can't build a dome with this skill");
                                } else if (LanguageManager.getCurrentLanguage() == "de") {
                                    showAlert("Du kannst mit dieser Fähigkeit keine Kuppel bauen");
                                }

                                gameboard.getChildren().remove(hbox);
                            }

                        });
                    }
                    else if("Artemis".equals(playerInTurn.getAssignedGod())){
                        artemis.setRest(2);
                        if(artemisRect.getFill()==Color.GRAY){
                            artemisRect.setFill(null);
                        }
                        clickStone(clickedNode);

                    }else{
                        clickStone(clickedNode);
                    }

                    clearFieldHighlighting(allRectsOnGamefield());

                    if(playerInTurn.getAssignedGod()==null && !isDom){
                        if (is3Player) {
                            resetWorkerMoved(playerInTurn);
                            switchTurn();
                            handleWinningActionsSurroundingP3();
                            paintWorkerAndPlayer();
                            switchToStartAfterWinning();

                        } else{
                            resetWorkerMoved(playerInTurn);
                            switchTurn1();
                            paintWorkerAndPlayer1();
                            handleWinningActionsSurroundingP2();
                            switchToStartAfterWinning();
                        }
                    } else if(playerInTurn.getAssignedGod().equals("Hephaestus") ||playerInTurn.getAssignedGod().equals("Atlas")) {
                        future3.thenAccept(tmp -> {

                            //manage to switch turn for Hephaestus
                            if(godCount.get()==1){
                                playerInTurn.setTurnAmount(playerInTurn.getTurnAmount() - 1);
                                String newCount = String.valueOf(playerInTurn.getTurnAmount());
                                if (playerInTurn == player1) {
                                    playerCount1.setText(newCount);
                                } else if (playerInTurn == player2) {
                                    playerCount2.setText(newCount);
                                } else if (playerInTurn == player3) {
                                    playerCount3.setText(newCount);
                                }
                            //manage to switch turn for atlas
                            } else if(godCount.get()==2){
                                playerInTurn.setTurnAmount(playerInTurn.getTurnAmount() + 1);
                                String newCount = String.valueOf(playerInTurn.getTurnAmount());
                                if (playerInTurn == player1) {
                                    playerCount1.setText(newCount);
                                } else if (playerInTurn == player2) {
                                    playerCount2.setText(newCount);
                                } else if (playerInTurn == player3) {
                                    playerCount3.setText(newCount);
                                }
                            }
                            if (!isDom && is3Player ) {
                                resetWorkerMoved(playerInTurn);
                                switchTurn();
                                handleWinningActionsSurroundingP3();
                                paintWorkerAndPlayer();
                                switchToStartAfterWinning();
                                isDom=false;

                            } else if(!isDom){
                                resetWorkerMoved(playerInTurn);
                                switchTurn1();
                                paintWorkerAndPlayer1();
                                handleWinningActionsSurroundingP2();
                                switchToStartAfterWinning();
                            }
                        });
                    } else{
                        if (!isDom && is3Player) {
                            resetWorkerMoved(playerInTurn);
                            switchTurn();
                            handleWinningActionsSurroundingP3();
                            paintWorkerAndPlayer();
                            switchToStartAfterWinning();
                            isDom=false;

                        } else if(!isDom){
                            resetWorkerMoved(playerInTurn);
                            switchTurn1();
                            paintWorkerAndPlayer1();
                            handleWinningActionsSurroundingP2();
                            switchToStartAfterWinning();
                        }
                        clearFieldHighlighting(allRectsOnGamefield());

                    }
                    clearFieldHighlighting(allRectsOnGamefield());

                }else{
                    clearFieldHighlighting(allRectsOnGamefield());
                }

            }catch(NullPointerException e){
                System.out.println("invalid field");
                System.out.println(e);
            }
        });



    }


    private void translateManager() {
        if(LanguageManager.getCurrentLanguage()== "en") {
            countlabelPlayer1.setText("count");
            countlabelPlayer2.setText("count");
            if(is3Player) {
                playerCountlabel3.setText("count");
            }
            menumenu.setText("Menu");
            menusettings.setText("Settings");
            homeMenuItem.setText("Home");
            quitMenuItem.setText("Quit");
            germanItem.setText("German");
            englishItem.setText("English");
            koreanItem.setText("Korean");
            themeItem.setText("Change theme");
        } else if (LanguageManager.getCurrentLanguage()== "de") {
            countlabelPlayer1.setText("Runde");
            countlabelPlayer2.setText("Runde");
            if(is3Player) {
                playerCountlabel3.setText("Runde");
            }
            menumenu.setText(LanguageManager.getText("menumenu"));
            menusettings.setText(LanguageManager.getText("menusettings"));
            homeMenuItem.setText(LanguageManager.getText("menuhome"));
            saveItem.setText(LanguageManager.getText("save"));
            quitMenuItem.setText(LanguageManager.getText("quit"));
            germanItem.setText(LanguageManager.getText("menugerman"));
            englishItem.setText(LanguageManager.getText("menuenglish"));
            koreanItem.setText(LanguageManager.getText("menukorean"));
            themeItem.setText(LanguageManager.getText("menutheme"));
        } else if (LanguageManager.getCurrentLanguage()== "kr") {
            countlabelPlayer1.setText("카운트");
            countlabelPlayer2.setText("카운트");
            if(is3Player) {
                playerCountlabel3.setText("카운트");
            }
            menumenu.setText(LanguageManager.getText("menumenu"));
            menusettings.setText(LanguageManager.getText("menusettings"));
            homeMenuItem.setText(LanguageManager.getText("menuhome"));
            saveItem.setText(LanguageManager.getText("save"));
            quitMenuItem.setText(LanguageManager.getText("quit"));
            germanItem.setText(LanguageManager.getText("menugerman"));
            englishItem.setText(LanguageManager.getText("menuenglish"));
            koreanItem.setText(LanguageManager.getText("menukorean"));
            themeItem.setText(LanguageManager.getText("menutheme"));
        }
    }

    public void checkAi_FirstTurn(Player player, int ailevel){
        setAiLevel(ailevel);
        System.out.println("AiLevel: " + aiLevel);
        if(player.isCom()){
            aiAction(player);
        }
    }

    /**
     * Provides information, if Athena is used in the game as god
     * @return
     */

    public boolean anyPlayerHasAthena() {
        boolean anyPlayerHasAthena = false;
        if(is3Player) {
            if ("Athena".equals(player1.getAssignedGod())) {
                anyPlayerHasAthena = true;
            } else if("Athena".equals(player2.getAssignedGod())) {
                anyPlayerHasAthena = true;
            } else if("Athena".equals(player3.getAssignedGod())) {
                anyPlayerHasAthena = true;
            }
        } else {
            if ("Athena".equals(player1.getAssignedGod())) {
                anyPlayerHasAthena = true;
            } else if("Athena".equals(player2.getAssignedGod())) {
                anyPlayerHasAthena = true;
            }
        }
        System.out.println("Athena is in the game: " + anyPlayerHasAthena);
        return anyPlayerHasAthena;
    }

    /**
     * Clear & Save valid fields per player in a list for 2-Player-Version and determines the player, for which the surrounding win condition is checked
     * in the gamemanager
     */

    public void handleWinningActionsSurroundingP2() {

        //handle win condition with surrounding the component - 2 Players
        allValidFieldsPlayer1.clear();
        allValidFieldsPlayer2.clear();
        saveAllValidWorkerFieldsP1();
        saveAllValidWorkerFieldsP2();

        if (movingWorker.getId() == circle1.getId() || movingWorker.getId() == circle2.getId()) {
            if (allValidFieldsPlayer1.isEmpty() && !allValidFieldsPlayer2.isEmpty()) {
                gameManager.checkWinConditionSurroundingP2(player2);
            } else if (!allValidFieldsPlayer1.isEmpty() && allValidFieldsPlayer2.isEmpty()) {
                gameManager.checkWinConditionSurroundingP2(player1);
            }
        } else if (movingWorker.getId() == circle3.getId() || movingWorker.getId() == circle4.getId()) {
            if (allValidFieldsPlayer1.isEmpty() && !allValidFieldsPlayer2.isEmpty()) {
                gameManager.checkWinConditionSurroundingP2(player2);
            } else if (!allValidFieldsPlayer1.isEmpty() && allValidFieldsPlayer2.isEmpty()) {
                gameManager.checkWinConditionSurroundingP2(player1);

            }
        }
    }

    boolean isOnePlayerOut = false;
    /**
     * Clear & Save valid fields per player in a list for 3-Player-Version and determines the player, for which the surrounding win condition is checked
     * in the gamemanager
     */
    public void handleWinningActionsSurroundingP3() {
        //handle win condition with surrounding the opponent - 3 Players
        allValidFieldsPlayer1.clear();
        allValidFieldsPlayer2.clear();
        allValidFieldsPlayer3.clear();
        List<Circle> expandedCircles = new ArrayList<>();

        if (expandedCircles.size() != 0) {
            gamefield.getChildren().removeAll(expandedCircles);

        }
        if (circle1 != null) {
            saveAllValidWorkerFieldsP1();
        }
        if (circle3 != null) {
            saveAllValidWorkerFieldsP2();
        }
        if (circle5 != null) {
            saveAllValidWorkerFieldsP3();
        }

        System.out.println("allValidFieldsPlayer1.isEmpty() " + allValidFieldsPlayer1.isEmpty());
        System.out.println("allValidFieldsPlayer2.isEmpty() " + allValidFieldsPlayer2.isEmpty());
        System.out.println("allValidFieldsPlayer3.isEmpty() " + allValidFieldsPlayer3.isEmpty());


        if(movingWorker.getId() == circle1.getId() || movingWorker.getId() == circle2.getId() || movingWorker.getId() == circle7.getId()) {
            if(allValidFieldsPlayer1.isEmpty() && allValidFieldsPlayer2.isEmpty() && !allValidFieldsPlayer3.isEmpty()) {
                gameManager.checkWinConditionSurroundingP3(player3);
                switchToStartAfterWinning();
            } else if(!allValidFieldsPlayer1.isEmpty() && allValidFieldsPlayer2.isEmpty() && allValidFieldsPlayer3.isEmpty()) {
                gameManager.checkWinConditionSurroundingP3(player1);
                switchToStartAfterWinning();
            } else if(allValidFieldsPlayer1.isEmpty() && !allValidFieldsPlayer2.isEmpty() && allValidFieldsPlayer3.isEmpty()) {
                gameManager.checkWinConditionSurroundingP3(player2);
                switchToStartAfterWinning();
            }
        } else if (movingWorker.getId() == circle3.getId() || movingWorker.getId() == circle4.getId() || movingWorker.getId() == circle8.getId()) {
            if(allValidFieldsPlayer1.isEmpty() && allValidFieldsPlayer2.isEmpty() && !allValidFieldsPlayer3.isEmpty()) {
                gameManager.checkWinConditionSurroundingP3(player3);
                switchToStartAfterWinning();
            } else if(!allValidFieldsPlayer1.isEmpty() && allValidFieldsPlayer2.isEmpty() && allValidFieldsPlayer3.isEmpty()) {
                gameManager.checkWinConditionSurroundingP3(player1);
                switchToStartAfterWinning();
            } else if(allValidFieldsPlayer1.isEmpty() && !allValidFieldsPlayer2.isEmpty() && allValidFieldsPlayer3.isEmpty()) {
                gameManager.checkWinConditionSurroundingP3(player2);
                switchToStartAfterWinning();
            }
        } else if (movingWorker.getId() == circle5.getId() || movingWorker.getId() == circle6.getId() || movingWorker.getId() == circle9.getId()) {
            if(allValidFieldsPlayer1.isEmpty() && allValidFieldsPlayer2.isEmpty() && !allValidFieldsPlayer3.isEmpty()) {
                gameManager.checkWinConditionSurroundingP3(player3);
                switchToStartAfterWinning();
            } else if(!allValidFieldsPlayer1.isEmpty() && allValidFieldsPlayer2.isEmpty() && allValidFieldsPlayer3.isEmpty()) {
                gameManager.checkWinConditionSurroundingP3(player1);
                switchToStartAfterWinning();
            } else if(allValidFieldsPlayer1.isEmpty() && !allValidFieldsPlayer2.isEmpty() && allValidFieldsPlayer3.isEmpty()) {
                gameManager.checkWinConditionSurroundingP3(player2);
                switchToStartAfterWinning();
            }

        }

        if (allValidFieldsPlayer1.isEmpty()) {
            if(is1Worker){
                gamefield.getChildren().remove(circle1);
                expandedCircles.add(circle1);
                circle1 = null;

            }else if(is2Worker){
                gamefield.getChildren().removeAll(circle1, circle2);
                expandedCircles.add(circle1);
                expandedCircles.add(circle2);
                circle1 = null;
                circle2 = null;
            }

            if(is3Worker) {
                gamefield.getChildren().removeAll(circle1, circle2, circle7);
                expandedCircles.add(circle1);
                expandedCircles.add(circle2);
                expandedCircles.add(circle7);
                circle1 = null;
                circle2 = null;
                circle7 = null;
            }
            if (playerInTurn == player1) {
                switchTurn();
            }
            isOnePlayerOut = true;
        } else if (allValidFieldsPlayer2.isEmpty()) {

            if(is1Worker){
                gamefield.getChildren().remove(circle3);
                expandedCircles.add(circle3);
                circle3 = null;

            }else if(is2Worker){
                gamefield.getChildren().removeAll(circle3, circle4);
                expandedCircles.add(circle3);
                expandedCircles.add(circle4);

                circle3 = null;
                circle4 = null;
            }

            if (is3Worker) {
                gamefield.getChildren().removeAll(circle3, circle4,circle8);
                expandedCircles.add(circle3);
                expandedCircles.add(circle4);
                expandedCircles.add(circle8);

                circle3 = null;
                circle4 = null;
                circle8 = null;

            }
            if (playerInTurn == player2) {
                switchTurn();

            }

            isOnePlayerOut = true;
        } else if (allValidFieldsPlayer3.isEmpty()) {
            if(is1Worker){
                gamefield.getChildren().remove(circle5);
                expandedCircles.add(circle5);
                circle5 = null;

            }else if(is2Worker){
                gamefield.getChildren().removeAll(circle5, circle6);
                expandedCircles.add(circle5);
                expandedCircles.add(circle6);

                circle5 = null;
                circle6 = null;
            }

            if (is3Worker) {
                gamefield.getChildren().removeAll(circle5, circle6,circle9);
                expandedCircles.add(circle5);
                expandedCircles.add(circle6);
                expandedCircles.add(circle9);
                circle5 = null;
                circle6 = null;
                circle9 = null;
            }
            if (playerInTurn == player3) {
                switchTurn();
            }

            isOnePlayerOut = true;
        }
    }



    /**
     * to manage all actions that occur after a stone is clicked
     *
     * @param clickedNode
     * @return
     */
    public boolean clickStone(Node clickedNode) {
        Rectangle clickedRectangle = (Rectangle) clickedNode;
        isDom=false;
        int colIndex = GridPane.getColumnIndex(clickedRectangle);
        int rowIndex = GridPane.getRowIndex(clickedRectangle);

        if (!stonePoolsSeparated) {

            switch (clickCount[rowIndex][colIndex]) {
                case 0:
                    updateStone(clickedRectangle, rowIndex, colIndex, stone1);
                    break;
                case 1:
                    updateStone(clickedRectangle, rowIndex, colIndex, stone2);
                    break;
                case 2:
                    if (heightToWin == 2) {
                        updateStone(clickedRectangle, rowIndex, colIndex, stone4);
                    } else {
                        updateStone(clickedRectangle, rowIndex, colIndex, stone3);
                    }
                    break;
                case 3:
                    if (heightToWin == 2) {
                        if(LanguageManager.getCurrentLanguage() == "en") {
                            showAlert("It´s already a dome");
                        } else if(LanguageManager.getCurrentLanguage() == "de") {
                            showAlert("Es ist bereits eine Kuppel");
                        }
                        isDom = true;
                        break;
                    } else if (heightToWin == 3) {
                        updateStone(clickedRectangle, rowIndex, colIndex, stone4);
                    } else if (heightToWin == 4 || heightToWin == 5) {
                        updateStone(clickedRectangle, rowIndex, colIndex, stoneLevel4);
                    }
                    break;
                case 4:
                    if (heightToWin == 3) {
                        if(LanguageManager.getCurrentLanguage() == "en") {
                            showAlert("It´s already a dome");
                        } else if(LanguageManager.getCurrentLanguage() == "de") {
                            showAlert("Es ist bereits eine Kuppel");
                        }
                        isDom = true;
                        break;

                    } else if (heightToWin == 4) {
                        updateStone(clickedRectangle, rowIndex, colIndex, stone4);
                    } else if (heightToWin == 5) {
                        updateStone(clickedRectangle, rowIndex, colIndex, stoneLevel5);
                    }
                    break;
                case 5:
                    if (heightToWin == 4) {
                        if(LanguageManager.getCurrentLanguage() == "en") {
                            showAlert("It´s already a dome");
                        } else if(LanguageManager.getCurrentLanguage() == "de") {
                            showAlert("Es ist bereits eine Kuppel");
                        }
                        isDom = true;
                    } else if (heightToWin == 5) {
                        updateStone(clickedRectangle, rowIndex, colIndex, stone4);
                    }
                    break;
                case 6:
                    if (heightToWin == 5) {
                        if(LanguageManager.getCurrentLanguage() == "en") {
                            showAlert("It´s already a dome");
                        } else if(LanguageManager.getCurrentLanguage() == "de") {
                            showAlert("Es ist bereits eine Kuppel");
                        }
                        isDom = true;
                    }
                default:
                    if(LanguageManager.getCurrentLanguage() == "en") {
                        showAlert("It´s already a dome");
                    } else if(LanguageManager.getCurrentLanguage() == "de") {
                        showAlert("Es ist bereits eine Kuppel");
                    }
                    isDom = true;
                    break;
            }

            // if there is one stone pool per Player
        } else if (stonePoolsSeparated) {

            //if player1 is in turn
            if (playerInTurn == player1) {
                switch (clickCount[rowIndex][colIndex]) {
                    case 0:
                        updateStone(clickedRectangle, rowIndex, colIndex, stone1P1);
                        break;
                    case 1:
                        updateStone(clickedRectangle, rowIndex, colIndex, stone2P1);
                        break;
                    case 2:
                        if (heightToWin == 2) {
                            updateStone(clickedRectangle, rowIndex, colIndex, stone4P1);
                        } else {
                            updateStone(clickedRectangle, rowIndex, colIndex, stone3P1);
                        }
                        break;
                    case 3:
                        if (heightToWin == 2) {
                            if(LanguageManager.getCurrentLanguage() == "en") {
                                showAlert("It´s already a dome");
                            } else if(LanguageManager.getCurrentLanguage() == "de") {
                                showAlert("Es ist bereits eine Kuppel");
                            }
                            isDom = true;

                            break;
                        } else if (heightToWin == 3) {
                            updateStone(clickedRectangle, rowIndex, colIndex, stone4P1);
                        } else if (heightToWin == 4 || heightToWin == 5) {
                            updateStone(clickedRectangle, rowIndex, colIndex, stoneLevel4P1);
                        }
                        break;
                    case 4:
                        if (heightToWin == 3) {
                            if(LanguageManager.getCurrentLanguage() == "en") {
                                showAlert("It´s already a dome");
                            } else if(LanguageManager.getCurrentLanguage() == "de") {
                                showAlert("Es ist bereits eine Kuppel");
                            }
                            isDom = true;

                            break;

                        } else if (heightToWin == 4) {
                            updateStone(clickedRectangle, rowIndex, colIndex, stone4P1);
                        } else if (heightToWin == 5) {
                            updateStone(clickedRectangle, rowIndex, colIndex, stoneLevel5P1);
                        }
                        break;
                    case 5:
                        if (heightToWin == 4) {
                            if(LanguageManager.getCurrentLanguage() == "en") {
                                showAlert("It´s already a dome");
                            } else if(LanguageManager.getCurrentLanguage() == "de") {
                                showAlert("Es ist bereits eine Kuppel");
                            }
                            isDom = true;

                        } else if (heightToWin == 5) {
                            updateStone(clickedRectangle, rowIndex, colIndex, stone4P1);
                        }
                        break;
                    case 6:
                        if (heightToWin == 5) {
                            if(LanguageManager.getCurrentLanguage() == "en") {
                                showAlert("It´s already a dome");
                            } else if(LanguageManager.getCurrentLanguage() == "de") {
                                showAlert("Es ist bereits eine Kuppel");
                            }
                            isDom = true;

                        }
                    default:
                        if(LanguageManager.getCurrentLanguage() == "en") {
                            showAlert("It´s already a dome");
                        } else if(LanguageManager.getCurrentLanguage() == "de") {
                            showAlert("Es ist bereits eine Kuppel");
                        }
                        isDom = true;

                        break;
                }
                //if player2 is in turn
            } else if (playerInTurn == player2) {
                    switch (clickCount[rowIndex][colIndex]) {
                        case 0:
                            updateStone(clickedRectangle, rowIndex, colIndex, stone1P2);
                            break;
                        case 1:
                            updateStone(clickedRectangle, rowIndex, colIndex, stone2P2);
                            break;
                        case 2:
                            if (heightToWin == 2) {
                                updateStone(clickedRectangle, rowIndex, colIndex, stone4P2);
                            } else {
                                updateStone(clickedRectangle, rowIndex, colIndex, stone3P2);
                            }
                            break;
                        case 3:
                            if (heightToWin == 2) {
                                if(LanguageManager.getCurrentLanguage() == "en") {
                                    showAlert("It´s already a dome");
                                } else if(LanguageManager.getCurrentLanguage() == "de") {
                                    showAlert("Es ist bereits eine Kuppel");
                                }
                                isDom = true;

                                break;
                            } else if (heightToWin == 3) {
                                updateStone(clickedRectangle, rowIndex, colIndex, stone4P2);
                            } else if (heightToWin == 4 || heightToWin == 5) {
                                updateStone(clickedRectangle, rowIndex, colIndex, stoneLevel4P2);
                            }
                            break;
                        case 4:
                            if (heightToWin == 3) {
                                if(LanguageManager.getCurrentLanguage() == "en") {
                                    showAlert("It´s already a dome");
                                } else if(LanguageManager.getCurrentLanguage() == "de") {
                                    showAlert("Es ist bereits eine Kuppel");
                                }
                                isDom = true;

                                break;

                            } else if (heightToWin == 4) {
                                updateStone(clickedRectangle, rowIndex, colIndex, stone4P2);
                            } else if (heightToWin == 5) {
                                updateStone(clickedRectangle, rowIndex, colIndex, stoneLevel5P2);
                            }
                            break;
                        case 5:
                            if (heightToWin == 4) {
                                if(LanguageManager.getCurrentLanguage() == "en") {
                                    showAlert("It´s already a dome");
                                } else if(LanguageManager.getCurrentLanguage() == "de") {
                                    showAlert("Es ist bereits eine Kuppel");
                                }
                                isDom = true;

                            } else if (heightToWin == 5) {
                                updateStone(clickedRectangle, rowIndex, colIndex, stone4P2);
                            }
                            break;
                        case 6:
                            if (heightToWin == 5) {
                                if(LanguageManager.getCurrentLanguage() == "en") {
                                    showAlert("It´s already a dome");
                                } else if(LanguageManager.getCurrentLanguage() == "de") {
                                    showAlert("Es ist bereits eine Kuppel");
                                }
                                isDom = true;

                            }
                        default:
                            if(LanguageManager.getCurrentLanguage() == "en") {
                                showAlert("It´s already a dome");
                            } else if(LanguageManager.getCurrentLanguage() == "de") {
                                showAlert("Es ist bereits eine Kuppel");
                            }
                            isDom = true;

                            break;
                    }
                    //if player3 is in turn
                } else if (playerInTurn == player3) {
                    switch (clickCount[rowIndex][colIndex]) {
                        case 0:
                            updateStone(clickedRectangle, rowIndex, colIndex, stone1P3);
                            break;
                        case 1:
                            updateStone(clickedRectangle, rowIndex, colIndex, stone2P3);
                            break;
                        case 2:
                            if (heightToWin == 2) {
                                updateStone(clickedRectangle, rowIndex, colIndex, stone4P3);
                            } else {
                                updateStone(clickedRectangle, rowIndex, colIndex, stone3P3);
                            }
                            break;
                        case 3:
                            if (heightToWin == 2) {
                                if(LanguageManager.getCurrentLanguage() == "en") {
                                    showAlert("It´s already a dome");
                                } else if(LanguageManager.getCurrentLanguage() == "de") {
                                    showAlert("Es ist bereits eine Kuppel");
                                }
                                isDom = true;

                                break;
                            } else if (heightToWin == 3) {
                                updateStone(clickedRectangle, rowIndex, colIndex, stone4P3);
                            } else if (heightToWin == 4 || heightToWin == 5) {
                                updateStone(clickedRectangle, rowIndex, colIndex, stoneLevel4P3);
                            }
                            break;
                        case 4:
                            if (heightToWin == 3) {
                                if(LanguageManager.getCurrentLanguage() == "en") {
                                    showAlert("It´s already a dome");
                                } else if(LanguageManager.getCurrentLanguage() == "de") {
                                    showAlert("Es ist bereits eine Kuppel");
                                }
                                isDom = true;

                                break;

                            } else if (heightToWin == 4) {
                                updateStone(clickedRectangle, rowIndex, colIndex, stone4P3);
                            } else if (heightToWin == 5) {
                                updateStone(clickedRectangle, rowIndex, colIndex, stoneLevel5P3);
                            }
                            break;
                        case 5:
                            if (heightToWin == 4) {
                                if(LanguageManager.getCurrentLanguage() == "en") {
                                    showAlert("It´s already a dome");
                                } else if(LanguageManager.getCurrentLanguage() == "de") {
                                    showAlert("Es ist bereits eine Kuppel");
                                }
                                isDom = true;

                            } else if (heightToWin == 5) {
                                updateStone(clickedRectangle, rowIndex, colIndex, stone4P3);
                            }
                            break;
                        case 6:
                            if (heightToWin == 5) {
                                if(LanguageManager.getCurrentLanguage() == "en") {
                                    showAlert("It´s already a dome");
                                } else if(LanguageManager.getCurrentLanguage() == "de") {
                                    showAlert("Es ist bereits eine Kuppel");
                                }
                                isDom = true;

                            }
                        default:
                            if(LanguageManager.getCurrentLanguage() == "en") {
                                showAlert("It´s already a dome");
                            } else if(LanguageManager.getCurrentLanguage() == "de") {
                                showAlert("Es ist bereits eine Kuppel");
                            }
                            isDom = true;

                            break;
                    }

                }
        }
        return isDom;
    }


    /**
     * change each player's turn after built stone
     */
    public void switchTurn1() {
        player1.setHasTurn(!player1.isHasTurn());
        player2.setHasTurn(!player2.isHasTurn());
        playerInTurn.setMoved(false);
        setNewStatus1(player1, player2);

        if (playerInTurn.isCom() && gameManager.getWinnerExists() == false) {
            aiAction(playerInTurn);
        } else {
            switchToStartAfterWinning();
        }
    }

    /**
     * To paint the worker circles dynamically to the chosen color in the game settings
     *
     * @param player1
     * @param player2
     */
    private void setNewStatus1(Player player1, Player player2) {
        /**
         * update who has turn
         */
        playerInTurn = player1.isHasTurn() ? player1 : player2;
        System.out.println("================================================================================================");
        System.out.println("\n" + playerInTurn.getName() + " has the turn.");
    }

    public void paintWorkerAndPlayer1(){
        /**
         * Workers who do not belong to the player who is in turn are painted gray
         * the glow of player who is not in turn is removed
         */
        if (playerInTurn == player1) {
            colorPlayer1.setEffect(getGlow());
            circle1.setFill(player1.getColor());
            circle2.setFill(player1.getColor());
            circle1.setStyle("-fx-opacity: 1;");
            colorPlayer1.setStyle("-fx-opacity: 1;");
            circle2.setStyle("-fx-opacity: 1;");

            colorPlayer2.setStyle("-fx-opacity: 0.3;");
            circle3.setStyle("-fx-opacity: 0.3;");
            circle4.setStyle("-fx-opacity: 0.3;");
            colorPlayer2.setEffect(null);

            if (is3Worker) {
                circle7.setFill(player1.getColor());
                circle7.setStyle("-fx-opacity: 1;");

                circle8.setStyle("-fx-opacity: 0.3;");
            }

        } else if (playerInTurn == player2) {
            colorPlayer2.setEffect(getGlow());
            circle3.setFill(player2.getColor());
            circle4.setFill(player2.getColor());
            colorPlayer2.setStyle("-fx-opacity: 1;");
            circle3.setStyle("-fx-opacity: 1;");
            circle4.setStyle("-fx-opacity: 1;");

            colorPlayer1.setStyle("-fx-opacity: 0.3;");
            circle1.setStyle("-fx-opacity: 0.3;");
            circle2.setStyle("-fx-opacity: 0.3;");
            colorPlayer1.setEffect(null);

            if (is3Worker) {
                circle8.setFill(player2.getColor());
                circle8.setStyle("-fx-opacity: 1;");

                circle7.setStyle("-fx-opacity: 0.3;");
            }
        }
    }
    /**
     * Handles AI action for the bot player.
     *
     * @param bot The bot player for which the AI action is executed.
     */
    public void aiAction(Player bot) {
        Platform.runLater(() -> {

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // The thread will be interrupted if InterruptedException occurs, and the thread will exit.
                return;
            }
            // Action in initial turn
            if (bot.getTurnAmount() == 0) {
                // Place workers in the initial turn
                int row, col;

                do {
                    row = random.nextInt(fieldsize);
                    col = random.nextInt(fieldsize);
                } while (occupiedPositions[row][col]);

                int count = bot.getInitialMove() * 2;

                // Add worker to the GridPane
                gamefield.add(getCircleOfWorker(bot.getWorkers().get(bot.getInitialMove() - 1)), col, row);
                bot.getWorkers().get(bot.getInitialMove() - 1).setWorkerPosition(row, col);
                GridPane.setHalignment(getCircleOfWorker(bot.getWorkers().get(bot.getInitialMove() - 1)), javafx.geometry.HPos.CENTER);

                count -= 1;
                bot.setInitialMove(bot.getInitialMove() - 1);
                // set the occupied field as true
                occupiedPositions[row][col] = true;

                if (count == 1) {
                    playerInTurn.setTurnAmount(playerInTurn.getTurnAmount() + 1);

                    String newCount = String.valueOf(playerInTurn.getTurnAmount());
                    if (playerInTurn == player1) {
                        playerCount1.setText(newCount);
                    } else if (playerInTurn == player2) {
                        playerCount2.setText(newCount);
                    } else {
                        playerCount3.setText(newCount);
                    }
                    System.out.println("Initial turn ended");
                }

                if (is3Player) {
                    switchTurn();
                    paintWorkerAndPlayer();

                } else {
                    switchTurn1();
                    paintWorkerAndPlayer1();
                }
            } else {

            /**
             *  Action after initial turn
              */
                List<Rectangle> validFieldsToWin = new ArrayList<>();

                List<Rectangle> highestValidFieldsToMove = new ArrayList<>();
                List<Rectangle> validFieldsToMoveEnemies = new ArrayList<>();
                List<Rectangle> validFieldToDisturb = new ArrayList<>();

                List<Player> enemies = new ArrayList<>();
                Player enemy = null;

                //save enemies
                if (is3Player) {
                    if (player1.equals(bot)) {
                        enemies.add(player2);
                        enemies.add(player3);
                    } else if (player2.equals(bot)) {
                        enemies.add(player1);
                        enemies.add(player3);
                    } else if (player3.equals(bot)) {
                        enemies.add(player1);
                        enemies.add(player2);
                    }
                    for(Player eP : enemies) {
                        for(Worker w : eP.getWorkers()){
                            if(is1Worker) {
                                if (enemy == player1) {
                                    saveValidFields(circle1);
                                    validFieldsToMoveEnemies.addAll(validFieldsToMoveTo);
                                } else if (enemy == player2) {
                                    saveValidFields(circle3);
                                    validFieldsToMoveEnemies.addAll(validFieldsToMoveTo);
                                } else if (enemy == player3) {
                                    saveValidFields(circle5);
                                    validFieldsToMoveEnemies.addAll(validFieldsToMoveTo);
                                }
                            }else{
                                saveValidFields(getCircleOfWorker(w));
                                validFieldsToMoveEnemies.addAll(validFieldsToMoveTo);
                            }
                        }
                    }

                }else {
                    enemy = (bot == player1) ? player2 : player1;
                    if(is1Worker){
                        if(enemy ==player1){
                            saveValidFields(circle1);
                            validFieldsToMoveEnemies.addAll(validFieldsToMoveTo);
                        }else if(enemy ==player2){
                            saveValidFields(circle3);
                            validFieldsToMoveEnemies.addAll(validFieldsToMoveTo);
                        }else if(enemy ==player3){
                            saveValidFields(circle5);
                            validFieldsToMoveEnemies.addAll(validFieldsToMoveTo);
                        }
//                        saveValidFields(getCircleOfWorker(enemy.getWorkers().get(0)));
//                        validFieldsToMoveEnemies.addAll(validFieldsToMoveTo);

                    }else{
                        for(Worker w : enemy.getWorkers()){
                            saveValidFields(getCircleOfWorker(w));
                            validFieldsToMoveEnemies.addAll(validFieldsToMoveTo);
                        }
                    }
                }

                // Move workers
                int size = 0;
                if (is2Worker) {
                    size = 2;
                } else if (is3Worker) {
                    size = 3;
                } else if (is1Worker) {
                    size = 1;
                }
                int nm = random.nextInt(size);

                //Worker to move
                Worker tmp = null;
                Worker tmp2 = playerInTurn.getWorkers().get(nm);

                // List of the highest level of workers
                List<Integer> fieldLevel = new ArrayList<>();
                //save the highest field to move based on the current worker's location
                if (aiLevel == 2) {

                    int highestLevel = 0;
                    boolean noCheck = false;
                    /**
                     * Move
                     **/

                    for (Worker w : bot.getWorkers()) {
                        boolean shouldStop = false;

                        if(is1Worker){
                            if(bot ==player1){
                                saveValidFields(circle1);
                            }else if(bot ==player2){
                                saveValidFields(circle3);
                            }else if(bot ==player3){
                                saveValidFields(circle5);
                            }
                        }else{
                            saveValidFields(getCircleOfWorker(w));
                        }

                        // find the field with the height to win
                        if(!noCheck){
                            for (Rectangle field : validFieldsToMoveTo) {
                                int level = clickCount[GridPane.getRowIndex(field)][GridPane.getColumnIndex(field)];
                                if (level == heightToWin) {
                                    validFieldsToWin.add(0, field);
                                    tmp = w;
                                    noCheck = true;
                                    break;
                                }
                            }
                        }

                        // If there is no field with the height to win, choose a field to disturb enemy workers
                        if(validFieldsToWin.size() == 0 && validFieldToDisturb.size()==0){
                            for (Rectangle field : validFieldsToMoveTo) {
                                Circle circle = new Circle(1);
                                gamefield.add(circle, GridPane.getColumnIndex(field), GridPane.getRowIndex(field));
                                saveValidFieldsInAi2(circle);
                                gamefield.getChildren().remove(circle);

                                List<Rectangle> checkBeforeMove = new ArrayList<>();

                                for (Rectangle nextField : validFieldsToBuildAI2) {
                                    int level = clickCount[GridPane.getRowIndex(nextField)][GridPane.getColumnIndex(nextField)];

                                    if ((level == heightToWin && validFieldsToMoveEnemies.contains(field))
                                            || (level == heightToWin - 1 && validFieldsToMoveEnemies.contains(field))) {
                                        checkBeforeMove.add(field);
                                    }
                                }

                                // if there are just fields with level heightToWin - 1 or heightToWin skip this iteration
                                if(checkBeforeMove.size() == validFieldsToBuildAI2.size()){

                                }else{

                                    for(Rectangle nextField : validFieldsToBuildAI2){
                                        int level = clickCount[GridPane.getRowIndex(nextField)][GridPane.getColumnIndex(nextField)];
                                        if (level==heightToWin && validFieldsToMoveEnemies.contains(nextField)) {

                                            validFieldToDisturb.add(0, field);
                                            validFieldToDisturb.add(1, nextField);
                                            tmp = w;
                                            shouldStop = true;
                                            break;
                                        }
                                    }
                                }
                                if(shouldStop){
                                    break;
                                }
                            }

                        //save the highest level of each worker
                        for (Rectangle field : validFieldsToMoveTo) {
                            int level = clickCount[GridPane.getRowIndex(field)][GridPane.getColumnIndex(field)];
                            if (level > highestLevel && level <= heightToWin) {
                                highestLevel = level;
                            }
                        }
                            fieldLevel.add(highestLevel);
                        }
                    }

                    //If there is a field with winheight, go to it
                    if(validFieldsToWin.size()!=0) {
                        int row = GridPane.getRowIndex(validFieldsToWin.get(0));
                        int col = GridPane.getColumnIndex(validFieldsToWin.get(0));
                        GridPane.setRowIndex(getCircleOfWorker(tmp), row);
                        GridPane.setColumnIndex(getCircleOfWorker(tmp), col);

                    //If there is a field to disturb enemy to win, go to it
                    }else if(validFieldToDisturb.size() !=0) {

                        int row = GridPane.getRowIndex(validFieldToDisturb.get(0));
                        int col = GridPane.getColumnIndex(validFieldToDisturb.get(0));
                        GridPane.setRowIndex(getCircleOfWorker(tmp), row );
                        GridPane.setColumnIndex(getCircleOfWorker(tmp), col);

                    //check which worker can move to the highest field
                    }else if(validFieldToDisturb.size() == 0){
                        int maxNumber = Integer.MIN_VALUE;
                        int maxNumberIndex = -1;

                        for (int i = 0; i < fieldLevel.size(); i++) {
                            int currentNumber = fieldLevel.get(i);
                            if (currentNumber > maxNumber) {
                                maxNumber = currentNumber;
                                maxNumberIndex = i;
                            }
                        }
                        tmp = bot.getWorkers().get(maxNumberIndex);


                        //determine the highest valid field to move
                        for (Rectangle field : saveValidFields(getCircleOfWorker(tmp))) {
                            int level = clickCount[GridPane.getRowIndex(field)][GridPane.getColumnIndex(field)];
                            if (level == highestLevel) {
                                highestValidFieldsToMove.add(field);
                            }
                        }
                        //if there is no field to move than add the field with same height
                        if(highestValidFieldsToMove.size() == 0){
                            for (Rectangle field : saveValidFields(getCircleOfWorker(tmp))) {
                                int level = clickCount[GridPane.getRowIndex(field)][GridPane.getColumnIndex(field)];
                                if (level == tmp.getHeightWorker()) {
                                    highestValidFieldsToMove.add(field);
                                }
                            }
                        }
                        //if there is no field to move than add the field with lower height
                        if(highestValidFieldsToMove.size() == 0){
                            for (Rectangle field : saveValidFields(getCircleOfWorker(tmp))) {
                                int level = clickCount[GridPane.getRowIndex(field)][GridPane.getColumnIndex(field)];
                                if (level < tmp.getHeightWorker()) {
                                    highestValidFieldsToMove.add(field);
                                }
                            }
                        }
                        //choose one random valid field to move
                        int randomIndex2 = random.nextInt(highestValidFieldsToMove.size());
                        Rectangle selectedRectangle2 = highestValidFieldsToMove.get(randomIndex2);
                        int row = GridPane.getRowIndex(selectedRectangle2);
                        int col = GridPane.getColumnIndex(selectedRectangle2);
                        GridPane.setRowIndex(getCircleOfWorker(tmp), row);
                        GridPane.setColumnIndex(getCircleOfWorker(tmp), col);
                    }

                    workerInTurn = tmp;
                    movingWorker = getCircleOfWorker(workerInTurn);

                    saveWorkerHeight(getCircleOfWorker(workerInTurn));

                } else if (aiLevel == 1) {

                    int highestLevel = 0;
                    //choose random worker of the player
                    tmp2 = bot.getWorkers().get(nm);

                    // Move
                    for (Worker w : bot.getWorkers()) {
                        if(is1Worker){
                            if(bot ==player1){
                                saveValidFields(circle1);
                            }else if(bot ==player2){
                                saveValidFields(circle3);
                            }else if(bot ==player3){
                                saveValidFields(circle5);
                            }
                        }else{
                            saveValidFields(getCircleOfWorker(w));
                        }

                        for (Rectangle field : validFieldsToMoveTo) {
                            int level = clickCount[GridPane.getRowIndex(field)][GridPane.getColumnIndex(field)];
                            if (level > highestLevel && level <= heightToWin) {
                                highestLevel = level;
                            }
                        }

                        fieldLevel.add(highestLevel);
                    }

                    int maxNumber = Integer.MIN_VALUE;
                    int maxNumberIndex = -1;

                    for (int i = 0; i < fieldLevel.size(); i++) {
                        int currentNumber = fieldLevel.get(i);
                        if (currentNumber > maxNumber) {
                            maxNumber = currentNumber;
                            maxNumberIndex = i;
                        }
                    }
                     tmp = bot.getWorkers().get(maxNumberIndex);


                    //determine the highest valid field to move
                    for (Rectangle field : saveValidFields(getCircleOfWorker(tmp))) {
                        int level = clickCount[GridPane.getRowIndex(field)][GridPane.getColumnIndex(field)];
                        if (level == highestLevel) {
                            highestValidFieldsToMove.add(field);
                        }
                    }
                    //choose one highest field to move
                    int randomIndex2 = random.nextInt(highestValidFieldsToMove.size());
                    Rectangle selectedRectangle2 = highestValidFieldsToMove.get(randomIndex2);
                    int row = GridPane.getRowIndex(selectedRectangle2);
                    int col = GridPane.getColumnIndex(selectedRectangle2);
                    GridPane.setRowIndex(getCircleOfWorker(tmp), row);
                    GridPane.setColumnIndex(getCircleOfWorker(tmp), col);

                    workerInTurn = tmp;
                    movingWorker = getCircleOfWorker(workerInTurn);

                    saveWorkerHeight(getCircleOfWorker(workerInTurn));

                /**
                 * Move ended
                 **/
            }
                if( handleWinningActionsHeight()|| aiSelected){


                }else{
                    List<Rectangle> buildToDisturb = new ArrayList<>();
                    List<Rectangle> validFieldsToBuildOn = new ArrayList<>();
                    List<Rectangle> toRemove = new ArrayList<>();

                    /**
                     * Build
                     **/

                    //delay the build action after move
                    PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(event -> {
                        saveValidFields(movingWorker);

                        /**
                         * interfere  enemies if ai level is 2
                         */
                        if (aiLevel == 2) {
                            int highestLevel2 = 0;

                            /**
                             * Save all the opponent's valid fields that has the winning height and is included in my worker's valid field
                             **/

                            for (Rectangle field : validFieldsToBuild) {
                                int level = clickCount[GridPane.getRowIndex(field)][GridPane.getColumnIndex(field)];
                                if (level == heightToWin && validFieldsToMoveEnemies.contains(field)) {
                                    buildToDisturb.add(0, field);
                                    break;
                                }
                            }
                            //if to disturb opponent is not necessary, add the field with same height as the current worker
                            if(buildToDisturb.size()==0){
                                for (Rectangle field : validFieldsToBuild) {
                                    int level = clickCount[GridPane.getRowIndex(field)][GridPane.getColumnIndex(field)];
                                    if (level == workerHeight) {
                                        validFieldsToBuildOn.add(field);
                                    }
                                }
                                //remove the stone which has the level of heightToWin -1 and valid for the enemy
                                for (Rectangle field : validFieldsToBuildOn) {
                                    int level = clickCount[GridPane.getRowIndex(field)][GridPane.getColumnIndex(field)];
                                    if ((level + 1) == heightToWin && validFieldsToMoveEnemies.contains(field)) {
                                        toRemove.add(field);
                                    }
                                }

                                if (toRemove.size() > 0) {
                                    validFieldsToBuildOn.removeAll(toRemove);
                                }
                                //if there is no field to build, add the field with the lower height as the current worker
                                if (validFieldsToBuildOn.size() == 0) {
                                    for (Rectangle field : validFieldsToBuild) {
                                        int level = clickCount[GridPane.getRowIndex(field)][GridPane.getColumnIndex(field)];
                                        if (level < workerHeight) {
                                            validFieldsToBuildOn.add(field);
                                        }
                                    }
                                    for (Rectangle field : validFieldsToBuildOn) {
                                        int level = clickCount[GridPane.getRowIndex(field)][GridPane.getColumnIndex(field)];
                                        if ((level + 1) == heightToWin && validFieldsToMoveEnemies.contains(field)) {
                                            toRemove.add(field);
                                        }
                                    }
                                    if (toRemove.size() > 0) {
                                        validFieldsToBuildOn.removeAll(toRemove);
                                    }
                                }
                            }

                        //ai level 1, Select any field to build
                        } else {

                            int highestLevel2 = 0;
                            for (Rectangle field : validFieldsToBuild) {
                                int level = clickCount[GridPane.getRowIndex(field)][GridPane.getColumnIndex(field)];
                                if (level > highestLevel2 && level < heightToWin) {
                                    highestLevel2 = level;
                                }
                            }

                            for (Rectangle field : validFieldsToBuild) {
                                int level = clickCount[GridPane.getRowIndex(field)][GridPane.getColumnIndex(field)];
                                if (level == highestLevel2) {
                                    validFieldsToBuildOn.add(field);
                                }
                            }
                        }

                        int randomIndex3 =0;
                        //choose one field to build
                        Rectangle selectedRectangle3 = null;

                        if(buildToDisturb.size() > 0){
                            selectedRectangle3 = buildToDisturb.get(0);
                        }else{
                            randomIndex3 = random.nextInt(validFieldsToBuildOn.size());
                            selectedRectangle3 = validFieldsToBuildOn.get(randomIndex3);
                        }

                        clickStone(selectedRectangle3);

                        if (is3Player) {
                            switchTurn();
                            paintWorkerAndPlayer();
                            handleWinningActionsSurroundingP3();

                        } else {
                            switchTurn1();
                            paintWorkerAndPlayer1();
                            handleWinningActionsSurroundingP2();
                        }
                    });
                    pause.play();
                }
            }
        });
    }

    /**
     * To paint the worker circles dynamically to the chosen color in the game settings
     *
     * @param player1
     * @param player2
     * @param player3
     */
    private void setNewStatus(Player player1, Player player2, Player player3) {

        /**
         * update who has turn
         */
        if (player1.isHasTurn()) {
            playerInTurn = player1;
        } else if (player2.isHasTurn()) {
            playerInTurn = player2;
        } else if (player3.isHasTurn()) {
            playerInTurn = player3;
        }

        System.out.println("playerInTurn : " + playerInTurn.getName());

    }
    // Painting for is3Player
    public void paintWorkerAndPlayer() {
        /**
         * Workers who do not belong to the player who is in turn are painted gray
         * the glow of player who is not in turn is removed
         */
        if (playerInTurn == player1) {

            colorPlayer1.setFill(player1.getColor());
            colorPlayer1.setEffect(getGlow());
            colorPlayer1.setStyle("-fx-opacity: 1;");
            circle1.setFill(player1.getColor());
            circle2.setFill(player1.getColor());
            circle1.setStyle("-fx-opacity: 1;");
            circle2.setStyle("-fx-opacity: 1;");

            colorPlayer2.setStyle("-fx-opacity: 0.3;");
            colorPlayer2.setEffect(null);
            circle3.setFill(player2.getColor());
            circle4.setFill(player2.getColor());
            circle3.setStyle("-fx-opacity: 0.3;");
            circle4.setStyle("-fx-opacity: 0.3;");

            colorPlayer3.setStyle("-fx-opacity: 0.3;");
            colorPlayer3.setEffect(null);
            circle5.setFill(player3.getColor());
            circle6.setFill(player3.getColor());
            circle5.setStyle("-fx-opacity: 0.3;");
            circle6.setStyle("-fx-opacity: 0.3;");

            if (is3Worker) {
                circle7.setFill(player1.getColor());
                circle7.setStyle("-fx-opacity: 1;");

                circle8.setFill(player2.getColor());
                circle8.setStyle("-fx-opacity: 0.3;");
                circle9.setFill(player3.getColor());
                circle9.setStyle("-fx-opacity: 0.3;");
            }

        } else if (playerInTurn == player2) {

            colorPlayer2.setFill(player2.getColor());
            colorPlayer2.setEffect(getGlow());
            colorPlayer2.setStyle("-fx-opacity: 1;");
            circle3.setFill(player2.getColor());
            circle4.setFill(player2.getColor());
            circle3.setStyle("-fx-opacity: 1;");
            circle4.setStyle("-fx-opacity: 1;");

            colorPlayer1.setStyle("-fx-opacity: 0.3;");
            circle1.setFill(player1.getColor());
            circle2.setFill(player1.getColor());
            circle1.setStyle("-fx-opacity: 0.3;");
            circle2.setStyle("-fx-opacity: 0.3;");
            colorPlayer1.setEffect(null);

            colorPlayer3.setStyle("-fx-opacity: 0.3;");
            circle5.setFill(player3.getColor());
            circle6.setFill(player3.getColor());
            circle5.setStyle("-fx-opacity: 0.3;");
            circle6.setStyle("-fx-opacity: 0.3;");
            colorPlayer3.setEffect(null);

            if (is3Worker) {
                circle8.setFill(player2.getColor());
                circle8.setStyle("-fx-opacity: 1;");

                circle7.setStyle("-fx-opacity: 0.3;");
                circle7.setFill(player1.getColor());
                circle9.setFill(player3.getColor());
                circle9.setStyle("-fx-opacity: 0.3;");
            }


        } else if (playerInTurn == player3) {
            colorPlayer3.setFill(player3.getColor());
            colorPlayer3.setEffect(getGlow());
            colorPlayer3.setStyle("-fx-opacity: 1;");
            circle5.setFill(player3.getColor());
            circle6.setFill(player3.getColor());
            circle5.setStyle("-fx-opacity: 1;");
            circle6.setStyle("-fx-opacity: 1;");

            colorPlayer1.setFill(player1.getColor());
            colorPlayer1.setStyle("-fx-opacity: 0.3;");
            colorPlayer1.setEffect(null);
            circle1.setFill(player1.getColor());
            circle2.setFill(player1.getColor());
            circle1.setStyle("-fx-opacity: 0.3;");
            circle2.setStyle("-fx-opacity: 0.3;");

            colorPlayer2.setFill(player2.getColor());
            colorPlayer2.setStyle("-fx-opacity: 0.3;");
            colorPlayer2.setEffect(null);
            circle3.setFill(player2.getColor());
            circle4.setFill(player2.getColor());
            circle3.setStyle("-fx-opacity: 0.3;");
            circle4.setStyle("-fx-opacity: 0.3;");

            if (is3Worker) {
                circle9.setFill(player3.getColor());
                circle9.setStyle("-fx-opacity: 1;");

                circle7.setFill(player1.getColor());
                circle8.setFill(player2.getColor());
                circle7.setStyle("-fx-opacity: 0.3;");
                circle8.setStyle("-fx-opacity: 0.3;");
            }
        }
    }

    /**
     * change each player's turn
     */
    public void switchTurn() {

        if (player1.isHasTurn()) {
            player1.setHasTurn(false);
            player2.setHasTurn(true);
            if (is3Player) {
                player3.setHasTurn(false);
            }
        } else if (player2.isHasTurn()) {
            player1.setHasTurn(false);
            player2.setHasTurn(false);
            if (is3Player) {
                player3.setHasTurn(true);
            }
        } else if (player3.isHasTurn()) {
            player1.setHasTurn(true);
            player2.setHasTurn(false);
            if (is3Player) {
                player3.setHasTurn(false);
            }
        }

        playerInTurn.setMoved(false);
        setNewStatus(player1, player2, player3);
        if (playerInTurn.isCom() && gameManager.getWinnerExists() == false) {
            aiAction(playerInTurn);
        } else if(playerInTurn.isCom() && gameManager.getWinnerExists()){
            switchToStartAfterWinning();
        }
    }


    /**
     * To retrieve the player's data from the game settings
     */
    public void setPlayersAndWorker1(Player play1, Player play2) {
        this.player1 = play1;
        this.player2 = play2;
        restPlayers.add(play1);
        restPlayers.add(play2);

        if (is1Worker) {
            setWorkersFromAmount(1);
        } else if (is3Worker) {
            setWorkersFromAmount(3);
        } else {
            setWorkersFromAmount(2);
        }

        if (player1.isCom()) {
            player1.setName("[BOT] " + player1.getName());
            playerName1.setText(player1.getName());

        } else {
            player1.setName(player1.getName());
            playerName1.setText(player1.getName());
        }

        if (player2.isCom()) {
            player2.setName("[BOT] " + player2.getName());
            playerName2.setText(player2.getName());

        } else {
            player2.setName(player2.getName());
            playerName2.setText(player2.getName());
        }

        colorPlayer1.setFill(player1.getColor());
        circle1.setFill(player1.getColor());
        circle2.setFill(player1.getColor());

        colorPlayer2.setFill(player2.getColor());
        circle3.setFill(player2.getColor());
        circle4.setFill(player2.getColor());

        setNewStatus1(player1, player2);
    }

    /**
     * To retrieve the player's data from the game settings if there is first winner in 3P mode
     */
    public void setPlayersAndWorker1WithoutWinner(Player play1, Player play2) {
        this.player1 = play1;
        this.player2 = play2;
        restPlayers.add(play1);
        restPlayers.add(play2);

        if (is1Worker) {
            setWorkersFromAmount(1);
        } else if (is3Worker) {
            setWorkersFromAmount(3);
        } else {
            setWorkersFromAmount(2);
        }

        if (player1.isCom()) {
            player1.setName("[BOT] " + player1.getName());
            playerName1.setText(player1.getName());

        } else {
            player1.setName(player1.getName());
            playerName1.setText(player1.getName());
        }

        if (player2.isCom()) {
            player2.setName("[BOT] " + player2.getName());
            playerName2.setText(player2.getName());

        } else {
            player2.setName(player2.getName());
            playerName2.setText(player2.getName());
        }

        colorPlayer1.setFill(player1.getColor());
        circle1.setFill(player1.getColor());
        circle2.setFill(player1.getColor());
        imagePlayer1.setImage(play1.getImage());

        colorPlayer2.setFill(player2.getColor());
        circle3.setFill(player2.getColor());
        circle4.setFill(player2.getColor());
        imagePlayer2.setImage(play2.getImage());

        if(play1.getAssignedGod() != null){
            godTextP1.setText(play1.getAssignedGod());
            godTextP2.setText(play2.getAssignedGod());


        }

    }


    /**
     * To retrieve the player's data from the game settings
     */
    public void setPlayersAndWorker(Player play1, Player play2, Player play3) {
        this.player1 = play1;
        this.player2 = play2;
        this.player3 = play3;
        restPlayers.add(play1);
        restPlayers.add(play2);
        restPlayers.add(play3);

        player1.setImage(imagePlayer1.getImage());
        player2.setImage(imagePlayer2.getImage());
        player3.setImage(playerImage3.getImage());

        is3Player = true;
        if (ChangeTheme.getTheme()==2) {
            Image newImage3 = new Image(getClass().getResource("/image/vampire3.png").toExternalForm());
            playerImage3.setImage(newImage3);
        }
        worker5 = new Worker(circle5.getId());
        worker6 = new Worker(circle6.getId());


        colorPlayer1.setFill(player1.getColor());
        circle1.setFill(player1.getColor());
        circle2.setFill(player1.getColor());

        colorPlayer2.setFill(player2.getColor());
        circle3.setFill(player2.getColor());
        circle4.setFill(player2.getColor());

        colorPlayer3.setFill(player3.getColor());
        circle5.setFill(player3.getColor());
        circle6.setFill(player3.getColor());

        if (is1Worker) {
            setWorkersFromAmount(1);
        } else if (is3Worker) {
            setWorkersFromAmount(3);
        } else {
            setWorkersFromAmount(2);
        }

        if (player1.isCom()) {
            player1.setName("[BOT] " + player1.getName());
            playerName1.setText(player1.getName());
        } else {
            player1.setName(player1.getName());
            playerName1.setText(player1.getName());
        }

        if (player2.isCom()) {
            player2.setName("[BOT] " + player2.getName());
            playerName2.setText(player2.getName());

        } else {
            player2.setName(player2.getName());
            playerName2.setText(player2.getName());
        }

        if (player3.isCom()) {
            player3.setName("[BOT] " + player3.getName());
            playerName3.setText(player3.getName());

        } else {
            player3.setName(player3.getName());
            playerName3.setText(player3.getName());
        }

        setNewStatus(player1, player2, player3);
    }

    /**
     * To retrieve the amount of stone for each Type from the game settings
     *
     * @param level1
     * @param level2
     * @param level3
     * @param dome
     */
    public void setStoneSetting(String level1, String level2, String level3, String dome) {
        stone1.setText(level1);
        stone2.setText(level2);
        stone3.setText(level3);
        stone4.setText(dome);
    }


    /**
     * To retrieve the amount of stone for each Type from the game settings for winning height 2
     *
     * @param level1
     * @param level2
     * @param dome
     */
    public void setStoneSettingWinningHeight2(String level1, String level2, String dome) {
        stone1.setText(level1);
        stone2.setText(level2);
        stone4.setText(dome);
    }

    /**
     * To retrieve the amount of stone for each Type from the game settings
     *
     * @param level1
     * @param level2
     * @param level3
     * @param level4
     * @param dome
     */
    public void setStoneSettingWinningHeight4(String level1, String level2, String level3, String level4, String dome) {
        stone1.setText(level1);
        stone2.setText(level2);
        stone3.setText(level3);
        stoneLevel4.setText(level4);
        stone4.setText(dome);
    }

    /**
     * To retrieve the amount of stone for each Type from the game settings
     *
     * @param level1
     * @param level2
     * @param level3
     * @param level4
     * @param level5
     * @param dome
     */
    public void setStoneSettingWinningHeight5(String level1, String level2, String level3, String level4, String level5, String dome) {
        stone1.setText(level1);
        stone2.setText(level2);
        stone3.setText(level3);
        stoneLevel4.setText(level4);
        stoneLevel5.setText(level5);
        stone4.setText(dome);
    }


    public void demeterPaintStone(Rectangle clickedRectangle, int row, int col, int[][] count){

        switch (heightToWin){
            case 2:
                String amount = stone3.getText();
                int newAmount = Integer.parseInt(amount);

                if (newAmount > 0) {
                    newAmount -= 1;
                    String res = String.valueOf(newAmount);
                    stone3.setText(res);
                    count[row][col] = 3;
                    clickedRectangle.setFill(Color.RED);
                    gameManager.setHeightField(3);
                }
                break;
            case 3:
                String amount2 = stone4.getText();
                int newAmount2 = Integer.parseInt(amount2);

                if (newAmount2 > 0) {
                    newAmount2 -= 1;
                    String res = String.valueOf(newAmount2);
                    stone4.setText(res);
                    clickedRectangle.setFill(Color.RED);
                    count[row][col] = 4;
                    gameManager.setHeightField(4);
                }
                break;
            case 4:
                String amount3 = stoneLevel4.getText();
                int newAmount3 = Integer.parseInt(amount3);

                if (newAmount3 > 0) {
                    newAmount3 -= 1;
                    String res = String.valueOf(newAmount3);
                    stoneLevel4.setText(res);
                    clickedRectangle.setFill(Color.RED);
                    count[row][col] = 5;
                    gameManager.setHeightField(5);
                }

                break;
            case 5:
                String amount4 = stoneLevel4.getText();
                int newAmount4 = Integer.parseInt(amount4);

                if (newAmount4 > 0) {
                    newAmount4 -= 1;
                    String res = String.valueOf(newAmount4);
                    stoneLevel5.setText(res);
                    clickedRectangle.setFill(Color.RED);
                    count[row][col] = 6;
                    gameManager.setHeightField(6);
                }

                break;
        }
    }
    /**
     * Checking and adjusting the status of the stones
     *
     * @param clicked
     * @param row
     * @param col
     * @param stone
     */
    public void updateStone(Rectangle clicked, int row, int col, Text stone) {
        String amount = stone.getText();
        int newAmount = Integer.parseInt(amount);

        if (newAmount > 0) {
            newAmount -= 1;
            String res = String.valueOf(newAmount);
            stone.setText(res);

            paintStone(clickCount[row][col], clicked);
            clickCount[row][col]++;

            if (playerInTurn == player1) {
                player1.setTurnAmount(player1.getTurnAmount() + 1);
                String newCount = String.valueOf(player1.getTurnAmount());
                playerCount1.setText(newCount);

            } else if (playerInTurn == player2) {
                player2.setTurnAmount(player2.getTurnAmount() + 1);
                String newCount = String.valueOf(player2.getTurnAmount());
                playerCount2.setText(newCount);

            } else if (playerInTurn == player3) {
                player3.setTurnAmount(player3.getTurnAmount() + 1);
                String newCount = String.valueOf(player3.getTurnAmount());
                playerCount3.setText(newCount);
            }

            System.out.println("you built with the worker: " + workerInTurn.getWorkerID() + "\n");
        } else {
            if(LanguageManager.getCurrentLanguage()== "en") {
                showAlert("no more stone of this type is usable!");
            } else if(LanguageManager.getCurrentLanguage()== "de") {
                showAlert("Von diesem Steintyp sind keine Steine mehr übrig!");
            }
            stone.setText("0");
        }
    }

    /**
     * Painting stones according to their level and save their level in the gameManager (heightField-Variable)
     *
     * @param count
     * @param clicked
     */
    private void paintStone(int count, Rectangle clicked) {
        switch (count) {
            case 0:
                clicked.setFill(Color.PEACHPUFF);
                gameManager.setHeightField(1);
                break;
            case 1:
                clicked.setFill(Color.LIGHTGREEN);
                gameManager.setHeightField(2);
                break;
            case 2:
                if (heightToWin == 2) {
                    clicked.setFill(Color.RED);
                    System.out.println("der grüne wird rot gefärbt");
                } else {
                    clicked.setFill(Color.DARKCYAN);
                    System.out.println("der grüne wird blau gefärbt");
                }
                gameManager.setHeightField(3);
                break;
            case 3:
                if (heightToWin == 3) {
                    clicked.setFill(Color.RED);
                    System.out.println("der blaue wird rot gefärbt");
                } else if (heightToWin == 4 || heightToWin == 5) {
                    clicked.setFill(Color.GREY);
                    System.out.println("der blaue wird grau gefärbt");
                }
                gameManager.setHeightField(4);
                break;
            case 4:
                if (heightToWin == 4) {
                    clicked.setFill(Color.RED);
                    System.out.println("der blaue wird rot gefärbt");
                } else if (heightToWin == 5) {
                    clicked.setFill(Color.DARKRED);
                    System.out.println("der blaue wird dunkelrot gefärbt");
                }
                gameManager.setHeightField(5);
                break;
            case 5:
                if (heightToWin == 5) {
                    clicked.setFill(Color.RED);
                }
                gameManager.setHeightField(6);
                break;
            default:
                System.out.println("keiner der Fälle ist eingetreten");
                break;
        }
    }

    /**
     * to show alert message
     *
     * @param message
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showWinAlertForPan(Player currentPlayer, Pan pan) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            if(LanguageManager.getCurrentLanguage()== "en") {
                alert.setTitle("Game ended - HeightDiff(Pan)");
                alert.setContentText(currentPlayer.getName() + " has won in " + currentPlayer.getTurnAmount() + " turns!");
            } else if(LanguageManager.getCurrentLanguage() == "de") {
                alert.setTitle("Spiel beendet");
                alert.setContentText(currentPlayer.getName() + " hat in " + currentPlayer.getTurnAmount() + " Zügen gewonnen!");
            } else if(LanguageManager.getCurrentLanguage() == "kr") {
                alert.setTitle(" ");
                alert.setContentText(currentPlayer.getName() + " " + currentPlayer.getTurnAmount() + " !");
            }
            alert.setOnCloseRequest(event -> pan.winnerCheckHeightDiffPan());
            alert.showAndWait();
    }



    /**
     * Painting the field according to set size
     */
    public void buildField(int size) {

        fieldsize = size;
        clickCount = new int[fieldsize][fieldsize];
        occupiedPositions = new boolean[fieldsize][fieldsize];

        if (size == 8) {

            workerContainerP2.setLayoutX(workerContainerP2.getLayoutX() + 270);
            playerName2.setLayoutX(playerName2.getLayoutX() + 270);
            colorPlayer2.setLayoutX(colorPlayer2.getLayoutX() + 270);
            imagePlayer2.setLayoutX(imagePlayer2.getLayoutX() + 270);
            playerCount2.setLayoutX(playerCount2.getLayoutX() + 270);
            countlabelPlayer2.setLayoutX(countlabelPlayer2.getLayoutX() + 270);

            stone1Label.setLayoutY(stone1Label.getLayoutY() + 200);
            stone2Label.setLayoutY(stone2Label.getLayoutY() + 200);
            stone3Label.setLayoutY(stone3Label.getLayoutY() + 200);
            domeLabel.setLayoutY(domeLabel.getLayoutY() + 200);

            stone1Label.setLayoutX(stone1Label.getLayoutX() + 150);
            stone2Label.setLayoutX(stone2Label.getLayoutX() + 150);
            stone3Label.setLayoutX(stone3Label.getLayoutX() + 150);
            domeLabel.setLayoutX(domeLabel.getLayoutX() + 150);

            stone1.setLayoutY(stone1.getLayoutY() + 200);
            stone2X.setLayoutY(stone2X.getLayoutY() + 200);
            stone3X.setLayoutY(stone3X.getLayoutY() + 200);
            domeX.setLayoutY(domeX.getLayoutY() + 200);

            stone1.setLayoutX(stone1.getLayoutX() + 150);
            stone2.setLayoutX(stone2.getLayoutX() + 150);
            stone3.setLayoutX(stone3.getLayoutX() + 150);
            stone4.setLayoutX(stone4.getLayoutX() + 150);

            stone1X.setLayoutY(stone1X.getLayoutY() + 200);
            stone2.setLayoutY(stone2.getLayoutY() + 200);
            stone3.setLayoutY(stone3.getLayoutY() + 200);
            stone4.setLayoutY(stone4.getLayoutY() + 200);

            stone1X.setLayoutX(stone1X.getLayoutX() + 150);
            stone2X.setLayoutX(stone2X.getLayoutX() + 150);
            stone3X.setLayoutX(stone3X.getLayoutX() + 150);
            domeX.setLayoutX(domeX.getLayoutX() + 150);

            stone1Rect.setLayoutY(stone1Rect.getLayoutY() + 200);
            stone2Rect.setLayoutY(stone2Rect.getLayoutY() + 200);
            stone3Rect.setLayoutY(stone3Rect.getLayoutY() + 200);
            domeRect.setLayoutY(domeRect.getLayoutY() + 200);

            stone1Rect.setLayoutX(stone1Rect.getLayoutX() + 150);
            stone2Rect.setLayoutX(stone2Rect.getLayoutX() + 150);
            stone3Rect.setLayoutX(stone3Rect.getLayoutX() + 150);
            domeRect.setLayoutX(domeRect.getLayoutX() + 150);

            if(is3Worker && is3Player){
                workerContainerP2.setLayoutX(workerContainerP2.getLayoutX() + 30);

            }

            if (is3Player) {
                playerName2.setLayoutX(playerName2.getLayoutX() + 100);
                colorPlayer2.setLayoutX(colorPlayer2.getLayoutX() + 100);
                imagePlayer2.setLayoutX(imagePlayer2.getLayoutX() + 100);
                playerCount2.setLayoutX(playerCount2.getLayoutX() + 100);
                countlabelPlayer2.setLayoutX(countlabelPlayer2.getLayoutX() + 100);

                colorPlayer3.setLayoutX(colorPlayer3.getLayoutX() + 230);
                playerImage3.setLayoutX(playerImage3.getLayoutX() + 230);
                playerName3.setLayoutX(playerName3.getLayoutX() + 230);
                playerCountlabel3.setLayoutX(playerCountlabel3.getLayoutX() + 230);
                playerCount3.setLayoutX(playerCount3.getLayoutX() + 230);

            }
        } else if (size == 5 && is3Player==false && is3Worker){
//                workerContainerP1.setLayoutX(workerContainerP1.getLayoutX() + 20);
//                workerContainerP1.setLayoutY(workerContainerP1.getLayoutY() - 120);
//                workerContainerP2.setLayoutX(workerContainerP2.getLayoutX() + 30);
//                workerContainerP2.setLayoutY(workerContainerP2.getLayoutY() - 100);
        }

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Rectangle rect = new Rectangle(85, 60);
                rect.setFill(Color.WHITESMOKE);
                rect.setStroke(Color.BLACK);
                gamefield.add(rect, col, row);
            }
        }
    }

    /**
     * check if there is already a circle in the gamefield
     * @param gridPane
     * @param columnIndex
     * @param rowIndex
     * @return
     */
    public boolean isCircleExistInCell(GridPane gridPane, int columnIndex, int rowIndex) {

        for (Node childNode : gridPane.getChildren()) {
            if (childNode instanceof Circle) {
                int childColIndex = GridPane.getColumnIndex(childNode);
                int childRowIndex = GridPane.getRowIndex(childNode);

                if (childColIndex == columnIndex && childRowIndex == rowIndex) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * remove the draggable effect of worker
     *
     * @param worker
     */
    private void dontMakeDraggable(Circle worker) {
        worker.setOnDragDetected(null);
    }


    /**
     * Setting drag and drop function for the workers (circles on the gamefield)
     * for the initial positioning and the movement on the gamefield during the game.
     *
     * @param worker The circles, which represent the workers in the game
     */

    private void makeWorkerDraggable(Circle worker) {

        /**
         * Method, which is called when the user wants to pull/drag a circle(worker)
         * Only if the player hasn't moved his worker yet is it permitted
         */

        worker.setOnDragDetected(event -> {
            System.out.println("worker: " + worker.getId());

            /**
             * Only the circles that have not yet been moved can be used for the initial turn.
             */
            if (playerInTurn.getTurnAmount() == 0 && getWorkerOfCircle(worker).isMoved() == false) {
                Dragboard dragboard = worker.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(worker.getId());
                dragboard.setContent(content);
                dragboard.setDragView(worker.snapshot(null, null));
                dragboard.setContent(content);
                event.consume();

                /**
                 * after initial turn, just the player who has not moved his worker can drag the worker
                 */
            } else if (playerInTurn.getInitialMove() == 0 &&playerInTurn.isMoved() == false) {
                Dragboard dragboard = worker.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(worker.getId());
                dragboard.setContent(content);
                dragboard.setDragView(worker.snapshot(null, null));
                dragboard.setContent(content);
                event.consume();

            }else if(playerInTurn.getInitialMove() == 0 && (playerInTurn.getAssignedGod().equals("Artemis") && artemis.getRest()>0)){
                Dragboard dragboard = worker.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(worker.getId());
                dragboard.setContent(content);
                dragboard.setDragView(worker.snapshot(null, null));
                dragboard.setContent(content);
                event.consume();
            } else {
                if(LanguageManager.getCurrentLanguage() == "en") {
                    showAlert("you can´t move twice in one turn");
                } else if(LanguageManager.getCurrentLanguage() == "de") {
                    showAlert("Du kannst dich nur einmal pro Runde bewegen");
                }
            }

        });

        /**
         * Method, which is called, when the user is dragging the circle over the gridpane/gamefield
         */

        gamefield.setOnDragOver(event -> {
            if (event.getGestureSource() != gamefield && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });


        /**
         * When a user initially drags and drops a worker onto or within the gamefield, the following actions are performed
         */

        gamefield.setOnDragDropped(event -> {
            Dragboard dragboard = event.getDragboard();

            boolean success = false;


            if (dragboard.hasString()) {

                /**
                 * get the Circle, which the user wants to drag
                 */
                Circle draggedCircle = (Circle) event.getGestureSource();


                /**
                 * get the node, the user wants to drop the worker on
                 */
                Node clickedNode = (Node) event.getTarget();

                /**
                 * get the position of the target node
                 */

                int columnIndex = GridPane.getColumnIndex(clickedNode);
                int rowIndex = GridPane.getRowIndex(clickedNode);

                /**
                 * for the initial turn, the workers which is moved are set as true
                 */
                if (playerInTurn.getTurnAmount() == 0) {
                    playerInTurn.setInitialMove(playerInTurn.getInitialMove() - 1);
                    getWorkerOfCircle(worker).setMoved(true);
                    System.out.println("InitialMove: " + playerInTurn.getInitialMove() + "\n");

                    /**
                     * after the player locates his workers on the gameboard, the turn will be exchanged.
                     */
                    if (playerInTurn.getInitialMove() == 0) {
                        playerInTurn.setTurnAmount(1);
                        String newCount = String.valueOf(playerInTurn.getTurnAmount());
                        if (playerInTurn == player1) {
                            playerCount1.setText(newCount);
                        } else if (playerInTurn == player2) {
                            playerCount2.setText(newCount);
                        } else {
                            playerCount3.setText(newCount);
                        }
                    }

                    if (!is3Player) {
                        switchTurn1();
                        paintWorkerAndPlayer1();
                    } else {
                        switchTurn();
                        paintWorkerAndPlayer();

                    }
                }

                /**
                 * If the worker, which is dragged was placed in a VBox or HBox before (outside the gamefield), it is removed from there
                 * and added to the gamefield on the position of the targeted node.
                 * The drag-and-drop action was successful.
                 */

                if (draggedCircle.getParent() instanceof VBox) {
                    VBox vbox = (VBox) draggedCircle.getParent();
                    vbox.getChildren().remove(draggedCircle);

                    GridPane.setConstraints(draggedCircle, columnIndex, rowIndex);
                    GridPane.setMargin(draggedCircle, new Insets(0, 0, 0, 25));
                    gamefield.getChildren().add(draggedCircle);

                    occupiedPositions[rowIndex][columnIndex] = true;

                    /**
                     * save the worker position
                     */
                    getWorkerOfCircle(worker).setWorkerPosition(rowIndex, columnIndex);

                    success = true;

                } else if (draggedCircle.getParent() instanceof HBox) {

                    HBox hbox = (HBox) draggedCircle.getParent();
                    hbox.getChildren().remove(draggedCircle);

                    GridPane.setConstraints(draggedCircle, columnIndex, rowIndex);
                    GridPane.setMargin(draggedCircle, new Insets(0, 0, 0, 25));
                    gamefield.getChildren().add(draggedCircle);


                    success = true;

                    occupiedPositions[rowIndex][columnIndex] = true;

                    /**
                     * If the worker has already been on the gamefield, it checks for every node on the gamefield,
                     * if the target position is equal to the position of the gamefield node and if yes, it makes the node the target field.
                     */

                } else if (draggedCircle.getParent() instanceof GridPane) {

                    //get the position if the circle/worker, which is about to be moved on the gamefield
                    int colIndDraggedCirc = GridPane.getColumnIndex(draggedCircle);
                    int rowIndDraggedCirc = GridPane.getRowIndex(draggedCircle);

                    //assign the Indices to the instance variables for the worker, which is about to be moved

                    colIndNotYetDragged = colIndDraggedCirc;
                    rowIndNotYetDragged = rowIndDraggedCirc;

                    if("Artemis".equals(playerInTurn.getAssignedGod()) && artemis.getRest()==2){
                        artemis.setPreviousPosition(colIndNotYetDragged, rowIndNotYetDragged);
                        for (Node node : gamefield.getChildren()) {
                            if (node instanceof Rectangle && GridPane.getColumnIndex(node) == artemis.getPreviousCol() && GridPane.getRowIndex(node) == artemis.getPreviousRow()) {
                                artemisRect = (Rectangle) node;
                                break;
                            }
                        }
                    }
                    System.out.println("column: " + colIndNotYetDragged + "  row: " + rowIndNotYetDragged);

                    Rectangle targetField = null;
                    for (Node node : gamefield.getChildren()) {
                        if (node instanceof Rectangle && GridPane.getColumnIndex(node) == columnIndex && GridPane.getRowIndex(node) == rowIndex) {
                            targetField = (Rectangle) node;
                            break;
                        }
                    }
                    if("Artemis".equals(playerInTurn.getAssignedGod()) && artemis.getRest()==1){
                        for (Node node : gamefield.getChildren()) {
                            if (node instanceof Rectangle && GridPane.getColumnIndex(node) == artemis.getPreviousCol() && GridPane.getRowIndex(node) == artemis.getPreviousRow()) {
                                artemisRect = (Rectangle) node;
                                break;
                            }
                        }
                        getWorkerOfCircle(worker).getValidfield().remove(artemisRect);
                    }

                    /**
                     * If there is a target field (if the target field is on the gamefield) and this field is valid to move to for the worker,
                     * the worker is removed from its previous position and moved to the target position.
                     */
                    if (targetField != null && getWorkerOfCircle(worker).getValidfield().contains(targetField)) {

                        GridPane gridPane = (GridPane) draggedCircle.getParent();
                        gridPane.getChildren().remove(draggedCircle);

                        GridPane.setConstraints(draggedCircle, columnIndex, rowIndex);
                        GridPane.setMargin(draggedCircle, new Insets(0, 0, 0, 25));
                        gamefield.getChildren().add(draggedCircle);

                        /**
                         * The dragged Circle (worker) is saved in the movingWorker variable and given to the gamemanager
                         */
                        movingWorker = draggedCircle;
                            /**
                             * The height of the field, on which the just moved worker is positioned is saved
                             */
                            saveWorkerHeight(movingWorker);


                            // if the current player has Athena, save the worker Height in the respective variable for athena
                            if("Athena".equals(playerInTurn.getAssignedGod())) {
                                workerHeightAthena = workerHeight;
                            }

                            //The difference in height is calculated for the Athena skill
                            diffInMovementAthena = heightBeforeMovement - workerHeightAthena;
                            if(diffInMovementAthena < 0) {
                                playerAthenaMovedUp = true;
                            } else {
                                playerAthenaMovedUp = false;
                            }
                            System.out.println("Die Höhe NACH der Bewegung: " + workerHeight);
                            System.out.println("diffInMovementAthena: " + diffInMovementAthena);


                            success = true;
                        /**
                         * The height of the field, on which the just moved worker is positioned is saved
                         */
                        saveWorkerHeight(movingWorker);
                        success = true;

                        /**
                         * If the player which is in turn has chosen Apollo and uses its power, it takes the rectangle of the opponent and
                         * therefore the opponent mus take the position of the worker, which the player in turn has just left.
                         */
                        if ("Apollo".equals(playerInTurn.getAssignedGod())) {
                            Apollo apollo = new Apollo();
                            apollo.switchPositions(movingWorker, colIndNotYetDragged, rowIndNotYetDragged, gamefield);
                        }else if("Artemis".equals(playerInTurn.getAssignedGod())){
                            if(artemis.getRest()==2){
                                artemis.setRest(artemis.getRest()-1);

                            }else if(artemis.getRest()==1){
//                                getWorkerOfCircle(worker).getValidfield().add(artemisRect);
                                artemis.setRest(artemis.getRest()-1);
                            }
                        }

                        /**
                         * The just moved worker (circle) is assigned to the worker object "workerInTurn"
                         */
                        workerInTurn = getWorkerOfCircle(movingWorker);

                        if (playerInTurn.getTurnAmount() != 0 && playerInTurn.isMoved() == false ) {
                            workerInTurn.setMoved(true);
                            validFieldsToBuild = saveValidFields(movingWorker);

                        }
                        playerInTurn.setMoved(true);

                    } else {
                        if(LanguageManager.getCurrentLanguage()=="en") {
                            showAlert("it´s not a valid field for that worker");
                        } else if (LanguageManager.getCurrentLanguage() == "de") {
                            showAlert("Dieses Feld ist nicht erreichbar für den Arbeiter");
                        }
                        notValidAlertShown = true;
                    }
                    if("Artemis".equals(playerInTurn.getAssignedGod()) && artemis.getRest()==0){
                        for (Node node : gamefield.getChildren()) {
                            if (node instanceof Rectangle && GridPane.getColumnIndex(node) == artemis.getPreviousCol() && GridPane.getRowIndex(node) == artemis.getPreviousRow()) {
                                artemisRect = (Rectangle) node;
                                break;
                            }
                        }
                        getWorkerOfCircle(worker).getValidfield().add(artemisRect);
                    }
                }

                /**
                 * After dropping the worker, the highlights of the worker and valid fields are removed
                 */
                clearWorkerHighlighting(movingWorker);
                clearFieldHighlighting(validFieldsToMoveTo);
                isActive = false;

                /**
                 * save the worker position
                 */
                workerInTurn.setWorkerPosition(columnIndex, rowIndex);

                /**
                 * Manages what happens after a player has won the game.
                 */
                if (!notValidAlertShown) {
                    handleWinningActionsHeight();
                    //if Pan is chosen god, it checks if the player just moved two stages downwards, which would mean a win
                    if("Pan".equals(playerInTurn.getAssignedGod())) {
                        Pan pan = new Pan();
                        if(pan.checkWinConditionPan(pan.saveHeightDiffInMovement(heightBeforeMovement, workerHeight), playerInTurn) == true) {
                            showWinAlertForPan(playerInTurn, pan);
                            gameManager.setWinnerExists(pan.winnerExists);
                            switchToStartAfterWinning();
                        }
                    }
                } else if(notValidAlertShown) {
                    notValidAlertShown = false;
                }

            }
            validFieldsToMoveTo.clear();
            clearFieldHighlighting(allRectsOnGamefield());

            event.setDropCompleted(success);
            event.consume();
        });
    }

    /**
     * Method to check for each field on the gamefield, which (8) fields surround the specific field and save them in a list
     *
     * @param node element on the gamefield
     * @return a list of surrounding fields (dependent on a specific node)
     */
    public List<Rectangle> saveSurroundingFields(Node node) {
        List<Rectangle> surroundingFields = new ArrayList<>();

        int colIndCirc = GridPane.getColumnIndex(node);
        int rowIndCirc = GridPane.getRowIndex(node);

        for (Node element : gamefield.getChildren()) {
            int nodeX = GridPane.getColumnIndex(element);
            int nodeY = GridPane.getRowIndex(element);

            int deltaCol = Math.abs(colIndCirc - nodeX);
            int deltaRow = Math.abs(rowIndCirc - nodeY);

            if ((element instanceof Rectangle) && ((deltaCol == 1 && deltaRow == 0) || (deltaCol == 0 && deltaRow == 1) || (deltaCol == 1 && deltaRow == 1))) {
                Rectangle field = (Rectangle) element;
                surroundingFields.add(field);
            }
        }
        return (surroundingFields);
    }

    /**
     * Method to check for each field on the gamefield, which (8) fields surround the specific field and save them in a list
     *
     * @param node element on the gamefield
     * @return a list of surrounding fields (dependent on a specific node)
     */
    private List<Rectangle> ForGlobeSaveSurroundingFields(Node node) {
        List<Rectangle> surroundingFields = new ArrayList<>();

        int colIndCirc = GridPane.getColumnIndex(node);
        int rowIndCirc = GridPane.getRowIndex(node);

        for (Node element : gamefield.getChildren()) {
            int nodeX = GridPane.getColumnIndex(element);
            int nodeY = GridPane.getRowIndex(element);

            int deltaCol = Math.abs(colIndCirc - nodeX);
            int deltaRow = Math.abs(rowIndCirc - nodeY);

            /**
             * the validfield in the globe mode
             */
            if ((element instanceof Rectangle) && (
                    (deltaCol == 1 && deltaRow == 0) || (deltaCol == 0 && deltaRow == 1) ||
                            (deltaCol == 1 && deltaRow == 1) ||
                            (colIndCirc == 0 && nodeX == gamefield.getRowCount() - 1 && deltaRow == 0) ||
                            (colIndCirc == gamefield.getRowCount() - 1 && nodeX == 0 && deltaRow == 0) ||
                            (rowIndCirc == 0 && nodeY == gamefield.getRowCount() - 1 && deltaCol == 0) ||
                            (rowIndCirc == gamefield.getRowCount() - 1 && nodeY == 0 && deltaCol == 0) ||
                            (Math.abs(colIndCirc - nodeX) == gamefield.getRowCount() - 1 && deltaRow == 0) ||
                            (Math.abs(rowIndCirc - nodeY) == gamefield.getRowCount() - 1 && deltaCol == 0) ||
                            (colIndCirc == 0 && nodeX == gamefield.getRowCount() - 1 && deltaRow == 1) ||
                            (colIndCirc == gamefield.getColumnCount() - 1 && nodeX == 0 && deltaRow == 1) ||
                            (rowIndCirc == 0 && nodeY == gamefield.getRowCount() - 1 && deltaCol == 1) ||
                            (rowIndCirc == gamefield.getRowCount() - 1 && nodeY == 0 && deltaCol == 1)
            )) {
                Rectangle field = (Rectangle) element;
                surroundingFields.add(field);
            }
        }
        return surroundingFields;
    }


    /**
     * Method to check for each field on the gamefield, if there is a circle on it or not and to return the free fields in a list
     *
     * @return a list of the fields, on which there is no worker on
     */
    public List<Rectangle> saveRectanglesWithoutCircle() {

        List<Rectangle> recList = new ArrayList<>();
        List<Circle> circList = new ArrayList<>();

        for (Node child : gamefield.getChildren()) {
            if (child instanceof Rectangle) {
                recList.add((Rectangle) child);
            } else if (child instanceof Circle) {
                circList.add((Circle) child);
            }
        }

        List<Rectangle> rectanglesWithoutCircle = new ArrayList<>();

        for (Rectangle rectangle : recList) {
            int colIndRec = GridPane.getColumnIndex(rectangle);
            int rowIndRec = GridPane.getRowIndex(rectangle);

            boolean hasCircle = false;

            for (Circle circle : circList) {
                int colIndCirc = GridPane.getColumnIndex(circle);
                int rowIndCirc = GridPane.getRowIndex(circle);

                if ((colIndRec == colIndCirc) && (rowIndRec == rowIndCirc)) {
                    hasCircle = true;
                    break;

                }
            }
            if (!hasCircle) {
                rectanglesWithoutCircle.add(rectangle);
            }
        }

        return rectanglesWithoutCircle;
    }


    /**
     * Method to check for each element on the gamefield, if the height delta is valid for movement and save the fields in a list
     *
     * @param node element on the gamefield
     * @return a list of fields with a height delta, that is valid for movement (dependent on a specific node)
     */
    public List<Rectangle> saveRectsWithValidHeight(Node node) {
        List<Rectangle> rectCorrectHeightDelta = new ArrayList<>();
        Integer colIndCirc = GridPane.getColumnIndex(node);
        Integer rowIndCirc = GridPane.getRowIndex(node);

        for (Node element : gamefield.getChildren()) {
            Integer colIndNode = GridPane.getColumnIndex(element);
            Integer rowIndNode = GridPane.getRowIndex(element);

            if (element instanceof Rectangle && clickCount[rowIndNode][colIndNode] != heightToWin+1) {
                int delta = clickCount[rowIndNode][colIndNode] - clickCount[rowIndCirc][colIndCirc];
                if (delta <= 1) {
                    Rectangle field = (Rectangle) element;
                    rectCorrectHeightDelta.add(field);
                }

            }

        }
        return rectCorrectHeightDelta;
    }

    /**
     * Saves all the rectangles on the gamefield, which are valid for movement of the specific worker all stages up and only one stage down
     * @param node worker which is positioned the specific rectangle
     * @return rectAllUpOneDown List of Rectangles, with a delta to the current one of >= -1 (which means all up, one down)
     */
    public List<Rectangle> saveRectsAllUpOneDown(Node node) {
        List<Rectangle> rectAllUpOneDown = new ArrayList<>();
        Integer colIndCirc = GridPane.getColumnIndex(node);
        Integer rowIndCirc = GridPane.getRowIndex(node);

        for (Node element : gamefield.getChildren()) {
            Integer colIndNode = GridPane.getColumnIndex(element);
            Integer rowIndNode = GridPane.getRowIndex(element);

            if (element instanceof Rectangle && clickCount[rowIndNode][colIndNode] != heightToWin+1) {
                int delta = clickCount[rowIndNode][colIndNode] - clickCount[rowIndCirc][colIndCirc];
                if (delta >= -1) {
                    Rectangle field = (Rectangle) element;
                    rectAllUpOneDown.add(field);
                }

            }

        }
        return rectAllUpOneDown;
    }

    /**
     * Saves all the rectangles on the gamefield, which are valid for movement of the specific worker one stage up and one stage down
     * @param node worker which is positioned the specific rectangle
     * @return rectOneUpOneDown List of Rectangles, with a delta to the current one of 1 or 0 (which means same level and one level up, and one stage down is possible)
     */

    public List<Rectangle> saveRectsOneUpOneDown(Node node) {
        List<Rectangle> rectOneUpOneDown = new ArrayList<>();
        Integer colIndCirc = GridPane.getColumnIndex(node);
        Integer rowIndCirc = GridPane.getRowIndex(node);

        for (Node element : gamefield.getChildren()) {
            Integer colIndNode = GridPane.getColumnIndex(element);
            Integer rowIndNode = GridPane.getRowIndex(element);

            if (element instanceof Rectangle && clickCount[rowIndNode][colIndNode] != heightToWin+1) {
                int delta = Math.abs(clickCount[rowIndNode][colIndNode] - clickCount[rowIndCirc][colIndCirc]);
                if (delta == 1 || delta == 0) {
                    Rectangle field = (Rectangle) element;
                    rectOneUpOneDown.add(field);
                }

            }

        }
        return rectOneUpOneDown;
    }

    /**
     * Saves all fields on the gamefield where already a dome is build
     * @return list of rectangles with domes on the gamefield
     */

    public List<Rectangle> saveDomesOnGamefield() {

        List<Rectangle> domeRect = new ArrayList<>();

        for(Node rect : gamefield.getChildren()) {
            if(rect instanceof Rectangle) {
                if(((Rectangle) rect).getFill() != Color.RED) {
                   domeRect.add((Rectangle) rect);
                }
            }
        }
        return domeRect;
    }



    /**
     * Method to finally check all valid fields to move to after a node has been clicked
     *
     * @param clickedNode click on a node on the gamefield
     * @return a list of valid fields for movement for a specific node
     */

    private List<Rectangle> saveValidFields(Node clickedNode) {

        List<Rectangle> validFields = new ArrayList<>();
        List<Rectangle> validFieldsToBuildOn = new ArrayList<>();
        Circle clickedCircle = (Circle) clickedNode;
        clickedWorker = clickedNode;

        for (Node node : gamefield.getChildren()) {
            if (isGlobe) {
                if (ForGlobeSaveSurroundingFields(clickedNode).contains(node)
                        && saveRectanglesWithoutCircle().contains(node)
                        && saveRectsWithValidHeight(clickedNode).contains(node)) {
                    Rectangle field = (Rectangle) node;
                    validFields.add(field);
                }
                //save validFields to build on
                if (ForGlobeSaveSurroundingFields(clickedNode).contains(node)
                        && saveRectanglesWithoutCircle().contains(node)) {
                    Rectangle field = (Rectangle) node;
                    validFieldsToBuildOn.add(field);
                }
            } else {
                if (saveSurroundingFields(clickedNode).contains(node)
                        && saveRectanglesWithoutCircle().contains(node)
                        && saveRectsWithValidHeight(clickedNode).contains(node)) {
                    Rectangle field = (Rectangle) node;
                    validFields.add(field);
                }
                if (saveSurroundingFields(clickedNode).contains(node)
                        && saveRectanglesWithoutCircle().contains(node)) {
                    Rectangle field = (Rectangle) node;
                    validFieldsToBuildOn.add(field);
                }
            }

        }
        validFieldsToMoveTo = validFields;
        validFieldsToBuild = validFieldsToBuildOn;

        //save the valid fields to build in the worker
        getWorkerOfCircle(clickedCircle).setValidFieldToMove(validFieldsToMoveTo);
        getWorkerOfCircle(clickedCircle).setValidFieldToBuild(validFieldsToBuild);

        return (validFields);
    }

    /**
     * Saves the list of valid fields to move to depending on a specific worker and the legal moves settings the user chose
     *
     * @param legalMoves determines which legal move - setting the user chose (One level up, one level down, all up, all down)
     * @param clickedNode specific worker which has been chosen to move
     * @return list of rectangles which are valid to move to under specific legal movement - settings
     */
    private List<Rectangle> saveValidFieldsLegalMoves(int legalMoves, Node clickedNode) {

        List<Rectangle> validFieldsLegalMoves = new ArrayList<>();
        List<Rectangle> validFieldsToBuildOn = new ArrayList<>();
        Circle clickedCircle = (Circle) clickedNode;
        clickedWorker = clickedNode;

        for (Node node : gamefield.getChildren()) {
            if (isGlobe) {
                if(legalMoves == 2) {
                    if (ForGlobeSaveSurroundingFields(clickedNode).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)
                            && saveRectsAllUpOneDown(clickedNode).contains(node)
                            && saveDomesOnGamefield().contains(node)) {
                        Rectangle field = (Rectangle) node;
                        validFieldsLegalMoves.add(field);
                    }
                    //save validFields to build on
                    if (ForGlobeSaveSurroundingFields(clickedNode).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)) {
                        Rectangle field = (Rectangle) node;
                        validFieldsToBuildOn.add(field);
                    }
                } else if(legalMoves == 3) {
                    if (ForGlobeSaveSurroundingFields(clickedNode).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)
                            && saveDomesOnGamefield().contains(node)) {
                        Rectangle field = (Rectangle) node;
                        validFieldsLegalMoves.add(field);
                    }
                    //save validFields to build on
                    if (ForGlobeSaveSurroundingFields(clickedNode).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)) {
                        Rectangle field = (Rectangle) node;
                        validFieldsToBuildOn.add(field);
                    }

                } else if (legalMoves == 4) {
                    if (ForGlobeSaveSurroundingFields(clickedNode).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)
                            && saveRectsOneUpOneDown(clickedNode).contains(node)) {
                        Rectangle field = (Rectangle) node;
                        validFieldsLegalMoves.add(field);
                    }
                    //save validFields to build on
                    if (ForGlobeSaveSurroundingFields(clickedNode).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)) {
                        Rectangle field = (Rectangle) node;
                        validFieldsToBuildOn.add(field);
                    }
                }

            } else {
                if(legalMoves == 2) {
                    if (saveSurroundingFields(clickedNode).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)
                            && saveRectsAllUpOneDown(clickedNode).contains(node)
                            && saveDomesOnGamefield().contains(node)) {
                        Rectangle field = (Rectangle) node;
                        validFieldsLegalMoves.add(field);
                    }
                    if (saveSurroundingFields(clickedNode).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)) {
                        Rectangle field = (Rectangle) node;
                        validFieldsToBuildOn.add(field);
                    }
                } else if (legalMoves == 3) {
                    if (saveSurroundingFields(clickedNode).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)
                            && saveDomesOnGamefield().contains(node)) {
                        Rectangle field = (Rectangle) node;
                        validFieldsLegalMoves.add(field);
                    }
                    if (saveSurroundingFields(clickedNode).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)) {
                        Rectangle field = (Rectangle) node;
                        validFieldsToBuildOn.add(field);
                    }
                } else if (legalMoves == 4) {
                    if (saveSurroundingFields(clickedNode).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)
                            && saveRectsOneUpOneDown(clickedNode).contains(node)) {
                        Rectangle field = (Rectangle) node;
                        validFieldsLegalMoves.add(field);
                    }
                }
            }

        }

        validFieldsToMoveTo = validFieldsLegalMoves;
        validFieldsToBuild = validFieldsToBuildOn;

        //save the valid fields to build in the worker
        getWorkerOfCircle(clickedCircle).setValidFieldToMove(validFieldsToMoveTo);
        getWorkerOfCircle(clickedCircle).setValidFieldToBuild(validFieldsToBuildOn);

        return (validFieldsLegalMoves);
    }


    /**
     * Saves all valid fields of Player 1 on a specific timestamp (and depending on the (amount of) circles on the gamefield)
     *
     */
    private void saveAllValidWorkerFieldsP1() {
        List<Rectangle> allValidFieldsP1 = new ArrayList<>();
        List<Rectangle> uniqueValidFieldsP1 = new ArrayList<>();
        List<Circle> allCirclesOnGamefieldP1 = new ArrayList<>();

        allCirclesOnGamefieldP1.add(circle1);

        if (is2Worker) {
            allCirclesOnGamefieldP1.add(circle2);
        }

        if (is3Worker) {
            allCirclesOnGamefieldP1.add(circle7);
        }

        for (Circle circle : allCirclesOnGamefieldP1) {
            for (Node node : gamefield.getChildren()) {

                if (isGlobe) {
                    if (ForGlobeSaveSurroundingFields(circle).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)
                            && saveRectsWithValidHeight(circle).contains(node)) {
                        Rectangle field = (Rectangle) node;
                        allValidFieldsP1.add(field);

                    }
                } else {
                    if (saveSurroundingFields(circle).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)
                            && saveRectsWithValidHeight(circle).contains(node)) {
                        Rectangle field = (Rectangle) node;
                        allValidFieldsP1.add(field);
                    }
                }

            }
            for (Rectangle fieldP1 : allValidFieldsP1) {
                if (!uniqueValidFieldsP1.contains(fieldP1)) {
                    uniqueValidFieldsP1.add(fieldP1);
                }
            }
            allValidFieldsPlayer1 = uniqueValidFieldsP1;
            gameManager.setValidFieldsPlayer1(allValidFieldsPlayer1);

        }

    }


    /**
     * Saves all valid fields of Player 2 on a specific timestamp (and depending on the (amount of) circles on the gamefield)
     *
     */

    private void saveAllValidWorkerFieldsP2() {
        List<Rectangle> allValidFieldsP2 = new ArrayList<>();
        List<Circle> allCirclesOnGamefieldP2 = new ArrayList<>();
        List<Rectangle> uniqueValidFieldsP2 = new ArrayList<>();

        allCirclesOnGamefieldP2.add(circle3);

        if (is2Worker) {
            allCirclesOnGamefieldP2.add(circle4);
        }

        if (is3Worker) {
            allCirclesOnGamefieldP2.add(circle8);
        }

        for (Circle circle : allCirclesOnGamefieldP2) {
            for (Node node : gamefield.getChildren()) {
                if (isGlobe) {
                    if (ForGlobeSaveSurroundingFields(circle).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)
                            && saveRectsWithValidHeight(circle).contains(node)) {
                        Rectangle field = (Rectangle) node;
                        allValidFieldsP2.add(field);
                    }
                } else {
                    if (saveSurroundingFields(circle).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)
                            && saveRectsWithValidHeight(circle).contains(node)) {
                        Rectangle field = (Rectangle) node;
                        allValidFieldsP2.add(field);
                    }
                }
            }
            for (Rectangle fieldP2 : allValidFieldsP2) {
                if (!uniqueValidFieldsP2.contains(fieldP2)) {
                    uniqueValidFieldsP2.add(fieldP2);
                }
            }
            allValidFieldsPlayer2 = uniqueValidFieldsP2;
            gameManager.setValidFieldsPlayer2(allValidFieldsPlayer2);
        }

    }


    /**
     * Saves all valid fields of Player 3 on a specific timestamp (and depending on the (amount of) circles on the gamefield)
     *
     */

    private void saveAllValidWorkerFieldsP3() {
        List<Rectangle> allValidFieldsP3 = new ArrayList<>();
        List<Circle> allCirclesOnGamefieldP3 = new ArrayList<>();
        List<Rectangle> uniqueValidFieldsP3 = new ArrayList<>();

        allCirclesOnGamefieldP3.add(circle5);

        if (is2Worker) {
            allCirclesOnGamefieldP3.add(circle6);
        }

        if (is3Worker) {
            allCirclesOnGamefieldP3.add(circle9);
        }

        for (Circle circle : allCirclesOnGamefieldP3) {
            for (Node node : gamefield.getChildren()) {
                if (isGlobe) {
                    if (ForGlobeSaveSurroundingFields(circle).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)
                            && saveRectsWithValidHeight(circle).contains(node)) {
                        Rectangle field = (Rectangle) node;
                        allValidFieldsP3.add(field);
                    }
                } else {
                    if (saveSurroundingFields(circle).contains(node)
                            && saveRectanglesWithoutCircle().contains(node)
                            && saveRectsWithValidHeight(circle).contains(node)) {
                        Rectangle field = (Rectangle) node;
                        allValidFieldsP3.add(field);
                    }
                }
            }
            for (Rectangle fieldP3 : allValidFieldsP3) {
                if (!uniqueValidFieldsP3.contains(fieldP3)) {
                    uniqueValidFieldsP3.add(fieldP3);
                    System.out.println(GridPane.getRowIndex(fieldP3) +" : "+ GridPane.getColumnIndex(fieldP3));
                }
            }
            System.out.println(" allValidFieldsPlayer3: "+ allValidFieldsPlayer3.size());
            System.out.println(" allValidFieldsPlayer3: "+ allValidFieldsPlayer3.isEmpty());

            allValidFieldsPlayer3 = uniqueValidFieldsP3;
            gameManager.setValidFieldsPlayer3(allValidFieldsPlayer3);
        }

    }

    /**
     * Manages the highlighting of workers and the fields on the gameboard.
     * If the worker is not activated yet, it and its valid fields are highlighted.
     * If the worker is already activated, all Highlights (on fields and workers) are cleared.
     */
    private void manageHighlighting() {
        if (!isActive) {
            highlightValidFields(validFieldsToMoveTo);
            highlightChosenWorker(clickedWorker);
            isActive = true;
        } else if (isActive) {
            clearFieldHighlighting(allRectsOnGamefield());


//            clearWorkerHighlighting(circle1);
//            clearWorkerHighlighting(circle2);
//            clearWorkerHighlighting(circle3);
//            clearWorkerHighlighting(circle4);
//            clearWorkerHighlighting(circle5);
//            clearWorkerHighlighting(circle6);

         /*   clearWorkerHighlighting(circle1);
            clearWorkerHighlighting(circle2);
            clearWorkerHighlighting(circle3);
            clearWorkerHighlighting(circle4);
            if(player3 != null) {
                clearWorkerHighlighting(circle5);
                clearWorkerHighlighting(circle6);
            }

          */

            isActive = false;

        }
    }


    /**
     * Retrieves all rectangles on the gamefield and saves them in a list
     *
     * @return list of all rectangles on the gamefield
     */
    public List<Rectangle> allRectsOnGamefield() {
        List<Rectangle> allRectangles = new ArrayList<>();

        for (Node node : gamefield.getChildren()) {
            if (node instanceof Rectangle) {
                Rectangle field = (Rectangle) node;
                allRectangles.add(field);
            }
        }
        return allRectangles;
    }

    /**
     * Retrieves all rectangles on the gamefield, which have the same height as a worker itself and returns them in a list
     * @param gamefield
     * @return list of rectangles, which have the same height as the worker
     */

    public List<Rectangle> rectsWithWorkerHeights(GridPane gamefield) {
        List<Rectangle> rectsWithWorkerHeights = new ArrayList<>();

        for(Node node : gamefield.getChildren()) {
            if(node instanceof Rectangle) {
                int colIndNode = GridPane.getColumnIndex(node);
                int rowIndNode = GridPane.getRowIndex(node);
                System.out.println(rowIndNode + " , " + colIndNode);

                int heightField = clickCount[rowIndNode][colIndNode];

                System.out.println(heightField);
                System.out.println(workerHeight);

                if(heightField == workerHeight) {
                    rectsWithWorkerHeights.add((Rectangle) node);
                }
            }
        }
        System.out.println(rectsWithWorkerHeights.size());
        return rectsWithWorkerHeights;
    }




    public List<Rectangle> rectsWithHigherWorkerHeights(GridPane gamefield) {
        List<Rectangle> rectsWithHigherWorkerHeights = new ArrayList<>();

        for(Node node : gamefield.getChildren()) {
            if(node instanceof Rectangle) {
                int colIndNode = GridPane.getColumnIndex(node);
                int rowIndNode = GridPane.getRowIndex(node);
                System.out.println(rowIndNode + " , " + colIndNode);

                int heightField = clickCount[rowIndNode][colIndNode];

                System.out.println(heightField);
                System.out.println(workerHeight);

                if(heightField > workerHeight) {
                    rectsWithHigherWorkerHeights.add((Rectangle) node);
                }
            }
        }
        System.out.println("Anzahl rects mit einer höheren Höhe: " + rectsWithHigherWorkerHeights.size());
        return rectsWithHigherWorkerHeights;
    }






    /**
     * Retrieves the rectangle of the GridPane on which a worker is positioned
     * @param node
     * @param gamefield
     * @return the rectangle on which the worker is positioned
     */

    public Rectangle getOwnRect(Node node, GridPane gamefield) {
        int colIndNode = GridPane.getColumnIndex(node);
        int rowIndNode = GridPane.getRowIndex(node);
        Rectangle ownField = new Rectangle();

        for (Node child : gamefield.getChildren()) {
            if (child instanceof Rectangle) {
                int colIndChild = GridPane.getColumnIndex(child);
                int rowIndChild = GridPane.getRowIndex(child);

                if (colIndNode == colIndChild && rowIndNode == rowIndChild) {
                    ownField = (Rectangle) child;
                }
            }
        }
        return ownField;
    }



    /**
     * highlights the fields which are before evaluated as valid for movement
     *
     * @param recList List of fields, which are valid to move to
     */
    private void highlightValidFields(List<Rectangle> recList) {
        for (Rectangle validRect : recList) {
            validRect.setStyle("-fx-stroke: yellow; -fx-stroke-width: 2;");
        }

    }

    /**
     * Clears the highlighting of fields in a given List
     *
     * @param recList
     */

    private void clearFieldHighlighting(List<Rectangle> recList) {
        for (Rectangle validRect : recList) {
            validRect.setStyle("-fx-stroke:black; -fx-stroke-width:1");
        }
    }

    /**
     * Highlights a specific node by increasing the stroke width
     *
     * @param node element of the gamefield
     */
    private void highlightChosenWorker(Node node) {
        node.setStyle("-fx-stroke:black; -fx-stroke-width:4");

    }

    /**
     * Deletes the highlighting of a specific node by decreasing the stroke width again
     *
     * @param node element of the gamefield
     */
    private void clearWorkerHighlighting(Node node) {
        node.setStyle("-fx-stroke:black; -fx-stroke-width:1");

    }

    /**
     * Method to save the height of a field on the gamefield at a specific position,
     * dependent on an element on the gamefield and to set the height accordingly in the GameManager class
     *
     * @param node worker element on the gamefield, to which the height of the field should be saved
     */

    public void saveWorkerHeight(Node node) {
        int colIndCirc = GridPane.getColumnIndex(node);
        int rowIndCirc = GridPane.getRowIndex(node);

        workerHeight = clickCount[rowIndCirc][colIndCirc];
        System.out.println("die höhe des workers ist:" + workerHeight);
        gameManager.setHeightField(workerHeight);

    }

    /**
     * Method to save the height of a field on the gamefield at a specific position
     *
     * @param node worker element on the gamefield, to which the height of the field should be saved, which the worker had before movement
     */

    public int saveWorkerHeightBeforeMovement(Node node) {
        int colIndCirc = GridPane.getColumnIndex(node);
        int rowIndCirc = GridPane.getRowIndex(node);

        int workerHeightBeforeMovement = clickCount[rowIndCirc][colIndCirc];
        System.out.println("die höhe des workers war:" + workerHeightBeforeMovement);

        return workerHeightBeforeMovement;

    }




    /**
     * If a player has won, the method manages what happens. Depending on to which player the just moved worker
     * belongs, the winning conditions are checked and if there is a winner, the winner (his name & turn amount)
     * is displayed in an alert and after closing the alert, the screen switches to the startmenu.
     */

    private boolean handleWinningActionsHeight() {
        if (movingWorker.getId() == circle1.getId() || movingWorker.getId() == circle2.getId() || movingWorker.getId() == circle7.getId()) {
            gameManager.checkWinConditionHeight(player1, workerInTurn);

        } else if (movingWorker.getId() == circle3.getId() || movingWorker.getId() == circle4.getId() || movingWorker.getId() == circle8.getId()) {
            gameManager.checkWinConditionHeight(player2, workerInTurn);

        } else if (movingWorker.getId() == circle5.getId() || movingWorker.getId() == circle6.getId() || movingWorker.getId() == circle9.getId()) {

            gameManager.checkWinConditionHeight(player3, workerInTurn);
        }

        switchToStartAfterWinning();

        return gameManager.getWinnerExists()==true;
    }

    /**
     * Depending on the winning height, the user chooses, the stone bar on the gamefield is adapted
     *
     * @param winningHeight the height of the field the player must move to in order to win the game
     */

    public void setStonesFromWinningHeight(String winningHeight) {
        switch (winningHeight) {
            case "2":
                stone1.setLayoutX(stone2.getLayoutX());
                stone1Label.setLayoutX(stone2Label.getLayoutX());
                stone1Rect.setLayoutX(stone2Rect.getLayoutX());
                stone1X.setLayoutX(stone2X.getLayoutX());

                stone2.setLayoutX(stone3.getLayoutX());
                stone2Label.setLayoutX(stone3Label.getLayoutX());
                stone2Rect.setLayoutX(stone3Rect.getLayoutX());
                stone2X.setLayoutX(stone3X.getLayoutX());

                gameboard.getChildren().remove(stone3);
                gameboard.getChildren().remove(stone3Label);
                gameboard.getChildren().remove(stone3X);
                gameboard.getChildren().remove(stone3Rect);

                heightToWin = 2;

                break;

            case "3":
                heightToWin = 3;
                break;

            case "4":
                Text stoneL4 = new Text("10");
                stoneLevel4 = stoneL4;
                Text stone4Left = new Text("x");
                stone4X = stone4Left;
                Text stoneFourLabel = new Text("L4");
                stone4Label = stoneFourLabel;
                Rectangle stoneFourRect = new Rectangle(10, 10, Color.BLACK);
                stone4Rect = stoneFourRect;
                gameboard.getChildren().addAll(stone4Rect, stoneLevel4, stone4X, stone4Label);

                stoneLevel4.setLayoutX(stone4.getLayoutX() - 40);
                stoneLevel4.setLayoutY(stone4.getLayoutY());
                stoneLevel4.setFont(stone4.getFont());
                stone4X.setLayoutX(domeX.getLayoutX() - 40);
                stone4X.setLayoutY(domeX.getLayoutY());
                stone4X.setFont(domeX.getFont());
                stone4Label.setLayoutX(domeLabel.getLayoutX() - 40);
                stone4Label.setLayoutY(domeLabel.getLayoutY());
                stone4Label.setFont(domeLabel.getFont());
                stone4Rect.setLayoutX(domeRect.getLayoutX() - 40);
                stone4Rect.setLayoutY(domeRect.getLayoutY());
                stone4Rect.setArcHeight(5.0);
                stone4Rect.setArcWidth(5.0);
                stone4Rect.setHeight(26);
                stone4Rect.setWidth(26);
                stone4Rect.setStrokeType(StrokeType.INSIDE);
                stone4Rect.setStroke(Color.BLACK);
                stone4Rect.setFill(Color.GREY);
                stone4Label.toFront();

                stone4.setLayoutX(stone4.getLayoutX() + 40);
                domeX.setLayoutX(domeX.getLayoutX() + 40);
                domeLabel.setLayoutX(domeLabel.getLayoutX() + 40);
                domeRect.setLayoutX(domeRect.getLayoutX() + 40);

                stone1.setLayoutX(stone1.getLayoutX() - 40);
                stone1Label.setLayoutX(stone1Label.getLayoutX() - 40);
                stone1Rect.setLayoutX(stone1Rect.getLayoutX() - 40);
                stone1X.setLayoutX(stone1X.getLayoutX() - 40);

                stone2.setLayoutX(stone2.getLayoutX() - 40);
                stone2Label.setLayoutX(stone2Label.getLayoutX() - 40);
                stone2Rect.setLayoutX(stone2Rect.getLayoutX() - 40);
                stone2X.setLayoutX(stone2X.getLayoutX() - 40);

                stone3.setLayoutX(stone3.getLayoutX() - 40);
                stone3Label.setLayoutX(stone3Label.getLayoutX() - 40);
                stone3Rect.setLayoutX(stone3Rect.getLayoutX() - 40);
                stone3X.setLayoutX(stone3X.getLayoutX() - 40);

                heightToWin = 4;

                break;
            case "5":
                Text stoneL5 = new Text("10");
                stoneLevel5 = stoneL5;
                Text stone5Left = new Text("x");
                stone5X = stone5Left;
                Text stoneFiveLabel = new Text("L5");
                stone5Label = stoneFiveLabel;
                Rectangle stoneFiveRect = new Rectangle(10, 10, Color.BLACK);
                stone5Rect = stoneFiveRect;

                Text stoneLev4 = new Text("10");
                stoneLevel4 = stoneLev4;
                Text stoneFourLeft = new Text("x");
                stone4X = stoneFourLeft;
                Text stoneFourlabel = new Text("L4");
                stone4Label = stoneFourlabel;
                Rectangle stoneFourRectangle = new Rectangle(10, 10, Color.BLACK);
                stone4Rect = stoneFourRectangle;

                gameboard.getChildren().addAll(stone5Rect, stoneLevel5, stone5X, stone5Label, stone4Rect, stoneLevel4, stone4X, stone4Label);

                stoneLevel4.setLayoutX(stone4.getLayoutX() - 40);
                stoneLevel4.setLayoutY(stone4.getLayoutY());
                stoneLevel4.setFont(stone4.getFont());
                stone4X.setLayoutX(domeX.getLayoutX() - 40);
                stone4X.setLayoutY(domeX.getLayoutY());
                stone4X.setFont(domeX.getFont());
                stone4Label.setLayoutX(domeLabel.getLayoutX() - 43);
                stone4Label.setLayoutY(domeLabel.getLayoutY());
                stone4Label.setFont(domeLabel.getFont());
                stone4Rect.setLayoutX(domeRect.getLayoutX() - 40);
                stone4Rect.setLayoutY(domeRect.getLayoutY());
                stone4Rect.setArcHeight(5.0);
                stone4Rect.setArcWidth(5.0);
                stone4Rect.setHeight(26);
                stone4Rect.setWidth(26);
                stone4Rect.setStrokeType(StrokeType.INSIDE);
                stone4Rect.setStroke(Color.BLACK);
                stone4Rect.setFill(Color.GREY);
                stone4Label.toFront();

                stoneLevel5.setLayoutX(stoneLevel4.getLayoutX() + 80);
                stoneLevel5.setLayoutY(stoneLevel4.getLayoutY());
                stoneLevel5.setFont(stoneLevel4.getFont());
                stone5X.setLayoutX(domeX.getLayoutX() + 40);
                stone5X.setLayoutY(domeX.getLayoutY());
                stone5X.setFont(domeX.getFont());
                stone5Label.setLayoutX(domeLabel.getLayoutX() + 37);
                stone5Label.setLayoutY(domeLabel.getLayoutY());
                stone5Label.setFont(domeLabel.getFont());
                stone5Rect.setLayoutX(domeRect.getLayoutX() + 40);
                stone5Rect.setLayoutY(domeRect.getLayoutY());
                stone5Rect.setArcHeight(5.0);
                stone5Rect.setArcWidth(5.0);
                stone5Rect.setHeight(26);
                stone5Rect.setWidth(26);
                stone5Rect.setStrokeType(StrokeType.INSIDE);
                stone5Rect.setStroke(Color.BLACK);
                stone5Rect.setFill(Color.DARKRED);
                stone5Label.toFront();

                stone4.setLayoutX(stone4.getLayoutX() + 120);
                domeX.setLayoutX(domeX.getLayoutX() + 120);
                domeLabel.setLayoutX(domeLabel.getLayoutX() + 120);
                domeRect.setLayoutX(domeRect.getLayoutX() + 120);

                stone1.setLayoutX(stone1.getLayoutX() - 40);
                stone1Label.setLayoutX(stone1Label.getLayoutX() - 40);
                stone1Rect.setLayoutX(stone1Rect.getLayoutX() - 40);
                stone1X.setLayoutX(stone1X.getLayoutX() - 40);

                stone2.setLayoutX(stone2.getLayoutX() - 40);
                stone2Label.setLayoutX(stone2Label.getLayoutX() - 40);
                stone2Rect.setLayoutX(stone2Rect.getLayoutX() - 40);
                stone2X.setLayoutX(stone2X.getLayoutX() - 40);

                stone3.setLayoutX(stone3.getLayoutX() - 40);
                stone3Label.setLayoutX(stone3Label.getLayoutX() - 40);
                stone3Rect.setLayoutX(stone3Rect.getLayoutX() - 40);
                stone3X.setLayoutX(stone3X.getLayoutX() - 40);

                heightToWin = 5;
                break;
        }
        gameManager.setWinningHeight(heightToWin);
    }


    /**
     * Depending on the chosen worker amount, the workers(circles) on the gamefield are setted
     *
     * @param workerAmount the amount of workers per Player for the game chosen in the gamesettings
     */
    public void setWorkersFromAmount(int workerAmount) {

        player1.addWorker(worker1);
        player1.addWorker(worker2);
        player2.addWorker(worker3);
        player2.addWorker(worker4);

        //in case the user chooses to play with 1 worker per player, one worker each is removed from the containers on the gameboard
        if (workerAmount == 1) {
            workerContainerP1.getChildren().remove(circle2);
            workerContainerP2.getChildren().remove(circle4);
            gameboard.getChildren().remove(worker2);
            gameboard.getChildren().remove(worker4);

            //in case of 3-Player-Version
            if (is3Player) {
                workerContainerP3.getChildren().remove(circle6);
                gameboard.getChildren().remove(worker6);

                player3.addWorker(worker5);
            }

        } else if (workerAmount == 2) {
            is2Worker = true;
            if (is3Player) {
                player3.addWorker(worker5);
                player3.addWorker(worker6);
            }
            // in case the user chooses to play with 3 workers per player, one worker each is added to the responding worker container on the gameboard
        } else if (workerAmount == 3) {
            worker7 = new Worker(circle7.getId());
            worker8 = new Worker(circle8.getId());

            circle7.setRadius(circle1.getRadius());
            circle7.setFill(player1.getColor());
            circle7.setStroke(Color.BLACK);
            workerContainerP1.getChildren().add(circle7);
            workerContainerP1.setAlignment(Pos.CENTER);
            workerContainerP1.setMargin(circle1, new Insets(0, 0, -65, 0));
            workerContainerP1.setMargin(circle2, new Insets(0, 0, -65, 0));
            workerContainerP1.setMargin(circle7, new Insets(0, 0, -65, 0));

            circle8.setRadius(circle3.getRadius());
            circle8.setFill(player2.getColor());
            circle8.setStroke(Color.BLACK);
            workerContainerP2.getChildren().add(circle8);
            workerContainerP2.setAlignment(Pos.CENTER);
            workerContainerP2.setMargin(circle3, new Insets(0, 0, -65, 0));
            workerContainerP2.setMargin(circle4, new Insets(0, 0, -65, 0));
            workerContainerP2.setMargin(circle8, new Insets(0, 0, -65, 0));

            player1.addWorker(worker7);
            player2.addWorker(worker8);
            //in case of 3-Player-Version
            if (is3Player) {
                circle9.setRadius(circle5.getRadius());
                circle9.setFill(player3.getColor());
                circle9.setStroke(Color.BLACK);

                worker9 = new Worker(circle9.getId());
                workerContainerP3.getChildren().add(circle9);
                workerContainerP3.setAlignment(Pos.CENTER);
                workerContainerP3.setMargin(circle5, new Insets(0, 0, 0, -45));
                workerContainerP3.setMargin(circle6, new Insets(0, 0, 0, -45));
                workerContainerP3.setMargin(circle9, new Insets(0, 0, 0, -45));

                player3.addWorker(worker9);
                player3.addWorker(worker5);
                player3.addWorker(worker6);

            }

        }
    }

    /**
     * If seperate stone pools are chosen by the user, the collaborative stone pool gets removed and depending on
     * the fieldsize, the height to win and the 3-Player-Mode the stone pools are created and positioned on the gameboard at specific locations.
     * @param seperateStonePools if the seperate stone pools (one stone pool per player) is chosen or not
     */

    public void manageSeperateStonePools(boolean seperateStonePools) {

        stonePoolsSeparated = seperateStonePools;

        //if the user chose to play with stone pools per player
        if (seperateStonePools == true) {

            // removes the stone pool which would be used collaboratively
            removeCollborativeStonePool();

            //implementation for fieldsize 8
            if (fieldsize == 8) {

                //implementation for winning heights below 4
                if(heightToWin < 4) {
                    //manage layout and positioning in case of 2 player mode
                    setUpSeperateStonePools(is3Player);
                    setStrokeSeparateStonePools(player1, stonePoolP1);
                    stonePoolP1.setLayoutX(200);
                    stonePoolP1.setLayoutY(650);
                    gameboard.getChildren().add(stonePoolP1);
                    setStrokeSeparateStonePools(player2, stonePoolP2);
                    stonePoolP2.setLayoutX(460);
                    stonePoolP2.setLayoutY(650);
                    gameboard.getChildren().add(stonePoolP2);
                    //manage layout and positioning in case of 3 player mode
                    if (is3Player) {
                        stonePoolP1.setLayoutX(120);
                        stonePoolP1.setLayoutY(650);
                        stonePoolP2.setLayoutX(380);
                        stonePoolP2.setLayoutY(650);
                        stonePoolP3.setLayoutX(640);
                        stonePoolP3.setLayoutY(650);
                        gameboard.getChildren().add(stonePoolP3);
                        setStrokeSeparateStonePools(player3, stonePoolP3);
                    }
                    //implementation for winning height 4 or above
                } else {
                    //manage layout and positioning in case of 2 player mode
                    setUpSeperateStonePools(is3Player);
                    setStrokeSeparateStonePools(player1, stonePoolP1);
                    stonePoolP1.setLayoutX(160);
                    stonePoolP1.setLayoutY(650);
                    gameboard.getChildren().add(stonePoolP1);
                    setStrokeSeparateStonePools(player2, stonePoolP2);
                    stonePoolP2.setLayoutX(475);
                    stonePoolP2.setLayoutY(650);
                    gameboard.getChildren().add(stonePoolP2);
                    //manage layout and positioning in case of 3 player mode
                    if (is3Player) {
                        stonePoolP1.setLayoutX(35);
                        stonePoolP1.setLayoutY(650);
                        stonePoolP2.setLayoutX(350);
                        stonePoolP2.setLayoutY(650);
                        stonePoolP3.setLayoutX(665);
                        stonePoolP3.setLayoutY(650);
                        gameboard.getChildren().add(stonePoolP3);
                        setStrokeSeparateStonePools(player3, stonePoolP3);
                    }
                }

            //implementation for fieldsize 5
            } else {
                //manage layout and positioning in case of 2 player mode
                //if height to win is larger than 3 the stonepools are positioned among each other
                if (heightToWin > 3) {
                    setUpSeperateStonePools(is3Player);
                    setStrokeSeparateStonePools(player1, stonePoolP1);
                    stonePoolP1.setLayoutX(230);
                    stonePoolP1.setLayoutY(460);
                    gameboard.getChildren().add(stonePoolP1);
                    setStrokeSeparateStonePools(player2, stonePoolP2);
                    stonePoolP2.setLayoutX(230);
                    stonePoolP2.setLayoutY(500);
                    gameboard.getChildren().add(stonePoolP2);
                    //manage layout and positioning in case of 3 player mode
                    if (is3Player) {
                        stonePoolP1.setLayoutX(200);
                        stonePoolP1.setLayoutY(460);
                        stonePoolP2.setLayoutX(200);
                        stonePoolP2.setLayoutY(500);
                        stonePoolP3.setLayoutX(200);
                        stonePoolP3.setLayoutY(540);
                        gameboard.getChildren().add(stonePoolP3);
                        setStrokeSeparateStonePools(player3, stonePoolP3);
                    }


                    //if height to win is lower than 3 or 3 the stonepools are positioned next to each h other
                } else {
                    //manage layout and positioning in case of 2 player mode
                    setUpSeperateStonePools(is3Player);
                    setStrokeSeparateStonePools(player1, stonePoolP1);
                    stonePoolP1.setLayoutX(130);
                    stonePoolP1.setLayoutY(470);
                    gameboard.getChildren().add(stonePoolP1);
                    setStrokeSeparateStonePools(player2, stonePoolP2);
                    stonePoolP2.setLayoutX(350);
                    stonePoolP2.setLayoutY(470);
                    gameboard.getChildren().add(stonePoolP2);
                    //manage layout and positioning in case of 2 player mode
                    if (is3Player) {
                        stonePoolP1.setLayoutX(30);
                        stonePoolP1.setLayoutY(470);
                        stonePoolP2.setLayoutX(250);
                        stonePoolP2.setLayoutY(470);
                        stonePoolP3.setLayoutX(470);
                        stonePoolP3.setLayoutY(470);
                        gameboard.getChildren().add(stonePoolP3);
                        setStrokeSeparateStonePools(player3, stonePoolP3);
                    }
                }
            }

            // else the pools aren't seperated
            } else {
                System.out.println("One collaborative stonepool");
            }
        }

    /**
     * removes collaborative stone pool depending on the height to win
     */
    public void removeCollborativeStonePool() {
        if (heightToWin == 2) {
            gameboard.getChildren().removeAll(stone1Rect, stone2Rect, domeRect, stone1, stone2, stone4,
                    stone1Label, stone2Label, domeLabel, stone1X, stone2X, domeX);
        } else if (heightToWin == 3) {
            gameboard.getChildren().removeAll(stone1Rect, stone2Rect, stone3Rect, domeRect, stone1, stone2, stone3, stone4,
                    stone1Label, stone2Label, stone3Label, domeLabel, stone1X, stone2X, stone3X, domeX);
        } else if (heightToWin == 4) {
            gameboard.getChildren().removeAll(stone1Rect, stone2Rect, stone3Rect, domeRect, stone4Rect, stone1, stone2, stone3, stone4, stoneLevel4,
                    stone1Label, stone2Label, stone3Label, domeLabel, stone4Label, stone1X, stone2X, stone3X, domeX, stone4X);
        } else if (heightToWin == 5) {
            gameboard.getChildren().removeAll(stone1Rect, stone2Rect, stone3Rect, domeRect, stone4Rect, stone5Rect, stone1, stone2, stone3, stone4, stoneLevel4, stoneLevel5,
                    stone1Label, stone2Label, stone3Label, domeLabel, stone4Label, stone5Label, stone1X, stone2X, stone3X, domeX, stone4X, stone5X);
        }
    }

    /**
     * Initial method to create the elements needed when the user chooses seperate stone pools depending on if there are 2 Players or 3 players and the height to win
     * and fills the elments with initial information.
     * @param player3Mode information about if there are 3 Players or not
     */
    public void setUpSeperateStonePools(boolean player3Mode) {


        //Elements for Stone Pool Player 1

        HBox stonePoolPlayer1 = new HBox();

        Text stone1LabelPlayer1 = new Text();
        Text stone2LabelPlayer1 = new Text();
        Text stone3LabelPlayer1 = new Text();
        Text domeLabelPlayer1 = new Text();
        Text stone4LabelPlayer1 = new Text();
        Text stone5LabelPlayer1 = new Text();

        Rectangle stone1RectPlayer1 = new Rectangle();
        Rectangle stone2RectPlayer1 = new Rectangle();
        Rectangle stone3RectPlayer1 = new Rectangle();
        Rectangle domeRectPlayer1 = new Rectangle();
        Rectangle stone4RectPlayer1 = new Rectangle();
        Rectangle stone5RectPlayer1 = new Rectangle();

        Text stone1XPlayer1 = new Text();
        Text stone2XPlayer1 = new Text();
        Text stone3XPlayer1 = new Text();
        Text domeXPlayer1 = new Text();
        Text stone4XPlayer1 = new Text();
        Text stone5XPlayer1 = new Text();

        Text stone1Player1 = new Text();
        Text stone2Player1 = new Text();
        Text stone3Player1 = new Text();
        Text stone4Player1 = new Text();
        Text stoneLevel4Player1 = new Text();
        Text stoneLevel5Player1 = new Text();

        //assign them to class variables
        stone1P1 = stone1Player1;
        stone2P1 = stone2Player1;
        stone3P1 = stone3Player1;
        stone4P1 = stone4Player1;
        stoneLevel4P1 = stoneLevel4Player1;
        stoneLevel5P1 = stoneLevel5Player1;


        //Elements for Stone Pool Player 2

        HBox stonePoolPlayer2 = new HBox();

        Text stone1LabelPlayer2 = new Text();
        Text stone2LabelPlayer2 = new Text();
        Text stone3LabelPlayer2 = new Text();
        Text domeLabelPlayer2 = new Text();
        Text stone4LabelPlayer2 = new Text();
        Text stone5LabelPlayer2 = new Text();

        Rectangle stone1RectPlayer2 = new Rectangle();
        Rectangle stone2RectPlayer2 = new Rectangle();
        Rectangle stone3RectPlayer2 = new Rectangle();
        Rectangle domeRectPlayer2 = new Rectangle();
        Rectangle stone4RectPlayer2 = new Rectangle();
        Rectangle stone5RectPlayer2 = new Rectangle();

        Text stone1XPlayer2 = new Text();
        Text stone2XPlayer2 = new Text();
        Text stone3XPlayer2 = new Text();
        Text domeXPlayer2 = new Text();
        Text stone4XPlayer2 = new Text();
        Text stone5XPlayer2 = new Text();

        Text stone1Player2 = new Text();
        Text stone2Player2 = new Text();
        Text stone3Player2 = new Text();
        Text stoneLevel4Player2 = new Text();
        Text stoneLevel5Player2 = new Text();
        Text stone4Player2 = new Text();

        //assign them to class variables
        stone1P2 = stone1Player2;
        stone2P2 = stone2Player2;
        stone3P2 = stone3Player2;
        stone4P2 = stone4Player2;
        stoneLevel4P2 = stoneLevel4Player2;
        stoneLevel5P2 = stoneLevel5Player2;


        //Elements for Stone Pool Player 3

        HBox stonePoolPlayer3 = new HBox();

        Text stone1LabelPlayer3 = new Text();
        Text stone2LabelPlayer3 = new Text();
        Text stone3LabelPlayer3 = new Text();
        Text domeLabelPlayer3 = new Text();
        Text stone4LabelPlayer3 = new Text();
        Text stone5LabelPlayer3 = new Text();

        Rectangle stone1RectPlayer3 = new Rectangle();
        Rectangle stone2RectPlayer3 = new Rectangle();
        Rectangle stone3RectPlayer3 = new Rectangle();
        Rectangle domeRectPlayer3 = new Rectangle();
        Rectangle stone4RectPlayer3 = new Rectangle();
        Rectangle stone5RectPlayer3 = new Rectangle();

        Text stone1XPlayer3 = new Text();
        Text stone2XPlayer3 = new Text();
        Text stone3XPlayer3 = new Text();
        Text domeXPlayer3 = new Text();
        Text stone4XPlayer3 = new Text();
        Text stone5XPlayer3 = new Text();

        Text stone1Player3 = new Text();
        Text stone2Player3 = new Text();
        Text stone3Player3 = new Text();
        Text stone4Player3 = new Text();
        Text stoneLevel4Player3 = new Text();
        Text stoneLevel5Player3 = new Text();

        //assign them to class variables
        stone1P3 = stone1Player3;
        stone2P3 = stone2Player3;
        stone3P3 = stone3Player3;
        stone4P3 = stone4Player3;
        stoneLevel4P3 = stoneLevel4Player3;
        stoneLevel5P3 = stoneLevel5Player3;



        // per default there are 2 HBoxes created for Player1 and Player2 stone pools with L1, L2 and Dome each
        stone1LabelPlayer1.setText(stone1Label.getText());
        stone2LabelPlayer1.setText(stone2Label.getText());
        domeLabelPlayer1.setText(domeLabel.getText());
        stone1Player1.setText(stone1.getText());
        stone2Player1.setText(stone2.getText());
        stone4Player1.setText(stone4.getText());
        stone1XPlayer1.setText(stone1X.getText());
        stone2XPlayer1.setText(stone2X.getText());
        domeXPlayer1.setText(domeX.getText());


        stone1RectPlayer1.setArcHeight(5.0);
        stone1RectPlayer1.setArcWidth(5.0);
        stone1RectPlayer1.setHeight(20);
        stone1RectPlayer1.setWidth(20);
        stone1RectPlayer1.setStrokeType(StrokeType.INSIDE);
        stone1RectPlayer1.setStroke(Color.BLACK);
        stone1RectPlayer1.setFill(Color.PEACHPUFF);

        stone2RectPlayer1.setArcHeight(5.0);
        stone2RectPlayer1.setArcWidth(5.0);
        stone2RectPlayer1.setHeight(20);
        stone2RectPlayer1.setWidth(20);
        stone2RectPlayer1.setStrokeType(StrokeType.INSIDE);
        stone2RectPlayer1.setStroke(Color.BLACK);
        stone2RectPlayer1.setFill(Color.LIGHTGREEN);

        domeRectPlayer1.setArcHeight(5.0);
        domeRectPlayer1.setArcWidth(5.0);
        domeRectPlayer1.setHeight(20);
        domeRectPlayer1.setWidth(20);
        domeRectPlayer1.setStrokeType(StrokeType.INSIDE);
        domeRectPlayer1.setStroke(Color.BLACK);
        domeRectPlayer1.setFill(Color.RED);

        StackPane stone1PanePlayer1 = new StackPane(stone1RectPlayer1, stone1LabelPlayer1);
        StackPane stone2PanePlayer1 = new StackPane(stone2RectPlayer1, stone2LabelPlayer1);
        StackPane domePanePlayer1 = new StackPane(domeRectPlayer1, domeLabelPlayer1);

        stonePoolPlayer1.getChildren().add(0,stone1PanePlayer1);
        stonePoolPlayer1.getChildren().add(1, stone1XPlayer1);
        stonePoolPlayer1.getChildren().add(2, stone1Player1);
        stonePoolPlayer1.setMargin(stone1PanePlayer1, new Insets(0,3,0,5));

        stonePoolPlayer1.getChildren().add(3,stone2PanePlayer1);
        stonePoolPlayer1.getChildren().add(4, stone2XPlayer1);
        stonePoolPlayer1.getChildren().add(5, stone2Player1);
        stonePoolPlayer1.setMargin(stone2PanePlayer1, new Insets(0,3,0,5));

        stonePoolPlayer1.getChildren().add(6, domePanePlayer1);
        stonePoolPlayer1.getChildren().add(7, domeXPlayer1);
        stonePoolPlayer1.getChildren().add(8, stone4Player1);
        stonePoolPlayer1.setMargin(domePanePlayer1, new Insets(0,3,0,5));

        stonePoolPlayer1.setAlignment(Pos.CENTER);



        //VBox with stones for Player2 is created
        stone1LabelPlayer2.setText(stone1Label.getText());
        stone2LabelPlayer2.setText(stone2Label.getText());
        domeLabelPlayer2.setText(domeLabel.getText());
        stone1Player2.setText(stone1.getText());
        stone2Player2.setText(stone2.getText());
        stone4Player2.setText(stone4.getText());
        stone1XPlayer2.setText(stone1X.getText());
        stone2XPlayer2.setText(stone2X.getText());
        domeXPlayer2.setText(domeX.getText());


        stone1RectPlayer2.setArcHeight(5.0);
        stone1RectPlayer2.setArcWidth(5.0);
        stone1RectPlayer2.setHeight(20);
        stone1RectPlayer2.setWidth(20);
        stone1RectPlayer2.setStrokeType(StrokeType.INSIDE);
        stone1RectPlayer2.setStroke(Color.BLACK);
        stone1RectPlayer2.setFill(Color.PEACHPUFF);

        stone2RectPlayer2.setArcHeight(5.0);
        stone2RectPlayer2.setArcWidth(5.0);
        stone2RectPlayer2.setHeight(20);
        stone2RectPlayer2.setWidth(20);
        stone2RectPlayer2.setStrokeType(StrokeType.INSIDE);
        stone2RectPlayer2.setStroke(Color.BLACK);
        stone2RectPlayer2.setFill(Color.LIGHTGREEN);

        domeRectPlayer2.setArcHeight(5.0);
        domeRectPlayer2.setArcWidth(5.0);
        domeRectPlayer2.setHeight(20);
        domeRectPlayer2.setWidth(20);
        domeRectPlayer2.setStrokeType(StrokeType.INSIDE);
        domeRectPlayer2.setStroke(Color.BLACK);
        domeRectPlayer2.setFill(Color.RED);


        StackPane stone1PanePlayer2 = new StackPane(stone1RectPlayer2, stone1LabelPlayer2);
        StackPane stone2PanePlayer2 = new StackPane(stone2RectPlayer2, stone2LabelPlayer2);
        StackPane domePanePlayer2 = new StackPane(domeRectPlayer2, domeLabelPlayer2);

        stonePoolPlayer2.getChildren().add(0,stone1PanePlayer2);
        stonePoolPlayer2.getChildren().add(1, stone1XPlayer2);
        stonePoolPlayer2.getChildren().add(2, stone1Player2);
        stonePoolPlayer2.setMargin(stone1PanePlayer2, new Insets(0,3,0,5));


        stonePoolPlayer2.getChildren().add(3,stone2PanePlayer2);
        stonePoolPlayer2.getChildren().add(4, stone2XPlayer2);
        stonePoolPlayer2.getChildren().add(5, stone2Player2);
        stonePoolPlayer2.setMargin(stone2PanePlayer2, new Insets(0,3,0,5));

        stonePoolPlayer2.getChildren().add(6, domePanePlayer2);
        stonePoolPlayer2.getChildren().add(7, domeXPlayer2);
        stonePoolPlayer2.getChildren().add(8, stone4Player2);
        stonePoolPlayer2.setMargin(domePanePlayer2, new Insets(0,3,0,5));

        stonePoolPlayer2.setAlignment(Pos.CENTER);


        //VBox with stones for Player3 is created
        stone1LabelPlayer3.setText(stone1Label.getText());
        stone2LabelPlayer3.setText(stone2Label.getText());
        domeLabelPlayer3.setText(domeLabel.getText());
        stone1Player3.setText(stone1.getText());
        stone2Player3.setText(stone2.getText());
        stone4Player3.setText(stone4.getText());
        stone1XPlayer3.setText(stone1X.getText());
        stone2XPlayer3.setText(stone2X.getText());
        domeXPlayer3.setText(domeX.getText());


        stone1RectPlayer3.setArcHeight(5.0);
        stone1RectPlayer3.setArcWidth(5.0);
        stone1RectPlayer3.setHeight(20);
        stone1RectPlayer3.setWidth(20);
        stone1RectPlayer3.setStrokeType(StrokeType.INSIDE);
        stone1RectPlayer3.setStroke(Color.BLACK);
        stone1RectPlayer3.setFill(Color.PEACHPUFF);

        stone2RectPlayer3.setArcHeight(5.0);
        stone2RectPlayer3.setArcWidth(5.0);
        stone2RectPlayer3.setHeight(20);
        stone2RectPlayer3.setWidth(20);
        stone2RectPlayer3.setStrokeType(StrokeType.INSIDE);
        stone2RectPlayer3.setStroke(Color.BLACK);
        stone2RectPlayer3.setFill(Color.LIGHTGREEN);

        domeRectPlayer3.setArcHeight(5.0);
        domeRectPlayer3.setArcWidth(5.0);
        domeRectPlayer3.setHeight(20);
        domeRectPlayer3.setWidth(20);
        domeRectPlayer3.setStrokeType(StrokeType.INSIDE);
        domeRectPlayer3.setStroke(Color.BLACK);
        domeRectPlayer3.setFill(Color.RED);


        StackPane stone1PanePlayer3 = new StackPane(stone1RectPlayer3, stone1LabelPlayer3);
        StackPane stone2PanePlayer3 = new StackPane(stone2RectPlayer3, stone2LabelPlayer3);
        StackPane domePanePlayer3 = new StackPane(domeRectPlayer3, domeLabelPlayer3);

        stonePoolPlayer3.getChildren().add(0,stone1PanePlayer3);
        stonePoolPlayer3.getChildren().add(1, stone1XPlayer3);
        stonePoolPlayer3.getChildren().add(2, stone1Player3);
        stonePoolPlayer3.setMargin(stone1PanePlayer3, new Insets(0,3,0,5));


        stonePoolPlayer3.getChildren().add(3,stone2PanePlayer3);
        stonePoolPlayer3.getChildren().add(4, stone2XPlayer3);
        stonePoolPlayer3.getChildren().add(5, stone2Player3);
        stonePoolPlayer3.setMargin(stone2PanePlayer3, new Insets(0,3,0,5));

        stonePoolPlayer3.getChildren().add(6, domePanePlayer3);
        stonePoolPlayer3.getChildren().add(7, domeXPlayer3);
        stonePoolPlayer3.getChildren().add(8, stone4Player3);
        stonePoolPlayer3.setMargin(domePanePlayer3, new Insets(0,3,0,5));

        stonePoolPlayer3.setAlignment(Pos.CENTER);


        stonePoolP1 = stonePoolPlayer1;
        stonePoolP2 = stonePoolPlayer2;
        stonePoolP3 = stonePoolPlayer3;


        // implementation for P2 with winning height 3
        if (heightToWin == 3) {

            //add stone 3 elements for Player 1 Pool
            stone3LabelPlayer1.setText(stone3Label.getText());
            stone3Player1.setText(stone3.getText());
            stone3XPlayer1.setText(stone3X.getText());

            stone3RectPlayer1.setArcHeight(5.0);
            stone3RectPlayer1.setArcWidth(5.0);
            stone3RectPlayer1.setHeight(20);
            stone3RectPlayer1.setWidth(20);
            stone3RectPlayer1.setStrokeType(StrokeType.INSIDE);
            stone3RectPlayer1.setStroke(Color.BLACK);
            stone3RectPlayer1.setFill(Color.DARKCYAN);

            StackPane stone3PanePlayer1 = new StackPane(stone3RectPlayer1, stone3LabelPlayer1);

            stonePoolPlayer1.getChildren().add(6,stone3PanePlayer1);
            stonePoolPlayer1.getChildren().add(7, stone3XPlayer1);
            stonePoolPlayer1.getChildren().add(8, stone3Player1);
            stonePoolPlayer1.setMargin(stone3PanePlayer1, new Insets(0,3,0,5));


            //add stone 3 elements for Player 2 Pool
            stone3LabelPlayer2.setText(stone3Label.getText());
            stone3Player2.setText(stone3.getText());
            stone3XPlayer2.setText(stone3X.getText());

            stone3RectPlayer2.setArcHeight(5.0);
            stone3RectPlayer2.setArcWidth(5.0);
            stone3RectPlayer2.setHeight(20);
            stone3RectPlayer2.setWidth(20);
            stone3RectPlayer2.setStrokeType(StrokeType.INSIDE);
            stone3RectPlayer2.setStroke(Color.BLACK);
            stone3RectPlayer2.setFill(Color.DARKCYAN);

            StackPane stone3PanePlayer2 = new StackPane(stone3RectPlayer2, stone3LabelPlayer2);

            stonePoolPlayer2.getChildren().add(6,stone3PanePlayer2);
            stonePoolPlayer2.getChildren().add(7, stone3XPlayer2);
            stonePoolPlayer2.getChildren().add(8, stone3Player2);
            stonePoolPlayer2.setMargin(stone3PanePlayer2, new Insets(0,3,0,5));

            if(is3Player) {
                //add stone 3 elements for Player 3 Pool
                stone3LabelPlayer3.setText(stone3Label.getText());
                stone3Player3.setText(stone3.getText());
                stone3XPlayer3.setText(stone3X.getText());

                stone3RectPlayer3.setArcHeight(5.0);
                stone3RectPlayer3.setArcWidth(5.0);
                stone3RectPlayer3.setHeight(20);
                stone3RectPlayer3.setWidth(20);
                stone3RectPlayer3.setStrokeType(StrokeType.INSIDE);
                stone3RectPlayer3.setStroke(Color.BLACK);
                stone3RectPlayer3.setFill(Color.DARKCYAN);

                StackPane stone3PanePlayer3 = new StackPane(stone3RectPlayer3, stone3LabelPlayer3);

                stonePoolPlayer3.getChildren().add(6,stone3PanePlayer3);
                stonePoolPlayer3.getChildren().add(7, stone3XPlayer3);
                stonePoolPlayer3.getChildren().add(8, stone3Player3);
                stonePoolPlayer3.setMargin(stone3PanePlayer3, new Insets(0,3,0,5));
            }

            // implementation for P2 with winning height 4
            } else if (heightToWin == 4) {

            //add stone 3 elements for Player 1 Pool
            stone3LabelPlayer1.setText(stone3Label.getText());
            stone3Player1.setText(stone3.getText());
            stone3XPlayer1.setText(stone3X.getText());

            stone3RectPlayer1.setArcHeight(5.0);
            stone3RectPlayer1.setArcWidth(5.0);
            stone3RectPlayer1.setHeight(20);
            stone3RectPlayer1.setWidth(20);
            stone3RectPlayer1.setStrokeType(StrokeType.INSIDE);
            stone3RectPlayer1.setStroke(Color.BLACK);
            stone3RectPlayer1.setFill(Color.DARKCYAN);

            StackPane stone3PanePlayer1 = new StackPane(stone3RectPlayer1, stone3LabelPlayer1);

            stonePoolPlayer1.getChildren().add(6,stone3PanePlayer1);
            stonePoolPlayer1.getChildren().add(7, stone3XPlayer1);
            stonePoolPlayer1.getChildren().add(8, stone3Player1);
            stonePoolPlayer1.setMargin(stone3PanePlayer1, new Insets(0,3,0,5));


            //add stone 3 elements for Player 2 Pool
            stone3LabelPlayer2.setText(stone3Label.getText());
            stone3Player2.setText(stone3.getText());
            stone3XPlayer2.setText(stone3X.getText());

            stone3RectPlayer2.setArcHeight(5.0);
            stone3RectPlayer2.setArcWidth(5.0);
            stone3RectPlayer2.setHeight(20);
            stone3RectPlayer2.setWidth(20);
            stone3RectPlayer2.setStrokeType(StrokeType.INSIDE);
            stone3RectPlayer2.setStroke(Color.BLACK);
            stone3RectPlayer2.setFill(Color.DARKCYAN);

            StackPane stone3PanePlayer2 = new StackPane(stone3RectPlayer2, stone3LabelPlayer2);

            stonePoolPlayer2.getChildren().add(6,stone3PanePlayer2);
            stonePoolPlayer2.getChildren().add(7, stone3XPlayer2);
            stonePoolPlayer2.getChildren().add(8, stone3Player2);
            stonePoolPlayer2.setMargin(stone3PanePlayer2, new Insets(0,3,0,5));

            //add stone 4 elements for Player 1 Pool
            stone4LabelPlayer1.setText(stone4Label.getText());
            stone4Player1.setText(stone4.getText());
            stone4XPlayer1.setText(stone4X.getText());
            stoneLevel4Player1.setText(stoneLevel4.getText());

            stone4RectPlayer1.setArcHeight(5.0);
            stone4RectPlayer1.setArcWidth(5.0);
            stone4RectPlayer1.setHeight(20);
            stone4RectPlayer1.setWidth(20);
            stone4RectPlayer1.setStrokeType(StrokeType.INSIDE);
            stone4RectPlayer1.setStroke(Color.BLACK);
            stone4RectPlayer1.setFill(Color.GREY);

            StackPane stone4PanePlayer1 = new StackPane(stone4RectPlayer1, stone4LabelPlayer1);

            stonePoolPlayer1.getChildren().add(9,stone4PanePlayer1);
            stonePoolPlayer1.getChildren().add(10, stone4XPlayer1);
            stonePoolPlayer1.getChildren().add(11, stoneLevel4Player1);
            HBox.setMargin(stone4PanePlayer1, new Insets(0,3,0,5));


            //add stone 4 elements for Player 2 Pool
            stone4LabelPlayer2.setText(stone4Label.getText());
            stone4Player2.setText(stone4.getText());
            stone4XPlayer2.setText(stone4X.getText());
            stoneLevel4Player2.setText(stoneLevel4.getText());

            stone4RectPlayer2.setArcHeight(5.0);
            stone4RectPlayer2.setArcWidth(5.0);
            stone4RectPlayer2.setHeight(20);
            stone4RectPlayer2.setWidth(20);
            stone4RectPlayer2.setStrokeType(StrokeType.INSIDE);
            stone4RectPlayer2.setStroke(Color.BLACK);
            stone4RectPlayer2.setFill(Color.GREY);

            StackPane stone4PanePlayer2 = new StackPane(stone4RectPlayer2, stone4LabelPlayer2);

            stonePoolPlayer2.getChildren().add(9,stone4PanePlayer2);
            stonePoolPlayer2.getChildren().add(10, stone4XPlayer2);
            stonePoolPlayer2.getChildren().add(11, stoneLevel4Player2);
            HBox.setMargin(stone4PanePlayer2, new Insets(0,3,0,5));


            if(is3Player) {
                //add stone 3 elements for Player 3 Pool
                stone3LabelPlayer3.setText(stone3Label.getText());
                stone3Player3.setText(stone3.getText());
                stone3XPlayer3.setText(stone3X.getText());

                stone3RectPlayer3.setArcHeight(5.0);
                stone3RectPlayer3.setArcWidth(5.0);
                stone3RectPlayer3.setHeight(20);
                stone3RectPlayer3.setWidth(20);
                stone3RectPlayer3.setStrokeType(StrokeType.INSIDE);
                stone3RectPlayer3.setStroke(Color.BLACK);
                stone3RectPlayer3.setFill(Color.DARKCYAN);

                StackPane stone3PanePlayer3 = new StackPane(stone3RectPlayer3, stone3LabelPlayer3);

                stonePoolPlayer3.getChildren().add(6,stone3PanePlayer3);
                stonePoolPlayer3.getChildren().add(7, stone3XPlayer3);
                stonePoolPlayer3.getChildren().add(8, stone3Player3);
                HBox.setMargin(stone3PanePlayer3, new Insets(0,3,0,5));



                //add stone 4 elements for Player 3 Pool
                stone4LabelPlayer3.setText(stone4Label.getText());
                stone4Player3.setText(stone4.getText());
                stone4XPlayer3.setText(stone4X.getText());
                stoneLevel4Player3.setText(stoneLevel4.getText());

                stone4RectPlayer3.setArcHeight(5.0);
                stone4RectPlayer3.setArcWidth(5.0);
                stone4RectPlayer3.setHeight(20);
                stone4RectPlayer3.setWidth(20);
                stone4RectPlayer3.setStrokeType(StrokeType.INSIDE);
                stone4RectPlayer3.setStroke(Color.BLACK);
                stone4RectPlayer3.setFill(Color.GREY);

                StackPane stone4PanePlayer3 = new StackPane(stone4RectPlayer3, stone4LabelPlayer3);

                stonePoolPlayer3.getChildren().add(9,stone4PanePlayer3);
                stonePoolPlayer3.getChildren().add(10, stone4XPlayer3);
                stonePoolPlayer3.getChildren().add(11, stoneLevel4Player3);
                HBox.setMargin(stone4PanePlayer3, new Insets(0,3,0,5));
            }



            // implementation for P2 with winning height 5
            } else if (heightToWin == 5) {

            //add stone 3 elements for Player 1 Pool
            stone3LabelPlayer1.setText(stone3Label.getText());
            stone3Player1.setText(stone3.getText());
            stone3XPlayer1.setText(stone3X.getText());

            stone3RectPlayer1.setArcHeight(5.0);
            stone3RectPlayer1.setArcWidth(5.0);
            stone3RectPlayer1.setHeight(20);
            stone3RectPlayer1.setWidth(20);
            stone3RectPlayer1.setStrokeType(StrokeType.INSIDE);
            stone3RectPlayer1.setStroke(Color.BLACK);
            stone3RectPlayer1.setFill(Color.DARKCYAN);

            StackPane stone3PanePlayer1 = new StackPane(stone3RectPlayer1, stone3LabelPlayer1);

            stonePoolPlayer1.getChildren().add(6,stone3PanePlayer1);
            stonePoolPlayer1.getChildren().add(7, stone3XPlayer1);
            stonePoolPlayer1.getChildren().add(8, stone3Player1);
            HBox.setMargin(stone3PanePlayer1, new Insets(0,3,0,5));


            //add stone 3 elements for Player 2 Pool
            stone3LabelPlayer2.setText(stone3Label.getText());
            stone3Player2.setText(stone3.getText());
            stone3XPlayer2.setText(stone3X.getText());

            stone3RectPlayer2.setArcHeight(5.0);
            stone3RectPlayer2.setArcWidth(5.0);
            stone3RectPlayer2.setHeight(20);
            stone3RectPlayer2.setWidth(20);
            stone3RectPlayer2.setStrokeType(StrokeType.INSIDE);
            stone3RectPlayer2.setStroke(Color.BLACK);
            stone3RectPlayer2.setFill(Color.DARKCYAN);

            StackPane stone3PanePlayer2 = new StackPane(stone3RectPlayer2, stone3LabelPlayer2);

            stonePoolPlayer2.getChildren().add(6,stone3PanePlayer2);
            stonePoolPlayer2.getChildren().add(7, stone3XPlayer2);
            stonePoolPlayer2.getChildren().add(8, stone3Player2);
            HBox.setMargin(stone3PanePlayer2, new Insets(0,3,0,5));

            //add stone 4 elements for Player 1 Pool
            stone4LabelPlayer1.setText(stone4Label.getText());
            stone4XPlayer1.setText(stone4X.getText());
            stoneLevel4Player1.setText(stoneLevel4.getText());

            stone4RectPlayer1.setArcHeight(5.0);
            stone4RectPlayer1.setArcWidth(5.0);
            stone4RectPlayer1.setHeight(20);
            stone4RectPlayer1.setWidth(20);
            stone4RectPlayer1.setStrokeType(StrokeType.INSIDE);
            stone4RectPlayer1.setStroke(Color.BLACK);
            stone4RectPlayer1.setFill(Color.GREY);

            StackPane stone4PanePlayer1 = new StackPane(stone4RectPlayer1, stone4LabelPlayer1);

            stonePoolPlayer1.getChildren().add(9,stone4PanePlayer1);
            stonePoolPlayer1.getChildren().add(10, stone4XPlayer1);
            stonePoolPlayer1.getChildren().add(11, stoneLevel4Player1);
            HBox.setMargin(stone4PanePlayer1, new Insets(0,3,0,5));


            //add stone 4 elements for Player 2 Pool
            stone4LabelPlayer2.setText(stone4Label.getText());
            stone4XPlayer2.setText(stone4X.getText());
            stoneLevel4Player2.setText(stoneLevel4.getText());

            stone4RectPlayer2.setArcHeight(5.0);
            stone4RectPlayer2.setArcWidth(5.0);
            stone4RectPlayer2.setHeight(20);
            stone4RectPlayer2.setWidth(20);
            stone4RectPlayer2.setStrokeType(StrokeType.INSIDE);
            stone4RectPlayer2.setStroke(Color.BLACK);
            stone4RectPlayer2.setFill(Color.GREY);

            StackPane stone4PanePlayer2 = new StackPane(stone4RectPlayer2, stone4LabelPlayer2);

            stonePoolPlayer2.getChildren().add(9,stone4PanePlayer2);
            stonePoolPlayer2.getChildren().add(10, stone4XPlayer2);
            stonePoolPlayer2.getChildren().add(11, stoneLevel4Player2);
            HBox.setMargin(stone4PanePlayer2, new Insets(0,3,0,5));



            //add stone 5 elements for Player 1 Pool
            stone5LabelPlayer1.setText(stone5Label.getText());
            stone5XPlayer1.setText(stone5X.getText());
            stoneLevel5Player1.setText(stoneLevel5.getText());


            stone5RectPlayer1.setArcHeight(5.0);
            stone5RectPlayer1.setArcWidth(5.0);
            stone5RectPlayer1.setHeight(20);
            stone5RectPlayer1.setWidth(20);
            stone5RectPlayer1.setStrokeType(StrokeType.INSIDE);
            stone5RectPlayer1.setStroke(Color.BLACK);
            stone5RectPlayer1.setFill(Color.DARKRED);

            StackPane stone5PanePlayer1 = new StackPane(stone5RectPlayer1, stone5LabelPlayer1);

            stonePoolPlayer1.getChildren().add(12,stone5PanePlayer1);
            stonePoolPlayer1.getChildren().add(13, stone5XPlayer1);
            stonePoolPlayer1.getChildren().add(14, stoneLevel5Player1);
            HBox.setMargin(stone5PanePlayer1, new Insets(0,3,0,5));


            //add stone 5 elements for Player 2 Pool
            stone5LabelPlayer2.setText(stone5Label.getText());
            stoneLevel5Player2.setText(stoneLevel5.getText());
            stone5XPlayer2.setText(stone5X.getText());


            stone5RectPlayer2.setArcHeight(5.0);
            stone5RectPlayer2.setArcWidth(5.0);
            stone5RectPlayer2.setHeight(20);
            stone5RectPlayer2.setWidth(20);
            stone5RectPlayer2.setStrokeType(StrokeType.INSIDE);
            stone5RectPlayer2.setStroke(Color.BLACK);
            stone5RectPlayer2.setFill(Color.DARKRED);

            StackPane stone5PanePlayer2 = new StackPane(stone5RectPlayer2, stone5LabelPlayer2);

            stonePoolPlayer2.getChildren().add(12,stone5PanePlayer2);
            stonePoolPlayer2.getChildren().add(13, stone5XPlayer2);
            stonePoolPlayer2.getChildren().add(14, stoneLevel5Player2);
            HBox.setMargin(stone5PanePlayer2, new Insets(0,3,0,5));



            if(is3Player) {
                //add stone 3 elements for Player 3 Pool
                stone3LabelPlayer3.setText(stone3Label.getText());
                stone3Player3.setText(stone3.getText());
                stone3XPlayer3.setText(stone3X.getText());

                stone3RectPlayer3.setArcHeight(5.0);
                stone3RectPlayer3.setArcWidth(5.0);
                stone3RectPlayer3.setHeight(20);
                stone3RectPlayer3.setWidth(20);
                stone3RectPlayer3.setStrokeType(StrokeType.INSIDE);
                stone3RectPlayer3.setStroke(Color.BLACK);
                stone3RectPlayer3.setFill(Color.DARKCYAN);

                StackPane stone3PanePlayer3 = new StackPane(stone3RectPlayer3, stone3LabelPlayer3);

                stonePoolPlayer3.getChildren().add(6,stone3PanePlayer3);
                stonePoolPlayer3.getChildren().add(7, stone3XPlayer3);
                stonePoolPlayer3.getChildren().add(8, stone3Player3);
                HBox.setMargin(stone3PanePlayer3, new Insets(0,3,0,5));



                //add stone 4 elements for Player 3 Pool
                stone4LabelPlayer3.setText(stone4Label.getText());
                stone4Player3.setText(stone4.getText());
                stone4XPlayer3.setText(stone4X.getText());
                stoneLevel4Player3.setText(stoneLevel4.getText());

                stone4RectPlayer3.setArcHeight(5.0);
                stone4RectPlayer3.setArcWidth(5.0);
                stone4RectPlayer3.setHeight(20);
                stone4RectPlayer3.setWidth(20);
                stone4RectPlayer3.setStrokeType(StrokeType.INSIDE);
                stone4RectPlayer3.setStroke(Color.BLACK);
                stone4RectPlayer3.setFill(Color.GREY);

                StackPane stone4PanePlayer3 = new StackPane(stone4RectPlayer3, stone4LabelPlayer3);

                stonePoolPlayer3.getChildren().add(9,stone4PanePlayer3);
                stonePoolPlayer3.getChildren().add(10, stone4XPlayer3);
                stonePoolPlayer3.getChildren().add(11, stoneLevel4Player3);
                HBox.setMargin(stone4PanePlayer3, new Insets(0,3,0,5));


                //add stone 5 elements for Player 3 Pool
                stone5LabelPlayer3.setText(stone5Label.getText());
                stoneLevel5Player3.setText(stoneLevel5.getText());
                stone5XPlayer3.setText(stone5X.getText());


                stone5RectPlayer3.setArcHeight(5.0);
                stone5RectPlayer3.setArcWidth(5.0);
                stone5RectPlayer3.setHeight(20);
                stone5RectPlayer3.setWidth(20);
                stone5RectPlayer3.setStrokeType(StrokeType.INSIDE);
                stone5RectPlayer3.setStroke(Color.BLACK);
                stone5RectPlayer3.setFill(Color.DARKRED);

                StackPane stone5PanePlayer3 = new StackPane(stone5RectPlayer3, stone5LabelPlayer3);

                stonePoolPlayer3.getChildren().add(12,stone5PanePlayer3);
                stonePoolPlayer3.getChildren().add(13, stone5XPlayer3);
                stonePoolPlayer3.getChildren().add(14, stoneLevel5Player3);
                HBox.setMargin(stone5PanePlayer3, new Insets(0,3,0,5));

            }
            // there are no separated stone pools
            } else {
                System.out.println("One collaborative stonepool");
            }
        }

    /**
     * sets a stroke in the respective player color around the stone pool
     * @param player player, whose stone pool should be edited with the player's chosen color
     * @param stonePool corresponding stone pool
     */
    public void setStrokeSeparateStonePools(Player player, HBox stonePool) {
        Color borderColor = player.getColor();
        double borderWidth = 3.0;
        BorderStroke borderStroke = new BorderStroke(borderColor, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(borderWidth));
        Border border = new Border(borderStroke);
        stonePool.setBorder(border);
        stonePool.setPadding(new javafx.geometry.Insets(5));

    }

    /**
     * gets the legal moves id
     * @return legal moves id which determines the choice of legal moves by the user
     */

    private int getLegalMovesId() {
        return legalMovesId;
    }

    /**
     * sets the legal moves id from the gamesettings in the game
     * @param legalMovesId legal moves settings chosen by the user in the gamesettings
     */
    public void setLegalMovesId(int legalMovesId) {
        this.legalMovesId = legalMovesId;
    }



    /**
     * Shows the chosen god by player 1 on the gamefield
     * @param chosenGodP1 the god that player1 has chosen in the gamesettings
     */

    public void showGodP1(String chosenGodP1, Color color) {
        VBox godP1 = new VBox();
        godPlayer1 = godP1;
        godTextP1 = new Text(chosenGodP1);
        godTextP1.setFill(color);
        Font godFont = new Font("System Bold", 15.0);
        godTextP1.setFont(godFont);
        godPlayer1.getChildren().add(godTextP1);
        gameboard.getChildren().add(godP1);
        godPlayer1.setLayoutX(61);
        godPlayer1.setLayoutY(399);
        VBox.setMargin(godTextP1, new Insets(0, 0, 0, -10));
    }

    /**
     * Shows the chosen god by player 2 on the gamefield
     * @param chosenGodP2 the god that player1 has chosen in the gamesettings
     */

    public void showGodP2(String chosenGodP2, Color color, int fieldsize) {
        VBox godP2 = new VBox();
        godPlayer2 = godP2;
        godTextP2 = new Text(chosenGodP2);
        godTextP2.setFill(color);
        Font godFont = new Font("System Bold", 15.0);
        godTextP2.setFont(godFont);
        godPlayer2.getChildren().add(godTextP2);
        gameboard.getChildren().add(godP2);
        if(fieldsize==8){
            godPlayer2.setLayoutX(860);

        }else{
            godPlayer2.setLayoutX(590);

        }
        godPlayer2.setLayoutY(399);
        VBox.setMargin(godTextP2, new Insets(0, 40, 0,0));

    }

    /**
     * Shows the chosen god by player 3 on the gamefield, if player 3 exists
     * @param chosenGodP3 the god that player3 has chosen in the gamesettings
     */

    public void showGodP3(String chosenGodP3, Color color) {
        if (player3.getAssignedGod() != null) {
            VBox godP3 = new VBox();
            godPlayer3 = godP3;
             godTextP3 = new Text(chosenGodP3);
            godTextP3.setFill(color);
            Font godFont = new Font("System Bold", 15.0);
            godTextP3.setFont(godFont);
            godPlayer3.getChildren().add(godTextP3);
            gameboard.getChildren().add(godP3);
            godPlayer3.setLayoutX(464);
            godPlayer3.setLayoutY(67);
            VBox.setMargin(godTextP3, new Insets(5, 0, 0, 0));
        }

    }


    /**
     * Switches from the Gameboard to the Startmenu
     */
    public void switchToStartAfterWinning() {
        if(gameManager.getWinnerExists()) {
            loadmainmenu();
        }

    }

    /**
     * glow effect to show who is turn
     * @return
     */
    private DropShadow getGlow() {
        DropShadow glow = new DropShadow();
        glow.setColor(playerInTurn.getColor());
        glow.setWidth(20);
        glow.setHeight(20);
        glow.setSpread(0.8);

        return glow;
    }

    public void loadmainmenu(){
        is3Player=false;
        toMainForAI =true;
        aiSelected=true;

        try {
            URL url = getClass().getResource("/fxml/Startmenu.fxml");
            Parent root = FXMLLoader.load(url);

            Scene mainMenu = new Scene(root,750,500);

            Stage currentStage = (Stage) imagePlayer1.getScene().getWindow();

            currentStage.setScene(mainMenu);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public Worker getWorkerOfCircle(Circle circle){
        Worker tmp = new Worker();
        switch(circle.getId()){
            case "circle1":
                tmp= worker1;
                break;
            case "circle2":
                tmp= worker2;
                break;
            case "circle3":
                tmp= worker3;
                break;
            case "circle4":
                tmp= worker4;
                break;
            case "circle5":
                tmp= worker5;
                break;
            case "circle6":
                tmp= worker6;
                break;
            case "circle7":
                tmp= worker7;
                break;
            case "circle8":
                tmp= worker8;
                break;
            case "circle9":
                tmp= worker9;
                break;
        }
        return tmp;
    }

    public Circle getCircleOfWorker(Worker w){
        Circle tmp = new Circle();
        switch(w.getWorkerID()){
            case "circle1":
                tmp= circle1;
                break;
            case "circle3":
                tmp= circle3;
                break;
            case "circle5":
                tmp= circle5;
                break;
            case "circle2":
                tmp= circle2;
                break;
            case "circle4":
                tmp= circle4;
                break;
            case "circle6":
                tmp= circle6;
                break;
            case "circle7":
                tmp= circle7;
                break;
            case "circle8":
                tmp= circle8;
                break;
            case "circle9":
                tmp= circle9;
                break;
        }
        return tmp;
    }
    public void resetWorkerMoved(Player current) {
        for(Worker w : current.getWorkers()){
            w.setMoved(false);
        }
    }

    private void saveValidFieldsInAi2(Node clickedNode) {

        List<Rectangle> validFields = new ArrayList<>();
        List<Rectangle> validFieldsToBuildOn = new ArrayList<>();
        Circle clickedCircle = (Circle) clickedNode;
        clickedWorker = clickedNode;

        for (Node node : gamefield.getChildren()) {
            if (isGlobe) {
                if (ForGlobeSaveSurroundingFields(clickedNode).contains(node)
                        && saveRectanglesWithoutCircle().contains(node)
                        && saveRectsWithValidHeight(clickedNode).contains(node)) {
                    Rectangle field = (Rectangle) node;
                    validFields.add(field);
                }
                //save validFields to build on
                if (ForGlobeSaveSurroundingFields(clickedNode).contains(node)
                        && saveRectanglesWithoutCircle().contains(node)) {
                    Rectangle field = (Rectangle) node;
                    validFieldsToBuildOn.add(field);
                }
            } else {
                if (saveSurroundingFields(clickedNode).contains(node)
                        && saveRectanglesWithoutCircle().contains(node)
                        && saveRectsWithValidHeight(clickedNode).contains(node)) {
                    Rectangle field = (Rectangle) node;
                    validFields.add(field);
                }
                if (saveSurroundingFields(clickedNode).contains(node)
                        && saveRectanglesWithoutCircle().contains(node)) {
                    Rectangle field = (Rectangle) node;
                    validFieldsToBuildOn.add(field);
                }
            }

        }

        validFieldsToMoveToAI2 = validFields;
        validFieldsToBuildAI2 = validFieldsToBuildOn;

    }
}


















