package gaussian;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedImage img = ImageIO.read(new File("src/image/assignment03.png"));

        GaussianBlur.kernel(15, 1.6);
        GaussianBlur kevin = GaussianBlur.prepare(1.6, 1.6, 15, 15);
        BufferedImage bi = kevin.filter(img);

        File outputfile = new File("src/image/GaussianBlured.png");
        ImageIO.write(bi, "png", outputfile);

    }

}
