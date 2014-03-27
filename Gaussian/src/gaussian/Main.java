package gaussian;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*--------------------------------------------------------------------------------------
 Test Gaussian Kernel in Matlab: 
 kernel = fspecial('gauss',15,1.6),
 Written by: Kevin T. Duraj
 --------------------------------------------------------------------------------------*/
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedImage img = ImageIO.read(new File("src/image/assignment03.png"));

        GaussianBlur gausian2D = new GaussianBlur(1.6, 3);
        gausian2D.displayKernel();
        //gausian2D.filter(img);
        //BufferedImage bi = gausian2D.filter(img);

        //File outputfile = new File("src/image/GaussianBlured3.png");
        //ImageIO.write(bi, "png", outputfile);
    }
}
/*----------------------------------------------------------------------------*/
