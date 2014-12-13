package algorithm;


public class DiamondSquare {
	public static void applyDiamondSquare(double[][] points, double roughness) {
		for (int i = points.length; i > 0; --i) {
			diamondStep(points, roughness, i);
			squareStep(points, roughness, i);
			
			roughness /= 2;
		}
	}
	
	private static void diamondStep(double[][] points, double roughness, int length) {
		
	}
	
	private static void squareStep(double[][] points, double roughness, int length) {
	}
}
