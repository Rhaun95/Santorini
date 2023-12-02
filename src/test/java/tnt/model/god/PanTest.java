package tnt.model.god;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import tnt.model.Highscores;
import tnt.model.Player;

import static org.junit.jupiter.api.Assertions.*;

    /**
     * This class contains unit tests for the GameManager class.
     * @author Veronika Wehr
     */

    public class PanTest {

        Pan pan = new Pan();
        Player testPlayer = new Player("testPlayer", Color.RED, false);


        /**
         * Tests if the height diff of movement is calculated saved correctly
         *
         */

        @Test
        public void testSaveHeightDiffInMovement() {

            int heightBefore = 10;
            int heightAfter = 8;
            int expectedHeightDiff = heightBefore - heightAfter;

            int resultHeightDiff = pan.saveHeightDiffInMovement(heightBefore, heightAfter);

            assertEquals(expectedHeightDiff, resultHeightDiff);
            assertEquals(expectedHeightDiff, pan.getHeightDiffForPan());

        }


        /**
         * Tests if the method winnerCheckHeightDiffPan returns correctly true, if the heightDiffForPan is 2
         *
         */

        @Test
        public void testWinnerCheckHeightWinPan_winnerDiff() {
            pan.setHeightDiffForPan(2);
            boolean winnerWithPanSkill = pan.winnerCheckHeightDiffPan();

            assertTrue(winnerWithPanSkill);

        }

        /**
         * Tests if the method winnerCheckHeightDiffPan returns correctly false, if the heightDiffForPan is 3 (above 2)
         *
         */

        @Test
        public void testWinnerCheckHeightWinPan_NoWinnerDiffAbove2() {
            pan.setHeightDiffForPan(3);
            boolean winnerWithPanSkill = pan.winnerCheckHeightDiffPan();

            assertFalse(winnerWithPanSkill);
        }

        /**
         * Tests if the method winnerCheckHeightDiffPan returns correctly false, if the heightDiffForPan is 1 (below 2)
         *
         */

        @Test
        public void testWinnerCheckHeightWinPan_NoWinnerDiffBelow2() {
            pan.setHeightDiffForPan(1);
            boolean winnerWithPanSkill = pan.winnerCheckHeightDiffPan();

            assertFalse(winnerWithPanSkill);
        }

        /**
         * Tests if the method winnerCheckHeightDiffPan returns correctly false, if the heightDiffForPan is negative (-2)
         *
         */

        @Test
        public void testWinnerCheckHeightWinPan_NegativeDiff() {
            pan.setHeightDiffForPan(-2);
            boolean winnerWithPanSkill = pan.winnerCheckHeightDiffPan();

            assertFalse(winnerWithPanSkill);
        }


        /**
         * Tests if the method correctly returns true, if the winning condition for pan is given and the
         * turn amount of the current player gets updated
         */

        @Test
        public void testWinConditionPan_Winning() {

            int diffInMovement = 2;
            testPlayer.setTurnAmount(5);
            boolean conditionCheck = pan.checkWinConditionPan(diffInMovement, testPlayer);
            assertTrue(conditionCheck);
            assertTrue(testPlayer.isHasWon());
            assertEquals(6, testPlayer.getTurnAmount());

        }



        /**
         * Tests if the method correctly returns false, if the winning condition for pan is not given
         * (diff in movement is higher than 2) and therefore the turn amount of the current player does not get updated
         */

        @Test
        public void testWinConditionPan_NoWinningAbove2() {

            int diffInMovement = 3;
            testPlayer.setTurnAmount(5);
            boolean conditionCheck = pan.checkWinConditionPan(diffInMovement, testPlayer);
            assertFalse(conditionCheck);
            assertFalse(testPlayer.isHasWon());
            assertEquals(5, testPlayer.getTurnAmount());

        }

        /**
         * Tests if the method correctly returns false, if the winning condition for pan is not given
         * (diff in movement is lower than 2) and therefore the turn amount of the current player does not get updated
         */

        @Test
        public void testWinConditionPan_NoWinningBelow2() {

            int diffInMovement = 1;
            testPlayer.setTurnAmount(5);
            boolean conditionCheck = pan.checkWinConditionPan(diffInMovement, testPlayer);
            assertFalse(conditionCheck);
            assertFalse(testPlayer.isHasWon());
            assertEquals(5, testPlayer.getTurnAmount());

        }


        /**
         * Tests if the method correctly returns false, if the winning condition for pan is not given
         * (diff in movement is negative / worker moves up) and therefore the turn amount of the current player does not get updated
         */

        @Test
        public void testWinConditionPan_NegativeDiff() {

            int diffInMovement = -2;
            testPlayer.setTurnAmount(5);
            boolean conditionCheck = pan.checkWinConditionPan(diffInMovement, testPlayer);
            assertFalse(conditionCheck);
            assertFalse(testPlayer.isHasWon());
            assertEquals(5, testPlayer.getTurnAmount());

        }

        /**
         * Tests if the method correctly returns false, if the winning condition for pan is not given
         * (there is no diff in movement / the worker moves on the same height) and therefore the turn amount of the current player does not get updated
         */
        @Test
        public void testWinConditionPan_NoDiff() {

            int diffInMovement = 0;
            testPlayer.setTurnAmount(5);
            boolean conditionCheck = pan.checkWinConditionPan(diffInMovement, testPlayer);
            assertFalse(conditionCheck);
            assertFalse(testPlayer.isHasWon());
            assertEquals(5, testPlayer.getTurnAmount());

        }

    }
