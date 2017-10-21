/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup.inclusion;

import growup.Cell;
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
    private boolean painted = false;

    public boolean isPainted() {
        return painted;
    }
    
    

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

    public InclusionSet(List<Inclusion> inclusions, InclusionTimeType timeType) {
        this.inclusions = inclusions;
        this.timeType = timeType;
    }

    public void setInclusions(Cell[][] cells) {
        int width = cells.length - 1;
        int height = cells[0].length - 1;
        if (this.timeType.equals(InclusionTimeType.PRE)) {
                inclusions.forEach((i -> {
                for (int x = 0; x < i.getSize(); x++) {
                    for (int y = 0; y < i.getSize(); y++) {
                        if (i.getxLocation() + x < width && i.getyLocation() + y < height) {
                            cells[i.getxLocation() + x][i.getyLocation() + y].setIsInclusion(true);
                        }
                    }
                }

            }));
        } else {
            List<CellsCords> tempList = new ArrayList<>();
            for(int i = 0 ; i < cells.length ; i ++){
                for(int j = 0; j< cells[0].length; j++){
                    if(cells[i][j].isBorder()){
                        tempList.add(new CellsCords(i, j));
                    }
                }
            }
            int inclSize = inclusions.get(0).getSize();
            Set<Inclusion> temporatyIncSet = new HashSet<Inclusion>();
            while(temporatyIncSet.size()<inclusions.size()){
                Random generator = new Random();
                int randomO = generator.nextInt(tempList.size())+(inclSize/2);
                Inclusion incl = new Inclusion();
                incl.setSize(inclSize);
                
                int randomX = tempList.get(randomO).getX()-(inclSize/2);
                int randomY = tempList.get(randomO).getY()-(inclSize/2);
                
                if(randomX < 0 ) randomX = 0;
                if(randomY < 0 ) randomX = 0;
                
                incl.setxLocation(randomX);
                incl.setyLocation(randomY);
                temporatyIncSet.add(incl);
            }
            temporatyIncSet.forEach((i -> {
                for (int x = 0; x < i.getSize(); x++) {
                    for (int y = 0; y < i.getSize(); y++) {
                        if (i.getxLocation() + x < width && i.getxLocation() + x > 0 && i.getyLocation() + y < height && i.getyLocation() + y > 0) {
                            cells[i.getxLocation() + x][i.getyLocation() + y].setIsInclusion(true);
                        }
                    }
                }
            }));
            
        }
        this.painted = true;
    }

}
