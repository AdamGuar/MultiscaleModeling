/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Adam
 */
public class Cell {

    private int x;
    private int y;
    static int size = 1;
    private boolean alive;
    private boolean nextIteration;
    private Color color;
    private boolean isInclusion;

    public boolean isIsInclusion() {
        return isInclusion;
    }

    public void setIsInclusion(boolean isInclusion) {
        this.isInclusion = isInclusion;
    }
    
    
    

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setNextIteration(boolean nextIt) {
        this.nextIteration = nextIt;
    }

    public void nextIteration() {
        alive = nextIteration;

    }

    public Cell(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public void drawCell(Graphics g) {

        /*g.setColor(Color.BLACK);
            g.drawRect(x*size, y*size, size, size);
            if(alive) g.setColor(this.color);
            else
            g.setColor(Color.WHITE);
            g.fillRect(x*size+1, y*size+1, size-1 , size-1);*/
        if (alive) {
            g.setColor(this.color);
        } else {
            g.setColor(Color.WHITE);
        }
        
        if (isInclusion){
            g.setColor(Inclusion.getInclusionColor());
        }
        
        g.fillRect(x * size, y * size, size, size);

    }

}
