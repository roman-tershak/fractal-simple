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

	public double getA() {
		return v2.getA() - v1.getA();
	}

	public double getB() {
		return v2.getB() - v1.getB();
	}
	
	public ComplexVector mul(double d) {
		double da = v2.getA() - v1.getA();
		double db = v2.getB() - v1.getB();
		
		return new ComplexVector(v1, new Complex(v1.getA() + da * d, v1.getB() + db * d));
	}
	
	public ComplexVector add(Complex c) {
		return new ComplexVector(v1.add(c), v2.add(c));
	}

	public double getMod() {
		double da = v2.getA() - v1.getA();
		double db = v2.getB() - v1.getB();
		return Math.sqrt(da * da + db * db);
	}

	@Override
	public String toString() {
		return "ComplexVector [v1=" + v1 + ", v2=" + v2 + "]";
	}
}
