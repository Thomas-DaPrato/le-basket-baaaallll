module basket_baaallll.code_java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens basket_baaallll.code_java to javafx.fxml;
    exports basket_baaallll.code_java;
    exports basket_baaallll.code_java.Power;
    opens basket_baaallll.code_java.Power to javafx.fxml;
    exports basket_baaallll.code_java.Power.ball;
    opens basket_baaallll.code_java.Power.ball to javafx.fxml;
    exports basket_baaallll.code_java.entities;
    opens basket_baaallll.code_java.entities to javafx.fxml;
}