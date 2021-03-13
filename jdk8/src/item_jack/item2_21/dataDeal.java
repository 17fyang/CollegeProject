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
 * detail:��һ�����������������һ������������У�
 * �ñ��������ɸѡ��������������������
 * 1�������л����ϵ����У��������������ϵ��������ͬ���������������
 * 2����һ�л����ϵ����У��������������ϵ��������һ���ĸ��������У����ĸ����ݶ����������������
 * 
 * 2.23����������
 * ɸѡ����������Ҫ�ڱ�3�г���3�λ�����
 * ����д�ӡ���ڱ�1�е���λ��
 * 
 * 2.26����������
 * ɸѡ����������Ҫ�ڱ�4�г���2�λ�����
 * ����д�ӡ����ÿһ��abc�е�������
 * ����ɸѡ����234�Ŀ���
 */
public class dataDeal {

	public static void main(String[] args) {
		System.out.println("item1_12");
		System.out.println("version��1.0");
		System.out.println("date:2020.1.12\r\n" + 
				" * request����һ�����������������һ������������У�\r\n" + 
				" * �ñ��������ɸѡ��������������������\r\n" + 
				" * 1�������л����ϵ����У��������������ϵ��������ͬ���������������\r\n" + 
				" * 2����һ�л����ϵ����У��������������ϵ��������һ���ĸ��������У����ĸ����ݶ����������������\r\n\n" + 
				" * 2.23����������\r\n" + 
				" * ɸѡ����������Ҫ�ڱ�3�г���3�λ�����\r\n" + 
				" * ����д�ӡ���ڱ�1�е���λ��\r\n" + 
				" * \r\n" + 
				" * 2.26����������\r\n" + 
				" * ɸѡ����������Ҫ�ڱ�4�г���2�λ�����\r\n" + 
				" * ����д�ӡ����ÿһ��abc�е�������\r\n" + 
				" * ����ɸѡ����234�Ŀ���");
		System.out.println();
		System.out.println();
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
	public static void run() {
		//�ڶ�����������Դ�ļ��е�����ȡ����ȥ�أ�����һ��list����
		System.out.println("���ڶ�������Դ�ļ�......");
		Config config=null;
		List<Record> originalList=null;
		Screen screen=null;
		try {
			screen=Screen.getScreen();
			config=Config.getConfig();
			originalList=config.getSourceList();
		} catch (Exception e) {
			System.out.println("��������Դ����ʧ�ܣ���");
			e.printStackTrace();
			return;
		}
		System.out.println("ȥ�غ���������"+originalList.size());

		//�����������ݴ�����ȡ��������������
		System.out.println("���ݴ������ɺ�����......");
		Set<Set<String>> finalSet=null;
		try {
			finalSet=getFirstDealResult(originalList);
		} catch (Exception e) {
			System.out.println("��һ�����ݴ���ʧ�ܣ���");
			e.printStackTrace();
			return;
		}
		System.out.println("���ɺ����У�"+originalList.size()+"/"+originalList.size()+"......");

		//���岽������53322�Ĺ���ɸѡ����
		System.out.println("��ʼ�������......");
		List<OutData> outList=null;
		try {
			outList=chooseResult(originalList,finalSet);
		} catch (Exception e) {
			System.out.println("����ƥ��ʧ�ܣ���");
			e.printStackTrace();
			return;
		}
		System.out.println("������У�100%");
		System.out.println("������������������"+outList.size());

		//����������53322ɸѡ�Ļ����ϼ���ɸѡ
		System.out.println("��ʼɸѡ......");
		List<OutData> screenList=null;
		try {
			screenList=screen.startScreen(outList);
		} catch (Exception e) {
			System.out.println("ɸѡ����ʧ�ܣ���");
			e.printStackTrace();
			return;
		}
		System.out.println("ɸѡ���......");
		System.out.println("����ɸѡ����������������"+screenList.size());

		//���߲������������д���ļ�
		System.out.println("���ڽ�����д���ļ�......");
		try {
			writeResult(screenList,config.getDataDeal_aim());
		} catch (Exception e) {
			System.out.println("д������ʧ�ܣ���");
			e.printStackTrace();
			return;
		}
	}


	//����������һ�����ݴ����õ���������λ��������
	private static Set<Set<String>> getFirstDealResult(List<Record> originalList) throws Exception{
		Set<Set<String>> finalSet=new HashSet<Set<String>>();
		int listLength=originalList.size();
		for(int i=0;i<listLength;i++) {
			if(i%5==0)		System.out.println("���ɺ����У�"+(i+1)+"/"+listLength+"......");
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

	//���岽������53322�Ĺ���ɸѡ����
	private static List<OutData> chooseResult(List<Record> originalList, Set<Set<String>> finalSet) {
		List<OutData> resultList=new LinkedList<OutData>();
		Iterator<Set<String>> finalIt=finalSet.iterator();
		int total=finalSet.size();
		int count=0;
		while(finalIt.hasNext()) {
			//�������
			count++;
			double temp=(int)(count*1.0/total*10000)*1.0/100;
			if(count % 1000==0)		System.out.println("������У�"+temp+"%");

			//����ÿ�����е�Դ����
			Set<String> finalSingleSet=finalIt.next();
			List<Record> recordList=new LinkedList<Record>();
			for(int i=0;i<originalList.size();i++) {
				Set<String> tempSet=new HashSet<String>(finalSingleSet);
				int beforeLength=tempSet.size();
				tempSet.addAll(originalList.get(i).getDataSet());
				if(tempSet.size()==beforeLength)	recordList.add(originalList.get(i));
			}
			
			//�����������������������������1.0��
			if(recordList.size()==6) {
				OutData out=new OutData();
				out.setOutList(recordList);
				out.setOutSet(finalSingleSet);
				resultList.add(out);
			}
		}

		return resultList;
	}

	//���߲������������д���ļ�
	private static void writeResult(List<OutData> outList, File f) throws Exception{
		WritableWorkbook workbook=Workbook.createWorkbook(f);
		WritableSheet sheet=workbook.createSheet("result", 0);

		for(int i=0;i<outList.size();i++) {
			OutData out=outList.get(i);
			//д����������
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

			//д��ԭʼ���ݣ�����λ��
			List<Record> list=out.getOutList();
			for(int j=0;j<list.size();j++) {
				//ԭʼ����
				Record record=list.get(j);
				Label label1=new Label(2+j,i,record.getDataString());
				sheet.addCell(label1);
				//����λ��
				List<Integer> integerList=record.getLocateList();
				Collections.sort(integerList);
				StringBuilder sb2=new StringBuilder();
				for(int locate:integerList) 		sb2.append(String.valueOf((char)(locate+65)));
				Label label2=new Label(list.size()+3+j,i,sb2.toString());
				sheet.addCell(label2);
			}
			
			//д��ABC�ܸ���
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

		//д�벢�ر�
		workbook.write();
		workbook.close();
	}

}