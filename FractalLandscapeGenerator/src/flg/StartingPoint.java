package flg;

import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.SimpleUniverse;

import flg.algorithm.DiamondSquare;
import flg.rendering.RenderLandscape;
import flg.rendering.Triangle;


public class StartingPoint {

    public static void main(String[] args) {

        SimpleUniverse universe = new SimpleUniverse();

        BranchGroup group = new BranchGroup();


        int dim = 100;
        float[][] map = new float[dim][dim];
        map[0][0] = map[0][dim - 1] = map[dim - 1][0] = map[dim -1 ][dim - 1] = 12.0f;
       
        DiamondSquare.applyDiamondSquare(map, 2.4f);
        
        group.addChild(RenderLandscape.createLandscape(map));

//		group.addChild(Pyramid.createPyramid());
        
//        List<Triangle> triangles = new ArrayList<Triangle>();
//        
//        Point3f ul = new Point3f(0.0f, 0.0f, -1.0f);
//        Point3f ur = new Point3f(0.0f, 1.0f, 0.5f);
//        Point3f ll = new Point3f(-1.0f, 0.0f, 0.0f);
//        Point3f lr = new Point3f(1.0f, 1.0f, 0.2f);
//        
//        triangles.add(new Triangle(ul, ur, ll));
//        triangles.add(new Triangle(ll, ur, lr));
//        
//        group.addChild(RenderLandscape.renderLandscape(triangles));
        
        //group.addChild(RenderLandscape.createLandscape(Pyramid.pyramidDataPoints()));
		
      	TransformGroup cctg = new TransformGroup();
      	Transform3D cc3d = new Transform3D();
        cc3d.setTranslation(new Vector3f (0.8f ,1.0f ,-2.0f ));
        cctg.setTransform(cc3d);
        group.addChild(cctg);

        // above pyramid
        Vector3f viewTranslation = new Vector3f();
        viewTranslation.z = -3f;
        viewTranslation.x = 0f;
        viewTranslation.y = .3f;
        Transform3D viewTransform = new Transform3D();
        viewTransform.setTranslation(viewTranslation);
        Transform3D rotation = new Transform3D();
        rotation.rotX(-Math.PI / 8.0d);
//        rotation.rotZ(-Math.PI / 2.0d);
        rotation.mul(viewTransform);
        universe.getViewingPlatform().getViewPlatformTransform().setTransform(
                rotation);
        universe.getViewingPlatform().getViewPlatformTransform().getTransform(
                viewTransform);

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

        universe.getViewingPlatform().setNominalViewingTransform();


    }

}
