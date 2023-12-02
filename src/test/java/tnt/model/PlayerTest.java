package tnt.model;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tnt.model.god.Apollo;


import java.util.ArrayList;

/**
 * This class contains unit tests for the Player class.
 */


class PlayerTest {

    Player player = new Player("Cheater", Color.RED, false, false, 0, new ArrayList<>());

    /**
     * Tests if the addWorker method correctly adds one worker to the worker list.
     */

    @Test
    public void testAddWorker() {
        Worker worker1 = new Worker("worker1");
        player.addWorker(worker1);
        Assertions.assertTrue(player.getWorkers().contains(worker1));
    }

    /**
     * Tests if the addWorker method correctly adds multiple worker to the worker list.
     */

    @Test
    public void testAddMultipleWorkers() {
        Worker worker2 = new Worker("worker2");
        Worker worker3 = new Worker("worker3");

        player.addWorker(worker2);
        player.addWorker(worker3);

        Assertions.assertTrue(player.getWorkers().contains(worker2));
        Assertions.assertTrue(player.getWorkers().contains(worker3));

    }

    /**
     * Tests if a god is correctly assigned to the player
     */

    @Test
    public void testAssignGod() {

        God apollo = new Apollo();

        Assertions.assertNull(player.getGod());

        player.assignGod(apollo);

        Assertions.assertNotNull(player.getGod());
        Assertions.assertEquals(apollo, player.getGod());


    }

}