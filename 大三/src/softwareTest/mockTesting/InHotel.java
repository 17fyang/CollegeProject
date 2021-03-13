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
//            return name = "�ɹ���ס" + roomNo + "����!";
//        } else {
//            return "�÷����Ѿ��п�����ס!";
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
