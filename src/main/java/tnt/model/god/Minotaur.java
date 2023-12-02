package tnt.model.god;

import tnt.model.God;
import tnt.viewmodel.GameboardController;

public class Minotaur implements God {

    public void godSkill(GameboardController gameboardController) {
        System.out.println("Minotaur-Skill wird ausgeführt");
    }
}