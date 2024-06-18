package ui;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class DigitIcon extends Actor {
    GreenfootImage image;

    public DigitIcon(int digit) {
        image = new GreenfootImage("images/ui/numeral" + digit + ".png");
        setImage(image);
    }
}
