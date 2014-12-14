package flg.rendering.test;

import java.util.List;

import flg.rendering.RenderLandscape;
import flg.rendering.Triangle;

public class TriangleTest {
	public static void runTest() {
		float[][] map = new float[4][4];
		
		for (int i = 0; i < map.length; ++i) {
			for (int j = 0; j < map[0].length; ++j) {
				map[i][j] = i * j;
			}
		}
		
		List<Triangle> triangles = RenderLandscape.getTriangles(map);
		
		for (Triangle t : triangles) {
			System.out.println("1: " + t.getCoordinate1());
			System.out.println("2: " + t.getCoordinate2());
			System.out.println("3: " + t.getCoordinate3());
			System.out.println();
		}
	}
}
