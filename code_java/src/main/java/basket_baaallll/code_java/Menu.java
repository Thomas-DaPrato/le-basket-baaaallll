package basket_baaallll.code_java;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Menu extends Application {
    @Override
    public void start(Stage menuStage) throws FileNotFoundException {
        Group menuRoot = new Group();
        Scene menuScene = new Scene(menuRoot, 700,500);
        menuStage.setScene(menuScene);

        ImageView background = new ImageView(new Image(new FileInputStream("src/main/resources/images/menu.png")));
        background.setX(0);
        background.setY(0);
        Button play = new Button("Play");
        play.setLayoutX(350);
        play.setLayoutY(295);

        Button help = new Button("Help");
        help.setLayoutX(350);
        help.setLayoutY(395);

        menuRoot.getChildren().addAll(background,play,help);

        play.setOnAction(actionEvent -> {
            menuStage.hide();
            try {
                MenuChoicePerso.start();
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
