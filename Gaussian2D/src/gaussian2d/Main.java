package gaussian2d;

import java.io.IOException;

public class Main {
    
    /*--------------------------------------------------------------------------------------------*/    
    public static void main(String[] args) throws IOException {

        /* Reflection Padding */
        String input = "src/image/assignment03.png";
        String final_image = "src/image/program1.png";
        String output = Reflection(input, 15);
        //System.out.println("New Image: " + output);
        
        /* Gausian 2D */
        Gaussian2D g2d = new Gaussian2D();
        int[][] source = g2d.ImageRead(output);
        float[][] kernel = g2d.kernel(1.6f, 15, false);
        int[][] image = g2d.convolve(source, kernel);

        g2d.ImageWrite(image, final_image);
        
        /* Statistics */
        Statistics stat = new Statistics(final_image);
        System.out.println("\n\n" + final_image);
        System.out.format("Mean     : %.3f\n\n", + stat.getMean());

    }
    /*--------------------------------------------------------------------------------------------*/
    private static String Reflection(String input, int size) throws IOException {
        
        String output = "src/image/ReflectionPadded.png";
        ImageReflection ref = new ImageReflection(input);
       
        int[][]src = ref.ImageRead(input);
        int[][]vert = ref.ImageRead("src/image/vertical.png");
        int[][]horiz = ref.ImageRead("src/image/horizontal.png");
        int[][]larger = ref.reflection(src, vert, horiz, size);
        
        ref.writeImage(larger, output);        
    
        return output;
    }
    /*--------------------------------------------------------------------------------------------*/    
}
