package item_jack.item2_21;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import item_jack.item2_21.utils.Config;
import item_jack.item2_21.utils.OutData;
import item_jack.item2_21.utils.Record;
import item_jack.item2_21.utils.Screen;
import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/*
 * date:2020.2.21
 * author:yf
 * detail:表一中正好六条数据组成一次五个数据排列，
 * 用表二的数据筛选，如果表二中满足条件：
 * 1、有两行或以上的行中，都有三个或以上的数据组成同样的五个数据排列
 * 2、有一行或以上的行中，都有两个或以上的数据组成一个四个数据排列，这四个数据都在五个数据排列中
 * 
 * 2.23增加条件：
 * 筛选出来的数据要在表3中出现3次或以上
 * 结果中打印出在表1中的列位置
 * 
 * 2.26增加条件：
 * 筛选出来的数据要在表4中出现2次或以上
 * 结果中打印出在每一行abc列的总数量
 * 设置筛选条件234的开关
 */
public class dataDeal {

	public static void main(String[] args) {
		System.out.println("item1_12");
		System.out.println("version：1.0");
		System.out.println("date:2020.1.12\r\n" + 
				" * request：表一中正好六条数据组成一次五个数据排列，\r\n" + 
				" * 用表二的数据筛选，如果表二中满足条件：\r\n" + 
				" * 1、有两行或以上的行中，都有三个或以上的数据组成同样的五个数据排列\r\n" + 
				" * 2、有一行或以上的行中，都有两个或以上的数据组成一个四个数据排列，这四个数据都在五个数据排列中\r\n\n" + 
				" * 2.23增加条件：\r\n" + 
				" * 筛选出来的数据要在表3中出现3次或以上\r\n" + 
				" * 结果中打印出在表1中的列位置\r\n" + 
				" * \r\n" + 
				" * 2.26增加条件：\r\n" + 
				" * 筛选出来的数据要在表4中出现2次或以上\r\n" + 
				" * 结果中打印出在每一行abc列的总数量\r\n" + 
				" * 设置筛选条件234的开关");
		System.out.println();
		System.out.println();
		try {
			run();
			System.out.print("数据处理完毕，");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.print("数据处理出错，");
		}
		System.out.println("按任意键退出！");
		Scanner sc = new Scanner(System.in); 
		sc.next();
		sc.close();
	}
	public static void run() {
		//第二步：将数据源文件中的数据取出，去重，生成一个list对象
		System.out.println("正在读入数据源文件......");
		Config config=null;
		List<Record> originalList=null;
		Screen screen=null;
		try {
			screen=Screen.getScreen();
			config=Config.getConfig();
			originalList=config.getSourceList();
		} catch (Exception e) {
			System.out.println("生成数据源对象失败！！");
			e.printStackTrace();
			return;
		}
		System.out.println("去重后数据量："+originalList.size());

		//第三步：数据处理，获取满足条件的排列
		System.out.println("数据处理：生成候补排列......");
		Set<Set<String>> finalSet=null;
		try {
			finalSet=getFirstDealResult(originalList);
		} catch (Exception e) {
			System.out.println("第一次数据处理失败！！");
			e.printStackTrace();
			return;
		}
		System.out.println("生成候补排列："+originalList.size()+"/"+originalList.size()+"......");

		//第五步：根据53322的规则筛选排列
		System.out.println("开始检测排列......");
		List<OutData> outList=null;
		try {
			outList=chooseResult(originalList,finalSet);
		} catch (Exception e) {
			System.out.println("数据匹配失败！！");
			e.printStackTrace();
			return;
		}
		System.out.println("检测排列：100%");
		System.out.println("满足排列数据条数："+outList.size());

		//第六步：在53322筛选的基础上继续筛选
		System.out.println("开始筛选......");
		List<OutData> screenList=null;
		try {
			screenList=screen.startScreen(outList);
		} catch (Exception e) {
			System.out.println("筛选数据失败！！");
			e.printStackTrace();
			return;
		}
		System.out.println("筛选完成......");
		System.out.println("满足筛选表条件数据条数："+screenList.size());

		//第七步：将结果数据写回文件
		System.out.println("正在将数据写回文件......");
		try {
			writeResult(screenList,config.getDataDeal_aim());
		} catch (Exception e) {
			System.out.println("写回数据失败！！");
			e.printStackTrace();
			return;
		}
	}


