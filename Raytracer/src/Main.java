import Cameras.Camera;
import Cameras.Orthographic;
import Cameras.Perspective;
import GeometricObjects.GeometricObject;
import Lights.Ambient;
import Lights.PointLight;
import Materials.Phong;
import Materials.Reflective;
import Materials.Transparent;
import Samplers.Adaptive;
import Utility.*;
import GeometricObjects.Sphere;
import GeometricObjects.Plane;
import World.World;
import World.ViewPlane;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        //renderPhongSpheres();
        renderCornellBox();
        //renderGlassCornellBox();
    }

    static void renderPhongSpheres() throws IOException
    {
        ViewPlane viewPlane = new ViewPlane(270, 270, 1, new Adaptive(3));
        World world = new World(viewPlane, new RGBColor(0, 0, 0), new Ambient(new RGBColor(1, 1, 1), 1));
        Point3D cameraEye = new Point3D(0, 0, 200);
        Camera orthographic = new Orthographic(cameraEye);
        Camera perspective = new Perspective(cameraEye);

        //region Materials
        Phong ruby = new Phong();
        ruby.setAmbient(new RGBColor(0.17f, 0.01f, 0.01f), 1);
        ruby.setDiffuse(new RGBColor(0.61f, 0.04f, 0.04f), 1);
        ruby.setSpecular(new RGBColor(0.72f, 0.63f, 0.63f), 1f, 76.8f);

        Phong gold = new Phong();
        gold.setAmbient(new RGBColor(0.25f, 0.2f, 0.07f), 1);
        gold.setDiffuse(new RGBColor(0.75f, 0.6f, 0.22f), 1);
        gold.setSpecular(new RGBColor(0.62f, 0.55f, 0.36f), 1, 51.2f);

        Phong chrome = new Phong();
        chrome.setAmbient(new RGBColor(0.25f, 0.25f, 0.25f), 1);
        chrome.setDiffuse(new RGBColor(0.4f, 0.4f, 0.4f), 1);
        chrome.setSpecular(new RGBColor(0.77f, 0.77f, 0.77f), 1, 76.8f);
        //endregion

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
        world.build("examples/PhongSpheres");
        world.getCamera().renderScene(world);
    }

    static void renderCornellBox() throws IOException
    {
        ViewPlane viewPlane = new ViewPlane(270, 270, 1, new Adaptive(3));
        World world = new World(viewPlane, new RGBColor(0, 0, 0), new Ambient(new RGBColor(1, 1, 1), 1));
        Point3D cameraEye = new Point3D(0, 30, 350);
        Camera orthographic = new Orthographic(cameraEye);
        Camera perspective = new Perspective(cameraEye);

        //region Materials
        Phong ruby = new Phong();
        ruby.setAmbient(new RGBColor(0.0f, 0.0f, 0.0f), 1);
        ruby.setDiffuse(new RGBColor(0.61f, 0.04f, 0.04f), 2.5f);
        ruby.setSpecular(new RGBColor(0.72f, 0.63f, 0.63f), 0.4f, 20.8f);

        Phong black = new Phong();
        black.setAmbient(new RGBColor(0.05f, 0.05f, 0.05f), 1);
        black.setDiffuse(new RGBColor(0.1f, 0.1f, 0.1f), 1);
        black.setSpecular(new RGBColor(0.0f, 0.0f, 0.0f), 0.4f, 20f);

        Phong chrome = new Phong();
        chrome.setAmbient(new RGBColor(0.27f, 0.27f, 0.27f), 0.3f);
        chrome.setDiffuse(new RGBColor(0.6f, 0.6f, 0.6f), 2);
        chrome.setSpecular(new RGBColor(0.77f, 0.77f, 0.77f), 0.1f, 2);

        Phong blue = new Phong();
        blue.setAmbient(new RGBColor(0.0f, 0.00f, 0.00f), 1);
        blue.setDiffuse(new RGBColor(0.04f, 0.04f, 0.61f), 2.5f);
        blue.setSpecular(new RGBColor(0.63f, 0.63f, 0.72f), 0.4f, 20.8f);

        Reflective reflective = new Reflective();
        reflective.setReflective(new RGBColor(1, 1, 1), 1.0f);

        Transparent transparent = new Transparent();
        transparent.setReflective(new RGBColor(1, 1, 1), 0.1f);
        transparent.setTransmitter(1.9f, 0.9f);
        //endregion

        PointLight pointLight1 = new PointLight(new RGBColor(1, 1, 1), 4, true, new Vector3D(0, 60, 80));
        pointLight1.setAttenuation(1, 0.001f, 0.0001f);

        GeometricObject leftSphere = new Sphere(reflective, new Point3D(-26, -35, 65), 25);
        GeometricObject rightSphere = new Sphere(transparent, new Point3D(26, -35, 105), 25);
        GeometricObject downPlane = new Plane(chrome, new Point3D(0, -60, 0), new Normal(0, 1, 0));
        GeometricObject backPlane = new Plane(chrome, new Point3D(0, 0, 10), new Normal(0, 0, 1));
        GeometricObject leftPlane = new Plane(ruby, new Point3D(-90, 0, 0), new Normal(1, 0, 0));
        GeometricObject rightPlane = new Plane(blue, new Point3D(90, 0, 0), new Normal(-1, 0, 0));
        GeometricObject upPlane = new Plane(black, new Point3D(0, 120, 0), new Normal(0, -1, 0));
        GeometricObject closePlane = new Plane(black, new Point3D(0, 0, 360), new Normal(0, 0, -1));

        world.addLight(pointLight1);
        world.addObject(leftSphere);
        world.addObject(rightSphere);
        world.addObject(downPlane);
        world.addObject(backPlane);
        world.addObject(leftPlane);
        world.addObject(rightPlane);
        world.addObject(upPlane);
        world.addObject(closePlane);
        world.setCamera(perspective);
        world.build("examples/CornellBox");
        world.getCamera().renderScene(world);
    }

    static void renderGlassCornellBox() throws IOException
    {
        ViewPlane viewPlane = new ViewPlane(270, 270, 1, new Adaptive(3));
        World world = new World(viewPlane, new RGBColor(0, 0, 0), new Ambient(new RGBColor(1, 1, 1), 1));
        Point3D cameraEye = new Point3D(0, 30, 350);
        Camera orthographic = new Orthographic(cameraEye);
        Camera perspective = new Perspective(cameraEye);

        //region Materials
        Reflective rubyReflective = new Reflective();
        rubyReflective.setAmbient(new RGBColor(0.0f, 0.0f, 0.0f), 1);
        rubyReflective.setDiffuse(new RGBColor(0.61f, 0.04f, 0.04f), 2.5f);
        rubyReflective.setSpecular(new RGBColor(0.72f, 0.63f, 0.63f), 0.4f, 20.8f);
        rubyReflective.setReflective(new RGBColor(1, 1, 1), 0.2f);

        Reflective blackReflective = new Reflective();
        blackReflective.setAmbient(new RGBColor(0.05f, 0.05f, 0.05f), 1);
        blackReflective.setDiffuse(new RGBColor(0.1f, 0.1f, 0.1f), 1);
        blackReflective.setSpecular(new RGBColor(0.0f, 0.0f, 0.0f), 0.4f, 20f);
        blackReflective.setReflective(new RGBColor(1, 1, 1), 0.2f);

        Reflective chromeReflective = new Reflective();
        chromeReflective.setAmbient(new RGBColor(0.27f, 0.27f, 0.27f), 0.3f);
        chromeReflective.setDiffuse(new RGBColor(0.6f, 0.6f, 0.6f), 2);
        chromeReflective.setSpecular(new RGBColor(0.77f, 0.77f, 0.77f), 0.1f, 2);
        chromeReflective.setReflective(new RGBColor(1, 1, 1), 0.2f);

        Reflective blueReflective = new Reflective();
        blueReflective.setAmbient(new RGBColor(0.0f, 0.00f, 0.00f), 1);
        blueReflective.setDiffuse(new RGBColor(0.04f, 0.04f, 0.61f), 2.5f);
        blueReflective.setSpecular(new RGBColor(0.63f, 0.63f, 0.72f), 0.4f, 20.8f);
        blueReflective.setReflective(new RGBColor(1, 1, 1), 0.2f);

        Reflective reflective = new Reflective();
        reflective.setReflective(new RGBColor(1, 1, 1), 1.0f);

        Transparent transparent = new Transparent();
        transparent.setReflective(new RGBColor(1, 1, 1), 0.1f);
        transparent.setTransmitter(1.9f, 0.9f);
        //endregion

        PointLight pointLight1 = new PointLight(new RGBColor(1, 1, 1), 4, true, new Vector3D(0, 60, 80));
        pointLight1.setAttenuation(1, 0.001f, 0.0001f);

        GeometricObject leftSphere = new Sphere(reflective, new Point3D(-26, -35, 65), 25);
        GeometricObject rightSphere = new Sphere(transparent, new Point3D(26, -35, 105), 25);
        GeometricObject downPlane = new Plane(chromeReflective, new Point3D(0, -60, 0), new Normal(0, 1, 0));
        GeometricObject backPlane = new Plane(chromeReflective, new Point3D(0, 0, 10), new Normal(0, 0, 1));
        GeometricObject leftPlane = new Plane(rubyReflective, new Point3D(-90, 0, 0), new Normal(1, 0, 0));
        GeometricObject rightPlane = new Plane(blueReflective, new Point3D(90, 0, 0), new Normal(-1, 0, 0));
        GeometricObject upPlane = new Plane(blackReflective, new Point3D(0, 120, 0), new Normal(0, -1, 0));
        GeometricObject closePlane = new Plane(blackReflective, new Point3D(0, 0, 360), new Normal(0, 0, -1));

        world.addLight(pointLight1);
        world.addObject(leftSphere);
        world.addObject(rightSphere);
        world.addObject(downPlane);
        world.addObject(backPlane);
        world.addObject(leftPlane);
        world.addObject(rightPlane);
        world.addObject(upPlane);
        world.addObject(closePlane);
        world.setCamera(perspective);
        world.build("examples/GlassCornellBox");
        world.getCamera().renderScene(world);
    }
}