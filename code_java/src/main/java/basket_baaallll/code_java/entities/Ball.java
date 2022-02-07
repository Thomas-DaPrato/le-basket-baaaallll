package basket_baaallll.code_java.entities;

import basket_baaallll.code_java.entities.Entities;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Ball implements Entities {
    private int x;
    private int y;

    private int xDirection = 0;
    private int yDirection = 1;
    private int speed = 1;

    private ImageView texture = new ImageView();

    public Ball(int x, int y, String pathTexture, Group root) throws FileNotFoundException {
        this.x = x;
        this.y = y;
        this.texture.setImage(new Image(new FileInputStream(pathTexture)));
        this.texture.setX(x);
        this.texture.setY(y);

        root.getChildren().add(texture);
    }

    public void move(){
        if (x < 0 || x + texture.getImage().getWidth() > 1000)
            xDirection *= -1;
        if (y < 200 || y + texture.getImage().getHeight() > 700)
            yDirection *= -1;

        x += 3*speed*xDirection;
        y += 3*speed*yDirection;

        update();
    }

    public void update(){
        texture.setX(x);
        texture.setY(y);
    }

    public int getxDirection() {
        return xDirection;
    }

    public void setxDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void checkCollision(Rectangle hitbox){
        //right collision
        if (x + texture.getImage().getWidth() > hitbox.getX() &&
            x + texture.getImage().getWidth() < hitbox.getX() + hitbox.getWidth() &&
            y < (hitbox.getY() + hitbox.getHeight()) &&
            y + texture.getImage().getHeight() > hitbox.getY()) {

            setxDirection(-1);
            move();
        }

        //left collision
        else if (x > hitbox.getX() &&
                 x < hitbox.getX() + hitbox.getWidth() &&
                 y < (hitbox.getY() + hitbox.getHeight()) &&
                 y + texture.getImage().getHeight() > hitbox.getY()) {
            setxDirection(1);
            move();
        }

        //top collision
        else if (y + texture.getImage().getHeight() > hitbox.getY() &&
                 y + texture.getImage().getHeight() < hitbox.getY() + hitbox.getHeight() &&
                 x < (hitbox.getX() + hitbox.getWidth()) &&
                 x + texture.getImage().getWidth() > hitbox.getX()) {
            setyDirection(-1);
            move();
        }
    }

    public boolean checkBasket(Rectangle hitbox){
        return  (x > hitbox.getX() &&
                x < hitbox.getX() + hitbox.getWidth() &&
                y > hitbox.getY() &&
                y < hitbox.getY() + hitbox.getHeight() &&
                yDirection > 0 );
    }

    public void reset(){
        x = 480;
        y = 400;
        xDirection = 0;
        yDirection = 1;
    }

}
