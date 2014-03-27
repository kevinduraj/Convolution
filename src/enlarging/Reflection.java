package enlarging;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class Reflection {

    private String input;
    static BufferedImage image;
    
    /*--------------------------------------------------------------------------------------------*/
    public Reflection(String input) throws IOException {
        this.input = input;
        
        FlipVerticaly();
        FlipHorizontaly();
        Rotate180();        
    }
    /*--------------------------------------------------------------------------------------------*/
    public Reflection() throws IOException  {
        this.input = "src/image/sample.png";
        int[][] gray = createImage(10, 10);
        ImageWrite(gray, input);
        FlipVerticaly();
        FlipHorizontaly();
        Rotate180();    
    }    
    /*--------------------------------------------------------------------------------------------*/
    public int[][] createImage(int width, int height) {

        int gray[][] = new int[width][height];
        int counter = 0;

        for (int i = 0; i < gray.length; ++i) {
            for (int j = 0; j < gray[i].length; ++j) {

                gray[i][j] = counter++;
            }
        }
        return gray;
    }
    /*--------------------------------------------------------------------------------------------*/

    public int[][] reflection(int img[][], int size) {

        int gray[][] = new int[img.length + size * 2][img[0].length + size * 2];

        for (int i = size; i < gray.length - size; ++i) {
            for (int j = size; j < gray[i].length - size; ++j) {

                gray[i][j] = img[i - size][j - size];
            }
        }
        return gray;
    }

    /*--------------------------------------------------------------------------------------------*/
    public void displayImage(int img[][]) {

        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {

                System.out.format("%3d ", img[i][j]);

            }
            System.out.println();
        }
        System.out.println();
    }
    /*--------------------------------------------------------------------------------------------*/

    public void displayBorder(int img[][]) {

        for (int i = 0; i < img.length; i++) {

            for (int j = 0; j < img[i].length; j++) {

                // horizontal
                if (i == 0) {
                    System.out.format("%3d ", img[i][j]);
                } // vertical;
                else if ((i != 0) && (j == 0)) {
                    System.out.format("%3d ", img[i][j]);
                } else if (i == img.length - 1) {
                    System.out.format("%3d ", img[i][j]);
                } else if ((i > 0) && (j == img.length - 1)) {
                    System.out.format("%3d ", img[i][j]);
                } else {
                    System.out.format("%3s ", "x");
                }
            }

            System.out.println();

        }
        System.out.println();
    }
    /*--------------------------------------------------------------------------------------------*/

    int[][] flipLeft(int[][] source, int kernel) {

        int size = kernel / 2;
        int img[][] = new int[source.length + size][source[0].length + size];

        //for (int i = 0; i < source.length; i++) {
        for (int i = source.length - 1; i >= 0; i--) {
            //for(int j = source[0].length - 1; j>=0; j--) {
            for (int j = 0; j < source[i].length; j++) {
                System.out.format("%3d ", source[i][j]);
            }
            System.out.println();
        }

        return img;
    }
    /*--------------------------------------------------------------------------------------------*/

    int[][] flipOver(int[][] source, int kernel) {

        int size = kernel / 2;
        int img[][] = new int[source.length + size][source[0].length + size];

        for (int i = source.length - 1; i >= 0; i--) {
            for (int j = source[0].length - 1; j >= 0; j--) {
                System.out.format("%3d ", source[i][j]);
            }
            System.out.println();
        }

        return img;
    }

    /*--------------------------------------------------------------------------------------------*/
    private int[][] fillLarger(int src[][], int size) {

        int[][] img = new int[src.length + (size*2)][src[0].length + (size*2)];

        for (int i = 0; i < img.length-size; i++) {
            for (int j = 0; j < img[i].length-size; j++) {
                img[i][j] = 1;//img[i][j];
            }
        }

        return img;
    }
    /*--------------------------------------------------------------------------------------------*/

    public int[][] reflection(int[][] padded, int orig[][], int vert[][], int horiz[][], int rotate[][], int size) {

        int cols = orig.length;
        int rows = orig[0].length;

        int nrow = padded.length;
        int ncol = padded[0].length;

        //--- Top Left Corner ----//
        //for (int i = 0; i < size; i++) { // vertical
        //    for (int j=0; j<size; j++) { // horizontal
        //        //padded[i][j] = horiz[i - size][rows - c];
        //        padded[i][j] = 1;
        //    }
        //}

        //--- Left Side  ----//
        //for (int i = size, x=0; i < rows+size-1; x++, i++) {
          for (int i=rows-size, x=0; i >0; x++, i--) {
            //for (int j=0, y=0; j<size; y++, j++) { 
              for (int j=size, y=0; j>=0; y++, j--) {               
                padded[i][j] = 2;//orig[x][y];
            }
        }

        
        //--- Right Side ----//
        //for (int i = size; i < cols + size; i++) { // vertical
        //    for (int c=0, j=ncol-size-1; j < ncol; c++, j++) { // horizontal
        //        //img[i][j] = horiz[i-half][c];
        //        padded[i][j] = 2;
        //    }
        //}

        //--- Top Side ---//
        //for (int i = 0; i < size; i++) { // vertical
        //    for (int j = size; j < rows + size; j++) { // horizontal
        //        //img[i][j] = vert[(width1 - half) + i][j - half];
        //        padded[i][j] = 3;
        //    }
        //}
        /*        //--- Bottom Side ----//
        for (int i = nrow-size-1; i < nrow; i++) { // vertical
        for (int j = size; j < rows+size; j++) { // horizontal
        //img[i][half + j] = vert[i - (height2 - half-1)][j];
        padded[i][j] = 4;
        }
        }
        
        //---- Top Left Corner ----//
        for(int c=1, x=0; x<size; c++, x++) {
        //padded[x][x] = orig[size-c][size-c];
        //img[x][x] = 5;
        }
        
        //---- Bottom Right Corner ---//
        for(int c=size, x=nrow; x>=nrow-size; c--, x--) {
        //padded[x-1][x-1] = rotate[c][c];
        }
        
        //---- Top Right Corner ---//
        for(int c=size, i=0, j=ncol-1; i<=size; c--, i++, j--) {
        //img[i][j] = src[c-1][width1-c];
        //padded[i][j] = horiz[c+1][c];
        }
        
        //---- Left Bottom Corner ---//
        for(int c=size, i=nrow-1, j=0; j<size; c--, i--, j++) {
        //padded[i][j] = orig[cols-c][c-1+1];
        }*/
        
        return padded;
    }
    /*--------------------------------------------------------------------------------------------*/
    public void displayReflection(int img[][]) {

        for (int i = 0; i < img.length; i++) {

            for (int j = 0; j < img[i].length; j++) {

                //---- Top ---//
                if (i == 0) {
                    int a = i + 2;
                    if (a > 9) {
                        a = 9;
                    }
                    System.out.format("%3d ", img[a][j]);

                } //--- Left ----//
                else if ((i != 0) && (j == 0)) {
                    int a = j + 2;
                    System.out.format("%3d ", img[i][a]);

                } //--- Bottom -----//
                else if (i == img.length - 1) {
                    int a = i - 2;
                    System.out.format("%3d ", img[a][j]);

                } //---- Right ----//
                else if ((i > 0) && (j == img.length - 1)) {
                    int a = j - 2;
                    System.out.format("%3d ", img[i][a]);

                } //----- inside ----//
                else {
                    //System.out.format("%3s ", "x");
                    System.out.format("%3d ", img[i][j]);
                }
            }

            System.out.println();

        }
        System.out.println();
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
    
    public void FlipVerticaly() throws IOException {

        image = ImageIO.read(new File(input));

        // Flip the image vertically
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -image.getHeight(null));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage bi = op.filter(image, null);

        File outputfile = new File("src/image/vertical.png");
        ImageIO.write(bi, "png", outputfile);
    }

    /*--------------------------------------------------------------------------------------------*/
    public  void FlipHorizontaly() throws IOException {

        image = ImageIO.read(new File(input));

        // Flip the image horizontally
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage bi = op.filter(image, null);

        File outputfile = new File("src/image/horizontal.png");
        ImageIO.write(bi, "png", outputfile);

    }
    /*--------------------------------------------------------------------------------------------*/
    public void Rotate180() throws IOException {

        image = ImageIO.read(new File(input));

        // Flip the image vertically and horizontally; equivalent to rotating the image 180 degrees
        AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
        tx.translate(-image.getWidth(null), -image.getHeight(null));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        BufferedImage bi = op.filter(image, null);

        File outputfile = new File("src/image/rotate180.png");
        ImageIO.write(bi, "png", outputfile);

    }
    /*--------------------------------------------------------------------------------------------*/

}
