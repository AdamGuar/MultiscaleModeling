/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup;

import growup.simulation.Cell;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author adwoznia
 */
public class NeighberhoodChecker {
    
    private int probability=10;

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }
    
    public void checkNornamNeighbers(Cell[][] cells, int i, int j, int width, int height, List<Color> ColorList) {
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

        checkNeighbersMoore(cells, mx, my, i, j, gy, gx, ColorList);
    }

    public void checkNeighbersImproved(Cell[][] cells, int i, int j, int width, int height, List<Color> ColorList) {
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

        checkNeighbersMore2(cells, mx, my, i, j, gy, gx, ColorList);
        checkNeighbersNearestMoore(cells, mx, my, i, j, gy, gx, ColorList);
        checkNeighbersFurtherMoore(cells, mx, my, i, j, gy, gx, ColorList);
        checkNeighbersRandom(cells, mx, my, i, j, gy, gx, ColorList);
    }

    private void checkNeighbersChords(int i, int j, int width, int height) {
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

    }

    private void checkNeighbersMoore(Cell[][] cells, int mx, int my, int i, int j, int gy, int gx, List<Color> ColorList) {

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

        Color winner = Color.PINK;

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

    private void checkNeighbersMore2(Cell[][] cells, int mx, int my, int i, int j, int gy, int gx, List<Color> ColorList) {

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

        Color winner = Color.PINK;

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
            if (winnerFreq >= 5) {
                for (int jj = 0; jj < ColorList.size(); jj++) {
                    if (Collections.frequency(ColorList, jj) == winnerFreq) {
                        winner = ColorList.get(jj);
                    }
                    cells[i][j].setColor(winner);
                }

                ColorList.clear();
            }
        } else {
            //cells[i][j].setNextIteration(false);
        }

    }

    private void checkNeighbersNearestMoore(Cell[][] cells, int mx, int my, int i, int j, int gy, int gx, List<Color> ColorList) {

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

        Color winner = Color.PINK;

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
            if (winnerFreq >= 3) {
                for (int jj = 0; jj < ColorList.size(); jj++) {
                    if (Collections.frequency(ColorList, jj) == winnerFreq) {
                        winner = ColorList.get(jj);
                    }
                    cells[i][j].setColor(winner);
                }

                ColorList.clear();
            }
        } else {
            //cells[i][j].setNextIteration(false);
        }

    }

    private void checkNeighbersFurtherMoore(Cell[][] cells, int mx, int my, int i, int j, int gy, int gx, List<Color> ColorList) {

        if (cells[mx][my].isAlive()) {
            ColorList.add(cells[mx][my].getColor());
        }

        if (cells[mx][gy].isAlive()) {
            ColorList.add(cells[mx][gy].getColor());
        }

        if (cells[gx][my].isAlive()) {
            ColorList.add(cells[gx][my].getColor());
        }

        if (cells[gx][gy].isAlive()) {
            ColorList.add(cells[gx][gy].getColor());
        }

        Color winner = Color.PINK;

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
            if (winnerFreq == 3) {
                for (int jj = 0; jj < ColorList.size(); jj++) {
                    if (Collections.frequency(ColorList, jj) == winnerFreq) {
                        winner = ColorList.get(jj);
                    }
                    cells[i][j].setColor(winner);
                }

                ColorList.clear();
            }
        } else {
            //cells[i][j].setNextIteration(false);
        }

    }

    private void checkNeighbersRandom(Cell[][] cells, int mx, int my, int i, int j, int gy, int gx, List<Color> ColorList) {
        
        Random random = new Random();
        int rint = random.nextInt(10 - 1 + 1) + 1;
        
        
        
        
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

        Color winner = Color.PINK;

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
                if(randomReturnTrueWithGivenProbablity(probability))
                    cells[i][j].setColor(winner);
            }

            ColorList.clear();
        } else {
            //cells[i][j].setNextIteration(false);
        }

    }

    
    
    private boolean randomReturnTrueWithGivenProbablity(int probability){

        Set<Integer> pickedNumbers = new HashSet<Integer>();
        while(pickedNumbers.size()<probability){
            int r = (int) (Math.random() * (99 - 0)) + 0;
            pickedNumbers.add(r);
        }

        return pickedNumbers.contains(2);
    }
    
}
