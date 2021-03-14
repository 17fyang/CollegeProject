package softwareTest.mockTesting;

public class InHotel extends Empty implements InHotelInterface{
//    private int room;
    MainRun mr =null;
    
    IData iData;
    public InHotel() {}
    public InHotel(MainRun mr) {
    	this.mr=mr;
    }
    public InHotel(IData idata) {
    	this.iData=idata;
    }
    public String in(int roomNo, String name) {

    	return iData.in_Out_Room(roomNo, name);
    	
//        room = roomNo % 100 - 1;
//        if (isEmpty(roomNo)) {
//            mr.getRoom()[roomNo / 100 - 1][room] = name;
//            return name = "成功入住" + roomNo + "房间!";
//        } else {
//            return "该房间已经有客人入住!";
//        }
    }

    public boolean isEmpty(int roomNo) {
        String room = mr.getRoom()[roomNo / 100 - 1][roomNo % 100 - 1];
        if("EMPTY".equals(room)){
            return true;
        }else{
            return false;
        }
    }
}
