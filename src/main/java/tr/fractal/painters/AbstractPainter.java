package tr.fractal.painters;

import java.awt.Graphics;

import tr.fractal.FractalCalculator;
import tr.fractal.ui.PaintingArea;

public abstract class AbstractPainter implements Painter {

	private final FractalCalculator fractalCalculator;
	private final PaintingArea paintingArea;

	public AbstractPainter(FractalCalculator fractalCalculator, PaintingArea paintingArea) {
		this.fractalCalculator = fractalCalculator;
		this.paintingArea = paintingArea;
	}
	
	public FractalCalculator getFractalCalculator() {
		return fractalCalculator;
	}
	
	public PaintingArea getPaintingArea() {
		return paintingArea;
	}
	
	public abstract void paint(Graphics g);
}