package tnt.viewmodel;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import tnt.model.Player;
import tnt.model.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@Data
@NoArgsConstructor
class GameboardControllerTest{
    GameboardController gameboardController = new GameboardController();
    List<Worker> workers = new ArrayList<>();


    List<Rectangle> stonesForBuild = new ArrayList<>();

    private GridPane gamefield;

    private GameManager gameManager;

    private Player testPlayer1;

    private Player testPlayer2;

    private Worker testMovingWorker;

    private Circle testCircle1;
    private Circle testCircle2;
    private Circle testCircle3;
    private Circle testCircle4;

    private List<Rectangle> testAllValidFieldsP1;
    private List<Rectangle> testAllValidFieldsP2;
    private List<Rectangle> testAllValidFieldsP3;

    private Rectangle rect1;

    private Rectangle rect2;

    private Rectangle rect3;

    private Rectangle rect4;

    private Rectangle rect5;

    private Rectangle rect6;

    private Rectangle rect7;

    private Rectangle rect8;

    private Rectangle rect9;

    private Rectangle rect10;

    private Rectangle rect11;

    private Rectangle rect12;

    private Rectangle rect13;

    private Rectangle rect14;

    private Rectangle rect15;

    private Rectangle rect16;

    private Rectangle rect17;

    private Rectangle rect18;
    private Rectangle rect19;

    private Rectangle rect20;

    private Rectangle rect21;

    private Rectangle rect22;
    private Rectangle rect23;

    private Rectangle rect24;

    private Rectangle rect25;

    private Circle circ1;

    private int[][] clickCount;

    private Label playerName1;
    private Label playerName2;
    private Rectangle colorPlayer1;
    private Rectangle colorPlayer2;

    @Test
    void updateStoneAndPaintStoneTest() {
        Player player1 = new Player("player1", Color.RED, false, true, 1,workers );

        Worker worker1= new Worker("worker1");
        workers.add(worker1);
        Worker worker2= new Worker("worker2");
        workers.add(worker2);
        Rectangle clicked = new Rectangle();
        clicked.setFill(Color.WHITE);
        Text stone = new Text("3");
        int[][] initialClickCount  =  new int[5][5];
        int row = 0;
        int col = 0;

        gameboardController.setPlayerInTurn(player1);
        gameboardController.setWorkerInTurn(worker1);
        gameboardController.setClickCount(initialClickCount);
        gameboardController.updateStone(clicked, row, col, stone);

        // Assert
        assertEquals("2", stone.getText(), "Expected the stone text to be updated");
        assertEquals(1, gameboardController.getClickCount()[row][col], "Expected the click count to be incremented");
        assertEquals(Color.PEACHPUFF, clicked.getFill(), "Expected the color of the clicked rectangle to change");
        assertEquals(1, gameboardController.getGameManager().getHeightField());
        assertEquals(1, gameboardController.getPlayerInTurn().getTurnAmount());
    }


    @Test
    void buildField() {
        GridPane gamefield = new GridPane();
        gameboardController.gamefield = gamefield;
        gameboardController.buildField(5);

        assertEquals(5, gameboardController.gamefield.getColumnCount());
        assertEquals(5, gameboardController.gamefield.getRowCount());
    }

    @Test
    void testSwitchTurnFor3Players() {
        // Arrange
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        Player player3 = new Player("player3");
        player1.setHasTurn(true);
        player2.setHasTurn(false);
        player3.setHasTurn(false);

        gameboardController.setPlayerInTurn(player1);
        gameboardController.setPlayer1(player1);
        gameboardController.setPlayer2(player2);
        gameboardController.setPlayer3(player3);
        player1.setMoved(true);
        player2.setMoved(false);
        player3.setMoved(false);

        gameboardController.switchTurn();

        assertFalse(player1.isHasTurn(), "Player1 should not have the turn after switchTurn");
        assertTrue(player2.isHasTurn(), "Player2 should have the turn after switchTurn");
        assertFalse(player3.isHasTurn(), "Player3 should not have the turn after switchTurn");
    }







