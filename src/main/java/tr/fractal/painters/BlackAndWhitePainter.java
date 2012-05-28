package tr.fractal.painters;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import tr.fractal.FractalCalculator;
import tr.fractal.math.Complex;
import tr.fractal.math.ComplexVector;

public class BlackAndWhitePainter implements Painter {

	private final FractalCalculator fractalCalculator;
	private final JPanel paintingArea;
	
	private int prevWidth;
	private int prevHeight;
	
	public BlackAndWhitePainter(FractalCalculator fractalCalculator, JPanel paintingArea) {
		this.fractalCalculator = fractalCalculator;
		this.paintingArea = paintingArea;
	}
	
	public void paint(Graphics g) {

		setFractalComplexArea();
        int[][] m = fractalCalculator.calculate();
        
        int height = paintingArea.getHeight();
        
        for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				drawFractalPoint((Graphics2D) g, i, height - j, m[i][j]);
			}
		}
	}

	protected void drawFractalPoint(Graphics2D g2d, int i, int j, int n) {
		if (n >= Integer.MAX_VALUE) {
			g2d.drawLine(i, j, i, j);
		}
	}

	protected void setFractalComplexArea() {
		int height = paintingArea.getHeight();
		int width = paintingArea.getWidth();
		
		if (prevWidth != 0.0 && prevHeight != 0.0) {
			double wratio = ((double) width) / prevWidth;
			double hratio = ((double) height) / prevHeight;
			
			ComplexVector vector = fractalCalculator.getArea();
			double a1 = vector.getV1().getA();
			double b2 = vector.getV2().getB();
			
			double newA = a1 + vector.getA() * wratio;
			double newB = b2 - vector.getB() * hratio;
			
			fractalCalculator.setArea(new Complex(a1, newB), new Complex(newA, b2));
		}
		
		fractalCalculator.setGranularity(width, height);
		
		prevWidth = width;
		prevHeight = height;
	}

}
