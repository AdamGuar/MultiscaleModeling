/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup;

import growup.inclusion.Inclusion;
import growup.inclusion.InclusionSet;
import java.util.List;

/**
 *
 * @author Adam
 */
public class simThread implements Runnable {

    private String location;
    public float simSpeed;
    public int no;
    private int radius;
    private int width;
    private int height;
    private InclusionSet incl;

    public InclusionSet getIncl() {
        return incl;
    }

    public void setIncl(InclusionSet incl) {
        this.incl = incl;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    private String type;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void run() {
        Frame f = new Frame(this.width, this.height,this.incl);
        f.setSimType(type);
        //f.iterationTime=simSpeed;
        f.no = no;

        f.setVisible(true);
        f.setResizable(false);

        f.setLocationRelativeTo(null);

        f.createScreen(location, radius);

        long lastFrame = System.currentTimeMillis();

        while (true) {
            /*long thisFrame= System.currentTimeMillis();
             float tslf = (float) ((thisFrame-lastFrame)/100.0);
             lastFrame=thisFrame;*/

            //f.update(tslf);
            f.update();

            f.repaint();
            /*try {
             Thread.sleep(10);
             } catch (InterruptedException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
             }*/

        }
    }

}
