package flg.algorithm;

public class Util {

    public static float getMax(float[][] fs) {
        float max = fs[0][0];

        for (float[] fs2 : fs) {
            for (float f : fs2) {
                if (f > max) {
                    max = f;
                }
            }
        }

        return max;
    }

    public static int pow(int a, int b)
    {
        if (b == 0) {
            return 1;
        }
        if (b == 1) {
            return a;
        }
        if (b % 2 == 0) {
            return pow(a * a, b / 2); // even a=(a^2)^b/2
        }
        else {
            return a * pow(a * a, b / 2); // odd a=a*(a^2)^b/2
        }

    }

    public static float getMin(float[][] fs) {
        float min = fs[0][0];

        for (float[] fs2 : fs) {
            for (float f : fs2) {
                if (f < min) {
                    min = f;
                }
            }
        }

        return min;
    }
}
