package Cameras;

import Utility.Point2D;
import Utility.Point3D;
import Utility.RGBColor;
import World.World;
import World.ViewPlane;

import java.io.IOException;

public abstract class Camera
{
    private Point3D eye;

    public Camera(Point3D eye)
    {
        this.eye = eye;
    }

    public Point3D getEye()
    {
        return eye;
    }

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
    };

    public abstract RGBColor getPixelColor(float x, float y, World world);
}
