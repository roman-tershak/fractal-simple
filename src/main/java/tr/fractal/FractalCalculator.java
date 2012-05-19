package tr.fractal;

import tr.fractal.math.Complex;
import tr.fractal.math.ComplexVector;

public interface FractalCalculator {

	public ComplexVector getArea();
	
	public void setArea(Complex v1, Complex v2);
	
	public void setArea(ComplexVector add);

	public void setGranularity(int xn, int yn);
	
	public int getMaxIterations();

	public void setMaxIterations(int maxIterations);
	
	public int[][] calculate();
}
