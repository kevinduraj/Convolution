package gaussian;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    /*--------------------------------------------------------------------------------------------*/
    public static void main(String[] args) throws IOException {

        System.out.println("Reading Image: src/image/assignment03.png");
        BufferedImage src = ImageIO.read(new File("src/image/assignment03.png"));
        BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        
        System.out.println("Computing Gaussian Kernel");
        GaussianBlur gb = new GaussianBlur();
        ConvolveOp cop = gb.getGaussianBlurFilter(15, 1.6f, true);
        
        System.out.println("Computing Gaussian Horizontal Blur");
        cop.filter(src, dest);
        
        // Writing to a file
        File outputfile = new File("src/image/blur1.png");
        ImageIO.write(dest, "png", outputfile);
        
        src = ImageIO.read(new File("src/image/blur1.png"));
        dest = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        cop = gb.getGaussianBlurFilter(15, 1.6f, false);

        System.out.println("Computing Gaussian Vertical Blur");
        cop.filter(src, dest);

        // Writing to a file
        outputfile = new File("src/image/blur2.png");
        ImageIO.write(dest, "png", outputfile);        
    }
    /*--------------------------------------------------------------------------------------------*/
    /*--------------------------------------------------------------------------------------------*/
    private static String Reflection(String filename, int size) throws IOException {
        
        String output = "src/image/ReflectionPadded.png";
        ImageReflection ref = new ImageReflection();
        //int[][] gray1 = ref.createImage(width, lenght);
        //ref.writeImage(gray1, filename);
        
        FlipVerticaly(filename);
        FlipHorizontaly(filename);

        int[][]src = ref.ImageRead(filename);
        int[][]vert = ref.ImageRead("src/image/vertical.png");
        int[][]horiz = ref.ImageRead("src/image/horizontal.png");
        int[][]larger = ref.reflection(src, vert, horiz, size);
        
        ref.writeImage(larger, output);
        //int[][] gray4 = ref.flipLeft(gray1, 5);     
        return output;
    }    
    /*--------------------------------------------------------------------------------------------*/
    
}
