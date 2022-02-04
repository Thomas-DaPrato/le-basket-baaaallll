package basket_baaallll.code_java;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.FileNotFoundException;

public class Play {
    public Play(Group root, Scene scene) throws FileNotFoundException {
        Player j1 = new Player(100,800,"j1","src/main/resources/images/perso1d.png",root);
        j1.initControl(KeyCode.Q, KeyCode.D, KeyCode.SPACE,KeyCode.SHIFT);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            j1.move(key.getCode());
        });
    }
}
