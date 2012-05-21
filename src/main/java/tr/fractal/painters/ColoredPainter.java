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
			float h = 0.69F - (float) Math.sqrt((double) n / 10000);
			float s;
			if (n <= 1) {
				s = 1;
			} else {
				s = (float) 1 / n + 0.5F;
			}
			float b = (float) ((float) Math.atan((double) n / 6) / Math.PI * 1.3);
			g2d.setColor(Color.getHSBColor(h, s, b));
		}
		g2d.drawLine(i, j, i, j);
	}
	
	public static Color getColor(int n) {
		Color color;
		int rgb;
		int r = 0, g = 0, b = 0;
		if (n == Integer.MAX_VALUE) {
			color = Color.BLACK;
		} else {
			if (n < 40) {
				double ratio = Math.sqrt((double) n / 40);
				b = (int) (ratio * 224);
			} else if (n >= 40 && n < 120) {
				double ratio = Math.sqrt((double) (n - 39) / 120);
				g = (int) (ratio * 224);
				b = 222 - (int) (0.1 * (double) g);
			} else {
				double ratio = Math.sqrt((double) (n - 119) / 1000);
				r = (int) (ratio * 224);
				b = 222 - (int) (0.2 * (double) r);
				g = 178 - (int) (0.1 * (double) r);
			}
			rgb = r << 16 | g << 8 | b;
			color = new Color(rgb);
		}
		return color;
	}
}
