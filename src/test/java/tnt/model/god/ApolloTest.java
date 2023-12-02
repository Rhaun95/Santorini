package tnt.model.god;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;


/**
 * This class contains unit tests for the GameManager class.
 * @author Veronika Wehr
 */

public class ApolloTest {

    Apollo apollo = new Apollo();
    GridPane gamefield = new GridPane();

    /**
     * Tests, if the method returns all rectangles, which inhabit a circle, but only the rectangles, which inhabit circles of other colours than the target one
     */

    @Test
    public void testAdaptValidFields() {

        //clicked node with color red and the indices 1 and 1
        Circle targetNode = new Circle();
        targetNode.setFill(Color.RED);
        GridPane.setColumnIndex(targetNode,1);
        GridPane.setRowIndex(targetNode, 1);

        Rectangle rect1 = new Rectangle();
        Rectangle rect2 = new Rectangle();
        Rectangle rect3 = new Rectangle();

        Circle circ1 = new Circle();
        circ1.setFill(Color.RED);
        Circle circ2 = new Circle();
        circ2.setFill(Color.BLUE);

        // rect1 has circ1 (circle with same color red that the target one), rect2 has circ2 (different color) and rect3 doesn't have a circle

        gamefield.add(rect1, 1, 0);
        gamefield.add(rect2, 2, 2);
        gamefield.add(rect3, 3, 3);

        gamefield.add(circ1, 1,0);
        gamefield.add(circ2, 2,2);

        //call apollo method to save the valid rectangles, which have circles on it but not with the same color as the target one

        List<Rectangle> adaptRectangles = apollo.adaptValidFields(gamefield, targetNode);

        //tests if the method only returns the (one) expected rectangle (rect2)
        assertEquals(1, adaptRectangles.size());
        assertTrue(adaptRectangles.contains(rect2));

        //tests if the method doesn't return a list with rect 1 (circ with same color as target node)
        assertFalse(adaptRectangles.contains(rect1));

        //tests if the method doesn't return a list with rect 3 (rect without circle)
        assertFalse(adaptRectangles.contains(rect3));


    }


    /**
     * Tests if when the worker of the player wo has Apollo as god uses its power, the opponents worker correctly moves to the
     * old position of the worker with the apollo skill
     */

    @Test
    public void switchPositions() {

        // der worker which has just been dragged onto another field (with a possibly already inhabiting circle)
        Circle movingWorker = new Circle();
        movingWorker.setId("moving");
        GridPane.setColumnIndex(movingWorker, 2);
        GridPane.setRowIndex(movingWorker,2);


        // old positions of the just moved worker
        int colIndNotYetDragged = 1;
        int rowIndNotYetDragged  = 1;

        // the circle which is already inhabiting the field, where the moving worker has just been dropped
        Circle exchange = new Circle();
        exchange.setId("exchange");
        GridPane.setColumnIndex(exchange, 2);
        GridPane.setRowIndex(exchange,2);

        //add the workers/circles to the gamefield
        gamefield.getChildren().addAll(movingWorker, exchange);

        //call the switch method in apollo
        apollo.switchPositions(movingWorker, colIndNotYetDragged, rowIndNotYetDragged, gamefield);

        //tests if the old position of the moving worker and the current position of the other worker are equal (therefore the exchange circle must have been moved there)
        assertEquals(colIndNotYetDragged, GridPane.getColumnIndex(exchange));
        assertEquals(rowIndNotYetDragged, GridPane.getRowIndex(exchange));
        //tests if the moved worker is in the position where the exchange worker has been before (2,2)
        assertEquals(2, GridPane.getColumnIndex(movingWorker));
        assertEquals(2, GridPane.getRowIndex(movingWorker));

    }

}
