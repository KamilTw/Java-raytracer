package BRDF;

import Utility.RGBColor;
import Utility.ShadeRec;
import Utility.Vector3D;

public class PerfectSpecular
{
    private float kr;
    private RGBColor cr;

    public PerfectSpecular(float kr, RGBColor cr)
    {
        this.kr = kr;
        this.cr = cr;
    }

    public RGBColor sampleF(ShadeRec sr, Vector3D wo, Vector3D wi)
    {
        double ndotwo = wo.multiply(sr.getNormal());
        Vector3D minusWo = wo.reverse();
        wi.setVector(minusWo.add(sr.getNormal().multiply(2).multiply(ndotwo)));

        return cr.multiply(kr);
    }
}