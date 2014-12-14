package flg;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.SimpleUniverse;

import flg.algorithm.DiamondSquare;
import flg.rendering.RenderLandscape;


public class StartingPoint {

    public static void main(String[] args) {
   	
        SimpleUniverse universe = new SimpleUniverse();

        BranchGroup branchGroup = new BranchGroup();

        int dim = 100;
        float[][] map = new float[dim][dim];
        map[0][0] = map[0][dim - 1] = map[dim - 1][0] = map[dim -1 ][dim - 1] = 12.0f;
       
        DiamondSquare.applyDiamondSquare(map, 2.4f);
        
        branchGroup.addChild(RenderLandscape.renderLandscapeGrid(map));
        
      	Transform3D mapTransform = new Transform3D();
        mapTransform.setTranslation(new Vector3f (-(float)dim / 2f, -(float)dim / 2f, 20f));
        
        TransformGroup mapTransformGroup = new TransformGroup();
        mapTransformGroup.setTransform(mapTransform);
        
        branchGroup.addChild(mapTransformGroup);

//        Vector3f viewTranslation = new Vector3f();
//        viewTranslation.x = -(float)dim * 40;
//        viewTranslation.y = (float)dim * 40;
//        viewTranslation.z = - 30;
//        Transform3D viewTransform = new Transform3D();
//        viewTransform.setTranslation(viewTranslation);
//        universe.getViewingPlatform().getViewPlatformTransform().setTransform(viewTransform);
//        
//        Transform3D rotation = new Transform3D();
//        rotation.rotX(- Math.PI / 4d);
//        rotation.rotY(- Math.PI / 6d);
//        rotation.rotZ(-Math.PI / 22.0d);
//        rotation.mul(viewTransform);
//        universe.getViewingPlatform().getViewPlatformTransform().setTransform(rotation);
		
		Transform3D transform3d = new Transform3D();
		transform3d.rotX(-3d * Math.PI / 4d);
		
		Transform3D r2 = new Transform3D();
		r2.rotY(-Math.PI / 8.0d);
		transform3d.mul(r2);
		
		Transform3D r3 = new Transform3D();
		r3.rotZ(-Math.PI / 8.0d);
		transform3d.mul(r3);
//		
//		Transform3D r4 = new Transform3D();
//		r4.rotX(-3*Math.PI / 12.0d);
//		transform3d.mul(r4);
//		
        Vector3f viewTranslation = new Vector3f();
		viewTranslation.z = 150f;
		viewTranslation.x = (float)dim / 2f;
		viewTranslation.y = -(float)dim / 2.5f;
		Transform3D viewTransform = new Transform3D();
		viewTransform.setTranslation(viewTranslation);
		
		transform3d.mul(viewTransform);
		
		transform3d.setScale(5.5d);
		
		universe.getViewingPlatform().getViewPlatformTransform().setTransform(
				transform3d);
		universe.getViewingPlatform().getViewPlatformTransform().getTransform(
				viewTransform);
        
//      universe.getViewingPlatform().setNominalViewingTransform();
		
        // lights
        BoundingSphere bounds = new BoundingSphere(new Point3d(3.0, 0.0, -20.0),
                1000.0);
        Color3f light1Color = new Color3f(.12f, .1f, .7f);
        Vector3f light1Direction = new Vector3f(-4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        branchGroup.addChild(light1);
        Color3f ambientColor = new Color3f(.3f, .4f, .7f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        branchGroup.addChild(ambientLightNode);

        universe.addBranchGraph(branchGroup);
    }
}
