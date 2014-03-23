package gaussian;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class GaussianBlur {
    
    private final BufferedImageOp opHor;
    private final BufferedImageOp opVer;
    
    /*--------------------------------------------------------------------------------------------*/
    private GaussianBlur(BufferedImageOp opHor, BufferedImageOp opVer) {
        this.opHor = opHor;
        this.opVer = opVer;
    }
    /*--------------------------------------------------------------------------------------------*/
    public static GaussianBlur prepare(double sigmaHor, double sigmaVer, int width, int height) {
        
        BufferedImageOp opHor = null;
        if (sigmaVer != 0) {
            float[] kernelValuesHor = gausianKernelValues(sigmaHor, width);
            Kernel kernelHor = new Kernel(kernelValuesHor.length, 1, kernelValuesHor);
            opHor = new ConvolveOp(kernelHor, ConvolveOp.EDGE_NO_OP, null);
        }

        BufferedImageOp opVer = null;
        if (sigmaVer != 0) {
            float[] kernelValuesVer = gausianKernelValues(sigmaVer, height);
            Kernel kernelVer = new Kernel(1, kernelValuesVer.length, kernelValuesVer);
            opVer = new ConvolveOp(kernelVer, ConvolveOp.EDGE_NO_OP, null);
        }
        return new GaussianBlur(opHor, opVer);
    }
        
    /*--------------------------------------------------------------------------------------------*/
    public static double[][] kernel(int size, double sigma) {

        int i, j, center;
        double sigma2, dem, x, y, sum;

        double[][] kernel1 = new double[size][size];
        double[][] kernel2 = new double[size][size];
        center = size / 2;
        sigma2 = sigma * sigma;
        dem = 2 * Math.PI * sigma2;

        for (i = 0, sum = 0; i < size; i++) {
            for (j = 0; j < size; j++) {
                x = i - center;
                y = j - center;
                kernel1[i][j] = Math.pow(Math.E, -(x * x + y * y) / (2 * sigma2)) / dem;
                sum += kernel1[i][j];
            }
        }

        for (i = 0; i < size; i++) {
            for (j = 0; j < size; j++) {

                kernel2[i][j] = kernel1[i][j] / sum;
                System.out.format("%.9f ", kernel2[i][j]);

            }
            System.out.println("");
        }
        return kernel2;
    }

    /*--------------------------------------------------------------------------------------------*/
    private static float[] gausianKernelValues(double sigma, int n) {
        
        double[] dvs = new double[n * 2 + 1];
        double sum = 0.0;
        for (int i = 0; i < dvs.length; i++) {
            double x = i - n;
            double v = 1.0 / Math.sqrt(2 * Math.PI * sigma * sigma) * Math.exp(-x * x / (2 * sigma * sigma));
            dvs[i] = v;
            sum += v;
        }

        float[] res = new float[n * 2 + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (float) (dvs[i] / sum);
        }
        return res;
    }
    /*--------------------------------------------------------------------------------------------*/
    public BufferedImage filter(BufferedImage image) {
        if (opHor != null) {
            image = opHor.filter(image, null);
        }
        if (opVer != null) {
            image = opVer.filter(image, null);
        }
        return image;
    }
    /*--------------------------------------------------------------------------------------------*/
}
