package tr.fractal.painters;

import java.awt.Graphics;

import tr.fractal.ui.PaintingArea;


public interface Painter {

	public void paintOn(PaintingArea paintingArea, Graphics g);
}
