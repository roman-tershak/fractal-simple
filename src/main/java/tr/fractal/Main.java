package tr.fractal;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tr.fractal.math.Complex;
import tr.fractal.math.FractalCalculator;
import tr.fractal.math.FractalCalculatorImpl;
import tr.fractal.math.MandelbrotFormula;
import tr.fractal.painters.BlackAndWhitePainter;
import tr.fractal.painters.impl.DefaultCoordsPainter;
import tr.fractal.painters.impl.MandelbrotPlainFractalPainter;
import tr.fractal.ui.PaintingArea;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static final int FRAME_WIDTH = 1024;
    public static final int FRAME_HEIGHT = 720;
    protected static final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
	private JPanel panel;

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
//        initEventSubsystem();
        
        setVisible(true);
    }
	
	private void initContentPane() {
		fractalCalculator = new FractalCalculatorImpl(new MandelbrotFormula());
		
		fractalCalculator.setArea(new Complex(-2, -2), new Complex(2, 2));
		fractalCalculator.setMaxIterations(1000);
		
		panel = new PaintingArea(new BlackAndWhitePainter(fractalCalculator));
		getContentPane().add(panel);
	}

	public static void main(String[] args) {
		new Main();
	}

}
