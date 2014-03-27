package enlarging;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    private static String input = "src/image/Lenna.png";
    private static String border = "src/image/Padded.png";
    private static String output = "src/image2/reflection.png";
    private static int size = 7;
            
    /*--------------------------------------------------------------------------------------------*/

    public static void main(String[] args) throws IOException {
       
        Padding padding = new Padding();
        int[][] out = padding.add(input, size);
        //ImageDisplay(out);        
        ImageWrite(out, "src/image/padded.png");        
        
        Reflection(size);
        
    }
    /*--------------------------------------------------------------------------------------------*/

    private static void Reflection(int size) throws IOException {

        Reflection ref = new Reflection(input);
        int[][] orig = ref.ImageRead(input);        
        int[][] padded = ref.ImageRead(border);        
        int[][] vert = ref.ImageRead("src/image/vertical.png");       
        int[][] horiz = ref.ImageRead("src/image/horizontal.png");
        int[][] rotate = ref.ImageRead("src/image/rotate180.png");

        padded = ref.reflection(padded, orig, vert, horiz, rotate, size);
        ImageWrite(padded, output);
        
    }
    /*--------------------------------------------------------------------------------------------*/

    private static void CreateTestImage(String input) throws IOException {

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
