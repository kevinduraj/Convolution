package gaussian2d;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.exp;
import javax.imageio.ImageIO;

public class Gaussian2D {

    public static void main(String[] args) {
        int[][] source = ImageRead("src/image/assignment03.png");
        int[][] dest   = gaussianBlur(source, 1.6f, 15);
        ImageWrite(dest, "src/image/blured112.png");
    }
    /* --------------------------------------------------------------------------------------------
     Blurs image with a Gaussian kernel
     ---------------------------------------------------------------------------------------------*/

    public static int[][] gaussianBlur(int[][] img, float sigma, int size) {

        // Generate a sizexsize kernel 
        float[][] kernel = new float[size][size];
        int uc, vc;
        float g, sum;
        sum = 0;
        for (int u = 0; u < kernel.length; u++) {
            for (int v = 0; v < kernel[0].length; v++) {
                // Center the Gaussian sample so max is at u,v = 10,10
                uc = u - (kernel.length - 1) / 2;
                vc = v - (kernel[0].length - 1) / 2;
                // Calculate and save
                g = (float) exp(-(uc * uc + vc * vc) / (2 * sigma * sigma));
                sum += g;
                kernel[u][v] = g;
            }
        }
        // Normalize it
        for (int u = 0; u < kernel.length; u++) {
            for (int v = 0; v < kernel[0].length; v++) {
                kernel[u][v] /= sum;
            }
        }

        // Convolve and return
        return convolve(img, kernel);
    }

    /* --------------------------------------------------------------------------------------------
     * Convolves a float[][] representation of an image with a kernel of weights
     * -------------------------------------------------------------------------------------------*/
    public static int[][] convolve(int[][] img, float[][] kernel) {
        int xn, yn;
        float average;

        int w = img.length;
        int h = img[0].length;
        int[][] output = new int[w][h];

        //--- IMAGE: Iterate through image pixels ---//
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {

                //--- KERNEL: Iterate through kernel ---//
                average = 0;
                for (int u = 0; u < kernel.length; u++) {
                    for (int v = 0; v < kernel[0].length; v++) {

                        //--- Get associated neighbor pixel coordinates ---//
                        xn = x + u - kernel.length / 2;
                        yn = y + v - kernel[0].length / 2;

                        //--  Make sure we don't go off of an edge of the image ---//
                        xn = constrain(xn, 0, w - 1);
                        yn = constrain(yn, 0, h - 1);

                        //--- Add weighted neighbor to average ---//
                        average += img[xn][yn] * kernel[u][v];
                    }
                } /*--- KERNEL ---*/
                
                //--- Set output pixel to weighted average value ---//
                output[x][y] = (int) average;
            }
        } /*--- IMAGE ---*/
        
        return output;
    }
    /*--------------------------------------------------------------------------------------------*/

    public static int constrain(int x, int a, int b) {
        if (x < a) {
            return a;
        } else if (b < x) {
            return b;
        } else {
            return x;
        }
    }
    /*--------------------------------------------------------------------------------------------*/

    public static int[][] ImageRead(String filename) {

        try {

            File infile = new File(filename);
            BufferedImage bi = ImageIO.read(infile);

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
            System.out.println(e);
            return null;
        }
    }
    /*--------------------------------------------------------------------------------------------*/

    public static void ImageWrite(int img[][], String filename) {

        try {
            BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < bi.getHeight(); ++i) {
                for (int j = 0; j < bi.getWidth(); ++j) {
                    int val = img[i][j];
                    int pixel = (val << 16) | (val << 8) | (val);
                    bi.setRGB(j, i, pixel);
                }
            }

            //--- Write output image
            File outputfile = new File(filename);
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    /*--------------------------------------------------------------------------------------------*/

}
