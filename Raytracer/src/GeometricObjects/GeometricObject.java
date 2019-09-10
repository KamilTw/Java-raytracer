package GeometricObjects;

import Utility.ShadeRec;
import Utility.Ray;

public interface GeometricObject
{
    boolean hit(Ray ray, double tmin, ShadeRec shadeRec);
}