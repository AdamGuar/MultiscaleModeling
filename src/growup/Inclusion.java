/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup;


import java.awt.Color;
import java.util.Random;

/**
 *
 * @author adwoznia
 */
public class Inclusion {

    private int size;
    private int xLocation;
    private int yLocation;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Inclusion(int size, int screenWidth, int screenHeight) {

        Random rn = new Random();
        int rangeX = screenWidth - 0 + 1;
        int randomNumX = rn.nextInt(rangeX) + 0;

        int rangeY = screenHeight - 0 + 1;
        int randomNumY = rn.nextInt(rangeY) + 0;
        randomNumX = randomNumX - 1;
        randomNumY = randomNumY - 1;
        
        xLocation = randomNumX;
        yLocation = randomNumY;

        this.size = size;
    }

    public int getxLocation() {
        return xLocation;
    }

    public void setxLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public static Color getInclusionColor() {
        return Color.BLACK;
    }

}
