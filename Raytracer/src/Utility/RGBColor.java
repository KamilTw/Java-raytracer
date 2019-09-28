package Utility;

public class RGBColor
{
    private float r, g, b;

    public RGBColor(float r, float g, float b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getRGB()
    {
        // Normalize color values
        maxToOne();

        // Output colors need to be in 0-255 range
        int outputR = Math.round(getR()*255);
        int outputG = Math.round(getG()*255);
        int outputB = Math.round(getB()*255);

        return 65536 * outputR + 256 * outputG + outputB;
        //return outputR<<16 | outputG<<8 | outputB;
    }

    public float getR()
    {
        return r;
    }

    public float getG()
    {
        return g;
    }

    public float getB()
    {
        return b;
    }

    public float subtract(RGBColor c)
    {
        return Math.abs(getR() - c.getR()) + Math.abs(getG() - c.getG()) + Math.abs(getB() - c.getB());
    }

    public RGBColor multiply(double number)
    {
        return new RGBColor(getR() * (float)number, getG() * (float)number,getB() * (float)number);
    }

    public RGBColor multiply(RGBColor c)
    {
        return new RGBColor(getR() * c.getR(), getG() * c.getG(), getB() * c.getB());
    }

    public RGBColor add(RGBColor c)
    {
        return new RGBColor(getR() + c.getR(), getG() + c.getG(), getB() + c.getB());
    }

    public RGBColor divide(double number)
    {
        return new RGBColor(getR() / (float)number, getG() / (float)number, getB() / (float)number);
    }

    public void maxToOne()
    {
        float maxValue = Math.max(getR(), Math.max(getG(), getB()));

        if (maxValue > 1.0)
        {
            r = r / maxValue;
            g = g / maxValue;
            b = b / maxValue;
        }
    }
}