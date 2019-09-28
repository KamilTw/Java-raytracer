package Utility;

import Materials.Material;
import World.World;

public class ShadeRec
{
    private boolean hitAnObject = false;
    private Material material;
    private Point3D hitPoint;
    private Point3D localHitPoint;
    private Normal normal;
    private Ray ray;
    private int depth;
    private DepthBuffer depthBuffer;
    private World w;

    public boolean getHitAnObject()
    {
        return hitAnObject;
    }

    public Material getMaterial()
    {
        return material;
    }

    public Point3D getHitPoint()
    {
        return hitPoint;
    }

    public Point3D getLocalHitPoint()
    {
        return localHitPoint;
    }

    public Normal getNormal()
    {
        return normal;
    }

    public Ray getRay()
    {
        return ray;
    }

    public int getDepth()
    {
        return depth;
    }

    public World getWorld()
    {
        return w;
    }

    public void setHitAnObject(boolean hitAnObject)
    {
        this.hitAnObject = hitAnObject;
    }

    public void setMaterial(Material material)
    {
        this.material = material;
    }

    public void setHitPoint(Vector3D v)
    {
        hitPoint = new Point3D(v.getX(), v.getY(), v.getZ());
    }

    public void setLocalHitPoint(Vector3D v)
    {
        localHitPoint = new Point3D(v.getX(), v.getY(), v.getZ());
    }

    public void setLocalHitPoint(Point3D localHitPoint)
    {
        this.localHitPoint = localHitPoint;
    }

    public void setNormal(Vector3D v)
    {
        normal = new Normal(v.getX(), v.getY(), v.getZ());
    }

    public void setNormal(Normal n)
    {
        normal = new Normal(n.getX(), n.getY(), n.getZ());
    }

    public void setRay(Ray ray)
    {
        this.ray = ray;
    }

    public void setDepth(int depth)
    {
        this.depth = depth;
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