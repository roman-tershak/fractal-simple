package tr.fractal.painters;

import java.awt.Color;
import java.awt.Graphics2D;

import tr.fractal.FractalCalculator;
import tr.fractal.ui.PaintingArea;

public class ColoredPainter extends BlackAndWhitePainter {

	public ColoredPainter(FractalCalculator fractalCalculator, PaintingArea paintingArea) {
		super(fractalCalculator, paintingArea);
	}

	@Override
	protected void drawFractalPoint(Graphics2D g2d, int i, int j, int n) {
		g2d.setColor(getColor(n));
		g2d.drawLine(i, j, i, j);
	}

	public static Color getColor(int n) {
//		return getHSBColor(n);
		return getRGBColor(n);
	}
	
	static Color getHSBColor(int n) {
		Color color;
		if (n == Integer.MAX_VALUE) {
			color = Color.BLACK;
		} else {
			float h = 0.69F - (float) Math.sqrt((double) n / 10000);
			float s;
			if (n <= 1) {
				s = 1;
			} else {
				s = (float) 1 / n + 0.5F;
			}
			float b = (float) ((float) Math.atan((double) n / 6) / Math.PI * 1.3);
			color = Color.getHSBColor(h, s, b);
		}
		return color;
	}
	
	static Color getRGBColor(int n) {
		Color color;
		int rgb;
		if (n == Integer.MAX_VALUE) {
			color = Color.BLACK;
		} else {
			int r, g, b;
			if (n < 40) {
				double ratio = Math.sqrt((double) n / 40);
				b = (int) (ratio * 224);
				g = 0;
				r = 0;
			} else if (n >= 40 && n < 120) {
				double ratio = Math.sqrt((double) (n - 39) / 80);
				g = (int) (ratio * 224);
				b = 222 - (int) (0.1 * (double) g);
				r = 0;
			} else if (n >= 120 && n < 360) {
				double ratio = Math.sqrt((double) (n - 119) / 240);
				r = (int) (ratio * 224);
				g = 222 - (int) (0.2 * (double) r);
				b = 200 - (int) (0.1 * (double) r);
			} else if (n >= 360 && n < 1080) {
				double ratio = Math.sqrt((double) (n - 359) / 720);
				r = 16 + (int) (ratio * 224);
				g = 108 - (int) (0.2 * (double) r);
				b = 146 - (int) (0.4 * (double) r);
			} else {
				double ratio = Math.sqrt((double) (n - 1079) / 2160);
				r = 32 + ((int) (ratio * 224));
				g = 100 - (int) (0.6 * (double) r);
				b = 80 - (int) (0.2 * (double) r);
			}
			rgb = r << 16 | g << 8 | b;
			color = new Color(rgb);
		}
		return color;
	}
}
