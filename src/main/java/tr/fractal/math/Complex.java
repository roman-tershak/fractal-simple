package tr.fractal.math;

public class Complex {

	private final double a;
	private final double b;

	public Complex(double a, double b) {
		this.a = a;
		this.b = b;
	}

	public Complex add(Complex other) {
		return new Complex((a + other.a), (b + other.b));
	}
	
	public Complex sub(Complex other) {
		return new Complex((a - other.a), (b - other.b));
	}
	
	public Complex mul(Complex other) {
		double c = other.a;
		double d = other.b;
		return new Complex((a*c - b*d), (a*d + b*c));
	}
	
	public Complex div(Complex other) {
		double c = other.a;
		double d = other.b;
		double e = c*c + d*d;
		return new Complex((a*c + b*d)/e, (b*c - a*d)/e);
	}
	
	public double getA() {
		return a;
	}
	public double getB() {
		return b;
	}
}
