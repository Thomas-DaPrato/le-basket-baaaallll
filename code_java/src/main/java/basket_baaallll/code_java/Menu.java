package basket_baaallll.code_java;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
public class Menu extends Application {
    @Override
    public void start(Stage menuStage) throws Exception {
        Group menuRoot = new Group();
        Scene menuScene = new Scene(menuRoot, 700,500);
        menuStage.setScene(menuScene);

        ImageView background = new ImageView(new Image(new FileInputStream("src/main/resources/images/menu.png")));
        background.setX(0);
        background.setY(0);
        Button play = new Button("Play");
        play.setStyle("-fx-pref-height: 50px;-fx-pref-width: 150px;-fx-background-color: orange;-fx-text-fill: white");

        play.setLayoutX(350);
        play.setLayoutY(295);

        Button help = new Button("Help");
        help.setLayoutX(350);
        help.setLayoutY(395);

        //https://fr.acervolima.com/comment-lire-un-fichier-audio-en-utilisant-java/
        //build sound game
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/musiques/music_menu.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        menuRoot.getChildren().addAll(background,play,help);

        play.setOnAction(actionEvent -> {
            menuStage.hide();
            clip.stop();
            try {
                MenuChoicePerso.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        menuStage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}
