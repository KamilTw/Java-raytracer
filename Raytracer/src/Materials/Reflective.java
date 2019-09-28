package Materials;

import BRDF.PerfectSpecular;
import Utility.RGBColor;
import Utility.Ray;
import Utility.ShadeRec;
import Utility.Vector3D;

public class Reflective extends Phong
{
    private PerfectSpecular reflectiveBRDF;

    public Reflective()
    {
        super();
        reflectiveBRDF = new PerfectSpecular(0, new RGBColor(0, 0, 0));
    }

    public void setReflective(RGBColor cr, float kr)
    {
        reflectiveBRDF = new PerfectSpecular(kr, cr);
    }

    @Override
    public RGBColor shade(ShadeRec sr)
    {
        RGBColor L = super.shade(sr);

        Vector3D wo = sr.getRay().getDirection().reverse();
        Vector3D wi = new Vector3D(0, 0, 0);
        RGBColor fr = reflectiveBRDF.sampleF(sr, wo, wi);
        Ray reflectedRay = new Ray(sr.getHitPoint(), wi);

        L = L.add(fr.multiply(sr.getWorld().getTracer().traceRay(reflectedRay, sr.getDepth() + 1)));

        return L;
    }
}
