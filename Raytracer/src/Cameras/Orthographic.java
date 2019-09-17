package Cameras;

import Utility.*;
import World.World;

public class Orthographic extends Camera
{
    public Orthographic(Point3D eye)
    {
        super(eye);
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
