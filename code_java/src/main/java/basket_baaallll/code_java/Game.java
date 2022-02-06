package basket_baaallll.code_java;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.EnumSet;
import java.util.Set;

public class Game {
    public static void start() throws FileNotFoundException {

        Stage playingStage = new Stage();

        Group playingRoot = new Group();
        Scene playingScene = new Scene(playingRoot,1000,700);
        playingStage.setScene(playingScene);

        ImageView background = new ImageView(new Image(new FileInputStream("src/main/resources/images/terrain_basket.png")));
        background.setX(0);
        background.setY(200);

        playingRoot.getChildren().add(background);

        Player j1 = new Player(100,500,"j1","src/main/resources/images/perso1d.png", "src/main/resources/images/perso1g.png",playingRoot);
        j1.setControl(KeyCode.Q, KeyCode.D, KeyCode.SPACE, KeyCode.SHIFT);
        j1.setActualTexture(j1.getRightTexture());

        final Set<KeyCode> activeKeys = EnumSet.noneOf(KeyCode.class);

        //https://stackoverflow.com/questions/50337303/how-do-i-change-the-speed-of-an-animationtimer-in-javafx  --> timeline
        //https://www.developpez.net/forums/d1677438/java/interfaces-graphiques-java/javafx/setonkeypressed-multiple-touches/  --> multiple key pressed
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), actionEvent -> {
            for (KeyCode keyCode: activeKeys){
                j1.move(keyCode);
            }
            j1.gravity();
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        playingScene.setOnKeyPressed(keyEvent -> {
            activeKeys.add(keyEvent.getCode());
        });

        playingScene.setOnKeyReleased(keyEvent -> {
            activeKeys.remove(keyEvent.getCode());
        });

        playingStage.show();
    }
}
