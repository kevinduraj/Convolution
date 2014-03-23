package gaussian;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedImage img = ImageIO.read(new File("src/image/assignment03.png"));

        //GaussianBlur.displayKernel(15, 1.6);
        
        GaussianBlur gausian2D = new GaussianBlur(1.6, 15);
        gausian2D.displayKernel(15, 1.6);
        gausian2D.filter(img);
        BufferedImage bi = gausian2D.filter(img);

        File outputfile = new File("src/image/GaussianBlured3.png");
        ImageIO.write(bi, "png", outputfile);

    }

}
