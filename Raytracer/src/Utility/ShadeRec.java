package Utility;

import World.World;

public class ShadeRec
{
        boolean hitAnObject;
        Point3D localHitPoint;
        Normal normal;
        RGBColor color;
        World w;

    boolean getHitAnObject()
    {
        return hitAnObject;
    }

    public Point3D getLocalHitPoint()
    {
        return localHitPoint;
    }

    public Normal getNormal()
    {
        return normal;
    }

    public RGBColor getColor()
    {
        return color;
    }

    public World getWorld()
    {
        return w;
    }

    public void setNormal(Vector3D v)
    {
        normal = new Normal(v.getX(), v.getY(), v.getZ());
    }

    public void setNormal(Normal n)
    {
        normal = new Normal(n.getX(), n.getY(), n.getZ());
    }

    public void setLocalHitPoint(Vector3D v)
    {
        localHitPoint = new Point3D(v.getX(), v.getY(), v.getZ());
    }

    public ShadeRec(World w)
    {
        this.w = w;
    }
}