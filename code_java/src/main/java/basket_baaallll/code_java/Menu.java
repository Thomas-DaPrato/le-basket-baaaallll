package basket_baaallll.code_java;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Menu {
    public Menu(Group root, Stage stage, Scene scene) {
        Button play = new Button("Play");
        play.setLayoutX(350);
        play.setLayoutY(295);

        Button help = new Button("Help");
        help.setLayoutX(350);
        help.setLayoutY(395);

        play.setOnAction(actionEvent -> {
            root.getChildren().removeAll(play,help);
            stage.setWidth(1000);
            stage.setHeight(1000);
            try {
                Play Gameplay = new Play(root,scene);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        });

        help.setOnAction(actionEvent -> {
            try {
                java.awt.Desktop.getDesktop().open(new File("src/main/resources/html/ggggg.html"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        root.getChildren().addAll(play,help);
    }
}
