package tr.fractal.math;

import java.util.ArrayList;
import java.util.List;

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
	
	public Complex calculateOneIteration(Complex... cs) {
		Complex z = cs[0];
		Complex c = cs[1];
		return z.mul(z).add(c);
	}
	
	public List<ComplexVector> calculateOneStep(Complex... cs) {
		Complex z = cs[0];
		Complex c = cs[1];
		List<ComplexVector> steps = new ArrayList<ComplexVector>();
		
		Complex mul = z.mul(z);
		steps.add(new ComplexVector(z, mul));
		steps.add(new ComplexVector(mul, mul.add(c)));
		
		return steps;
	}
}
