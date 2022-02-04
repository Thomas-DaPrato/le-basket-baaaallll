package basket_baaallll.code_java;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class Player {
    private int x;
    private int y;

    private HashMap<String, KeyCode> control = new HashMap<>();

    private Label name;

    private ImageView texture;

    public Player(int x, int y, String name, String pathTexture, Group root) throws FileNotFoundException {
        this.x = x;
        this.y = y;
        this.name = new Label(name);
        this.name.setLayoutX(x);
        this.name.setLayoutY(y -25);
        this.texture = new ImageView(new Image(new FileInputStream(pathTexture)));
        this.texture.setX(x);
        this.texture.setY(y);
        control.put("left",null);
        control.put("right",null);
        control.put("jump",null);
        control.put("power",null);
        root.getChildren().addAll(texture,this.name);
    }

    public void initControl(KeyCode keyLeft, KeyCode keyRight, KeyCode keyJump, KeyCode keyPower){
        control.replace("left",keyLeft);
        control.replace("right",keyRight);
        control.replace("jump",keyJump);
        control.replace("power",keyPower);
    }

    public void move(KeyCode key){
        if (control.get("left") == key){
            x -= 3;
        }
        if (control.get("right") == key){
            x += 3;
        }
        if(control.get("jump") == key){
            y -= 20;
        }
        if(control.get("power") == key){
            usePower();
        }
        update();
    }

    public void usePower(){
        //TO DO
    }

    public void update(){
        name.setLayoutX(x);
        name.setLayoutY(y - 25);
        texture.setX(x);
        texture.setY(y);
    }
}
