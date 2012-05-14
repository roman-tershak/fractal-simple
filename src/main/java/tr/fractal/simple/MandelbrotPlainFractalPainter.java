package tr.fractal.simple;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class MandelbrotPlainFractalPainter implements Painter {

	private final CoordsPainter coordsPainter;
	
	public MandelbrotPlainFractalPainter(CoordsPainter coordsPainter) {
		this.coordsPainter = coordsPainter;
	}

	public void paintOn(PaintingArea paintingArea, Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		int height = paintingArea.getHeight();
		int width = paintingArea.getWidth();
        int cx = width / 2;
        int cy = height / 2;
        double ratio = (width > height ? height : width) / 4;
		
        double d = 1/ratio;
		for (double ca = -2.0; ca < 2.0; ca += d) {
			for (double cb = -2.0; cb < 2.0; cb += d) {
				
				Complex c = new Complex(ca, cb);
				Complex z = new Complex(0.0, 0.0);
				boolean inSet = true;
				
				for (int i = 0; i < 500; i++) {
					z = z.mul(z).add(c);
					
					double a = z.getA();
					double b = z.getB();
					if (a < -2.0 || a > 2.0 || b < -2.0 || b > 2.0) {
						inSet = false;
						break;
					}
				}
				
				if (inSet) {
					int x1 = cx + (int) (ca*ratio);
					int y1 = cy + (int) (cb*ratio);
					int x2 = x1;
					int y2 = y1;
					
					g2d.drawLine(x1, y1, x2, y2);
				}
			}
		}
		
		coordsPainter.drawCoords(g2d, cx, cy, width, height, ratio);
		
	}

}
