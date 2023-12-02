package tnt.model.god;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import tnt.model.God;
import tnt.viewmodel.GameboardController;

import java.util.concurrent.CompletableFuture;

public class Hephaestus implements God {


    public void godSkill(GameboardController gameboardController) {
        System.out.println("Hephaestus-Skill wird ausgeführt");
    }
    public CompletableFuture<Boolean> buildMore(AnchorPane gameboard, Button btn, Button cancle,Rectangle rect, HBox hbox, CompletableFuture<Boolean> future){

        hbox.getChildren().add(btn);
        hbox.getChildren().add(cancle);
        gameboard.getChildren().add(hbox);
        AnchorPane.setTopAnchor(hbox, rect.getLayoutY()+130);
        AnchorPane.setLeftAnchor(hbox, rect.getLayoutX()+160);


        btn.setOnAction(event -> {
            System.out.println("button was clicked!");
            future.complete(true);
        });

        cancle.setOnAction(event -> {
            future.complete(false);

        });

        return future;
    }


}