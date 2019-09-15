package Utility;

public class Point2D
{
    private float x, y;

    public Point2D(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public Point2D subtract(Point2D p)
    {
        return new Point2D((getX() + p.getX()) / 2, (getY() + p.getY()) / 2);
    }
}
