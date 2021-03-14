package item_jack.item5_2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import item_jack.item5_2.utils.Config;
import item_jack.item5_2.utils.OutData;
import item_jack.item5_2.utils.Record;
import jxl.CellView;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class dataDeal {
	public static void main(String[] args) {
		System.out.println("item4_27");
		System.out.println("version：1.0");
		System.out.println("date:2020.4.27\r\n" + 
				"表1中正好6条或以上条数据组成一次六个数据排列，根据每个数据的冒号后面的数字排序，最小的排1号，然后2号。。。。。\r\n" + 
				"* 1、有1行或以上的行中，正好有4个数据在同一行上\r\n" + 
				"* 2、有1行或以上的行中，正好有3个数据在同一行上\r\n" + 
				"* 3、这4个数据和3个数据必须是在所有排序中排在前6位，而且都要出现。\r\n\r\n");
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
	public static void run() throws Exception {
		List<OutData> outList=new ArrayList<OutData>();
		Collections.synchronizedList(outList);
		System.out.println("结果集同步成功...");
		List<Line> lineList=Config.getConfig().getLineList();
		Record[] originalList=Config.getConfig().getSourceList();

		//创建线程池
		int corePoolSize=lineList.size()/2;
		int maxSize=lineList.size()*lineList.size();
		LinkedBlockingQueue<Runnable> queue=new LinkedBlockingQueue<Runnable>();
		ExecutorService pool = new ThreadPoolExecutor(corePoolSize, maxSize,
				3L, TimeUnit.HOURS,queue);

		System.out.println("线程池初始化成功...");
		System.out.println("开始执行线程池任务...");
		System.out.println("有效行数："+lineList.size());
		for(int i=0;i<lineList.size();i++) {
			for(int j=i+1;j<lineList.size();j++) {
				DealThread thread=new DealThread(lineList.get(i),lineList.get(j),outList);
				thread.dependencyInjection(originalList);
				pool.execute(thread);
			}
		}
		while(!queue.isEmpty()) {
			Thread.sleep(2000);
			System.out.println("等待任务投放，剩余："+queue.size()+"...");
		}
		Thread.sleep(2000);
		System.out.println("线程池投放完成，等待任务完成...");
		pool.shutdown();
		while(!pool.isTerminated()) {
			Thread.sleep(2000);
			System.out.println("等待任务完成...");
		}
		System.out.println("线程池关闭完成，开始写入...");
		writeResult(outList,Config.getConfig().getDataDeal_aim());
	}

	private static void writeResult(List<OutData> outList, File f) throws Exception{
		WritableWorkbook workbook=Workbook.createWorkbook(f);
		WritableSheet sheet=workbook.createSheet("result", 0);

		int firstSpilt=2;//第一列空格
		int secondSpilt=2;//第二列空格
		for(int i=0;i<outList.size();i++) {
			OutData out=outList.get(i);
			//写回最终排列
			String arrange=setToString(out.getOutSet());
			sheet.addCell(new Label(0,i,arrange));

			//写回原始数据
			List<Record> list=out.getOutList();
			for(int j=0;j<list.size();j++) {
				secondSpilt=Math.max(secondSpilt, list.size()+firstSpilt+1);
				sheet.addCell(new Label(firstSpilt+j,i,list.get(j).getDataString()));
			}
		}
		for(int i=0;i<outList.size();i++) {
			OutData out=outList.get(i);
			//写回要求中的条件数据
			List<List<Record>> satisfyList=out.getSatisfyList();
			int point=secondSpilt+1;
			for(int j=0;j<satisfyList.size();j++) {
				List<Record> single=satisfyList.get(j);
				for(int k=0;k<single.size();k++) {
					sheet.addCell(new Label(point++,i,single.get(k).getDataString()));
				}
				point++;
			}
		}
		CellView cv = new CellView();
		cv.setAutosize(true);
		for(int i=0;i<sheet.getColumns();i++) {
			sheet.setColumnView(i, 24);
		}
		sheet.setColumnView(firstSpilt-1, 8);
		//写入并关闭
		workbook.write();
		workbook.close();
	}

	//把set中的排序整理成输出格式
	private static String setToString(Set<String> set) {
		StringBuilder sb=new StringBuilder();
		sb.append("(");
		List<String> rankedList=new LinkedList<String>(set);
		Collections.sort(rankedList);
		for(int j=0;j<rankedList.size();j++) {
			sb.append(rankedList.get(j)+",");
		}
		sb.delete(sb.length()-1, sb.length());
		sb.append(")");
		return sb.toString();
	}
}
