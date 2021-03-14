package IntelligenceSystem.reverseNetwork;

public class Neuron {
	private double threshold;
	private double[] before;
	private double[] after;
	
	
	public double getThreshold() {
		return threshold;
	}
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	public double[] getBefore() {
		return before;
	}
	public void setBefore(double[] before) {
		this.before = before;
	}
	public double[] getAfter() {
		return after;
	}
	public void setAfter(double after[]) {
		this.after = after;
	}
}
