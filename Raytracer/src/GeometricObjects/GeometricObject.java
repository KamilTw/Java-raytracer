package GeometricObjects;

import Utility.DepthBuffer;
import Utility.RGBColor;
import Utility.ShadeRec;
import Utility.Ray;

public abstract class GeometricObject
{
    private RGBColor color;

    public GeometricObject(RGBColor color)
    {
        this.color = color;
    }

    public abstract boolean hit(Ray ray, DepthBuffer depthBuffer, ShadeRec shadeRec);

    public RGBColor getColor()
    {
        return color;
    }
}