import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.List;

public class Upgrade extends Actor {
    private final UpgradeType type;
    private final int hpAdd = 1;
    private GreenfootImage image;
    public Upgrade(UpgradeType type) {
        if (type == UpgradeType.HP) {
            image = new GreenfootImage("images/PNG/Power-ups/pill_red.png");
        } else if (type == UpgradeType.TIER) {
            image = new GreenfootImage("images/PNG/Power-ups/bolt_gold.png");
        }
        this.type = type;
        setImage(image);
    }

    private void applyUpgrade() {
        List<Player> playerList = getIntersectingObjects(Player.class);
        if (playerList.size() == 1) {
            Player player = playerList.get(0);

            if (type == UpgradeType.HP) {
                player.setHp(player.getHp()+hpAdd);
            } else if (type == UpgradeType.TIER) {
                player.setTier(player.getTier()+1);
            }
        }
    }
}
