package Tracers;

import Utility.RGBColor;
import Utility.Ray;
import Utility.ShadeRec;
import World.World;

public class MultipleObjects extends Tracer
{
    public MultipleObjects(World world)
    {
        super(world);
    }

    @Override
    public RGBColor traceRay(Ray ray)
    {
        ShadeRec sr = getWorld().hitObjects(ray);
        {
            if (sr.getHitAnObject())
            {
                return sr.getColor();
            }
            else
            {
                return getWorld().getBgColor();
            }
        }
    }
}