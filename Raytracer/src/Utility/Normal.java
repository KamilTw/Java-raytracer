package Utility;

public class Normal
{
    private double x, y, z;

    public Normal(double x, double y, double z)
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

    public Normal multiply(double a)
    {
        return new Normal(x * a, y * a, z * a);
    }
}
