package GeometricObjects;

import Utility.Point3D;
import Utility.Ray;
import Utility.ShadeRec;
import Utility.Vector3D;

public class Sphere implements GeometricObject
{
    Point3D center;
    double radius;
    static final double kEpsilon = 0.001f;

    public Sphere(Point3D center, double radius)
    {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public boolean hit(Ray ray, double tmin, ShadeRec shadeRec)
    {
        double t;
        Vector3D temp = ray.getOrigin().subtract(center);
        double a = ray.getDirection().multiply(ray.getDirection());
        double b = ray.getDirection().multiply(temp.multiply(2.0));
        double c = temp.multiply(temp) - radius * radius;
        double delta = b * b - 4.0 * a * c;

        if (delta < 0.0)
        {
            System.out.println("No hit point");
            return false;
        }
        else
        {
            double e = Math.sqrt(delta);
            double denom = 2.0 * a;
            t = (-b - e) / denom;           // first solution

            if (t > kEpsilon && e != 0)
            {
                tmin = t;
                shadeRec.setNormal(temp.add(ray.getDirection().multiply(t)).divide(radius));
                shadeRec.setLocalHitPoint(ray.getOrigin().add(ray.getDirection().multiply(t)));
                System.out.println(shadeRec.getLocalHitPoint().getX() + " " + shadeRec.getLocalHitPoint().getY() + " " + shadeRec.getLocalHitPoint().getZ());
                //return true;
            }

            t = (-b + e) / denom;           // second solution

            if (t > kEpsilon)
            {
                tmin = t;
                shadeRec.setNormal(temp.add(ray.getDirection().multiply(t)).divide(radius));
                shadeRec.setLocalHitPoint(ray.getOrigin().add(ray.getDirection().multiply(t)));
                System.out.println(shadeRec.getLocalHitPoint().getX() + " " + shadeRec.getLocalHitPoint().getY() + " " + shadeRec.getLocalHitPoint().getZ());
                return true;
            }
        }

        System.out.println("No hit point");
        return false;
    }
}
