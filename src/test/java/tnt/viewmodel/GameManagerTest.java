package tnt.viewmodel;


import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * This class contains unit tests for the GameManager class.
 * @author Veronika Wehr, Hendrik Gonschor
 */

public class GameManagerTest {

    GameManager gameManager = new GameManager();

    /**
     * Tests if the method correctly returns true, if the heightField and the winningHeight match (have the same value)
     */

    @Test
    public void testWinnerCheckHeight_match() {

        gameManager.setHeightField(3);
        gameManager.setWinningHeight(3);

        boolean matchResult = gameManager.winnerCheckHeight();

        assertTrue(matchResult);

    }


    /**
     * Tests if the method correctly returns false, if the heightField and the winningHeight do not match (have different values)
     */

    @Test
    public void testWinnerCheckHeight_nomatch1() {

        gameManager.setHeightField(3);
        gameManager.setWinningHeight(2);

        boolean matchResult = gameManager.winnerCheckHeight();

        assertFalse(matchResult);
    }

    /**
     * Tests if the method correctly returns false, if the heightField and the winningHeight do not match (have different values)
     */

    @Test
    public void testWinnerCheckHeight_nomatch2() {

        gameManager.setHeightField(1);
        gameManager.setWinningHeight(4);

        boolean matchResult = gameManager.winnerCheckHeight();

        assertFalse(matchResult);
    }

    /**
     * Tests for 2 Player Mode, if the method winnerCheckP2 correctly returns true, if Player1 is the winner (if Player1 still has valid fields to move to, but Player 2 has not)
     */
    @Test
    public void testWinnerCheckSurroundingP2_Player1Wins() {

        Rectangle fieldP1 = new Rectangle();
        List<Rectangle> fieldsP1 = new ArrayList<>();
        fieldsP1.add(fieldP1);
        gameManager.setValidFieldsPlayer1(fieldsP1);

        List<Rectangle> fieldsP2 = new ArrayList<>();
        fieldsP2.clear();
        gameManager.setValidFieldsPlayer2(fieldsP2);

        boolean winResult = gameManager.winnerCheckSurroundingP2();

        assertTrue(winResult);

    }


    /**
     * Tests for 2 Player Mode, if the method winnerCheckP2 correctly returns true, if Player2 is the winner (if Player2 still has valid fields to move to, but Player 1 has not)
     */

    @Test
    public void testWinnerCheckSurroundingP2_Player2Wins() {
        Rectangle fieldP2 = new Rectangle();
        List<Rectangle> fieldsP2 = new ArrayList<>();
        fieldsP2.add(fieldP2);
        gameManager.setValidFieldsPlayer2(fieldsP2);

        List<Rectangle> fieldsP1 = new ArrayList<>();
        fieldsP1.clear();
        gameManager.setValidFieldsPlayer1(fieldsP1);

        boolean winResult = gameManager.winnerCheckSurroundingP2();

        assertTrue(winResult);


    }

    /**
     * Tests for 2 Player Mode, if the method winnerCheckP2 correctly returns false, if no Player wins (both Players do still have valid fields to move to)
     */

    @Test
    public void testWinnerCheckSurroundingP2_noWinner() {

        Rectangle fieldP1 = new Rectangle();
        List<Rectangle> fieldsP1 = new ArrayList<>();
        fieldsP1.add(fieldP1);
        gameManager.setValidFieldsPlayer1(fieldsP1);

        Rectangle fieldP2 = new Rectangle();
        List<Rectangle> fieldsP2 = new ArrayList<>();
        fieldsP2.add(fieldP2);
        gameManager.setValidFieldsPlayer2(fieldsP2);

        boolean winResult = gameManager.winnerCheckSurroundingP2();

        assertFalse(winResult);

    }

    /**
     * Tests for 3 Player Mode, if the method winnerCheckP3 correctly returns true, if Player1 is the winner (if Player1 still has valid fields to move to, but Player 2 and Player 3 have not)
     */

    @Test
    public void testWinnerCheckSurroundingP3_Player1Wins() {
        Rectangle fieldP1 = new Rectangle();
        List<Rectangle> fieldsP1 = new ArrayList<>();
        fieldsP1.add(fieldP1);
        gameManager.setValidFieldsPlayer1(fieldsP1);

        List<Rectangle> fieldsP2 = new ArrayList<>();
        fieldsP2.clear();
        gameManager.setValidFieldsPlayer2(fieldsP2);

        List<Rectangle> fieldsP3 = new ArrayList<>();
        fieldsP3.clear();
        gameManager.setValidFieldsPlayer3(fieldsP3);

        boolean winResult = gameManager.winnerCheckSurroundingP3();

        assertTrue(winResult);
    }

    /**
     * Tests for 3 Player Mode, if the method winnerCheckP3 correctly returns true, if Player 2 is the winner (if Player 2 still has valid fields to move to, but Player 1 and Player 3 have not)
     */

    @Test
    public void testWinnerCheckSurroundingP3_Player2Wins() {
        Rectangle fieldP2 = new Rectangle();
        List<Rectangle> fieldsP2 = new ArrayList<>();
        fieldsP2.add(fieldP2);
        gameManager.setValidFieldsPlayer2(fieldsP2);

        List<Rectangle> fieldsP1 = new ArrayList<>();
        fieldsP1.clear();
        gameManager.setValidFieldsPlayer1(fieldsP1);

        List<Rectangle> fieldsP3 = new ArrayList<>();
        fieldsP3.clear();
        gameManager.setValidFieldsPlayer3(fieldsP3);

        boolean winResult = gameManager.winnerCheckSurroundingP3();

        assertTrue(winResult);
    }


    /**
     * Tests for 3 Player Mode, if the method winnerCheckP3 correctly returns true, if Player3 is the winner (if Player3 still has valid fields to move to, but Player 1 and Player 2 have not)
     */

    @Test
    public void testWinnerCheckSurroundingP3_Player3Wins() {
        Rectangle fieldP3 = new Rectangle();
        List<Rectangle> fieldsP3 = new ArrayList<>();
        fieldsP3.add(fieldP3);
        gameManager.setValidFieldsPlayer3(fieldsP3);

        List<Rectangle> fieldsP1 = new ArrayList<>();
        fieldsP1.clear();
        gameManager.setValidFieldsPlayer1(fieldsP1);

        List<Rectangle> fieldsP2 = new ArrayList<>();
        fieldsP2.clear();
        gameManager.setValidFieldsPlayer2(fieldsP2);

        boolean winResult = gameManager.winnerCheckSurroundingP3();

        assertTrue(winResult);
    }


    /**
     * Tests for 3 Player Mode, if the method winnerCheckP3 correctly returns false, if no Player wins (every Player does still have valid fields to move to)
     */

    @Test
    public void testWinnerCheckSurroundingP3_noWinner() {

        Rectangle fieldP1 = new Rectangle();
        List<Rectangle> fieldsP1 = new ArrayList<>();
        fieldsP1.add(fieldP1);
        gameManager.setValidFieldsPlayer1(fieldsP1);

        Rectangle fieldP2 = new Rectangle();
        List<Rectangle> fieldsP2 = new ArrayList<>();
        fieldsP2.add(fieldP2);
        gameManager.setValidFieldsPlayer2(fieldsP2);

        Rectangle fieldP3 = new Rectangle();
        List<Rectangle> fieldsP3 = new ArrayList<>();
        fieldsP3.add(fieldP3);
        gameManager.setValidFieldsPlayer3(fieldsP3);

        boolean winResult = gameManager.winnerCheckSurroundingP3();

        assertFalse(winResult);
    }


}