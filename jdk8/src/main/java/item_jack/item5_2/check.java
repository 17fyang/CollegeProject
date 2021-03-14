package item_jack.item5_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import item_jack.item5_2.utils.Config;
import item_jack.item5_2.utils.OutData;
import item_jack.item5_2.utils.Record;

public class check {
	public static void main(String[] args) throws Exception {
		System.out.println("结果集同步成功...");
		List<Line> lineList=Config.getConfig().getLineList();
		Record[] originalList=Config.getConfig().getSourceList();
		Set<String> set=new HashSet<String>();
		set.add("01");
		set.add("14");
		set.add("23");
		set.add("25");
		set.add("29");
		set.add("30");
		
		List<Record> outList=new LinkedList<Record>();
		for(int i=0;i<originalList.length;i++) {
			if(set.containsAll(originalList[i].getDataSet()))	outList.add(originalList[i]);
		}
		sortRecord(outList);
		
		List<List<Record>> satisfyList=new LinkedList<List<Record>>();
		for(int i=0;i<lineList.size();i++) {
			List<Record> temp=sonRecord(set,lineList.get(i));
			if(temp.size()>=3 || temp.size()==4)	satisfyList.add(temp);
		}
		System.out.println();
	}
	
	
	//对一个集合排序
		private static void sortRecord(List<Record> list) {
			for(int i=0;i<list.size();i++) {
				for(int j=1;j<list.size()-i;j++) {
					if(list.get(j-1).getIndex()>list.get(j).getIndex()) {
						Record r=list.get(j-1);
						list.set(j-1, list.get(j));
						list.set(j, r);
					}
				}
			}
		}
	
	
	//给定一个大排列和小排列的链表，返回一个链表，里面的元素都是大排列的子集
		private static List<Record> sonRecord(Set<String> father,Line son){
			List<Record> sonList=new LinkedList<Record>();
			for(Record record : son.getData()) {
				if(father.containsAll(record.getDataSet()))		sonList.add(record);
			}
			return sonList;
		}
}
