package tnt.model.god;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import tnt.model.God;
import tnt.viewmodel.GameboardController;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to implement the skills needed for the god Hermes
 * @author Veronika Wehr
 */


public class Hermes implements God {

    GameboardController gameboardController = new GameboardController();

    public void godSkill(GameboardController gameboardController) {
        System.out.println("Hermes-Skill wird ausgeführt");
    }


    /**
     * Saves those rectangles in a list, which are additionally valid for Player, which have chosen Hermes as god
     * @param gamefield
     * @param rectsWithWorkerHeights those rectangles, which have the same height as a worker itself
     * @param clickedNode the worker, which has just been chosen to move
     * @return
     */

    public List<Rectangle> adaptValidFields(GridPane gamefield, List<Rectangle> rectsWithWorkerHeights, Node clickedNode) {

        List<Rectangle> recList = new ArrayList<>();
        List<Circle> circList = new ArrayList<>();
        List<Rectangle> rectsWithWorkerHeightWithoutCircle = new ArrayList<>();



        for (Node child : gamefield.getChildren()) {
            if (child instanceof Rectangle) {
                recList.add((Rectangle) child);
            } else if (child instanceof Circle) {
                circList.add((Circle) child);
            }
        }

        List<Rectangle> rectanglesWithCircle = new ArrayList<>();

        for (Rectangle rectangle : recList) {
            int colIndRec = GridPane.getColumnIndex(rectangle);
            int rowIndRec = GridPane.getRowIndex(rectangle);

            boolean hasCircle = false;

            for (Circle circle : circList) {
                int colIndCirc = GridPane.getColumnIndex(circle);
                int rowIndCirc = GridPane.getRowIndex(circle);

                if ((colIndRec == colIndCirc) && (rowIndRec == rowIndCirc)) {
                    hasCircle = true;
                    break;

                }

            }
            if (hasCircle) {
                rectanglesWithCircle.add(rectangle);
            }


        }

        rectsWithWorkerHeightWithoutCircle = rectsWithWorkerHeights;
        rectsWithWorkerHeightWithoutCircle.removeAll(rectanglesWithCircle);
        rectsWithWorkerHeightWithoutCircle.add(gameboardController.getOwnRect(clickedNode, gamefield));

        return rectsWithWorkerHeightWithoutCircle;
    }
}