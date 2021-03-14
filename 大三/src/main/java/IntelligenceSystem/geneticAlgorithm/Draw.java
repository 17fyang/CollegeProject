package IntelligenceSystem.geneticAlgorithm;

import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.BasicStroke;  
import java.awt.Color;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartFrame;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.axis.NumberAxis;  
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;  

/*
 * 画图类
 */
public class Draw {
	CLS cls=new CLS();
	XYSeries series=new XYSeries("test");

	//传入group数据，转换数据格式
	public double[][] dataTransfer(String[] group){
		double result[][]=new double[2][group.length];
		for(int i=0;i<group.length;i++) {
			String str=group[i];
			double d[]=cls.decode(str);
			result[0][i]=d[0];
			result[1][i]=d[1];
		}
		return result;
	}
	
	//折线图(传入group数组)
		public  void addGroupData(int x,String[] group) {
			double result=0;
			double[] fit=cls.fitAll(group);
			for(int i=0;i<fit.length;i++) 		result+=fit[i];
			series.add(x,result);
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
	public void showChart(double[][] data) throws Exception {  

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
