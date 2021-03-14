package item_jack.item5_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import item_jack.item5_2.utils.OutData;
import item_jack.item5_2.utils.Record;

public class DealThread extends Thread{
	static final int rankedTimes=6;
	Line line1;
	Line line2;
	List<OutData> outList;
	List<String> stringList;
	Record[] originalList;
	Set<Set<String>> rankSet=new HashSet<Set<String>>();
	Set<Set<String>> finalSet=new HashSet<Set<String>>();
	
	public DealThread(Line line1,Line line2,List<OutData> list) {
		this.line1=line1;
		this.line2=line2;
		this.outList=list;

		//整合两行中的特殊值
		stringList=new ArrayList<String>(line1.getOtherData());
		stringList.addAll(line2.getOtherData());

		//第一次添加
		Set<String> set=new HashSet<String>();
		set.addAll(line1.getPublicData());
		set.addAll(line2.getPublicData());
		rankSet.add(set);
	}
	
	public void dependencyInjection(Record[] originalList) {
		this.originalList=originalList;
	}
	public void run() {
		Set<Set<String>> leftSetTemp=new HashSet<Set<String>>();
		for(int t=0;t<rankedTimes;t++) {
			if(!leftSetTemp.isEmpty()) {
				rankSet=leftSetTemp;
				leftSetTemp=new HashSet<Set<String>>();
			}
			Iterator<Set<String>> it=rankSet.iterator();
			while(it.hasNext()) {
				Set<String> temp=it.next();
				for(int i=0;i<stringList.size();i++) {
					if(temp.size()>=rankedTimes)	continue;
					if(temp.contains(stringList.get(i)))	continue;
					Set<String> newTemp=new HashSet<String>(temp);
					newTemp.add(stringList.get(i));
					if(newTemp.size()==rankedTimes)		finalSet.add(newTemp);
					else 	leftSetTemp.add(newTemp);
				}
			}
		}
		Queue<Set<String>> queue=new LinkedList<Set<String>>(finalSet);
		chooseResult(queue,line1,line2);
	}
	//根据规则筛选排列
	private void chooseResult(Queue<Set<String>> queue,Line line1,Line line2) {
		while(!queue.isEmpty()) {
			Set<String> single=queue.poll();
			List<Record> son1=sonRecord(single,line1.getData());
			List<Record> son2=sonRecord(single,line2.getData());
			if(son1.size()<3 || son2.size()<3)	continue;//初步判断
			OutData out=conditionScreen(single,son1,son2);
			if(out!=null)	outList.add(out);
		}
	}

	//条件筛选
	private OutData conditionScreen(Set<String> set,List<Record> son1,List<Record> son2) {
		if(condition1(son1,son2)==false)	return null;
		//添加子集
		List<Record> list=new LinkedList<Record>();
		for(int i=0;i<originalList.length;i++) {
			Record record=originalList[i];
			if(set.containsAll(record.getDataSet())) {
				list.add(record);
			}
		}
		this.sortRecord(list);
		if(!condition2(list,set,son1,son2))		return null;
		
		if(son1.size()>son2.size()) {
			List<Record> t=son1;
			son1=son2;
			son2=t;
		}
		List<List<Record>> satisfyList=new LinkedList<List<Record>>();	
		satisfyList.add(this.getRanked(son1, list));
		satisfyList.add(this.getRanked(son2, list));
		satisfyList.add(son1);
		satisfyList.add(son2);
		
		
		OutData out=new OutData();
		out.setOutSet(set);
		out.setOutList(list);
		out.setSatisfyList(satisfyList);
		return out;
	}
	
	private List<Record> getRanked(List<Record> son,List<Record> totalList) {
		List<Record> list=new LinkedList<Record>();
		for(int i=0;i<son.size();i++) {
			Record record=new Record();
			String value=null;
			for(int j=0;j<rankedTimes;j++) {
				if(son.get(i).getIndex()==totalList.get(j).getIndex()) {
					if(value==null)	value=String.valueOf(j+1);
					else		value=value.concat(" ("+(j+1)+") ");
				}
			}
			record.setDataString(value);
			list.add(record);
		}
		return list;
	}
	
	//是否满足全部在前六且一个3一个4
	private boolean condition2(List<Record> list,Set<String> set, List<Record> son1,List<Record> son2) {
		if(list.size()<rankedTimes)	return false;	
		List<Record> temp=new LinkedList<Record>(list.subList(0, rankedTimes));
		int maxIndex=0;
		for(int i=0;i<temp.size();i++)	if(temp.get(i).getIndex()>maxIndex)	maxIndex=temp.get(i).getIndex();
		for(int i=0;i<son1.size();i++) {
			if(son1.get(i).getIndex()>maxIndex)	son1.remove(i--);
			else temp.remove(son1.get(i));
		}
		for(int i=0;i<son2.size();i++) {
			if(son2.get(i).getIndex()>maxIndex)	son2.remove(i--);
			else temp.remove(son2.get(i));
		}
		if(!temp.isEmpty())	return false;//不全在前六
		if(son1.size()>son2.size()) {
			List<Record> t=son1;
			son1=son2;
			son2=t;
		}
		if(son1.size()!=3 || son2.size()!=4)		return false;//一个3一个4
		return true;
	}
	
	private boolean condition1(List<Record> son1,List<Record> son2) {
		//检测有没有出现一行相同数据
		for(Record record:son1) {
			for(Record r:son2) {
				if(record.getDataSet().equals(r.getDataSet()))	return true;
			}
		}
		return false;
	}

	//对一个集合排序
	private void sortRecord(List<Record> list) {
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
	private List<Record> sonRecord(Set<String> father,List<Record> son){
		List<Record> sonList=new LinkedList<Record>();
		for(Record record : son) {
			if(father.containsAll(record.getDataSet()))		sonList.add(record);
		}
		return sonList;
	}


}
