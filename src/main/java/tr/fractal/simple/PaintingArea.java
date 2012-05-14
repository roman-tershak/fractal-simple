package tr.fractal.simple;

import java.awt.Graphics;

import javax.swing.JPanel;

public class PaintingArea extends JPanel {
	private static final long serialVersionUID = 1L;

	private final Painter painter;
	
	public PaintingArea(Painter painter) {
		this.painter = painter;
	}
	
	@Override
	protected void paintChildren(Graphics g) {
		painter.paintOn(this, g);
	}
}
