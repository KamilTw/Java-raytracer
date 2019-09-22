import Cameras.Camera;
import Cameras.Orthographic;
import Cameras.Perspective;
import GeometricObjects.GeometricObject;
import Lights.Ambient;
import Lights.PointLight;
import Materials.Phong;
import Samplers.Adaptive;
import Utility.*;
import GeometricObjects.Sphere;
import GeometricObjects.Plane;
import World.World;
import World.ViewPlane;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException
    {
        ViewPlane viewPlane = new ViewPlane(270, 270, 1, new Adaptive(3));
        World world = new World(viewPlane, new RGBColor(0, 0, 0), new Ambient(new RGBColor(1, 1, 1), 1));
        Point3D cameraEye = new Point3D(0, 0, 200);
        Camera orthographic = new Orthographic(cameraEye);
        Camera perspective = new Perspective(cameraEye);

        Phong ruby = new Phong(new RGBColor(0.17f, 0.01f, 0.01f), 1,
                               new RGBColor(0.61f, 0.04f, 0.04f), 1,
                               new RGBColor(0.72f, 0.63f, 0.63f), 1f, 76.8f);

        Phong gold = new Phong(new RGBColor(0.25f, 0.2f, 0.07f), 1,
                               new RGBColor(0.75f, 0.6f, 0.22f), 1,
                               new RGBColor(0.62f, 0.55f, 0.36f), 1, 51.2f);

        Phong chrome = new Phong(new RGBColor(0.25f, 0.25f, 0.25f), 1,
                                 new RGBColor(0.4f, 0.4f, 0.4f), 1,
                                 new RGBColor(0.77f, 0.77f, 0.77f), 1, 76.8f);

        PointLight pointLight1 = new PointLight(new RGBColor(1, 1, 1), 4, true, new Vector3D(0, 180, 0));
        pointLight1.setAttenuation(1, 0.0001f, 0.000001f);
        PointLight pointLight2 = new PointLight(new RGBColor(1, 1, 1), 2, true, new Vector3D(-100, 150, 20));
        pointLight2.setAttenuation(1, 0.0001f, 0.000001f);

        GeometricObject midSphere = new Sphere(ruby, new Point3D(0, 0, 0), 60);
        GeometricObject leftSphere = new Sphere(gold, new Point3D(-150, 0, 0), 60);
        GeometricObject rightSphere = new Sphere(chrome, new Point3D(150, 0, 0), 60);
        GeometricObject downPlane = new Plane(chrome, new Point3D(0, -60, 0), new Normal(0, 1, 0));
        GeometricObject backPlane = new Plane(ruby, new Point3D(0, 0, -400), new Normal(0, 0, 1));

        world.addLight(pointLight1);
        world.addLight(pointLight2);
        world.addObject(midSphere);
        world.addObject(leftSphere);
        world.addObject(rightSphere);
        world.addObject(downPlane);
        world.addObject(backPlane);
        world.setCamera(perspective);
        world.build("Image");
        world.getCamera().renderScene(world);
    }
}
