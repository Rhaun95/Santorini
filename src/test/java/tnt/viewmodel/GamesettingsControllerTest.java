package tnt.viewmodel;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import tnt.model.Player;
import tnt.model.god.*;

import static org.junit.jupiter.api.Assertions.*;



public class GamesettingsControllerTest {

    GamesettingsController gamesettingsController = new GamesettingsController();
    Player testPlayer = new Player("testPlayer", Color.RED, false);

    /**
     * Tests the correct assignment of Gods to the Player 1
     */

    //tests, if the method correctly returns that no God has been chosen, if the God input doesn't equal the available gods

    @Test
    public void testSetGodP1_InvalidGod() {

        String chosenTestGodP1 = "Zeus";
        gamesettingsController.setPlayer1(testPlayer);
        gamesettingsController.setGodP1(chosenTestGodP1);

        assertFalse(gamesettingsController.getPlayer1().isHasGod());
        assertNotEquals(chosenTestGodP1, gamesettingsController.getPlayer1().getAssignedGod());
        assertNull(gamesettingsController.getPlayer1().getGod());


    }

    //tests, if the method correctly returns that no God has been chosen, if the God input doesn't equal the available gods

    @Test
    public void testSetGodP1_EmptyGod() {

        String chosenTestGodP1 = "";
        gamesettingsController.setPlayer1(testPlayer);
        gamesettingsController.setGodP1(chosenTestGodP1);

        assertFalse(gamesettingsController.getPlayer1().isHasGod());
        assertNotEquals(chosenTestGodP1, gamesettingsController.getPlayer1().getAssignedGod());
        assertNull(gamesettingsController.getPlayer1().getGod());


    }


    //tests for every available god, if the method correctly returns the God that's been chosen, if the God input is valid


    @Test
    public void testSetGodP1_ValidGodApollo() {

        String chosenTestGodP1 = "Apollo";
        gamesettingsController.setPlayer1(testPlayer);
        gamesettingsController.setGodP1(chosenTestGodP1);

        assertTrue(gamesettingsController.getPlayer1().isHasGod());
        assertEquals(chosenTestGodP1, gamesettingsController.getPlayer1().getAssignedGod());
        assertNotNull(gamesettingsController.getPlayer1().getGod());
        assertTrue(gamesettingsController.getPlayer1().getGod() instanceof Apollo);


    }



    @Test
    public void testSetGodP1_ValidGodAtlas() {

        String chosenTestGodP1 = "Atlas";
        gamesettingsController.setPlayer1(testPlayer);
        gamesettingsController.setGodP1(chosenTestGodP1);

        assertTrue(gamesettingsController.getPlayer1().isHasGod());
        assertEquals(chosenTestGodP1, gamesettingsController.getPlayer1().getAssignedGod());
        assertNotNull(gamesettingsController.getPlayer1().getGod());
        assertTrue(gamesettingsController.getPlayer1().getGod() instanceof Atlas);


    }



    @Test
    public void testSetGodP1_ValidGodHephaestus() {

        String chosenTestGodP1 = "Hephaestus";
        gamesettingsController.setPlayer1(testPlayer);
        gamesettingsController.setGodP1(chosenTestGodP1);

        assertTrue(gamesettingsController.getPlayer1().isHasGod());
        assertEquals(chosenTestGodP1, gamesettingsController.getPlayer1().getAssignedGod());
        assertNotNull(gamesettingsController.getPlayer1().getGod());
        assertTrue(gamesettingsController.getPlayer1().getGod() instanceof Hephaestus);


    }





    @Test
    public void testSetGodP1_ValidGodPan() {

        String chosenTestGodP1 = "Pan";
        gamesettingsController.setPlayer1(testPlayer);
        gamesettingsController.setGodP1(chosenTestGodP1);

        assertTrue(gamesettingsController.getPlayer1().isHasGod());
        assertEquals(chosenTestGodP1, gamesettingsController.getPlayer1().getAssignedGod());
        assertNotNull(gamesettingsController.getPlayer1().getGod());
        assertTrue(gamesettingsController.getPlayer1().getGod() instanceof Pan);


    }



    /**
     * Tests the correct assignment of Gods to the Player 2
     */


    //tests, if the method correctly returns that no God has been chosen, if the God input doesn't equal the available gods

    @Test
    public void testSetGodP2_InvalidGod() {

        String chosenTestGodP2 = "Loki";
        gamesettingsController.setPlayer2(testPlayer);
        gamesettingsController.setGodP2(chosenTestGodP2);

        assertFalse(gamesettingsController.getPlayer2().isHasGod());
        assertNotEquals(chosenTestGodP2, gamesettingsController.getPlayer2().getAssignedGod());
        assertNull(gamesettingsController.getPlayer2().getGod());


    }

    //tests, if the method correctly returns that no God has been chosen, if the God input doesn't equal the available gods

    @Test
    public void testSetGodP2_EmptyGod() {

        String chosenTestGodP2 = "";
        gamesettingsController.setPlayer2(testPlayer);
        gamesettingsController.setGodP2(chosenTestGodP2);

        assertFalse(gamesettingsController.getPlayer2().isHasGod());
        assertNotEquals(chosenTestGodP2, gamesettingsController.getPlayer2().getAssignedGod());
        assertNull(gamesettingsController.getPlayer2().getGod());


    }


    //tests for every available god, if the method correctly returns the God that's been chosen, if the God input is valid




    @Test
    public void testSetGodP2ValidGodApollo() {

        String chosenTestGodP2 = "Apollo";
        gamesettingsController.setPlayer2(testPlayer);
        gamesettingsController.setGodP2(chosenTestGodP2);

        assertTrue(gamesettingsController.getPlayer2().isHasGod());
        assertEquals(chosenTestGodP2, gamesettingsController.getPlayer2().getAssignedGod());
        assertNotNull(gamesettingsController.getPlayer2().getGod());
        assertTrue(gamesettingsController.getPlayer2().getGod() instanceof Apollo);


    }