    @BeforeEach
    public void setupForTests() {
        GridPane testGamefield = new GridPane();

        rect1 = new Rectangle();
        rect2 = new Rectangle();
        rect3 = new Rectangle();
        rect4 = new Rectangle();
        rect5 = new Rectangle();

        rect6 = new Rectangle();
        rect7 = new Rectangle();
        rect8 = new Rectangle();
        rect9 = new Rectangle();

        rect10 = new Rectangle();
        rect11 = new Rectangle();
        rect12 = new Rectangle();
        rect13 = new Rectangle();
        rect14 = new Rectangle();

        rect15 = new Rectangle();
        rect16 = new Rectangle();
        rect17 = new Rectangle();
        rect18 = new Rectangle();
        rect19 = new Rectangle();

        rect20 = new Rectangle();
        rect21 = new Rectangle();
        rect22 = new Rectangle();
        rect23 = new Rectangle();
        rect24 = new Rectangle();
        rect25 = new Rectangle();

        testGamefield.add(rect1, 0,0);
        testGamefield.add(rect2, 1,0);
        testGamefield.add(rect3, 2,0);
        testGamefield.add(rect4, 3,0);
        testGamefield.add(rect5, 4,0);

        testGamefield.add(rect6, 0,1);
        testGamefield.add(rect7, 1,1);
        testGamefield.add(rect8, 2,1);
        testGamefield.add(rect9, 3,1);
        testGamefield.add(rect10, 4,1);

        testGamefield.add(rect11, 0,2);
        testGamefield.add(rect12, 1,2);
        testGamefield.add(rect13, 2,2);
        testGamefield.add(rect14, 3,2);
        testGamefield.add(rect15, 4,2);

        testGamefield.add(rect16, 0,3);
        testGamefield.add(rect17, 1,3);
        testGamefield.add(rect18, 2,3);
        testGamefield.add(rect19, 3,3);
        testGamefield.add(rect20, 4,3);

        testGamefield.add(rect21, 0,4);
        testGamefield.add(rect22, 1,4);
        testGamefield.add(rect23, 2,4);
        testGamefield.add(rect24, 3,4);
        testGamefield.add(rect25, 4,4);

        gamefield = testGamefield;


    }

//    @Test
//    public void testSetPlayersAndWorker1(){
//
//        testPlayer1 = new Player("Player1", Color.BLUE, false);
//        testPlayer2 = new Player("Player2", Color.RED, true);
//
//
////        gameboardController.setPlayer1(testPlayer1);
////        gameboardController.setPlayer2(testPlayer2);
////        gameboardController.setPlayerName1(playerName1);
////        gameboardController.setPlayerName2(playerName2);
////        gameboardController.setColorPlayer1(colorPlayer1);
////        gameboardController.setColorPlayer2(colorPlayer2);
//
//        gameboardController.setPlayersAndWorker1(testPlayer1, testPlayer2);
//
//        assertEquals("[BOT] Player1", testPlayer1.getName());
//        assertEquals(Color.BLUE, testPlayer1.getColor());
//        assertEquals("Player2", testPlayer1.getName());
//        assertEquals(Color.RED, testPlayer2.getColor());
//
//        assertEquals("[BOT] Player1", gameboardController.getPlayerName1().getText());
//        assertEquals("Player2", gameboardController.getPlayerName2().getText());
//
//        assertEquals(testPlayer1.getColor(), gameboardController.getCircle1().getFill());
//        assertEquals(testPlayer1.getColor(), gameboardController.getCircle2().getFill());
//        assertEquals(testPlayer2.getColor(), gameboardController.getCircle3().getFill());
//        assertEquals(testPlayer2.getColor(), gameboardController.getCircle4().getFill());
//
//    }
    @Test
    void testSwitchTurnFor2Players() {
        // Arrange
        Player player1 = new Player("player1");
        Player player2 = new Player("player2");
        player1.setHasTurn(true);
        player2.setHasTurn(false);

        gameboardController.setPlayerInTurn(player1);
        gameboardController.setPlayer1(player1);
        gameboardController.setPlayer2(player2);

        gameboardController.switchTurn1();

        assertFalse(player1.isHasTurn(), "Player1 should not have the turn after switchTurn");
        assertTrue(player2.isHasTurn(), "Player2 should have the turn after switchTurn");
    }

    /**
     * Tests if the method correctly returns a List of 3 Rectangles,
     * if the selected node is positioned in a corner
     */

