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
import tr.fractal.ui.PaintingArea;

public class EventProcessor extends MouseAdapter implements KeyListener {
	private static final double MOVE_AREA_DELTA = 0.02;
	
	private final Container contentPane;
	private final PaintingArea paintingArea;
	private final FractalCalculator fractalCalculator;
	private final StatusBar statusBar;

	private Timer timer;

	private double zoomingRatio;

	public EventProcessor(Container contentPane, PaintingArea paintingArea, 
			FractalCalculator fractalCalculator, StatusBar statusBar) {
		this.contentPane = contentPane;
		this.paintingArea = paintingArea;
		this.fractalCalculator = fractalCalculator;
		this.statusBar = statusBar;
	}

	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			zoomingRatio = 0.95;
		} else {
			zoomingRatio = 1.05;
		}
		doZooming(e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
//		System.out.println("mouseDragged");
		doZooming(e);
	}

	public void mouseReleased(MouseEvent e) {
//		System.out.println("mouseReleased");
		stopZooming();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		int mx = e.getX();
		int my = paintingArea.getHeight() - e.getY();
		
		statusBar.currentItem(mx, my);
	}

	private void doZooming(MouseEvent e) {
		final int mx = e.getX();
		final int my = e.getY();
		
		stopZooming();
		
		timer = new Timer("", true);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				zoomInOut(mx, my, zoomingRatio);
			}
		}, 100, 100);
	}

	private void stopZooming() {
		if (timer != null) {
			timer.cancel();
		}
	}

	private void zoomInOut(int mx, int my, double ratio) {
		
		Complex v1 = fractalCalculator.getArea().getV1();
		Complex v2 = fractalCalculator.getArea().getV2();
		
		Complex vc = convertFromPx(mx, my);
		
		ComplexVector vect1new = new ComplexVector(vc, v1).mul(ratio);
		ComplexVector vect2new = new ComplexVector(vc, v2).mul(ratio);
		
		ComplexVector vectNew = new ComplexVector(vect1new.getV2(), vect2new.getV2());
		fractalCalculator.setArea(vectNew);
		
		statusBar.fractalChanged();
		
		contentPane.repaint();
	}

	private Complex convertFromPx(int mx, int my) {
		Complex v1 = fractalCalculator.getArea().getV1();
		Complex v2 = fractalCalculator.getArea().getV2();
		
		int width = paintingArea.getWidth();
		int height = paintingArea.getHeight();
		
		double xr = (v2.getA() - v1.getA()) / width;
		double yr = (v2.getB() - v1.getB()) / height;
		
		Complex vc = new Complex(v1.getA() + mx * xr, v1.getB() + (height - my) * yr);
		return vc;
	}

	public void keyTyped(KeyEvent e) {
		char keyChar = e.getKeyChar();
		int currMaxIter = fractalCalculator.getMaxIterations();
		int nextMaxIter = currMaxIter;
		switch (keyChar) {
		case '<':
			nextMaxIter = (int) (currMaxIter * 0.95);
			break;
		case '>':
			nextMaxIter = (int) (currMaxIter * 1.05);
			break;
		}
		if (nextMaxIter != currMaxIter) {
			fractalCalculator.setMaxIterations(nextMaxIter);
			statusBar.fractalChanged();
			contentPane.repaint();
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

	public void keyReleased(KeyEvent e) {
	}

	private void moveArea(double a, double b) {
		ComplexVector fractalArea = fractalCalculator.getArea();
		double modulus = fractalArea.getMod();
		ComplexVector newVector = fractalArea.add(new Complex(a * modulus, b * modulus));
		fractalCalculator.setArea(newVector);
		statusBar.fractalChanged();
		contentPane.repaint();
	}
}
