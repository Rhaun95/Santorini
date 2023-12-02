package tnt.model.god;

import javafx.scene.control.Alert;
import lombok.Data;
import tnt.model.God;
import tnt.model.Highscores;
import tnt.model.Player;
import tnt.viewmodel.GameboardController;
import tnt.viewmodel.LanguageManager;

/**
 * Class to implement the skills needed for the god Pan
 * @author Veronika Wehr
 */


@Data
public class Pan implements God {



    /**
     * Variable, in which the absolute difference between the field height before the movement and after the movement of a worker are saved
     */

    private int heightDiffForPan;


    /**
     * Variable, which gives the information, if a winner exists in the game or not
     */

    public boolean winnerExists;



    public void godSkill(GameboardController gameboardController) {
        System.out.println("Pan-Skill wird ausgeführt");
    }


    /**
     * Checks if the delta in moving down is two and if yes, shows winner alert
     * @param diffInMovingDown the absolute height delta through movement
     * @param currentPlayer Player, which is in turn
     */

    public boolean checkWinConditionPan(int diffInMovingDown, Player currentPlayer) {
        if(diffInMovingDown == 2) {
            currentPlayer.setTurnAmount(currentPlayer.getTurnAmount()+1);
            currentPlayer.setHasWon(true);
            Highscores.updateHighscore(currentPlayer.getName(), currentPlayer.getTurnAmount());
            return true;
        } else {
            return false;
        }
    }


    /**
     * Saves the difference in height after a worker has moved
     * @param heightBefore
     * @param heightAfter
     * @return
     */
    public int saveHeightDiffInMovement(int heightBefore, int heightAfter) {
        System.out.println("Die Höhe zuvor war: " + heightBefore + ", " + "Die Höhe danach war: " + heightAfter);
        //int heightDiff = Math.abs(heightAfter-heightBefore);
        int heightDiff = heightBefore-heightAfter;
        heightDiffForPan = heightDiff;
        return heightDiff;
    }


    /**
     * Checks if the height difference is two and if yes, sets the winnerExist variable to true
     * @return true, if a winner exists in the game, false if not
     */
    public boolean winnerCheckHeightDiffPan() {
        if (heightDiffForPan == 2) {
            winnerExists = true;

        } else {
            winnerExists = false;
        }
        return winnerExists;
    }

    public int getHeightDiffForPan() {
        return heightDiffForPan;
    }

    public void setHeightDiffForPan(int heightDiffForPan) {
        this.heightDiffForPan = heightDiffForPan;
    }

}