package mcm2020.chart;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jfree.data.xy.XYSeries;

import jxl.Sheet;
import jxl.Workbook;

public class TypeChart {
	public TypeChart() throws Exception {
		File f=new File("File/mcm2020/passingevents.xls");
		File matchFile=new File("File/mcm2020/matches.xls");
		Workbook workbook=Workbook.getWorkbook(f);
		Sheet sheet=workbook.getSheet(0);
		Data[] d=getData(sheet);
		Match m[]=getMatch(matchFile);
//		ballControlling(d);
		passTimes(d);
// 		dealMatches_xy(m,d);
	}
	
	//控球率
	private void ballControlling(Data[] d) {
		Data[][] splitData=this.splitData(d);
		String team="Huskies";
		Map<Integer,Double> map=new HashMap<Integer,Double>();
		for(int i=0;i<splitData.length;i++) {
			double lastTime=splitData[i][0].getEventTime();
			String lastTeam=splitData[i][0].getTeamID();
			double totalTimes=0;
			double max=0;
			for(int j=1;j<splitData[i].length;j++) {
				if(splitData[i][j].getEventTime()<lastTime) {//上下半场
					lastTime=splitData[i][j].getEventTime();
					lastTeam=team;
					max=splitData[i][j-1].getEventTime();
					continue;
				}
				if(splitData[i][j].getTeamID().equals(lastTeam))	continue;//球权没变
				
				if(!splitData[i][j].getTeamID().equals(team)) {//球权给了对方队
					totalTimes+=splitData[i][j].getEventTime()-lastTime;
					lastTime=splitData[i][j].getEventTime();
				}else {//球权给了我们队
					lastTime=splitData[i][j].getEventTime();
				}
				lastTeam=splitData[i][j].getTeamID();
			}
			map.put(i, totalTimes/2/max);
		}
		for(int key:map.keySet()) {
			System.out.println(map.get(key));
		}
		
	}
	
	//区分出每一场的data
	private Data[][] splitData(Data[] d){
		Data result[][]=new Data[38][];
		int match=1;
		int lastLocate=0;
		for(int i=0;i<d.length;i++) {
			if(d[i].getMatchID()!=match) {
				Data newData[]=new Data[i-lastLocate];
				for(int j=0;j<newData.length;j++) {
					newData[j]=d[j+lastLocate];
				}
				result[match-1]=newData;
				match++;
				lastLocate=i;
			}
		}
		int i=d.length;
		Data newData[]=new Data[i-lastLocate];
		for(int j=0;j<newData.length;j++) {
			newData[j]=d[j+lastLocate];
		}
		result[match-1]=newData;
		lastLocate=i;
		
		return result;
	}
	
	
	//传球次数或smartpass次数
	private void passTimes(Data[] d) {
		Map<Integer,Integer> map=new HashMap<Integer,Integer>();
		for(int i=0;i<d.length;i++) {
			if(!d[i].getEventSubType().equals("Smart pass"))	continue;//关键传球
			if(!d[i].getTeamID().equals("Huskies"))	continue;
			int matchId=d[i].getMatchID();
			if(map.get(matchId)==null)	map.put(matchId	,1);
			else	map.put(matchId, map.get(matchId)+1);
		}
		for(int i=0;i<d.length;i++) {
			int matchId=d[i].getMatchID();
			if(map.get(matchId)==null)	map.put(matchId	,0);
		}
		for(int key:map.keySet()) {
			System.out.println(map.get(key));
		}
	}
	
	//生成线图
	private void dealMatches_line(Match[] m,Data[] d) {
		for(int i=0;i<1;i++) {
			Draw draw=new Draw();
			
			DealResult result=this.readData(d, m[i].getMatchID());
			List<XYSeries> list1=CoordinateProcessing_line(m[i],result.getTwoCooperation());
			List<XYSeries> list2=CoordinateProcessing_line(m[i],result.getThreeCooperation());
			List<XYSeries> list3=CoordinateProcessing_line(m[i],result.getManyCooperation());
			List<XYSeries> list=new ArrayList<XYSeries>();
			list.addAll(list1);
			list.addAll(list2);
			list.addAll(list3);
			XYSeries[] series=(XYSeries[])list.toArray(new XYSeries[list.size()]);
			draw.drawData(series, "test");
		}
	}
	//坐标处理，生成线坐标
		private List<XYSeries> CoordinateProcessing_line(Match match,List<Data[]> list) {
			List<XYSeries> SeriesList=new ArrayList<XYSeries>(list.size());
			for(Data[] data:list) {
				SeriesList.add(Draw.getSeries(data, match.getSide()));
			}
			return SeriesList;
		}
	
