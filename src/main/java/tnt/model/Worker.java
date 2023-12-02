package tnt.model;

import javafx.scene.shape.Rectangle;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * Model class for the workers
 * @author Veronika Wehr, Soohyeon Kim, Hendrik Gonschor
 */

@Data
public class Worker implements Serializable {

    /**
     * The string used for identification of the worker
     */
    private String workerID;

    private int x;
    private int y;

    /**
     * The height of the field, a worker is positioned on
     */

    private int heightWorker;

    /**
     * The boolean to check, if the worker has already moved one time
     */
    private boolean moved = false;

     private List<Rectangle> validfield;
     private List<Rectangle> validfielToBuild;

    public String getWorkerID() {
        return workerID;
    }

    public void setWorkerID(String workerID) {
        this.workerID = workerID;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHeightWorker(int heightWorker) {
        this.heightWorker = heightWorker;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public List<Rectangle> getValidfield() {
        return validfield;
    }

    public void setValidfield(List<Rectangle> validfield) {
        this.validfield = validfield;
    }

    public List<Rectangle> getValidfielToBuild() {
        return validfielToBuild;
    }

    public void setValidfielToBuild(List<Rectangle> validfielToBuild) {
        this.validfielToBuild = validfielToBuild;
    }

    public Worker(){

    }
    /**
     * Worker constructor
     * @param workerID the String, which represents the worker
     */
    public Worker(String workerID) {
        this.workerID = workerID;
    }

    public void setWorkerPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int[] getWorkerPosition(){
        int[] pos= {x,y};
        return pos;
    }

    /**
     * get the validfield of each worker
     * @param newValidfield
     */
    public void setValidFieldToMove(List<Rectangle> newValidfield){
        this.validfield = newValidfield;
    }
    public void setValidFieldToBuild(List<Rectangle> newValidfield){
        this.validfielToBuild = newValidfield;
    }

    /**
     * find the correct player by using the circle ID
     * @param str
     * @return
     */
    public String getPlayerFromCircle(String str){
        String playername ="";
        if(str.equals("circle1") || str.equals("circle2") || str.equals("circle7")){
            playername = "player1";
        }else if(str.equals("circle3") || str.equals("circle4") || str.equals("circle8")){
            playername = "player2";
        }else if(str.equals("circle5") || str.equals("circle6") || str.equals("circle9")){
            playername= "player3";
        }
        return playername;
    }

    public int getHeightWorker(){
        return heightWorker;
    }

}