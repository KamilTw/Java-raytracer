package Cameras;

import Utility.*;
import World.World;
import World.ViewPlane;

import java.io.IOException;


public class Orthographic extends Camera
{
    public Orthographic(Point3D eye)
    {
        super(eye);
    }

    @Override
    public void renderScene(World world) throws IOException
    {
        RGBColor pixelColor = new RGBColor(0, 0, 0);
        ViewPlane viewPlane = world.getViewPlane();

        for (int h = 0; h < viewPlane.getHres(); h++)
        {
            for (int w = 0; w < viewPlane.getVres(); w++)
            {
                Point2D startingPixel = new Point2D(0, 0);
                startingPixel.setX(viewPlane.getPixelSize() * (w - 0.5f * viewPlane.getVres()));
                startingPixel.setY(viewPlane.getPixelSize() * (h - 0.5f * viewPlane.getHres()));

                if (viewPlane.getSampler() != null)
                {
                    pixelColor = viewPlane.getSampler().generateSamples(startingPixel, 1, world, pixelColor, this);
                }
                else
                {
                    pixelColor = getPixelColor(startingPixel.getX(), startingPixel.getY(), world);
                }

                world.getImage().setPixelColor(w , h, pixelColor);
            }
        }
        System.out.println("Rendering finished");
        world.getImage().saveImage();
    }

    @Override
    public RGBColor getPixelColor(float x, float y, World world)
    {
        Point3D origin = new Point3D(x + getEye().getX(), y + getEye().getY(), getEye().getZ());
        Vector3D direction = new Vector3D(0, 0, -1);

        Ray ray = new Ray(origin, direction);

        return world.getTracer().traceRay(ray);
    }
}
