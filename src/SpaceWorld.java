import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import ui.HpUi;

import java.util.List;
import java.util.Random;

/**
 * This subclass is just an example. You can delete it or change the code if you want.
 * It's not necessary for the scrolling system.
 */
public class SpaceWorld extends World
{
    ShipActor player;
    
    public SpaceWorld() {
        super(1200, 580, 1);
        setBackground(new GreenfootImage("images/Backgrounds/blue.png"));
        createSpaceWorld();
    }

    public void createSpaceWorld() {
        player = new ShipActor(1);
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
        int spawnY = rand.nextInt(getHeight()-300)+150;
        if (objGenerated == 0) {
            addObject(new Enemy(1), getWidth()-100, spawnY);
        }
    }

    public void act() {
        renderHpUi();
        addEnemies();
    }
}