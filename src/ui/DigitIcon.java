package ui;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class DigitIcon extends Actor {
    GreenfootImage image;

    public DigitIcon(int digit) {
        if (digit < 0 && digit > 9) {
            image = new GreenfootImage("images/PNG/UI/numeralX.png");
        } else {
            image = new GreenfootImage("images/PNG/UI/numeral" + digit + ".png");
        }
        setImage(image);
    }
}
