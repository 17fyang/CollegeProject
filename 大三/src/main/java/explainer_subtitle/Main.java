package explainer_subtitle;

public class Main {
	public static final String path="file/subtitle.srt";
	public static void main(String[] args) {
		Read r=new Read();
		Subt subt[]=r.read(path);
		printToConsole(subt);
		printToFrame(subt);
		
	}
	private static void printToConsole(Subt[] subt) {
		for(int i=0;i<subt.length;i++) {
			System.out.println(SubtToString(subt[i]));
		}
	}
	private static void printToFrame(Subt[] subt) {
		Print p=new Print();
		try {
			p.draw(subt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String SubtToString(Subt subt) {
		StringBuilder sb=new StringBuilder();
		
		sb.append("编号："+subt.getSid()+"\r\n");
		String s1=TimeToString(subt.getBegin());
		String s2=TimeToString(subt.getEnd());
		sb.append("时间："+s1+" to "+s2+"\r\n");
		String temp[]=subt.getContent().split("\r\n");
		String content="";
		for(int i=0;i<temp.length;i++) content=content.concat(temp[i]);
		sb.append("内容："+content+"\r\n");
		return sb.toString();
	}
	private static String TimeToString(Time2 time) {
		StringBuilder sb=new StringBuilder();
		sb.append(time.getHour()+"时");
		sb.append(time.getMinute()+"分");
		sb.append(time.getSecond()+"秒");
		sb.append(time.getMsecond()+"`");
		return sb.toString();
		
	}
	
}
