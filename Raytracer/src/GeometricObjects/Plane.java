package GeometricObjects;

import Utility.Normal;
import Utility.Point3D;
import Utility.Ray;
import Utility.ShadeRec;

public class Plane implements GeometricObject
{
    Point3D p;
    Normal n;
    static final double kEpsilon = 0.001f;

    public Plane(Point3D p, Normal n)
    {
        this.p = p;
        this.n = n;
    }

    @Override
    public boolean hit(Ray ray, double tmin, ShadeRec shadeRec)
    {
        double t = p.subtract(ray.getOrigin()).multiply(n) / ray.getDirection().multiply(n);

        if (t > kEpsilon)
        {
            tmin = t;
            shadeRec.setNormal(n);
            shadeRec.setLocalHitPoint(ray.getOrigin().add(ray.getDirection().multiply(t)));
            System.out.print(shadeRec.getLocalHitPoint().getX() + " " + shadeRec.getLocalHitPoint().getY() + " " + shadeRec.getLocalHitPoint().getZ());
            return true;
        }

        System.out.println("No hit point");
        return false;
    }
}