    @Test
    public void testSaveSurroundingFields_inCorner() {

        Rectangle selectedRect = new Rectangle();
        gamefield.add(selectedRect, 0,0);
        gameboardController.gamefield = gamefield;
        List<Rectangle> resultSurroundingFields = gameboardController.saveSurroundingFields(selectedRect);


        //tests if there are 3 surrounding fields
        assertEquals(3, resultSurroundingFields.size());

    }


    /**
     * Tests if the method correctly returns a List of 5 Rectangles,
     * if the selected node is positioned on a border, but not a corner
     */

    @Test
    public void testSaveSurroundingFields_onBorderNotCorner() {

        Rectangle selectedRect = new Rectangle();
        gamefield.add(selectedRect, 2,0);
        gameboardController.gamefield = gamefield;
        List<Rectangle> resultSurroundingFields = gameboardController.saveSurroundingFields(selectedRect);


        //tests if there are 5 surrounding fields
        assertEquals(5, resultSurroundingFields.size());

    }



    /**
     * Tests if the method correctly returns a list of 8 Rectangles,
     * if the selected node is positioned on a field in the center of the GridPane
     */

    @Test
    public void testSaveSurroundingFields_inCenter() {

        Rectangle selectedRect = new Rectangle();
        gamefield.add(selectedRect, 2,2);
        gameboardController.gamefield = gamefield;
        List<Rectangle> resultSurroundingFields = gameboardController.saveSurroundingFields(selectedRect);


        //tests if there are 8 surrounding fields
        assertEquals(8, resultSurroundingFields.size());

    }


    /**
     * Tests if the method correctly returns a list of 22 Rectangles, on which
     * are no circles positioned (because on three fields are circles)
     */
    @Test
    public void testSaveRectanglesWithoutCircle_FewCircles() {

        Circle circ1 = new Circle();
        Circle circ2 = new Circle();
        Circle circ3 = new Circle();

        gamefield.add(circ1, 0, 0);
        gamefield.add(circ2, 3,3);
        gamefield.add(circ3, 2,4);
        gameboardController.gamefield = gamefield;

        List<Rectangle> resultFewCircles = gameboardController.saveRectanglesWithoutCircle();

        assertEquals(22, resultFewCircles.size());
        Assertions.assertFalse(resultFewCircles.contains(rect1));
        Assertions.assertFalse(resultFewCircles.contains(rect19));
        Assertions.assertFalse(resultFewCircles.contains(rect23));


    }

    /**
     * Tests if the method correctly returns a list of 25 Rectangles (all Rectangles), on which
     * are no circles positioned in this test case
     */
    @Test
    public void testSaveRectanglesWithoutCircle_NoCircles() {

        gameboardController.gamefield = gamefield;

        List<Rectangle> resultNoCircles = gameboardController.saveRectanglesWithoutCircle();

        assertEquals(25, resultNoCircles.size());

    }

    /**
     * Tests if the method correctly returns a list of zero Rectangles (none of the rectangles on the gamefield),
     * because on every Rectangle on the gamefield there are circles positioned
     */
    @Test
    public void testSaveRectanglesWithoutCircle_CirclesEverywhere() {

        Circle circ1 = new Circle();
        Circle circ2 = new Circle();
        Circle circ3 = new Circle();
        Circle circ4 = new Circle();
        Circle circ5 = new Circle();

        Circle circ6 = new Circle();
        Circle circ7 = new Circle();
        Circle circ8 = new Circle();
        Circle circ9 = new Circle();
        Circle circ10 = new Circle();

        Circle circ11 = new Circle();
        Circle circ12 = new Circle();
        Circle circ13 = new Circle();
        Circle circ14 = new Circle();
        Circle circ15 = new Circle();

        Circle circ16 = new Circle();
        Circle circ17 = new Circle();
        Circle circ18 = new Circle();
        Circle circ19 = new Circle();
        Circle circ20 = new Circle();

        Circle circ21 = new Circle();
        Circle circ22 = new Circle();
        Circle circ23 = new Circle();
        Circle circ24 = new Circle();
        Circle circ25 = new Circle();


        gamefield.add(circ1, 0, 0);
        gamefield.add(circ2, 0,1);
        gamefield.add(circ3, 0,2);
        gamefield.add(circ4, 0, 3);
        gamefield.add(circ5, 0,4);

        gamefield.add(circ6, 1, 0);
        gamefield.add(circ7, 1,1);
        gamefield.add(circ8, 1,2);
        gamefield.add(circ9, 1, 3);
        gamefield.add(circ10, 1,4);

        gamefield.add(circ11, 2, 0);
        gamefield.add(circ12, 2,1);
        gamefield.add(circ13, 2,2);
        gamefield.add(circ14, 2, 3);
        gamefield.add(circ15, 2,4);

        gamefield.add(circ16, 3, 0);
        gamefield.add(circ17, 3,1);
        gamefield.add(circ18, 3,2);
        gamefield.add(circ19, 3, 3);
        gamefield.add(circ20, 3,4);

        gamefield.add(circ21, 4, 0);
        gamefield.add(circ22, 4,1);
        gamefield.add(circ23, 4,2);
        gamefield.add(circ24, 4, 3);
        gamefield.add(circ25, 4,4);


        gameboardController.gamefield = gamefield;

        List<Rectangle> resultCirclesEverywhere = gameboardController.saveRectanglesWithoutCircle();

        assertEquals(0, resultCirclesEverywhere.size());

    }