	//生成点图
	private void dealMatches_xy(Match[] m,Data[] d) {
		for(int i=35;i<36;i++) {
			DealResult result=this.readData(d, m[i].getMatchID());
			CoordinateProcessing(m[i],result.getTwoCooperation());
			CoordinateProcessing(m[i],result.getThreeCooperation());
			CoordinateProcessing(m[i],result.getManyCooperation());
		}
	}
	//坐标处理，生成坐标
	private void CoordinateProcessing(Match match,List<Data[]> list) {
		for(Data[] data:list) {
			for(int i=0;i<1;i++) {
				if(match.getSide().equals("away")) {
					System.out.print(data[i].getEventDestination_y()+"    ");
					System.out.print(data[i].getEventDestination_x()+"    ");
				}else if(match.getSide().equals("home")) {
					System.out.print(100-data[i].getEventDestination_y()+"    ");
					System.out.print(100-data[i].getEventDestination_x()+"    ");
				}
			}
			System.out.print(data.length+"    ");
			System.out.println();
		}
	}
	
	
	private void dealMatches(Match[] m,Data[] d) {
		for(int i=0;i<m.length;i++) {
			DealResult result=this.readData(d, m[i].getMatchID());
//			System.out.print("第"+m[i].getMatchID()+"场比赛：");
			System.out.print(result.getTwoCooperation().size()+"    ");
			System.out.print(result.getThreeCooperation().size()+"    ");
			System.out.print(result.getManyCooperation().size()+"    ");
			System.out.print(m[i].getOutcome());
			System.out.println();
		}
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

	private DealResult readData(Data[] d,int matchId) {
		double timeInterval=10;
		String lastBallTeam="Huskies";
		double lastTime=d[0].getEventTime();
		int cooperateNumber=0;
		DealResult result=new DealResult();
		List<Data[]> twoCooperation=new LinkedList<Data[]>();
		List<Data[]> threeCooperation=new LinkedList<Data[]>();
		List<Data[]> manyCooperation=new LinkedList<Data[]>();
		for(int i=1;i<d.length;i++) {
			if(d[i].getMatchID()!=matchId) continue;
			if(d[i].getEventTime()-lastTime>timeInterval || !d[i].getTeamID().equals(lastBallTeam)) {
				List<Data[]>list=new LinkedList<Data[]>();
				if(cooperateNumber==2) list=twoCooperation;
				else if(cooperateNumber==3) list=threeCooperation;
				else if(cooperateNumber>3) list=manyCooperation;
				Data addData[]=new Data[cooperateNumber];
				for(int k=i-cooperateNumber;k<i;k++) {
					addData[cooperateNumber+k-i]=d[k];
				}
				list.add(addData);
				
				lastTime=d[i].getEventTime();
				cooperateNumber=0;
			}
			cooperateNumber++;
		}
		result.setTwoCooperation(twoCooperation);
		result.setThreeCooperation(threeCooperation);
		result.setManyCooperation(manyCooperation);
		return result;
	}

	private Data[] getData(Sheet sheet) {
		Data dataList[]=new Data[sheet.getRows()-1];
		for(int i=1;i<sheet.getRows();i++) {
			Data d=new Data();
			d.setMatchID(Integer.parseInt(sheet.getCell(0,i).getContents()));
			d.setTeamID(sheet.getCell(1,i).getContents());
			d.setOriginPlayerID(sheet.getCell(2,i).getContents());
			d.setDestinationPlayerID(sheet.getCell(3,i).getContents());
			d.setMatchPeriod(sheet.getCell(4,i).getContents());
			d.setEventTime(Double.parseDouble(sheet.getCell(5,i).getContents()));
			d.setEventSubType(sheet.getCell(6,i).getContents());
			d.setEventOrigin_x(sheet.getCell(7,i).getContents());
			d.setEventOrigin_y(sheet.getCell(8,i).getContents());
			d.setEventDestination_x(Integer.parseInt(sheet.getCell(9,i).getContents()));
			d.setEventDestination_y(Integer.parseInt(sheet.getCell(10,i).getContents()));
			dataList[i-1]=d;
		}
		return dataList;
	}
}

class DealResult{
	private List<Data[]> twoCooperation=null;
	private List<Data[]> threeCooperation=null;
	private List<Data[]> manyCooperation=null;
	public List<Data[]> getTwoCooperation() {
		return twoCooperation;
	}
	public void setTwoCooperation(List<Data[]> twoCooperation) {
		this.twoCooperation = twoCooperation;
	}
	public List<Data[]> getThreeCooperation() {
		return threeCooperation;
	}
	public void setThreeCooperation(List<Data[]> threeCooperation) {
		this.threeCooperation = threeCooperation;
	}
	public List<Data[]> getManyCooperation() {
		return manyCooperation;
	}
	public void setManyCooperation(List<Data[]> manyCooperation) {
		this.manyCooperation = manyCooperation;
	}
}


class Match{
	private int MatchID;
	private String OpponentID;
	private String Outcome;
	private int OwnScore;
	private int OpponentScore;
	private String Side;
	private String CoachID;
	public int getMatchID() {
		return MatchID;
	}
	public void setMatchID(int matchID) {
		MatchID = matchID;
	}
	public String getOpponentID() {
		return OpponentID;
	}
	public void setOpponentID(String opponentID) {
		OpponentID = opponentID;
	}
	public String getOutcome() {
		return Outcome;
	}
	public void setOutcome(String outcome) {
		Outcome = outcome;
	}
	public int getOwnScore() {
		return OwnScore;
	}
	public void setOwnScore(int ownScore) {
		OwnScore = ownScore;
	}
	public int getOpponentScore() {
		return OpponentScore;
	}
	public void setOpponentScore(int opponentScore) {
		OpponentScore = opponentScore;
	}
	public String getSide() {
		return Side;
	}
	public void setSide(String side) {
		Side = side;
	}
	public String getCoachID() {
		return CoachID;
	}
	public void setCoachID(String coachID) {
		CoachID = coachID;
	}

}

class Data{
	private int MatchID;
	private String TeamID;
	private String OriginPlayerID;
	private String DestinationPlayerID;
	private String MatchPeriod;
	private double EventTime;
	private String EventSubType;
	
