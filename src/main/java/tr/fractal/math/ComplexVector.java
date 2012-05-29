package tr.fractal.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
		
		boolean caIn;
		if (a1 <= a2) {
			caIn = (a1 - Complex.PRECISION <= ca && ca <= a2 + Complex.PRECISION);
		} else {
			caIn = (a2 - Complex.PRECISION <= ca && ca <= a1 + Complex.PRECISION);
		}
		boolean cbIn;
		if (b1 <= b2) {
			cbIn = (b1 - Complex.PRECISION <= cb && cb <= b2 + Complex.PRECISION);
		} else {
			cbIn = (b2 - Complex.PRECISION <= cb && cb <= b1 + Complex.PRECISION);
		}
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
		
		if (Math.abs(d) < Complex.PRECISION) {
			return null;
		} else {
			double ab12 = a1*b2 - b1*a2;
			double ab34 = a3*b4 - b3*a4;
			
			Complex x = new Complex((ab12*a34 - a12*ab34)/d, (ab12*b34 - b12*ab34)/d);
			
			if (areaContains(x) && vector.areaContains(x)) {
				return x;
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
		} else {
			initFrameVectors();
			
			List<Complex> interPoints = new ArrayList<Complex>();
			
			ComplexVector[] borders = new ComplexVector[] {left, top, bottom, right};
			for (int i = 0; i < borders.length; i++) {
				Complex xv = borders[i].intersect(vector);
				if (xv != null) {
					interPoints.add(xv);
				}
			}
			
			int interPointsSize = interPoints.size();
			if (interPointsSize == 2) {
				
				return new ComplexVector(interPoints.get(0), interPoints.get(1));
				
			} else if (interPointsSize == 1) {
				
				if (ov1In) {
					return new ComplexVector(ov1, interPoints.get(0));
				} else {
					return new ComplexVector(interPoints.get(0), ov2);
				}
			} else {
				return null;
			}
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