    /**
     * Tests if the method returns the correct amount of rectangles on the gamefield with the
     * valid Height regarding the current height of a node
     *
     */

    @Test
    public void testSaveRectsWithValidHeight_SomeValidFields() {

        clickCount = new int[][]{
                {0,0,2,0,1},
                {3,0,0,0,0},
                {0,0,0,4,3},
                {0,0,0,0,2},
                {1,1,0,3,2}
        };
        gameboardController.setClickCount(clickCount);
        gameboardController.gamefield = gamefield;
        gameboardController.setHeightToWin(3);

        List<Rectangle> resultValidHeightRects = gameboardController.saveRectsWithValidHeight(rect7);

        for(Node node : resultValidHeightRects) {
            System.out.println(GridPane.getColumnIndex(node) + ", " + GridPane.getRowIndex(node));
        }

        //expected are that every Rectangle of the gamefield with height 0 and height 1 are inside the list
        Assertions.assertEquals(18, resultValidHeightRects.size());

        //same height
        Assertions.assertTrue(resultValidHeightRects.contains(rect1));

        //one step higher
        Assertions.assertTrue(resultValidHeightRects.contains(rect5));

        //two steps higher
        Assertions.assertFalse(resultValidHeightRects.contains(rect25));


    }

    /**
     * Tests if the method returns correctly, that all rectangles on the gamefield have
     * a valid height
     *
     */
    @Test
    public void testSaveRectsWithValidHeight_AllFieldsValid() {

        clickCount = new int[][]{
                {0,0,1,0,1},
                {1,0,0,0,0},
                {0,0,0,1,1},
                {0,0,0,0,1},
                {1,1,0,1,1}
        };
        gameboardController.setClickCount(clickCount);
        gameboardController.gamefield = gamefield;
        gameboardController.setHeightToWin(3);

        List<Rectangle> resultValidHeightRects = gameboardController.saveRectsWithValidHeight(rect7);

        for(Node node : resultValidHeightRects) {
            System.out.println(GridPane.getColumnIndex(node) + ", " + GridPane.getRowIndex(node));
        }

        //expected are that every Rectangle of the gamefield is inside the list
        Assertions.assertEquals(25, resultValidHeightRects.size());

        //same height
        Assertions.assertTrue(resultValidHeightRects.contains(rect1));

        //one step higher
        Assertions.assertTrue(resultValidHeightRects.contains(rect5));

        //two steps higher
        Assertions.assertTrue(resultValidHeightRects.contains(rect25));


    }


    /**
     * Tests if the method returns correctly that none of the rectangles on the gamefield currently have a valid height
     *
     */
    @Test
    public void testSaveRectsWithValidHeight_NoFieldsValid() {

        clickCount = new int[][]{
                {2,2,2,2,2},
                {2,0,2,2,2},
                {2,2,2,2,2},
                {2,2,2,2,2},
                {2,2,2,2,2}
        };
        gameboardController.setClickCount(clickCount);
        gameboardController.gamefield = gamefield;
        gameboardController.setHeightToWin(3);

        List<Rectangle> resultValidHeightRects = gameboardController.saveRectsWithValidHeight(rect7);

        for(Node node : resultValidHeightRects) {
            System.out.println(GridPane.getColumnIndex(node) + ", " + GridPane.getRowIndex(node));
        }

        //expected are that none of the Rectangle of the gamefield are inside the list - but the one with the node/current height itself
        Assertions.assertEquals(1, resultValidHeightRects.size());

        //same height
        Assertions.assertFalse(resultValidHeightRects.contains(rect1));

        //one step higher
        Assertions.assertFalse(resultValidHeightRects.contains(rect5));

        //two steps higher
        Assertions.assertFalse(resultValidHeightRects.contains(rect25));


    }


