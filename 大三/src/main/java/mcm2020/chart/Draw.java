package mcm2020.chart;


import java.util.UUID;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Draw {
	
	public static XYSeries getSeries(Data[] data,String side) {
		XYSeries series=new XYSeries(UUID.randomUUID());
		for(int i=0;i<data.length;i++) {
			if(side.equals("away")) {
				series.add(data[i].getEventDestination_y(), data[i].getEventDestination_x());
			}else if(side.equals("home")) {
				series.add(100-data[i].getEventDestination_y(),100- data[i].getEventDestination_x());
			}
		}
		return series;
	}
	
	//根据传进来的xy坐标数据画图
	public void drawData(XYSeries series[],String name) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		for(int i=0;i<series.length;i++)	dataset.addSeries(series[i]);
		JFreeChart chart = ChartFactory.createXYLineChart(
				" ", // chart title
				"x", // x axis label
				" y", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL,
				false, // include legend
				false, // tooltips
				false // urls
				);
		ChartFrame frame = new ChartFrame(name, chart);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
}
