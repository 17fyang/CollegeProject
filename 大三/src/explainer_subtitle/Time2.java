package explainer_subtitle;

public class Time2 {
	private int hour;
	private int minute;
	private int second;
	private int msecond;
	public Time2() {}
	public Time2(int hour,int minute,int second,int msecond) {
		this.hour=hour;
		this.minute=minute;
		this.second=second;
		this.msecond=msecond;
	}
	
	
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	public int getMsecond() {
		return msecond;
	}
	public void setMsecond(int msecond) {
		this.msecond = msecond;
	}
	
}
