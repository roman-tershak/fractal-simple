package tr.fractal.ui;

import java.awt.Graphics;

import javax.swing.JPanel;

import tr.fractal.painters.Painter;


public class PaintingArea extends JPanel {
	private static final long serialVersionUID = 1L;

	private Painter painter;
	
	public PaintingArea() {
	}
	
	public void setPainter(Painter painter) {
		this.painter = painter;
	}
	
	@Override
	protected void paintChildren(Graphics g) {
		painter.paint(g);
	}
}
