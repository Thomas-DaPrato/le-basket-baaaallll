package basket_baaallll.code_java;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Menu extends Application {
    @Override
    public void start(Stage menuStage){
        Group menuRoot = new Group();
        Scene menuScene = new Scene(menuRoot, 700,500);
        menuStage.setScene(menuScene);

        Button play = new Button("Play");
        play.setLayoutX(350);
        play.setLayoutY(295);

        Button help = new Button("Help");
        help.setLayoutX(350);
        help.setLayoutY(395);

        menuRoot.getChildren().addAll(play,help);

        play.setOnAction(actionEvent -> {
            menuStage.hide();
            try {
                Game.start();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        menuStage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}
