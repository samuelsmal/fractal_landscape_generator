package flg.rendering;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.LineArray;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.TriangleArray;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;


public class RenderLandscape {
	public static Shape3D createLandscape(float[][] map) {
		return renderLandscapeGrid(getTriangles(map));
	}

	/**
	 * GRID
	 * 
	 * @param triangles
	 * @return
	 */
	public static Shape3D renderLandscapeGrid(List<Triangle> triangles) {
		LineArray landscape = new LineArray(triangles.size() * 4, LineArray.COORDINATES);
        int counter = 0;
        for (int i = 0; i < triangles.size(); ++i) {
            landscape.setCoordinate(counter++, triangles.get(i).getCoordinate1());
            landscape.setCoordinate(counter++, triangles.get(i).getCoordinate2());
            landscape.setCoordinate(counter++, triangles.get(i).getCoordinate3());
            landscape.setCoordinate(counter++, triangles.get(i).getCoordinate1());
        }
        Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
        Appearance app = new Appearance();
        ColoringAttributes ca = new ColoringAttributes(white,
            ColoringAttributes.SHADE_FLAT);
        app.setColoringAttributes(ca);

        return new Shape3D(landscape, app);
	}
	
	/**
	 * GRID
	 * 
	 * @param triangles
	 * @return
	 */
	public static Shape3D renderLandscapeGrid(float[][] map) {
        ArrayList<Point3f> ps = new ArrayList<>();
        
        for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				Point3f current_center = new Point3f(i, j, map[i][j]);
				
				if (i + 1 < map.length) {
					ps.add(current_center);
					ps.add(new Point3f(i + 1, j, map[i+1][j]));
					
					if (j + 1 < map[i].length) {
						ps.add(current_center);
						ps.add(new Point3f(i + 1, j + 1, map[i + 1][j + 1]));
					}
				}
				
				if (j + 1 < map[i].length) {
					ps.add(current_center);
					ps.add(new Point3f(i, j + 1, map[i][j + 1]));
				}
			}
		}
        
        LineArray landscape = new LineArray(ps.size(), LineArray.COORDINATES);
        int counter = 0;
        
        for (Point3f point3f : ps) {
			landscape.setCoordinate(counter++, point3f);
		}
        
        Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
        Appearance app = new Appearance();
        ColoringAttributes ca = new ColoringAttributes(white,
            ColoringAttributes.SHADE_FLAT);
        app.setColoringAttributes(ca);

        return new Shape3D(landscape, app);
	}
	

    /**
     *
     * @param triangles
     * @return
     */
    public static Shape3D renderLandscape(List<Triangle> triangles) {
        TriangleArray landscape = new TriangleArray(triangles.size() * 3, TriangleArray.COORDINATES);
        int counter = 0;
        for (int i = 0; i < triangles.size(); ++i) {
            landscape.setCoordinate(counter++, triangles.get(i).getCoordinate1());
            landscape.setCoordinate(counter++, triangles.get(i).getCoordinate2());
            landscape.setCoordinate(counter++, triangles.get(i).getCoordinate3());
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
    public static List<Triangle> getTriangles(float[][] map) {

        List<Triangle> ret = new ArrayList<>();

        if ((map.length % 2 != 0) && (map[0].length % 2 != 0)) {
            System.err.println("Grid size must be even.");
            System.exit(1);
        }

        for (int x = 0; x < map.length; x++) {
            if (x % 2 == 0) {
                for (int y = 0; y < (map[x].length - 1); y++) {
                    Point3f coordinate1 = new Point3f((float)x, (float)y, map[x][y]);
                    Point3f coordinate3 = new Point3f((float)x, (float)y + 1f, map[x][y + 1]);
                    Point3f coordinate2 = new Point3f((float)x + 1f, (float)y, map[x + 1][y]);
                    ret.add(new Triangle(coordinate1, coordinate2, coordinate3));
                }
            } else {
                for (int y = 1; y < map[x].length; y++) {
                    Point3f coordinate1 = new Point3f(x, y, map[x][y]);
                    Point3f coordinate3 = new Point3f(x, y - 1, map[x][y - 1]);
                    Point3f coordinate2 = new Point3f(x - 1, y, map[x - 1][y]);
                    ret.add(new Triangle(coordinate1, coordinate2, coordinate3));
                }
            }
        }

        return ret;
    }
}
