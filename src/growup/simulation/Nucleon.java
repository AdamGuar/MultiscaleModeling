/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup.simulation;

import java.awt.Color;

/**
 *
 * @author Dom
 */
public class Nucleon {
    private int x;
    private int y;
    
    private Color color;

    public Nucleon(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
   
}
