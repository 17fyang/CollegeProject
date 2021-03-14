package softwareTest.mockTesting;

public class OutHotel extends Empty implements OutHotelInterface{
	MainRun mr = new MainRun();
	private IData iData=null;
	public OutHotel() {}
	public OutHotel(IData iData) {
		this.iData=iData;
	}
    public String out(int roomNo){
    	return iData.in_Out_Room(roomNo, HotelConstant.EMPTY);
    	
//        if(isEmpty(roomNo)){
//            return "该房间没有客人入住，退房失败！";
//        }else{
//            mr.getRoom()[roomNo/100-1][roomNo%100-1]="EMPTY";
//            return roomNo+"退房成功!";
//        }
    }
}
