package basket_baaallll.code_java;

import basket_baaallll.code_java.Power.ball.FreezeBall;
import basket_baaallll.code_java.Power.ball.TpBall;
import basket_baaallll.code_java.Power.player.ChangeControlPlayer;
import basket_baaallll.code_java.Power.player.FreezePlayer;
import basket_baaallll.code_java.entities.Ball;
import basket_baaallll.code_java.entities.Player;
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

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.EnumSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;






public class Game {
    public static void start(String nameJ1, String nameJ2) throws FileNotFoundException {

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
        scoreJ1.setLayoutX(350);
        scoreJ1.setLayoutY(100);
        scoreJ1.setTextFill(Color.WHITE);

        Label scoreJ2 = new Label("0");
        scoreJ2.setLayoutX(650);
        scoreJ2.setLayoutY(100);
        scoreJ2.setTextFill(Color.WHITE);

        AtomicInteger minute = new AtomicInteger(5);
        AtomicInteger seconde = new AtomicInteger(0);
        AtomicInteger msToS = new AtomicInteger(0);

        Label chrono = new Label(minute.get() + ":" + 0 + seconde.get());
        chrono.setLayoutX(450);
        chrono.setLayoutY(100);
        chrono.setTextFill(Color.WHITE);

        //build display power
        ImageView imagePowerJ1 = new ImageView();
        imagePowerJ1.setX(50);
        imagePowerJ1.setY(50);
        ImageView powerJ1 = new ImageView(new Image(new FileInputStream("src/main/resources/images/barrePouvoir.png")));
        powerJ1.setX(175);
        powerJ1.setY(100);

        Rectangle fullPowerJ1 = new Rectangle(175,100,150,30);
        fullPowerJ1.setStroke(Color.ORANGE);
        fullPowerJ1.setFill(Color.TRANSPARENT);
        Rectangle hidePowerJ1 = new Rectangle(175,100,150,30);
        hidePowerJ1.setFill(Color.BLACK);

        ImageView imagePowerJ2 = new ImageView();
        imagePowerJ2.setX(850);
        imagePowerJ2.setY(50);
        ImageView powerJ2 = new ImageView(new Image(new FileInputStream("src/main/resources/images/barrePouvoir.png")));
        powerJ2.setX(675);
        powerJ2.setY(100);
        powerJ2.setRotate(180);

        Rectangle fullPowerJ2 = new Rectangle(675,100,150,30);
        fullPowerJ2.setStroke(Color.ORANGE);
        fullPowerJ2.setFill(Color.TRANSPARENT);
        Rectangle hidePowerJ2 = new Rectangle(675,100,150,30);
        hidePowerJ2.setFill(Color.BLACK);


        playingRoot.getChildren().addAll(background, displayScore, scoreJ1, scoreJ2, chrono, powerJ1,powerJ2,hidePowerJ1,hidePowerJ2);

        Player j1 = new Player(100,500,nameJ1,"src/main/resources/images/perso1d.png", "src/main/resources/images/perso1g.png",playingRoot);
        j1.setActualTexture(j1.getRightTexture());

        Player j2 = new Player(900,500,nameJ2,"src/main/resources/images/perso2d.png", "src/main/resources/images/perso2g.png",playingRoot);
        j2.setActualTexture(j2.getLeftTexture());

        Ball ball = new Ball(480,400,"src/main/resources/images/ball.png",playingRoot);

        final Set<KeyCode> activeKeys = EnumSet.noneOf(KeyCode.class);

        Random power = new Random();
        AtomicBoolean gameChrono = new AtomicBoolean(false);
        AtomicBoolean startChrono = new AtomicBoolean(true);

        //https://stackoverflow.com/questions/50337303/how-do-i-change-the-speed-of-an-animationtimer-in-javafx  --> timeline
        //https://www.developpez.net/forums/d1677438/java/interfaces-graphiques-java/javafx/setonkeypressed-multiple-touches/  --> multiple key pressed
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), actionEvent -> {
            if (startChrono.get()){
                startChrono.set(false);
                chrono(playingRoot, j1, j2, ball,gameChrono, msToS);
            }
            if (gameChrono.get()) {
                if (msToS.get() == 0) {
                    msToS.set(1000);
                    seconde.addAndGet(-1);
                    if (seconde.get() < 0) {
                        seconde.set(59);
                        minute.addAndGet(-1);
                    }
                    chrono.setText(minute.get() + ":" + (seconde.get() < 10 ? "0" + seconde.get() : seconde.get()));

                    if (!j1.isHasPower()) {
                        playingRoot.getChildren().removeAll(fullPowerJ1, imagePowerJ1);
                        hidePowerJ1.setWidth(hidePowerJ1.getWidth() - 15);
                        j1.decrementTimePower();
                        if (j1.getTimePower() == 0) {
                            j1.setHasPower(true);
                            try {
                                choosePower(power.nextInt(4), j1, ball, 885, 350, j2, imagePowerJ1);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            playingRoot.getChildren().addAll(fullPowerJ1, imagePowerJ1);
                        }
                    }


                    if (!j2.isHasPower()) {
                        playingRoot.getChildren().removeAll(fullPowerJ2, imagePowerJ2);
                        hidePowerJ2.setWidth(hidePowerJ2.getWidth() - 15);
                        hidePowerJ2.setX(hidePowerJ2.getX() + 15);
                        j2.decrementTimePower();
                        if (j2.getTimePower() == 0) {
                            j2.setHasPower(true);
                            try {
                                choosePower(power.nextInt(4), j2, ball, 30, 350, j1, imagePowerJ2);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            playingRoot.getChildren().addAll(fullPowerJ2, imagePowerJ2);
                        }
                    }
                }
            }

            for (KeyCode keyCode: activeKeys){
                j1.move(keyCode);
                if (j1.isHasPower() && j1.getControl().get("power") == keyCode){
                    j1.setHasPower(false);
                    hidePowerJ1.setWidth(150);
                }
                j2.move(keyCode);
                if (j2.isHasPower() && j2.getControl().get("power") == keyCode){
                    j2.setHasPower(false);
                    hidePowerJ2.setWidth(150);
                    hidePowerJ2.setX(675);
                }
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
                    reset(ball, j1, j2, hidePowerJ1, hidePowerJ2);
                    playingRoot.getChildren().removeAll(imagePowerJ1,imagePowerJ2, fullPowerJ1, fullPowerJ2);
                    startChrono.set(true);
                    gameChrono.set(false);
            }

            if (ball.checkBasket(rightBasket)){
                j1.incrementScore();
                scoreJ1.setText(String.valueOf(j1.getScore()));
                reset(ball, j1, j2, hidePowerJ1, hidePowerJ2);
                playingRoot.getChildren().removeAll(imagePowerJ1,imagePowerJ2, fullPowerJ1, fullPowerJ2);
                startChrono.set(true);
                gameChrono.set(false);
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

    private static void reset(Ball ball, Player j1, Player j2, Rectangle hidePowerJ1, Rectangle hidePowerJ2) {
        ball.reset();
        j1.reset(100,500);
        j2.reset(900,500);
        hidePowerJ1.setWidth(150);
        hidePowerJ2.setWidth(150);
        hidePowerJ2.setX(675);
        j1.setActualTexture(j1.getRightTexture());
        j2.setActualTexture(j2.getLeftTexture());
    }

    private static void choosePower(int number, Player player, Ball ball, int x, int y, Player affectPlayer, ImageView imagePower) throws FileNotFoundException {
        switch (number){
            case 0 -> {
                player.setPower(new FreezeBall(ball));
                imagePower.setImage(new Image(new FileInputStream("src/main/resources/images/freeze_ball.png")));
            }
            case 1 -> {
                player.setPower(new TpBall(ball,x,y));
                imagePower.setImage(new Image(new FileInputStream("src/main/resources/images/tp_ball.png")));
            }
            case 2 -> {
                player.setPower(new FreezePlayer(affectPlayer));
                imagePower.setImage(new Image(new FileInputStream("src/main/resources/images/freeze_joueur.png")));
            }
            case 3 -> {
                player.setPower(new ChangeControlPlayer(affectPlayer));
                imagePower.setImage(new Image(new FileInputStream("src/main/resources/images/inverse_commande.png")));
            }
        }
    }

    private static void chrono(Group root, Player j1, Player j2, Ball ball, AtomicBoolean gameChrono, AtomicInteger msToS){
        AtomicInteger timer = new AtomicInteger(3);
        Label chrono = new Label(String.valueOf(timer.get()));
        chrono.setLayoutX(500);
        chrono.setLayoutY(250);
        chrono.setTextFill(Color.WHITE);
        root.getChildren().addAll(chrono);
        ball.setSpeed(0);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
            timer.addAndGet(-1);
            if (timer.get() == 0){
                j1.setControl(KeyCode.Q, KeyCode.D, KeyCode.SPACE, KeyCode.SHIFT);
                j2.setControl(KeyCode.LEFT,KeyCode.RIGHT,KeyCode.UP,KeyCode.NUMPAD0);
                ball.setSpeed(1);
                root.getChildren().removeAll(chrono);
                gameChrono.set(true);
                msToS.set(1000);
            }
            chrono.setText(String.valueOf(timer.get()));
        }));

        timeline.setCycleCount(3);
        timeline.play();
    }
}
