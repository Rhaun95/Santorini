package tnt.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * This class contains unit tests for the Player class.
 */

public class WorkerTest {

    /**
     * Tests, if the method getPlayerFromCircle returns the respective and correct player for a given worker/circle
     */

    @Test
    public void testGetPlayerFromCircle() {
        Worker worker = new Worker();

        //tests if method correctly returns "player1"
        String testString1 = worker.getPlayerFromCircle("circle1");
        Assertions.assertEquals("player1", testString1);

        //tests if method correctly returns "player2"
        String testString2 = worker.getPlayerFromCircle("circle4");
        Assertions.assertEquals("player2", testString2);


        //tests if method correctly returns "player3"
        String testString3 = worker.getPlayerFromCircle("circle6");
        Assertions.assertEquals("player3", testString3);

        //tests if method correctly returns an empty player, if there is no valid circle
        String testString4 = worker.getPlayerFromCircle("circle10");
        Assertions.assertEquals("", testString4);

        //tests if method correctly returns an empty player, if there is no valid circle
        String testString5 = worker.getPlayerFromCircle("");
        Assertions.assertEquals("", testString5);
    }

}
