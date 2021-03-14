package leetCode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * date:2019-11-32
 * type:璐績绠楁硶
 * 缁欏畾涓�涓暟缁勶紝瀹冪殑绗爄 涓厓绱犳槸涓�鏀粰瀹氳偂绁ㄧ i 澶╃殑浠锋牸銆�
 * 璁捐涓�涓畻娉曟潵璁＄畻浣犳墍鑳借幏鍙栫殑鏈�澶у埄娑︺�備綘鏈�澶氬彲浠ュ畬鎴惵犱袱绗斅犱氦鏄撱��
 * 娉ㄦ剰:聽浣犱笉鑳藉悓鏃跺弬涓庡绗斾氦鏄擄紙浣犲繀椤诲湪鍐嶆璐拱鍓嶅嚭鍞帀涔嬪墠鐨勮偂绁級銆�
 */
public class Q122 {
	public static void main(String[] args) {
		int[] prices= {3,3,5,0,0,3,1,4};
		System.out.println(maxProfit(prices));
	}
	public static int maxProfit(int[] prices) {
		boolean isContinue=false;
		List<Integer> list=new LinkedList<Integer>();

		int add=0;
		for(int i=1;i<prices.length;i++) {
			int temp=prices[i]-prices[i-1];
			if(temp<0)	{
				isContinue=true;
				list.add(add);
				add=0;
			}
			else {
				add+=temp;
			}
		}
		list.add(add);
		Collections.sort(list);
		if(list.size()==1)	return list.get(0);
		else return list.get(list.size()-2)+list.get(list.size()-1);
	}
}
