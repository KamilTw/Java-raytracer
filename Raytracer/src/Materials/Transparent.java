package Materials;

import BRDF.PerfectSpecular;
import BRDF.PerfectTransmitter;
import Utility.RGBColor;
import Utility.Ray;
import Utility.ShadeRec;
import Utility.Vector3D;

public class Transparent extends Phong
{
    private PerfectSpecular reflectiveBRDF;
    private PerfectTransmitter transmitterBRDF;

    public Transparent()
    {
        super();
        reflectiveBRDF = new PerfectSpecular(0, new RGBColor(0, 0, 0));
        transmitterBRDF = new PerfectTransmitter(0, 0);
    }

    public void setReflective(RGBColor cr, float kr)
    {
        reflectiveBRDF = new PerfectSpecular(kr, cr);
    }

    public void setTransmitter(float ior, float kt)
    {
        transmitterBRDF = new PerfectTransmitter(ior, kt);
    }

    @Override
    public RGBColor shade(ShadeRec sr)
    {
        RGBColor L = super.shade(sr);

        Vector3D wo = sr.getRay().getDirection().reverse();
        Vector3D wi = new Vector3D(0, 0, 0);
        RGBColor fr = reflectiveBRDF.sampleF(sr, wo, wi);
        Ray reflectedRay = new Ray(sr.getHitPoint(), wi);

        // Checks for total internal reflection
        // If so, there is no refraction, only reflection ray
        if (transmitterBRDF.tir(sr))
        {
            L = L.add(sr.getWorld().getTracer().traceRay(reflectedRay, sr.getDepth() + 1));
            // No fr included because kr coefficient is equal to one when total internal reflection occurs
        }
        else
        {
            Vector3D wt = new Vector3D(0, 0, 0);
            RGBColor ft = transmitterBRDF.sampleF(sr, wo, wt);
            Ray transmittedRay = new Ray(sr.getHitPoint(), wt);

            L = L.add(fr.multiply(sr.getWorld().getTracer().traceRay(reflectedRay, sr.getDepth() + 1)));
            L = L.add(ft.multiply(sr.getWorld().getTracer().traceRay(transmittedRay, sr.getDepth() + 1)));
        }

        return L;
    }
}
