package Samplers;

import Cameras.Camera;
import Utility.Point2D;
import Utility.RGBColor;
import World.World;

public interface Sampler
{
    RGBColor generateSamples(Point2D p, int counter, World world, RGBColor pixelColor, Camera camera);
}
