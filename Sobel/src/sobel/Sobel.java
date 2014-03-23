package sobel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sobel {

    /*--------------------------------------------------------------------------------------------*/
    public void process(String filename) {

        int[][] img = ImageRead(filename);
        int rows = img.length;
        int cols = img[0].length;

        double[][] Gx = new double[rows][cols];
        double[][] Gy = new double[rows][cols];
        double[][] G  = new double[rows][cols];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {

                    Gx[i][j] = Gy[i][j] = G[i][j] = 0; // Initialize

                } else {

                    Gx[i][j] = img[i + 1][j - 1] + 2 * img[i + 1][j] + img[i + 1][j + 1]
                        - img[i - 1][j - 1] - 2 * img[i - 1][j] - img[i - 1][j + 1];

                    Gy[i][j] = img[i - 1][j + 1] + 2 * img[i][j + 1] + img[i + 1][j + 1]
                        - img[i - 1][j - 1] - 2 * img[i][j - 1] - img[i + 1][j - 1];

                    G[i][j] = Math.abs(Gx[i][j]) + Math.abs(Gy[i][j]);

                }

            } /*--- for (int j = 0; j < ncols; j++) ---*/

        } /*--- for (int i = 0; i < nrows; i++) ---*/

        ImageWrite("src/image/Lenna_GX.png", Gx);
        ImageWrite("src/image/Lenna_GY.png", Gy);
        ImageWrite("src/image/Lenna_G.png", G);
    }
    /*--------------------------------------------------------------------------------------------*/

    private int[][] ImageRead(String filename) {

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
            System.out.println("image I/O error");
            return null;
        }
    }
    /*--------------------------------------------------------------------------------------------*/

    private void ImageWrite(String filename, double img[][]) {
        try {
            BufferedImage bi = new BufferedImage(img[0].length, img.length, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < bi.getHeight(); ++i) {
                for (int j = 0; j < bi.getWidth(); ++j) {
                    int val = (int) img[i][j];
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
}
