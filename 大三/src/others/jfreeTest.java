package others;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class jfreeTest {
	public static void main(String[] args) {
		XYSeries series = new XYSeries("xySeries");
		for (int x = -100; x < 100; x++) {
			int y = x*x;
			series.add(x, y);
		}
		
		XYSeries series2 = new XYSeries("xy6Series");
		for (int x = -100; x < 100; x++) {
			int y = x;
			series.add(x, y);
		}
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series2);
		dataset.addSeries(series);
		JFreeChart chart = ChartFactory.createXYLineChart(
				"y = sin(x)", // chart title
				"x", // x axis label
				"sin(x)", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL,
				false, // include legend
				false, // tooltips
				false // urls
				);
		ChartFrame frame = new ChartFrame("my picture", chart);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
