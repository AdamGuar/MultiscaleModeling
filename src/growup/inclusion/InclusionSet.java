/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup.inclusion;

import growup.simulation.Cell;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Adam
 */
public class InclusionSet {

    List<Inclusion> inclusions;
    InclusionTimeType timeType;
    InclusionShape shape;
    private boolean painted = false;

    public List<Inclusion> getInclusions() {
        return inclusions;
    }

    public void setInclusions(List<Inclusion> inclusions) {
        this.inclusions = inclusions;
    }

    public InclusionTimeType getTimeType() {
        return timeType;
    }

    public void setTimeType(InclusionTimeType timeType) {
        this.timeType = timeType;
    }

    public InclusionShape getShape() {
        return shape;
    }

    public void setShape(InclusionShape shape) {
        this.shape = shape;
    }

    public boolean isPainted() {
        return painted;
    }

    public void setPainted(boolean painted) {
        this.painted = painted;
    }

    public InclusionSet(List<Inclusion> inclusions, InclusionTimeType timeType, InclusionShape shape) {
        this.inclusions = inclusions;
        this.timeType = timeType;
        this.shape = shape;
    }

    public void setInclusions(Cell[][] cells) {
        int width = cells.length - 1;
        int height = cells[0].length - 1;
        if (this.timeType.equals(InclusionTimeType.PRE)) {
            drawShapeOfInclusions(cells);
        } else {
            List<CellsCords> tempList = new ArrayList<>();
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[0].length; j++) {
                    if (cells[i][j].isBorder()) {
                        tempList.add(new CellsCords(i, j));
                    }
                }
            }
            int inclSize = inclusions.get(0).getSize();
            Set<Inclusion> temporatyIncSet = new HashSet<Inclusion>();
            while (temporatyIncSet.size() < inclusions.size()) {
                Random generator = new Random();
                int randomO = generator.nextInt(tempList.size()) + (inclSize / 2);
                Inclusion incl = new Inclusion();
                incl.setSize(inclSize);

                int randomX = tempList.get(randomO).getX() - (inclSize / 2);
                int randomY = tempList.get(randomO).getY() - (inclSize / 2);

                if (randomX < 0) {
                    randomX = 0;
                }
                if (randomY < 0) {
                    randomX = 0;
                }

                incl.setxLocation(randomX);
                incl.setyLocation(randomY);
                temporatyIncSet.add(incl);
            }
            this.inclusions.clear();
            this.inclusions.addAll(temporatyIncSet);
            drawShapeOfInclusions(cells);

        }
        this.painted = true;
    }

    private void drawShapeOfInclusions(Cell[][] cells) {
        int width = cells.length - 1;
        int height = cells[0].length - 1;

        if (this.shape.equals(InclusionShape.SQUARE)) {
            inclusions.forEach((i -> {
                for (int x = 0; x < i.getSize(); x++) {
                    for (int y = 0; y < i.getSize(); y++) {
                        if (i.getxLocation() + x < width && i.getxLocation() + x > 0 && i.getyLocation() + y < height && i.getyLocation() + y > 0) {
                            cells[i.getxLocation() + x][i.getyLocation() + y].setIsInclusion(true);
                        }
                    }
                }

            }));
        }else{
            inclusions.forEach((i -> {
                int xcenter = i.getxLocation();
                int ycenter = i.getyLocation();
                int xs = xcenter - i.getSize();
                int ys = ycenter - i.getSize();
                for(int ii = xs; ii < xs +(4*i.getSize()) ; ii++){
                    for(int jj = xs; jj < ys +(4*i.getSize()) ; jj++){
                        double d = Math.sqrt( ((xcenter-ii)*(xcenter-ii))  +  ((ycenter-jj) * (ycenter-jj))    );
                        if (d<i.getSize()){
                            if(ii>=0&&ii<width&&jj>=0&&jj<height)
                                cells[ii][jj].setIsInclusion(true);
                        }
                    }
                }
               
 
            }));
        }
    }

}
