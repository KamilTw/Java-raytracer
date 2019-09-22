package BRDF;

import Utility.RGBColor;
import Utility.ShadeRec;
import Utility.Vector3D;

public class Lambertian
{
    private float kd;
    private RGBColor cd;
    final double invPi = 0.3183098861837906715;

    public Lambertian(float kd, RGBColor cd)
    {
        this.kd = kd;
        this.cd = cd;
    }

    public RGBColor f(ShadeRec sr, Vector3D wo, Vector3D wi)
    {
        return cd.multiply(kd).multiply(invPi);
    }

    public RGBColor rho(ShadeRec sr, Vector3D wo)
    {
        return cd.multiply(kd);
    }
}
