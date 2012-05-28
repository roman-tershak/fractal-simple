package tr.fractal.painters;

import java.awt.Graphics;
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
	private final List<ComplexVector> trail = new LinkedList<ComplexVector>();

	private Complex init;

	public FractalPointIterativePainter(FractalCalculator fractalCalculator, PaintingArea paintingArea, FractalFormula fractalFormula) {
		super(fractalCalculator, paintingArea);
		this.fractalFormula = fractalFormula;
	}

	@Override
	public void paint(Graphics g) {
		if (trail.size() > 0) {
			
		}
	}
	
	public void setInitialPoint(Complex init) {
		this.init = init;
		head = Complex.ZERO;
		trail.clear();
	}
	
	public void calculateNextIteration() {
		Complex newHead = fractalFormula.calculateOneIteration(head, init);
		trail.add(new ComplexVector(head, newHead));
	}
}
