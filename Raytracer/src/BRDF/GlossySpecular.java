package BRDF;

import Utility.RGBColor;
import Utility.ShadeRec;
import Utility.Vector3D;

public class GlossySpecular
{
    private float ks;
    private RGBColor cs;
    private float exp;

    public GlossySpecular(float ks, RGBColor cs, float exp)
    {
        this.ks = ks;
        this.cs = cs;
        this.exp = exp;
    }

    public RGBColor f(ShadeRec sr, Vector3D wo, Vector3D wi)
    {
        RGBColor L = new RGBColor(0, 0, 0);
        double ndotwi = wi.multiply(sr.getNormal());
        Vector3D minusWi = new Vector3D(-wi.getX(), -wi.getY(), -wi.getZ());
        Vector3D r = minusWi.add(sr.getNormal().multiply(2.0).multiply(ndotwi));
        double rdotwo = wo.multiply(r);

        if (rdotwo > 0.0)
        {
            L = cs.multiply(ks).multiply(Math.pow(rdotwo, exp));
        }

        return L;
    }
}
