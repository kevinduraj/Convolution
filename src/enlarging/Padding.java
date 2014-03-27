package enlarging;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Padding {

    public int[][] add(String filename, int size) {

        int[][] src = ImageRead(filename);
        int row = src.length;
        int col = src[0].length;

        int nrow = src.length + (size * 2)-1;
        int ncol = src[0].length + (size * 2)-1;

        int[][] image = new int[nrow][ncol];

        for (int i = 0; i < row-1; i++) {
            for (int j = 0; j < col-1; j++) {
                image[i+size][j+size] = src[i][j];
            }
        }
        return image;
    }

    /*--------------------------------------------------------------------------------------------*/
    public int[][] ImageRead(String filename) {

        try {

            // -- read input image
            File infile = new File(filename);
            BufferedImage bi = ImageIO.read(infile);

            // -- separate image into RGB components
            int red[][] = new int[bi.getHeight()][bi.getWidth()];
            int grn[][] = new int[bi.getHeight()][bi.getWidth()];
            int blu[][] = new int[bi.getHeight()][bi.getWidth()];
            for (int i = 0; i < red.length; ++i) {
                for (int j = 0; j < red[i].length; ++j) {
                    red[i][j] = bi.getRGB(j, i) >> 16 & 0xFF;
                    grn[i][j] = bi.getRGB(j, i) >> 8 & 0xFF;
                    blu[i][j] = bi.getRGB(j, i) & 0xFF;
                }
            }

            return grn;

        } catch (IOException e) {
            System.out.println("image I/O error");
            return null;
        }
    }
    /*--------------------------------------------------------------------------------------------*/
    public void ImageWrite(int img[][], String filename) {

        try {
            BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < bi.getHeight(); ++i) {
                for (int j = 0; j < bi.getWidth(); ++j) {
                    int val = img[i][j];
                    int pixel = (val << 16) | (val << 8) | (val);
                    bi.setRGB(j, i, pixel);
                }
            }

            File outputfile = new File(filename);
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            System.out.println(e);
        }
    }    
    /*--------------------------------------------------------------------------------------------*/
    public void display(int img[][]) {

        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {

                System.out.format("%3d ", img[i][j]);

            }
            System.out.println();
        }
        System.out.println();
    }    
    /*--------------------------------------------------------------------------------------------*/
}
