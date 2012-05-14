package tr.fractal.simple;

import java.awt.Color;
import java.awt.Graphics2D;

public class DefaultCoordsPainter implements CoordsPainter {

	public void drawCoords(Graphics2D g2d, int cx, int cy, int width, int height, double ratio) {
		g2d.drawLine(0, cy, width, cy);
        g2d.drawLine(cx, 0, cx, height);
        
        int r1 = (int) ratio;
        int r2 = (int) (2 * ratio);
        Color before = g2d.getColor();
        g2d.setColor(Color.RED);
        g2d.drawLine(cx-r2, cy-2, cx-r2, cy+2);
        g2d.drawLine(cx-r1, cy-2, cx-r1, cy+2);
        g2d.drawLine(cx+r1, cy-2, cx+r1, cy+2);
        g2d.drawLine(cx+r2, cy-2, cx+r2, cy+2);
        g2d.drawLine(cx-2, cy-r2, cx+2, cy-r2);
        g2d.drawLine(cx-2, cy-r1, cx+2, cy-r1);
        g2d.drawLine(cx-2, cy+r1, cx+2, cy+r1);
        g2d.drawLine(cx-2, cy+r2, cx+2, cy+r2);
        g2d.setColor(before);
	}

}
