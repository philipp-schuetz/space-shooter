import greenfoot.*;
import ui.DigitIcon;
import ui.HpUi;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SpaceWorld extends World
{
    Player player;
    
    public SpaceWorld() {
        super(1200, 580, 1);
        setBackground(new GreenfootImage("images/Backgrounds/blue.png"));
        createSpaceWorld();
    }

    public void createSpaceWorld() {
        player = new Player(1);
        addObject(player, 100, getHeight()/2);
    }
    private void renderHpUi() {
        List<HpUi> uiList = getObjects(HpUi.class);
        for (HpUi hpUi : uiList) {
            removeObject(hpUi);
        }
        for (int i = 0; i < player.getHp(); i++) {
            addObject(new HpUi(), getWidth()-(i+1)*50, 50);
        }
    }

    public void addEnemies() {
        Random rand = new Random();
        int objGenerated = rand.nextInt(80);
        int spawnX = getWidth()-100;
        int spawnY = rand.nextInt(getHeight()-300)+150;
        if (objGenerated == 0) {
            int enemyTier = 1;
            if (player.getScore() > 1000) {
                enemyTier = 2;
            } else if (player.getScore() > 2000) {
                enemyTier = 3;
            }
            Enemy enemy = new Enemy(enemyTier);
            List<Enemy> objectsAtSpawnPos = this.getObjectsAt(spawnX, spawnY, Enemy.class);
            if (objectsAtSpawnPos.isEmpty()) {
                addObject(enemy, spawnX, spawnY);
            }
        }
    }

    public void addUpgrades() {
        Random rand = new Random();
        int objGenerated = rand.nextInt(2000);
        int spawnY = rand.nextInt(getHeight()-300)+150;
        if (objGenerated == 0) {
            addObject(new Upgrade(UpgradeType.HP), getWidth()-100, spawnY);
        }else if (objGenerated == 1) {
            addObject(new Upgrade(UpgradeType.TIER), getWidth()-100, spawnY);
        }
    }

    private void renderPlayerScore() {
        int playerScore = player.getScore();
        LinkedList<Integer> result = new LinkedList<>();
        while (playerScore > 0) {
            result.push(playerScore % 10);
            playerScore /= 10;
        }
        removeObjects(getObjects(DigitIcon.class));
        int xPos = 50;
        for (int digit: result) {
            addObject(new DigitIcon(digit), xPos, 50);
            xPos += 25;
        }
    }

    public void act() {
        renderHpUi();
        addEnemies();
        addUpgrades();
        renderPlayerScore();
    }
}