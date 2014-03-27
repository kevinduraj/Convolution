package enlarging;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    static BufferedImage image;
    static final boolean DEBUG = true;
    /*--------------------------------------------------------------------------------------------*/

    public static void main(String[] args) throws IOException {

        int size = 4;
        //String input = "src/image/Lenna.png";
        CreteTestImage("src/image/sample.png");

        Padding padding = new Padding();
        int[][] out = padding.add("src/image/sample.png", size);
        //ImageDisplay(out);
        ImageWrite(out, "src/image/padded.png");
        
        Reflection(size);
        //System.out.println("New Image: " + output);
    }
    /*--------------------------------------------------------------------------------------------*/

    private static void Reflection(int size) throws IOException {

        Reflection ref = new Reflection("src/image/sample.png");

        int[][] orig = ref.ImageRead("src/image/sample.png");
        
        int[][] padded = ref.ImageRead("src/image/padded.png");
        
        int[][] vert = ref.ImageRead("src/image/vertical.png");
        System.out.println("Vertical");
        ImageDisplay(vert);
        
        int[][] horiz = ref.ImageRead("src/image/horizontal.png");
        System.out.println("Horizontal");
        ImageDisplay(horiz);
        
        int[][] rotate = ref.ImageRead("src/image/rotate180.png");
        System.out.println("Rotate");
        ImageDisplay(rotate);

        padded = ref.reflection(padded, orig, vert, horiz, rotate, size);
        ImageWrite(padded, "src/image2/refletion.png");
        System.out.println("Reflection");
        ImageDisplay(padded);

        

    }
    /*--------------------------------------------------------------------------------------------*/

    private static void CreteTestImage(String input) throws IOException {

        int width, height;
        width = height = 15;

        int gray[][] = new int[width][height];
        int counter = 0;

        for (int i = 0; i < gray.length; ++i) {
            for (int j = 0; j < gray[i].length; ++j) {

                gray[i][j] = counter++;
            }
        }

        ImageWrite(gray, input);
        ImageDisplay(gray);

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

            File outputfile = new File(filename);
            ImageIO.write(bi, "png", outputfile);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    /*--------------------------------------------------------------------------------------------*/

    public static void ImageDisplay(int img[][]) {

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
