package tr.fractal.simple;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static final int FRAME_WIDTH = 1024;
    public static final int FRAME_HEIGHT = 720;
    protected static final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
	private JPanel panel;
    
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
		panel = new PaintingArea(new MandelbrotPlainFractalPainter(new DefaultCoordsPainter()));
		getContentPane().add(panel);
	}

	public static void main(String[] args) {
		new Main();
	}

}
