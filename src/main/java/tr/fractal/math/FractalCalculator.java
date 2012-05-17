package tr.fractal.math;

public interface FractalCalculator {

	public ComplexVector getArea();
	
	public void setArea(Complex v1, Complex v2);
	
	public void setArea(ComplexVector add);

	public void setGranularity(int xn, int yn);
	
	public int getMaxIterations();

	public void setMaxIterations(int maxIterations);
	
	public int[][] calculate();
}
