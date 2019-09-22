package World;

import Samplers.Sampler;

public class ViewPlane
{
    private int hres;
    private int vres;
    private float pixelSize;
    private Sampler sampler;

    public ViewPlane(int hres, int vres, float pixelSize, Sampler sampler)
    {
        this.hres = hres;
        this.vres = vres;
        this.pixelSize = pixelSize;
        this.sampler = sampler;
    }

    public int getHres()
    {
        return hres;
    }

    public int getVres()
    {
        return vres;
    }

    public float getPixelSize()
    {
        return pixelSize;
    }

    public Sampler getSampler()
    {
        return sampler;
    }
}
