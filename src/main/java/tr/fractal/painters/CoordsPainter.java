package tr.fractal.painters;

import java.awt.Graphics2D;

public interface CoordsPainter {

	public void drawCoords(Graphics2D g2d, int cx, int cy, int width, int height, double ratio);
}