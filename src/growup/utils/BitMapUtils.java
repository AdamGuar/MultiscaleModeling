/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup.utils;

import growup.simulation.Cell;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Dom
 */
public class BitMapUtils {


    public static void writeImage(Cell[][] cellArr, String filePath) {
        String path = filePath;
        BufferedImage image = new BufferedImage(cellArr.length, cellArr[0].length, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < cellArr.length; x++) {
            for (int y = 0; y < cellArr[0].length; y++) {
                if(cellArr[x][y].getColor()!=null)
                    image.setRGB(x, y, cellArr[x][y].getColor().getRGB());
                else
                    image.setRGB(x, y, Color.WHITE.getRGB());
            }
        }

        File ImageFile = new File(path);
        try {
            ImageIO.write(image, "bmp", ImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Color[][] readImage(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);

        Color[][] array2D = new Color[image.getWidth()][image.getHeight()];

        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
            for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
                int color = image.getRGB(xPixel, yPixel);
                array2D[xPixel][yPixel] = new Color(color);
            }
        }
        
        return array2D;
    }
}


