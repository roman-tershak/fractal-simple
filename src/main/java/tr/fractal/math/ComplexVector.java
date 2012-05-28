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

	public boolean areaContains(ComplexVector vector) {
		
		Complex ov1 = vector.getV1();
		Complex ov2 = vector.getV2();
		
		if (areaContains(ov1) || areaContains(ov2)) {
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

	public boolean areaContains(Complex complex) {
		double ca = complex.getA();
		double cb = complex.getB();
		
		double a1 = getV1().getA();
		double b1 = getV1().getB();
		double a2 = getV2().getA();
		double b2 = getV2().getB();
		
		boolean caIn = (a1 <= a2) ? (a1 <= ca && ca <= a2) : (a2 <= ca && ca <= a1); 
		boolean cbIn = (b1 <= b2) ? (b1 <= cb && cb <= b2) : (b2 <= cb && cb <= b1);
		if (caIn && cbIn) {
			return true;
		} else {
			return false;
		}
	}

	public Complex intersect(ComplexVector vector) {
		double a1 = getV1().getA();
		double b1 = getV1().getB();
		double a2 = getV2().getA();
		double b2 = getV2().getB();
		double a3 = vector.getV1().getA();
		double b3 = vector.getV1().getB();
		double a4 = vector.getV2().getA();
		double b4 = vector.getV2().getB();
		
		double a12 = a1 - a2;
		double b12 = b1 - b2;
		double a34 = a3 - a4;
		double b34 = b3 - b4;
		
		double d = a12*b34 - b12*a34;
		
		if (d < Complex.PRECISION) {
			return null;
		} else {
			double ab12 = a1*b2 - b1*a2;
			double ab34 = a3*b4 - b3*a4;
			
			double ax = (ab12*a34 - a12*ab34)/d;
			double bx = (ab12*b34 - b12*ab34)/d;
			
			boolean aIn = (a1 <= a2) ? (a1 <= ax && ax <= a2) : (a2 <= ax && ax <= a1);
			boolean bIn = (b1 <= b2) ? (b1 <= bx && bx <= b2) : (b2 <= bx && bx <= b1);
			
			if (aIn && bIn) {
				return new Complex(ax, bx);
			} else {
				return null;
			}
		}
	}
	
	public ComplexVector areaIntersect(ComplexVector vector) {
		Complex ov1 = vector.getV1();
		Complex ov2 = vector.getV2();
		
		boolean ov1In = areaContains(ov1);
		boolean ov2In = areaContains(ov2);
		
		if (ov1In && ov2In) {
			return vector;
		} else if (ov1In) {
			for (ComplexVector border : new ComplexVector[] {left, top, bottom, right}) {
				Complex xv2 = border.intersect(vector);
				if (xv2 != null) {
					return new ComplexVector(ov1, xv2);
				}
			}
			return new ComplexVector(ov1, ov1);
		} else if (ov2In) {
			for (ComplexVector border : new ComplexVector[] {left, top, bottom, right}) {
				Complex xv1 = border.intersect(vector);
				if (xv1 != null) {
					return new ComplexVector(xv1, ov2);
				}
			}
			return new ComplexVector(ov2, ov2);
		} else {
			return null;
		}
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
