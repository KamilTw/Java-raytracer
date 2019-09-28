package Tracers;

import Utility.RGBColor;
import Utility.Ray;
import World.World;

public abstract class Tracer
{
    private World world;

    public Tracer(World world)
    {
        this.world = world;
    }

    public RGBColor traceRay(Ray ray, int depth)
    {
        return new RGBColor(0,0, 0);
    }

    public World getWorld()
    {
        return world;
    }
}