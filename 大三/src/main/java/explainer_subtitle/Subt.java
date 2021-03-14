package explainer_subtitle;


public class Subt {
	private int sid;
	private Time2 begin;
	private Time2 end;
	private String content;
	public Subt() {}
	public Subt(int sid,Time2 begin,Time2 end,String content) {
		this.sid=sid;
		this.begin=begin;
		this.end=end;
		this.content=content;
	}
	
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public Time2 getBegin() {
		return begin;
	}
	public void setBegin(Time2 begin) {
		this.begin = begin;
	}
	public Time2 getEnd() {
		return end;
	}
	public void setEnd(Time2 end) {
		this.end = end;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
