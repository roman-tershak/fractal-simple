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
		int rgb;
		int r = 0, g = 0, b = 0;
		if (n == Integer.MAX_VALUE) {
			g2d.setColor(Color.BLACK);
		} else {
			if (n < 40) {
				double ratio = Math.sqrt((double) n / 64);
				b = (int) (ratio * 255);
			} else if (n > 40 && n < 1000) {
				double ratio = Math.sqrt((double) n / 1000);
				b = 64;
				g = (int) (ratio * 255);
			} else {
				b = 32;
				g = 64;
				double ratio = Math.sqrt((double) n / 1000);
				r = (int) (ratio * 255);
			}
			rgb = r << 16 | g << 8 | b;
			g2d.setColor(new Color(rgb ));
		}
		g2d.drawLine(i, j, i, j);
	}
}
