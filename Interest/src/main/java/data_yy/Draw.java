package data_yy;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Draw {
	XYSeries series=new XYSeries("test");
	List<double[]> dataList=new ArrayList<double[]>();
	public void addOneResult(double x, double y) {
		double[] d=new double[2];
		d[0]=x;
		d[1]=y;
		dataList.add(d);
	}
	public void addAllResult(double[] x, double[] y) {
		if(x.length!=y.length)	System.out.println("error!,x,y数组长度不一致");
		for(int i=0;i<x.length;i++) {
			double[] d=new double[2];
			d[0]=x[i];
			d[1]=y[i];
			dataList.add(d);
		}
	}
	public void addOneResult(int x, double y) {
		addOneResult((double)x,y);
	}
	public void addAllResult(int[] x, double[] y) {
		if(x.length!=y.length)	System.out.println("error!,x,y数组长度不一致");
		for(int i=0;i<x.length;i++) 	addOneResult(x[i],y[i]);
	}
	
	
	//折线图
	public void drawLine(String name) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
//		for(int i=0;i<series.length;i++)	dataset.addSeries(series[i]);
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

	//散点图
	public void showChart() throws Exception {  
		double data[][]=new double[2][dataList.size()];
		for(int i=0;i<dataList.size();i++) {
			data[0][i]=dataList.get(i)[0];
			data[1][i]=dataList.get(i)[1];
		}
		
		
		DefaultXYDataset xydataset = new DefaultXYDataset();  
		xydataset.addSeries("car position", data);  
		JFreeChart chart = ChartFactory.createScatterPlot("Driving record", "longitude", "latitude",
				xydataset, 
				PlotOrientation.VERTICAL,
				true, 
				false,
				false);  
		ChartFrame frame = new ChartFrame("散点图", chart, true);  
		chart.setBackgroundPaint(Color.white);    
		chart.setBorderPaint(Color.GREEN);    
		chart.setBorderStroke(new BasicStroke(1.5f));    
		XYPlot xyplot = (XYPlot) chart.getPlot();    
		xyplot.setBackgroundPaint(new Color(255, 253, 246));    
		ValueAxis vaaxis = xyplot.getDomainAxis();    
		vaaxis.setAxisLineStroke(new BasicStroke(1.5f));    
		ValueAxis va = xyplot.getDomainAxis(0);    
		va.setAxisLineStroke(new BasicStroke(1.5f));    
		va.setAxisLineStroke(new BasicStroke(1.5f)); // 坐标轴粗细    
		va.setAxisLinePaint(new Color(215, 215, 215)); // 坐标轴颜色    
		xyplot.setOutlineStroke(new BasicStroke(1.5f)); // 边框粗细    
		va.setLabelPaint(new Color(10, 10, 10)); // 坐标轴标题颜色    
		va.setTickLabelPaint(new Color(102, 102, 102)); // 坐标轴标尺值颜色    
		ValueAxis axis = xyplot.getRangeAxis();    
		axis.setAxisLineStroke(new BasicStroke(1.5f));    
		XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer) xyplot    
				.getRenderer();    
		xylineandshaperenderer.setSeriesOutlinePaint(0, Color.WHITE); 
		xylineandshaperenderer.setUseOutlinePaint(true);    
		NumberAxis numberaxis = (NumberAxis) xyplot.getDomainAxis();    
		numberaxis.setAutoRangeIncludesZero(false);    
		numberaxis.setTickMarkInsideLength(2.0F);    
		numberaxis.setTickMarkOutsideLength(0.0F);    
		numberaxis.setAxisLineStroke(new BasicStroke(1.5f));    
		
		
		xylineandshaperenderer.setSeriesOutlineStroke(0, new BasicStroke(2.5F));//设置点大小

		frame.pack();  
		frame.setVisible(true);  
	}
}
