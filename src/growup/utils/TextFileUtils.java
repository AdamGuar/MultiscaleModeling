/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup.utils;

import growup.simulation.Cell;
import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import javax.imageio.ImageIO;

/**
 *
 * @author Dom
 */
public class TextFileUtils {

    public static void createTXTFile(Cell[][] cells, String path) throws FileNotFoundException {
        StringBuilder builder = new StringBuilder();
        try (PrintWriter out = new PrintWriter(new File(path))) {
            for (int x = 0; x < cells.length; x++) {
                for (int y = 0; y < cells[0].length; y++) {
                    if(cells[x][y].getColor()!=null)
                        builder.append(x + ";" + y + "\t" + cells[x][y].getColor().getRGB() + "\n");
                    else
                        builder.append(x + ";" + y + "\t" + Color.WHITE.getRGB() + "\n");
                }
            }
            out.print(builder.toString());
            out.close();
        }
    }
    
    public static Color[][] loadColorsFromTXTFile(File file) throws IOException {
        List<ArrayAdapterEntity> adaptersList = new ArrayList<>();
        /*    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(!line.isEmpty())
                    adaptersList.add(new ArrayAdapterEntity(line));
            }
        }*/
        
        try (Stream<String> stream = Files.lines(file.toPath())) {
            stream.forEach(l->{
                adaptersList.add(new ArrayAdapterEntity(l));
            });
        }
        
        System.out.println("File loaded, converting string lines to colors");
        
        Color[][] colors = ArrayAdapter.initializeColorArray(adaptersList);
        
        for(int x = 0 ; x < colors.length; x=x+1){
            for(int y = 0 ; y < colors[0].length ; y=y+1){
                colors[x][y] = ArrayAdapter.getColorByXY(adaptersList, x, y);
              //  colors[x+1][y] = ArrayAdapter.getColorByXY(adaptersList, x+1, y);
               // colors[x][y+1] = ArrayAdapter.getColorByXY(adaptersList, x, y+1);
                //colors[x+1][y+1] = ArrayAdapter.getColorByXY(adaptersList, x+1, y+1);
            }
        }
        return colors;
    }
    
    
    

    public static Color[][] loadColorsFromTXTFile2(File file,int width,int height) throws IOException {
        List<ArrayAdapterEntity> adaptersList = new ArrayList<>();
               
        Color[][] colors = new Color[width][height];
        
        /*    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(!line.isEmpty())
                    adaptersList.add(new ArrayAdapterEntity(line));
            }
        }*/
        
        try (Stream<String> stream = Files.lines(file.toPath())) {
            stream.forEach(l->{
                adaptersList.add(new ArrayAdapterEntity(l));
                String[] tabs = l.split("\t");
                String[] cords = tabs[0].split(";");
                int x = Integer.parseInt(cords[0]);
                int y = Integer.parseInt(cords[1]);
                Color color = new Color(Integer.parseInt(tabs[1]));
                colors[x][y] = color;
            });
        }
        
    return colors;
    }

    private static class ArrayAdapterEntity {

        int width;
        int height;
        int color;

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

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public ArrayAdapterEntity(String txtLine) {
            String[] tabs = txtLine.split("\t");
            String[] cords = tabs[0].split(";");
            width = Integer.parseInt(cords[0]);
            height = Integer.parseInt(cords[1]);
            color = Integer.parseInt(tabs[1]);
        }

    }

    private static class ArrayAdapter {
        
        

        private static int getMaxWidth(List<ArrayAdapterEntity> list) {

            int max = 0;

            for (ArrayAdapterEntity a : list) {
                if (a.getWidth() > max) {
                    max = a.getWidth();
                }
            }

            return max;
        }

        private static int getMaxHeight(List<ArrayAdapterEntity> list) {

            int max = 0;

            for (ArrayAdapterEntity a : list) {
                if (a.getHeight() > max) {
                    max = a.getHeight();
                }
            }

            return max;
        }

        public static Color[][] initializeColorArray(List<ArrayAdapterEntity> list) {
            return new Color[ArrayAdapter.getMaxWidth(list)][ArrayAdapter.getMaxHeight(list)];
        }
        
        public static Color getColorByXY(List<ArrayAdapterEntity> list,int x,int y){
            for (ArrayAdapterEntity a : list) {
                if (a.getHeight() == y && a.getWidth() == x) {
                   return new Color(a.getColor());
                }
            }
            return null;
        }

    }

}
