import Cameras.Camera;
import Cameras.Orthographic;
import Cameras.Perspective;
import GeometricObjects.GeometricObject;
import Samplers.Adaptive;
import Utility.*;
import GeometricObjects.Sphere;
import GeometricObjects.Plane;
import World.World;
import World.Image;
import World.ViewPlane;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException
    {
        /*Sphere sphere = new Sphere(new RGBColor(255, 255, 255), new Point3D(0, 0, 0), 10);
        Ray r1 = new Ray(new Point3D(0, 0, -30), new Vector3D(0, 0, 1));
        Adaptive sampler = new Adaptive(3);
        ViewPlane viewPlane = new ViewPlane(100, 100, 1, sampler);
        World world = new World(viewPlane, new RGBColor(0, 0, 0));
        ShadeRec sr = new ShadeRec(world);
        DepthBuffer t = new DepthBuffer();

        sphere.hit(r1, t, sr);

        Ray r2 = new Ray(new Point3D(0, 0, -20), new Vector3D(0, 1, 0));
        sphere.hit(r2, t, sr);

        Ray r3 = new Ray(new Point3D(10, 0, -20), new Vector3D(0,0, 1));
        sphere.hit(r3, t, sr);

        Plane plane = new Plane(new RGBColor(255, 255, 255), new Point3D(0, 0, 0), new Normal(0, 1, 1));
        plane.hit(r2, t, sr);


        Image img = new Image("testowy", 10, 10);
        img.setPixelColor(0, 0, new RGBColor(255, 0, 0));
        img.saveImage();*/

        ViewPlane viewPlane = new ViewPlane(200, 200, 1, new Adaptive(3));
        World world = new World(viewPlane, new RGBColor(0, 0, 0));
        Point3D cameraEye = new Point3D(0, 0, 200);
        Camera orthographic = new Orthographic(cameraEye);
        Camera perspective = new Perspective(cameraEye);

        GeometricObject sphere1 = new Sphere(new RGBColor(0, 0, 255), new Point3D(0, 0, 0), 49);
        GeometricObject sphere2 = new Sphere(new RGBColor(255, 0, 0), new Point3D(62, 0, -25), 30);
        world.addObject(sphere1);
        world.addObject(sphere2);

        world.setCamera(perspective);
        world.build("Image");
        world.getCamera().renderScene(world);
    }
}
