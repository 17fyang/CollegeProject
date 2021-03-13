package leetCode;

import java.util.Collections;

public class Q04 {
	public static void main(String[] args) {
		
	}
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int i=0;
		int j=0;
		int k=0;
		int num[]=new int[nums1.length+nums2.length];
		while(i<nums1.length && j<nums2.length) {
			if(nums1[i]>nums2[j]) {
				num[k]=nums2[j];
				j++;
			}else {
				num[k]=nums2[i];
				i++;
			}
			k++;
		}
		while(i<nums1.length) {
			num[k]=nums1[i];
			i++;
		}
		while(j<nums2.length) {
			num[k]=nums2[j];
			j++;
		}
		
		
//		int num[]=new int[nums1.length+nums2.length];
//		for(int i=0;i<num.length;i++) {
//			if(i<nums1.length)	num[i]=nums1[i];
//			else num[i]=nums2[i-nums1.length];
//		}
//		for(int i=0;i<num.length;i++) {
//			for(int j=i+1;j<num.length;j++) {
//				if(num[i]<num[j]) {
//					int temp=num[i];
//					num[i]=num[j];
//					num[j]=temp;
//				}
//			}
//		}
		int length=num.length;
		if(length%2==0)		return (num[length/2]+num[length/2-1])*1.0/2;
		else 		return num[length/2];
    }
}
