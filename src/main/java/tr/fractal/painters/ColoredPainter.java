package tr.fractal.painters;

import java.awt.Color;
import java.awt.Graphics2D;

import tr.fractal.FractalCalculator;

public class ColoredPainter extends BlackAndWhitePainter {

	public ColoredPainter(FractalCalculator fractalCalculator) {
		super(fractalCalculator);
	}

	@Override
	protected void drawFractalPoint(Graphics2D g2d, int i, int j, int n) {
		if (n == Integer.MAX_VALUE) {
			g2d.setColor(Color.BLACK);
		} else {
			float h = 0.66F - (float) Math.sqrt((double) n / 10000);
			float s;
			if (n <= 1) {
				s = 1;
			} else {
				s = (float) 1 / n + 0.5F;
			}
			float b = (float) ((float) Math.atan(n) / Math.PI * 1.3);
			g2d.setColor(Color.getHSBColor(h, s, b));
		}
		g2d.drawLine(i, j, i, j);
	}
}
