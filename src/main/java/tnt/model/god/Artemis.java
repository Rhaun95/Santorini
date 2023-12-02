package tnt.model.god;

import lombok.Data;
import tnt.model.God;
import tnt.viewmodel.GameboardController;

@Data
public class Artemis implements God {

    private int rest = 2;
    private int previousCol;
    private int previousRow;

    public int getPreviousCol() {
        return previousCol;
    }

    public int getPreviousRow() {
        return previousRow;
    }

    public int getRest() {
        return rest;
    }
    public void setRest(int rest) {
        this.rest = rest;
    }

    public void setPreviousPosition(int col, int row) {
        previousCol = col;
        previousRow = row;
    }
    public void godSkill(GameboardController gameboardController) {
        System.out.println("Artemis-Skill wird ausgeführt");
    }

}
