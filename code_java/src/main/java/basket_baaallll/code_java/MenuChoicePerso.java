package basket_baaallll.code_java;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MenuChoicePerso {
    public static void start() throws Exception {
        Stage choicePersoStage = new Stage();
        Group choicePersoRoot = new Group();
        Scene choicePersoScene = new Scene(choicePersoRoot, 700,500);

        choicePersoStage.setScene(choicePersoScene);

        ImageView skinJ1 = new ImageView(new Image(new FileInputStream("src/main/resources/images/perso1d.png")));
        skinJ1.setX(100);
        skinJ1.setY(100);
        TextField nameJ1 = new TextField();
        nameJ1.setLayoutX(200);
        nameJ1.setLayoutY(150);

        ImageView skinJ2 = new ImageView(new Image(new FileInputStream("src/main/resources/images/perso2d.png")));
        skinJ2.setX(100);
        skinJ2.setY(300);
        TextField nameJ2 = new TextField();
        nameJ2.setLayoutX(200);
        nameJ2.setLayoutY(350);


        //build sound game
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/musiques/music_chargement.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);


        Button lauchGame = new Button("GO !!");
        lauchGame.setLayoutX(600);
        lauchGame.setLayoutY(450);
        lauchGame.setOnAction(actionEvent -> {
            choicePersoStage.hide();
            clip.stop();
            try {
                Game.start(nameJ1.getText(),nameJ2.getText());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        choicePersoRoot.getChildren().addAll(skinJ1,skinJ2, nameJ1, nameJ2, lauchGame);

        choicePersoStage.show();
    }
}
