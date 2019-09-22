package GeometricObjects;

import Materials.Material;
import Utility.*;

public class Plane extends GeometricObject
{
    private Point3D p;
    private Normal n;
    private static final double kEpsilon = 0.001f;

    public Plane(Material material, Point3D p, Normal n)
    {
        super(material);
        this.p = p;
        this.n = n;
    }

    @Override
    public boolean hit(Ray ray, DepthBuffer depthBuffer, ShadeRec shadeRec)
    {
        double distance = p.subtract(ray.getOrigin()).multiply(n) / ray.getDirection().multiply(n);

        if (distance > kEpsilon)
        {
            depthBuffer.setDistance(distance);
            shadeRec.setNormal(n);
            shadeRec.setLocalHitPoint(ray.getOrigin().add(ray.getDirection().multiply(distance)));
            //System.out.print(shadeRec.getLocalHitPoint().getX() + " " + shadeRec.getLocalHitPoint().getY() + " " + shadeRec.getLocalHitPoint().getZ());
            return true;
        }

        //System.out.println("No hit point");
        return false;
    }

    @Override
    public boolean shadowHit(Ray ray, DepthBuffer depthBuffer)
    {
        double distance = p.subtract(ray.getOrigin()).multiply(n) / ray.getDirection().multiply(n);

        if (distance > kEpsilon)
        {
            depthBuffer.setDistance(distance);
            return true;
        }
        return false;
    }
}
