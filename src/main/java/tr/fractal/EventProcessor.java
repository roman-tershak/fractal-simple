package tr.fractal;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import tr.fractal.math.Complex;
import tr.fractal.math.ComplexVector;
import tr.fractal.math.FractalCalculator;
import tr.fractal.ui.PaintingArea;

public class EventProcessor extends MouseAdapter implements KeyListener {
	private static final double MOVE_AREA_DELTA = 0.02;
	
	private final Container contentPane;
	private final PaintingArea paintingArea;
	private final FractalCalculator fractalCalculator;

	private Timer timer;

	public EventProcessor(Container contentPane, PaintingArea paintingArea, 
			FractalCalculator fractalCalculator) {
		this.contentPane = contentPane;
		this.paintingArea = paintingArea;
		this.fractalCalculator = fractalCalculator;
	}

	public void mousePressed(MouseEvent e) {
		final int mx = e.getX();
		final int my = e.getY();
		
		final double ratio;
		if (e.getButton() == MouseEvent.BUTTON1) {
			ratio = 0.95;
		} else {
			ratio = 1.05;
		}
		
		if (timer != null) {
			timer.cancel();
		}
		
		timer = new Timer("", true);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				zoomInOut(mx, my, ratio);
			}
		}, 100, 100);
		System.out.println("Scheduled");
	}

	public void mouseReleased(MouseEvent e) {
		if (timer != null) {
			timer.cancel();
			System.out.println("Cancelled");
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
	}
	
	private void zoomInOut(int mx, int my, double ratio) {
		
		System.out.println("Zoomed");
		
		Complex v1 = fractalCalculator.getArea().getV1();
		Complex v2 = fractalCalculator.getArea().getV2();
		
		int width = paintingArea.getWidth();
		int height = paintingArea.getHeight();
		
		double xr = (v2.getA() - v1.getA()) / width;
		double yr = (v2.getB() - v1.getB()) / height;
		
		Complex vc = new Complex(v1.getA() + mx * xr, v1.getB() + (height - my) * yr);
		
		ComplexVector vect1 = new ComplexVector(vc, v1);
		ComplexVector vect2 = new ComplexVector(vc, v2);
		
		ComplexVector vect1new;
		ComplexVector vect2new;
		
		vect1new = vect1.mul(ratio);
		vect2new = vect2.mul(ratio);
		
		fractalCalculator.setArea(vect1new.getV2(), vect2new.getV2());
		
		contentPane.repaint();
	}

	public void keyTyped(KeyEvent e) {
		char keyChar = e.getKeyChar();
		switch (keyChar) {
		case '<':
			fractalCalculator.setMaxIterations((int) (fractalCalculator.getMaxIterations() * 0.95));
			contentPane.repaint();
			break;
		case '>':
			fractalCalculator.setMaxIterations((int) (fractalCalculator.getMaxIterations() * 1.05));
			contentPane.repaint();
			break;
		}
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			moveArea(-MOVE_AREA_DELTA, 0.0);
			break;
		case KeyEvent.VK_RIGHT:
			moveArea(MOVE_AREA_DELTA, 0.0);
			break;
		case KeyEvent.VK_UP:
			moveArea(0.0, MOVE_AREA_DELTA);
			break;
		case KeyEvent.VK_DOWN:
			moveArea(0.0, -MOVE_AREA_DELTA);
			break;
		}
	}

	private void moveArea(double a, double b) {
		ComplexVector fractalArea = fractalCalculator.getArea();
		double modulus = fractalArea.getMod();
		fractalCalculator.setArea(fractalArea.add(new Complex(a * modulus, b * modulus)));
		contentPane.repaint();
	}

	public void keyReleased(KeyEvent e) {
	}
}