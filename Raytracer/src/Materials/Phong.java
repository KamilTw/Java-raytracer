package Materials;

import BRDF.GlossySpecular;
import BRDF.Lambertian;
import Utility.RGBColor;
import Utility.Ray;
import Utility.ShadeRec;
import Utility.Vector3D;

public class Phong implements Material
{
    private Lambertian ambientBRDF;
    private Lambertian diffuseBRDF;
    private GlossySpecular specularBRDF;

    public Phong(RGBColor ca, float ka, RGBColor cd, float kd, RGBColor cs, float ks, float exp)
    {
        ambientBRDF = new Lambertian(ka, ca);
        diffuseBRDF = new Lambertian(kd, cd);
        specularBRDF = new GlossySpecular(ks, cs, exp);
    }

    @Override
    public RGBColor shade(ShadeRec sr)
    {
        // Ambient
        Vector3D wo = new Vector3D(-sr.getRay().getDirection().getX(), -sr.getRay().getDirection().getY(), -sr.getRay().getDirection().getZ());
        RGBColor L = ambientBRDF.rho(sr, wo).multiply(sr.getWorld().getAmbient().L());

        // Diffuse + specular
        int numLights = sr.getWorld().getLights().size();
        for (int i = 0; i < numLights; i++)
        {
            Vector3D wi = sr.getWorld().getLights().elementAt(i).getDirection(sr);
            double ndotwi = wi.multiply(sr.getNormal());

            if (ndotwi > 0)
            {
                boolean inShadow = false;

                if (sr.getWorld().getLights().elementAt(i).isCastingShadow())
                {
                    Ray shadowRay = new Ray(sr.getHitPoint(), wi);
                    inShadow = sr.getWorld().getLights().elementAt(i).inShadow(shadowRay, sr);
                }

                if (!inShadow)
                {
                    double attenuation = sr.getWorld().getLights().elementAt(i).calculateAttenuation(sr);
                    RGBColor diffuseSpecular = diffuseBRDF.f(sr, wo, wi).add(specularBRDF.f(sr, wo, wi));
                    L = L.add(diffuseSpecular.multiply(attenuation).multiply(sr.getWorld().getLights().elementAt(i).L().multiply(ndotwi)));
                }
            }
        }
        return L;
    }
}
