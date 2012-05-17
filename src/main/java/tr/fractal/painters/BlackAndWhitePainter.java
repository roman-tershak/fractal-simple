package tr.fractal.painters;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import tr.fractal.math.Complex;
import tr.fractal.math.FractalCalculator;

public class BlackAndWhitePainter implements Painter {

	private final FractalCalculator fractalCalculator;
	private JPanel panel;
	
	private int prevWidth;
	private int prevHeight;
	
	public BlackAndWhitePainter(FractalCalculator fractalCalculator) {
		this.fractalCalculator = fractalCalculator;
	}
	
	public void setPaintArea(JPanel panel) {
		this.panel = panel;
	}

	public void paint(Graphics g) {

		setArea();
        int[][] m = fractalCalculator.calculate();
        
        for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				drawFractalPoint((Graphics2D) g, m[i][j], i, j);
			}
		}
	}

	protected void setArea() {
		int height = panel.getHeight();
		int width = panel.getWidth();
		
		if (prevWidth != 0.0 && prevHeight != 0.0) {
			double wratio = ((double) width) / prevWidth;
			double hratio = ((double) height) / prevHeight;
			
			Complex v1 = fractalCalculator.getArea().getV1();
			Complex v2 = fractalCalculator.getArea().getV2();
			
			double newA = (v2.getA() - v1.getA()) * wratio + v1.getA();
			double newB = (v2.getB() - v1.getB()) * hratio + v1.getB();
			
			Complex newV2 = new Complex(newA, newB);
			
			fractalCalculator.setArea(v1, newV2);
		}
		
		fractalCalculator.setGranularity(width, height);
		
		prevWidth = width;
		prevHeight = height;
	}

	protected void drawFractalPoint(Graphics2D g2d, int n, int i, int j) {
		if (n >= Integer.MAX_VALUE) {
			int jnorm = panel.getHeight() - j;
			g2d.drawLine(i, jnorm, i, jnorm);
		}
	}

}
