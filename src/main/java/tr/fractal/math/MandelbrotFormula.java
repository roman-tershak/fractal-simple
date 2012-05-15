package tr.fractal.math;

public class MandelbrotFormula implements FractalFormula {

	public int calculate(double ca, double cb, int maxIterations) {
		
		double za = 0;
		double zb = 0;
		
		for (int i = 0; i < maxIterations; i++) {
			
			double za2 = za*za - zb*zb + ca;
			if (za2 < -10 || za2 > 10) {
				return i;
			}
			
			zb = 2*za*zb + cb;
			if (zb < -10 || zb > 10) {
				return i;
			}
			
			za = za2;
		}
		return Integer.MAX_VALUE;
	}
}
