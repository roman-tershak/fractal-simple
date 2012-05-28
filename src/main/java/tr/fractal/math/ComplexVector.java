package tr.fractal.math;

public class ComplexVector {

	private final Complex v1;
	private final Complex v2;
	
	private ComplexVector left;
	private ComplexVector top;
	private ComplexVector bottom;
	private ComplexVector right;
	
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

	public boolean contains(ComplexVector vector) {
		
		Complex ov1 = vector.getV1();
		Complex ov2 = vector.getV2();
		
		if (contains(ov1) || contains(ov2)) {
			return true;
		} else {
			initFrameVectors();
			if (left.intersect(vector) != null || top.intersect(vector) != null || 
					right.intersect(vector) != null || bottom.intersect(vector) != null) {
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean contains(Complex complex) {
		double ca = complex.getA();
		double cb = complex.getB();
		
		double a1 = getV1().getA();
		double b1 = getV1().getB();
		double a2 = getV2().getA();
		double b2 = getV2().getB();
		
		if (a1 <= ca && ca <= a2 && b1 <= cb && cb <= b2) {
			return true;
		} else {
			return false;
		}
	}

	public Complex intersect(ComplexVector vector) {
		// TODO Auto-generated method stub
		return null;
	}

	private void initFrameVectors() {
		if (left == null) {
			Complex vlt = new Complex(v1.getA(), v2.getB());
			Complex vrb = new Complex(v2.getA(), v1.getB());
	
			left = new ComplexVector(v1, vlt);
			top = new ComplexVector(vlt, v2);
			bottom = new ComplexVector(v1, vrb);
			right = new ComplexVector(vrb, v2);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ComplexVector [v1=").append(v1).append(", v2=").append(v2).append("]");
		return sb.toString();
	}

	public String toShortString() {
		StringBuilder sb = new StringBuilder();
		sb.append(v1.getA()).append(", ").append(v1.getB()).append(" -> ");
		sb.append(v2.getA()).append(", ").append(v2.getB());
		return sb.toString();
	}
}
