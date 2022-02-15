package basket_baaallll.code_java.Power.ball;

import basket_baaallll.code_java.entities.Ball;
import basket_baaallll.code_java.Power.PowerBall;

public class TpBall extends PowerBall {

    private final int x;
    private final int y;

    public TpBall(Ball ball, int x, int y) {
        super(ball);
        this.x = x;
        this.y = y;
    }

    @Override
    public void use() {
        ball.setX(x);
        ball.setY(y);
        ball.setyDirection(1);
    }
}
