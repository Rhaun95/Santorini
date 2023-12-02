package tnt.model.god;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tnt.viewmodel.GameboardController;

import java.util.List;

public class HermesTest {

    Hermes hermes = new Hermes();
    GridPane gamefield = new GridPane();

    int[][] clickCount;

    GameboardController gameboardController = new GameboardController();

    /**
     * Tests if the method correctly returns some rects, in case that there are some rectangles with the same height,
     * that the worker inhabits and that there are just two other circles in the way
     */

    @Test
    public void testAdaptValidFields_FewRectsWithSameHeightAndTwoOtherCircles() {


        Circle circle1 = new Circle();
        Circle circle2 = new Circle();
        Circle circle3 = new Circle();
        Rectangle rect1 = new Rectangle();
        Rectangle rect2 = new Rectangle();
        Rectangle rect3 = new Rectangle();
        Rectangle rect4 = new Rectangle();


        gamefield.add(rect1, 0,0);
        gamefield.add(rect2, 1,1);
        gamefield.add(rect3, 2,2);
        gamefield.add(rect4, 3,3);

        gamefield.add(circle1, 0, 0);
        gamefield.add(circle2, 2,2);
        gamefield.add(circle3, 4,4);



        clickCount = new int[][]{
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2}
        };


        gameboardController.setClickCount(clickCount);
        gameboardController.setGamefield(gamefield);
        gameboardController.setWorkerHeight(2);

        List<Rectangle> rectsWithSameHeightAsWorkerHeight;
        rectsWithSameHeightAsWorkerHeight = gameboardController.rectsWithWorkerHeights(gamefield);

        List<Rectangle> resultRects = hermes.adaptValidFields(gamefield,rectsWithSameHeightAsWorkerHeight, circle3);

        Assertions.assertEquals(rectsWithSameHeightAsWorkerHeight.size(), resultRects.size());
        Assertions.assertEquals(3,resultRects.size());

    }

    /**
     * Tests if the method correctly returns just the Rectangle, where the active worker is positioned,
     * in case this is the only field, which has the same height as the worker itself
     */

    @Test
    public void testAdaptValidFields_JustOwnRectWithSameHeight() {


        Circle circle1 = new Circle();
        Circle circle2 = new Circle();
        Circle circle3 = new Circle();
        Rectangle rect1 = new Rectangle();
        Rectangle rect2 = new Rectangle();
        Rectangle rect3 = new Rectangle();
        Rectangle rect4 = new Rectangle();


        gamefield.add(rect1, 0,0);
        gamefield.add(rect2, 1,1);
        gamefield.add(rect3, 2,2);
        gamefield.add(rect4, 3,3);

        gamefield.add(circle1, 0, 0);
        gamefield.add(circle2, 2,2);
        gamefield.add(circle3, 4,4);



        clickCount = new int[][]{
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2}
        };


        gameboardController.setClickCount(clickCount);
        gameboardController.setGamefield(gamefield);
        gameboardController.setWorkerHeight(3);

        List<Rectangle> rectsWithSameHeightAsWorkerHeight;
        rectsWithSameHeightAsWorkerHeight = gameboardController.rectsWithWorkerHeights(gamefield);

        List<Rectangle> resultRects = hermes.adaptValidFields(gamefield,rectsWithSameHeightAsWorkerHeight, circle3);

        Assertions.assertEquals(rectsWithSameHeightAsWorkerHeight.size(), resultRects.size());
        Assertions.assertEquals(1,resultRects.size());

    }


    /**
     * Tests if the method correctly returns all the rectangles on the gamefield,
     * in case every field has the same height as the worker itself (including its own rectangle)
     */

    @Test
    public void testAdaptValidFields_AllRectWithSameHeightAndJustOwnRect() {


        Circle circle1 = new Circle();
        Circle circle2 = new Circle();
        Circle circle3 = new Circle();
        Rectangle rect1 = new Rectangle();
        Rectangle rect2 = new Rectangle();
        Rectangle rect3 = new Rectangle();
        Rectangle rect4 = new Rectangle();
        Rectangle rect5 = new Rectangle();

        Rectangle rect6 = new Rectangle();
        Rectangle rect7 = new Rectangle();
        Rectangle rect8 = new Rectangle();
        Rectangle rect9 = new Rectangle();
        Rectangle rect10 = new Rectangle();

        Rectangle rect11 = new Rectangle();
        Rectangle rect12 = new Rectangle();
        Rectangle rect13 = new Rectangle();
        Rectangle rect14 = new Rectangle();
        Rectangle rect15 = new Rectangle();

        Rectangle rect16 = new Rectangle();
        Rectangle rect17 = new Rectangle();
        Rectangle rect18 = new Rectangle();
        Rectangle rect19 = new Rectangle();
        Rectangle rect20 = new Rectangle();

        Rectangle rect21 = new Rectangle();
        Rectangle rect22 = new Rectangle();
        Rectangle rect23 = new Rectangle();
        Rectangle rect24 = new Rectangle();
        Rectangle rect25 = new Rectangle();


        gamefield.add(rect1, 0,0);
        gamefield.add(rect2, 0,1);
        gamefield.add(rect3, 0,2);
        gamefield.add(rect4, 0,3);
        gamefield.add(rect5, 0,4);

        gamefield.add(rect6, 1,0);
        gamefield.add(rect7, 1,1);
        gamefield.add(rect8, 1,2);
        gamefield.add(rect9, 1,3);
        gamefield.add(rect10, 1,4);

        gamefield.add(rect11, 2,0);
        gamefield.add(rect12, 2,1);
        gamefield.add(rect13, 2,2);
        gamefield.add(rect14, 2,3);
        gamefield.add(rect15, 2,4);

        gamefield.add(rect16, 2,0);
        gamefield.add(rect17, 2,1);
        gamefield.add(rect18, 2,2);
        gamefield.add(rect19, 2,3);
        gamefield.add(rect20, 2,4);

        gamefield.add(rect21, 2,0);
        gamefield.add(rect22, 2,1);
        gamefield.add(rect23, 2,2);
        gamefield.add(rect24, 2,3);
        gamefield.add(rect25, 2,4);


        gamefield.add(circle1, 0, 0);



        clickCount = new int[][]{
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2},
                {2, 2, 2, 2, 2}
        };


        gameboardController.setClickCount(clickCount);
        gameboardController.setGamefield(gamefield);
        gameboardController.setWorkerHeight(2);

        List<Rectangle> rectsWithSameHeightAsWorkerHeight;
        rectsWithSameHeightAsWorkerHeight = gameboardController.rectsWithWorkerHeights(gamefield);

        List<Rectangle> resultRects = hermes.adaptValidFields(gamefield,rectsWithSameHeightAsWorkerHeight, circle1);

        Assertions.assertEquals(rectsWithSameHeightAsWorkerHeight.size(), resultRects.size());
        Assertions.assertEquals(25,resultRects.size());

    }



}
