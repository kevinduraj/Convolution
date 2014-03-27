package enlarging;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

    static BufferedImage image;
    static final boolean DEBUG = true;
    /*--------------------------------------------------------------------------------------------*/

    public static void main(String[] args) throws IOException {

        int size = 4;
        //String input = "src/image/Lenna.png";
        //SampleImage("src/image/sample.png");

        image = ImageIO.read(new File("src/image/Lenna.png"));
        int w=10;
        BufferedImage newImage = new BufferedImage(image.getWidth() + 2 * w, image.getHeight(), image.getType());

        Graphics g = newImage.getGraphics();

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, image.getWidth() + 2 * w, image.getHeight());
        g.drawImage(image, w, 0, null);
        g.dispose();
        

        File outputfile = new File("src/image/saved.png");
        ImageIO.write(newImage, "png", outputfile);

        //Padding padding = new Padding();
        //int[][] out = padding.add("src/image/sample.png", size);
        //padding.display(out);
        //padding.ImageWrite(out, "src/image/padded.png");
        //Reflection(size);
        //System.out.println("New Image: " + output);
    }
    /*--------------------------------------------------------------------------------------------*/

    private static void Reflection(int size) throws IOException {

        Reflection ref = new Reflection("src/image/sample.png");

        int[][] orig = ref.ImageRead("src/image/sample.png");
        int[][] padded = ref.ImageRead("src/image/padded.png");
        int[][] vert = ref.ImageRead("src/image/vertical.png");
        int[][] horiz = ref.ImageRead("src/image/horizontal.png");
        int[][] rotate = ref.ImageRead("src/image/rotate180.png");

        padded = ref.reflection(padded, orig, vert, horiz, rotate, size);
        ref.ImageWrite(padded, "src/image/refletion.png");
        ref.displayImage(padded);

    }
    /*--------------------------------------------------------------------------------------------*/

    private static void SampleImage(String input) throws IOException {

        Reflection ref = new Reflection();
        int[][] test = ref.createImage(15, 15);
        ref.ImageWrite(test, input);
        ref.displayImage(test);

    }
    /*--------------------------------------------------------------------------------------------*/
}
