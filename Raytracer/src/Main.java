import Utility.*;
import GeometricObjects.Sphere;
import  GeometricObjects.Plane;
import World.World;

public class Main {
    public static void main(String[] args)
    {
        Sphere sphere = new Sphere(new Point3D(0, 0, 0), 10);
        Ray r1 = new Ray(new Point3D(0, 0, -30), new Vector3D(0, 0, 1));
        World world = new World();
        ShadeRec sr = new ShadeRec(world);
        double t = 0;

        sphere.hit(r1, t, sr);

        Ray r2 = new Ray(new Point3D(0, 0, -20), new Vector3D(0, 1, 0));
        sphere.hit(r2, t, sr);

        Ray r3 = new Ray(new Point3D(10, 0, -20), new Vector3D(0,0, 1));
        sphere.hit(r3, t, sr);

        Plane plane = new Plane(new Point3D(0, 0, 0), new Normal(0, 1, 1));
        plane.hit(r2, t, sr);
    }
}
