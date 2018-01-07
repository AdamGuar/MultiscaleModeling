/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup.simulation;

import growup.Cord;
import growup.GeoOps;
import growup.NeighberhoodChecker;
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
import javax.swing.JOptionPane;

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
    NeighberhoodChecker checker;
    private boolean borderFlag = false;
    public static boolean control = false;
    public static int howMany = 99;
    private int mcNo = SimulationControl.MONTE_CARLO_NO;

    float colorR, colorG, colorB;

    private boolean pause = false;

    public Cell[][] getCells() {
        return cells;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void setCellsByColors(Color[][] colors) {
        for (int x = 0; x < colors.length; x++) {
            for (int y = 0; y < colors[0].length; y++) {
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

    public Simulation(int no, String type, String location, int radius, int width, int height, InclusionSet inclusions, int probability) {
        this.mcNo = no;
        if (SimulationControl.IS_MONTE_CARLO) {
            simulationMCConstructor(no, type, location, radius, width, height, inclusions, probability);
        } else {
            simulationCAConstructor(no, type, location, radius, width, height, inclusions, probability);
        }

    }

    public void simulationCAConstructor(int no, String type, String location, int radius, int width, int height, InclusionSet inclusions, int probability) {
        this.width = width / Cell.size;
        this.height = height / Cell.size;
        this.type = type;
        r = new Random();
        randColor = new Random();
        cells = new Cell[width][height];
        this.inclusions = inclusions;
        this.checker = new NeighberhoodChecker();
        checker.setProbability(probability);

        clearScreen();
        paintCells(location, no, radius);

        if (inclusions.getTimeType().equals(InclusionTimeType.PRE)) {
            this.inclusions.setInclusions(cells);
        }
    }

    ;
    
    public void simulationMCConstructor(int no, String type, String location, int radius, int width, int height, InclusionSet inclusions, int probability) {
        this.width = width;
        this.height = height;

        this.checker = new NeighberhoodChecker();
        checker.setProbability(probability);

        cells = new Cell[width][height];
        ArrayList<Color> colorList = new ArrayList<>();
        randColor = new Random();

        Random randomGenerator = new Random();

        for (int x = 0; x < no; x++) {

            colorR = randColor.nextFloat();
            colorG = randColor.nextFloat();
            colorB = randColor.nextFloat();

            colorList.add(new Color(colorR, colorG, colorB));
            System.out.println(colorList.size());
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell(i, j);
                cells[i][j].setAlive(true);
                cells[i][j].setNextIteration(true);

            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (cells[i][j].isAlive()) {
                    int index = randomGenerator.nextInt(no);
                    cells[i][j].setColor(colorList.get(index));
                    cells[i][j].setAlive(true);
                    cells[i][j].setNextIteration(false);
                }
            }
        }
    }

    ;
    
    
    public void regenerateMC(int no) {

        ArrayList<Color> colorList = new ArrayList<>();
        randColor = new Random();

        Random randomGenerator = new Random();

        for (int x = 0; x < no; x++) {

            colorR = randColor.nextFloat();
            colorG = randColor.nextFloat();
            colorB = randColor.nextFloat();

            colorList.add(new Color(colorR, colorG, colorB));
            System.out.println(colorList.size());
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (!cells[i][j].isSaved()) {
                    cells[i][j] = new Cell(i, j);
                    cells[i][j].setAlive(true);
                }
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (cells[i][j].isAlive() && !cells[i][j].isSaved()) {
                    int index = randomGenerator.nextInt(no);
                    cells[i][j].setColor(colorList.get(index));
                    cells[i][j].setAlive(true);
                    cells[i][j].setNextIteration(false);
                }
            }
        }
    }

    ;
    
    
    

    public void drawCells(Graphics g) {

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j].drawCell(g);
            }
        }

    }

    public void checkAliveByColor() {
        for (int x = 0; x < this.cells.length; x = x + 1) {
            for (int y = 0; y < this.cells[0].length; y = y + 1) {
                if (this.cells[x][y].getColor() == null || this.cells[x][y].getColor().getRGB() == Color.WHITE.getRGB()) {
                    this.cells[x][y].setAlive(false);
                    this.cells[x][y].setNextIteration(false);
                } else {
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
                } else if (cells[i][j].getColor() == null || cells[i][j].getColor().getRGB() == Color.WHITE.getRGB()) {
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
        if (SimulationControl.DISTRIBUTE_ENERGY) {
            distributeEnergy();
        }
        if (SimulationControl.IS_MONTE_CARLO) {
            updateMC();
        }
        if (!SimulationControl.IS_MONTE_CARLO) {
            updateCA();
        }
        if (SimulationControl.RECRYSTALIZE) {
            updateSRX();
        }
        SimulationControl.PUBLIC_CELLS = this.cells;
    }

    int iterator = 0;
    ArrayList<Cord> points = new ArrayList<>();

    public void updateMC() {
        checkControl();
        if (control) {
            if (howMany == iterator) {
                pause = true;

                iterator = 0;
            }
        }
        if (!pause) {

            //System.out.println("ITERACJA NR: "+iterator);
            int energyPrev = 0;
            int energySum = 0;
            ArrayList<Color> nbColors = new ArrayList<>();

            if (points.size() >= cells.length * cells[0].length) {

                iterator++;
                points = new ArrayList<>();
            }
            //  checkBorders();
            Random rn = new Random();
            int rangeX = (width - 1) - 0 + 1;
            int i = rn.nextInt(rangeX) + 0;

            int rangeY = (height - 1) - 0 + 1;
            int j = rn.nextInt(rangeY) + 0;

            //&&!points.contains(new Cartez(i,j))
            // if (cells[i][j].border && !points.contains(new Cartez(i, j))) {
            if (!points.contains(new Cord(i, j))) {
                points.add(new Cord(i, j));

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

                // nbColors.add(cells[i][j].color);
                nbColors.add(cells[mx][my].getColor());
                nbColors.add(cells[mx][j].getColor());
                nbColors.add(cells[mx][gy].getColor());
                nbColors.add(cells[i][my].getColor());
                nbColors.add(cells[i][gy].getColor());
                nbColors.add(cells[gx][my].getColor());
                nbColors.add(cells[gx][j].getColor());
                nbColors.add(cells[gx][gy].getColor());

                for (Color object : nbColors) {
                    energySum = 0;
                    Random rg = new Random();
                    int index = rg.nextInt(nbColors.size());
                    Color tested = nbColors.get(index);

                    if (cells[mx][gy].getColor() != tested) {
                        energySum++;
                    }
                    if (cells[i][my].getColor() != tested) {
                        energySum++;
                    }
                    if (cells[i][gy].getColor() != tested) {
                        energySum++;
                    }
                    if (cells[gx][my].getColor() != tested) {
                        energySum++;
                    }
                    if (cells[gx][j].getColor() != tested) {
                        energySum++;
                    }
                    if (cells[gx][gy].getColor() != tested) {
                        energySum++;
                    }

                    //  System.out.println(dEnergy);
                    if (energySum <= energyPrev) {
                        cells[i][j].setColor(tested);
                    }
                    energyPrev = energySum;

                }

            }
        }

    }

    private void updateCA() {

        checkControl();

        if (!checkFilled() && !pause) {

            Color winner = Color.PINK;
            ArrayList<Color> ColorList = new ArrayList<>();

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {

                    if (!cells[i][j].isAlive() && !cells[i][j].isIsInclusion() && !cells[i][j].isSaved()) {

                        // NeighberhoodChecker.checkNornamNeighbers(cells, i, j, width, height, ColorList);
                        if (type.equals("Moore2")) {
                            checker.checkNeighbersImproved(cells, i, j, width, height, ColorList);
                        } else if (type.equals("Moore")) {
                            checker.checkNornamNeighbers(cells, i, j, width, height, ColorList);
                        }

                    }
                }
            }

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    cells[i][j].nextIteration();
                }
            }

        } else if (checkFilled() && !inclusions.isPainted()) {
            checkBorders();
            if (inclusions.getTimeType().equals(InclusionTimeType.POST)) {
                inclusions.setInclusions(cells);
            }
            for (int k = 0; k < SimulationControl.BORDER_THICKNESS; k++) {
                checkBorders();
                drawBorders();
            }
        } else if (checkFilled()) {
            for (int k = 0; k < SimulationControl.BORDER_THICKNESS; k++) {
                checkBorders();
                drawBorders();
            }
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

                if (anotherColorCounter != 0) {
                    cells[i][j].setBorder(true);
                }

            }
        }
    }

    private void drawBorders() {
        if (SimulationControl.DRAW_BORDERS) {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    if (cells[i][j].isBorder()) {
                        cells[i][j].setIsInclusion(true);
                    } else {
                        cells[i][j].setAlive(false);
                        cells[i][j].setColor(Color.white);
                    }
                    cells[i][j].nextIteration();
                }
            }
            pause = true;
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
        Color color = cells[mx][my].getColor();
        Random rand = new Random();
        int randomNum = rand.nextInt((1000 - 1) + 1) + 1;
        SimulationControl.SAVED_CELLS.put(randomNum, color);

        if (e.getButton() == 1) {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    if (cells[i][j].getColor().equals(color)) {
                        cells[i][j].setSaved(true);
                        cells[i][j].setSavedID(randomNum);
                    }
                }
            }

        }
        JOptionPane.showMessageDialog(null, "Grain with color " + color.toString() + " saved");
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

    private void checkControl() {
        if (SimulationControl.CLEAR_SCREAN) {
            this.type = SimulationControl.TYPE;
            this.checker.setProbability(SimulationControl.PROBABILITY);

            SimulationControl.CLEAR_SCREAN = false;
            clearScreen();

            if (SimulationControl.IS_MONTE_CARLO) {
                regenerateMC(this.mcNo);
            }

        }
        if (SimulationControl.RESTART_SIM) {
            SimulationControl.RESTART_SIM = false;
            paintCells("Random", SimulationControl.GRAINS_NO, 0);
        }
    }

    private void clearScreen() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (cells[i][j] != null && cells[i][j].isSaved()) {
                    cells[i][j].setColor(cells[i][j].getColor());
                    cells[i][j].setNextIteration(false);
                } else {
                    cells[i][j] = new Cell(i, j);
                    cells[i][j].setAlive(false);
                }

            }

        }
    }

    private void paintCells(String location, int no, int radius) {
        if (location.equals("Random")) {
            for (int i = 0; i < no; i++) {
                Random rn = new Random();
                int rangeX = width - 0 + 1;
                int randomNumX = rn.nextInt(rangeX) + 0;

                int rangeY = height - 0 + 1;
                int randomNumY = rn.nextInt(rangeY) + 0;
                randomNumX = randomNumX - 1;
                randomNumY = randomNumY - 1;

                if (randomNumX < 0) {
                    randomNumX = 0;
                }
                if (randomNumY < 0) {
                    randomNumY = 0;
                }

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

                    if (randomColor.equals(Color.BLACK) || randomColor.equals(Color.WHITE)) {
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

    private void distributeEnergy() {
        if (SimulationControl.ENERGY_HOMOGENOUS) {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    cells[i][j].setH(SimulationControl.REC_ENERGY_HIGHER_VALUE);
                }
            }
        } else {
            checkBorders();
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    if (cells[i][j].isBorder()) {
                        cells[i][j].setH(SimulationControl.REC_ENERGY_HIGHER_VALUE);
                    } else {
                        cells[i][j].setH(SimulationControl.REC_ENERGY_LOWER_VALUE);
                    }
                }
            }
        }

        SimulationControl.DISTRIBUTE_ENERGY = false;
    }

    private void updateSRX() {
        if (SimulationControl.INITIALIZE_NUCLEONS) {
            initializeNucleons();
        }
        if (!pause) {
            List<Cord> cords = getPossibleRecNucleonsNbrs();
            int randomNum = ThreadLocalRandom.current().nextInt(0, cords.size()-1);
            int i = cords.get(randomNum).x;
            int j = cords.get(randomNum).y;           
            
            srx(i,j);
            
        }
      /*  if (!pause) {
                
            //System.out.println("ITERACJA NR: "+iterator);
            int energyPrev = 0;
            int energySum = 0;
            ArrayList<Color> nbColors = new ArrayList<>();
            

            if (points.size() >= cells.length * cells[0].length) {

                iterator++;
                points = new ArrayList<>();
            }
            //  checkBorders();
            Random rn = new Random();
            int rangeX = (width - 1) - 0 + 1;
            int i = rn.nextInt(rangeX) + 0;

            int rangeY = (height - 1) - 0 + 1;
            int j = rn.nextInt(rangeY) + 0;

            if (!points.contains(new Cartez(i, j)) && !this.cells[i][j].isRecrystalized()) {
                points.add(new Cartez(i, j));

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

                // nbColors.add(cells[i][j].color);
                nbColors.add(cells[mx][my].getColor());
                nbColors.add(cells[mx][j].getColor());
                nbColors.add(cells[mx][gy].getColor());
                nbColors.add(cells[i][my].getColor());
                nbColors.add(cells[i][gy].getColor());
                nbColors.add(cells[gx][my].getColor());
                nbColors.add(cells[gx][j].getColor());
                nbColors.add(cells[gx][gy].getColor());

                boolean isAnyRecrystalized = false;
                
                int recx=0;
                int recy=0;
                
                if (cells[mx][my].isRecrystalized()) {
                    isAnyRecrystalized = true;
                    recx = my;
                    recy = my;
                }
                if (cells[mx][j].isRecrystalized()) {
                    isAnyRecrystalized = true;
                    recx = my;
                    recy = j;
                }
                if (cells[mx][gy].isRecrystalized()) {
                    isAnyRecrystalized = true;
                    recx = my;
                    recy = gy;
                }
                if (cells[i][my].isRecrystalized()) {
                    isAnyRecrystalized = true;
                    recx = i;
                    recy = my;
                }
                if (cells[i][gy].isRecrystalized()) {
                    isAnyRecrystalized = true;
                    recx = i;
                    recy = gy;
                }
                if (cells[gx][my].isRecrystalized()) {
                    isAnyRecrystalized = true;
                    recx = gx;
                    recy = my;
                }
                if (cells[gx][j].isRecrystalized()) {
                    isAnyRecrystalized = true;
                    recx = gx;
                    recy = j;
                }
                if (cells[gx][gy].isRecrystalized()) {
                    isAnyRecrystalized = true;
                    recx = gx;
                    recy = gy;
                }

                if (isAnyRecrystalized) {
                   //System.out.println("found recrystalized nb, H value of my cell is: "+this.cells[i][j].getH());
                   int energyBeforeRec = 0;
                   Color myColor = this.cells[i][j].getColor();
                   for(Color color : nbColors){
                       if (!color.equals(myColor))
                           energyBeforeRec++;
                   }
                   energyBeforeRec = energyBeforeRec + this.cells[i][j].getH();
                   
                   
                   int energyAfterRec = 0;
                   for(Color color : nbColors){
                       if (!color.equals(this.cells[recx][recy]))
                           energyAfterRec++;
                   }
                   
                  // System.out.println("Energy before rec: " +energyBeforeRec +" Energy after rec: "+energyAfterRec);
                   
                   if(energyAfterRec<energyBeforeRec){
                      // System.out.println("Swithing");
                       this.cells[i][j].toggleRecrystalization();
                      // System.out.println("My current color: "+this.cells[i][j].getColor());
                       this.cells[i][j].setColor(this.cells[recx][recy].getColor());
                      // System.out.println("My new color: "+this.cells[i][j].getColor());
                   }
                   
                }

            }
        } */

    }

    private void initializeNucleons() {
        SimulationControl.REC_NUCLEONS.forEach((n) -> {
            this.cells[n.getX()][n.getY()].setColor(n.getColor());
            this.cells[n.getX()][n.getY()].toggleRecrystalization();
        });
        SimulationControl.INITIALIZE_NUCLEONS = false;
        pause = false;
    }

    private List<Cord> getPossibleRecNucleonsNbrs() {
        List<Cord> cords = new ArrayList<Cord>();
        for (int i=0;i<this.cells.length;i++)
            for(int j=0;j<this.cells[1].length;j++){
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
                boolean isAnyRecrystalized = false;
                
                int recx=0;
                int recy=0;
                
                if (cells[mx][my].isRecrystalized()) {
                    isAnyRecrystalized = true;
                    recx = my;
                    recy = my;
                }
                if (cells[mx][j].isRecrystalized()) {
                    isAnyRecrystalized = true;
                    recx = my;
                    recy = j;
                }
                if (cells[mx][gy].isRecrystalized()) {
                    isAnyRecrystalized = true;
                    recx = my;
                    recy = gy;
                }
                if (cells[i][my].isRecrystalized()) {
                    isAnyRecrystalized = true;
                    recx = i;
                    recy = my;
                }
                if (cells[i][gy].isRecrystalized()) {
                    isAnyRecrystalized = true;
                    recx = i;
                    recy = gy;
                }
                if (cells[gx][my].isRecrystalized()) {
                    isAnyRecrystalized = true;
                    recx = gx;
                    recy = my;
                }
                if (cells[gx][j].isRecrystalized()) {
                    isAnyRecrystalized = true;
                    recx = gx;
                    recy = j;
                }
                if (cells[gx][gy].isRecrystalized()) {
                    isAnyRecrystalized = true;
                    recx = gx;
                    recy = gy;
                }
                
                if(isAnyRecrystalized)
                    cords.add(new Cord(i, j));
            }
    return cords;            
    }

    private void srx(int i, int j) {
        ArrayList<Color> nbColors = new ArrayList<>();
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
        
        nbColors.add(cells[mx][my].getColor());
        nbColors.add(cells[mx][j].getColor());
        nbColors.add(cells[mx][gy].getColor());
        nbColors.add(cells[i][my].getColor());
        nbColors.add(cells[i][gy].getColor());
        nbColors.add(cells[gx][my].getColor());
        nbColors.add(cells[gx][j].getColor());
        nbColors.add(cells[gx][gy].getColor());
        
        
        int recx = 0;
        int recy = 0;

        if (cells[mx][my].isRecrystalized()) {
            recx = my;
            recy = my;
        }
        if (cells[mx][j].isRecrystalized()) {
            recx = my;
            recy = j;
        }
        if (cells[mx][gy].isRecrystalized()) {
            recx = my;
            recy = gy;
        }
        if (cells[i][my].isRecrystalized()) {
            recx = i;
            recy = my;
        }
        if (cells[i][gy].isRecrystalized()) {
            recx = i;
            recy = gy;
        }
        if (cells[gx][my].isRecrystalized()) {
            recx = gx;
            recy = my;
        }
        if (cells[gx][j].isRecrystalized()) {
            recx = gx;
            recy = j;
        }
        if (cells[gx][gy].isRecrystalized()) {
            recx = gx;
            recy = gy;
        }
        
        int energyBeforeRec = 0;
        Color myColor = this.cells[i][j].getColor();
        for (Color color : nbColors) {
            if (!color.equals(myColor)) {
                energyBeforeRec++;
            }
        }
        energyBeforeRec = energyBeforeRec + this.cells[i][j].getH();

        int energyAfterRec = 0;
        for (Color color : nbColors) {
            if (!color.equals(this.cells[recx][recy])) {
                energyAfterRec++;
            }
        }

        // System.out.println("Energy before rec: " +energyBeforeRec +" Energy after rec: "+energyAfterRec);
        if (energyAfterRec < energyBeforeRec) {
            // System.out.println("Swithing");
            this.cells[i][j].toggleRecrystalization();
            // System.out.println("My current color: "+this.cells[i][j].getColor());
            this.cells[i][j].setColor(this.cells[recx][recy].getColor());
            // System.out.println("My new color: "+this.cells[i][j].getColor());
        }  
    }

}