    @Test
    public void testSetGod2_ValidGodAtlas() {

        String chosenTestGodP2 = "Atlas";
        gamesettingsController.setPlayer2(testPlayer);
        gamesettingsController.setGodP2(chosenTestGodP2);

        assertTrue(gamesettingsController.getPlayer2().isHasGod());
        assertEquals(chosenTestGodP2, gamesettingsController.getPlayer2().getAssignedGod());
        assertNotNull(gamesettingsController.getPlayer2().getGod());
        assertTrue(gamesettingsController.getPlayer2().getGod() instanceof Atlas);


    }


    @Test
    public void testSetGodP2_ValidGodHephaestus() {

        String chosenTestGodP2 = "Hephaestus";
        gamesettingsController.setPlayer2(testPlayer);
        gamesettingsController.setGodP2(chosenTestGodP2);

        assertTrue(gamesettingsController.getPlayer2().isHasGod());
        assertEquals(chosenTestGodP2, gamesettingsController.getPlayer2().getAssignedGod());
        assertNotNull(gamesettingsController.getPlayer2().getGod());
        assertTrue(gamesettingsController.getPlayer2().getGod() instanceof Hephaestus);


    }



    @Test
    public void testSetGodP2_ValidGodPan() {

        String chosenTestGodP2 = "Pan";
        gamesettingsController.setPlayer2(testPlayer);
        gamesettingsController.setGodP2(chosenTestGodP2);

        assertTrue(gamesettingsController.getPlayer2().isHasGod());
        assertEquals(chosenTestGodP2, gamesettingsController.getPlayer2().getAssignedGod());
        assertNotNull(gamesettingsController.getPlayer2().getGod());
        assertTrue(gamesettingsController.getPlayer2().getGod() instanceof Pan);


    }




    /**
     * Tests the correct assignment of Gods to the Player 3
     */


    //tests, if the method correctly returns that no God has been chosen, if the God input doesn't equal the available gods

    @Test
    public void testSetGodP3_InvalidGod() {

        String chosenTestGodP3 = "Thor";
        gamesettingsController.setPlayer3(testPlayer);
        gamesettingsController.setGodP3(chosenTestGodP3);

        assertFalse(gamesettingsController.getPlayer3().isHasGod());
        assertNotEquals(chosenTestGodP3, gamesettingsController.getPlayer3().getAssignedGod());
        assertNull(gamesettingsController.getPlayer3().getGod());

    }

    //tests, if the method correctly returns that no God has been chosen, if the God input doesn't equal the available gods

    @Test
    public void testSetGodP3_EmptyGod() {

        String chosenTestGodP3= "";
        gamesettingsController.setPlayer3(testPlayer);
        gamesettingsController.setGodP2(chosenTestGodP3);

        assertFalse(gamesettingsController.getPlayer3().isHasGod());
        assertNotEquals(chosenTestGodP3, gamesettingsController.getPlayer3().getAssignedGod());
        assertNull(gamesettingsController.getPlayer3().getGod());


    }


    //tests for every available god, if the method correctly returns the God that's been chosen, if the God input is valid



    @Test
    public void testSetGodP3ValidGodApollo() {

        String chosenTestGodP3 = "Apollo";
        gamesettingsController.setPlayer3(testPlayer);
        gamesettingsController.setGodP3(chosenTestGodP3);

        assertTrue(gamesettingsController.getPlayer3().isHasGod());
        assertEquals(chosenTestGodP3, gamesettingsController.getPlayer3().getAssignedGod());
        assertNotNull(gamesettingsController.getPlayer3().getGod());
        assertTrue(gamesettingsController.getPlayer3().getGod() instanceof Apollo);

    }



    @Test
    public void testSetGod3_ValidGodAtlas() {

        String chosenTestGodP3 = "Atlas";
        gamesettingsController.setPlayer3(testPlayer);
        gamesettingsController.setGodP3(chosenTestGodP3);

        assertTrue(gamesettingsController.getPlayer3().isHasGod());
        assertEquals(chosenTestGodP3, gamesettingsController.getPlayer3().getAssignedGod());
        assertNotNull(gamesettingsController.getPlayer3().getGod());
        assertTrue(gamesettingsController.getPlayer3().getGod() instanceof Atlas);

    }


    @Test
    public void testSetGodP3_ValidGodHephaestus() {

        String chosenTestGodP3 = "Hephaestus";
        gamesettingsController.setPlayer3(testPlayer);
        gamesettingsController.setGodP3(chosenTestGodP3);

        assertTrue(gamesettingsController.getPlayer3().isHasGod());
        assertEquals(chosenTestGodP3, gamesettingsController.getPlayer3().getAssignedGod());
        assertNotNull(gamesettingsController.getPlayer3().getGod());
        assertTrue(gamesettingsController.getPlayer3().getGod() instanceof Hephaestus);

    }




    @Test
    public void testSetGodP3_ValidGodPan() {

        String chosenTestGodP3 = "Pan";
        gamesettingsController.setPlayer3(testPlayer);
        gamesettingsController.setGodP3(chosenTestGodP3);

        assertTrue(gamesettingsController.getPlayer3().isHasGod());
        assertEquals(chosenTestGodP3, gamesettingsController.getPlayer3().getAssignedGod());
        assertNotNull(gamesettingsController.getPlayer3().getGod());
        assertTrue(gamesettingsController.getPlayer3().getGod() instanceof Pan);


    }

}
