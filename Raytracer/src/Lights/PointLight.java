package Lights;

import Utility.*;


public class PointLight extends Light
{
    private Vector3D location;

    public PointLight(RGBColor color, float intensity, boolean castShadow, Vector3D location)
    {
        super(color, intensity, castShadow);
        this.location = location;
    }

    @Override
    public RGBColor L()
    {
        return getColor().multiply(getIntensity());
    }

    @Override
    public double calculateAttenuation(ShadeRec sr)
    {
        double d = Math.sqrt(Math.pow(location.getX() - sr.getHitPoint().getX(), 2) +
                             Math.pow(location.getY() - sr.getHitPoint().getY(), 2) +
                             Math.pow(location.getZ() - sr.getHitPoint().getZ(), 2));

        double attenuation = 1 / (getConstantAtt() + getLinearAtt() * d + getQuadraticAtt() * Math.pow(d, 2));

        return attenuation;
    }

    @Override
    public boolean inShadow(Ray ray, ShadeRec sr)
    {
        DepthBuffer d = new DepthBuffer();
        int numObjects = sr.getWorld().getObjects().size();
        double distance = Math.sqrt(Math.pow(location.getX() - ray.getOrigin().getX(), 2) +
                                    Math.pow(location.getY() - ray.getOrigin().getY(), 2) +
                                    Math.pow(location.getZ() - ray.getOrigin().getZ(), 2));

        for (int i = 0; i < numObjects; i++)
        {
            if (sr.getWorld().getObjects().elementAt(i).shadowHit(ray, d) && d.getDistance() < distance)
            {
                return  true;
            }
        }
        return false;
    }

    @Override
    public Vector3D getDirection(ShadeRec sr)
    {
        Vector3D dir = new Vector3D(location.getX() - sr.getHitPoint().getX(),
                                    location.getY() - sr.getHitPoint().getY(),
                                    location.getZ() - sr.getHitPoint().getZ());
        dir.normalize();

        return dir;
    }
}
