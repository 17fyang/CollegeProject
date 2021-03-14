package explainer_subtitle;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class Print extends JFrame{
	private JTextArea ta=null;
	public Print() {
        JPanel jp=new JPanel();
		jp.setLayout(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("×ÖÄ»Êä³ö");
		this.setResizable(false);
		this.setBounds(200, 30, 800, 680);
		this.setVisible(true);
		
		ta = new JTextArea();
		ta.setFont(new Font("ËÎÌå",Font.BOLD,25));
		ta.setBackground(jp.getBackground());
		ta.setBounds(150, 480, 450, 80);
		
		jp.add(ta);
		this.add(jp);
		validate();
    }

	public void draw(Subt[] subt) throws Exception {
		Time2 lastEndTime=new Time2(0,0,0,0);
		for(int i=0;i<subt.length;i++) {
			int sleepTime=getSleepTime(lastEndTime,subt[i].getBegin());
			this.ta.setText("");
			validate();
			Thread.sleep(sleepTime);
			sleepTime=getSleepTime(subt[i].getBegin(),subt[i].getEnd());
			this.ta.setText(subt[i].getContent());
			validate();
			Thread.sleep(sleepTime);
		}
	}

	private int getSleepTime(Time2 begin, Time2 end) {
		int startTime=begin.getHour()*60*60*1000+begin.getMinute()*60*100
							+begin.getSecond()*1000+begin.getMsecond();
		int endTime=end.getHour()*60*60*1000+end.getMinute()*60*100
				+end.getSecond()*1000+end.getMsecond();
		return endTime-startTime;
	}

	
}
