package basket_baaallll.code_java.Power.player;

import basket_baaallll.code_java.Power.PowerPlayer;
import basket_baaallll.code_java.entities.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.HashMap;

public class FreezePlayer extends PowerPlayer {

    private int timer = 5;
    private final HashMap<String, KeyCode> initControl = new HashMap<>();

    public FreezePlayer(Player player) {
        super(player);
        initControl.put("left",player.getControl().get("left"));
        initControl.put("right",player.getControl().get("right"));
        initControl.put("jump",player.getControl().get("jump"));
        initControl.put("power",player.getControl().get("power"));
    }

    @Override
    public void use() {
        player.setControl(null,null,null,null);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> {
            timer -= 1;
            if (timer == 0){
                timer = 5;
                player.setControl(initControl.get("left"),initControl.get("right"),initControl.get("jump"),initControl.get("power"));
            }
        }));
        timeline.setCycleCount(5);
        timeline.play();
    }
}
