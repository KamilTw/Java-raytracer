package Samplers;

import Cameras.Camera;
import Utility.Point2D;
import Utility.RGBColor;
import World.World;

public class Adaptive implements Sampler
{
    private int maxIterations;

    public Adaptive(int maxIterations)
    {
        this.maxIterations = maxIterations;
    }

    @Override
    public RGBColor generateSamples(Point2D p, int counter, World world, RGBColor pixelColor, Camera camera)
    {
        pixelColor = new RGBColor(0, 0, 0);

        float spatialContrast = 0.001f;
        Point2D A = new Point2D(p.getX(), p.getY());
        Point2D B = new Point2D(p.getX() + 1 / (float)Math.pow(2, counter - 1), p.getY());
        Point2D C = new Point2D(p.getX() + 1 / (float)Math.pow(2, counter - 1), p.getY() - 1 / (float)Math.pow(2, counter - 1));
        Point2D D = new Point2D(p.getX(), p.getY() - 1 / (float)Math.pow(2, counter - 1));

        RGBColor AColor = camera.getPixelColor(A.getX(), A.getY(), world);
        RGBColor BColor = camera.getPixelColor(B.getX(), B.getY(), world);
        RGBColor CColor = camera.getPixelColor(C.getX(), C.getY(), world);
        RGBColor DColor = camera.getPixelColor(D.getX(), D.getY(), world);

        if (AColor.subtract(CColor) + CColor.subtract(DColor) + DColor.subtract(BColor) > spatialContrast && counter <= maxIterations)
        {
            pixelColor = pixelColor.add(generateSamples(A, counter + 1, world, pixelColor, camera));
            Point2D AB = A.subtract(B);
            pixelColor = pixelColor.add(generateSamples(AB, counter + 1, world, pixelColor, camera));
            Point2D AD = A.subtract(D);
            pixelColor = pixelColor.add(generateSamples(AD, counter + 1, world, pixelColor, camera));
            Point2D AC = A.subtract(C);
            pixelColor = pixelColor.add(generateSamples(AC, counter + 1, world, pixelColor, camera));
        }
        else
        {
            Point2D middle = A.subtract(C);
            RGBColor middleColor = camera.getPixelColor(middle.getX(), middle.getY(), world);
            pixelColor = middleColor.multiply(1 / (float)Math.pow(4, counter - 1));
        }

        return pixelColor;
    }
}
