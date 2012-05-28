package tr.fractal.ui;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import tr.fractal.painters.Painter;


public class PaintingArea extends JPanel {
	private static final long serialVersionUID = 1L;

	private final List<Painter> painters = new LinkedList<Painter>();
	
	public PaintingArea() {
	}
	
	@Override
	protected void paintChildren(Graphics g) {
		for (Painter painter : painters) {
			painter.paint(g);
		}
	}

	public void addPainter(Painter painter) {
		painters.add(painter);
	}
}
