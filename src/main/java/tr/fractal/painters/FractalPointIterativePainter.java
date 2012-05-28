package tr.fractal.painters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

import tr.fractal.FractalCalculator;
import tr.fractal.math.Complex;
import tr.fractal.math.ComplexVector;
import tr.fractal.math.FractalFormula;
import tr.fractal.ui.PaintingArea;

public class FractalPointIterativePainter extends AbstractPainter {

	private final FractalFormula fractalFormula;
	
	private Complex head;
	private Complex init;

	private final List<ComplexVector> trail = new LinkedList<ComplexVector>();

	public FractalPointIterativePainter(FractalCalculator fractalCalculator, PaintingArea paintingArea, FractalFormula fractalFormula) {
		super(fractalCalculator, paintingArea);
		this.fractalFormula = fractalFormula;
	}

	@Override
	public void paint(Graphics g) {
		if (trail.size() > 0) {
			Graphics2D g2d = (Graphics2D) g;
			
			g2d.setColor(new Color(30, 190, 220));
			
			ComplexVector area = getFractalCalculator().getArea();
			PaintingArea paintingArea = getPaintingArea();
			
			Complex va1 = area.getV1();
			
			double aa = area.getA();
			double ab = area.getB();
			
			int height = paintingArea.getHeight();
			double wratio = paintingArea.getWidth() / aa;
			double hratio = height / ab;
			
			for (ComplexVector iterVector : trail) {
				ComplexVector vectorToPaint = area.areaIntersect(iterVector);
				
				if (vectorToPaint != null) {
					Complex v1 = vectorToPaint.getV1();
					Complex v2 = vectorToPaint.getV2();
					
					int xi1 = (int) ((v1.getA() - va1.getA()) * wratio);
					int yi1 = height - (int) ((v1.getB() - va1.getB()) * hratio);
					
					int xi2 = (int) ((v2.getA() - va1.getA()) * wratio);
					int yi2 = height - (int) ((v2.getB() - va1.getB()) * hratio);
					
					g2d.drawLine(xi1, yi1, xi2, yi2);
				}
			}
		}
	}
	
	public void setInitialPoint(Complex init) {
//		System.out.println(init);
		this.init = init;
		head = Complex.ZERO;
		trail.clear();
	}
	
	public void calculateNextIteration() {
		Complex newHead = fractalFormula.calculateOneIteration(head, init);
		
		trail.add(new ComplexVector(head, newHead));
		head = newHead;
	}
}
