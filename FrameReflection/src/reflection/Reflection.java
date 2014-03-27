package reflection;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Reflection extends JComponent {

    private BufferedImage image;

    public Reflection() {
        try {
            image = ImageIO.read(new File("src/image/Lenna.png"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void paintComponent(Graphics g) {

        Graphics2D g2d      = (Graphics2D) g;
        int width           = getWidth();
        int height          = getHeight();
        int imageWidth      = image.getWidth();
        int imageHeight     = image.getHeight();
        int gap             = 1;       // Gap between the images
        float opacity       = 1.0f;    // 0.4f;
        float fadeHeight    = 0.1f;    // 0.3f;

        g2d.setPaint(new GradientPaint(0, 0, Color.black, 0, height, Color.darkGray));
        g2d.fillRect(0, 0, width, height);
        g2d.translate((width - imageWidth) / 2, height / 2 - imageHeight);
        g2d.drawRenderedImage(image, null);
        
        //--- Water Reflection ---//
        g2d.translate(0, 2 * imageHeight + gap);
        g2d.scale(1, -1);
        
        //--- Lower Left Corner Reflection ---//
        //g2d.translate(0, imageHeight);        
        //g2d.scale(-1, 1);
                
        BufferedImage reflection = new BufferedImage(imageWidth, imageHeight
                                                   , BufferedImage.TYPE_INT_ARGB);
        Graphics2D rg = reflection.createGraphics();
        rg.drawRenderedImage(image, null);
        rg.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_OVER));
        
        rg.setPaint(new GradientPaint(
                    0, 
                    imageHeight * fadeHeight,
                    new Color(0.0f, 0.0f, 0.0f, 0.0f), 0,
                    imageHeight,
                    new Color(0.0f, 0.0f, 0.0f, opacity)));

        rg.fillRect(0, 0, imageWidth, imageHeight);
        rg.dispose();
        g2d.drawRenderedImage(reflection, null);
    }

    public Dimension getPreferredSize() {
        return new Dimension(512*2, 512*2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reflection");
        Reflection r = new Reflection();
        frame.add(new Reflection());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
