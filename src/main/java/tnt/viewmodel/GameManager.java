package tnt.viewmodel;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.shape.Rectangle;
import lombok.Data;
import tnt.model.Player;
import tnt.model.Worker;
import tnt.model.Highscores;

import java.io.*;
import java.util.List;



/**
 * Class which manages the game actions and logic
 *
 * @author Hendrik Gonschor, Veronika Wehr
 *
 */
@Data
public class GameManager implements Serializable {

    /**
     * Height of the field, on which the dropped worker is positioned, is saved here (from gameboard)
     */
    private int heightField = 0;


    /**
     * Amount to which the worker had turn within the game is saved here (from gameboard)
     */

    private int turnAmount;

    private boolean winnerExists = false;

    /**
     * Lists for valid fields per player (depending on worker amount)
     */

    private List<Rectangle> validFieldsPlayer1;

    private List<Rectangle> validFieldsPlayer2;

    private List<Rectangle> validFieldsPlayer3;

    /**
     * The height the user choses for the game to be won
     */

    private int winningHeight;


    private int countWin = 0;

    public int getTurnAmount() {
        return turnAmount;
    }

    public void setTurnAmount(int turnAmount) {
        this.turnAmount = turnAmount;
    }

    public boolean isWinnerExists() {
        return winnerExists;
    }

    public List<Rectangle> getValidFieldsPlayer1() {
        return validFieldsPlayer1;
    }

    public void setValidFieldsPlayer1(List<Rectangle> validFieldsPlayer1) {
        this.validFieldsPlayer1 = validFieldsPlayer1;
    }

    public List<Rectangle> getValidFieldsPlayer2() {
        return validFieldsPlayer2;
    }

    public void setValidFieldsPlayer2(List<Rectangle> validFieldsPlayer2) {
        this.validFieldsPlayer2 = validFieldsPlayer2;
    }

    public List<Rectangle> getValidFieldsPlayer3() {
        return validFieldsPlayer3;
    }

    public void setValidFieldsPlayer3(List<Rectangle> validFieldsPlayer3) {
        this.validFieldsPlayer3 = validFieldsPlayer3;
    }

    public int getWinningHeight() {
        return winningHeight;
    }

    public void setWinningHeight(int winningHeight) {
        this.winningHeight = winningHeight;
    }

    public int getCountWin() {
        return countWin;
    }

    public void setCountWin(int countWin) {
        this.countWin = countWin;
    }

    /**
     * If a winner exists in the game, the player, which the worker was from, has won
     * and this is shown as an alert on the gameboard (with name and turn amount).
     * @param player which is in turn (had just dropped a worker)
     * @param worker which was just dragged and dropped by the player
     */
    public void checkWinConditionHeight(Player player, Worker worker) {
        System.out.println("Aktuelle Feldhöhe: " + heightField);
        System.out.println("Gewählte Gewinnhöhe: " + winningHeight);


            if(winnerCheckHeight() == true) {
                player.setTurnAmount(player.getTurnAmount()+1);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                if(LanguageManager.getCurrentLanguage()== "en") {
                    alert.setTitle("Game ended - Height");
                    alert.setContentText(player.getName() + " has won in " + player.getTurnAmount() + " turns!");
                } else if(LanguageManager.getCurrentLanguage() == "de") {
                    alert.setTitle("Spiel beendet");
                    alert.setContentText(player.getName() + " hat in " + player.getTurnAmount() + " Zügen gewonnen!");
                } else if(LanguageManager.getCurrentLanguage() == "kr") {
                    alert.setTitle(" ");
                    alert.setContentText(player.getName() + " " + player.getTurnAmount() + " !");
                }
                alert.setOnCloseRequest(event -> winnerCheckHeight());
                alert.showAndWait();
                player.setHasWon(true);
                Highscores.updateHighscore(player.getName(), player.getTurnAmount());
                countWin+=1;
            }
        }

