package flg.algorithm;

import java.util.ArrayList;
import java.util.Random;

public class DiamondSquare {
	
	
	public static void applyDiamondSquare(double[][] points, double roughness) {
		for (int i = points.length / 2; i > 0; --i) {
			diamondStep(points, roughness, i);
			squareStep(points, roughness, i);
			
			randomNumber(roughness);
			
			//roughness /= 2;
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
				
				points[i][j] = average(parentValues) + randomNumber(roughness);
			}
		}
	}
	
	private static void squareStep(double[][] points, double roughness, int length) {
		
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
				
				points[i][j] = average(values) + randomNumber(roughness);
			}
		}
	}
	
	private static double randomNumber(double roughness){
		
		Random rand = new Random();
		
		if ((random <= roughness) && (random >= ((-1) * roughness))){
			return random;
		} else {
		    return roughness/2;
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
