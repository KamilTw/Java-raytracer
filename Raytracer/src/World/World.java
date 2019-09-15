package World;

import Cameras.Camera;
import GeometricObjects.GeometricObject;
import Lights.Light;
import Tracers.MultipleObjects;
import Tracers.Tracer;
import Utility.DepthBuffer;
import Utility.RGBColor;
import Utility.Ray;
import Utility.ShadeRec;

import java.util.Vector;

public class World
{
    private ViewPlane viewPlane;
    private RGBColor bgColor;
    private Tracer tracer;
    private Light ambient;
    private Camera camera;
    private Vector<GeometricObject> objects;
    private Vector<Light> lights;
    private Image image;

    public World(ViewPlane viewPlane, RGBColor bgColor)
    {
        this.viewPlane = viewPlane;
        this.bgColor = bgColor;

        objects = new Vector<GeometricObject>();
    }

    public ViewPlane getViewPlane()
    {
        return viewPlane;
    }

    public RGBColor getBgColor()
    {
        return bgColor;
    }

    public Tracer getTracer()
    {
        return tracer;
    }

    public Camera getCamera()
    {
        return camera;
    }

    public Image getImage()
    {
        return image;
    }

    public void addObject(GeometricObject object)
    {
        objects.add(object);
    }

    public void addLight(Light light)
    {
        lights.add(light);
    }

    public void setCamera(Camera camera)
    {
        this.camera = camera;
    }

    public void build(String imageName)
    {
        image = new Image(imageName, viewPlane.getVres(), viewPlane.getHres());
        tracer = new MultipleObjects(this);
    }

    public ShadeRec hitObjects(Ray ray)
    {
        ShadeRec sr  = new ShadeRec(this);
        DepthBuffer d = new DepthBuffer();
        double minDistance = 1000000;
        int numObjects = objects.size();

        for (int i = 0; i < numObjects; i++)
        {
            if (objects.elementAt(i).hit(ray, d, sr) && (d.getDistance() < minDistance))
            {
                sr.setHitAnObject(true);
                minDistance = d.getDistance();
                sr.setColor(objects.elementAt(i).getColor());
                sr.setHitPoint(ray.getOrigin().add(ray.getDirection().multiply(d.getDistance())));

                sr.setDepthBufferDistance(minDistance);
            }
        }

        return sr;
    }
}
