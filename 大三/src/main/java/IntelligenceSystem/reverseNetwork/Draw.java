package IntelligenceSystem.reverseNetwork;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Draw {
	public final static double resultThreshold[]=Main.resultThreshold;
	XYSeries errorSeries=new XYSeries("error");
	List<XYSeries[]> seriesList=new LinkedList<XYSeries[]>();
	public Draw() {
		//图表初始化
		for(int i=0;i<resultThreshold.length;i++) {
			XYSeries series[]=new XYSeries[2];
			series[0]=new XYSeries("00");
			series[1]=new XYSeries("11");
			seriesList.add(series);
		}
	}
	//统计误差数据
	public void addErrorData(int x,List<double[]> errorList) {
		double errorSum=getErrorSum(errorList);
		errorSeries.add(x, errorSum);
	}
	//统计测试结果数据
	public void addTestData(int x,double[] testResult) {
		for(int i=0;i<seriesList.size();i++) {
			seriesList.get(i)[0].add(x, testResult[i]);
			seriesList.get(i)[1].add(x, resultThreshold[i]);
		}
	}
	//绘制误差表
	public void drawErrorChart() {
		XYSeries[] seriesArr= {errorSeries};
		drawData(seriesArr,"error");
	}
	//绘制测试结果表
	public void drawTestChart() {
		for(int i=0;i<seriesList.size();i++) {
			XYSeries[] seriesArr= seriesList.get(i);
			drawData(seriesArr,"result"+i);
		}
	}
	//绘制两个表
	public void drawAllChart() {
		drawErrorChart();
		drawTestChart();
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
	
	private double getErrorSum(List<double[]> errorList) {
		double sum=0;
		for(int i=0;i<errorList.size();i++) {
			double errorDouble[]=errorList.get(i);
			for(int j=0;j<errorDouble.length;j++) {
				sum+=errorDouble[j]*errorDouble[j];
			}
		}
		return sum;
	}
	
}
