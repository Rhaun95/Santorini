package tnt.model;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class for the player
 * @author Veronika Wehr, Soohyeon Kim, Hendrik Gonschor
 */

@Data
public class Player implements Serializable {

    private String name;
    private Color color;

    private Image image;
    private double colorRed;
    private double colorGreen;
    private double colorBlue;

    private String id;
    private boolean isCom;

    private boolean hasTurn;

    private int turnAmount;

    private boolean hasWon = false;

    private boolean hasGod;

    private God god;

    private String assignedGod;

    private boolean isMoved = false;

    private int initialMove=2;

    /**
     * List of workers, which are assigned to a specific player
     */
    private List<Worker> workers = new ArrayList<>();


    public Player(String name,Color color, boolean isCom){
        this.name = name;
        this.colorRed = color.getRed();
        this.colorGreen = color.getGreen();
        this.colorBlue = color.getBlue();
        this.color = color;
        this.isCom = isCom;
    }

    public Player(String name,Color color, List<Worker> workers) {
        this.name = name;
        this.color = color;
        this.colorRed = color.getRed();
        this.colorGreen = color.getGreen();
        this.colorBlue = color.getBlue();
        this.workers = workers;

    }

    public Player(String name,Color color, boolean isCom, boolean hasTurn, int turnAmount, List<Worker> workers) {
        this.name = name;
        this.color = color;
        this.color = color;
        this.colorRed = color.getRed();
        this.colorGreen = color.getGreen();
        this.colorBlue = color.getBlue();
        this.isCom = isCom;
        this.hasTurn = hasTurn;
        this.turnAmount = turnAmount;
        this.workers = workers;

    }

    public Player(String name,Color color, boolean isCom, boolean hasTurn, boolean hasWon, int turnAmount, List<Worker> workers) {
        this.name = name;
        this.color = color;
        this.colorRed = color.getRed();
        this.colorGreen = color.getGreen();
        this.colorBlue = color.getBlue();
        this.isCom = isCom;
        this.hasTurn = hasTurn;
        this.turnAmount = turnAmount;
        this.workers = workers;
        this.hasWon = hasWon;

    }

    public Player(String player1) {
    }

    public String getId(){
        return id;
    }
    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    /** Assigns a god to the player
     * @param god the god, that the player chooses before starting the game
     */
    public void assignGod(God god) {
        this.god = god;
    }

    public String getAssignedGod() {

        return assignedGod;
    }


    public boolean isHasTurn(){
        return hasTurn;
    }

    public boolean isCom(){
        return isCom;
    }
    public int getTurnAmount(){
        return turnAmount;
    }

    public String getName(){
        return name;
    }
    public Color getColor(){
        return color;
    }
    public int getInitialMove(){
        return initialMove;
    }
    public List<Worker> getWorkers(){
        return workers;
    }

    public void setHasTurn(boolean turn){
        hasTurn = turn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getColorRed() {
        return colorRed;
    }

    public void setColorRed(double colorRed) {
        this.colorRed = colorRed;
    }

    public double getColorGreen() {
        return colorGreen;
    }

    public void setColorGreen(double colorGreen) {
        this.colorGreen = colorGreen;
    }

    public double getColorBlue() {
        return colorBlue;
    }

    public void setColorBlue(double colorBlue) {
        this.colorBlue = colorBlue;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCom(boolean com) {
        isCom = com;
    }

    public void setTurnAmount(int turnAmount) {
        this.turnAmount = turnAmount;
    }

    public boolean isHasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    public boolean isHasGod() {
        return hasGod;
    }

    public void setHasGod(boolean hasGod) {
        this.hasGod = hasGod;
    }

    public God getGod() {
        return god;
    }

    public void setGod(God god) {
        this.god = god;
    }

    public void setAssignedGod(String assignedGod) {
        this.assignedGod = assignedGod;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    public void setInitialMove(int initialMove) {
        this.initialMove = initialMove;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}


