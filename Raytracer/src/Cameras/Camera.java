package Cameras;

import Utility.Point2D;
import Utility.Point3D;
import Utility.RGBColor;
import World.World;

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

    public abstract void renderScene(World world) throws IOException;

    public abstract RGBColor getPixelColor(float x, float y, World world);
}
