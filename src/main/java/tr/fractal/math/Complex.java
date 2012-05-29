package tr.fractal.math;

public final class Complex {

	public static final Complex ZERO = new Complex(0, 0);

	public static final double PRECISION = 1e-10;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(a);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(b);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass())
			return false;
		if (this == obj)
			return true;
		Complex other = (Complex) obj;
		if (Double.doubleToLongBits(a) != Double.doubleToLongBits(other.a))
			return false;
		if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Complex [a=" + a + ", b=" + b + "]";
	}
}
