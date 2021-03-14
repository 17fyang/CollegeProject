package softwareTest.mockTesting;

public class DP implements IData{
	private static String[][] rooms;
	MainRun mr=new MainRun();
	@Override
	public void iniRooms() {
		rooms=new String[HotelConstant.HOTEL_TOTAL_FLOOR][HotelConstant.HOTEL_ROOMS_EVERYFLOOR];
		for(int i=0;i<rooms.length;i++) {
			for(int j=0;j<rooms[0].length;j++) {
				rooms[i][j]=HotelConstant.EMPTY;
			}
		}
	}

	@Override
	public String in_Out_Room(int roomNo, String name) {
		if(name.equals(HotelConstant.EMPTY)) {
			if(roomNo(roomNo)==0) {
				rooms[(roomNo/100)-1][(roomNo%100)-1]=name;
				return roomNo+"退房成功！";
			}else
				return "该房间没有客人入住，退房失败！";
		}else {
			if(roomNo(roomNo)==0)
				return "该房间已经有客人入住！";
			else {
				rooms[(roomNo/100)-1][(roomNo%100)-1]=name;
				return name+"成功入住！"+roomNo+"房间！";
			}
		}
	}

	@Override
	public String getStation(int roomNo) {
		return rooms[(roomNo/100)-1][(roomNo%100)-1];
	}

	
	public int roomNo(int roomNo) {
		if(HotelConstant.EMPTY.equals(rooms[(roomNo/100)-1][(roomNo%100)-1]))		return roomNo;
		else 	return 0;
	}
}
