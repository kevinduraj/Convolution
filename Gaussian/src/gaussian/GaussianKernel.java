package gaussian;

public class GaussianKernel {

    public void process(double sigma) {
        
        int i, j, size, center;
        double sigma2, dem, kernel[][], x, y, sum;

        size = 1 + 2 * (int) Math.ceil(2.5 * sigma);
        kernel = new double[size][size];
        center = size / 2;
        sigma2 = sigma * sigma;
        dem = 2 * Math.PI * sigma2;
        
        for (i = 0, sum = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                x = i - center;
                y = j - center;
                kernel[i][j] = Math.pow(Math.E, -(x * x + y * y) / (2 * sigma2)) / dem;
                sum += kernel[i][j];
            }
        }
        
        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                System.out.print("" + kernel[i][j] / sum + " ");
            }
            System.out.println("");
        }
    }

}
