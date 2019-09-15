package Utility;

public class Point3D
{
    private double x, y, z;

    public Point3D(double x, double y, double z)
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

    public Vector3D subtract(Point3D p)
    {
        return new Vector3D(getX() - p.getX(), getY() - p.getY() ,getZ() - p.getZ());
    }

    public Vector3D add(Vector3D v)
    {
        return new Vector3D(getX() + v.getX(),getY() + v.getY(), getZ() + v.getZ());
    }
}