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

    public abstract RGBColor traceRay(Ray ray);

    public World getWorld()
    {
        return world;
    }
}