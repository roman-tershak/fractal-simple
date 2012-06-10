package tr.fractal.math;

import java.util.List;

public interface FractalFormula {

	int calculate(double ca, double cb, int maxIterations);

	Complex calculateOneIteration(Complex... cs);

	List<ComplexVector> calculateOneStep(Complex... cs);
}
