package tnt.model.god;


import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import tnt.model.God;
import tnt.viewmodel.GameboardController;

import java.util.ArrayList;
import java.util.List;



/**
 * Class to implement the skills needed for the god Apollo
 * @author Veronika Wehr
 */

public class Apollo implements God {

    GameboardController gameboardController = new GameboardController();



    @Override
    public void godSkill(GameboardController gameboardController) {



        System.out.println("Apollo-Skill wird ausgeführt");

    }

    /**
     * Saves the fields on the gamefield which have worker/circles on it (but not the own workers)
     * @param gamefield field on which the game elements are placed and interacted with
     * @param targetNode circle/worker which is clicked
     * @return list of rectangles, on which are currently workers placed (but not the fields with own workers)
     */

     public List<Rectangle> adaptValidFields(GridPane gamefield, Node targetNode) {
         List<Rectangle> recList = new ArrayList<>();
         List<Circle> circList = new ArrayList<>();

        /*Circle currentWorker = gameboardController.getMovingWorker();
       int colIndMovingWorker = GridPane.getColumnIndex(currentWorker);
       int rowIndMovingWorker = GridPane.getRowIndex(currentWorker);
         System.out.println(colIndMovingWorker + rowIndMovingWorker);

         */


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

         int colIndexClickedNode = GridPane.getColumnIndex(targetNode);
         int rowIndexClickedNode = GridPane.getRowIndex(targetNode);

         for(Node node : gamefield.getChildren()) {
             int colIndRect = GridPane.getColumnIndex(node);
             int rowIndRect = GridPane.getRowIndex(node);

             if ((colIndexClickedNode == colIndRect) && (rowIndexClickedNode == rowIndRect)) {
                 rectanglesWithCircle.remove(node);
             }
         }

        Color clickedColor = (Color) (((Circle) targetNode).getFill());

         for (Node node : gamefield.getChildren()) {
             if (node instanceof Circle && node != targetNode) {
                 Circle otherPlayer = (Circle) node;
                 // if a circle with the same color is found
                 if (otherPlayer.getFill().equals(clickedColor)) {
                     // The respective rectangle gets found and removed from the list
                     int colIndRect = GridPane.getColumnIndex(node);
                     int rowIndRect = GridPane.getRowIndex(node);
                     for (Node rectNode : gamefield.getChildren()) {
                         if (rectNode instanceof Rectangle) {
                             int colIndRectNode = GridPane.getColumnIndex(rectNode);
                             int rowIndRectNode = GridPane.getRowIndex(rectNode);
                             if (colIndRect == colIndRectNode && rowIndRect == rowIndRectNode) {
                                 rectanglesWithCircle.remove(rectNode);
                                 break; // It is assumed, that there is only one rectangle, that fits
                             }
                         }
                     }
                 }
             }
         }

         return rectanglesWithCircle;
     }


    /**
     * Moves the opponents worker, with which the current player wants to exchange positions, to the initial position of the current (just moved) worker
     * @param movingWorker the worker which has just moved
     * @param colIndMovingWorkerBefore column index, on which the just moved worker has been positioned before
     * @param rowIndMovingCircBefore row index, on which the just moved worker has been positioned before
     * @param gamefield field on which the game elements are positioned and interacted with
     */



    public void switchPositions(Node movingWorker, int colIndMovingWorkerBefore, int rowIndMovingCircBefore, GridPane gamefield) {

        //The position of the worker, which has just been moved are saved here
        int colIndMovingWorker = GridPane.getColumnIndex(movingWorker);
        int rowIndMovingWorker = GridPane.getRowIndex(movingWorker);

        // for every circle on the gamefield we get the position
        for(Node node : gamefield.getChildren()) {
            if (node instanceof Circle) {
                int colIndCirc = GridPane.getColumnIndex(node);
                int rowIndCirc = GridPane.getRowIndex(node);

                //if the moving worker and any worker on the gamefield are positioned at the same Index and the circle on the gamefield is not the moving worker
                if((colIndMovingWorker == colIndCirc) && (rowIndMovingWorker == rowIndCirc) && (node.getId() != movingWorker.getId())) {

                    GridPane.setColumnIndex(node,colIndMovingWorkerBefore);
                    GridPane.setRowIndex(node,rowIndMovingCircBefore);
                    System.out.println("Die Positionen wurden getauscht");
                }
            }
        }

    }





}
