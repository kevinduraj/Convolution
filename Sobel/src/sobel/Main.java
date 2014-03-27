package sobel;

import java.io.IOException;

public class Main {

    private static final String input = "src/image/reflection.png";   
    private static String output = "src/image/program3.png"; 
    
    /*--------------------------------------------------------------------------------------------*/
    public static void main(String[] args) throws IOException {
        
        /* Sobel */
        Sobel sobel = new Sobel();
        output = sobel.process(input);
        
        /* Statistics */
        Statistics stat = new Statistics(output);
        System.out.println("\n\n" + output);
        System.out.format("Mean     : %.3f\n\n", + stat.getMean());
        
    }
    /*--------------------------------------------------------------------------------------------*/     
}
