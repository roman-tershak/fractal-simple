package tr.fractal.math;

public interface FractalFormula {

	int calculate(double ca, double cb, int maxIterations);

	Complex calculateOneIteration(Complex... cs);

}
