package GeometricObjects;

import Materials.Material;
import Utility.DepthBuffer;
import Utility.RGBColor;
import Utility.ShadeRec;
import Utility.Ray;

public abstract class GeometricObject
{
    private Material material;

    public GeometricObject(Material material)
    {
        this.material = material;
    }

    public abstract boolean hit(Ray ray, DepthBuffer depthBuffer, ShadeRec shadeRec);

    public abstract boolean shadowHit(Ray ray, DepthBuffer depthBuffer);

    public Material getMaterial()
    {
        return material;
    }
}