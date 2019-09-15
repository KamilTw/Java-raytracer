package Utility;

import World.World;

public class ShadeRec
{
    private boolean hitAnObject;
    private Point3D hitPoint;
    private Point3D localHitPoint;
    private Normal normal;
    private RGBColor color;
    private DepthBuffer depthBuffer;
    private World w;

    public boolean getHitAnObject()
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

    public void setHitAnObject(boolean hitAnObject)
    {
        this.hitAnObject = hitAnObject;
    }

    public void setHitPoint(Vector3D v)
    {
        hitPoint = new Point3D(v.getX(), v.getY(), v.getZ());
    }

    public void setLocalHitPoint(Vector3D v)
    {
        localHitPoint = new Point3D(v.getX(), v.getY(), v.getZ());
    }

    public void setNormal(Vector3D v)
    {
        normal = new Normal(v.getX(), v.getY(), v.getZ());
    }

    public void setNormal(Normal n)
    {
        normal = new Normal(n.getX(), n.getY(), n.getZ());
    }

    public void setColor(RGBColor color)
    {
        this.color = color;
    }

    public void setDepthBufferDistance(double distance)
    {
        depthBuffer.setDistance(distance);
    }

    public ShadeRec(World w)
    {
        this.w = w;
        depthBuffer = new DepthBuffer();
    }
}