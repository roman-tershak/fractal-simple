package tr.fractal.painters;

import java.awt.Color;
import java.awt.Graphics2D;

import tr.fractal.math.FractalCalculator;

public class ColoredPainter extends BlackAndWhitePainter {

	static final int MAX_RGB_COLOR_INT = 1 << 23;
	
	private double ratio = MAX_RGB_COLOR_INT / (double) 100;
	
	public ColoredPainter(FractalCalculator fractalCalculator) {
		super(fractalCalculator);
	}

	public void setMaxIterations(int maxIterations) {
		ratio = MAX_RGB_COLOR_INT / (double) maxIterations;
	}
	
	@Override
	protected void drawFractalPoint(Graphics2D g2d, int i, int j, int n) {
		int rgb = MAX_RGB_COLOR_INT - (int) (n * ratio);
		rgb = rgb < 0 ? 0 : rgb;
		g2d.setColor(new Color(rgb ));
		g2d.drawLine(i, j, i, j);
	}
}