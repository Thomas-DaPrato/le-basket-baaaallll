package basket_baaallll.code_java.Power.ball;

import basket_baaallll.code_java.entities.Ball;
import basket_baaallll.code_java.Power.PowerBall;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class FreezeBall extends PowerBall {

    private int timer = 5;
    private final int initSpeed = ball.getSpeed();

    public FreezeBall(Ball ball) {
        super(ball);
    }

    @Override
    public void use() {
        ball.setSpeed(0);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
            timer -= 1;
            if (timer == 0){
                ball.setSpeed(initSpeed);
                timer = 5;
            }
        }));
        timeline.setCycleCount(5);
        timeline.play();
    }
}
