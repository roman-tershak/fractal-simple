package tr.fractal.math;

public class MandelbrotFormula implements FractalFormula {

	public int calculate(double ca, double cb, int maxIterations) {
		
		double za = 0;
		double zb = 0;
		
		for (int i = 0; i < maxIterations; i++) {
			
			double za2 = za*za - zb*zb + ca;
			zb = 2*za*zb + cb;
			za = za2;
			
			if (za*za + zb*zb > 100) {
				return i;
			}
			
		}
		return Integer.MAX_VALUE;
	}
}
