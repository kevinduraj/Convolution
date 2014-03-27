package enlarging;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {

    static BufferedImage image;
            
    /*--------------------------------------------------------------------------------------------*/
    public static void main(String[] args) throws IOException {
        
        String input = "src/image/Lenna.png";
        String output = Reflection(input, 1);
        System.out.println("New Image: " + output);
    }
    /*--------------------------------------------------------------------------------------------*/
    private static String Reflection(String input, int size) throws IOException {
        
        String output = "src/image/ReflectionPadded.png";
        ImageReflection ref = new ImageReflection(input);
        //int[][] test = ref.createImage(width, lenght);
        //ref.writeImage(test, filename);
        
        int[][]src = ref.ImageRead(input);
        int[][]vert = ref.ImageRead("src/image/vertical.png");
        int[][]horiz = ref.ImageRead("src/image/horizontal.png");
        int[][]larger = ref.reflection(src, vert, horiz, size);
        
        ref.writeImage(larger, output);        
        //ref.displayImage(larger);
    
        return output;
    }
    /*--------------------------------------------------------------------------------------------*/
}
