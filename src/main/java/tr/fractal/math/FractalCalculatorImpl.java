package tr.fractal.math;

import java.util.concurrent.atomic.AtomicBoolean;

public class FractalCalculatorImpl implements FractalCalculator {

	private Complex v1 = Complex.ZERO;
	private Complex v2 = Complex.ZERO;
	
	private int xn;
	private int yn;
	
	private int maxIterations;
	
	private final AtomicBoolean recalculate = new AtomicBoolean(true);
	private int[][] fractalMatrix;
	private FractalFormula formula;

	
	public FractalCalculatorImpl(FractalFormula formula) {
		this.formula = formula;
	}

	public void setArea(Complex v1, Complex v2) {
		if (!this.v1.equals(v1)) {
			this.v1 = v1;
			this.recalculate.set(true);
		}
		if (!this.v2.equals(v2)) {
			this.v2 = v2;
			this.recalculate.set(true);
		}
	}

	public void setGranularity(int xn, int yn) {
		if (this.xn != xn) {
			this.xn = xn;
			this.recalculate.set(true);
		}
		if (this.yn != yn) {
			this.yn = yn;
			this.recalculate.set(true);
		}
	}

	public void setMaxIterations(int maxIterations) {
		if (this.maxIterations != maxIterations) {
			this.maxIterations = maxIterations;
			recalculate.set(true);
		}
	}

	public int[][] calculate() {
		if (recalculate.compareAndSet(true, false)) {
			double a1 = v1.getA();
			double b1 = v1.getB();
			double a2 = v2.getA();
			double b2 = v2.getB();
			
			double dx = (a2 - a1) / xn;
			double dy = (b2 - b1) / yn;
			
			int[][] m = new int[xn][yn];
			
			double ai = a1;
			for (int i = 0; i < xn; i++, ai += dx) {
				double bi = b1;
				for (int j = 0; j < yn; j++, bi += dy) {
					
					m[i][j] = formula.calculate(ai, bi, maxIterations);
				}
				
			}
			fractalMatrix = m;
		}
		return fractalMatrix;
	}

}