    /**
     * If a winner exists in the game because all other workers from the opponent can't move anymore, the game is ended and shows an alert, who the winner is.
     * Then the gameboard is closed and the main menu is opened automatically, so that the player can start a new game immediately
     * @param player which has just build something
     */
        public void checkWinConditionSurroundingP2(Player player) {

            if(winnerCheckSurroundingP3() == true) {
            }

            if (winnerCheckSurroundingP2() == true) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setHeaderText(null);
                if (LanguageManager.getCurrentLanguage() == "en") {
                    alert.setTitle("Game ended - Surrounding");
                    alert.setContentText(player.getName() + " has won in " + player.getTurnAmount() + " turns!");
                } else if (LanguageManager.getCurrentLanguage() == "de") {
                    alert.setTitle("Spiel beendet");
                    alert.setContentText(player.getName() + " hat in " + player.getTurnAmount() + " Zügen gewonnen!");
                } else if (LanguageManager.getCurrentLanguage() == "kr") {
                    alert.setTitle(" ");
                    alert.setContentText(player.getName() + "가 "  + player.getTurnAmount() + "번만에 승리했습니다!");
                }
                alert.setOnCloseRequest(event -> winnerCheckSurroundingP2());
                alert.showAndWait();
                player.setHasWon(true);
                Highscores.updateHighscore(player.getName(), player.getTurnAmount());

            } else {
                System.out.println("No winner through surrounding!");
            }
        }




    /**
     * If a winner exists in the game because all other workers from the opponent can't move anymore, the game is ended and shows an alert, who the winner is.
     * Then the gameboard is closed and the main menu is opened automatically, so that the player can start a new game immediately
     * @param player which has just build something
     */

    public void checkWinConditionSurroundingP3(Player player) {

        if(winnerCheckSurroundingP3() == true) {

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            if(LanguageManager.getCurrentLanguage()== "en") {
                alert.setTitle("Game ended");
                alert.setContentText(player.getName() + " has won in " + player.getTurnAmount() + " turns!");
            } else if(LanguageManager.getCurrentLanguage() == "de") {
                alert.setTitle("Spiel beendet");
                alert.setContentText(player.getName() + " hat in " + player.getTurnAmount() + " Zügen gewonnen!");
            } else if(LanguageManager.getCurrentLanguage() == "kr") {
                alert.setTitle(" ");
                alert.setContentText(player.getName() + "가 "  + player.getTurnAmount() + "번만에 승리했습니다!");
            }
            alert.setOnCloseRequest(event -> winnerCheckSurroundingP3());
            alert.showAndWait();
            player.setHasWon(true);
            Highscores.updateHighscore(player.getName(), player.getTurnAmount());

        } else {
            System.out.println("No winner through surrounding!");
        }
    }


    /**
     * Checks if a winner exists in the game by checking, if the height of the field,
     * on which the dropped worker is positioned, is 3 (Third Level).
      * @return winnerExists true, if there is a winner in the game, else false
     */
    public boolean winnerCheckHeight() {
        if (heightField == winningHeight) {
            winnerExists = true;

        } else {
            winnerExists = false;
        }
        return winnerExists;
    }

    /**
     * Checks if a winner exists in the game by checking, if there is a player, who can't move anymore (2-Player-Version)
     * @return winnerExists true, if there is a winner in the game, else false
     */

    public boolean winnerCheckSurroundingP2() {
            if (((!validFieldsPlayer1.isEmpty()) && validFieldsPlayer2.isEmpty()) ||
                    ((!validFieldsPlayer2.isEmpty()) && validFieldsPlayer1.isEmpty())) {
                winnerExists = true;
            } else {
                winnerExists = false;
            }
        return winnerExists;
    }

    /**
     * Checks if a winner exists in the game by checking, if there is a player, who can't move anymore (3-Player-Version)
     * @return winnerExists true, if there is a winner in the game, else false
     */
    public boolean winnerCheckSurroundingP3() {
        if (validFieldsPlayer3 != null) {
            if (((!validFieldsPlayer1.isEmpty()) && validFieldsPlayer2.isEmpty() && validFieldsPlayer3.isEmpty()) ||
                    ((!validFieldsPlayer2.isEmpty()) && validFieldsPlayer1.isEmpty() && validFieldsPlayer3.isEmpty()) ||
                    ((!validFieldsPlayer3.isEmpty()) && validFieldsPlayer1.isEmpty() && validFieldsPlayer2.isEmpty())) {
                winnerExists = true;
            } else {
                winnerExists = false;
            }
        }
        return winnerExists;
    }


    /**
     * Gets the height of the field the currently dropped worker is positioned on
     * @return heightField
     */
    public int getHeightField() {

        return heightField;
    }


    /**
     * Sets the height of the field the currently dropped worker is positioned on
     * @param heightField
     */
    public void setHeightField(int heightField) {
        this.heightField = heightField;
    }

    public boolean getWinnerExists() {
        return winnerExists;
    }

    public void setWinnerExists(boolean winnerExists) {
        this.winnerExists = winnerExists;
    }


}
