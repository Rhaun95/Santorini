package tnt.model.god;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import lombok.AllArgsConstructor;
import tnt.model.God;
import tnt.model.Player;
import tnt.viewmodel.GameManager;
import tnt.viewmodel.GameboardController;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class Atlas implements God {

    private Player playerInTurn;
    private AnchorPane gameboard;
    private Rectangle clickedRectangel;
    private int columnIndex;
    private  int rowIndex;
    private int heightToWin;
    private int[][] clickcount;
    private Text stone3;
    private Text stone4;
    private Text stoneLevel4;

    private Text stoneLevel5;
    private GameManager gameManager;
    private CompletableFuture<Boolean> future;
    boolean tmp = false;

    public Atlas(){

    }
    public Atlas(Player playerInTurn, AnchorPane gameboard, Rectangle clickedRectangle, Integer columnIndex,
                 Integer rowIndex, int heightToWin, int[][] clickCount, Text stone3, Text stone4, Text stoneLevel4,
                 Text stoneLevel5, GameManager gameManager, CompletableFuture<Boolean> future2) {

        this.playerInTurn = playerInTurn;
        this.gameboard = gameboard;
        this.clickedRectangel = clickedRectangle;
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        this.heightToWin = heightToWin;
        this.clickcount = clickCount;
        this.stone3 = stone3;
        this.stone4 = stone4;
        this.stoneLevel4 = stoneLevel4;
        this.stoneLevel5 = stoneLevel5;
        this.gameManager = gameManager;
        this.future = future2;

    }

    public void godSkill(GameboardController gameboardController) {
        System.out.println("Atlas-Skill wird ausgeführt");
    }

    public CompletableFuture<Boolean> build(){

        HBox hbox = new HBox();
        // DOM 버튼 추가
        Button buildDomButton = new Button("DOM");
        hbox.getChildren().add(buildDomButton);

        // Normal 버튼 추가
        Button noActionButton = new Button("Normal");
        hbox.getChildren().add(noActionButton);

        gameboard.getChildren().add(hbox);
        AnchorPane.setTopAnchor(hbox, clickedRectangel.getLayoutY()+130);
        AnchorPane.setLeftAnchor(hbox, clickedRectangel.getLayoutX()+110);

            buildDomButton.setOnAction(event ->{
                tmp = true;
                switch (heightToWin){
                    case 2:
                        String amount = stone3.getText();
                        int newAmount = Integer.parseInt(amount);

                        if (newAmount > 0) {
                            newAmount -= 1;
                            String res = String.valueOf(newAmount);
                            stone3.setText(res);
                            clickcount[rowIndex][columnIndex] = 3;
                            clickedRectangel.setFill(Color.RED);
                            gameManager.setHeightField(3);
                        }
                        break;
                    case 3:
                        String amount2 = stone4.getText();
                        int newAmount2 = Integer.parseInt(amount2);

                        if (newAmount2 > 0) {
                            newAmount2 -= 1;
                            String res = String.valueOf(newAmount2);
                            stone4.setText(res);
                            clickedRectangel.setFill(Color.RED);
                            clickcount[rowIndex][columnIndex] = 4;
                            gameManager.setHeightField(4);
                        }
                        break;
                    case 4:
                        String amount3 = stoneLevel4.getText();
                        int newAmount3 = Integer.parseInt(amount3);

                        if (newAmount3 > 0) {
                            newAmount3 -= 1;
                            String res = String.valueOf(newAmount3);
                            stoneLevel4.setText(res);
                            clickedRectangel.setFill(Color.RED);
                            clickcount[rowIndex][columnIndex] = 5;
                            gameManager.setHeightField(5);
                        }
                        break;
                    case 5:
                        String amount4 = stoneLevel4.getText();
                        int newAmount4 = Integer.parseInt(amount4);

                        if (newAmount4 > 0) {
                            newAmount4 -= 1;
                            String res = String.valueOf(newAmount4);
                            stoneLevel5.setText(res);
                            clickedRectangel.setFill(Color.RED);
                            clickcount[rowIndex][columnIndex] = 6;
                            gameManager.setHeightField(6);
                        }
                        break;
                }
                gameboard.getChildren().remove(hbox);
                future.complete(true);

            });

            noActionButton.setOnAction(event ->{
                gameboard.getChildren().remove(hbox);
                future.complete(false);

            });

        if(playerInTurn.isCom()){
            buildDomButton.fire();
        }
        return future;
    }
}
