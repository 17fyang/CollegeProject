package yuewen;

public class Q01 {
	
	
	public int binarySearch (int[] arr, int a) {
        int i=0;
        int j=arr.length;
        while(i<j){
            int mid=(i+j)/2;
            if(a==arr[mid])		break;
            if(a>mid)	i=mid+1;
            else j=mid-1;
        }
        if(i<j)	return (i+j)/2;
        return -1;
    }
}
