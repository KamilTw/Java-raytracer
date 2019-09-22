package Lights;

import Utility.RGBColor;
import Utility.Ray;
import Utility.ShadeRec;
import Utility.Vector3D;

public abstract class Light
{
    private float intensity;
    private RGBColor color;
    private boolean castShadow;

    private float constantAttenuation   = 0.1f;
    private float linearAttenuation     = 0.0001f;
    private float quadraticAttenuation  = 0.000001f;

    public Light(RGBColor color, float intensity, boolean castShadow)
    {
        this.color = color;
        this.intensity = intensity;
        this.castShadow = castShadow;
    }

    public void setAttenuation(float constantAtt, float linearAtt, float quadraticAtt)
    {
        this.constantAttenuation = constantAtt;
        this.linearAttenuation = linearAtt;
        this.quadraticAttenuation = quadraticAtt;
    }

    public abstract RGBColor L();

    public abstract double calculateAttenuation(ShadeRec sr);

    public abstract boolean inShadow(Ray ray, ShadeRec sr);

    public abstract Vector3D getDirection(ShadeRec sr);

    public float getIntensity()
    {
        return intensity;
    }

    public RGBColor getColor()
    {
        return color;
    }

    public boolean isCastingShadow()
    {
        return castShadow;
    }

    public float getConstantAtt()
    {
        return constantAttenuation;
    }

    public float getLinearAtt()
    {
        return linearAttenuation;
    }

    public float getQuadraticAtt()
    {
        return quadraticAttenuation;
    }
}