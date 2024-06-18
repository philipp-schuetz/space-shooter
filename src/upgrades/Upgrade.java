package upgrades;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import ships.Player;

import java.util.List;

public class Upgrade extends Actor {
    private final UpgradeType type;
    private final int hpAdd = 1;
    private GreenfootImage image;
    private final int speed = 4;

    public Upgrade(UpgradeType type) {
        if (type == UpgradeType.HP) {
            image = new GreenfootImage("images/powerUps/pill_red.png");
        } else if (type == UpgradeType.TIER) {
            image = new GreenfootImage("images/powerUps/bolt_gold.png");
        }
        this.type = type;
        setImage(image);
    }

    private void applyUpgrade() {
        List<Player> playerList = getIntersectingObjects(Player.class);
        if (playerList.size() == 1) {
            Player player = playerList.get(0);
            if (type == UpgradeType.HP) {
                player.setHp(player.getHp() + hpAdd);
                Greenfoot.playSound("sounds/minecraft-eating.mp3");
            } else if (type == UpgradeType.TIER) {
                player.setTier(player.getTier() + 1);
                Greenfoot.playSound("sounds/minecraft-levelup.mp3");
            }
            getWorld().removeObject(this);
        }
    }

    private void moveSelf() {
        setLocation(getX() - speed, getY());
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }

    @Override
    public void act() {
        moveSelf();
        if (getWorld() != null) {
            applyUpgrade();
        }
    }
}
