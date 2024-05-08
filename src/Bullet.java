import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Bullet extends Actor {
    GreenfootImage image;
    private int damage = 0;
    private int speed = 16;
    public Bullet(int tier) {
        if (tier == 1) {
            image = new GreenfootImage("images/PNG/Lasers/laserBlue02.png");
            damage = 1;
        } else if (tier == 2) {
            image = new GreenfootImage("images/PNG/Lasers/laserGreen02.png");
            damage = 2;
        } else if (tier == 3) {
            image = new GreenfootImage("images/PNG/Lasers/laserRed02.png");
            damage = 3;
        }
        setImage(image);
        this.turn(90);
    }

    @Override
    public void act() {
        setLocation(getX() + speed, getY());
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
