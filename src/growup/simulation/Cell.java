/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup.simulation;

import growup.inclusion.Inclusion;
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
    private boolean border;
    private boolean saved = false;
    private int savedID;
    private int H;
    private boolean recrystalized = false;

    public boolean isRecrystalized() {
        return recrystalized;
    }
    
    

    public int getH() {
        return H;
    }

    public void setH(int H) {
        this.H = H;
    }
    
    public int getSavedID() {
        return savedID;
    }

    public void setSavedID(int savedID) {
        this.savedID = savedID;
    }
   
    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
    
    public boolean isBorder() {
        return border;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }
    

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
        
        if (saved){
            if(SimulationControl.SECOND_GROW_TYPE.equals("Substructure"))
                g.setColor(SimulationControl.SAVED_CELLS.get(this.savedID));
            else
                g.setColor(Color.PINK);
        }
        
        
        g.fillRect(x * size, y * size, size, size);

    }
    
    public void drawCellenegry(Graphics g) {

        
       if(this.recrystalized)
           g.setColor(Color.black);
       else if (SimulationControl.ENERGY_HOMOGENOUS)
           g.setColor(Color.blue);
       else
           if(this.H<SimulationControl.REC_ENERGY_HIGHER_VALUE)
               g.setColor(Color.blue);
           else 
               g.setColor(Color.green);
        
        
        g.fillRect(x * size, y * size, size, size);

    }
    
    

}
