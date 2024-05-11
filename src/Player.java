import greenfoot.*;


public class Player extends Actor {
    GreenfootImage image = new GreenfootImage("images/PNG/PlayerShip/playerShip1_blue.png");
    private final int speed = 6;
    private final int shootCooldownMax = 10;
    private int shootCooldown = 0;
    private int hp = 4;
    private int tier;

    private int score;

    public Player(int tier) {
        setImage(image);
        this.turn(90);
        this.tier = tier;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int value) {
        this.hp = value;
    }

    public int getScore() { return score; }

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
    private void renderGameOver() {
        getWorld().showText("Game Over", getWorld().getWidth()/2, 150);
    }

    @Override
    public void act() {
        score += 1;
        moveSelf();
        if (Greenfoot.isKeyDown("space")) {
            shoot();
        }
        if (hp == 0) {
            renderGameOver();
            Greenfoot.stop();
        }
    }
}