package Utility;

public class Ray
{
    private Point3D origin;
    private Vector3D direction;

    public Ray(Point3D origin, Vector3D direction)
    {
        this.origin = origin;
        this.direction = direction;
    }

    public Point3D getOrigin()
    {
        return origin;
    }

    public Vector3D getDirection()
    {
        return direction;
    }
}