    /**
     * Tests if the method correctly saves the fields with the valid height differences if
     * all up / one down is chosen regarding the legal moves
     *
     */
    @Test

    public void testSaveRectsAllUpOneDown_ValidFieldsUpAndDown() {

        clickCount = new int[][]{
                {0, 0, 0, 0, 5},
                {1, 2, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3},
                {2, 2, 4, 4, 4}
        };

        gameboardController.setClickCount(clickCount);
        gameboardController.gamefield = gamefield;
        gameboardController.setHeightToWin(4);

        List<Rectangle> resultAllUpOneDownRects = gameboardController.saveRectsAllUpOneDown(rect7);

        for (Node node : resultAllUpOneDownRects) {
            System.out.println(GridPane.getColumnIndex(node) + ", " + GridPane.getRowIndex(node));
        }

        //expected are that none of the Rectangle of the gamefield are inside the list - but the one with the node/current height itself
        Assertions.assertEquals(20, resultAllUpOneDownRects.size());

        //same height
        Assertions.assertTrue(resultAllUpOneDownRects.contains(rect13));

        //winning height + 1
        Assertions.assertFalse(resultAllUpOneDownRects.contains(rect5));

        //two steps up
        Assertions.assertTrue(resultAllUpOneDownRects.contains(rect25));

        //two steps down
        Assertions.assertFalse(resultAllUpOneDownRects.contains(rect1));

    }

    /**
     * Tests if the method correctly saves the fields with the valid height differences if
     * one up / one down is chosen regarding the legal moves
     *
     */

    @Test

    public void testSaveRectsOneUpOneDown() {

        clickCount = new int[][]{
                {0, 0, 0, 0, 5},
                {1, 3, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3},
                {5, 5, 6, 6, 4}
        };

        gameboardController.setClickCount(clickCount);
        gameboardController.gamefield = gamefield;
        gameboardController.setHeightToWin(5);

        List<Rectangle> resultOneUpOneDownRects = gameboardController.saveRectsOneUpOneDown(rect7);

        for (Node node : resultOneUpOneDownRects) {
            System.out.println(GridPane.getColumnIndex(node) + ", " + GridPane.getRowIndex(node));
        }

        //amount of rectangles in the list
        assertEquals(12, resultOneUpOneDownRects.size());

        //same height
        Assertions.assertTrue(resultOneUpOneDownRects.contains(rect16));

        // one step up
        Assertions.assertTrue(resultOneUpOneDownRects.contains(rect25));

        // one step down
        Assertions.assertTrue(resultOneUpOneDownRects.contains(rect11));

        // > 1 step up
        Assertions.assertFalse(resultOneUpOneDownRects.contains(rect5));

        // > 1 step down
        Assertions.assertFalse(resultOneUpOneDownRects.contains(rect2));
    }


    /**
     * Tests if the method correctly saves the rectangles, which do not have a dome on it
     * if there is one dome on the gamefield.
     */
    @Test
    public void testSaveDomesOnGamefield_OneDome() {

        rect1.setFill(Color.RED);

        gameboardController.gamefield = gamefield;

        List<Rectangle> resultDomeRect = gameboardController.saveDomesOnGamefield();

        Assertions.assertEquals(24, resultDomeRect.size());

    }


    /**
     * Tests if the method correctly saves the rectangles, which do not have a dome on it
     * if there is no dome on the gamefield.
     */

    @Test
    public void testSaveDomesOnGamefield_NoDome() {

        rect1.setFill(Color.BLUE);
        rect2.setFill(Color.VIOLET);
        rect3.setFill(Color.BLUE);

        gameboardController.gamefield = gamefield;

        List<Rectangle> resultDomeRect = gameboardController.saveDomesOnGamefield();

        Assertions.assertEquals(25, resultDomeRect.size());

    }


