package gaussian;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    /*--------------------------------------------------------------------------------------------*/
    public static void main(String[] args) throws IOException {
        
        /* Reflection Padding */
        String input = "src/image/assignment03.png";
        String output = Reflection(input, 15);
        System.out.println("New Image: " + output);
        
        Gaussian1D(output);                
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

    private static void Gaussian1D(String source) throws IOException {

        String gaussian1 = "src/image/gaussian_blur1.png";
        String gaussian2 = "src/image/gaussian_blur2.png";
        
        System.out.println("Reading Image: src/image/assignment03.png");
        BufferedImage src = ImageIO.read(new File(source));
        BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        
        System.out.println("Computing Gaussian Kernel");
        GaussianBlur gb = new GaussianBlur();
        ConvolveOp cop = gb.getGaussianBlurFilter(15, 1.6f, true);
        
        System.out.println("Computing Gaussian Horizontal Blur");
        cop.filter(src, dest);
        
        // Writing to a file
        File outputfile = new File(gaussian1);
        ImageIO.write(dest, "png", outputfile);
        
        src = ImageIO.read(new File(gaussian1));
        dest = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        cop = gb.getGaussianBlurFilter(15, 1.6f, false);

        System.out.println("Computing Gaussian Vertical Blur");
        cop.filter(src, dest);

        // Writing to a file
        outputfile = new File(gaussian2);
        ImageIO.write(dest, "png", outputfile);        
    }
}
