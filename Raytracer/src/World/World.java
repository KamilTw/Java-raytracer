package World;

import Cameras.Camera;
import GeometricObjects.GeometricObject;
import Lights.Ambient;
import Lights.Light;
import Tracers.MultipleObjects;
import Tracers.RayCast;
import Tracers.Tracer;
import Utility.*;

import java.awt.*;
import java.util.Vector;

public class World
{
    private ViewPlane viewPlane;
    private RGBColor bgColor;
    private Tracer tracer;
    private Ambient ambient;
    private Camera camera;
    private Vector<GeometricObject> objects;
    private Vector<Light> lights;
    private Image image;

    public World(ViewPlane viewPlane, RGBColor bgColor, Ambient ambient)
    {
        this.viewPlane = viewPlane;
        this.bgColor = bgColor;
        this.ambient = ambient;

        objects = new Vector<GeometricObject>();
        lights = new Vector<Light>();
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

    public Ambient getAmbient()
    {
        return ambient;
    }

    public Camera getCamera()
    {
        return camera;
    }

    public Vector<GeometricObject> getObjects()
    {
        return objects;
    }

    public Vector<Light> getLights()
    {
        return lights;
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
        tracer = new RayCast(this);
    }

    public ShadeRec hitObjects(Ray ray)
    {
        ShadeRec sr = new ShadeRec(this);
        Normal normal = new Normal(0, 0, 0);
        Point3D localHitPoint = new Point3D(0, 0, 0);
        DepthBuffer d = new DepthBuffer();
        double minDistance = 1000000;
        int numObjects = objects.size();

        for (int i = 0; i < numObjects; i++)
        {
            if (objects.elementAt(i).hit(ray, d, sr) && (d.getDistance() < minDistance))
            {
                sr.setHitAnObject(true);
                minDistance = d.getDistance();
                sr.setMaterial(objects.elementAt(i).getMaterial());
                sr.setHitPoint(ray.getOrigin().add(ray.getDirection().multiply(d.getDistance())));
                normal = sr.getNormal();
                localHitPoint = sr.getLocalHitPoint();

                sr.setDepthBufferDistance(minDistance);
            }
        }

        if (sr.getHitAnObject())
        {
            sr.setNormal(normal);
            sr.setLocalHitPoint(localHitPoint);
        }

        return sr;
    }
}
