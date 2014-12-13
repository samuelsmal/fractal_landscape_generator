package flg;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.SimpleUniverse;

import flg.algorithm.DiamondSquare;
import flg.rendering.RenderLandscape;


public class StartingPoint {

    public static void main(String[] args) {

        SimpleUniverse universe = new SimpleUniverse();

        BranchGroup group = new BranchGroup();
//        TransformGroup cctg = new TransformGroup();

//        Transform3D cc3d = new Transform3D();

        double[][] map = new double[4][4];
        map[0][0] = map[0][3] = map[3][0] = map[3][3] = 12.0d;
       
        DiamondSquare.applyDiamondSquare(map, 0.4d);
        
        group.addChild(RenderLandscape.createLandscape(map));


//        cc3d.setTranslation(new Vector3f (0.8f ,1.0f ,-2.0f ));
//        cctg.setTransform(cc3d);

//        group.addChild(cctg);

        // above pyramid
        Vector3f viewTranslation = new Vector3f();
        viewTranslation.z = 3;
        viewTranslation.x = 0f;
        viewTranslation.y = .3f;
        Transform3D viewTransform = new Transform3D();
        viewTransform.setTranslation(viewTranslation);
        Transform3D rotation = new Transform3D();
        rotation.rotX(-Math.PI / 12.0d);
        rotation.mul(viewTransform);
        universe.getViewingPlatform().getViewPlatformTransform().setTransform(
                rotation);
        universe.getViewingPlatform().getViewPlatformTransform().getTransform(
                viewTransform);

        // lights
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
                1000.0);
        Color3f light1Color = new Color3f(.7f, .7f, .7f);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        group.addChild(light1);
        Color3f ambientColor = new Color3f(.4f, .4f, .4f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        group.addChild(ambientLightNode);

        universe.addBranchGraph(group);

//        universe.getViewingPlatform().setNominalViewingTransform();


    }

}
