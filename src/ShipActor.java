import greenfoot.*;


public class ShipActor extends Actor {
    GreenfootImage image = new GreenfootImage("images/PNG/PlayerShip/playerShip1_blue.png");
    private final int speed = 6;
    private final int shootCooldownMax = 10;
    private int shootCooldown = 0;
    private int hp = 4;
    private int tier;

    public ShipActor(int tier) {
        setImage(image);
        this.turn(90);
        this.tier = tier;
    }

    public int getHp() {
        return hp;
    }

    private void moveSelf() {
        int newX = 0;
        int newY = 0;
        if (Greenfoot.isKeyDown("w")) {
            newX = getX();
            newY = getY() - speed;
        }
        if (Greenfoot.isKeyDown("s")) {
            newX = getX();
            newY = getY() + speed;
        }

        // check if ship is still inside world (distance = 50)
        if (newY > 50 && newY < getWorld().getHeight() - 1 - 50) {
            setLocation(newX, newY);
        }
    }

    private void shoot() {
        if (shootCooldown > 0) {
            shootCooldown--;
            return;
        }
        getWorld().addObject(new Bullet(tier), getX() + image.getHeight(), getY());
        shootCooldown = shootCooldownMax;
    }

    @Override
    public void act() {
        moveSelf();
        if (Greenfoot.isKeyDown("space")) {
            shoot();
        }
    }
}