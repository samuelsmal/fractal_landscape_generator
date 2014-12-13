package flg.algorithm.test;

import flg.algorithm.DiamondSquare;

public class DiamondSquareTest {
	public static void runTest() {
		double[][] points = new double[5][5];
		
		points[0][0] = 13d;
		points[0][4] = -12.232d;
		points[4][0] = 0.1213d;
		points[4][4] = -112232.d;
		
		DiamondSquare.applyDiamondSquare(points, 0.12d);
		
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				System.out.print(points[i][j]);
				System.out.print("\t");
			}
			System.out.println();
		}
	}
}
