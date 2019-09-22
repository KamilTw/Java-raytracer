package Materials;

import Utility.RGBColor;
import Utility.ShadeRec;

public interface Material
{
    RGBColor shade(ShadeRec sr);
}