package basket_baaallll.code_java.Power;

import basket_baaallll.code_java.entities.Player;

public abstract class PowerPlayer implements Power {
    protected Player player;

    public PowerPlayer(Player player) {
        this.player = player;
    }

    public abstract void use();
}
