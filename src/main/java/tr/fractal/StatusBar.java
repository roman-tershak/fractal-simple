package tr.fractal;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final FractalCalculator fractalCalculator;

	private JLabel statusText;

	private int currMy;
	private int currMx;
	
	StatusBar(FractalCalculator fractalCalculator) {
		super(new GridLayout(1, 3));
		
		this.fractalCalculator = fractalCalculator;
		
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		
		statusText = new JLabel();
		this.add(statusText);
		
		updateStatus();
	}

	public void currentItem(int mx, int my) {
		this.currMx = mx;
		this.currMy = my;
		
		updateStatus();
	}

	public void fractalChanged() {
		updateStatus();
	}
	
	private void updateStatus() {
		String areaTxt = fractalCalculator.getArea().toShortString();
		int maxIterations = fractalCalculator.getMaxIterations();
		int currN = fractalCalculator.getMatrixItem(currMx, currMy);
		
		float h = 0.66F - (float) Math.sqrt((double) currN / 10000);
		float s;
		if (currN <= 1) {
			s = 1;
		} else {
			s = (float) 1 / currN + 0.5F;
		}
		float b = (float) ((float) Math.atan(currN) / Math.PI * 1.3);
		
		statusText.setText(areaTxt + "    max iterations = " + maxIterations + "    " + currN +
				"   h=" + h + "   s=" + s + "   b=" + b);
	}
}