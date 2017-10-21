/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup;

import growup.inclusion.InclusionSet;
import growup.inclusion.InclusionTimeType;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_SPACE;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Adam
 */
public class Simulation implements KeyListener, MouseListener {

    Random r, randColor, rHex;
    private String type = "Moore";
    private Cell[][] cells;
    private int width;
    private int height;
    public boolean isFilled = false;
    private InclusionSet inclusions;

    float colorR, colorG, colorB;

    private boolean pause = false;

    public Cell[][] getCells() {
        return cells;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
    
    
    
    public void setCellsByColors(Color[][] colors){
        for(int x = 0 ; x < colors.length ; x ++){
            for(int y = 0 ; y < colors[0].length;y++){
                this.cells[x][y].setColor(colors[x][y]);
                this.cells[x][y].setAlive(true);
            }
           // checkAliveByColor();
            checkFilled();
        }
    }
 
    public int HowManyALive() {
        int counter = 0;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (cells[i][j].isAlive()) {
                    counter++;
                }
            }
        }

        return counter;
    }

    public Simulation(int no, String type, String location, int radius, int width, int height, InclusionSet inclusions) {
        this.width = width / Cell.size;
        this.height = height / Cell.size;
        this.type = type;
        r = new Random();
        randColor = new Random();
        cells = new Cell[width][height];
        this.inclusions = inclusions;
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                cells[i][j] = new Cell(i, j);
                cells[i][j].setAlive(false);
            }

        }
        
        if(inclusions.getTimeType().equals(InclusionTimeType.PRE))
        this.inclusions.setInclusions(cells);
        
        
        if (location.equals("Random")) {
            for (int i = 0; i < no; i++) {
                Random rn = new Random();
                int rangeX = width - 0 + 1;
                int randomNumX = rn.nextInt(rangeX) + 0;

                int rangeY = height - 0 + 1;
                int randomNumY = rn.nextInt(rangeY) + 0;
                randomNumX = randomNumX - 1;
                randomNumY = randomNumY - 1;

                cells[randomNumX][randomNumY].setAlive(true);

            }
        }

        if (location.equals("Równomiernie")) {
            System.out.println(no);
            int colNumber = (int) sqrt(no);    //tutaj zmienic
            int rowNumber = colNumber + 1;         //tutaj zmienic

            int dx = (width - 40) / rowNumber;
            int dy = (height - 40) / colNumber;

            int xLoc = 20;
            int yLoc = 20;
            cells[xLoc][yLoc].setAlive(true);
            for (int i = 0; i < no; i++) {

                for (int cols = 0; cols < colNumber; cols++) {
                    yLoc = yLoc + dy;
                    for (int rows = 0; rows < rowNumber; rows++) {
                        xLoc = xLoc + dx;
                        if (xLoc > width) {
                            xLoc = 20;
                        }
                        if (yLoc > height) {
                            yLoc = 20;
                        }
                        cells[xLoc][yLoc].setAlive(true);

                    }
                }

            }
        }

        if (location.equals("Promień")) {
            // System.out.print(radius);
            for (int i = 0; i < no; i++) {
                while (true) {

                    int belongsCounter = 0;
                    Random rn = new Random();
                    int rangeX = width - 0 + 1;
                    int randomNumX = rn.nextInt(rangeX) + 0;

                    int rangeY = height - 0 + 1;
                    int randomNumY = rn.nextInt(rangeY) + 0;
                    for (int x = 0; x < width; x++) {
                        for (int y = 0; y < height; y++) {

                            if (cells[x][y].isAlive()) {
                                if (GeoOps.belongsRadius(randomNumX, randomNumY, radius, x, y)) {
                                    belongsCounter++;
                                }
                            }

                        }
                    }

                    if (belongsCounter == 0) {
                        cells[randomNumX][randomNumY].setAlive(true);
                        break;
                    }

                }
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (cells[i][j].isAlive()) {
                    colorR = randColor.nextFloat();
                    colorG = randColor.nextFloat();
                    colorB = randColor.nextFloat();
                    
                    Color randomColor = new Color(colorR, colorG, colorB);
                    
                    if(randomColor.equals(Color.BLACK) || randomColor.equals(Color.WHITE)){
                        randomColor = Color.RED;
                    }
                    cells[i][j].setColor(randomColor);
                }
            }
        }

        if (location.equals("Myszka")) {
            pause = true;
        }
    }

    public void drawCells(Graphics g) {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j].drawCell(g);
            }
        }

    }
    
    public void checkAliveByColor(){        
         for(int x = 0 ; x < this.cells.length ; x=x+1){
            for(int y = 0 ; y < this.cells[0].length;y=y+1){
                if(this.cells[x][y].getColor()==null || this.cells[x][y].getColor().getRGB()==Color.WHITE.getRGB()){
                    this.cells[x][y].setAlive(false);
                    this.cells[x][y].setNextIteration(false);
                }
                else{
                    this.cells[x][y].setAlive(true);
                    this.cells[x][y].setNextIteration(true);
                }
            }
        }
        
    }
    
    
    private boolean checkFilled() {
        boolean result = false;
        int deadCounter = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!cells[i][j].isAlive()) {
                    deadCounter++;
                }
                else if(cells[i][j].getColor().getRGB()==Color.WHITE.getRGB()){
                    cells[i][j].setAlive(false);
                    deadCounter++;
                }
            }
        }
        if (deadCounter != 0) {
            result = false;
        } else {
            result = true;
        }

        return result;
    }

    public void update() {

        if (!checkFilled() && !pause) {

            Color winner = Color.PINK;
            ArrayList<Color> ColorList = new ArrayList<>();

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {

                    if (!cells[i][j].isAlive()&&!cells[i][j].isIsInclusion()) {

                        int mx = i - 1;
                        if (mx < 0) {
                            mx = width - 1;
                        }
                        int my = j - 1;
                        if (my < 0) {
                            my = height - 1;
                        }
                        int gx = (i + 1) % width;
                        int gy = (j + 1) % height;

                        int alivecounter = 0;

                        if (type.equals("Moore")) {

                            if (cells[mx][my].isAlive()) {
                                ColorList.add(cells[mx][my].getColor());
                            }

                            if (cells[mx][j].isAlive()) {
                                ColorList.add(cells[mx][j].getColor());
                            }

                            if (cells[mx][gy].isAlive()) {
                                ColorList.add(cells[mx][gy].getColor());
                            }
                            if (cells[i][my].isAlive()) {
                                ColorList.add(cells[i][my].getColor());
                            }
                            if (cells[i][gy].isAlive()) {
                                ColorList.add(cells[i][gy].getColor());
                            }
                            if (cells[gx][my].isAlive()) {
                                ColorList.add(cells[gx][my].getColor());
                            }
                            if (cells[gx][j].isAlive()) {
                                ColorList.add(cells[gx][j].getColor());
                            }
                            if (cells[gx][gy].isAlive()) {
                                ColorList.add(cells[gx][gy].getColor());
                            }
                        }

                        if (type.equals("HexLewy")) {

                            if (cells[mx][my].isAlive()) {
                                ColorList.add(cells[mx][my].getColor());
                            }

                            if (cells[mx][j].isAlive()) {
                                ColorList.add(cells[mx][j].getColor());
                            }

                            //if(cells[mx][gy].isAlive()) ColorList.add(cells[mx][gy].color);
                            if (cells[i][my].isAlive()) {
                                ColorList.add(cells[i][my].getColor());
                            }
                            if (cells[i][gy].isAlive()) {
                                ColorList.add(cells[i][gy].getColor());
                            }
                            //if(cells[gx][my].isAlive()) ColorList.add(cells[gx][my].color);
                            if (cells[gx][j].isAlive()) {
                                ColorList.add(cells[gx][j].getColor());
                            }
                            if (cells[gx][gy].isAlive()) {
                                ColorList.add(cells[gx][gy].getColor());
                            }
                        }

                        if (type.equals("HexPrawy")) {

                             //   if(cells[mx][my].isAlive()) ColorList.add(cells[mx][my].color);
                            if (cells[mx][j].isAlive()) {
                                ColorList.add(cells[mx][j].getColor());
                            }

                            if (cells[mx][gy].isAlive()) {
                                ColorList.add(cells[mx][gy].getColor());
                            }
                            if (cells[i][my].isAlive()) {
                                ColorList.add(cells[i][my].getColor());
                            }
                            if (cells[i][gy].isAlive()) {
                                ColorList.add(cells[i][gy].getColor());
                            }
                            if (cells[gx][my].isAlive()) {
                                ColorList.add(cells[gx][my].getColor());
                            }
                            if (cells[gx][j].isAlive()) {
                                ColorList.add(cells[gx][j].getColor());
                            }
                            //if(cells[gx][gy].isAlive()) ColorList.add(cells[gx][gy].color);
                        }

                        if (type.equals("HexRandom")) {
                            rHex = new Random();
                            boolean choice = rHex.nextBoolean();
                            if (choice) {
                                if (cells[mx][j].isAlive()) {
                                    ColorList.add(cells[mx][j].getColor());
                                }
                                if (cells[mx][gy].isAlive()) {
                                    ColorList.add(cells[mx][gy].getColor());
                                }
                                if (cells[i][my].isAlive()) {
                                    ColorList.add(cells[i][my].getColor());
                                }
                                if (cells[i][gy].isAlive()) {
                                    ColorList.add(cells[i][gy].getColor());
                                }
                                if (cells[gx][my].isAlive()) {
                                    ColorList.add(cells[gx][my].getColor());
                                }
                                if (cells[gx][j].isAlive()) {
                                    ColorList.add(cells[gx][j].getColor());
                                }

                            } else {

                                if (cells[mx][my].isAlive()) {
                                    ColorList.add(cells[mx][my].getColor());
                                }
                                if (cells[mx][j].isAlive()) {
                                    ColorList.add(cells[mx][j].getColor());
                                }
                                if (cells[i][my].isAlive()) {
                                    ColorList.add(cells[i][my].getColor());
                                }
                                if (cells[i][gy].isAlive()) {
                                    ColorList.add(cells[i][gy].getColor());
                                }
                                if (cells[gx][j].isAlive()) {
                                    ColorList.add(cells[gx][j].getColor());
                                }
                                if (cells[gx][gy].isAlive()) {
                                    ColorList.add(cells[gx][gy].getColor());
                                }

                            }
                        }

                        if (type.equals("Penta")) {
                            int randPent = ThreadLocalRandom.current().nextInt(1, 4 + 1);

                            if (randPent == 1) {
                                if (cells[mx][my].isAlive()) {
                                    ColorList.add(cells[mx][my].getColor());
                                }
                                if (cells[mx][j].isAlive()) {
                                    ColorList.add(cells[mx][j].getColor());
                                }
                                if (cells[mx][gy].isAlive()) {
                                    ColorList.add(cells[mx][gy].getColor());
                                }
                                if (cells[i][my].isAlive()) {
                                    ColorList.add(cells[i][my].getColor());
                                }
                                if (cells[i][gy].isAlive()) {
                                    ColorList.add(cells[i][gy].getColor());
                                }
                                if (cells[gx][my].isAlive()) {
                                    ColorList.add(cells[gx][my].getColor());
                                }
                                if (cells[gx][j].isAlive()) {
                                    ColorList.add(cells[gx][j].getColor());
                                }
                                if (cells[gx][gy].isAlive()) {
                                    ColorList.add(cells[gx][gy].getColor());
                                }
                            } else if (randPent == 2) {
                                if (cells[mx][my].isAlive()) {
                                    ColorList.add(cells[mx][my].getColor());
                                }
                                if (cells[mx][j].isAlive()) {
                                    ColorList.add(cells[mx][j].getColor());
                                }
                                if (cells[mx][gy].isAlive()) {
                                    ColorList.add(cells[mx][gy].getColor());
                                }
                                if (cells[i][my].isAlive()) {
                                    ColorList.add(cells[i][my].getColor());
                                }
                                if (cells[i][gy].isAlive()) {
                                    ColorList.add(cells[i][gy].getColor());
                                }
                                if (cells[gx][my].isAlive()) {
                                    ColorList.add(cells[gx][my].getColor());
                                }
                                if (cells[gx][j].isAlive()) {
                                    ColorList.add(cells[gx][j].getColor());
                                }
                                if (cells[gx][gy].isAlive()) {
                                    ColorList.add(cells[gx][gy].getColor());
                                }
                            } else if (randPent == 3) {
                                if (cells[mx][my].isAlive()) {
                                    ColorList.add(cells[mx][my].getColor());
                                }
                                if (cells[mx][j].isAlive()) {
                                    ColorList.add(cells[mx][j].getColor());
                                }
                                //  if(cells[mx][gy].isAlive()) ColorList.add(cells[mx][gy].color);
                                if (cells[i][my].isAlive()) {
                                    ColorList.add(cells[i][my].getColor());
                                }
                                if (cells[i][gy].isAlive()) {
                                    ColorList.add(cells[i][gy].getColor());
                                }
                                //   if(cells[gx][my].isAlive()) ColorList.add(cells[gx][my].color);
                                if (cells[gx][j].isAlive()) {
                                    ColorList.add(cells[gx][j].getColor());
                                }
                                //   if(cells[gx][gy].isAlive()) ColorList.add(cells[gx][gy].color);
                            } else if (randPent == 4) {
                                //  if(cells[mx][my].isAlive()) ColorList.add(cells[mx][my].color);
                                if (cells[mx][j].isAlive()) {
                                    ColorList.add(cells[mx][j].getColor());
                                }
                                if (cells[mx][gy].isAlive()) {
                                    ColorList.add(cells[mx][gy].getColor());
                                }
                                if (cells[i][my].isAlive()) {
                                    ColorList.add(cells[i][my].getColor());
                                }
                                if (cells[i][gy].isAlive()) {
                                    ColorList.add(cells[i][gy].getColor());
                                }
                                //   if(cells[gx][my].isAlive()) ColorList.add(cells[gx][my].color);
                                if (cells[gx][j].isAlive()) {
                                    ColorList.add(cells[gx][j].getColor());
                                }
                                //   if(cells[gx][gy].isAlive()) ColorList.add(cells[gx][gy].color);
                            } else if (randPent == 5) {
                                //  if(cells[mx][my].isAlive()) ColorList.add(cells[mx][my].color);
                                if (cells[mx][j].isAlive()) {
                                    ColorList.add(cells[mx][j].getColor());
                                }
                                //   if(cells[mx][gy].isAlive()) ColorList.add(cells[mx][gy].color);
                                if (cells[i][my].isAlive()) {
                                    ColorList.add(cells[i][my].getColor());
                                }
                                if (cells[i][gy].isAlive()) {
                                    ColorList.add(cells[i][gy].getColor());
                                }
                                //    if(cells[gx][my].isAlive()) ColorList.add(cells[gx][my].color);
                                if (cells[gx][j].isAlive()) {
                                    ColorList.add(cells[gx][j].getColor());
                                }
                                if (cells[gx][gy].isAlive()) {
                                    ColorList.add(cells[gx][gy].getColor());
                                }
                            }
                        } else if (type.equals("VonNeuman")) {

                              //  if(cells[mx][my].isAlive()) ColorList.add(cells[mx][my].color);
                            if (cells[mx][j].isAlive()) {
                                ColorList.add(cells[mx][j].getColor());
                            }

                            //if(cells[mx][gy].isAlive()) ColorList.add(cells[mx][gy].color);
                            if (cells[i][my].isAlive()) {
                                ColorList.add(cells[i][my].getColor());
                            }
                            if (cells[i][gy].isAlive()) {
                                ColorList.add(cells[i][gy].getColor());
                            }
                            //if(cells[gx][my].isAlive()) ColorList.add(cells[gx][my].color);
                            if (cells[gx][j].isAlive()) {
                                ColorList.add(cells[gx][j].getColor());
                            }
                            //if(cells[gx][gy].isAlive()) ColorList.add(cells[gx][gy].color);
                        }
                        ColorList.removeAll(Collections.singleton(Color.BLACK));
                        ColorList.removeAll(Collections.singleton(Color.WHITE));
                        if (!ColorList.isEmpty()) {

                            cells[i][j].setNextIteration(true);
                            int winnerFreq = 0;
                            for (int ii = 0; ii < ColorList.size(); ii++) {
                                if (Collections.frequency(ColorList, ii) > winnerFreq) {
                                    winnerFreq = Collections.frequency(ColorList, ii);
                                }
                            }
                            for (int jj = 0; jj < ColorList.size(); jj++) {
                                if (Collections.frequency(ColorList, jj) == winnerFreq) {
                                    winner = ColorList.get(jj);
                                }
                                cells[i][j].setColor(winner);
                            }

                            ColorList.clear();
                        } else {
                            //cells[i][j].setNextIteration(false);
                        }

                    }
                }
            }

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    cells[i][j].nextIteration();
                }
            }

        }else if(checkFilled() && !inclusions.isPainted()){
            checkBorders();
            if(inclusions.getTimeType().equals(InclusionTimeType.POST))
                inclusions.setInclusions(cells);
        }
    }
    
    
    public void checkBorders() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int mx = i - 1;
                if (mx < 0) {
                    mx = width - 1;
                }
                int my = j - 1;
                if (my < 0) {
                    my = height - 1;
                }
                int gx = (i + 1) % width;
                int gy = (j + 1) % height;

                int anotherColorCounter = 0;

                Color myColor = cells[i][j].getColor();

                if (!cells[mx][my].getColor().equals(myColor)) {
                    anotherColorCounter++;
                }

                if (!cells[mx][j].getColor().equals(myColor)) {
                    anotherColorCounter++;
                }

                if (!cells[mx][gy].getColor().equals(myColor)) {
                    anotherColorCounter++;
                }
                if (!cells[i][my].getColor().equals(myColor)) {
                    anotherColorCounter++;
                }
                if (!cells[i][gy].getColor().equals(myColor)) {
                    anotherColorCounter++;
                }
                if (!cells[gx][my].getColor().equals(myColor)) {
                    anotherColorCounter++;
                }
                if (!cells[gx][j].getColor().equals(myColor)) {
                    anotherColorCounter++;
                }
                if (!cells[gx][gy].getColor().equals(myColor)) {
                    anotherColorCounter++;
                }
                
                if(anotherColorCounter!=0) cells[i][j].setBorder(true);

            }
        }
    }
    
    

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        int codePause = VK_SPACE;

        if (ke.getKeyCode() == codePause) {

            if (pause) {
                pause = false;
            } else {
                pause = true;
            }
         //   System.out.println("PAUSA");

        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX() / Cell.size;
        int my = e.getY() / Cell.size;
        if (e.getButton() == 1) {
            colorR = randColor.nextFloat();
            colorG = randColor.nextFloat();
            colorB = randColor.nextFloat();
            cells[mx][my].setColor(new Color(colorR, colorG, colorB));
            cells[mx][my].setAlive(true);

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
