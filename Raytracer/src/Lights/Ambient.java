package Lights;

import Utility.RGBColor;
import Utility.ShadeRec;

public class Ambient
{
    private float intensity;
    private RGBColor color;

    public Ambient(RGBColor color, float intensity)
    {
        this.color = color;
        this.intensity = intensity;
    }

    public RGBColor L()
    {
        return color.multiply(intensity);
    }
}