package flg.algorithm;

import java.util.ArrayList;

public class DiamondSquare {
	public static void applyDiamondSquare(double[][] points, double roughness) {
		for (int i = points.length / 2; i > 0; --i) {
			diamondStep(points, roughness, i);
			//squareStep(points, roughness, i);
			
			roughness /= 2;
		}
	}
	
	private static void diamondStep(double[][] points, double roughness, int length) {
		for (int i = 0; i < points.length; ++i) {
			for (int j = 0; j < points.length; ++j) {
				ArrayList<Double> parentValues = new ArrayList<Double>();
				
				if (i >= length / 2) {
					if (j >= length / 2) {
						parentValues.add(points[i - length / 2][j - length / 2]);
					}
					
					if (j + length / 2 < points.length) {
						parentValues.add(points[i - length / 2][j + length / 2]);
					}
				}
				
				if (i + length / 2 < points.length) {
					if (j >= length / 2) {
						parentValues.add(points[i + length / 2][j - length / 2]);
					}
					
					if (j + length / 2 < points.length) {
						parentValues.add(points[i + length / 2][j + length / 2]);
					}
				}
				
				points[i][j] = average(parentValues) + roughness;
			}
		}
	}
	
	private static void squareStep(double[][] points, double roughness, int length) {
	}
	
	private static Double average(ArrayList<Double> l) {
		Double sum = 0d;
		
		for (Double i : l) {
			sum += i;
		}
		
		return sum / l.size();
	}
}
