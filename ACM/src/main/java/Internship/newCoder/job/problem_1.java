package Internship.newCoder.job;

import java.util.Arrays;

/*
 * 
“曾经有两次消除的机会摆在我面前，我却没有珍惜……”牛牛回忆道。
牛牛正在玩一款全新的消消乐游戏。这款游戏的主体是由一列列的方块构成，牛牛的目标就是要尽量消除这些方块。
每次操作，牛牛可以选择某个高度 x，将所有高度大于等于 x 的那些列全部消除 x 个方块，随后方块会下落，以填补消除造成的空白。
牛牛这一局的发挥极佳，眼看就要破纪录了，却发现自己只剩下了两次消除机会。
为了不错失这千载难逢的机会，他决定写个程序来算出最优解。
 */
public class problem_1 {
	public static void main(String[] args) {
		int[] nums=new int[1000];
		for(int i=0;i<nums.length;i++) {
			nums[i]=(int)(Math.random()*1000/1);
		}
		System.out.println(System.currentTimeMillis());
		long result=minimumValueAfterDispel(nums);
		System.out.println(System.currentTimeMillis());
		System.out.println(result);
	}
	public static long minimumValueAfterDispel (int[] nums) {
        // write code here
        Arrays.sort(nums);
        int length = nums.length;
        long sum = 0;
        long max = 0;
        for(int j=0; j<length; j++){
            sum += nums[j];
            int index = j;
            int index2 = j;
            int index3 = j;
            for(int i=0; i<=j; i++){
                while(index < length && (long)nums[index] < (long)nums[i] + nums[j]) {
                   index++;
                }
                while(index2 > 0 && nums[index2-1] >= nums[j] - nums[i]){
                    index2--;
                }
                while(index3 > i && nums[index3-1] >= nums[j] - nums[i]) {
                    index3--;
                }
                long tmp = (length-i)*(long)nums[i] + (length-j+i-index2)*((long)nums[j]-nums[i]);
                long tmp2 = (length-j)*(long)nums[j] + (length-index+j-i)*(long)nums[i];
                long tmp3 = (long)nums[i]*(index3 -i) + ((long)nums[j]-nums[i])*(j-index3) + (long)nums[j]*(length-j);
                max = Math.max(max, tmp);
                max = Math.max(max, tmp2);
                max = Math.max(max, tmp3);
            }
        }
        return sum - max;
    }
	
	public static long minimumValueAfterDispel2 (int[] nums) {
		long sumMin=-1;
		int k=0;
		for(int i=0;i<nums.length;i++) {
			k=nums[i];
			
			int newArray[]=new int[nums.length];
			for(int i2=0;i2<nums.length;i2++) {
				if(nums[i2]>=k) {
					newArray[i2]=nums[i2]-k;
				}else {
					newArray[i2]=nums[i2];
				}
			}
			
			for(int j=0;j<newArray.length;j++) {
				k=newArray[j];
				
				long sum=0;
				for(int i3=0;i3<newArray.length;i3++) {
					if(newArray[i3]>=j) {
						sum+=newArray[i3]-j;
					}else {
						sum+=newArray[i3];
					}
				}
				if(sum<sumMin || sumMin==-1)	sumMin=sum;
			}
		}
        return sumMin;
    }
}
