package mcm2020.chart;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.xy.XYSeries;

import jxl.Sheet;
import jxl.Workbook;

public class FullEventChart {
	public FullEventChart() throws Exception {
		File f=new File("File/mcm2020/fullevents.xls");
		File matchFile=new File("File/mcm2020/matches.xls");
		Workbook workbook=Workbook.getWorkbook(f);
		Sheet sheet=workbook.getSheet(0);
		FullEvent[] full=getData(sheet);
		Match m[]=getMatch(matchFile);
		beShot(full);
//		dealMatches_goalLine(m,full);
	}
	
	
	//被射门次数,解围次数
	private void beShot(FullEvent[] full) {
		Map<Integer,Integer> map=new HashMap<Integer,Integer>();
		for(int i=0;i<full.length;i++) {
			if(!full[i].getTeamID().equals("Huskies"))	continue;
			if(!full[i].getEventType().equals("Offside"))	continue;
			int matchId=full[i].getMatchID();
			if(map.get(matchId)==null)	map.put(matchId	,1);
			else	map.put(matchId, map.get(matchId)+1);
		}
		for(int i=0;i<full.length;i++) {
			int matchId=full[i].getMatchID();
			if(map.get(matchId)==null)	map.put(matchId	,0);
		}
		for(int key:map.keySet()) {
			System.out.println(map.get(key));
		}
	}
	
		//生成进球前的线图
		private void dealMatches_goalLine(Match[] m,FullEvent[] f) {
			int reCallNumber=4;
			Draw d=new Draw();
			List<XYSeries> list=new ArrayList<XYSeries>();
			for(int i=0;i<f.length;i++) {
				if(f[i].getEventType().equals("Shot")) {
					FullEvent[] passEvent=new FullEvent[reCallNumber];
					for(int j=0;j<reCallNumber;j++) {
						passEvent[j]=f[i-reCallNumber+j+1];
					}
					XYSeries series=Draw.getSeries(passEvent, m[0].getSide());
					list.add(series);
				}
			}
			XYSeries[] series=(XYSeries[])list.toArray(new XYSeries[list.size()]);
			d.drawData(series, "test");
		}
		
		private Match[] getMatch(File matchFile) throws Exception{
			Workbook workbook=Workbook.getWorkbook(matchFile);
			Sheet sheet=workbook.getSheet(0);
			Match dataList[]=new Match[sheet.getRows()-1];
			for(int i=1;i<sheet.getRows();i++) {
				Match d=new Match();
				d.setMatchID(Integer.parseInt(sheet.getCell(0, i).getContents()));
				d.setOpponentID(sheet.getCell(1, i).getContents());
				d.setOutcome(sheet.getCell(2, i).getContents());
				d.setOwnScore(Integer.parseInt(sheet.getCell(3, i).getContents()));
				d.setOpponentScore(Integer.parseInt(sheet.getCell(4, i).getContents()));
				d.setSide(sheet.getCell(5, i).getContents());
				d.setCoachID(sheet.getCell(6, i).getContents());
				dataList[i-1]=d;
			}
			return dataList;
		}
		
		private FullEvent[] getData(Sheet sheet) {
			int sheetLength=59272;
			FullEvent dataList[]=new FullEvent[sheetLength-1];
			for(int i=1;i<sheetLength;i++) {
				if(sheet.getCell(0,i).getContents()==null || sheet.getCell(0,i).getContents().equals("")) continue;
				FullEvent d=new FullEvent();
				if(!sheet.getCell(0,i).getContents().equals(""))d.setMatchID(Integer.parseInt(sheet.getCell(0,i).getContents()));
				d.setTeamID(sheet.getCell(1,i).getContents());
				d.setOriginPlayerID(sheet.getCell(2,i).getContents());
				d.setDestinationPlayerID(sheet.getCell(3,i).getContents());
				d.setMatchPeriod(sheet.getCell(4,i).getContents());
				d.setEventTime(Double.parseDouble(sheet.getCell(5,i).getContents()));
				d.setEventType(sheet.getCell(6,i).getContents());
				d.setEventSubType(sheet.getCell(7,i).getContents());
				d.setEventOrigin_x(sheet.getCell(8,i).getContents());
				d.setEventOrigin_y(sheet.getCell(9,i).getContents());
				if(!sheet.getCell(10,i).getContents().equals(""))d.setEventDestination_x(Integer.parseInt(sheet.getCell(10,i).getContents()));
				if(!sheet.getCell(11,i).getContents().equals(""))d.setEventDestination_y(Integer.parseInt(sheet.getCell(11,i).getContents()));
				dataList[i-1]=d;
			}
			return dataList;
		}
}

class FullEvent extends Data{
	private String EventType;

	public String getEventType() {
		return EventType;
	}

	public void setEventType(String eventType) {
		EventType = eventType;
	}

}

