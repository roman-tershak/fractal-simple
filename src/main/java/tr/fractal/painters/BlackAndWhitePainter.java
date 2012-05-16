package tr.fractal.painters;

import java.awt.Graphics;
import java.awt.Graphics2D;

import tr.fractal.math.FractalCalculator;
import tr.fractal.ui.PaintingArea;

public class BlackAndWhitePainter implements Painter {

	private final FractalCalculator fractalCalculator;

	public BlackAndWhitePainter(FractalCalculator fractalCalculator) {
		this.fractalCalculator = fractalCalculator;
	}

	public void paintOn(PaintingArea paintingArea, Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		int height = paintingArea.getHeight();
		int width = paintingArea.getWidth();
		
        int granularity = (height < width ? height : width);
		fractalCalculator.setGranularity(granularity, granularity);

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
