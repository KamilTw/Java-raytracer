package Utility;

public class Vector3D
{
    private double x, y, z;

    public Vector3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getZ()
    {
        return z;
    }

    // returns vector dot product
    public double multiply(Vector3D v)
    {
        return getX() * v.getX() + getY() * v.getY() + getZ() * v.getZ();
    }

    public double multiply(Normal n)
    {
        return getX() * n.getX() + getY() * n.getY() + getZ() * n.getZ();
    }

    public Vector3D multiply(double a)
    {
        return new Vector3D(a * getX(),a * getY(), a * getZ());
    }

    public Vector3D divide(double a)
    {
        return new Vector3D(getX() / a, getY() / a, getZ() / a);
    }

    public Vector3D add(Vector3D v)
    {
        return new Vector3D(getX() + v.getX(), getY() + v.getY(),getZ() + v.getZ());
    }
}