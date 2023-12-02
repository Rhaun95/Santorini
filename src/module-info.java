/**
 * The main module of the tnt application.
 */
module src {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires lombok;

    opens main.java.tnt.viewmodel to javafx.fxml, test.java.tnt.viewmodel;


    opens main.java.tnt.model;

    exports main.java.tnt.viewmodel to javafx.fxml;
    exports main.java.tnt.gui;
    opens main.java.tnt.model.god;
    exports main.java.tnt.model;
    exports main.java.tnt.model.god;
}
