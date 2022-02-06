package basket_baaallll.code_java;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class Player {
    private int x;
    private int y;
    private boolean hasJump = false;

    private HashMap<String, KeyCode> control = new HashMap<>();

    private Label name;
    private int score = 0;

    private int timePower = 10;
    private boolean hasPower = false;

    private ImageView actualTexture = new ImageView();
    private Image rightTexture;
    private Image leftTexture;
    private Rectangle hitbox;

    public Player(int x, int y, String name, String pathRightTexture, String pathLeftTexture, Group root) throws FileNotFoundException {
        this.x = x;
        this.y = y;

        this.name = new Label(name);
        this.name.setLayoutX(x);
        this.name.setLayoutY(y -25);

        this.actualTexture.setX(x);
        this.actualTexture.setY(y);
        this.rightTexture = new Image(new FileInputStream(pathRightTexture));
        this.leftTexture = new Image(new FileInputStream(pathLeftTexture));

        control.put("left",null);
        control.put("right",null);
        control.put("jump",null);
        control.put("power",null);

        root.getChildren().addAll(actualTexture,this.name);
    }

    public void setControl(KeyCode keyLeft, KeyCode keyRight, KeyCode keyJump, KeyCode keyPower){
        control.replace("left",keyLeft);
        control.replace("right",keyRight);
        control.replace("jump",keyJump);
        control.replace("power",keyPower);
    }

    public void setActualTexture(Image actualTexture) {
        this.actualTexture.setImage(actualTexture);
        this.hitbox = new Rectangle(x,y,actualTexture.getWidth(),actualTexture.getHeight());
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public ImageView getActualTexture() {
        return actualTexture;
    }

    public Image getRightTexture() {
        return rightTexture;
    }

    public Image getLeftTexture() {
        return leftTexture;
    }

    public int getScore() {
        return score;
    }

    public int getTimePower() {
        return timePower;
    }

    public boolean isHasPower() {
        return hasPower;
    }

    public void setHasPower(boolean hasPower) {
        this.hasPower = hasPower;
    }

    public void setTimePower(int timePower) {
        this.timePower = timePower;
    }

    public void move(KeyCode key){
        if (control.get("left") == key){
            if (x > 0)
                x -= 3;
            setActualTexture(leftTexture);
        }
        if (control.get("right") == key){
            if (x + actualTexture.getImage().getWidth() < 1000)
                x += 3;
            setActualTexture(rightTexture);
        }
        if(control.get("jump") == key){
            if (!hasJump) {
                y -= 100;
                hasJump = true;
            }
        }

        if(control.get("power") == key){
            usePower();
        }
        update();
    }

    public void gravity(){
        if (y < 700 - getRightTexture().getHeight()) {
            y += 3;
        }
        else {
            hasJump = false;
        }
        update();
    }


    public void usePower(){
        System.out.println("use power");
    }

    public void update(){
        name.setLayoutX(x);
        name.setLayoutY(y - 25);
        actualTexture.setX(x);
        actualTexture.setY(y);
        hitbox.setX(x);
        hitbox.setY(y);
    }

    public void incrementScore(){
        score +=1;
    }

    public void decrementTimePower(){
        timePower -= 1;
    }
}
