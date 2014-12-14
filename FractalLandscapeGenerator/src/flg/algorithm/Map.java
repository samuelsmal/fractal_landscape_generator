package flg.algorithm;

public class Map {
	public static float[][] hillCenterMap(int dim, float center, float corner) {
		float[][] map = new float[dim][dim];
		
        map[0][0] = map[0][dim - 1] = map[dim - 1][0] = map[dim - 1][dim - 1] = corner;
        
        int numberOfElements = dim / 10;
        
        for (int i = - numberOfElements / 2; i < numberOfElements / 2; ++i) {
        	for (int j = - numberOfElements / 2; j < numberOfElements / 2; ++j) {
            	map[dim / 2 + i][dim / 2 + j] = center;
            }
        }
		
		return map;
	}
}
