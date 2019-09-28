package Tracers;

import Utility.RGBColor;
import Utility.Ray;
import Utility.ShadeRec;
import World.World;

public class Whitted extends Tracer
{
    public Whitted(World world)
    {
        super(world);
    }

    @Override
    public RGBColor traceRay(Ray ray, int depth)
    {
        if (depth > getWorld().getViewPlane().getMaxBounces())
        {
            return new RGBColor(0, 0, 0);
        }
        else
        {
            ShadeRec sr = getWorld().hitObjects(ray);

            if (sr.getHitAnObject())
            {
                sr.setDepth(depth);
                sr.setRay(ray);
                return sr.getMaterial().shade(sr);
            }
            else
            {
                return getWorld().getBgColor();
            }
        }
    }
}