    /**
     * Tests if the method correctly saves the rectangles, which do not have a dome on it
     * if there are several domes on the gamefield.
     */

    @Test
    public void testSaveDomesOnGamefield_SeveralDomes() {

        rect1.setFill(Color.RED);
        rect2.setFill(Color.RED);
        rect3.setFill(Color.RED);

        gameboardController.gamefield = gamefield;

        List<Rectangle> resultDomeRect = gameboardController.saveDomesOnGamefield();

        Assertions.assertEquals(22, resultDomeRect.size());

    }

    /**
     * Tests if the method correctly saves all the rectangles on the gameboard with 25 Rectangles
     */

    @Test
    public void testAllRectsOnGamefield_25Rects() {

        gameboardController.gamefield = gamefield;

        List<Rectangle> resultAllRects = gameboardController.allRectsOnGamefield();

        assertEquals(25, resultAllRects.size());

    }

    /**
     * Tests if the method correctly saves all the rectangles on the gameboard with 25 Rectangles
     */

    @Test
    public void testAllRectsOnGamefield_20Rects() {

        gamefield.getChildren().removeAll(rect1, rect2, rect3, rect4, rect5);

        gameboardController.gamefield = gamefield;

        List<Rectangle> resultAllRects = gameboardController.allRectsOnGamefield();

        assertEquals(20, resultAllRects.size());

    }

    /**
     * Tests if the method correctly saves 0 rectangles, when there are no rectangles on the gamefield
     */

    @Test
    public void testAllRectsOnGamefield_NoRects() {

        gamefield.getChildren().clear();

        gameboardController.gamefield = gamefield;

        List<Rectangle> resultAllRects = gameboardController.allRectsOnGamefield();

        assertEquals(0, resultAllRects.size());

    }

    /**
     * Tests if the method correctly returns the height of a given worker
     */
    @Test
    public void testSaveWorkerHeight() {
        clickCount = new int[][]{
                {0, 0, 0, 0, 5},
                {1, 3, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3},
                {5, 5, 6, 6, 4}
        };

        gameboardController.setClickCount(clickCount);

        Circle testWorker = new Circle();

        gamefield.add(testWorker,1,1);

        gameboardController.gamefield = gamefield;

        gameboardController.saveWorkerHeight(testWorker);

        Assertions.assertEquals(3,gameboardController.getWorkerHeight());

    }


    /**
     * Tests, if the method correctly calculates the height of the field for a given worker
     */

    @Test
    public void testSaveWorkerHeightBeforeMovement() {
        clickCount = new int[][]{
                {0, 0, 0, 0, 5},
                {1, 3, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3},
                {5, 5, 6, 6, 4}
        };

        gameboardController.setClickCount(clickCount);

        Circle testWorker = new Circle();

        gamefield.add(testWorker,2,4);

        gameboardController.gamefield = gamefield;

        int resultHeightBeforeMovement = gameboardController.saveWorkerHeightBeforeMovement(testWorker);

        Assertions.assertEquals(6,resultHeightBeforeMovement);
    }


    /**
     * Tests, if the method correctly saves those rectangles in a list, which have the same height as the worker itself
     */

    @Test
    public void testRectsWithWorkerHeights_fewRectsWithSameHeight() {

        clickCount = new int[][]{
                {1, 0, 0, 0, 5},
                {1, 3, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3},
                {5, 5, 6, 6, 4}
        };

        gameboardController.gamefield = gamefield;
        gameboardController.clickCount = clickCount;

        gameboardController.setWorkerHeight(1);

        List<Rectangle> resultRects = gameboardController.rectsWithWorkerHeights(gamefield);

        Assertions.assertEquals(5, resultRects.size());
        Assertions.assertTrue(resultRects.contains(rect1));
        Assertions.assertFalse(resultRects.contains(rect25));



    }



    /**
     * Tests, if the method correctly saves no rectangles in a list, in case there is no field
     * with the same height as the worker
     */

