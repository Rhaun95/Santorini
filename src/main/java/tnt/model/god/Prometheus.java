package tnt.model.god;

import tnt.model.God;
import tnt.viewmodel.GameboardController;

public class Prometheus implements God {

    public void godSkill(GameboardController gameboardController) {
        System.out.println("Prometheus-Skill wird ausgeführt");
    }
}

