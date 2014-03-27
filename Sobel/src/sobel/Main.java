package sobel;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        /* Reflection Padding */
        String input = "src/image/assignment03.png";
        String output = Reflection(input, 15);
        System.out.println("New Image: " + output);
        
        /* Sobel */
        Sobel sobel = new Sobel();
        sobel.process(output);
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
