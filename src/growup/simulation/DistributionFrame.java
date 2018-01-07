/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup.simulation;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Dom
 */
public class DistributionFrame extends JFrame {

    private Screen s;
    private Cell[][] cells;
    private int width;
    private int height;


    public DistributionFrame(Cell[][] cells, int width, int height) {
        this.cells = cells;
        this.width = width;
        this.height = height;
        setSize(width, height);
        s = new Screen();
        add(s);
    }
    
    
    
    
    private class Screen extends JLabel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    cells[i][j].drawCellenegry(g);
                }
            }
        }

    }

}
