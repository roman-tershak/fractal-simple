package tr.fractal;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private final FractalCalculator fractalCalculator;
	
	StatusBar(FractalCalculator fractalCalculator) {
		super(new GridLayout(1, 3));
		
		this.fractalCalculator = fractalCalculator;
		
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		
		JLabel statusAreaSize = new JLabel(fractalCalculator.getArea().toShortString());
		this.add("statusAreaSize", statusAreaSize);
		
		JLabel statusMaxIter = new JLabel(String.valueOf(fractalCalculator.getMaxIterations()));
		this.add("statusMaxIter", statusMaxIter);
		
		JLabel statusCurrIter = new JLabel();
		this.add("statusCurrIter", statusCurrIter);
	}
}