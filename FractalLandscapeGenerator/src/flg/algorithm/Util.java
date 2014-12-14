package flg.algorithm;

public class Util {
	public static float getMax(float[][] fs) {
		float max = fs[0][0];
		
		for (float[] fs2 : fs) {
			for (float f : fs2) {
				if (f > max) max = f;
			}
		}
		
		return max;
	}
}
