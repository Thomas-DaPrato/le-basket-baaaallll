package basket_baaallll.code_java;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.EnumSet;
import java.util.Set;

public class Play {
    public Play(Group root, Scene scene) throws FileNotFoundException {
        Player j1 = new Player(100,800,"j1","src/main/resources/images/perso1d.png", "src/main/resources/images/perso1g.png",root);
        j1.setControl(KeyCode.Q, KeyCode.D, KeyCode.SPACE,KeyCode.SHIFT);
        j1.setActualTexture(j1.getRightTexture());

        final Set<KeyCode> activeKeys = EnumSet.noneOf(KeyCode.class);

        //https://stackoverflow.com/questions/50337303/how-do-i-change-the-speed-of-an-animationtimer-in-javafx  --> timeline
        //https://www.developpez.net/forums/d1677438/java/interfaces-graphiques-java/javafx/setonkeypressed-multiple-touches/  --> multiple key pressed
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), actionEvent -> {
            for (KeyCode keyCode: activeKeys){
                j1.move(keyCode);
            }
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        scene.setOnKeyPressed(keyEvent -> {
            activeKeys.add(keyEvent.getCode());
        });

        scene.setOnKeyReleased(keyEvent -> {
            activeKeys.remove(keyEvent.getCode());
        });
    }
}
