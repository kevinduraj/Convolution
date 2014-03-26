package enlarging;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    static BufferedImage image;
            
    /*--------------------------------------------------------------------------------------------*/
    public static void main(String[] args) throws IOException {
        
        String input = "src/image/Lenna.png";
        String output = Reflection(input, 1);

        System.out.println("Original");
        //displayValues(filename);
                      
        System.out.println("Vertical");
        //displayValues("src/image/vertical.png");
        
        System.out.println("Horizonal");
        //displayValues("src/image/horizontal.png");
        
        System.out.println("Reflection Image");
        //displayValues("src/image/ReflectionPadded.png");
        

        
    }
    /*--------------------------------------------------------------------------------------------*/
    private static String Reflection(String input, int size) throws IOException {
        
        String output = "src/image/ReflectionPadded.png";
        ImageReflection ref = new ImageReflection();
        //int[][] gray1 = ref.createImage(width, lenght);
        //ref.writeImage(gray1, filename);
        
        ref.FlipVerticaly(input);
        ref.FlipHorizontaly(input);
        ref.Rotate180(input);

        int[][]src = ref.ImageRead(input);
        int[][]vert = ref.ImageRead("src/image/vertical.png");
        int[][]horiz = ref.ImageRead("src/image/horizontal.png");
        int[][]larger = ref.reflection(src, vert, horiz, size);
        
        ref.writeImage(larger, output);
        //int[][] gray4 = ref.flipLeft(gray1, 5);     
        return output;
    }

    /*--------------------------------------------------------------------------------------------*/
    private static void displayValues(String filename) {
        
        ImageReflection ref = new ImageReflection();
        int[][] green = ref.ImageRead(filename);
        ref.displayImage(green);
    }
    /*--------------------------------------------------------------------------------------------*/
}
