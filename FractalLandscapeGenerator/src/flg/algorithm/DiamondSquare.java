package flg.algorithm;

import java.util.ArrayList;
import java.util.Random;

public class DiamondSquare {
	/**
	 * 
	 * @param points
	 * @param roughness > 0 && < 1 
	 */
	public static void applyDiamondSquare(double[][] points, double roughness) {
		Random rand = new Random();
		
		for (int i = points.length / 2; i > 0; --i) {
			diamondStep(points, rand, roughness, i);
			squareStep(points, rand, roughness, i);
			
			roughness /= 2;
		}
	}
	
	private static void diamondStep(double[][] points, Random rand, double roughness, int length) {
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
				
				points[i][j] = average(parentValues) + (rand.nextDouble() - 0.5d)*roughness;
			}
		}
	}
	
	private static void squareStep(double[][] points, Random rand, double roughness, int length) {
		
		for (int i=0;i<points.length;++i){
			for(int j=0; j<points.length;++j){
				ArrayList<Double> values = new ArrayList<Double>();
			
				if (i >= length) {
					if (j >= length) {
						values.add(points[i - length/2][j]);
					}
					
					if (j + length < points.length) {
						values.add(points[i][j + length/2]);
					}
				}
				
				if (i + length < points.length) {
					if (j >= length) {
						values.add(points[i][j - length/2]);
					}
					
					if (j + length < points.length) {
						values.add(points[i + length/2][j]);
					}
				}
				
				points[i][j] = average(values) + (rand.nextDouble() - 0.5d)*roughness;
			}
		}
	}
	
	private static Double average(ArrayList<Double> l) {
		Double sum = 0d;
		
		for (Double i : l) {
			sum += i;
		}
		
		return sum / l.size();
	}
}
