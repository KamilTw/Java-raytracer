package Utility;


import java.awt.*;

public class RGBColor
{
    private Color color;

    public RGBColor(int r, int g, int b)
    {
        color = new Color(r, g, b);
    }

    public int getRGB()
    {
        return color.getRGB();
        //return 65536 * r + 256 * g + b;
    }

    public int getR()
    {
        return color.getRed();
    }

    public int getG()
    {
        return color.getGreen();
    }

    public int getB()
    {
        return color.getBlue();
    }

    public float subtract(RGBColor c)
    {
        return Math.abs(getR() - c.getR()) + Math.abs(getG() - c.getG()) + Math.abs(getB() - c.getB());
    }

    public RGBColor multiply(float number)
    {
        return new RGBColor((int)(getR() * number), (int)(getG() * number), (int)(getB() * number));
    }

    public RGBColor add(RGBColor c)
    {
        return new RGBColor(getR() + c.getR(), getG() + c.getG(), getB() + c.getB());
    }
}