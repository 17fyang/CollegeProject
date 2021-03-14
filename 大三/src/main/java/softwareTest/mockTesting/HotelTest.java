package softwareTest.mockTesting;
import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test; 

public class HotelTest {
	private Mockery context=new Mockery();
//	@Test
//	@Ignore
	public void OutHotelTest() {
		IData iData=context.mock(IData.class);
		OutHotel oh=new OutHotel(iData);
		context.checking(new Expectations() {{
			oneOf(iData).in_Out_Room(701,"EMPTY");
			will(returnValue("701�˷��ɹ���"));
		}});
		assertEquals("701�˷��ɹ���",oh.out(701));
	}
	
	@Test
	public void InHotelTest() {
		IData iData=context.mock(IData.class);
		InHotel ih=new InHotel(iData);
		context.checking(new Expectations() {{
			oneOf(iData).in_Out_Room(405,"test");
			will(returnValue("test�ɹ���ס405����"));
		}});
		assertEquals("test�ɹ���ס405����",ih.in(405, "test"));
	}
	
	@Test
	public void ListHomeTest() {
		String roomsTest[][]=new String[2][3];
		for(int i=0;i<roomsTest.length;i++) {
			for(int j=0;j<roomsTest[0].length;j++) {
				roomsTest[i][j]="EMPTY";
			}
		}
		MainRunInterface mrInterface=context.mock(MainRunInterface.class);
		ListHome lh=new ListHome(mrInterface);
		context.checking(new Expectations() {{
			exactly(480).of(mrInterface).getRoom();
			will(returnValue(roomsTest));
		}});
		lh.search();
	}
	
//	@Test
	public void MainRunTest() {
		//InHotel����
		IData iData=context.mock(IData.class);
		InHotel ih=new InHotel(iData);
		context.checking(new Expectations() {{
			exactly(10).of(iData).in_Out_Room(405,"test");
			will(returnValue("test�ɹ���ס405����"));
		}});
		//OutHotel����
		OutHotel oh=new OutHotel(iData);
		context.checking(new Expectations() {{
			exactly(10).of(iData).in_Out_Room(701,"EMPTY");
			will(returnValue("701�˷��ɹ���"));
		}});
		//ListHome����
		String roomsTest[][]=new String[2][3];
		for(int i=0;i<roomsTest.length;i++) {
			for(int j=0;j<roomsTest[0].length;j++) {
				roomsTest[i][j]="EMPTY";
			}
		}
		MainRunInterface mrInterface=context.mock(MainRunInterface.class);
		ListHome lh=new ListHome(mrInterface);
		context.checking(new Expectations() {{
			exactly(480).of(mrInterface).getRoom();
			will(returnValue(roomsTest));
		}});
		//��ʼ����
		MainRun mr=new MainRun(lh,ih,oh);
		mr.command();
		
	}
}
