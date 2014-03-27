package gaussian;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

      private static final String input = "src/image/reflection.png";   
      private static final String output = "src/image/program2.png"; 

    /*--------------------------------------------------------------------------------------------*/
    public static void main(String[] args) throws IOException {
        
        String final_image = Gaussian1D();
        
        Statistics stat = new Statistics(final_image);
        System.out.println("\n\n" + final_image);
        System.out.format("Mean     : %.3f\n\n", + stat.getMean());        
                
    }
    /*--------------------------------------------------------------------------------------------*/

    private static String Gaussian1D() throws IOException {

        String gaussian1 = "src/image/gaussian_blur1.png";
        
        //System.out.println("Reading Image: src/image/assignment03.png");
        BufferedImage src = ImageIO.read(new File(input));
        BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        
        //System.out.println("Computing Gaussian Kernel");
        GaussianBlur gb = new GaussianBlur();
        ConvolveOp cop = gb.getGaussianBlurFilter(15, 1.6f, true);
        
        //System.out.println("Computing Gaussian Horizontal Blur");
        cop.filter(src, dest);
        
        // Writing to a file
        File outputfile = new File(gaussian1);
        ImageIO.write(dest, "png", outputfile);
        
        src = ImageIO.read(new File(gaussian1));
        dest = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        cop = gb.getGaussianBlurFilter(15, 1.6f, false);

        //System.out.println("Computing Gaussian Vertical Blur");
        cop.filter(src, dest);

        // Writing to a file
        outputfile = new File(output);
        ImageIO.write(dest, "png", outputfile);    
        
        return output;
    }
    /*--------------------------------------------------------------------------------------------*/
}
