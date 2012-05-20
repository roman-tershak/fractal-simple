package tr.fractal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tr.fractal.math.Complex;
import tr.fractal.math.MandelbrotFormula;
import tr.fractal.painters.ColoredPainter;
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
//		paintingArea.setPainter(new BlackAndWhitePainter(fractalCalculator));
		paintingArea.setPainter(new ColoredPainter(fractalCalculator));
		
		getContentPane().add(paintingArea, BorderLayout.CENTER);
		
        JPanel statusBar = new JPanel(new GridLayout(1, 3));
        statusBar.setBackground(Color.LIGHT_GRAY);
        statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
        
        JLabel statusAreaSize = new JLabel(fractalCalculator.getArea().toShortString());
		statusBar.add("statusAreaSize", statusAreaSize);
		
		JLabel statusMaxIter = new JLabel(String.valueOf(fractalCalculator.getMaxIterations()));
		statusBar.add("statusMaxIter", statusMaxIter);
        
		JLabel statusCurrIter = new JLabel();
		statusBar.add("statusCurrIter", statusCurrIter);
		
        getContentPane().add(statusBar, BorderLayout.SOUTH);

	}

	private void initEventSubsystem() {
        final Container contentPane = getContentPane();
        contentPane.setFocusable(true);
        
        EventProcessor eventProcessor = new EventProcessor(contentPane, paintingArea, fractalCalculator);
		paintingArea.addMouseListener(eventProcessor);
        paintingArea.addMouseMotionListener(eventProcessor);
//        contentPane.addMouseWheelListener(eventAdapter);
        
        contentPane.addKeyListener(eventProcessor);
	}

	public static void main(String[] args) {
		new Main();
	}

}