	//第三步：第一次数据处理，得到初步的五位数字排列
	private static Set<Set<String>> getFirstDealResult(List<Record> originalList) throws Exception{
		Set<Set<String>> finalSet=new HashSet<Set<String>>();
		int listLength=originalList.size();
		for(int i=0;i<listLength;i++) {
			if(i%5==0)		System.out.println("生成候补排列："+(i+1)+"/"+listLength+"......");
			Record record1=originalList.get(i);
			for(int j=i+1;j<listLength;j++) {
				Record record2=originalList.get(j);
				for(int k=j+1;k<listLength;k++) {
					Record record3=originalList.get(k);

					Set<String> firstSet=new HashSet<String>();
					firstSet.addAll(record1.getDataSet());
					firstSet.addAll(record2.getDataSet());
					firstSet.addAll(record3.getDataSet());

					if(firstSet.size()==5)	finalSet.add(firstSet);
				}
			}
		}
		return finalSet;
	}

	//第五步：根据53322的规则筛选排列
	private static List<OutData> chooseResult(List<Record> originalList, Set<Set<String>> finalSet) {
		List<OutData> resultList=new LinkedList<OutData>();
		Iterator<Set<String>> finalIt=finalSet.iterator();
		int total=finalSet.size();
		int count=0;
		while(finalIt.hasNext()) {
			//计数输出
			count++;
			double temp=(int)(count*1.0/total*10000)*1.0/100;
			if(count % 1000==0)		System.out.println("检测排列："+temp+"%");

			//计算每个排列的源个数
			Set<String> finalSingleSet=finalIt.next();
			List<Record> recordList=new LinkedList<Record>();
			for(int i=0;i<originalList.size();i++) {
				Set<String> tempSet=new HashSet<String>(finalSingleSet);
				int beforeLength=tempSet.size();
				tempSet.addAll(originalList.get(i).getDataSet());
				if(tempSet.size()==beforeLength)	recordList.add(originalList.get(i));
			}
			
			//如果正好是六个，则保留（满足条件1.0）
			if(recordList.size()==6) {
				OutData out=new OutData();
				out.setOutList(recordList);
				out.setOutSet(finalSingleSet);
				resultList.add(out);
			}
		}

		return resultList;
	}

	//第七步：将结果数据写回文件
	private static void writeResult(List<OutData> outList, File f) throws Exception{
		WritableWorkbook workbook=Workbook.createWorkbook(f);
		WritableSheet sheet=workbook.createSheet("result", 0);

		for(int i=0;i<outList.size();i++) {
			OutData out=outList.get(i);
			//写回最终排列
			StringBuilder sb=new StringBuilder();
			sb.append("(");
			List<String> rankedList=new LinkedList<String>(out.getOutSet());
			Collections.sort(rankedList);
			for(int j=0;j<rankedList.size();j++) {
				sb.append(rankedList.get(j)+",");
			}
			sb.delete(sb.length()-1, sb.length());
			sb.append(")");
			Label label=new Label(0,i,sb.toString());
			sheet.addCell(label);

			//写回原始数据，所在位置
			List<Record> list=out.getOutList();
			for(int j=0;j<list.size();j++) {
				//原始数据
				Record record=list.get(j);
				Label label1=new Label(2+j,i,record.getDataString());
				sheet.addCell(label1);
				//所在位置
				List<Integer> integerList=record.getLocateList();
				Collections.sort(integerList);
				StringBuilder sb2=new StringBuilder();
				for(int locate:integerList) 		sb2.append(String.valueOf((char)(locate+65)));
				Label label2=new Label(list.size()+3+j,i,sb2.toString());
				sheet.addCell(label2);
			}
			
			//写回ABC总个数
			int number=0;
			for(int j=0;j<list.size();j++) {
				List<Integer> integerList=list.get(j).getLocateList();
				for(int locate:integerList) 	{
					if(locate>=0 && locate<=2)		number++;
				}
			}
			WritableCellFormat wcfN = new WritableCellFormat(NumberFormats.INTEGER);
//			Label label3=new Label(list.size()*2+4,i,String.valueOf(number));
			jxl.write.Number labelNF = new jxl.write.Number(list.size()*2+4,i,number,wcfN);
			sheet.addCell(labelNF);
				
		}
		CellView cv = new CellView();
		cv.setAutosize(true);
		for(int i=0;i<sheet.getColumns();i++) {
			if(i<9)	sheet.setColumnView(i, 24);
			else 	sheet.setColumnView(i, 8);
		}

		//写入并关闭
		workbook.write();
		workbook.close();
	}

}