    @Test
    public void testRectsWithWorkerHeights_noRectsWithSameHeight() {

        clickCount = new int[][]{
                {1, 0, 0, 0, 5},
                {1, 2, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {1, 2, 2, 1, 2},
                {5, 5, 6, 6, 4}
        };

        gameboardController.gamefield = gamefield;
        gameboardController.clickCount = clickCount;

        gameboardController.setWorkerHeight(3);

        List<Rectangle> resultRects = gameboardController.rectsWithWorkerHeights(gamefield);

        Assertions.assertEquals(0, resultRects.size());
        Assertions.assertFalse(resultRects.contains(rect1));
        Assertions.assertFalse(resultRects.contains(rect17));
        Assertions.assertFalse(resultRects.contains(rect25));
        Assertions.assertFalse(resultRects.contains(rect5));
        Assertions.assertFalse(resultRects.contains(rect23));


    }


    /**
     * Tests, if the method correctly saves all rectangles from the gamefield in a list,
     * in case all the fields have the same height as the worker itself
     */

    @Test
    public void testRectsWithWorkerHeights_allRectsWithSameHeight() {

        clickCount = new int[][]{
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2}
        };

        gameboardController.gamefield = gamefield;
        gameboardController.clickCount = clickCount;

        gameboardController.setWorkerHeight(2);

        List<Rectangle> resultRects = gameboardController.rectsWithWorkerHeights(gamefield);

        Assertions.assertEquals(25, resultRects.size());
        Assertions.assertTrue(resultRects.contains(rect1));
        Assertions.assertTrue(resultRects.contains(rect17));
        Assertions.assertTrue(resultRects.contains(rect25));
        Assertions.assertTrue(resultRects.contains(rect5));
        Assertions.assertTrue(resultRects.contains(rect23));


    }

    /**
     * Tests, if the method correctly returns the rectangle, which is the one,
     * where a specific worker is positioned
     */

    @Test

    public void testGetOwnRect() {

        Circle circ1 = new Circle();
        Circle circ2 = new Circle();
        Circle circ3 = new Circle();

        gamefield.add(circ1, 0, 0);
        gamefield.add(circ2, 3,3);
        gamefield.add(circ3, 2,4);


        gameboardController.gamefield = gamefield;

        Rectangle resultRect = gameboardController.getOwnRect(circ1, gameboardController.gamefield);

        Assertions.assertEquals(rect1,resultRect);
        Assertions.assertNotEquals(rect2, resultRect);
        Assertions.assertNotEquals(rect25, resultRect);

    }

    /**
     * Tests, if the method correctly saves those rectangles in a list, which have a higher height as the worker itself,
     * in case that there are a few Rectangles with a heigher height on the gamefield
     */

    @Test
    public void testRectsWithHigherWorkerHeights_fewRectsWithHigherHeight() {

        clickCount = new int[][]{
                {1, 0, 0, 0, 5},
                {1, 3, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {3, 3, 3, 3, 3},
                {5, 5, 6, 6, 4}
        };


        gameboardController.gamefield = gamefield;
        gameboardController.clickCount = clickCount;

        gameboardController.setWorkerHeight(3);

        List<Rectangle> resultRects = gameboardController.rectsWithHigherWorkerHeights(gamefield);

        Assertions.assertEquals(6, resultRects.size());
        Assertions.assertTrue(resultRects.contains(rect5));
        Assertions.assertFalse(resultRects.contains(rect25));


    }


    /**
     * Tests, if the method correctly saves those rectangles in a list, which have a higher height as the worker itself,
     * in case that there are no Rectangles with a heigher height on the gamefield
     */

    @Test
    public void testRectsWithHigherWorkerHeights_noRectsWithHigherHeight() {

        clickCount = new int[][]{
                {1, 0, 0, 0, 1},
                {1, 2, 1, 1, 1},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 3, 3},
                {2, 2, 3, 3, 1}
        };


        gameboardController.gamefield = gamefield;
        gameboardController.clickCount = clickCount;

        gameboardController.setWorkerHeight(3);

        List<Rectangle> resultRects = gameboardController.rectsWithHigherWorkerHeights(gamefield);

        Assertions.assertEquals(0, resultRects.size());
        Assertions.assertFalse(resultRects.contains(rect5));
        Assertions.assertFalse(resultRects.contains(rect25));


    }


    /**
     * Tests, if the method correctly saves those rectangles in a list, which have a higher height as the worker itself,
     * in case that all rectangles on the gamefield have a higher height than the worker itself
     */

