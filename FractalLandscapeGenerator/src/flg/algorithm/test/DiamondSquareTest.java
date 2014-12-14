package flg.algorithm.test;

import flg.algorithm.DiamondSquare;


public class DiamondSquareTest {
	public static void runTest() {
		float[][] points = new float[5][5];
		
		points[0][0] = 13f;
		points[0][4] = -12.232f;
		points[4][0] = 0.1213f;
		points[4][4] = -120.0f;
		
		DiamondSquare.applyDiamondSquare(points, 0.8f);
		
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				System.out.print(points[i][j]);
				System.out.print("\t");
			}
			System.out.println();
		}
	}
}
