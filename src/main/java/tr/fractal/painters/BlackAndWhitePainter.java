package tr.fractal.painters;

import java.awt.Graphics;
import java.awt.Graphics2D;

import tr.fractal.math.Complex;
import tr.fractal.math.FractalCalculator;
import tr.fractal.math.FractalCalculatorImpl;
import tr.fractal.math.MandelbrotFormula;
import tr.fractal.ui.PaintingArea;

public class BlackAndWhitePainter implements Painter {

	public void paintOn(PaintingArea paintingArea, Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		int height = paintingArea.getHeight();
		int width = paintingArea.getWidth();
		
        FractalCalculator fractalCalculator = new FractalCalculatorImpl(new MandelbrotFormula());
        fractalCalculator.setArea(new Complex(-2, -2), new Complex(2, 2));
        int granularity = (height < width ? height : width);
		fractalCalculator.setGranularity(granularity , granularity);
        fractalCalculator.setMaxIterations(1000);

        int[][] m = fractalCalculator.calculate();
        
        for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if (m[i][j] >= Integer.MAX_VALUE) {
					
					g2d.drawLine(i, j, i, j);
				}
			}
		}
        

	}

}
