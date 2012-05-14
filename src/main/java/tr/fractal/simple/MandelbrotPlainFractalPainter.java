package tr.fractal.simple;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class MandelbrotPlainFractalPainter implements Painter {

	public void paintOn(PaintingArea paintingArea, Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		int height = paintingArea.getHeight();
		int width = paintingArea.getWidth();
        int cx = width / 2;
        int cy = height / 2;
        double ratio = (width > height ? height : width) / 4;
		
        g2d.drawLine(0, cy, width, cy);
        g2d.drawLine(cx, 0, cx, height);
        
        int r1 = (int) ratio;
        int r2 = (int) (2 * ratio);
        g2d.drawOval(cx-r1, cy-r1, 2*r1, 2*r1);
        g2d.drawOval(cx-r2, cy-r2, 2*r2, 2*r2);
        
		for (double ca = -2.0; ca < 2.0; ca += 0.005) {
			for (double cb = -2.0; cb < 2.0; cb += 0.005) {
				
				Complex c = new Complex(ca, cb);
				Complex z = new Complex(0.0, 0.0);
				boolean inSet = true;
				
				for (int i = 0; i < 100; i++) {
					z = z.mul(z).mul(z).mul(z).mul(z).mul(z).mul(z).mul(z).add(c);
					
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
		
	}

}
