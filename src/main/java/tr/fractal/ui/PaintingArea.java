package tr.fractal.ui;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import tr.fractal.painters.Painter;


public class PaintingArea extends JPanel {
	private static final long serialVersionUID = 1L;

	private final List<Painter> painters = new LinkedList<Painter>();
	private volatile boolean painting = false;
	
	public PaintingArea() {
	}
	
	@Override
	protected void paintChildren(Graphics g) {
		painting = true;
		for (Painter painter : painters) {
			painter.paint(g);
		}
		painting = false;
	}

	public void addPainter(Painter painter) {
		painters.add(painter);
	}
	
	public boolean painting() {
		return painting;
	}
}
