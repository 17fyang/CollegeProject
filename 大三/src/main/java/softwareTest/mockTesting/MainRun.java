package softwareTest.mockTesting;

import java.util.Scanner;

public class MainRun implements  MainRunInterface{
	private static String[][] rooms;
	private static ListHome lh=new ListHome();
	private static InHotel ih=new InHotel();
	private static OutHotel oh=new OutHotel();
	public MainRun() {}
	public MainRun(ListHome lh,InHotel ih,OutHotel oh) {
		this.lh=lh;
		this.ih=ih;
		this.oh=oh;
	}
	
//	public static void main(String[] args) {
//		iniRooms();
//		command();
//	}
	public void iniRooms() {
		rooms=new String[10][12];
		for(int i=0;i<rooms.length;i++) {
			for(int j=0;j<rooms[0].length;j++) {
				rooms[i][j]="EMPTY";
			}
		}
	}
	public void command() {
		String comm;
		while(true) {
			System.out.println("请输入命令：");
			Scanner sca=new Scanner(System.in);
			System.gc();
			comm=sca.next();
			if("search".equalsIgnoreCase(comm)) {
				lh.search();
			}else if("in".equalsIgnoreCase(comm)) {
				int roomNo=sca.nextInt();
				try {
					if(validRoomNo(roomNo)) {
						String name=sca.next();
						System.out.println(ih.in(roomNo,name));
					}else
						System.out.println("房间号出错");
				}catch(Exception e) {
					System.out.println("房间号出错！");
				}
			}else if("out".equalsIgnoreCase(comm)) {
				int roomNo=sca.nextInt();
				if(validRoomNo(roomNo)) {
					System.out.println(oh.out(roomNo));
				}else
					System.out.println("房间号出错！");
			}else if("exit".equalsIgnoreCase(comm)) {
				System.out.println("程序退出！");
				break;
			}else {
				System.out.println("命令输入错误，请重新输入！");
			}
		}
	}
	
	public   boolean validRoomNo(int roomNo) {
		if((roomNo/100>10) || (roomNo/100<1 || roomNo%100>12) || (roomNo%100<1))		return false;
		else		return true;
	}
	
	public  String[][] getRoom(){
		return rooms;
	}
}
