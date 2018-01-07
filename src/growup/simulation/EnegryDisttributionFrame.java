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
 * @author adwoznia
 */
public class EnegryDisttributionFrame extends JFrame{
    
    
    private Screen s;
    private int witdh;
    private int height;
    private Cell[][] cells;

    public EnegryDisttributionFrame(int witdh, int height) {
        this.witdh = witdh;
        this.height = height;
    }
    
    
    public void repaint() {
        s.repaint();
    }
    
    
    private class Screen extends JLabel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            for(int i=0; i < cells.length;i++){
                for(int j=0;j<cells[0].length;j++)
                    cells[i][j].drawCellenegry(g);
            }
        }

    }
    
    
}
