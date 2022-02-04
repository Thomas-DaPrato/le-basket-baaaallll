module basket_baaallll.code_java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens basket_baaallll.code_java to javafx.fxml;
    exports basket_baaallll.code_java;
}