package Tracers;

import Utility.RGBColor;
import Utility.Ray;
import Utility.ShadeRec;
import World.World;

public class RayCast extends Tracer
{
    public RayCast(World world)
    {
        super(world);
    }

    @Override
    public RGBColor traceRay(Ray ray, int depth)
    {
        ShadeRec sr = getWorld().hitObjects(ray);

        if (sr.getHitAnObject())
        {
            sr.setRay(ray);
            return sr.getMaterial().shade(sr);
        }
        else
        {
            return  getWorld().getBgColor();
        }
    }
}
