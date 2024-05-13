package ui;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.awt.*;
import java.io.InputStream;
import java.util.LinkedList;

public class GameOver extends Actor {
    private int score;

    public GameOver(int score) {
        this.score = score;
        GreenfootImage image = new GreenfootImage("images/PNG/UI/game-over.png");
        setImage(image);

        LinkedList<Integer> result = new LinkedList<>();
        while (score > 0) {
            result.push(score % 10);
            score /= 10;
        }
        int xPos = getX();
        for (int digit : result) {
            getWorld().addObject(new DigitIcon(digit), xPos, getY() + 50);
            xPos += 25;
        }
    }
}
