package tr.fractal.math;

public class ComplexVector {

	private final Complex v1;
	private final Complex v2;
	
	public ComplexVector(Complex v1, Complex v2) {
		this.v1 = v1;
		this.v2 = v2;
	}
	
	public Complex getV1() {
		return v1;
	}
	
	public Complex getV2() {
		return v2;
	}
}
