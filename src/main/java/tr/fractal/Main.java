package tr.fractal;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tr.fractal.math.Complex;
import tr.fractal.math.ComplexVector;
import tr.fractal.math.FractalCalculator;
import tr.fractal.math.FractalCalculatorImpl;
import tr.fractal.math.MandelbrotFormula;
import tr.fractal.painters.BlackAndWhitePainter;
import tr.fractal.painters.Painter;
import tr.fractal.ui.PaintingArea;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static final int FRAME_WIDTH = 1024;
    public static final int FRAME_HEIGHT = 720;
    public static final double v1a = -2.0;
    public static final double v1b = -2.0 * (double) FRAME_HEIGHT / FRAME_WIDTH;
    public static final double v2a = -v1a;
    public static final double v2b = -v1b;
    
    protected static final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
	private PaintingArea paintingArea;

	private FractalCalculator fractalCalculator;
    
	public Main() {
		super();
		initializeUI();
	}
	
    private void initializeUI() {
        setTitle("Fractal Viewer");
        setSize(FRAME_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        initContentPane();
        initEventSubsystem();
        
        setVisible(true);
    }
	
	private void initContentPane() {
		fractalCalculator = new FractalCalculatorImpl(new MandelbrotFormula());
		
		fractalCalculator.setArea(new Complex(v1a, v1b), new Complex(v2a, v2b));
		fractalCalculator.setMaxIterations(100);
		
		paintingArea = new PaintingArea();
		paintingArea.setPainter(new BlackAndWhitePainter(fractalCalculator));
		
		getContentPane().add(paintingArea);
	}

	private void initEventSubsystem() {
        final Container contentPane = getContentPane();
        contentPane.setFocusable(true);
        
        MouseAdapter mouseAdapter = new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.println("mousePressed");
				
				int mx = e.getX();
				int my = e.getY();
				
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
				
				if (e.getButton() == MouseEvent.BUTTON1) {
					vect1new = vect1.mul(0.95);
					vect2new = vect2.mul(0.95);
				} else {
					vect1new = vect1.mul(1.05);
					vect2new = vect2.mul(1.05);
				}
				
				fractalCalculator.setArea(vect1new.getV2(), vect2new.getV2());
				
				contentPane.repaint();
			}
			public void mouseReleased(MouseEvent e) {
				System.out.println("mouseReleased");
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println("mouseDragged");
			}
		};
		contentPane.addMouseListener(mouseAdapter);
        contentPane.addMouseMotionListener(mouseAdapter);
//        contentPane.addMouseWheelListener(eventAdapter);
	}

	public static void main(String[] args) {
		new Main();
	}

}
