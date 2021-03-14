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
		System.out.println("version��1.0");
		System.out.println("date:2020.4.27\r\n" + 
				"��1������6�����������������һ�������������У�����ÿ�����ݵ�ð�ź��������������С����1�ţ�Ȼ��2�š���������\r\n" + 
				"* 1����1�л����ϵ����У�������4��������ͬһ����\r\n" + 
				"* 2����1�л����ϵ����У�������3��������ͬһ����\r\n" + 
				"* 3����4�����ݺ�3�����ݱ���������������������ǰ6λ�����Ҷ�Ҫ���֡�\r\n\r\n");
		try {
			run();
			System.out.print("���ݴ�����ϣ�");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.print("���ݴ������");
		}
		System.out.println("��������˳���");
		Scanner sc = new Scanner(System.in); 
		sc.next();
		sc.close();
	}
	public static void run() throws Exception {
		List<OutData> outList=new ArrayList<OutData>();
		Collections.synchronizedList(outList);
		System.out.println("�����ͬ���ɹ�...");
		List<Line> lineList=Config.getConfig().getLineList();
		Record[] originalList=Config.getConfig().getSourceList();

		//�����̳߳�
		int corePoolSize=lineList.size()/2;
		int maxSize=lineList.size()*lineList.size();
		LinkedBlockingQueue<Runnable> queue=new LinkedBlockingQueue<Runnable>();
		ExecutorService pool = new ThreadPoolExecutor(corePoolSize, maxSize,
				3L, TimeUnit.HOURS,queue);

		System.out.println("�̳߳س�ʼ���ɹ�...");
		System.out.println("��ʼִ���̳߳�����...");
		System.out.println("��Ч������"+lineList.size());
		for(int i=0;i<lineList.size();i++) {
			for(int j=i+1;j<lineList.size();j++) {
				DealThread thread=new DealThread(lineList.get(i),lineList.get(j),outList);
				thread.dependencyInjection(originalList);
				pool.execute(thread);
			}
		}
		while(!queue.isEmpty()) {
			Thread.sleep(2000);
			System.out.println("�ȴ�����Ͷ�ţ�ʣ�ࣺ"+queue.size()+"...");
		}
		Thread.sleep(2000);
		System.out.println("�̳߳�Ͷ����ɣ��ȴ��������...");
		pool.shutdown();
		while(!pool.isTerminated()) {
			Thread.sleep(2000);
			System.out.println("�ȴ��������...");
		}
		System.out.println("�̳߳عر���ɣ���ʼд��...");
		writeResult(outList,Config.getConfig().getDataDeal_aim());
	}

	private static void writeResult(List<OutData> outList, File f) throws Exception{
		WritableWorkbook workbook=Workbook.createWorkbook(f);
		WritableSheet sheet=workbook.createSheet("result", 0);

		int firstSpilt=2;//��һ�пո�
		int secondSpilt=2;//�ڶ��пո�
		for(int i=0;i<outList.size();i++) {
			OutData out=outList.get(i);
			//д����������
			String arrange=setToString(out.getOutSet());
			sheet.addCell(new Label(0,i,arrange));

			//д��ԭʼ����
			List<Record> list=out.getOutList();
			for(int j=0;j<list.size();j++) {
				secondSpilt=Math.max(secondSpilt, list.size()+firstSpilt+1);
				sheet.addCell(new Label(firstSpilt+j,i,list.get(j).getDataString()));
			}
		}
		for(int i=0;i<outList.size();i++) {
			OutData out=outList.get(i);
			//д��Ҫ���е���������
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
		//д�벢�ر�
		workbook.write();
		workbook.close();
	}

	//��set�е���������������ʽ
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
