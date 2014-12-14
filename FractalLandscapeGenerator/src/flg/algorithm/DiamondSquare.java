package flg.algorithm;

import java.util.ArrayList;
import java.util.Random;

public class DiamondSquare {
	/**
	 * 
	 * @param points
	 * @param roughness
	 */
	public static void applyDiamondSquare(float[][] points, float roughness) {
		Random rand = new Random();
		
		for (int i = points.length / 2; i > 0; --i) {
			diamondStep(points, rand, roughness, i);
			squareStep(points, rand, roughness, i);
			
			roughness *= 0.998f;
		}
	}
	
	private static void diamondStep(float[][] points, Random rand, float roughness, int length) {
		for (int i = 0; i < points.length; ++i) {
			for (int j = 0; j < points.length; ++j) {
				ArrayList<Float> parentValues = new ArrayList<Float>();
				
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
				
				points[i][j] = average(parentValues) + (rand.nextFloat() - 0.5f) * roughness;
			}
		}
	}
	
	private static void squareStep(float[][] points, Random rand, float roughness, int length) {
		for (int i=0;i<points.length;++i){
			for(int j=0; j<points.length;++j){
				ArrayList<Float> values = new ArrayList<Float>();
			
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
				
				points[i][j] = average(values) + (rand.nextFloat() - 0.5f) * roughness;
			}
		}
	}
	
	private static Float average(ArrayList<Float> l) {
		Float sum = 0f;
		
		for (Float i : l) {
			sum += i;
		}
		
		return sum / l.size();
	}
}