    @Test
    public void testRectsWithHigherWorkerHeights_allRectsWithHigherHeight() {

        clickCount = new int[][]{
                {3, 3, 3, 3, 3},
                {3, 3, 3, 4, 4},
                {4, 4, 4, 4, 4},
                {5, 5, 5, 5, 5},
                {4, 4, 4, 5, 5}
        };


        gameboardController.gamefield = gamefield;
        gameboardController.clickCount = clickCount;

        gameboardController.setWorkerHeight(2);

        List<Rectangle> resultRects = gameboardController.rectsWithHigherWorkerHeights(gamefield);

        Assertions.assertEquals(25, resultRects.size());
        Assertions.assertTrue(resultRects.contains(rect5));
        Assertions.assertTrue(resultRects.contains(rect25));


    }

    /**
     * Tests if the method correctly returns true, if it's the 3-Player-Mode and any of
     * the players chose "Athena" as god-skill
     */

    @Test
    public void testAnyPlayerHasAthena_AthenaInGameP3() {
        gameboardController.set3Player(true);
        Player testPlayer1 = new Player("Cheater1", Color.RED, false);
        Player testPlayer2 = new Player("Cheater2", Color.RED, false);
        Player testPlayer3 = new Player("Cheater3", Color.RED, false);
        testPlayer1.setAssignedGod("Athena");
        testPlayer2.setAssignedGod("Atlas");
        testPlayer3.setAssignedGod("Apollo");

        gameboardController.setPlayer1(testPlayer1);
        gameboardController.setPlayer2(testPlayer2);
        gameboardController.setPlayer3(testPlayer3);

        boolean athenaGameResult = gameboardController.anyPlayerHasAthena();

        assertTrue(athenaGameResult);
    }


    /**
     * Tests if the method correctly returns false, if it's the 3-Player-Mode and none of
     * the players chose "Athena" as god-skill
     */

    @Test
    public void testAnyPlayerHasAthena_AthenaNotInGameP3() {
        gameboardController.set3Player(true);
        Player testPlayer1 = new Player("Cheater1", Color.RED, false);
        Player testPlayer2 = new Player("Cheater2", Color.RED, false);
        Player testPlayer3 = new Player("Cheater3", Color.RED, false);
        testPlayer1.setAssignedGod("Hermes");
        testPlayer2.setAssignedGod("Atlas");
        testPlayer3.setAssignedGod("Apollo");

        gameboardController.setPlayer1(testPlayer1);
        gameboardController.setPlayer2(testPlayer2);
        gameboardController.setPlayer3(testPlayer3);

        boolean athenaGameResult = gameboardController.anyPlayerHasAthena();

        assertFalse(athenaGameResult);
    }


    /**
     * Tests if the method correctly returns true, if it's the 2-Player-Mode and any of
     * the players chose "Athena" as god-skill
     */

    @Test
    public void testAnyPlayerHasAthena_AthenaInGameP2() {
        gameboardController.set3Player(false);
        Player testPlayer1 = new Player("Cheater1", Color.RED, false);
        Player testPlayer2 = new Player("Cheater2", Color.RED, false);
        testPlayer1.setAssignedGod("Hermes");
        testPlayer2.setAssignedGod("Athena");

        gameboardController.setPlayer1(testPlayer1);
        gameboardController.setPlayer2(testPlayer2);


        boolean athenaGameResult = gameboardController.anyPlayerHasAthena();

        assertTrue(athenaGameResult);
    }

    /**
     * Tests if the method correctly returns false, if it's the 2-Player-Mode and none of
     * the players chose "Athena" as god-skill
     */

    @Test
    public void testAnyPlayerHasAthena_AthenaNotInGameP2() {
        gameboardController.set3Player(false);
        Player testPlayer1 = new Player("Cheater1", Color.RED, false);
        Player testPlayer2 = new Player("Cheater2", Color.RED, false);
        testPlayer1.setAssignedGod("Hermes");
        testPlayer2.setAssignedGod("Pan");

        gameboardController.setPlayer1(testPlayer1);
        gameboardController.setPlayer2(testPlayer2);


        boolean athenaGameResult = gameboardController.anyPlayerHasAthena();

        assertFalse(athenaGameResult);
    }
}