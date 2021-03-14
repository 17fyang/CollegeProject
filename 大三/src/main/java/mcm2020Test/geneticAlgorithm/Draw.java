package mcm2020Test.geneticAlgorithm;

import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.BasicStroke;  
import java.awt.Color;
import java.util.LinkedList;
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

/*
 * ��ͼ��
 */
public class Draw {
	private int number=50;
	CLS cls=new CLS();
	XYSeries serie=new XYSeries("test",false,true);
	double radius=CLS.radius;
	
	
	
	
	public void DrawGroup(String group[]) {
		XYSeries series[]=new XYSeries[group.length];
		for(int i=0;i<series.length;i++) {
			double[] xy=cls.decode(group[i]);
			series[i]=this.getCircle(xy[0], xy[1], radius);
		}
		this.drawCircle(series);
	}
	
	public XYSeries getCircle(double x,double y,double radius){
		XYSeries series=new XYSeries("test"+x+"."+y,false,true);
		for(int i=0;i<number;i++) {
			double xx= Math.sin(i*1.0/number*2*Math.PI)*radius+x;
			double yy= Math.cos(i*1.0/number*2*Math.PI)*radius+y;
			series.add(xx, yy);
		}
		return series;
	}
	
	public void drawCircle(XYSeries[] series) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		for(int i=0;i<series.length;i++)			dataset.addSeries(series[i]);
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
		
		ChartFrame frame = new ChartFrame("testName", chart);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
	//����group���ݣ�ת�����ݸ�ʽ
	public void dataTransfer(String[] group){
		double result[][]=new double[2][group.length];
		for(int i=0;i<group.length;i++) {
			String str=group[i];
			double d[]=cls.decode(str);
			result[0][i]=d[0];
			result[1][i]=d[1];
		}
		
		try {
			this.showChart(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//����ͼ(����group����)
		public  void addGroupData(int x,String[] group) {
			double result=0;
			double[] fit=cls.fitAll(group).fitness;
			for(int i=0;i<fit.length;i++) 		result+=fit[i];
			serie.add(x,result);
		}
	//����ͼ
	public void drawLine(String name) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(serie);
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

	//ɢ��ͼ
	public void showChart(double[][] data) throws Exception {  
		DefaultXYDataset xydataset = new DefaultXYDataset();  
		xydataset.addSeries("Charging Pile Position", data);  
		JFreeChart chart = ChartFactory.createScatterPlot("Driving record", "x-axis", "y-axis",
				xydataset, 
				PlotOrientation.VERTICAL,
				true, 
				false,
				false);  
		ChartFrame frame = new ChartFrame("ɢ��ͼ", chart, true);  
		chart.setBackgroundPaint(Color.white);    
		chart.setBorderPaint(Color.GREEN);    
		chart.setBorderStroke(new BasicStroke(1.5f));    
		XYPlot xyplot = (XYPlot) chart.getPlot();    

		xyplot.setBackgroundPaint(new Color(255, 253, 246));    
		ValueAxis vaaxis = xyplot.getDomainAxis();    
		vaaxis.setAxisLineStroke(new BasicStroke(1.5f));    

		ValueAxis va = xyplot.getDomainAxis(0);    
		va.setAxisLineStroke(new BasicStroke(1.5f));    

		va.setAxisLineStroke(new BasicStroke(1.5f)); // �������ϸ    
		va.setAxisLinePaint(new Color(215, 215, 215)); // ��������ɫ    
		xyplot.setOutlineStroke(new BasicStroke(1.5f)); // �߿��ϸ    
		va.setLabelPaint(new Color(10, 10, 10)); // �����������ɫ    
		va.setTickLabelPaint(new Color(102, 102, 102)); // ��������ֵ��ɫ    
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
		xylineandshaperenderer.setSeriesOutlineStroke(0, new BasicStroke(2.5F));//���õ��С

		frame.pack();  
		frame.setVisible(true);  
	} 
}