	private String EventOrigin_x	;
	private String EventOrigin_y	;
	private int EventDestination_x;
	private int EventDestination_y;
	
	public int getMatchID() {
		return MatchID;
	}
	public void setMatchID(int matchID) {
		MatchID = matchID;
	}
	public String getTeamID() {
		return TeamID;
	}
	public void setTeamID(String teamID) {
		TeamID = teamID;
	}
	public String getOriginPlayerID() {
		return OriginPlayerID;
	}
	public void setOriginPlayerID(String originPlayerID) {
		OriginPlayerID = originPlayerID;
	}
	public String getDestinationPlayerID() {
		return DestinationPlayerID;
	}
	public void setDestinationPlayerID(String destinationPlayerID) {
		DestinationPlayerID = destinationPlayerID;
	}
	public String getMatchPeriod() {
		return MatchPeriod;
	}
	public void setMatchPeriod(String matchPeriod) {
		MatchPeriod = matchPeriod;
	}
	public double getEventTime() {
		return EventTime;
	}
	public void setEventTime(double eventTime) {
		EventTime = eventTime;
	}
	public String getEventSubType() {
		return EventSubType;
	}
	public void setEventSubType(String eventSubType) {
		EventSubType = eventSubType;
	}
	public String getEventOrigin_x() {
		return EventOrigin_x;
	}
	public void setEventOrigin_x(String eventOrigin_x) {
		EventOrigin_x = eventOrigin_x;
	}
	public String getEventOrigin_y() {
		return EventOrigin_y;
	}
	public void setEventOrigin_y(String eventOrigin_y) {
		EventOrigin_y = eventOrigin_y;
	}
	public int getEventDestination_x() {
		return EventDestination_x;
	}
	public void setEventDestination_x(int eventDestination_x) {
		EventDestination_x = eventDestination_x;
	}
	public int getEventDestination_y() {
		return EventDestination_y;
	}
	public void setEventDestination_y(int eventDestination_y) {
		EventDestination_y = eventDestination_y;
	}
	
	

}
