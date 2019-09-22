package Cameras;

import Utility.*;
import World.World;

public class Perspective extends Camera
{
    public Perspective(Point3D eye)
    {
        super(eye);
    }

    @Override
    public RGBColor getPixelColor(float x, float y, World world)
    {
        Point3D origin = getEye();
        Vector3D direction = new Vector3D(x, y, 100 - origin.getZ());

        Ray ray = new Ray(origin, direction);
        ray.getDirection().normalize();

        return world.getTracer().traceRay(ray);
    }
}
