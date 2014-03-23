package gaussian2d;

public class Main {

    public static void main(String[] args) {

        Gaussian2D g2d = new Gaussian2D();
        int[][] source = g2d.ImageRead("src/image/assignment03.png");
        float[][] kernel = g2d.kernel(1.6f, 15);
        int[][] image = g2d.convolve(source, kernel);

        g2d.ImageWrite(image, "src/image/gaussian2.png");

    }
}
