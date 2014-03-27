package gaussian2d;

import java.io.IOException;

public class Main {
    
        public static final String input = "src/image/reflection.png";
        public static final String output = "src/image/program1.png";
        
    /*--------------------------------------------------------------------------------------------*/    
    public static void main(String[] args) throws IOException {
        
        /* Gausian 2D */
        Gaussian2D g2d = new Gaussian2D();
        int[][] source = g2d.ImageRead(input);
        float[][] kernel = g2d.kernel(1.6f, 15, false);
        int[][] image = g2d.convolve(source, kernel);

        g2d.ImageWrite(image, output);
        
        /* Statistics */
        Statistics stat = new Statistics(output);
        System.out.println("\n\n" + output);
        System.out.format("Mean     : %.3f\n\n", + stat.getMean());

    }
    /*--------------------------------------------------------------------------------------------*/   
}
