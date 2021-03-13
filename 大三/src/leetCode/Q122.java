package leetCode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * date:2019-11-32
 * type:贪心算法
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
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
