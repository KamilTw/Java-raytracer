package BRDF;

import Utility.Normal;
import Utility.RGBColor;
import Utility.ShadeRec;
import Utility.Vector3D;

public class PerfectTransmitter
{
    float ior, kt;  // ior - index of refraction

    public PerfectTransmitter(float ior, float kt)
    {
        this.ior = ior;
        this.kt = kt;
    }

    // Checks for total internal reflection
    public boolean tir(ShadeRec sr)
    {
        Vector3D wo = sr.getRay().getDirection().reverse();
        double cosThetaI = wo.multiply(sr.getNormal());
        double eta = ior;

        // Ray collision with internal side of an object
        if (cosThetaI < 0.0)
        {
            eta = 1.0 / eta;
        }

        return (1.0 - (1.0 - cosThetaI * cosThetaI) / (eta * eta) < 0.0);
    }

    // Calculates refraction vector (wt) and color using kt
    public RGBColor sampleF(ShadeRec sr, Vector3D wo, Vector3D wt)
    {
        Normal n = sr.getNormal();
        double cosThetaI = wo.multiply(n);
        double eta = ior;

        // Ray collision with internal side of an object
        if (cosThetaI < 0.0)
        {
            cosThetaI = -cosThetaI;
            n = n.reverse();
            eta = 1.0 / eta;
        }

        double temp = 1.0 - (1.0 - cosThetaI * cosThetaI) / (eta * eta);
        double cosTheta2 = Math.sqrt(temp);
        Vector3D minusWo = wo.reverse();
        wt.setVector(minusWo.divide(eta).subtract(n.multiply(cosTheta2 - cosThetaI / eta)));

        RGBColor white = new RGBColor(1, 1, 1);
        return white.multiply(kt / (eta * eta));
    }
}