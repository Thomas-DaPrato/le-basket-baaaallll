package basket_baaallll.code_java;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        double height = 500;
        double width = 700;
        Group root = new Group();
        Scene scene = new Scene(root, width,height);
        stage.setTitle("basket ball");
        stage.setScene(scene);
        Menu menu = new Menu(root,stage,scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
