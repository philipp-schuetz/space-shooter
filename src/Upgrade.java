import greenfoot.Actor;

import java.util.List;

public class Upgrade extends Actor {
    private UpgradeType type;
    private final int hpAdd = 1;
    public Upgrade(UpgradeType type) {
        this.type = type;
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
