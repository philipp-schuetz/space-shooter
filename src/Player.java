import greenfoot.*;
import ui.GameOver;


public class Player extends Actor {
    GreenfootImage image = new GreenfootImage("images/PNG/PlayerShip/playerShip1_blue.png");
    private int speed = 0;
    private int shootCooldownMax = 0;
    private int shootCooldown = 0;
    private final int hpMax = 4;
    private int hp = hpMax;
    private int tier;
    private int tierMax = 3;
    private int score;

    public Player(int tier) {
        this.turn(90);
        setTier(tier);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int value) {
        if (value < this.hp) {
            Greenfoot.playSound("sounds/minecraft_dmg.mp3");
        }
        this.hp = value;
    }

    public int getTier() {
        return this.tier;
    }
    public void setTier(int value) {
        if (value <= tierMax) {
            this.tier = value;
        }
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
        Greenfoot.playSound("sounds/bow_shoot.mp3");
        getWorld().addObject(new Bullet(tier), getX() + image.getHeight(), getY());
        shootCooldown = shootCooldownMax;
    }
    private void evaluateTier() {
        if (tier == 1) {
            image = new GreenfootImage("images/PNG/PlayerShip/playerShip1_blue.png");
            this.speed = 6;
            this.shootCooldownMax = 8;
        } else if (tier == 2) {
            image = new GreenfootImage("images/PNG/PlayerShip/playerShip1_green.png");
            this.speed = 8;
            this.shootCooldownMax = 10;
        } else if (tier == 3) {
            image = new GreenfootImage("images/PNG/PlayerShip/playerShip1_red.png");
            this.speed = 10;
            this.shootCooldownMax = 12;
        }
        setImage(image);
    }
    private void renderGameOver() {
        getWorld().addObject(new GameOver(1000), getWorld().getWidth()/2, 150);
    }

    @Override
    public void act() {
        evaluateTier();
        score += 1;
        moveSelf();
        if (Greenfoot.isKeyDown("space")) {
            shoot();
        }
        if (hp < 1) {
            renderGameOver();
            Greenfoot.stop();
        }
    }
}