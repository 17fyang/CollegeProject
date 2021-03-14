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
//            return "璇ユ埧闂存病鏈夊浜哄叆浣忥紝閫�鎴垮け璐ワ紒";
//        }else{
//            mr.getRoom()[roomNo/100-1][roomNo%100-1]="EMPTY";
//            return roomNo+"閫�鎴挎垚鍔�!";
//        }
    }
}
