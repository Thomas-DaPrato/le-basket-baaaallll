package basket_baaallll.code_java.Power;

import basket_baaallll.code_java.entities.Ball;

public abstract class PowerBall implements Power {

    protected Ball ball;

    public PowerBall(Ball ball) {
        this.ball = ball;
    }

    public abstract void use();
}
