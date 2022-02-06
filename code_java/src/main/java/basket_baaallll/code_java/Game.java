package basket_baaallll.code_java;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.EnumSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {
    public static void start() throws FileNotFoundException {

        Stage playingStage = new Stage();

        Group playingRoot = new Group();
        Scene playingScene = new Scene(playingRoot,1000,700);
        playingStage.setScene(playingScene);


        //Build basketball court
        ImageView background = new ImageView(new Image(new FileInputStream("src/main/resources/images/terrain_basket.png")));
        background.setX(0);
        background.setY(200);

        Rectangle leftPost = new Rectangle(0,352,23,348);
        Rectangle leftRing = new Rectangle(126,406,8,13);
        Rectangle rightPost = new Rectangle(978,352,22,347);
        Rectangle rightRing = new Rectangle(871,411,8,13);
        Rectangle leftBasket = new Rectangle(23,406,103,13);
        Rectangle rightBasket = new Rectangle(879,411,99,13);

        //build display score
        Rectangle displayScore = new Rectangle(0,0,1000,200);
        displayScore.setFill(Color.BLACK);

        Label scoreJ1 = new Label("0");
        scoreJ1.setLayoutX(300);
        scoreJ1.setLayoutY(100);
        scoreJ1.setTextFill(Color.WHITE);

        Label scoreJ2 = new Label("0");
        scoreJ2.setLayoutX(700);
        scoreJ2.setLayoutY(100);
        scoreJ2.setTextFill(Color.WHITE);

        AtomicInteger minute = new AtomicInteger(5);
        AtomicInteger seconde = new AtomicInteger(0);
        AtomicInteger msToS = new AtomicInteger(0);

        Label chrono = new Label(minute.get() + ":" + seconde.get());
        chrono.setLayoutX(450);
        chrono.setLayoutY(100);
        chrono.setTextFill(Color.WHITE);


        playingRoot.getChildren().addAll(background, displayScore, scoreJ1, scoreJ2, chrono);

        Player j1 = new Player(100,500,"j1","src/main/resources/images/perso1d.png", "src/main/resources/images/perso1g.png",playingRoot);
        j1.setControl(KeyCode.Q, KeyCode.D, KeyCode.SPACE, KeyCode.SHIFT);
        j1.setActualTexture(j1.getRightTexture());

        Player j2 = new Player(900,500,"j2","src/main/resources/images/perso2d.png", "src/main/resources/images/perso2g.png",playingRoot);
        j2.setControl(KeyCode.LEFT,KeyCode.RIGHT,KeyCode.UP,KeyCode.NUMPAD0);
        j2.setActualTexture(j2.getLeftTexture());

        Ball ball = new Ball(480,400,"src/main/resources/images/ball.png",playingRoot);

        final Set<KeyCode> activeKeys = EnumSet.noneOf(KeyCode.class);

        //https://stackoverflow.com/questions/50337303/how-do-i-change-the-speed-of-an-animationtimer-in-javafx  --> timeline
        //https://www.developpez.net/forums/d1677438/java/interfaces-graphiques-java/javafx/setonkeypressed-multiple-touches/  --> multiple key pressed
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), actionEvent -> {
            if (msToS.get() == 0){
                msToS.set(1000);
                seconde.addAndGet(-1);
                if (seconde.get() < 0){
                    seconde.set(59);
                    minute.addAndGet(-1);
                }
                chrono.setText(minute.get() + ":" + seconde.get());
            }

            for (KeyCode keyCode: activeKeys){
                j1.move(keyCode);
                j2.move(keyCode);
            }

            j1.gravity();
            j2.gravity();

            ball.move();

            ball.checkCollision(j1.getHitbox());
            ball.checkCollision(j2.getHitbox());
            ball.checkCollision(leftPost);
            ball.checkCollision(rightPost);
            ball.checkCollision(rightRing);
            ball.checkCollision(leftRing);

            if (ball.checkBasket(leftBasket)) {
                    j2.incrementScore();
                    scoreJ2.setText(String.valueOf(j2.getScore()));
                    ball.reset();
                }

            if (ball.checkBasket(rightBasket)){
                j1.incrementScore();
                scoreJ1.setText(String.valueOf(j1.getScore()));
                ball.reset();
            }

            msToS.addAndGet(-10);

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
