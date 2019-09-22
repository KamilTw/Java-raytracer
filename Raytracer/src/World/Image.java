package World;

import Utility.RGBColor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image
{
    private File file;
    private BufferedImage bufferedImage;
    private int hres;

    public Image(String name, int vres, int hres)
    {
        this.hres = hres;
        file = new File(name + ".png");
        bufferedImage = new BufferedImage(vres, hres, BufferedImage.TYPE_INT_RGB);
    }

    public void setPixelColor(int x, int y, RGBColor pixelColor)
    {
        int yUpsideDown = hres - 1 - y;
        bufferedImage.setRGB(x, yUpsideDown, pixelColor.getRGB());
    }

    public void saveImage() throws IOException
    {
        ImageIO.write(bufferedImage, "png", file);
        System.out.println("Image saved");
    }
}
