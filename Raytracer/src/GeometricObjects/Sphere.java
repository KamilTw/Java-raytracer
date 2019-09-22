package GeometricObjects;

import Materials.Material;
import Utility.*;

public class Sphere extends GeometricObject
{
    private Point3D center;
    private double radius;
    private static final double kEpsilon = 0.001f;

    public Sphere(Material material, Point3D center, double radius)
    {
        super(material);
        this.center = center;
        this.radius = radius;
    }

    @Override
    public boolean hit(Ray ray, DepthBuffer depthBuffer, ShadeRec shadeRec)
    {
        double distance;
        Vector3D temp = ray.getOrigin().subtract(center);
        double a = ray.getDirection().multiply(ray.getDirection());
        double b = ray.getDirection().multiply(temp.multiply(2.0));
        double c = temp.multiply(temp) - radius * radius;
        double delta = b * b - 4.0 * a * c;

        if (delta < 0.0)
        {
            //System.out.println("No hit point");
            return false;
        }
        else
        {
            double e = Math.sqrt(delta);
            double denom = 2.0 * a;
            distance = (-b - e) / denom;           // first solution

            if (distance > kEpsilon && e != 0)
            {
                depthBuffer.setDistance(distance);
                shadeRec.setNormal(temp.add(ray.getDirection().multiply(distance)).divide(radius));
                shadeRec.setLocalHitPoint(ray.getOrigin().add(ray.getDirection().multiply(distance)));
                //System.out.println(shadeRec.getLocalHitPoint().getX() + " " + shadeRec.getLocalHitPoint().getY() + " " + shadeRec.getLocalHitPoint().getZ());
                return true;
            }

            distance = (-b + e) / denom;           // second solution

            if (distance > kEpsilon)
            {
                depthBuffer.setDistance(distance);
                shadeRec.setNormal(temp.add(ray.getDirection().multiply(distance)).divide(radius));
                shadeRec.setLocalHitPoint(ray.getOrigin().add(ray.getDirection().multiply(distance)));
                //System.out.println(shadeRec.getLocalHitPoint().getX() + " " + shadeRec.getLocalHitPoint().getY() + " " + shadeRec.getLocalHitPoint().getZ());
                return true;
            }
        }

        //System.out.println("No hit point");
        return false;
    }

    @Override
    public boolean shadowHit(Ray ray, DepthBuffer depthBuffer)
    {
        double distance;
        Vector3D temp = ray.getOrigin().subtract(center);
        double a = ray.getDirection().multiply(ray.getDirection());
        double b = ray.getDirection().multiply(temp.multiply(2.0));
        double c = temp.multiply(temp) - radius * radius;
        double delta = b * b - 4.0 * a * c;

        if (delta < 0.0)
        {
            return false;
        }
        else
        {
            double e = Math.sqrt(delta);
            double denom = 2.0 * a;
            distance = (-b - e) / denom;           // first solution

            if (distance > kEpsilon && e != 0)
            {
                depthBuffer.setDistance(distance);
                return true;
            }

            distance = (-b + e) / denom;           // second solution

            if (distance > kEpsilon)
            {
                depthBuffer.setDistance(distance);
                return true;
            }
        }

        return false;
    }
}