package flg;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.SimpleUniverse;

import flg.algorithm.DiamondSquare;
import flg.rendering.HeatMap;
import flg.rendering.RenderLandscape;


public class StartingPoint {

    public static void main(String[] args) {

        int dim = 300;
        float[][] map = new float[dim][dim];
        map[0][0] = map[0][dim - 1] = map[dim - 1][0] = map[dim - 1][dim - 1] = 12.0f;

        DiamondSquare.applyDiamondSquare(map, 2.4f);

        // Heat Map
        HeatMap heatmap = new HeatMap(map);

        JFrame top = new JFrame("HeatMap");
        top.setBounds(0, 0, HeatMap.PIXEL_SIZE * map.length, HeatMap.PIXEL_SIZE * map[0].length);
        top.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        top.setResizable(true);
        top.getContentPane().add(heatmap);
        top.setVisible(true);



        // Java 3D Model
        SimpleUniverse universe = new SimpleUniverse();
        BranchGroup group = new BranchGroup();

        group.addChild(RenderLandscape.createLandscape(map));

        TransformGroup cctg = new TransformGroup();
        Transform3D cc3d = new Transform3D();
        cc3d.setTranslation(new Vector3f(0.8f, 1.0f, -2.0f));
        cctg.setTransform(cc3d);
        group.addChild(cctg);

        // above pyramid
        Vector3f viewTranslation = new Vector3f();
        viewTranslation.x = -(float) dim * 40;
        viewTranslation.y = (float) dim * 40;
        // viewTranslation.z = - 30;
        Transform3D viewTransform = new Transform3D();
        viewTransform.setTranslation(viewTranslation);
        universe.getViewingPlatform().getViewPlatformTransform().setTransform(viewTransform);

        Transform3D rotation = new Transform3D();
        rotation.rotX(Math.PI / 2.5d);
        // rotation.rotY(Math.PI / 121d);
        // rotation.rotZ(-Math.PI / 22.0d);
        // rotation.mul(viewTransform);
        universe.getViewingPlatform().getViewPlatformTransform().setTransform(rotation);


        // lights
        BoundingSphere bounds = new BoundingSphere(new Point3d(3.0, 0.0, -20.0),
                1000.0);

        Color3f light1Color = new Color3f(.12f, .1f, .7f);
        Vector3f light1Direction = new Vector3f(-4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        group.addChild(light1);

        Color3f ambientColor = new Color3f(.3f, .4f, .7f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        group.addChild(ambientLightNode);

        universe.addBranchGraph(group);

        // universe.getViewingPlatform().setNominalViewingTransform();


    }
}
