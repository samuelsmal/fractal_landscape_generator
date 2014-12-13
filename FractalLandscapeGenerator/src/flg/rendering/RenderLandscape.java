package flg.rendering;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.Appearance;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;


public class RenderLandscape {
	public static Shape3D createLandscape(double[][] map) {
		return renderLandscape(getTriangles(map));
	}
	

    /**
     *
     * @param triangles
     * @return
     */
    private static Shape3D renderLandscape(List<Triangle> triangles) {
        TriangleArray landscape = new TriangleArray((triangles.size() - 1) * 3, TriangleArray.COORDINATES);
        for (int i = 0; i < triangles.size(); i += 3) {
            landscape.setCoordinate(i, triangles.get(i).getCoordinate1());
            landscape.setCoordinate(i + 1, triangles.get(i).getCoordinate2());
            landscape.setCoordinate(i + 2, triangles.get(i).getCoordinate3());
        }

        GeometryInfo geometryInfo = new GeometryInfo(landscape);
        NormalGenerator ng = new NormalGenerator();
        ng.generateNormals(geometryInfo);

        GeometryArray result = geometryInfo.getGeometryArray();

        Appearance appearance = new Appearance();
        Color3f color = new Color3f(Color.yellow);
        Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
        Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
        Texture texture = new Texture2D();
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 0.0f, 0.0f));
        Material mat = new Material(color, black, color, white, 70f);
        appearance.setTextureAttributes(texAttr);
        appearance.setMaterial(mat);
        appearance.setTexture(texture);

        return new Shape3D(result, appearance);
    }

    /**
     * Creates a list of Triangles from a square grid.
     *
     * @param map
     * @return
     */
    private static List<Triangle> getTriangles(double[][] map) {

        List<Triangle> ret = new ArrayList<>();

        if ((map.length % 2 != 0) && (map[0].length % 2 != 0)) {
            System.err.println("Grid size must be even.");
            System.exit(1);
        }

        for (int x = 0; x < map.length; x++) {
            if (x % 2 == 0) {
                for (int y = 0; y < (map[x].length - 1); y++) {
                    Point3d coordinate1 = new Point3d(x, y, map[x][y]);
                    Point3d coordinate2 = new Point3d(x, y + 1, map[x][y + 1]);
                    Point3d coordinate3 = new Point3d(x + 1, y, map[x + 1][y]);
                    ret.add(new Triangle(coordinate1, coordinate2, coordinate3));
                }
            } else {
                for (int y = 1; y < map[x].length; y++) {
                    Point3d coordinate1 = new Point3d(x, y, map[x][y]);
                    Point3d coordinate2 = new Point3d(x, y - 1, map[x][y - 1]);
                    Point3d coordinate3 = new Point3d(x - 1, y, map[x - 1][y]);
                    ret.add(new Triangle(coordinate1, coordinate2, coordinate3));
                }
            }
        }

        return ret;
    }
}
