package tr.fractal.painters;

import java.awt.Graphics;

import javax.swing.JPanel;


public interface Painter {
	
	public void setPaintArea(JPanel jPanel);

	public void paint(Graphics g);
}
