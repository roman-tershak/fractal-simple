package tr.fractal.math;

public interface FractalCalculator {

	public void setArea(Complex v1, Complex v2);

	public void setGranularity(int xn, int yn);
	
	public void setMaxIterations(int maxIterations);
	
	public int[][] calculate();
}
