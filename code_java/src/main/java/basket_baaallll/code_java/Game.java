package basket_baaallll.code_java;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Game extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setTitle("basket ball");
        stage.setScene(scene);
        stage.setFullScreen(true);
        Menu menu = new Menu(root,stage,scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
