import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.List;

public class Bullet extends Actor {
    GreenfootImage image;
    private int damage = 0;
    private int speed = 16;
    public Bullet(int tier) {
        if (tier == 1) {
            image = new GreenfootImage("images/lasers/laserBlue02.png");
            damage = 1;
        } else if (tier == 2) {
            image = new GreenfootImage("images/lasers/laserGreen02.png");
            damage = 2;
        } else if (tier == 3) {
            image = new GreenfootImage("images/lasers/laserRed02.png");
            damage = 3;
        }
        setImage(image);
        this.turn(90);
    }

    private void attack() {
        List<Enemy> enemies = getIntersectingObjects(Enemy.class);
        if (enemies.isEmpty()) {
            return;
        }
        Enemy enemy = getIntersectingObjects(Enemy.class).get(0);
        enemy.setHp(enemy.getHp() - damage);
        getWorld().removeObject(this);
    }

    @Override
    public void act() {
        setLocation(getX() + speed, getY());
        attack();
        if (getWorld() != null && isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
